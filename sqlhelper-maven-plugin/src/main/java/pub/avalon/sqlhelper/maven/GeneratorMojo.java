package pub.avalon.sqlhelper.maven;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name = "generator", defaultPhase = LifecyclePhase.COMPILE)
public class GeneratorMojo extends AbstractMojo {

    @Parameter(required = true)
    private String driverClassName;
    @Parameter(required = true)
    private String jdbcUrl;
    @Parameter(required = true)
    private String username;
    @Parameter(required = true)
    private String password;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        System.out.println("========================================");
        System.out.println("========================================");
    }
}
