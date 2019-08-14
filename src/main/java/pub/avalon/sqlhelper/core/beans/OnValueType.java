package pub.avalon.sqlhelper.core.beans;

/**
 * On值类型
 *
 * @author baichao
 * @since 2018/7/10
 */
public enum OnValueType {
    /**
     * 连接其它on字段
     */
    JOIN_ON,
    /**
     * 连接其他列字段
     */
    JOIN_COLUMN,
    /**
     * 具体值
     */
    VALUE
}
