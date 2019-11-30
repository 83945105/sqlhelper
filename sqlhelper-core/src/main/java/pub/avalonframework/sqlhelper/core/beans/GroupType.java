package pub.avalonframework.sqlhelper.core.beans;

/**
 * @author baichao
 */
public enum GroupType implements ColumnHandler {
    /**
     * min
     */
    MIN("min"),
    /**
     * max
     */
    MAX("max"),
    /**
     * total
     */
    COUNT("count"),
    /**
     * sum
     */
    SUM("sum"),
    /**
     * average
     */
    AVG("avg"),
    /**
     * standard deviation
     */
    STDDEV("stddev"),
    /**
     * variance
     */
    VARIANCE("variance");

    private String methodName;

    GroupType(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public String execute(String columnSql) {
        return this.methodName + "(" + columnSql + ")";
    }
}