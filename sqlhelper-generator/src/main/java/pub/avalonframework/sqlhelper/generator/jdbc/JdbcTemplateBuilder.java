package pub.avalonframework.sqlhelper.generator.jdbc;

/**
 * @author baichao
 */
public final class JdbcTemplateBuilder {

    private String driverClassName;

    private String jdbcUrl;

    private String username;

    private String password;

    private JdbcTemplateBuilder() {
    }

    public static JdbcTemplateBuilder newJdbcTemplateBuilder() {
        return new JdbcTemplateBuilder();
    }

    public JdbcTemplate build() {
        if (driverClassName == null) {
            throw new RuntimeException("DriverClassName can not be null.");
        }
        if (jdbcUrl == null) {
            throw new RuntimeException("JdbcUrl can not be null.");
        }
        if (username == null) {
            throw new RuntimeException("Username can not be null.");
        }
        if (password == null) {
            throw new RuntimeException("Password can not be null.");
        }
        if (driverClassName.toLowerCase().contains("mysql")) {
            return new MySqlJdbcTemplate(driverClassName, jdbcUrl, username, password);
        }
        throw new RuntimeException("This database is not supported at this time.");
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public JdbcTemplateBuilder setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
        return this;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public JdbcTemplateBuilder setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public JdbcTemplateBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public JdbcTemplateBuilder setPassword(String password) {
        this.password = password;
        return this;
    }
}