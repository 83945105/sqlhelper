package pub.avalonframework.sqlhelper.maven;

import org.apache.maven.plugins.annotations.Parameter;

import java.util.List;

/**
 * @author baichao
 */
public class DataSource {

    @Parameter(required = true)
    private String driverClassName;

    @Parameter(required = true)
    private String jdbcUrl;

    @Parameter(required = true)
    private String username;

    @Parameter(required = true)
    private String password;

    @Parameter
    protected GenerateOptions defaultGenerateOptions = GenerateOptions.DEFAULT_GENERATE_OPTIONS;

    @Parameter
    protected OutputOptions defaultOutputOptions = OutputOptions.DEFAULT_OUTPUT_OPTIONS;

    @Parameter(required = true)
    protected List<Table> tables;

    public String getDriverClassName() {
        return driverClassName;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}