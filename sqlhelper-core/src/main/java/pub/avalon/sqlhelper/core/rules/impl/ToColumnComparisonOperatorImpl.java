package pub.avalon.sqlhelper.core.rules.impl;

import pub.avalon.sqlhelper.core.data.AbstractComparisonSqlPartDatum;
import pub.avalon.sqlhelper.core.data.ColumnDatum;
import pub.avalon.sqlhelper.core.data.ComparisonType;
import pub.avalon.sqlhelper.core.data.SqlPartDatum;
import pub.avalon.sqlhelper.core.helper.ColumnHelper;
import pub.avalon.sqlhelper.core.helper.Helper;
import pub.avalon.sqlhelper.core.rules.ToColumnComparisonOperator;

import java.util.List;

/**
 * @author baichao
 */
public interface ToColumnComparisonOperatorImpl<T, S extends AbstractComparisonSqlPartDatum<S>> extends ToColumnComparisonOperator<T> {

    /**
     * get helper
     *
     * @return extends {@link Helper} object
     */
    T getHelper();

    /**
     * get abstract comparison sql part datum
     *
     * @return extends {@link AbstractComparisonSqlPartDatum}
     */
    AbstractComparisonSqlPartDatum<S> getAbstractComparisonSqlPartDatum();

    /**
     * add sql part datum
     *
     * @param sqlPartDatum implements {@link SqlPartDatum} object
     */
    void addSqlPartDatum(S sqlPartDatum);

    @Override
    default T equalTo(ColumnHelper<?> columnHelper) {
        List<ColumnDatum> columnData = columnHelper.takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return this.getHelper();
        }
        this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetColumnData(ComparisonType.EQUAL, columnData));
        return this.getHelper();
    }

    @Override
    default T notEqualTo(ColumnHelper<?> columnHelper) {
        List<ColumnDatum> columnData = columnHelper.takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return this.getHelper();
        }
        this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetColumnData(ComparisonType.NOT_EQUAL, columnData));
        return this.getHelper();
    }

    @Override
    default T greaterThan(ColumnHelper<?> columnHelper) {
        List<ColumnDatum> columnData = columnHelper.takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return this.getHelper();
        }
        this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetColumnData(ComparisonType.GREATER, columnData));
        return this.getHelper();
    }

    @Override
    default T greaterThanAndEqualTo(ColumnHelper<?> columnHelper) {
        List<ColumnDatum> columnData = columnHelper.takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return this.getHelper();
        }
        this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetColumnData(ComparisonType.GREATER_EQUAL, columnData));
        return this.getHelper();
    }

    @Override
    default T lessThan(ColumnHelper<?> columnHelper) {
        List<ColumnDatum> columnData = columnHelper.takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return this.getHelper();
        }
        this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetColumnData(ComparisonType.LESS, columnData));
        return this.getHelper();
    }

    @Override
    default T lessThanAndEqualTo(ColumnHelper<?> columnHelper) {
        List<ColumnDatum> columnData = columnHelper.takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return this.getHelper();
        }
        this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetColumnData(ComparisonType.LESS_EQUAL, columnData));
        return this.getHelper();
    }

    @Override
    default T between(ColumnHelper<?> columnHelper) {
        List<ColumnDatum> columnData = columnHelper.takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return this.getHelper();
        }
        if (columnData.size() != 2) {
            throw new RuntimeException("ColumnData size must be 2 in between.");
        }
        this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetColumnData(ComparisonType.BETWEEN, columnData));
        return this.getHelper();
    }

    @Override
    default T like(ColumnHelper<?> columnHelper) {
        List<ColumnDatum> columnData = columnHelper.takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return this.getHelper();
        }
        this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetColumnData(ComparisonType.LIKE, columnData));
        return this.getHelper();
    }

    @Override
    default T in(ColumnHelper<?> columnHelper) {
        List<ColumnDatum> columnData = columnHelper.takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return this.getHelper();
        }
        this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetColumnData(ComparisonType.IN, columnData));
        return this.getHelper();
    }

    @Override
    default T notIn(ColumnHelper<?> columnHelper) {
        List<ColumnDatum> columnData = columnHelper.takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return this.getHelper();
        }
        this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetColumnData(ComparisonType.NOT_IN, columnData));
        return this.getHelper();
    }
}