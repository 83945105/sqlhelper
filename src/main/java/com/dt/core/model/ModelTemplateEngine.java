package com.dt.core.model;

import com.dt.core.converter.ColumnFieldConverter;
import com.dt.core.converter.HumpConverter;
import com.dt.core.jdbc.JdbcSourceEngine;
import org.fusesource.jansi.Ansi;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.*;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 模组模板引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class ModelTemplateEngine {

    private JdbcSourceEngine jdbcSourceEngine;

    private ColumnFieldConverter columnFieldConverter = new HumpConverter();

    private Map<String, String> generateTables = new LinkedHashMap<>();

    private String templateSuffix = "Model";

    private static final String PACKAGE_PATH_REGEX = "^([a-z,A-Z,$,_][a-z,A-Z,0-9,$,_,]*.?[a-z,A-Z,$,_][a-z,A-Z,0-9,$,_,]*|[a-z,A-Z,$,_][a-z,A-Z,0-9,$,_,]*)+";

    public ModelTemplateEngine(JdbcSourceEngine jdbcSourceEngine) {
        this.jdbcSourceEngine = jdbcSourceEngine;
    }

    public ModelTemplateEngine(JdbcSourceEngine jdbcSourceEngine, ColumnFieldConverter columnFieldConverter) {
        this.jdbcSourceEngine = jdbcSourceEngine;
        this.columnFieldConverter = columnFieldConverter;
    }

    public ModelTemplateEngine setTemplateSuffix(String templateSuffix) {
        if (templateSuffix == null) {
            throw new RuntimeException("templateSuffix can not be null.");
        }
        this.templateSuffix = templateSuffix;
        return this;
    }

    public ModelTemplateEngine addTable(String tableName, String alias) {
        try {
            if (!jdbcSourceEngine.isTableExist(tableName)) {
                throw new RuntimeException("table [" + tableName + "] does not exist.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        this.generateTables.put(tableName, alias);
        return this;
    }

    public ModelTemplateEngine addTable(String tableName) {
        try {
            if (!jdbcSourceEngine.isTableExist(tableName)) {
                throw new RuntimeException("table [" + tableName + "] does not exist.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        this.generateTables.put(tableName, columnFieldConverter.columnToField(tableName));
        return this;
    }

    public void process(String packagePath) throws SQLException {
        process("/", packagePath);
    }

    private static final String PROJECT_ROOT_PATH_SIGN = "/";
    private static final String PACKAGE_LINK_REGEX = "\\.";

    public void process(String projectPath, String packagePath) throws SQLException {
        if (projectPath == null || projectPath.trim().length() == 0) {
            throw new RuntimeException("projectPath can not be null or empty.");
        }
        if (!Pattern.matches(PACKAGE_PATH_REGEX, packagePath)) {
            throw new RuntimeException("packagePath format error.");
        }
        if (PROJECT_ROOT_PATH_SIGN.equals(projectPath.trim())) {
            projectPath = System.getProperty("user.dir");
        }

        StringBuilder path = new StringBuilder(projectPath)
                .append(File.separator)
                .append("src")
                .append(File.separator)
                .append("main")
                .append(File.separator)
                .append("java");
        for (String s : packagePath.split(PACKAGE_LINK_REGEX)) {
            path.append(File.separator).append(s);
        }
        path.append(File.separator);

        String pathPrefix = path.toString();

        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setCharacterEncoding("utf-8");
        resolver.setPrefix("templates/");
        resolver.setSuffix(".text");
        resolver.setTemplateMode(TemplateMode.TEXT);
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(resolver);
        Context context = new Context();
        Map<String, Object> setting;

        for (Map.Entry<String, String> entry : generateTables.entrySet()) {
            System.out.println(Ansi.ansi().eraseScreen()
                    .fg(Ansi.Color.BLUE)
                    .a("========================================================================================================================================================================================================\n")
                    .reset());
            setting = new HashMap<>(5);
            setting.put("packagePath", packagePath);
            setting.put("tableName", entry.getKey());
            setting.put("tableAlias", entry.getValue());
            setting.put("primaryKeyColumnInfo", jdbcSourceEngine.getPrimaryKeyColumn(entry.getKey(), columnFieldConverter));
            setting.put("templateSuffix", templateSuffix);

            context.setVariable("setting", setting);
            context.setVariable("columnInfoCollection", jdbcSourceEngine.getColumns(entry.getKey(), columnFieldConverter));

            String x = templateEngine.process("model", context);
            System.out.println(Ansi.ansi().eraseScreen().fg(Ansi.Color.YELLOW).a(x).reset());
            printFile(x, pathPrefix + entry.getValue() + templateSuffix + ".java");
            System.out.println(Ansi.ansi().eraseScreen()
                    .fg(Ansi.Color.BLUE)
                    .a("\n========================================================================================================================================================================================================")
                    .reset());
        }

        System.out.println(Ansi.ansi().eraseScreen()
                .fg(Ansi.Color.GREEN)
                .a("the generated java files are in the ")
                .fg(Ansi.Color.RED)
                .a(pathPrefix)
                .reset());
    }

    public void printFile(String content, String printPath) {
        File file = new File(printPath);
        OutputStreamWriter osw = null;
        FileOutputStream fos = null;
        try {
            if (!file.getParentFile().exists()) {
                if (file.getParentFile().mkdirs()) {
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                }
            }
            fos = new FileOutputStream(file);
            osw = new OutputStreamWriter(fos, "UTF-8");
            BufferedWriter writer = new BufferedWriter(osw);
            writer.write(content);
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

}
