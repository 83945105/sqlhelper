package pub.avalon.sqlhelper.maven;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import pub.avalon.sqlhelper.generator.engine.TemplateEngineBuilder;
import pub.avalon.sqlhelper.generator.jdbc.JdbcTemplate;
import pub.avalon.sqlhelper.generator.jdbc.JdbcTemplateBuilder;

import java.util.List;

@Mojo(name = "generator", defaultPhase = LifecyclePhase.COMPILE)
public class GeneratorMojo extends AbstractMojo {

    @Parameter(required = true)
    private DataSource dataSource;

    @Parameter
    private GenerateOptions defaultGenerateOptions;

    @Parameter
    private OutputOptions defaultOutputOptions;

    @Parameter(required = true)
    private List<Table> tables;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        System.out.println(dataSource.getDriverClassName());
        System.out.println(dataSource.getJdbcUrl());
        System.out.println(dataSource.getUsername());
        System.out.println(dataSource.getPassword());
        JdbcTemplate jdbcTemplate = JdbcTemplateBuilder.newJdbcTemplateBuilder()
                .setDriverClassName(dataSource.getDriverClassName())
                .setJdbcUrl(dataSource.getJdbcUrl())
                .setUsername(dataSource.getUsername())
                .setPassword(dataSource.getPassword())
                .build();
        System.out.println("66666666666666666666666666666666");
        TemplateEngineBuilder.newTemplateEngineBuilder()
                .setJdbcTemplate(jdbcTemplate)
                .build()
                .setDefaultGenerateOptions(new pub.avalon.sqlhelper.generator.options.GenerateOptions().setPackagePath("pub.avalon.sqlhelper.readme.entity"))
                .addTable("sys_user", "SysUser")
                .addTable("role_resource", "RoleResource")
                .addTable("user_role", "UserRole")
                .generateClassFiles(new pub.avalon.sqlhelper.generator.options.OutputOptions("D://generateFiles"));

    }
}