package pub.avalon.sqlhelper.generator.engine;

import org.fusesource.jansi.Ansi;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import pub.avalon.sqlhelper.generator.beans.Column;
import pub.avalon.sqlhelper.generator.beans.Table;
import pub.avalon.sqlhelper.generator.jdbc.JdbcTemplate;
import pub.avalon.sqlhelper.generator.option.GenerateOptions;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 模板引擎
 *
 * @author baichao
 * @date 2019/6/5
 */
public class TemplateEngine {

    private JdbcTemplate jdbcTemplate;

    private List<Table> tables = new ArrayList<>();

    public TemplateEngine(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 生成配置
     */
    private GenerateOptions generateOptions = GenerateOptions.GENERATE_OPTIONS;

    public TemplateEngine setGenerateOptions(GenerateOptions generateOptions) {
        this.generateOptions = generateOptions;
        return this;
    }

    public TemplateEngine addTable(String tableName, String tableAlias) throws SQLException {
        return this.addTable(tableName, tableAlias, this.generateOptions);
    }

    public TemplateEngine addTable(String tableName, String tableAlias, GenerateOptions generateOptions) throws SQLException {
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
        this.tables.add(
                new Table()
                        .setTableName(tableName)
                        .setTableAlias(tableAlias)
                        .setGenerateOptions(generateOptions)
                        .setPrimaryKeyColumn(primaryKeyColumn)
                        .setColumns(columns)
        );
        return this;
    }

    private static final String PACKAGE_PATH_REGEX = "^([a-z,A-Z,$,_][a-z,A-Z,0-9,$,_,]*.?[a-z,A-Z,$,_][a-z,A-Z,0-9,$,_,]*|[a-z,A-Z,$,_][a-z,A-Z,0-9,$,_,]*)+";
    private static final String PROJECT_ROOT_PATH_SIGN = "/";
    private static final String PACKAGE_LINK_REGEX = "\\.";

    public void generate(String projectPath, String packagePath) {
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
        org.thymeleaf.TemplateEngine templateEngine = new org.thymeleaf.TemplateEngine();
        templateEngine.setTemplateResolver(resolver);
        Context context = new Context();
        Map<String, Object> setting;

        for (Table table : tables) {
            System.out.println(Ansi.ansi().eraseScreen()
                    .fg(Ansi.Color.BLUE)
                    .a("========================================================================================================================================================================================================\n")
                    .reset());
            setting = new HashMap<>(1);
            setting.put("packagePath", packagePath);

            context.setVariable("table", table);
            context.setVariable("setting", setting);

            // 根据配置判断是否哪个模板
            String template = "";
            if (table.getGenerateOptions().isEntity()) {
                // 生成实体类
                if (table.getGenerateOptions().isEntityAlone()) {
                    // 实体类与helper类分离
                    template = "entity";
                    String x = templateEngine.process(template, context);
                    System.out.println(Ansi.ansi().eraseScreen().fg(Ansi.Color.YELLOW).a(x).reset());
                    printFile(x, pathPrefix + table.getTableAlias() + table.getGenerateOptions().getEntitySuffix() + ".java");
                    System.out.println(Ansi.ansi().eraseScreen()
                            .fg(Ansi.Color.BLUE)
                            .a("\n========================================================================================================================================================================================================")
                            .reset());
                    template = "helper";
                    x = templateEngine.process(template, context);
                    System.out.println(Ansi.ansi().eraseScreen().fg(Ansi.Color.YELLOW).a(x).reset());
                    printFile(x, pathPrefix + table.getTableAlias() + table.getGenerateOptions().getHelperClassName() + ".java");
                    System.out.println(Ansi.ansi().eraseScreen()
                            .fg(Ansi.Color.BLUE)
                            .a("\n========================================================================================================================================================================================================")
                            .reset());
                } else {
                    // 实体类与helper类一起
                    template = "entity-helper";
                    String x = templateEngine.process(template, context);
                    System.out.println(Ansi.ansi().eraseScreen().fg(Ansi.Color.YELLOW).a(x).reset());
                    printFile(x, pathPrefix + table.getTableAlias() + table.getGenerateOptions().getEntitySuffix() + ".java");
                    System.out.println(Ansi.ansi().eraseScreen()
                            .fg(Ansi.Color.BLUE)
                            .a("\n========================================================================================================================================================================================================")
                            .reset());
                }
            } else {
                // 不生成实体类
                template = "helper";
                String x = templateEngine.process(template, context);
                System.out.println(Ansi.ansi().eraseScreen().fg(Ansi.Color.YELLOW).a(x).reset());
                printFile(x, pathPrefix + table.getTableAlias() + table.getGenerateOptions().getHelperClassName() + ".java");
                System.out.println(Ansi.ansi().eraseScreen()
                        .fg(Ansi.Color.BLUE)
                        .a("\n========================================================================================================================================================================================================")
                        .reset());
            }
        }
        System.out.println(Ansi.ansi().eraseScreen()
                .fg(Ansi.Color.GREEN)
                .a("the generated java files are in the ")
                .fg(Ansi.Color.RED)
                .a(pathPrefix)
                .reset());
    }

    private void printFile(String content, String printPath) {
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
            osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
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
