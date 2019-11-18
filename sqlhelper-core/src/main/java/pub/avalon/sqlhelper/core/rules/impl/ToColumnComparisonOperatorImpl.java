package pub.avalon.sqlhelper.core.rules.impl;

import pub.avalon.sqlhelper.core.data.*;
import pub.avalon.sqlhelper.core.helper.ColumnHelper;
import pub.avalon.sqlhelper.core.helper.Helper;
import pub.avalon.sqlhelper.core.rules.ToColumnComparisonOperator;
import pub.avalon.sqlhelper.core.utils.ExceptionUtils;

import java.util.ArrayList;
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
        for (ColumnDatum columnDatum : columnData) {
            this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSingleSqlPartDatum(ComparisonType.EQUAL, columnDatum));
        }
        return this.getHelper();
    }

    @Override
    default T notEqualTo(ColumnHelper<?> columnHelper) {
        List<ColumnDatum> columnData = columnHelper.takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return this.getHelper();
        }
        for (ColumnDatum columnDatum : columnData) {
            this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSingleSqlPartDatum(ComparisonType.NOT_EQUAL, columnDatum));
        }
        return this.getHelper();
    }

    @Override
    default T greaterThan(ColumnHelper<?> columnHelper) {
        List<ColumnDatum> columnData = columnHelper.takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return this.getHelper();
        }
        for (ColumnDatum columnDatum : columnData) {
            this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSingleSqlPartDatum(ComparisonType.GREATER, columnDatum));
        }
        return this.getHelper();
    }

    @Override
    default T greaterThanAndEqualTo(ColumnHelper<?> columnHelper) {
        List<ColumnDatum> columnData = columnHelper.takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return this.getHelper();
        }
        for (ColumnDatum columnDatum : columnData) {
            this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSingleSqlPartDatum(ComparisonType.GREATER_EQUAL, columnDatum));
        }
        return this.getHelper();
    }

    @Override
    default T lessThan(ColumnHelper<?> columnHelper) {
        List<ColumnDatum> columnData = columnHelper.takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return this.getHelper();
        }
        for (ColumnDatum columnDatum : columnData) {
            this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSingleSqlPartDatum(ComparisonType.LESS, columnDatum));
        }
        return this.getHelper();
    }

    @Override
    default T lessThanAndEqualTo(ColumnHelper<?> columnHelper) {
        List<ColumnDatum> columnData = columnHelper.takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return this.getHelper();
        }
        for (ColumnDatum columnDatum : columnData) {
            this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSingleSqlPartDatum(ComparisonType.LESS_EQUAL, columnDatum));
        }
        return this.getHelper();
    }

    @Override
    default T between(ColumnHelper<?> columnHelper, ColumnHelper<?> secondColumnHelper) {
        List<ColumnDatum> columnData = columnHelper.takeoutSqlPartData();
        List<ColumnDatum> secondColumnData = secondColumnHelper.takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0 || secondColumnData == null || secondColumnData.size() == 0) {
            return this.getHelper();
        }
        if (columnData.size() != secondColumnData.size()) {
            ExceptionUtils.columnDataSizeNotEqualException();
        }
        int size = columnData.size();
        for (int i = 0; i < size; i++) {
            this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetPairSqlPartDatum(ComparisonType.BETWEEN, columnData.get(i), secondColumnData.get(i)));
        }
        return this.getHelper();
    }

    @Override
    default T like(ColumnHelper<?> columnHelper) {
        List<ColumnDatum> columnData = columnHelper.takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return this.getHelper();
        }
        for (ColumnDatum columnDatum : columnData) {
            this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSingleSqlPartDatum(ComparisonType.LIKE, columnDatum));
        }
        return this.getHelper();
    }

    @Override
    default T in(ColumnHelper<?>... columnHelpers) {
        List<AbstractSqlPartDatum> sqlPartData = new ArrayList<>(columnHelpers.length * 2);
        for (ColumnHelper<?> columnHelper : columnHelpers) {
            List<ColumnDatum> columnData = columnHelper.takeoutSqlPartData();
            if (columnData == null || columnData.size() == 0) {
                continue;
            }
            sqlPartData.addAll(columnData);
        }
        this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetMultiSqlPartDatum(ComparisonType.IN, sqlPartData));
        return this.getHelper();
    }

    @Override
    default T notIn(ColumnHelper<?>... columnHelpers) {
        List<AbstractSqlPartDatum> sqlPartData = new ArrayList<>(columnHelpers.length * 2);
        for (ColumnHelper<?> columnHelper : columnHelpers) {
            List<ColumnDatum> columnData = columnHelper.takeoutSqlPartData();
            if (columnData == null || columnData.size() == 0) {
                continue;
            }
            sqlPartData.addAll(columnData);
        }
        this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetMultiSqlPartDatum(ComparisonType.NOT_IN, sqlPartData));
        return this.getHelper();
    }
}