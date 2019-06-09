package pub.avalon.sqlhelper.generator.beans;

/**
 * 数据库列
 *
 * @author 白超
 * @date 2019/6/4
 */
public interface Column {

    /**
     * 名称
     *
     * @return
     */
    String getName();

    /**
     * 类型
     *
     * @return
     */
    String getType();

    /**
     * 备注
     *
     * @return
     */
    String getComment();

}
