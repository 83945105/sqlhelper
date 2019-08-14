package pub.avalon.sqlhelper.core.beans;

/**
 * 比较规则
 *
 * @author baichao
 * @since 2018/7/10
 */
public enum ComparisonRule {

    /**
     * 参数为null时跳过
     */
    NULL_SKIP,
    /**
     * 参数为null时抛出异常
     */
    NULL_THROW_EXCEPTION

}
