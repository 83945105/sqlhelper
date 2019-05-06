package pub.avalon.sqlhelper.core.engine;

import pub.avalon.beans.DataBaseType;
import pub.avalon.beans.LimitHandler;
import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.builder.SqlBuilder;
import pub.avalon.sqlhelper.core.data.SqlData;
import pub.avalon.sqlhelper.core.norm.Model;
import pub.avalon.sqlhelper.core.sql.Query;

/**
 * 分页引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class LimitIntactEngine<M extends Model<M, MC, MO, MW, MS, MG>,
        MC extends ColumnModel<M, MC, MO, MW, MS, MG>,
        MO extends OnModel<M, MC, MO, MW, MS, MG>,
        MW extends WhereModel<M, MC, MO, MW, MS, MG>,
        MS extends SortModel<M, MC, MO, MW, MS, MG>,
        MG extends GroupModel<M, MC, MO, MW, MS, MG>> extends SqlEngine<M> implements Query {

    LimitIntactEngine(Class<M> mainClass, DataBaseType dataBaseType) {
        super(mainClass, dataBaseType);
    }

    LimitIntactEngine(String tableName, Class<M> mainClass, DataBaseType dataBaseType) {
        super(tableName, mainClass, dataBaseType);
    }

    LimitIntactEngine(String tableName, Class<M> mainClass, String alias, DataBaseType dataBaseType) {
        super(tableName, mainClass, alias, dataBaseType);
    }

    public LimitIntactEngine limitTop(Integer num) {
        this.sqlData.buildLimitData(1, num);
        return this;
    }

    public LimitIntactEngine limitOne() {
        this.sqlData.buildLimitData(1, 1);
        return this;
    }

    public LimitIntactEngine limit(LimitHandler limit) {
        this.sqlData.setLimitData(limit);
        return this;
    }

    public LimitIntactEngine limit(Integer start, Integer end) {
        this.sqlData.setLimitStart(start);
        this.sqlData.setLimitEnd(end);
        return this;
    }

    public LimitIntactEngine limit(Integer total, Integer currentPage, Integer pageSize) {
        this.sqlData.buildLimitData(total, currentPage, pageSize);
        return this;
    }

    public SqlData<M> getData() {
        return this.sqlData;
    }

    @Override
    public SqlBuilder query() {
        return this.sqlBuilderProxy.query();
    }

    @Override
    public SqlBuilder queryCount() {
        return this.sqlBuilderProxy.queryCount();
    }

}
