package pub.avalonframework.sqlhelper.core.option;

/**
 * @author baichao
 */
public final class SqlPrintOptions {

    public final static SqlPrintOptions DEFAULT_SQL_PRINT_OPTIONS = new SqlPrintOptions();

    private boolean enabled = false;

    private boolean colour = false;

    private boolean sqlEnabled = true;

    private boolean argsEnabled = true;

    public boolean isEnabled() {
        return enabled;
    }

    public SqlPrintOptions setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public boolean isColour() {
        return colour;
    }

    public SqlPrintOptions setColour(boolean colour) {
        this.colour = colour;
        return this;
    }

    public boolean isSqlEnabled() {
        return sqlEnabled;
    }

    public SqlPrintOptions setSqlEnabled(boolean sqlEnabled) {
        this.sqlEnabled = sqlEnabled;
        return this;
    }

    public boolean isArgsEnabled() {
        return argsEnabled;
    }

    public SqlPrintOptions setArgsEnabled(boolean argsEnabled) {
        this.argsEnabled = argsEnabled;
        return this;
    }
}