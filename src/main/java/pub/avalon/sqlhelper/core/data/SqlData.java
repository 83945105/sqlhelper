package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.modelbuilder.TableModel;

/**
 * Sql数据
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public interface SqlData<T extends TableModel> extends SqlDataProducer<SqlData<T>>, SqlDataConsumer<T> {

    /**
     * 分裂
     *
     * @param clazz 目标类Model.class
     * @return SqlData
     */
    <T extends TableModel> SqlData<T> fission(Class<T> clazz);

}
