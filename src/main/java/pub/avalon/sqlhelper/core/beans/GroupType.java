package pub.avalon.sqlhelper.core.beans;

/**
 * 聚合类型
 *
 * @author 白超
 * @date 2019/6/17
 */
public enum GroupType implements ColumnHandler {
    /**
     * 最小值
     */
    MIN,
    /**
     * 最大值
     */
    MAX,
    /**
     * 总数
     */
    COUNT,
    /**
     * 总和
     */
    SUM,
    /**
     * 平均值
     */
    AVG,
    /**
     * 标准偏差
     */
    STDDEV,
    /**
     * 方差
     */
    VARIANCE
}
