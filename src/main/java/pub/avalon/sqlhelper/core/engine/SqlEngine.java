package pub.avalon.sqlhelper.core.engine;

import pub.avalon.beans.DataBaseType;
import pub.avalon.sqlhelper.core.build.SqlBuilderProxy;
import pub.avalon.sqlhelper.core.data.FinalSqlData;
import pub.avalon.sqlhelper.core.data.MainTableData;
import pub.avalon.sqlhelper.core.norm.Model;

/**
 * 引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
@SuppressWarnings("unused")
public class SqlEngine<M extends Model> {

    protected FinalSqlData<M> sqlData;

    protected SqlBuilderProxy sqlBuilderProxy;

    SqlEngine(Class<M> mainClass, DataBaseType dataBaseType) {
        MainTableData<M> data = new MainTableData<>(mainClass);
        this.sqlData = new FinalSqlData<>(dataBaseType);
        this.sqlData.setMainTableData(data);
        this.sqlBuilderProxy = new SqlBuilderProxy(this.sqlData);
    }

    SqlEngine(Class<M> mainClass, String tableName, DataBaseType dataBaseType) {
        MainTableData<M> data = new MainTableData<>(mainClass);
        data.setTableName(tableName);
        this.sqlData = new FinalSqlData<>(dataBaseType);
        this.sqlData.setMainTableData(data);
        this.sqlBuilderProxy = new SqlBuilderProxy(this.sqlData);
    }

}
