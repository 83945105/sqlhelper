package pub.avalon.sqlhelper.core.sql;

/**
 * 查询
 *
 * @author 白超
 * @date 2018/8/20
 */
public interface Query<T> extends Sql {

    /**
     * 查询
     *
     * @return
     */
    T query();

    /**
     * 查询数量
     *
     * @return
     */
    T queryCount();

}
