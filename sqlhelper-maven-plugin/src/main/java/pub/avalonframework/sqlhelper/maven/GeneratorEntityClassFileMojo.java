package pub.avalonframework.sqlhelper.maven;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import pub.avalonframework.sqlhelper.generator.engine.TemplateEngine;
import pub.avalonframework.sqlhelper.generator.engine.TemplateEngineBuilder;
import pub.avalonframework.sqlhelper.generator.jdbc.JdbcTemplate;
import pub.avalonframework.sqlhelper.generator.jdbc.JdbcTemplateBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
 */
@Mojo(name = "generate entity class file",
        defaultPhase = LifecyclePhase.PROCESS_CLASSES,
        requiresDependencyResolution = ResolutionScope.COMPILE_PLUS_RUNTIME)
public class GeneratorEntityClassFileMojo extends AbstractGeneratorMojo {

    @Parameter(property = "project.compileClasspathElements", required = true, readonly = true)
    private List<String> classpaths;

    private String formatter(String str) {
        return str.replaceAll("[ \t\n]*", "");
    }

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        StringBuilder classPathsBuilder = new StringBuilder();
        for (String classpath : classpaths) {
            classPathsBuilder.append(classpath).append(";");
        }
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
                        templateEngine.generateClassFile(table.getTableName(), convertOutputOptions(dataSource.defaultOutputOptions)
                                .setCompileOptions(new ArrayList<String>() {{
                                    add("-classpath");
                                    add(classPathsBuilder.toString());
                                }}));
                    } else {
                        templateEngine.generateClassFile(table.getTableName(), convertOutputOptions(table.getOutputOptions())
                                .setCompileOptions(new ArrayList<String>() {{
                                    add("-classpath");
                                    add(classPathsBuilder.toString());
                                }}));
                    }
                }
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
    }
}