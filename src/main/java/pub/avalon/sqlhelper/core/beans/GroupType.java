package pub.avalon.sqlhelper.core.beans;

/**
 * 聚合类型
 *
 * @author baichao
 * @date 2019/6/17
 */
public enum GroupType implements ColumnHandler {
    /**
     * 最小值
     */
    MIN("min"),
    /**
     * 最大值
     */
    MAX("max"),
    /**
     * 总数
     */
    COUNT("count"),
    /**
     * 总和
     */
    SUM("sum"),
    /**
     * 平均值
     */
    AVG("avg"),
    /**
     * 标准偏差
     */
    STDDEV("stddev"),
    /**
     * 方差
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
