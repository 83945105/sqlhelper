package pub.avalonframework.sqlhelper.core.builder;

import pub.avalonframework.sqlhelper.core.data.SqlPartDatum;
import pub.avalonframework.sqlhelper.core.helper.Helper;
import pub.avalonframework.sqlhelper.core.utils.ExceptionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
 */
public abstract class AbstractSqlPartDatumBuilder<T extends Helper, S extends SqlPartDatum> implements SqlPartDatumBuilder<T, S> {

    protected String tableAlias;
    private T helper;

    public AbstractSqlPartDatumBuilder(String tableAlias, T helper) {
        if (tableAlias == null) {
            ExceptionUtils.tableAliasNullException();
        }
        this.tableAlias = tableAlias;
        this.helper = helper;
    }

    private List<S> sqlPartData = null;

    @Override
    public String getTableAlias() {
        return this.tableAlias;
    }

    @Override
    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
    }

    @Override
    public T getHelper() {
        return this.helper;
    }

    @Override
    public void setHelper(T helper) {
        this.helper = helper;
    }

    @Override
    public void addSqlPartDatum(S sqlPartDatum) {
        if (sqlPartDatum == null) {
            return;
        }
        if (this.sqlPartData == null) {
            this.sqlPartData = new ArrayList<>();
        }
        this.sqlPartData.add(sqlPartDatum);
    }

    @Override
    public List<S> takeoutSqlPartData() {
        List<S> sqlModelData = this.sqlPartData;
        this.sqlPartData = null;
        return sqlModelData;
    }
}