package pub.avalon.sqlhelper.maven.beans;

import org.apache.maven.plugins.annotations.Parameter;

/**
 * @author baichao
 */
public class DataSourceConfig {

    @Parameter(required = true)
    private String driverClassName;

    @Parameter(required = true)
    private String jdbcUrl;

    @Parameter(required = true)
    private String username;

    @Parameter(required = true)
    private String password;
}