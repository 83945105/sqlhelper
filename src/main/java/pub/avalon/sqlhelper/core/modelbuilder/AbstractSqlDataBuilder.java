package pub.avalon.sqlhelper.core.modelbuilder;

import pub.avalon.sqlhelper.core.data.SqlModelDatum;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * sql数据构建器
 *
 * @author 白超
 * @date 2019/5/2
 */
public abstract class AbstractSqlDataBuilder<S extends SqlModel<S, E>, E extends SqlModelDatum> implements SqlDataBuilder<S, E> {

    private S sqlModel;
    private Set<E> sqlModelData = null;

    @Override
    public S getSqlModel() {
        return this.sqlModel;
    }

    @Override
    public void setSqlModel(S sqlModel) {
        this.sqlModel = sqlModel;
    }

    @Override
    public void addSqlModelDatum(E sqlModelDatum) {
        if (sqlModelDatum == null) {
            return;
        }
        if (this.sqlModelData == null) {
            this.sqlModelData = new LinkedHashSet<>();
        }
        this.sqlModelData.add(sqlModelDatum);
    }

    @Override
    public Set<E> takeoutSqlModelData() {
        Set<E> sqlModelData = this.sqlModelData;
        this.sqlModelData = null;
        return sqlModelData;
    }

}
