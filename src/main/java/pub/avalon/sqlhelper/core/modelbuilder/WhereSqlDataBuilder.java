package pub.avalon.sqlhelper.core.modelbuilder;

import pub.avalon.sqlhelper.core.beans.BeanUtils;
import pub.avalon.sqlhelper.core.beans.ComparisonRule;
import pub.avalon.sqlhelper.core.beans.WhereType;
import pub.avalon.sqlhelper.core.beans.WhereValueType;
import pub.avalon.sqlhelper.core.callback.SubQueryCallback;
import pub.avalon.sqlhelper.core.callback.WhereColumnCallback;
import pub.avalon.sqlhelper.core.comparison.WhereComparisonOperator;
import pub.avalon.sqlhelper.core.data.ColumnDatum;
import pub.avalon.sqlhelper.core.data.WhereDatum;
import pub.avalon.sqlhelper.core.exception.ComparisonException;
import pub.avalon.sqlhelper.core.exception.SqlException;

import java.util.Collection;
import java.util.Set;

/**
 * Where条件构建器
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class WhereSqlDataBuilder<T extends SqlModel<T, WhereDatum>> extends AbstractSqlDataBuilder<T, WhereDatum> implements WhereComparisonOperator<T> {

    protected WhereDatum whereDatum;

    @Override
    public void accept(String tableName, String tableAlias, String columnName, String columnAlias) {
        this.whereDatum = new WhereDatum();
        this.whereDatum.setOwnerTableName(tableName);
        this.whereDatum.setOwnerTableAlias(tableAlias);
        this.whereDatum.setOwnerColumnName(columnName);
    }

    /**
     * sql片段
     * 直接写入sql片段
     *
     * @param sqlPart
     * @return
     */
    public T sqlPart(String sqlPart) {
        this.whereDatum.setWhereValueType(WhereValueType.SQL_PART);
        this.whereDatum.setSqlPart(sqlPart);
        this.addSqlModelDatum(this.whereDatum);
        return this.getSqlModel();
    }

    @Override
    public T isNull() {
        this.whereDatum.setWhereType(WhereType.IS_NULL);
        this.whereDatum.setValueCount(0);
        this.addSqlModelDatum(this.whereDatum);
        return this.getSqlModel();
    }

    @Override
    public T isNotNull() {
        this.whereDatum.setWhereType(WhereType.IS_NOT_NULL);
        this.whereDatum.setValueCount(0);
        this.addSqlModelDatum(this.whereDatum);
        return this.getSqlModel();
    }

    @Override
    public T equalToValue(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getSqlModel();
                case NOT_NULL:
                    throw new ComparisonException("table alias [" + this.whereDatum.getOwnerTableAlias() + "] column [" + this.whereDatum.getOwnerColumnName() + "] equalTo, the value can not be null.");
                default:
                    return null;
            }
        }
        this.whereDatum.setWhereType(WhereType.EQUAL);
        this.whereDatum.setValueCount(1);
        this.whereDatum.setTargetValue(value);
        this.addSqlModelDatum(this.whereDatum);
        return this.getSqlModel();
    }

    @Override
    public T notEqualToValue(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getSqlModel();
                case NOT_NULL:
                    throw new ComparisonException("table alias [" + this.whereDatum.getOwnerTableAlias() + "] column [" + this.whereDatum.getOwnerColumnName() + "] notEqualTo, the value can not be null.");
                default:
                    return null;
            }
        }
        this.whereDatum.setWhereType(WhereType.NOT_EQUAL);
        this.whereDatum.setValueCount(1);
        this.whereDatum.setTargetValue(value);
        this.addSqlModelDatum(this.whereDatum);
        return this.getSqlModel();
    }

    @Override
    public T greaterThanValue(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getSqlModel();
                case NOT_NULL:
                    throw new ComparisonException("table alias [" + this.whereDatum.getOwnerTableAlias() + "] column [" + this.whereDatum.getOwnerColumnName() + "] greaterThan, the value can not be null.");
                default:
                    return null;
            }
        }
        this.whereDatum.setWhereType(WhereType.GREATER);
        this.whereDatum.setValueCount(1);
        this.whereDatum.setTargetValue(value);
        this.addSqlModelDatum(this.whereDatum);
        return this.getSqlModel();
    }

    @Override
    public T greaterThanAndEqualToValue(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getSqlModel();
                case NOT_NULL:
                    throw new ComparisonException("table alias [" + this.whereDatum.getOwnerTableAlias() + "] column [" + this.whereDatum.getOwnerColumnName() + "] greaterThanAndEqualTo, the value can not be null.");
                default:
                    return null;
            }
        }
        this.whereDatum.setWhereType(WhereType.GREATER_EQUAL);
        this.whereDatum.setValueCount(1);
        this.whereDatum.setTargetValue(value);
        this.addSqlModelDatum(this.whereDatum);
        return this.getSqlModel();
    }

    @Override
    public T lessThanValue(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getSqlModel();
                case NOT_NULL:
                    throw new ComparisonException("table alias [" + this.whereDatum.getOwnerTableAlias() + "] column [" + this.whereDatum.getOwnerColumnName() + "] lessThan, the value can not be null.");
                default:
                    return null;
            }
        }
        this.whereDatum.setWhereType(WhereType.LESS);
        this.whereDatum.setValueCount(1);
        this.whereDatum.setTargetValue(value);
        this.addSqlModelDatum(this.whereDatum);
        return this.getSqlModel();
    }

    @Override
    public T lessThanAndEqualToValue(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getSqlModel();
                case NOT_NULL:
                    throw new ComparisonException("table alias [" + this.whereDatum.getOwnerTableAlias() + "] column [" + this.whereDatum.getOwnerColumnName() + "] lessThanAndEqualTo, the value can not be null.");
                default:
                    return null;
            }
        }
        this.whereDatum.setWhereType(WhereType.LESS_EQUAL);
        this.whereDatum.setValueCount(1);
        this.whereDatum.setTargetValue(value);
        this.addSqlModelDatum(this.whereDatum);
        return this.getSqlModel();
    }

    @Override
    public T betweenValue(Object value, Object secondValue, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getSqlModel();
                case NOT_NULL:
                    throw new ComparisonException("table alias [" + this.whereDatum.getOwnerTableAlias() + "] column [" + this.whereDatum.getOwnerColumnName() + "] between, the value can not be null.");
                default:
                    return null;
            }
        }
        if (secondValue == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getSqlModel();
                case NOT_NULL:
                    throw new ComparisonException("table alias [" + this.whereDatum.getOwnerTableAlias() + "] column [" + this.whereDatum.getOwnerColumnName() + "] between, the secondValue can not be null.");
                default:
                    return null;
            }
        }
        this.whereDatum.setWhereType(WhereType.BETWEEN);
        this.whereDatum.setValueCount(2);
        this.whereDatum.setTargetValue(value);
        this.whereDatum.setTargetSecondValue(secondValue);
        this.addSqlModelDatum(this.whereDatum);
        return this.getSqlModel();
    }

    @Override
    public T likeValue(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getSqlModel();
                case NOT_NULL:
                    throw new ComparisonException("table alias [" + this.whereDatum.getOwnerTableAlias() + "] column [" + this.whereDatum.getOwnerColumnName() + "] like, the value can not be null.");
                default:
                    return null;
            }
        }
        this.whereDatum.setWhereType(WhereType.LIKE);
        this.whereDatum.setValueCount(1);
        this.whereDatum.setTargetValue(value);
        this.addSqlModelDatum(this.whereDatum);
        return this.getSqlModel();
    }

    @Override
    public T inValue(Object[] values, ComparisonRule comparisonRule) {
        if (values == null || values.length == 0) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getSqlModel();
                case NOT_NULL:
                    throw new ComparisonException("table alias [" + this.whereDatum.getOwnerTableAlias() + "] column [" + this.whereDatum.getOwnerColumnName() + "] in, the values can not be null or size = 0.");
                default:
                    return null;
            }
        }
        this.whereDatum.setWhereType(WhereType.IN);
        this.whereDatum.setValueCount(values.length);
        this.whereDatum.setTargetValue(values);
        this.addSqlModelDatum(this.whereDatum);
        return this.getSqlModel();
    }

    @Override
    public T inValue(Collection<?> values, ComparisonRule comparisonRule) {
        if (values == null || values.size() == 0) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getSqlModel();
                case NOT_NULL:
                    throw new ComparisonException("table alias [" + this.whereDatum.getOwnerTableAlias() + "] column [" + this.whereDatum.getOwnerColumnName() + "] in, the values can not be null or size = 0.");
                default:
                    return null;
            }
        }
        this.whereDatum.setWhereType(WhereType.IN);
        this.whereDatum.setValueCount(values.size());
        this.whereDatum.setTargetValue(values);
        this.addSqlModelDatum(this.whereDatum);
        return this.getSqlModel();
    }

    @Override
    public T notInValue(Object[] values, ComparisonRule comparisonRule) {
        if (values == null || values.length == 0) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getSqlModel();
                case NOT_NULL:
                    throw new ComparisonException("table alias [" + this.whereDatum.getOwnerTableAlias() + "] column [" + this.whereDatum.getOwnerColumnName() + "] in, the values can not be null or size = 0.");
                default:
                    return null;
            }
        }
        this.whereDatum.setWhereType(WhereType.NOT_IN);
        this.whereDatum.setValueCount(values.length);
        this.whereDatum.setTargetValue(values);
        this.addSqlModelDatum(this.whereDatum);
        return this.getSqlModel();
    }

    @Override
    public T notInValue(Collection<?> values, ComparisonRule comparisonRule) {
        if (values == null || values.size() == 0) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getSqlModel();
                case NOT_NULL:
                    throw new ComparisonException("table alias [" + this.whereDatum.getOwnerTableAlias() + "] column [" + this.whereDatum.getOwnerColumnName() + "] in, the values can not be null or size = 0.");
                default:
                    return null;
            }
        }
        this.whereDatum.setWhereType(WhereType.NOT_IN);
        this.whereDatum.setValueCount(values.size());
        this.whereDatum.setTargetValue(values);
        this.addSqlModelDatum(this.whereDatum);
        return this.getSqlModel();
    }

    @Override
    public T equalTo(WhereSqlDataBuilder whereSqlDataBuilder) {
        this.whereDatum.setWhereType(WhereType.EQUAL);
        this.whereDatum.setWhereValueType(WhereValueType.JOIN);
        WhereDatum whereDatum = whereSqlDataBuilder.whereDatum;
        this.whereDatum.setTargetTableName(whereDatum.getOwnerTableName());
        this.whereDatum.setTargetTableAlias(whereDatum.getOwnerTableAlias());
        this.whereDatum.setTargetColumnName(whereDatum.getOwnerColumnName());
        this.addSqlModelDatum(this.whereDatum);
        return this.getSqlModel();
    }

    @Override
    public T notEqualTo(WhereSqlDataBuilder whereSqlDataBuilder) {
        this.whereDatum.setWhereType(WhereType.NOT_EQUAL);
        this.whereDatum.setWhereValueType(WhereValueType.JOIN);
        WhereDatum whereDatum = whereSqlDataBuilder.whereDatum;
        this.whereDatum.setTargetTableName(whereDatum.getOwnerTableName());
        this.whereDatum.setTargetTableAlias(whereDatum.getOwnerTableAlias());
        this.whereDatum.setTargetColumnName(whereDatum.getOwnerColumnName());
        this.addSqlModelDatum(this.whereDatum);
        return this.getSqlModel();
    }

    @Override
    public T greaterThan(WhereSqlDataBuilder whereSqlDataBuilder) {
        this.whereDatum.setWhereType(WhereType.GREATER);
        this.whereDatum.setWhereValueType(WhereValueType.JOIN);
        WhereDatum whereDatum = whereSqlDataBuilder.whereDatum;
        this.whereDatum.setTargetTableName(whereDatum.getOwnerTableName());
        this.whereDatum.setTargetTableAlias(whereDatum.getOwnerTableAlias());
        this.whereDatum.setTargetColumnName(whereDatum.getOwnerColumnName());
        this.addSqlModelDatum(this.whereDatum);
        return this.getSqlModel();
    }

    @Override
    public T greaterThanAndEqualTo(WhereSqlDataBuilder whereSqlDataBuilder) {
        this.whereDatum.setWhereType(WhereType.GREATER_EQUAL);
        this.whereDatum.setWhereValueType(WhereValueType.JOIN);
        WhereDatum whereDatum = whereSqlDataBuilder.whereDatum;
        this.whereDatum.setTargetTableName(whereDatum.getOwnerTableName());
        this.whereDatum.setTargetTableAlias(whereDatum.getOwnerTableAlias());
        this.whereDatum.setTargetColumnName(whereDatum.getOwnerColumnName());
        this.addSqlModelDatum(this.whereDatum);
        return this.getSqlModel();
    }

    @Override
    public T lessThan(WhereSqlDataBuilder whereSqlDataBuilder) {
        this.whereDatum.setWhereType(WhereType.LESS);
        this.whereDatum.setWhereValueType(WhereValueType.JOIN);
        WhereDatum whereDatum = whereSqlDataBuilder.whereDatum;
        this.whereDatum.setTargetTableName(whereDatum.getOwnerTableName());
        this.whereDatum.setTargetTableAlias(whereDatum.getOwnerTableAlias());
        this.whereDatum.setTargetColumnName(whereDatum.getOwnerColumnName());
        this.addSqlModelDatum(this.whereDatum);
        return this.getSqlModel();
    }

    @Override
    public T lessThanAndEqualTo(WhereSqlDataBuilder whereSqlDataBuilder) {
        this.whereDatum.setWhereType(WhereType.LESS_EQUAL);
        this.whereDatum.setWhereValueType(WhereValueType.JOIN);
        WhereDatum whereDatum = whereSqlDataBuilder.whereDatum;
        this.whereDatum.setTargetTableName(whereDatum.getOwnerTableName());
        this.whereDatum.setTargetTableAlias(whereDatum.getOwnerTableAlias());
        this.whereDatum.setTargetColumnName(whereDatum.getOwnerColumnName());
        this.addSqlModelDatum(this.whereDatum);
        return this.getSqlModel();
    }

    @Override
    public <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T equalTo(Class<S> tableModelClass, String alias, WhereColumnCallback<SC> callback) {
        this.whereDatum.setWhereType(WhereType.EQUAL);
        this.whereDatum.setWhereValueType(WhereValueType.JOIN);
        SC sc = BeanUtils.tableModel(tableModelClass).newColumnSqlModel();
        sc = callback.apply(sc);
        Set<ColumnDatum> columnData = sc.takeoutSqlModelData();
        if (columnData == null || columnData.size() == 0) {
            return this.getSqlModel();
        }
        if (columnData.size() > 1) {
            throw new SqlException("you can not set more than one column for equalTo.");
        }
        ColumnDatum columnDatum = columnData.iterator().next();
        this.whereDatum.setTargetTableName(columnDatum.getOwnerTableName());
        this.whereDatum.setTargetTableAlias(columnDatum.getOwnerTableAlias());
        this.whereDatum.setTargetColumnName(columnDatum.getOwnerColumnName());
        this.addSqlModelDatum(this.whereDatum);
        return this.getSqlModel();
    }

    @Override
    public <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T notEqualTo(Class<S> tableModelClass, String alias, WhereColumnCallback<SC> callback) {
        this.whereDatum.setWhereType(WhereType.NOT_EQUAL);
        this.whereDatum.setWhereValueType(WhereValueType.JOIN);
        SC sc = BeanUtils.tableModel(tableModelClass).newColumnSqlModel();
        sc = callback.apply(sc);
        Set<ColumnDatum> columnData = sc.takeoutSqlModelData();
        if (columnData == null || columnData.size() == 0) {
            return this.getSqlModel();
        }
        if (columnData.size() > 1) {
            throw new SqlException("you can not set more than one column for equalTo.");
        }
        ColumnDatum columnDatum = columnData.iterator().next();
        this.whereDatum.setTargetTableName(columnDatum.getOwnerTableName());
        this.whereDatum.setTargetTableAlias(columnDatum.getOwnerTableAlias());
        this.whereDatum.setTargetColumnName(columnDatum.getOwnerColumnName());
        this.addSqlModelDatum(this.whereDatum);
        return this.getSqlModel();
    }

    @Override
    public <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T greaterThan(Class<S> tableModelClass, String alias, WhereColumnCallback<SC> callback) {
        this.whereDatum.setWhereType(WhereType.GREATER);
        this.whereDatum.setWhereValueType(WhereValueType.JOIN);
        SC sc = BeanUtils.tableModel(tableModelClass).newColumnSqlModel();
        sc = callback.apply(sc);
        Set<ColumnDatum> columnData = sc.takeoutSqlModelData();
        if (columnData == null || columnData.size() == 0) {
            return this.getSqlModel();
        }
        if (columnData.size() > 1) {
            throw new SqlException("you can not set more than one column for equalTo.");
        }
        ColumnDatum columnDatum = columnData.iterator().next();
        this.whereDatum.setTargetTableName(columnDatum.getOwnerTableName());
        this.whereDatum.setTargetTableAlias(columnDatum.getOwnerTableAlias());
        this.whereDatum.setTargetColumnName(columnDatum.getOwnerColumnName());
        this.addSqlModelDatum(this.whereDatum);
        return this.getSqlModel();
    }

    @Override
    public <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T greaterThanAndEqualTo(Class<S> tableModelClass, String alias, WhereColumnCallback<SC> callback) {
        this.whereDatum.setWhereType(WhereType.GREATER_EQUAL);
        this.whereDatum.setWhereValueType(WhereValueType.JOIN);
        SC sc = BeanUtils.tableModel(tableModelClass).newColumnSqlModel();
        sc = callback.apply(sc);
        Set<ColumnDatum> columnData = sc.takeoutSqlModelData();
        if (columnData == null || columnData.size() == 0) {
            return this.getSqlModel();
        }
        if (columnData.size() > 1) {
            throw new SqlException("you can not set more than one column for equalTo.");
        }
        ColumnDatum columnDatum = columnData.iterator().next();
        this.whereDatum.setTargetTableName(columnDatum.getOwnerTableName());
        this.whereDatum.setTargetTableAlias(columnDatum.getOwnerTableAlias());
        this.whereDatum.setTargetColumnName(columnDatum.getOwnerColumnName());
        this.addSqlModelDatum(this.whereDatum);
        return this.getSqlModel();
    }

    @Override
    public <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T lessThan(Class<S> tableModelClass, String alias, WhereColumnCallback<SC> callback) {
        this.whereDatum.setWhereType(WhereType.LESS);
        this.whereDatum.setWhereValueType(WhereValueType.JOIN);
        SC sc = BeanUtils.tableModel(tableModelClass).newColumnSqlModel();
        sc = callback.apply(sc);
        Set<ColumnDatum> columnData = sc.takeoutSqlModelData();
        if (columnData == null || columnData.size() == 0) {
            return this.getSqlModel();
        }
        if (columnData.size() > 1) {
            throw new SqlException("you can not set more than one column for equalTo.");
        }
        ColumnDatum columnDatum = columnData.iterator().next();
        this.whereDatum.setTargetTableName(columnDatum.getOwnerTableName());
        this.whereDatum.setTargetTableAlias(columnDatum.getOwnerTableAlias());
        this.whereDatum.setTargetColumnName(columnDatum.getOwnerColumnName());
        this.addSqlModelDatum(this.whereDatum);
        return this.getSqlModel();
    }

    @Override
    public <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T lessThanAndEqualTo(Class<S> tableModelClass, String alias, WhereColumnCallback<SC> callback) {
        this.whereDatum.setWhereType(WhereType.LESS_EQUAL);
        this.whereDatum.setWhereValueType(WhereValueType.JOIN);
        SC sc = BeanUtils.tableModel(tableModelClass).newColumnSqlModel();
        sc = callback.apply(sc);
        Set<ColumnDatum> columnData = sc.takeoutSqlModelData();
        if (columnData == null || columnData.size() == 0) {
            return this.getSqlModel();
        }
        if (columnData.size() > 1) {
            throw new SqlException("you can not set more than one column for equalTo.");
        }
        ColumnDatum columnDatum = columnData.iterator().next();
        this.whereDatum.setTargetTableName(columnDatum.getOwnerTableName());
        this.whereDatum.setTargetTableAlias(columnDatum.getOwnerTableAlias());
        this.whereDatum.setTargetColumnName(columnDatum.getOwnerColumnName());
        this.addSqlModelDatum(this.whereDatum);
        return this.getSqlModel();
    }

    @Override
    public <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T equalToSubQuery(String tableName, Class<S> modelClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
/*        SqlBuilder sqlBuilder = SubQueryCallback.execute(this.getModel().getSqlData(), tableName, modelClass, alias, callback);
        this.whereDatum.setWhereType(WhereType.EQUAL);
        this.whereDatum.setWhereValueType(WhereValueType.SUB_QUERY);
        this.whereDatum.setTargetSubQuery(sqlBuilder);
        this.addSqlModelDatum(this.whereDatum);*/
        return this.getSqlModel();
    }

    @Override
    public <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T notEqualToSubQuery(String tableName, Class<S> modelClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
        return null;
    }

    @Override
    public <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T greaterThanSubQuery(String tableName, Class<S> modelClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
        return null;
    }

    @Override
    public <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T greaterThanAndEqualToSubQuery(String tableName, Class<S> modelClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
        return null;
    }

    @Override
    public <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T lessThanSubQuery(String tableName, Class<S> modelClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
        return null;
    }

    @Override
    public <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T lessThanAndEqualToSubQuery(String tableName, Class<S> modelClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
        return null;
    }

    @Override
    public <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T likeSubQuery(String tableName, Class<S> modelClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
        return null;
    }

    @Override
    public <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T inSubQuery(String tableName, Class<S> modelClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
        return null;
    }

    @Override
    public <S extends TableModel<S, SO, SC, SW, SG, SS>,
            SO extends OnSqlModel<SO>,
            SC extends ColumnSqlModel<SC>,
            SW extends WhereSqlModel<SW>,
            SG extends GroupSqlModel<SG>,
            SS extends SortSqlModel<SS>> T notInSubQuery(String tableName, Class<S> modelClass, String alias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
        return null;
    }

   /* @Override

    @Override
    public <T extends Model<T>> T equalToSubQuery(String tableName, Class<T> modelClass, String alias, SubQuery<T, MC, MO, T, MS, MG, T, TC, TO, TW, TS, TG> subQuery) {
        SqlBuilder sqlBuilder = SubQuery.execute(this.getModel().getSqlData(), tableName, modelClass, alias, subQuery);
        this.whereDatum.setWhereType(WhereType.EQUAL);
        this.whereDatum.setWhereValueType(WhereValueType.SUB_QUERY);
        this.whereDatum.setTargetSubQuery(sqlBuilder);
        this.addSqlModelDatum(this.whereDatum);
        return this.getSqlModel();
    }

    @Override
    public <T extends Model<T>, TC extends ColumnModel<T>, TO extends OnModel<T>, TW extends WhereModel<T>, TS extends SortModel<T>, TG extends GroupModel<T, TC, TO, TW, TS, TG>> T notEqualToSubQuery(String tableName, Class<T> modelClass, String alias, SubQuery<T, MC, MO, T, MS, MG, T, TC, TO, TW, TS, TG> subQuery) {
        SqlBuilder sqlBuilder = SubQuery.execute(this.getModel().getSqlData(), tableName, modelClass, alias, subQuery);
        this.whereDatum.setWhereType(WhereType.NOT_EQUAL);
        this.whereDatum.setWhereValueType(WhereValueType.SUB_QUERY);
        this.whereDatum.setTargetSubQuery(sqlBuilder);
        this.addSqlModelDatum(this.whereDatum);
        return this.getSqlModel();
    }

    @Override
    public <T extends Model<T>, TC extends ColumnModel<T>, TO extends OnModel<T>, TW extends WhereModel<T>, TS extends SortModel<T>, TG extends GroupModel<T, TC, TO, TW, TS, TG>> T greaterThanSubQuery(String tableName, Class<T> modelClass, String alias, SubQuery<T, MC, MO, T, MS, MG, T, TC, TO, TW, TS, TG> subQuery) {
        SqlBuilder sqlBuilder = SubQuery.execute(this.getModel().getSqlData(), tableName, modelClass, alias, subQuery);
        this.whereDatum.setWhereType(WhereType.GREATER);
        this.whereDatum.setWhereValueType(WhereValueType.SUB_QUERY);
        this.whereDatum.setTargetSubQuery(sqlBuilder);
        this.addSqlModelDatum(this.whereDatum);
        return this.getSqlModel();
    }

    @Override
    public <T extends Model<T>, TC extends ColumnModel<T>, TO extends OnModel<T>, TW extends WhereModel<T>, TS extends SortModel<T>, TG extends GroupModel<T, TC, TO, TW, TS, TG>> T greaterThanAndEqualToSubQuery(String tableName, Class<T> modelClass, String alias, SubQuery<T, MC, MO, T, MS, MG, T, TC, TO, TW, TS, TG> subQuery) {
        SqlBuilder sqlBuilder = SubQuery.execute(this.getModel().getSqlData(), tableName, modelClass, alias, subQuery);
        this.whereDatum.setWhereType(WhereType.GREATER_EQUAL);
        this.whereDatum.setWhereValueType(WhereValueType.SUB_QUERY);
        this.whereDatum.setTargetSubQuery(sqlBuilder);
        this.addSqlModelDatum(this.whereDatum);
        return this.getSqlModel();
    }

    @Override
    public <T extends Model<T>, TC extends ColumnModel<T>, TO extends OnModel<T>, TW extends WhereModel<T>, TS extends SortModel<T>, TG extends GroupModel<T, TC, TO, TW, TS, TG>> T lessThanSubQuery(String tableName, Class<T> modelClass, String alias, SubQuery<T, MC, MO, T, MS, MG, T, TC, TO, TW, TS, TG> subQuery) {
        SqlBuilder sqlBuilder = SubQuery.execute(this.getModel().getSqlData(), tableName, modelClass, alias, subQuery);
        this.whereDatum.setWhereType(WhereType.LESS);
        this.whereDatum.setWhereValueType(WhereValueType.SUB_QUERY);
        this.whereDatum.setTargetSubQuery(sqlBuilder);
        this.addSqlModelDatum(this.whereDatum);
        return this.getSqlModel();
    }

    @Override
    public <T extends Model<T>, TC extends ColumnModel<T>, TO extends OnModel<T>, TW extends WhereModel<T>, TS extends SortModel<T>, TG extends GroupModel<T, TC, TO, TW, TS, TG>> T lessThanAndEqualToSubQuery(String tableName, Class<T> modelClass, String alias, SubQuery<T, MC, MO, T, MS, MG, T, TC, TO, TW, TS, TG> subQuery) {
        SqlBuilder sqlBuilder = SubQuery.execute(this.getModel().getSqlData(), tableName, modelClass, alias, subQuery);
        this.whereDatum.setWhereType(WhereType.LESS_EQUAL);
        this.whereDatum.setWhereValueType(WhereValueType.SUB_QUERY);
        this.whereDatum.setTargetSubQuery(sqlBuilder);
        this.addSqlModelDatum(this.whereDatum);
        return this.getSqlModel();
    }

    @Override
    public <T extends Model<T>, TC extends ColumnModel<T>, TO extends OnModel<T>, TW extends WhereModel<T>, TS extends SortModel<T>, TG extends GroupModel<T, TC, TO, TW, TS, TG>> T likeSubQuery(String tableName, Class<T> modelClass, String alias, SubQuery<T, MC, MO, T, MS, MG, T, TC, TO, TW, TS, TG> subQuery) {
        SqlBuilder sqlBuilder = SubQuery.execute(this.getModel().getSqlData(), tableName, modelClass, alias, subQuery);
        this.whereDatum.setWhereType(WhereType.LIKE);
        this.whereDatum.setWhereValueType(WhereValueType.SUB_QUERY);
        this.whereDatum.setTargetSubQuery(sqlBuilder);
        this.addSqlModelDatum(this.whereDatum);
        return this.getSqlModel();
    }

    @Override
    public <T extends Model<T>, TC extends ColumnModel<T>, TO extends OnModel<T>, TW extends WhereModel<T>, TS extends SortModel<T>, TG extends GroupModel<T, TC, TO, TW, TS, TG>> T inSubQuery(String tableName, Class<T> modelClass, String alias, SubQuery<T, MC, MO, T, MS, MG, T, TC, TO, TW, TS, TG> subQuery) {
        SqlBuilder sqlBuilder = SubQuery.execute(this.getModel().getSqlData(), tableName, modelClass, alias, subQuery);
        this.whereDatum.setWhereType(WhereType.IN);
        this.whereDatum.setWhereValueType(WhereValueType.SUB_QUERY);
        this.whereDatum.setTargetSubQuery(sqlBuilder);
        this.addSqlModelDatum(this.whereDatum);
        return this.getSqlModel();
    }

    @Override
    public <T extends Model<T>, TC extends ColumnModel<T>, TO extends OnModel<T>, TW extends WhereModel<T>, TS extends SortModel<T>, TG extends GroupModel<T, TC, TO, TW, TS, TG>> T notInSubQuery(String tableName, Class<T> modelClass, String alias, SubQuery<T, MC, MO, T, MS, MG, T, TC, TO, TW, TS, TG> subQuery) {
        SqlBuilder sqlBuilder = SubQuery.execute(this.getModel().getSqlData(), tableName, modelClass, alias, subQuery);
        this.whereDatum.setWhereType(WhereType.NOT_IN);
        this.whereDatum.setWhereValueType(WhereValueType.SUB_QUERY);
        this.whereDatum.setTargetSubQuery(sqlBuilder);
        this.addSqlModelDatum(this.whereDatum);
        return this.getSqlModel();
    }*/

}
