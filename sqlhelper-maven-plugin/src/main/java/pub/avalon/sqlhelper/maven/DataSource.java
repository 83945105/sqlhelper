package pub.avalon.sqlhelper.maven;

import org.apache.maven.plugins.annotations.Parameter;

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