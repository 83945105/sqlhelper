package pub.avalon.sqlhelper.generator.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import pub.avalon.sqlhelper.generator.beans.Column;
import pub.avalon.sqlhelper.generator.beans.Table;
import pub.avalon.sqlhelper.generator.jdbc.JdbcTemplate;
import pub.avalon.sqlhelper.generator.options.GenerateOptions;
import pub.avalon.sqlhelper.generator.options.OutputOptions;

import javax.tools.*;
import java.io.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author baichao
 */
public final class DefaultTemplateEngine implements TemplateEngine {

    private final Logger logger = LoggerFactory.getLogger(DefaultTemplateEngine.class);

    private final static String PROJECT_ROOT_PATH_SIGN = "/";

    private final static String PACKAGE_LINK_REGEX = "\\.";

    private JdbcTemplate jdbcTemplate;

    private GenerateOptions defaultGenerateOptions;

    private OutputOptions defaultOutputOptions;

    private Map<String, Table> tables = new LinkedHashMap<>();

    public DefaultTemplateEngine(JdbcTemplate jdbcTemplate, GenerateOptions defaultGenerateOptions) {
        this.jdbcTemplate = jdbcTemplate;
        this.defaultGenerateOptions = defaultGenerateOptions;
    }

    @Override
    public TemplateEngine addTable(String tableName, String tableAlias) {
        return this.addTable(tableName, tableAlias, this.defaultGenerateOptions);
    }

    @Override
    public TemplateEngine addTable(String tableName, String tableAlias, GenerateOptions generateOptions) {
        boolean tableExist = false;
        try {
            tableExist = this.jdbcTemplate.isTableExist(tableName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!tableExist) {
            throw new RuntimeException("table [" + tableName + "] not exist.");
        }
        Column primaryKeyColumn = this.jdbcTemplate.selectPrimaryKeyColumn(tableName);
        List<Column> columns = this.jdbcTemplate.selectColumns(tableName);
        this.tables.put(tableName, new Table()
                .setTableName(tableName)
                .setTableAlias(tableAlias)
                .setGenerateOptions(generateOptions)
                .setPrimaryKeyColumn(primaryKeyColumn)
                .setColumns(columns)
        );
        return this;
    }

    @Override
    public TemplateEngine setTables(Map<String, String> tableNameAliasMap) {
        if (tableNameAliasMap == null) {
            return this;
        }
        for (Map.Entry<String, String> entry : tableNameAliasMap.entrySet()) {
            this.addTable(entry.getKey(), entry.getValue());
        }
        return this;
    }

    @Override
    public TemplateEngine setTables(Map<String, String> tableNameAliasMap, GenerateOptions generateOptions) {
        if (tableNameAliasMap == null) {
            return this;
        }
        for (Map.Entry<String, String> entry : tableNameAliasMap.entrySet()) {
            this.addTable(entry.getKey(), entry.getValue(), generateOptions);
        }
        return this;
    }

    @Override
    public GenerateOptions getDefaultGenerateOptions() {
        return defaultGenerateOptions;
    }

    @Override
    public TemplateEngine setDefaultGenerateOptions(GenerateOptions defaultGenerateOptions) {
        if (defaultGenerateOptions == null) {
            return this;
        }
        this.defaultGenerateOptions = defaultGenerateOptions;
        return this;
    }

    @Override
    public OutputOptions getDefaultOutputOptions() {
        return defaultOutputOptions;
    }

    @Override
    public TemplateEngine setDefaultOutputOptions(OutputOptions defaultOutputOptions) {
        this.defaultOutputOptions = defaultOutputOptions;
        return this;
    }

    private Table getTable(String tableName) {
        Table table = tables.get(tableName);
        if (table == null) {
            throw new RuntimeException("Table " + tableName + " does not exist.");
        }
        return table;
    }

    private String buildPathPrefix(String folderPath, String packagePath) {
        if (folderPath == null || folderPath.trim().length() == 0) {
            throw new RuntimeException("folderPath can not be null or empty.");
        }
        if (PROJECT_ROOT_PATH_SIGN.equals(folderPath.trim())) {
            folderPath = System.getProperty("user.dir");
        }
        StringBuilder path = new StringBuilder(folderPath);
        if (packagePath == null) {
            return path.append(File.separator).toString();
        }
        for (String packagePathPart : packagePath.split(PACKAGE_LINK_REGEX)) {
            path.append(File.separator).append(packagePathPart);
        }
        return path.append(File.separator).toString();
    }

    @Override
    public String generateJavaCode(String tableName, OutputOptions outputOptions) {
        Table table = getTable(tableName);
        GenerateOptions generateOptions = table.getGenerateOptions();
        String packagePath = generateOptions.getPackagePath();
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setCharacterEncoding("utf-8");
        resolver.setPrefix("templates/");
        resolver.setSuffix(".text");
        resolver.setTemplateMode(TemplateMode.TEXT);
        org.thymeleaf.TemplateEngine templateEngine = new org.thymeleaf.TemplateEngine();
        templateEngine.setTemplateResolver(resolver);
        Context context = new Context();
        Map<String, Object> setting = new HashMap<>(1);
        setting.put("packagePath", packagePath);
        context.setVariable("table", table);
        context.setVariable("setting", setting);
        String template = "entity-helper";
        String javaCode = templateEngine.process(template, context);
        this.logger.info("Generate Java Code For Table " + tableName);
        return javaCode;
    }

    @Override
    public void generateJavaFile(String tableName, OutputOptions outputOptions) {
        String javaCode = generateJavaCode(tableName, outputOptions);
        Table table = getTable(tableName);
        String folderPath = buildPathPrefix(outputOptions.getFolderPath(), table.getGenerateOptions().getPackagePath());
        printJavaFile(table.getJavaFileName(), folderPath, javaCode);
        this.logger.info("Generate Java File For Table " + tableName);
    }

    @Override
    public void generateJavaFiles(OutputOptions outputOptions) {
        for (Map.Entry<String, Table> entry : tables.entrySet()) {
            this.generateJavaFile(entry.getKey(), outputOptions);
        }
    }

    @Override
    public void generateClassFile(String tableName, OutputOptions outputOptions) {
        String javaCode = generateJavaCode(tableName, outputOptions);
        Table table = getTable(tableName);
        String folderPath = buildPathPrefix(outputOptions.getFolderPath(), table.getGenerateOptions().getPackagePath());
        printClassFile(table.getJavaFileName(), folderPath, javaCode, outputOptions.getFolderPath());
        this.logger.info("Generate Class File For Table " + tableName);
    }

    @Override
    public void generateClassFiles(OutputOptions outputOptions) {
        for (Map.Entry<String, Table> entry : tables.entrySet()) {
            this.generateClassFile(entry.getKey(), outputOptions);
        }
    }

    private void printJavaFile(final String javaFileName, final String folderPath, final String javaCode) {
        File file = new File(folderPath + javaFileName + ".java");
        OutputStreamWriter osw = null;
        FileOutputStream fos = null;
        try {
            if (!file.getParentFile().exists()) {
                if (file.getParentFile().mkdirs()) {
                    if (!file.exists()) {
                        boolean success = file.createNewFile();
                        if (!success) {
                            throw new RuntimeException("create new file fail.");
                        }
                    }
                }
            }
            fos = new FileOutputStream(file);
            osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
            BufferedWriter writer = new BufferedWriter(osw);
            writer.write(javaCode);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (osw != null) {
                try {
                    osw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void printClassFile(final String javaFileName, final String folderPath, final String javaCode, final String outputPath) {
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();

        StandardJavaFileManager standardFileManager = javaCompiler.getStandardFileManager(null, null,
                null);


/*        try {
            standardFileManager.setLocation(StandardLocation.CLASS_PATH, getFiles(new File(""), JAR_FILE_SUFFIX));
            standardFileManager.setLocation(StandardLocation.SOURCE_PATH, getFiles(new File(""), JAVA_FILE_SUFFIX));
            standardFileManager.setLocation(StandardLocation.CLASS_OUTPUT, Collections.singletonList(new File(outputPath)));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }*/

        StringJavaObject stringJavaObject = new StringJavaObject(javaFileName, javaCode);
        Iterable<? extends JavaFileObject> javaFileObjects = Arrays.asList(stringJavaObject);
        File file = new File(folderPath);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                throw new RuntimeException("create folder [" + folderPath + "] fail.");
            }
        }
        List<String> options = new ArrayList<>();
        options.addAll(Arrays.asList("-d", outputPath));
        options.addAll(Arrays.asList("-classpath", System.getProperty("java.class.path")));

        JavaCompiler.CompilationTask task = javaCompiler.getTask(null, standardFileManager, null,
                options, null, javaFileObjects);
        if (!task.call()) {
            throw new RuntimeException("Compile java file " + javaFileName + " fail. javaCode\n" + javaCode);
        }
        //        standardFileManager.setLocation(StandardLocation.CLASS_PATH, new ArrayList<File>(){{
//            add(new File(""));
//        }});
//        optionsList.addAll(Arrays.asList("-classpath", System.getProperty("java.class.path")));
//        optionsList.addAll(Arrays.asList("classpath","/lib/framework-core.jar"));
    }

    private static class StringJavaObject extends SimpleJavaFileObject {
        private String content;

        private StringJavaObject(String name, String content) {
            super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
            this.content = content;
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
            return content;
        }
    }

    private final static String JAR_FILE_SUFFIX = ".jar";
    private final static String JAVA_FILE_SUFFIX = ".java";

    private List<File> getFiles(File sourceFile, String suffix) {
        if (sourceFile == null || !sourceFile.exists()) {
            return Collections.emptyList();
        }
        if (sourceFile.isDirectory()) {
            File[] files = sourceFile.listFiles(pathname -> pathname.isDirectory() || pathname.getName().endsWith(suffix));
            if (files == null) {
                return Collections.emptyList();
            }
            List<File> jarFiles = new ArrayList<>();
            for (File file : files) {
                jarFiles.addAll(getFiles(file, suffix));
            }
            return jarFiles;
        }
        if (sourceFile.getName().endsWith(suffix)) {
            return Collections.singletonList(sourceFile);
        }
        return Collections.emptyList();
    }
}