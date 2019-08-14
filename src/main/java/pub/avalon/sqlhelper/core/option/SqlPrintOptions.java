package pub.avalon.sqlhelper.core.option;

/**
 * sql打印配置
 *
 * @author baichao
 * @date 2019/5/20
 */
public final class SqlPrintOptions {

    public final static SqlPrintOptions DEFAULT_SQL_PRINT_OPTIONS = new SqlPrintOptions();

    /**
     * 是否开启
     */
    private boolean enabled = false;

    /**
     * 是否打印颜色
     */
    private boolean colour = false;

    /**
     * 是否开启打印sql
     */
    private boolean sqlEnabled = true;

    /**
     * 是否开启打印参数
     */
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
