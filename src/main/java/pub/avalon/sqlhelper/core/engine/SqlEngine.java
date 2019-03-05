package pub.avalon.sqlhelper.core.engine;

import pub.avalon.beans.DataBaseType;
import pub.avalon.sqlhelper.core.build.SqlBuilderProxy;
import pub.avalon.sqlhelper.core.data.FinalSqlData;
import pub.avalon.sqlhelper.core.data.MainTableData;
import pub.avalon.sqlhelper.core.data.SqlData;
import pub.avalon.sqlhelper.core.norm.Model;

/**
 * 引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class SqlEngine<M extends Model> {

    protected SqlData<M> sqlData;

    SqlBuilderProxy sqlBuilderProxy;

    SqlEngine(Class<M> mainClass, DataBaseType dataBaseType) {
        this.sqlData = new FinalSqlData<>(dataBaseType, new MainTableData<>(mainClass));
        this.sqlBuilderProxy = new SqlBuilderProxy(this.sqlData);
    }

    SqlEngine(String tableName, Class<M> mainClass, DataBaseType dataBaseType) {
        MainTableData<M> data = new MainTableData<>(mainClass);
        data.setTableName(tableName);
        this.sqlData = new FinalSqlData<>(dataBaseType, data);
        this.sqlBuilderProxy = new SqlBuilderProxy(this.sqlData);
    }

    SqlEngine(String tableName, Class<M> mainClass, String alias, DataBaseType dataBaseType) {
        MainTableData<M> data = new MainTableData<>(mainClass);
        data.setTableName(tableName);
        data.setTableAlias(alias);
        this.sqlData = new FinalSqlData<>(dataBaseType, data);
        this.sqlBuilderProxy = new SqlBuilderProxy(this.sqlData);
    }

    public SqlData<M> getSqlData() {
        return sqlData;
    }
}
