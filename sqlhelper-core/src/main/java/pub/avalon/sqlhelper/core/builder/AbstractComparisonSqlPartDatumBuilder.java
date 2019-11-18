package pub.avalon.sqlhelper.core.builder;

import pub.avalon.sqlhelper.core.data.AbstractComparisonSqlPartDatum;
import pub.avalon.sqlhelper.core.helper.Helper;

/**
 * @author baichao
 */
public abstract class AbstractComparisonSqlPartDatumBuilder<T extends Helper, S extends AbstractComparisonSqlPartDatum<S>> extends AbstractSqlPartDatumBuilder<T, S> {

    public AbstractComparisonSqlPartDatumBuilder(String tableAlias, T helper) {
        super(tableAlias, helper);
    }

    /**
     * get abstract comparison sql part datum
     *
     * @return extends {@link AbstractComparisonSqlPartDatum}
     */
    public abstract AbstractComparisonSqlPartDatum<S> getAbstractComparisonSqlPartDatum();
}