package pub.avalon.sqlhelper.core.sql;

/**
 * 查询
 *
 * @author 白超
 * @date 2018/8/20
 */
public interface QueryByPrimaryKey<T> extends Sql {

    /**
     * 根据主键查询
     *
     * @param keyValue 主键值
     * @return
     */
    T queryByPrimaryKey(Object keyValue);

}
