package pub.avalon.sqlhelper.generator.beans;

/**
 * @author baichao
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