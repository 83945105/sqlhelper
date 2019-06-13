package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.helper.TableHelper;

/**
 * Sql数据
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public interface SqlData<T extends TableHelper> extends SqlDataProducer, SqlDataConsumer<T> {

}
