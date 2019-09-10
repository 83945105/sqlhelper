package pub.avalon.sqlhelper.maven;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import pub.avalon.sqlhelper.generator.beans.StringConverter;
import pub.avalon.sqlhelper.generator.beans.TypeConverter;
import pub.avalon.sqlhelper.generator.engine.TemplateEngine;
import pub.avalon.sqlhelper.generator.engine.TemplateEngineBuilder;
import pub.avalon.sqlhelper.generator.jdbc.JdbcTemplate;
import pub.avalon.sqlhelper.generator.jdbc.JdbcTemplateBuilder;

/**
 * @author baichao
 */
@Mojo(name = "generate java file", defaultPhase = LifecyclePhase.COMPILE)
public class GeneratorJavaFileMojo extends AbstractGeneratorMojo {

    private pub.avalon.sqlhelper.generator.options.GenerateOptions convertGenerateOptions(GenerateOptions generateOptions) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (generateOptions == null) {
            return null;
        }
        pub.avalon.sqlhelper.generator.options.GenerateOptions bean = new pub.avalon.sqlhelper.generator.options.GenerateOptions();
        bean.setEntitySuffix(generateOptions.getEntitySuffix())
                .setEntityChainStyle(generateOptions.isEntityChainStyle())
                .setEntityFieldComment(generateOptions.isEntityFieldComment())
                .setHelperClassName(generateOptions.getHelperClassName())
                .setTypeConverter((TypeConverter) Class.forName(generateOptions.getTypeConverter()).newInstance())
                .setStringConverter((StringConverter) Class.forName(generateOptions.getStringConverter()).newInstance())
                .setPackagePath(generateOptions.getPackagePath())
                .addAllClassPath(generateOptions.getClassPaths());
        return bean;
    }

    private pub.avalon.sqlhelper.generator.options.OutputOptions convertOutputOptions(OutputOptions outputOptions) {
        if (outputOptions == null) {
            return null;
        }
        return new pub.avalon.sqlhelper.generator.options.OutputOptions(outputOptions.getFolderPath());
    }

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
                    .build();
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