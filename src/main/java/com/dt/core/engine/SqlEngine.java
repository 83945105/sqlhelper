package com.dt.core.engine;

import com.dt.core.bean.DataBaseType;
import com.dt.core.build.SqlBuilderProxy;
import com.dt.core.data.FinalSqlData;
import com.dt.core.data.MainTableData;
import com.dt.core.norm.Model;

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
