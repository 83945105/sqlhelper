package pub.avalon.sqlhelper.core.beans;

/**
 * 列类型
 *
 * @author baichao
 * @date 2019/7/24
 */
public enum ColumnType {
    /**
     * 默认
     */
    DEFAULT,
    /**
     * 虚拟
     */
    VIRTUAL,
    /**
     * 子查询
     */
    SUB_QUERY,
    /**
     * 操作列
     */
    HANDLER,
    /**
     * sql片段
     */
    SQL_PART

}
