package pub.avalonframework.sqlhelper.generator.engine;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.avalonframework.sqlhelper.generator.beans.Column;
import pub.avalonframework.sqlhelper.generator.beans.Table;
import pub.avalonframework.sqlhelper.generator.jdbc.JdbcTemplate;
import pub.avalonframework.sqlhelper.generator.options.GenerateOptions;
import pub.avalonframework.sqlhelper.generator.options.OutputOptions;

import javax.tools.*;
import java.io.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author baichao
 */
public final class TableHelperTemplateEngine implements TemplateEngine {

    private final Logger logger = LoggerFactory.getLogger(TableHelperTemplateEngine.class);

    private final static String PROJECT_ROOT_PATH_SIGN = "/";

    private final static String PACKAGE_LINK_REGEX = "\\.";

    private JdbcTemplate jdbcTemplate;

    private GenerateOptions defaultGenerateOptions;

    private OutputOptions defaultOutputOptions;

    private Map<String, Table> tables = new LinkedHashMap<>();

    public TableHelperTemplateEngine(JdbcTemplate jdbcTemplate, GenerateOptions defaultGenerateOptions) {
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
        Map<String, Object> setting = new HashMap<>(1);
        setting.put("packagePath", packagePath);
        Properties p = new Properties();
        p.setProperty("file.resource.loader.class", ClasspathResourceLoader.class.getName());
        p.setProperty(Velocity.ENCODING_DEFAULT, StandardCharsets.UTF_8.name());
        p.setProperty(Velocity.INPUT_ENCODING, StandardCharsets.UTF_8.name());
        p.setProperty(Velocity.OUTPUT_ENCODING, StandardCharsets.UTF_8.name());
        VelocityEngine velocityEngine = new VelocityEngine(p);
        Template template = velocityEngine.getTemplate("/templates/table-helper.java.vm", StandardCharsets.UTF_8.name());
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("table", table);
        velocityContext.put("setting", setting);
        StringWriter writer = new StringWriter();
        template.merge(velocityContext, writer);
        String javaCode = writer.toString();
        this.logger.info("Generate Java Code For Table " + tableName);
        return javaCode;
    }

    @Override
    public void generateJavaFile(String tableName, OutputOptions outputOptions) {
        String javaCode = generateJavaCode(tableName, outputOptions);
        Table table = getTable(tableName);
        String folderPath = buildPathPrefix(outputOptions.getFolderPath(), table.getGenerateOptions().getPackagePath());
        printJavaFile(table.getTableHelperJavaFileName(), folderPath, javaCode);
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
        printClassFile(table.getTableHelperJavaFileName(), folderPath, javaCode, outputOptions.getFolderPath(), outputOptions.getCompileOptions());
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

    private void printClassFile(final String javaFileName, final String folderPath, final String javaCode, final String outputPath, final List<String> compileOptions) {
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager standardFileManager = javaCompiler.getStandardFileManager(null, null,
                null);
        StringJavaObject stringJavaObject = new StringJavaObject(javaFileName, javaCode);
        Iterable<? extends JavaFileObject> javaFileObjects = Arrays.asList(stringJavaObject);
        File file = new File(folderPath);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                throw new RuntimeException("create folder [" + folderPath + "] fail.");
            }
        }
        List<String> options = new ArrayList<>(Arrays.asList("-d", outputPath));
        if (compileOptions != null) {
            options.addAll(compileOptions);
        }
        JavaCompiler.CompilationTask task = javaCompiler.getTask(null, standardFileManager, null,
                options, null, javaFileObjects);
        if (!task.call()) {
            throw new RuntimeException("Compile java file " + javaFileName + " fail. javaCode\n" + javaCode);
        }
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
}