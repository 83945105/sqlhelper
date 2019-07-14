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
public abstract class AbstractSqlPartDatumBuilder<S extends Helper<S, E>, E extends SqlPartDatum> implements SqlPartDatumBuilder<S, E> {

    protected String tableAlias;

    public AbstractSqlPartDatumBuilder(String tableAlias) {
        if (tableAlias == null) {
            ExceptionUtils.tableAliasNullException();
        }
        this.tableAlias = tableAlias;
    }

    private S helper;
    private Set<E> sqlPartData = null;

    @Override
    public S getHelper() {
        return this.helper;
    }

    @Override
    public void setHelper(S helper) {
        this.helper = helper;
    }

    @Override
    public void addSqlPartDatum(E sqlPartDatum) {
        if (sqlPartDatum == null) {
            return;
        }
        if (this.sqlPartData == null) {
            this.sqlPartData = new LinkedHashSet<>();
        }
        this.sqlPartData.add(sqlPartDatum);
    }

    @Override
    public Set<E> takeoutSqlPartData() {
        Set<E> sqlModelData = this.sqlPartData;
        this.sqlPartData = null;
        return sqlModelData;
    }

}
