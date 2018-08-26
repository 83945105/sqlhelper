package pub.avalon.sqlhelper.core.sql;

/**
 * 删除
 *
 * @author 白超
 * @date 2018/8/20
 */
public interface Delete<T> extends Sql {

    /**
     * 删除
     *
     * @return
     */
    T delete();

}
