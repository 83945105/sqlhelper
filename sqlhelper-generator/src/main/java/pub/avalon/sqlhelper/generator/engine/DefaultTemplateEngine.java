package pub.avalon.sqlhelper.generator.engine;

import org.fusesource.jansi.Ansi;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import pub.avalon.sqlhelper.generator.beans.Column;
import pub.avalon.sqlhelper.generator.beans.Table;
import pub.avalon.sqlhelper.generator.jdbc.JdbcTemplate;
import pub.avalon.sqlhelper.generator.options.GenerateOptions;
import pub.avalon.sqlhelper.generator.options.OutputOptions;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author baichao
 */
public final class DefaultTemplateEngine implements TemplateEngine {

    private JdbcTemplate jdbcTemplate;

    private GenerateOptions generateOptions;

    private List<Table> tables = new ArrayList<>();

    public DefaultTemplateEngine(JdbcTemplate jdbcTemplate, GenerateOptions generateOptions) {
        this.jdbcTemplate = jdbcTemplate;
        this.generateOptions = generateOptions;
    }

    @Override
    public TemplateEngine addTable(String tableName, String tableAlias) {
        return this.addTable(tableName, tableAlias, this.generateOptions);
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
    public String generateJavaCode(String tableName, OutputOptions outputOptions) {
        return null;
    }

    @Override
    public void generateJavaFile(String tableName, OutputOptions outputOptions) {

    }

    @Override
    public void generateJavaFiles(OutputOptions outputOptions) {

        String folderPath = outputOptions.getFolderPath();

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

    @Override
    public void generateClassFile(String tableName, OutputOptions outputOptions) {

    }

    @Override
    public void generateClassFiles(OutputOptions outputOptions) {

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
