package pub.avalon.sqlhelper.core.builder;

import pub.avalon.sqlhelper.core.data.SqlPartDatum;
import pub.avalon.sqlhelper.core.helper.Helper;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * sql数据构建器
 *
 * @author 白超
 * @date 2019/5/2
 */
public abstract class AbstractSqlDataBuilder<S extends Helper<S, E>, E extends SqlPartDatum> implements SqlDataBuilder<S, E> {

    private S sqlModel;
    private Set<E> sqlPartData = null;

    @Override
    public S getSqlModel() {
        return this.sqlModel;
    }

    @Override
    public void setSqlModel(S sqlModel) {
        this.sqlModel = sqlModel;
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
