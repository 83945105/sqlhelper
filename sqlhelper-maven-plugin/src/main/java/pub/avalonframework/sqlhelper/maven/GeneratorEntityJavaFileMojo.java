package pub.avalonframework.sqlhelper.maven;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import pub.avalonframework.sqlhelper.generator.engine.TemplateEngine;
import pub.avalonframework.sqlhelper.generator.engine.TemplateEngineBuilder;
import pub.avalonframework.sqlhelper.generator.jdbc.JdbcTemplate;
import pub.avalonframework.sqlhelper.generator.jdbc.JdbcTemplateBuilder;

/**
 * @author baichao
 */
@Mojo(name = "generate entity java file", defaultPhase = LifecyclePhase.COMPILE)
public class GeneratorEntityJavaFileMojo extends AbstractGeneratorMojo {

    private String formatter(String str) {
        return str.replaceAll("[ \t\n]*", "");
    }

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        for (DataSource dataSource : dataSources) {
            JdbcTemplate jdbcTemplate = JdbcTemplateBuilder.newJdbcTemplateBuilder()
                    .setDriverClassName(formatter(dataSource.getDriverClassName()))
                    .setJdbcUrl(formatter(dataSource.getJdbcUrl()))
                    .setUsername(formatter(dataSource.getUsername()))
                    .setPassword(formatter(dataSource.getPassword()))
                    .build();
            TemplateEngine templateEngine = TemplateEngineBuilder.newTemplateEngineBuilder()
                    .setJdbcTemplate(jdbcTemplate)
                    .buildEntityTemplateEngine();
            try {
                templateEngine.setDefaultGenerateOptions(convertGenerateOptions(dataSource.defaultGenerateOptions));
                for (Table table : dataSource.tables) {
                    if (table.getGenerateOptions() == null) {
                        templateEngine.addTable(table.getTableName(), table.getTableAlias());
                    } else {
                        templateEngine.addTable(table.getTableName(), table.getTableAlias(), convertGenerateOptions(table.getGenerateOptions()));
                    }
                    if (table.getOutputOptions() == null) {
                        templateEngine.generateJavaFile(table.getTableName(), convertOutputOptions(dataSource.defaultOutputOptions));
                    } else {
                        templateEngine.generateJavaFile(table.getTableName(), convertOutputOptions(table.getOutputOptions()));
                    }
                }
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
    }
}