package pub.avalon.sqlhelper.core.builder;

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
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.Collection;
import java.util.Set;

/**
 * 条件Sql片段数据构建器
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class WhereSqlPartDatumBuilder<T extends Helper<T, WhereDatum>> extends AbstractSqlPartDatumBuilder<T, WhereDatum> implements WhereComparisonOperator<T> {

    private WhereDatum whereDatum;

    private String tableName;
    private String tableAlias;
    private SqlBuilderOptions sqlBuilderOptions = SqlBuilderOptions.DEFAULT_SQL_BUILDER_OPTIONS;

    @Override
    public void accept(String tableName, String tableAlias, String columnName, String columnAlias, String fieldName) {
        this.whereDatum = new WhereDatum(this.tableName == null ? tableName : this.tableName, this.tableAlias == null ? tableAlias : this.tableAlias, columnName, columnAlias, fieldName);
    }

    @Override
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
    }

    @Override
    public void setSqlBuilderOptions(SqlBuilderOptions sqlBuilderOptions) {
        this.sqlBuilderOptions = sqlBuilderOptions;
    }

    /**
     * sql片段
     * 直接写入sql片段
     *
     * @param sqlPart sql片段
     * @return {@link Helper}
     */
    public T sqlPart(String sqlPart) {
        this.whereDatum.setWhereValueType(WhereValueType.SQL_PART);
        this.whereDatum.setSqlPart(sqlPart);
        this.addSqlPartDatum(this.whereDatum);
        return this.getHelper();
    }

    @Override
    public ComparisonRule getDefaultComparisonRule() {
        return this.sqlBuilderOptions.getSqlPartDatumBuilderOptions().getDefaultWhereComparisonRule();
    }

    @Override
    public T isNull() {
        this.whereDatum.setWhereType(WhereType.IS_NULL);
        this.whereDatum.setValueCount(0);
        this.addSqlPartDatum(this.whereDatum);
        return this.getHelper();
    }

    @Override
    public T isNotNull() {
        this.whereDatum.setWhereType(WhereType.IS_NOT_NULL);
        this.whereDatum.setValueCount(0);
        this.addSqlPartDatum(this.whereDatum);
        return this.getHelper();
    }

    @Override
    public T equalTo(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonException("table alias [" + this.whereDatum.getOwnerTableAlias() + "] column [" + this.whereDatum.getOwnerColumnName() + "] equalTo, the value can not be null.");
                default:
                    throw new RuntimeException("ComparisonRule's enumeration value is not supported for the time being");
            }
        }
        this.whereDatum.setWhereType(WhereType.EQUAL);
        this.whereDatum.setValueCount(1);
        this.whereDatum.setTargetValue(value);
        this.addSqlPartDatum(this.whereDatum);
        return this.getHelper();
    }

    @Override
    public T notEqualTo(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonException("table alias [" + this.whereDatum.getOwnerTableAlias() + "] column [" + this.whereDatum.getOwnerColumnName() + "] notEqualTo, the value can not be null.");
                default:
                    throw new RuntimeException("ComparisonRule's enumeration value is not supported for the time being");
            }
        }
        this.whereDatum.setWhereType(WhereType.NOT_EQUAL);
        this.whereDatum.setValueCount(1);
        this.whereDatum.setTargetValue(value);
        this.addSqlPartDatum(this.whereDatum);
        return this.getHelper();
    }

    @Override
    public T greaterThan(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonException("table alias [" + this.whereDatum.getOwnerTableAlias() + "] column [" + this.whereDatum.getOwnerColumnName() + "] greaterThan, the value can not be null.");
                default:
                    throw new RuntimeException("ComparisonRule's enumeration value is not supported for the time being");
            }
        }
        this.whereDatum.setWhereType(WhereType.GREATER);
        this.whereDatum.setValueCount(1);
        this.whereDatum.setTargetValue(value);
        this.addSqlPartDatum(this.whereDatum);
        return this.getHelper();
    }

    @Override
    public T greaterThanAndEqualTo(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonException("table alias [" + this.whereDatum.getOwnerTableAlias() + "] column [" + this.whereDatum.getOwnerColumnName() + "] greaterThanAndEqualTo, the value can not be null.");
                default:
                    throw new RuntimeException("ComparisonRule's enumeration value is not supported for the time being");
            }
        }
        this.whereDatum.setWhereType(WhereType.GREATER_EQUAL);
        this.whereDatum.setValueCount(1);
        this.whereDatum.setTargetValue(value);
        this.addSqlPartDatum(this.whereDatum);
        return this.getHelper();
    }

    @Override
    public T lessThan(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonException("table alias [" + this.whereDatum.getOwnerTableAlias() + "] column [" + this.whereDatum.getOwnerColumnName() + "] lessThan, the value can not be null.");
                default:
                    throw new RuntimeException("ComparisonRule's enumeration value is not supported for the time being");
            }
        }
        this.whereDatum.setWhereType(WhereType.LESS);
        this.whereDatum.setValueCount(1);
        this.whereDatum.setTargetValue(value);
        this.addSqlPartDatum(this.whereDatum);
        return this.getHelper();
    }

    @Override
    public T lessThanAndEqualTo(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonException("table alias [" + this.whereDatum.getOwnerTableAlias() + "] column [" + this.whereDatum.getOwnerColumnName() + "] lessThanAndEqualTo, the value can not be null.");
                default:
                    throw new RuntimeException("ComparisonRule's enumeration value is not supported for the time being");
            }
        }
        this.whereDatum.setWhereType(WhereType.LESS_EQUAL);
        this.whereDatum.setValueCount(1);
        this.whereDatum.setTargetValue(value);
        this.addSqlPartDatum(this.whereDatum);
        return this.getHelper();
    }

    @Override
    public T between(Object value, Object secondValue, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonException("table alias [" + this.whereDatum.getOwnerTableAlias() + "] column [" + this.whereDatum.getOwnerColumnName() + "] between, the value can not be null.");
                default:
                    throw new RuntimeException("ComparisonRule's enumeration value is not supported for the time being");
            }
        }
        if (secondValue == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonException("table alias [" + this.whereDatum.getOwnerTableAlias() + "] column [" + this.whereDatum.getOwnerColumnName() + "] between, the secondValue can not be null.");
                default:
                    throw new RuntimeException("ComparisonRule's enumeration value is not supported for the time being");
            }
        }
        this.whereDatum.setWhereType(WhereType.BETWEEN);
        this.whereDatum.setValueCount(2);
        this.whereDatum.setTargetValue(value);
        this.whereDatum.setTargetSecondValue(secondValue);
        this.addSqlPartDatum(this.whereDatum);
        return this.getHelper();
    }

    @Override
    public T like(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonException("table alias [" + this.whereDatum.getOwnerTableAlias() + "] column [" + this.whereDatum.getOwnerColumnName() + "] like, the value can not be null.");
                default:
                    throw new RuntimeException("ComparisonRule's enumeration value is not supported for the time being");
            }
        }
        this.whereDatum.setWhereType(WhereType.LIKE);
        this.whereDatum.setValueCount(1);
        this.whereDatum.setTargetValue(value);
        this.addSqlPartDatum(this.whereDatum);
        return this.getHelper();
    }

    @Override
    public T in(Object[] values, ComparisonRule comparisonRule) {
        if (values == null || values.length == 0) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonException("table alias [" + this.whereDatum.getOwnerTableAlias() + "] column [" + this.whereDatum.getOwnerColumnName() + "] in, the values can not be null or size = 0.");
                default:
                    throw new RuntimeException("ComparisonRule's enumeration value is not supported for the time being");
            }
        }
        this.whereDatum.setWhereType(WhereType.IN);
        this.whereDatum.setValueCount(values.length);
        this.whereDatum.setTargetValue(values);
        this.addSqlPartDatum(this.whereDatum);
        return this.getHelper();
    }

    @Override
    public T in(Collection<Object> values, ComparisonRule comparisonRule) {
        if (values == null || values.size() == 0) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonException("table alias [" + this.whereDatum.getOwnerTableAlias() + "] column [" + this.whereDatum.getOwnerColumnName() + "] in, the values can not be null or size = 0.");
                default:
                    throw new RuntimeException("ComparisonRule's enumeration value is not supported for the time being");
            }
        }
        this.whereDatum.setWhereType(WhereType.IN);
        this.whereDatum.setValueCount(values.size());
        this.whereDatum.setTargetValue(values);
        this.addSqlPartDatum(this.whereDatum);
        return this.getHelper();
    }

    @Override
    public T notIn(Object[] values, ComparisonRule comparisonRule) {
        if (values == null || values.length == 0) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonException("table alias [" + this.whereDatum.getOwnerTableAlias() + "] column [" + this.whereDatum.getOwnerColumnName() + "] in, the values can not be null or size = 0.");
                default:
                    throw new RuntimeException("ComparisonRule's enumeration value is not supported for the time being");
            }
        }
        this.whereDatum.setWhereType(WhereType.NOT_IN);
        this.whereDatum.setValueCount(values.length);
        this.whereDatum.setTargetValue(values);
        this.addSqlPartDatum(this.whereDatum);
        return this.getHelper();
    }

    @Override
    public T notIn(Collection<Object> values, ComparisonRule comparisonRule) {
        if (values == null || values.size() == 0) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonException("table alias [" + this.whereDatum.getOwnerTableAlias() + "] column [" + this.whereDatum.getOwnerColumnName() + "] in, the values can not be null or size = 0.");
                default:
                    throw new RuntimeException("ComparisonRule's enumeration value is not supported for the time being");
            }
        }
        this.whereDatum.setWhereType(WhereType.NOT_IN);
        this.whereDatum.setValueCount(values.size());
        this.whereDatum.setTargetValue(values);
        this.addSqlPartDatum(this.whereDatum);
        return this.getHelper();
    }

    @Override
    public T equalTo(WhereSqlPartDatumBuilder whereSqlDataBuilder) {
        this.whereDatum.setWhereType(WhereType.EQUAL);
        this.whereDatum.setWhereValueType(WhereValueType.JOIN);
        WhereDatum whereDatum = whereSqlDataBuilder.whereDatum;
        this.whereDatum.setTargetTableName(whereDatum.getOwnerTableName());
        this.whereDatum.setTargetTableAlias(whereDatum.getOwnerTableAlias());
        this.whereDatum.setTargetColumnName(whereDatum.getOwnerColumnName());
        this.whereDatum.setTargetColumnAlias(whereDatum.getOwnerColumnAlias());
        this.whereDatum.setTargetMappingFieldName(whereDatum.getOwnerMappingFieldName());
        this.addSqlPartDatum(this.whereDatum);
        return this.getHelper();
    }

    @Override
    public T notEqualTo(WhereSqlPartDatumBuilder whereSqlDataBuilder) {
        this.whereDatum.setWhereType(WhereType.NOT_EQUAL);
        this.whereDatum.setWhereValueType(WhereValueType.JOIN);
        WhereDatum whereDatum = whereSqlDataBuilder.whereDatum;
        this.whereDatum.setTargetTableName(whereDatum.getOwnerTableName());
        this.whereDatum.setTargetTableAlias(whereDatum.getOwnerTableAlias());
        this.whereDatum.setTargetColumnName(whereDatum.getOwnerColumnName());
        this.whereDatum.setTargetColumnAlias(whereDatum.getOwnerColumnAlias());
        this.whereDatum.setTargetMappingFieldName(whereDatum.getOwnerMappingFieldName());
        this.addSqlPartDatum(this.whereDatum);
        return this.getHelper();
    }

    @Override
    public T greaterThan(WhereSqlPartDatumBuilder whereSqlDataBuilder) {
        this.whereDatum.setWhereType(WhereType.GREATER);
        this.whereDatum.setWhereValueType(WhereValueType.JOIN);
        WhereDatum whereDatum = whereSqlDataBuilder.whereDatum;
        this.whereDatum.setTargetTableName(whereDatum.getOwnerTableName());
        this.whereDatum.setTargetTableAlias(whereDatum.getOwnerTableAlias());
        this.whereDatum.setTargetColumnName(whereDatum.getOwnerColumnName());
        this.whereDatum.setTargetColumnAlias(whereDatum.getOwnerColumnAlias());
        this.whereDatum.setTargetMappingFieldName(whereDatum.getOwnerMappingFieldName());
        this.addSqlPartDatum(this.whereDatum);
        return this.getHelper();
    }

    @Override
    public T greaterThanAndEqualTo(WhereSqlPartDatumBuilder whereSqlDataBuilder) {
        this.whereDatum.setWhereType(WhereType.GREATER_EQUAL);
        this.whereDatum.setWhereValueType(WhereValueType.JOIN);
        WhereDatum whereDatum = whereSqlDataBuilder.whereDatum;
        this.whereDatum.setTargetTableName(whereDatum.getOwnerTableName());
        this.whereDatum.setTargetTableAlias(whereDatum.getOwnerTableAlias());
        this.whereDatum.setTargetColumnName(whereDatum.getOwnerColumnName());
        this.whereDatum.setTargetColumnAlias(whereDatum.getOwnerColumnAlias());
        this.whereDatum.setTargetMappingFieldName(whereDatum.getOwnerMappingFieldName());
        this.addSqlPartDatum(this.whereDatum);
        return this.getHelper();
    }

    @Override
    public T lessThan(WhereSqlPartDatumBuilder whereSqlDataBuilder) {
        this.whereDatum.setWhereType(WhereType.LESS);
        this.whereDatum.setWhereValueType(WhereValueType.JOIN);
        WhereDatum whereDatum = whereSqlDataBuilder.whereDatum;
        this.whereDatum.setTargetTableName(whereDatum.getOwnerTableName());
        this.whereDatum.setTargetTableAlias(whereDatum.getOwnerTableAlias());
        this.whereDatum.setTargetColumnName(whereDatum.getOwnerColumnName());
        this.whereDatum.setTargetColumnAlias(whereDatum.getOwnerColumnAlias());
        this.whereDatum.setTargetMappingFieldName(whereDatum.getOwnerMappingFieldName());
        this.addSqlPartDatum(this.whereDatum);
        return this.getHelper();
    }

    @Override
    public T lessThanAndEqualTo(WhereSqlPartDatumBuilder whereSqlDataBuilder) {
        this.whereDatum.setWhereType(WhereType.LESS_EQUAL);
        this.whereDatum.setWhereValueType(WhereValueType.JOIN);
        WhereDatum whereDatum = whereSqlDataBuilder.whereDatum;
        this.whereDatum.setTargetTableName(whereDatum.getOwnerTableName());
        this.whereDatum.setTargetTableAlias(whereDatum.getOwnerTableAlias());
        this.whereDatum.setTargetColumnName(whereDatum.getOwnerColumnName());
        this.whereDatum.setTargetColumnAlias(whereDatum.getOwnerColumnAlias());
        this.whereDatum.setTargetMappingFieldName(whereDatum.getOwnerMappingFieldName());
        this.addSqlPartDatum(this.whereDatum);
        return this.getHelper();
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T equalTo(Class<S> tableHelperClass, String tableAlias, WhereColumnCallback<SC> callback) {
        this.whereDatum.setWhereType(WhereType.EQUAL);
        this.whereDatum.setWhereValueType(WhereValueType.JOIN);
        SC sc = BeanUtils.tableHelper(tableHelperClass).newColumnHelper();
        sc = callback.apply(sc);
        Set<ColumnDatum> columnData = sc.takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return this.getHelper();
        }
        if (columnData.size() > 1) {
            throw new SqlException("you can not set more than one column for equalTo.");
        }
        ColumnDatum columnDatum = columnData.iterator().next();
        this.whereDatum.setTargetTableName(columnDatum.getTableName());
        this.whereDatum.setTargetTableAlias(columnDatum.getTableAlias());
        this.whereDatum.setTargetColumnName(columnDatum.getColumnName());
        this.whereDatum.setTargetColumnAlias(columnDatum.getColumnAlias());
        this.whereDatum.setTargetMappingFieldName(columnDatum.getMappingFieldName());
        this.addSqlPartDatum(this.whereDatum);
        return this.getHelper();
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T notEqualTo(Class<S> tableHelperClass, String tableAlias, WhereColumnCallback<SC> callback) {
        this.whereDatum.setWhereType(WhereType.NOT_EQUAL);
        this.whereDatum.setWhereValueType(WhereValueType.JOIN);
        SC sc = BeanUtils.tableHelper(tableHelperClass).newColumnHelper();
        sc = callback.apply(sc);
        Set<ColumnDatum> columnData = sc.takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return this.getHelper();
        }
        if (columnData.size() > 1) {
            throw new SqlException("you can not set more than one column for equalTo.");
        }
        ColumnDatum columnDatum = columnData.iterator().next();
        this.whereDatum.setTargetTableName(columnDatum.getTableName());
        this.whereDatum.setTargetTableAlias(columnDatum.getTableAlias());
        this.whereDatum.setTargetColumnName(columnDatum.getColumnName());
        this.whereDatum.setTargetColumnAlias(columnDatum.getColumnAlias());
        this.whereDatum.setTargetMappingFieldName(columnDatum.getMappingFieldName());
        this.addSqlPartDatum(this.whereDatum);
        return this.getHelper();
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T greaterThan(Class<S> tableHelperClass, String tableAlias, WhereColumnCallback<SC> callback) {
        this.whereDatum.setWhereType(WhereType.GREATER);
        this.whereDatum.setWhereValueType(WhereValueType.JOIN);
        SC sc = BeanUtils.tableHelper(tableHelperClass).newColumnHelper();
        sc = callback.apply(sc);
        Set<ColumnDatum> columnData = sc.takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return this.getHelper();
        }
        if (columnData.size() > 1) {
            throw new SqlException("you can not set more than one column for equalTo.");
        }
        ColumnDatum columnDatum = columnData.iterator().next();
        this.whereDatum.setTargetTableName(columnDatum.getTableName());
        this.whereDatum.setTargetTableAlias(columnDatum.getTableAlias());
        this.whereDatum.setTargetColumnName(columnDatum.getColumnName());
        this.whereDatum.setTargetColumnAlias(columnDatum.getColumnAlias());
        this.whereDatum.setTargetMappingFieldName(columnDatum.getMappingFieldName());
        this.addSqlPartDatum(this.whereDatum);
        return this.getHelper();
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T greaterThanAndEqualTo(Class<S> tableHelperClass, String tableAlias, WhereColumnCallback<SC> callback) {
        this.whereDatum.setWhereType(WhereType.GREATER_EQUAL);
        this.whereDatum.setWhereValueType(WhereValueType.JOIN);
        SC sc = BeanUtils.tableHelper(tableHelperClass).newColumnHelper();
        sc = callback.apply(sc);
        Set<ColumnDatum> columnData = sc.takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return this.getHelper();
        }
        if (columnData.size() > 1) {
            throw new SqlException("you can not set more than one column for equalTo.");
        }
        ColumnDatum columnDatum = columnData.iterator().next();
        this.whereDatum.setTargetTableName(columnDatum.getTableName());
        this.whereDatum.setTargetTableAlias(columnDatum.getTableAlias());
        this.whereDatum.setTargetColumnName(columnDatum.getColumnName());
        this.whereDatum.setTargetColumnAlias(columnDatum.getColumnAlias());
        this.whereDatum.setTargetMappingFieldName(columnDatum.getMappingFieldName());
        this.addSqlPartDatum(this.whereDatum);
        return this.getHelper();
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T lessThan(Class<S> tableHelperClass, String tableAlias, WhereColumnCallback<SC> callback) {
        this.whereDatum.setWhereType(WhereType.LESS);
        this.whereDatum.setWhereValueType(WhereValueType.JOIN);
        SC sc = BeanUtils.tableHelper(tableHelperClass).newColumnHelper();
        sc = callback.apply(sc);
        Set<ColumnDatum> columnData = sc.takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return this.getHelper();
        }
        if (columnData.size() > 1) {
            throw new SqlException("you can not set more than one column for equalTo.");
        }
        ColumnDatum columnDatum = columnData.iterator().next();
        this.whereDatum.setTargetTableName(columnDatum.getTableName());
        this.whereDatum.setTargetTableAlias(columnDatum.getTableAlias());
        this.whereDatum.setTargetColumnName(columnDatum.getColumnName());
        this.whereDatum.setTargetColumnAlias(columnDatum.getColumnAlias());
        this.whereDatum.setTargetMappingFieldName(columnDatum.getMappingFieldName());
        this.addSqlPartDatum(this.whereDatum);
        return this.getHelper();
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T lessThanAndEqualTo(Class<S> tableHelperClass, String tableAlias, WhereColumnCallback<SC> callback) {
        this.whereDatum.setWhereType(WhereType.LESS_EQUAL);
        this.whereDatum.setWhereValueType(WhereValueType.JOIN);
        SC sc = BeanUtils.tableHelper(tableHelperClass).newColumnHelper();
        sc = callback.apply(sc);
        Set<ColumnDatum> columnData = sc.takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return this.getHelper();
        }
        if (columnData.size() > 1) {
            throw new SqlException("you can not set more than one column for equalTo.");
        }
        ColumnDatum columnDatum = columnData.iterator().next();
        this.whereDatum.setTargetTableName(columnDatum.getTableName());
        this.whereDatum.setTargetTableAlias(columnDatum.getTableAlias());
        this.whereDatum.setTargetColumnName(columnDatum.getColumnName());
        this.whereDatum.setTargetColumnAlias(columnDatum.getColumnAlias());
        this.whereDatum.setTargetMappingFieldName(columnDatum.getMappingFieldName());
        this.addSqlPartDatum(this.whereDatum);
        return this.getHelper();
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T equalToSubQuery(String tableName, Class<S> tableHelperClass, String tableAlias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
/*        SqlBuilder sqlBuilder = SubQueryCallback.execute(this.getModel().getSqlData(), tableName, modelClass, alias, callback);
        this.whereDatum.setWhereType(WhereType.EQUAL);
        this.whereDatum.setWhereValueType(WhereValueType.SUB_QUERY);
        this.whereDatum.setTargetSubQuery(sqlBuilder);
        this.addSqlModelDatum(this.whereDatum);*/
        return this.getHelper();
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T notEqualToSubQuery(String tableName, Class<S> tableHelperClass, String tableAlias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
        return null;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T greaterThanSubQuery(String tableName, Class<S> tableHelperClass, String tableAlias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
        return null;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T greaterThanAndEqualToSubQuery(String tableName, Class<S> tableHelperClass, String tableAlias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
        return null;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T lessThanSubQuery(String tableName, Class<S> tableHelperClass, String tableAlias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
        return null;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T lessThanAndEqualToSubQuery(String tableName, Class<S> tableHelperClass, String tableAlias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
        return null;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T likeSubQuery(String tableName, Class<S> tableHelperClass, String tableAlias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
        return null;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T inSubQuery(String tableName, Class<S> tableHelperClass, String tableAlias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
        return null;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T notInSubQuery(String tableName, Class<S> tableHelperClass, String tableAlias, SubQueryCallback<S, SO, SC, SW, SG, SS> callback) {
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
