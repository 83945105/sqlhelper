package pub.avalon.sqlhelper.core.builder;

import pub.avalon.sqlhelper.core.data.SqlPartDatum;
import pub.avalon.sqlhelper.core.helper.Helper;
import pub.avalon.sqlhelper.core.utils.ExceptionUtils;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Sql片段数据构建器基类
 *
 * @author 白超
 * @date 2019/5/2
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

    private Set<S> sqlPartData = null;

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
            this.sqlPartData = new LinkedHashSet<>();
        }
        this.sqlPartData.add(sqlPartDatum);
    }

    @Override
    public Set<S> takeoutSqlPartData() {
        Set<S> sqlModelData = this.sqlPartData;
        this.sqlPartData = null;
        return sqlModelData;
    }

}
