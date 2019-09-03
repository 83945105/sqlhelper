package pub.avalon.sqlhelper.maven;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.util.List;

public abstract class AbstractGeneratorMojo extends AbstractMojo {

    @Parameter(required = true)
    protected DataSource dataSource;

    @Parameter
    protected GenerateOptions defaultGenerateOptions = GenerateOptions.DEFAULT_GENERATE_OPTIONS;

    @Parameter
    protected OutputOptions defaultOutputOptions = OutputOptions.DEFAULT_OUTPUT_OPTIONS;

    @Parameter(required = true)
    protected List<Table> tables;
}