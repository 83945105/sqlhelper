package pub.avalon.sqlhelper.core.builder;

import pub.avalon.sqlhelper.core.beans.BeanUtils;
import pub.avalon.sqlhelper.core.beans.ComparisonRule;
import pub.avalon.sqlhelper.core.beans.OnType;
import pub.avalon.sqlhelper.core.beans.OnValueType;
import pub.avalon.sqlhelper.core.callback.OnColumnCallback;
import pub.avalon.sqlhelper.core.comparison.OnComparisonOperator;
import pub.avalon.sqlhelper.core.data.ColumnDatum;
import pub.avalon.sqlhelper.core.data.OnDatum;
import pub.avalon.sqlhelper.core.exception.ComparisonException;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.Collection;
import java.util.Set;

/**
 * On Sql片段数据构建器
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class OnSqlPartDatumBuilder<T extends Helper<T, OnDatum>> extends AbstractSqlPartDatumBuilder<T, OnDatum> implements OnComparisonOperator<T> {

    private OnDatum onDatum;

    private String tableName;
    private String tableAlias;
    private SqlBuilderOptions sqlBuilderOptions = SqlBuilderOptions.DEFAULT_SQL_BUILDER_OPTIONS;

    @Override
    public void accept(String tableName, String tableAlias, String columnName, String columnAlias, String fieldName) {
        this.onDatum = new OnDatum(this.tableName == null ? tableName : this.tableName, this.tableAlias == null ? tableAlias : this.tableAlias, columnName, columnAlias, fieldName);
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

    @Override
    public ComparisonRule getDefaultComparisonRule() {
        return this.sqlBuilderOptions.getSqlPartDatumBuilderOptions().getDefaultOnComparisonRule();
    }

    @Override
    public T isNull() {
        this.onDatum.setOnType(OnType.IS_NULL);
        this.onDatum.setOnValueType(OnValueType.VALUE);
        this.onDatum.setValueCount(0);
        this.addSqlPartDatum(this.onDatum);
        return this.getHelper();
    }

    @Override
    public T isNotNull() {
        this.onDatum.setOnType(OnType.IS_NOT_NULL);
        this.onDatum.setOnValueType(OnValueType.VALUE);
        this.onDatum.setValueCount(0);
        this.addSqlPartDatum(this.onDatum);
        return this.getHelper();
    }

    @Override
    public T equalToValue(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonException("join table alias [" + this.onDatum.getOwnerTableAlias() + "] column [" + this.onDatum.getOwnerColumnName() + "] equalTo, the value can not be null.");
                default:
                    return null;
            }
        }
        this.onDatum.setOnType(OnType.EQUAL);
        this.onDatum.setOnValueType(OnValueType.VALUE);
        this.onDatum.setValueCount(1);
        this.onDatum.setTargetValue(value);
        this.addSqlPartDatum(this.onDatum);
        return this.getHelper();
    }

    @Override
    public T notEqualToValue(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonException("join table alias [" + this.onDatum.getOwnerTableAlias() + "] column [" + this.onDatum.getOwnerColumnName() + "] notEqualTo, the value can not be null.");
                default:
                    return null;
            }
        }
        this.onDatum.setOnType(OnType.NOT_EQUAL);
        this.onDatum.setOnValueType(OnValueType.VALUE);
        this.onDatum.setValueCount(1);
        this.onDatum.setTargetValue(value);
        this.addSqlPartDatum(this.onDatum);
        return this.getHelper();
    }

    @Override
    public T greaterThanValue(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonException("join table alias [" + this.onDatum.getOwnerTableAlias() + "] column [" + this.onDatum.getOwnerColumnName() + "] greaterThan, the value can not be null.");
                default:
                    return null;
            }
        }
        this.onDatum.setOnType(OnType.GREATER);
        this.onDatum.setOnValueType(OnValueType.VALUE);
        this.onDatum.setValueCount(1);
        this.onDatum.setTargetValue(value);
        this.addSqlPartDatum(this.onDatum);
        return this.getHelper();
    }

    @Override
    public T greaterThanAndEqualToValue(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonException("join table alias [" + this.onDatum.getOwnerTableAlias() + "] column [" + this.onDatum.getOwnerColumnName() + "] greaterThanAndEqualTo, the value can not be null.");
                default:
                    return null;
            }
        }
        this.onDatum.setOnType(OnType.GREATER_EQUAL);
        this.onDatum.setOnValueType(OnValueType.VALUE);
        this.onDatum.setValueCount(1);
        this.onDatum.setTargetValue(value);
        this.addSqlPartDatum(this.onDatum);
        return this.getHelper();
    }

    @Override
    public T lessThanValue(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonException("join table alias [" + this.onDatum.getOwnerTableAlias() + "] column [" + this.onDatum.getOwnerColumnName() + "] lessThan, the value can not be null.");
                default:
                    return null;
            }
        }
        this.onDatum.setOnType(OnType.LESS);
        this.onDatum.setOnValueType(OnValueType.VALUE);
        this.onDatum.setValueCount(1);
        this.onDatum.setTargetValue(value);
        this.addSqlPartDatum(this.onDatum);
        return this.getHelper();
    }

    @Override
    public T lessThanAndEqualToValue(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonException("join table alias [" + this.onDatum.getOwnerTableAlias() + "] column [" + this.onDatum.getOwnerColumnName() + "] lessThanAndEqualTo, the value can not be null.");
                default:
                    return null;
            }
        }
        this.onDatum.setOnType(OnType.LESS_EQUAL);
        this.onDatum.setOnValueType(OnValueType.VALUE);
        this.onDatum.setValueCount(1);
        this.onDatum.setTargetValue(value);
        this.addSqlPartDatum(this.onDatum);
        return this.getHelper();
    }

    @Override
    public T betweenValue(Object value, Object secondValue, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonException("join table alias [" + this.onDatum.getOwnerTableAlias() + "] column [" + this.onDatum.getOwnerColumnName() + "] between, the value can not be null.");
                default:
                    return null;
            }
        }
        if (secondValue == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonException("join table alias [" + this.onDatum.getOwnerTableAlias() + "] column [" + this.onDatum.getOwnerColumnName() + "] between, the secondValue can not be null.");
                default:
                    return null;
            }
        }
        this.onDatum.setOnType(OnType.BETWEEN);
        this.onDatum.setOnValueType(OnValueType.VALUE);
        this.onDatum.setValueCount(2);
        this.onDatum.setTargetValue(value);
        this.onDatum.setTargetSecondValue(secondValue);
        this.addSqlPartDatum(this.onDatum);
        return this.getHelper();
    }

    @Override
    public T likeValue(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonException("join table alias [" + this.onDatum.getOwnerTableAlias() + "] column [" + this.onDatum.getOwnerColumnName() + "] like, the value can not be null.");
                default:
                    return null;
            }
        }
        this.onDatum.setOnType(OnType.LIKE);
        this.onDatum.setOnValueType(OnValueType.VALUE);
        this.onDatum.setValueCount(1);
        this.onDatum.setTargetValue(value);
        this.addSqlPartDatum(this.onDatum);
        return this.getHelper();
    }

    @Override
    public T inValue(Object[] values, ComparisonRule comparisonRule) {
        if (values == null || values.length == 0) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonException("join table alias [" + this.onDatum.getOwnerTableAlias() + "] column [" + this.onDatum.getOwnerColumnName() + "] in, the values can not be null or size = 0.");
                default:
                    return null;
            }
        }
        this.onDatum.setOnType(OnType.IN);
        this.onDatum.setOnValueType(OnValueType.VALUE);
        this.onDatum.setValueCount(values.length);
        this.onDatum.setTargetValue(values);
        this.addSqlPartDatum(this.onDatum);
        return this.getHelper();
    }

    @Override
    public T inValue(Collection<?> values, ComparisonRule comparisonRule) {
        if (values == null || values.size() == 0) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonException("join table alias [" + this.onDatum.getOwnerTableAlias() + "] column [" + this.onDatum.getOwnerColumnName() + "] in, the values can not be null or size = 0.");
                default:
                    return null;
            }
        }
        this.onDatum.setOnType(OnType.IN);
        this.onDatum.setOnValueType(OnValueType.VALUE);
        this.onDatum.setValueCount(values.size());
        this.onDatum.setTargetValue(values);
        this.addSqlPartDatum(this.onDatum);
        return this.getHelper();
    }

    @Override
    public T notInValue(Object[] values, ComparisonRule comparisonRule) {
        if (values == null || values.length == 0) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonException("join table alias [" + this.onDatum.getOwnerTableAlias() + "] column [" + this.onDatum.getOwnerColumnName() + "] in, the values can not be null or size = 0.");
                default:
                    return null;
            }
        }
        this.onDatum.setOnType(OnType.NOT_IN);
        this.onDatum.setOnValueType(OnValueType.VALUE);
        this.onDatum.setValueCount(values.length);
        this.onDatum.setTargetValue(values);
        this.addSqlPartDatum(this.onDatum);
        return this.getHelper();
    }

    @Override
    public T notInValue(Collection<?> values, ComparisonRule comparisonRule) {
        if (values == null || values.size() == 0) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonException("join table alias [" + this.onDatum.getOwnerTableAlias() + "] column [" + this.onDatum.getOwnerColumnName() + "] in, the values can not be null or size = 0.");
                default:
                    return null;
            }
        }
        this.onDatum.setOnType(OnType.NOT_IN);
        this.onDatum.setOnValueType(OnValueType.VALUE);
        this.onDatum.setValueCount(values.size());
        this.onDatum.setTargetValue(values);
        this.addSqlPartDatum(this.onDatum);
        return this.getHelper();
    }

    @Override
    public T equalTo(OnSqlPartDatumBuilder onSqlDataBuilder) {
        this.onDatum.setOnType(OnType.EQUAL);
        this.onDatum.setOnValueType(OnValueType.JOIN);
        OnDatum onDatum = onSqlDataBuilder.onDatum;
        this.onDatum.setTargetTableName(onDatum.getOwnerTableName());
        this.onDatum.setTargetTableAlias(onDatum.getOwnerTableAlias());
        this.onDatum.setTargetColumnName(onDatum.getOwnerColumnName());
        this.onDatum.setTargetColumnAlias(onDatum.getOwnerColumnAlias());
        this.onDatum.setTargetMappingFieldName(onDatum.getOwnerMappingFieldName());
        this.addSqlPartDatum(this.onDatum);
        return this.getHelper();
    }

    @Override
    public T notEqualTo(OnSqlPartDatumBuilder onSqlDataBuilder) {
        this.onDatum.setOnType(OnType.NOT_EQUAL);
        this.onDatum.setOnValueType(OnValueType.JOIN);
        OnDatum onDatum = onSqlDataBuilder.onDatum;
        this.onDatum.setTargetTableName(onDatum.getOwnerTableName());
        this.onDatum.setTargetTableAlias(onDatum.getOwnerTableAlias());
        this.onDatum.setTargetColumnName(onDatum.getOwnerColumnName());
        this.onDatum.setTargetColumnAlias(onDatum.getOwnerColumnAlias());
        this.onDatum.setTargetMappingFieldName(onDatum.getOwnerMappingFieldName());
        this.addSqlPartDatum(this.onDatum);
        return this.getHelper();
    }

    @Override
    public T greaterThan(OnSqlPartDatumBuilder onSqlDataBuilder) {
        this.onDatum.setOnType(OnType.GREATER);
        this.onDatum.setOnValueType(OnValueType.JOIN);
        OnDatum onDatum = onSqlDataBuilder.onDatum;
        this.onDatum.setTargetTableName(onDatum.getOwnerTableName());
        this.onDatum.setTargetTableAlias(onDatum.getOwnerTableAlias());
        this.onDatum.setTargetColumnName(onDatum.getOwnerColumnName());
        this.onDatum.setTargetColumnAlias(onDatum.getOwnerColumnAlias());
        this.onDatum.setTargetMappingFieldName(onDatum.getOwnerMappingFieldName());
        this.addSqlPartDatum(this.onDatum);
        return this.getHelper();
    }

    @Override
    public T greaterThanAndEqualTo(OnSqlPartDatumBuilder onSqlDataBuilder) {
        this.onDatum.setOnType(OnType.GREATER_EQUAL);
        this.onDatum.setOnValueType(OnValueType.JOIN);
        OnDatum onDatum = onSqlDataBuilder.onDatum;
        this.onDatum.setTargetTableName(onDatum.getOwnerTableName());
        this.onDatum.setTargetTableAlias(onDatum.getOwnerTableAlias());
        this.onDatum.setTargetColumnName(onDatum.getOwnerColumnName());
        this.onDatum.setTargetColumnAlias(onDatum.getOwnerColumnAlias());
        this.onDatum.setTargetMappingFieldName(onDatum.getOwnerMappingFieldName());
        this.addSqlPartDatum(this.onDatum);
        return this.getHelper();
    }

    @Override
    public T lessThan(OnSqlPartDatumBuilder onSqlDataBuilder) {
        this.onDatum.setOnType(OnType.LESS);
        this.onDatum.setOnValueType(OnValueType.JOIN);
        OnDatum onDatum = onSqlDataBuilder.onDatum;
        this.onDatum.setTargetTableName(onDatum.getOwnerTableName());
        this.onDatum.setTargetTableAlias(onDatum.getOwnerTableAlias());
        this.onDatum.setTargetColumnName(onDatum.getOwnerColumnName());
        this.onDatum.setTargetColumnAlias(onDatum.getOwnerColumnAlias());
        this.onDatum.setTargetMappingFieldName(onDatum.getOwnerMappingFieldName());
        this.addSqlPartDatum(this.onDatum);
        return this.getHelper();
    }

    @Override
    public T lessThanAndEqualTo(OnSqlPartDatumBuilder onSqlDataBuilder) {
        this.onDatum.setOnType(OnType.LESS_EQUAL);
        this.onDatum.setOnValueType(OnValueType.JOIN);
        OnDatum onDatum = onSqlDataBuilder.onDatum;
        this.onDatum.setTargetTableName(onDatum.getOwnerTableName());
        this.onDatum.setTargetTableAlias(onDatum.getOwnerTableAlias());
        this.onDatum.setTargetColumnName(onDatum.getOwnerColumnName());
        this.onDatum.setTargetColumnAlias(onDatum.getOwnerColumnAlias());
        this.onDatum.setTargetMappingFieldName(onDatum.getOwnerMappingFieldName());
        this.addSqlPartDatum(this.onDatum);
        return this.getHelper();
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T equalTo(Class<S> tableHelperClass, String tableAlias, OnColumnCallback<SC> callback) {
        this.onDatum.setOnType(OnType.EQUAL);
        this.onDatum.setOnValueType(OnValueType.JOIN);
        SC sc = BeanUtils.tableHelper(tableHelperClass).newColumnHelper();
        Set<ColumnDatum> columnData = callback.apply(sc).takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return this.getHelper();
        }
        OnDatum onDatum;
        for (ColumnDatum columnDatum : columnData) {
            onDatum = new OnDatum(this.onDatum.getOwnerTableName(), this.onDatum.getOwnerTableAlias(), this.onDatum.getOwnerColumnName(), this.onDatum.getOwnerColumnAlias(), this.onDatum.getOwnerMappingFieldName());
            onDatum.setOnType(OnType.EQUAL);
            onDatum.setOnValueType(OnValueType.JOIN);
            onDatum.setTargetTableName(columnDatum.getTableName());
            onDatum.setTargetTableAlias(tableAlias == null ? columnDatum.getTableAlias() : tableAlias);
            onDatum.setTargetColumnName(columnDatum.getColumnName());
            onDatum.setTargetColumnAlias(columnDatum.getColumnAlias());
            onDatum.setTargetMappingFieldName(columnDatum.getMappingFieldName());
            this.addSqlPartDatum(onDatum);
        }
        return this.getHelper();
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T notEqualTo(Class<S> tableHelperClass, String tableAlias, OnColumnCallback<SC> callback) {
        this.onDatum.setOnType(OnType.NOT_EQUAL);
        this.onDatum.setOnValueType(OnValueType.JOIN);
        SC sc = BeanUtils.tableHelper(tableHelperClass).newColumnHelper();
        Set<ColumnDatum> columnData = callback.apply(sc).takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return this.getHelper();
        }
        OnDatum onDatum;
        for (ColumnDatum columnDatum : columnData) {
            onDatum = new OnDatum(this.onDatum.getOwnerTableName(), this.onDatum.getOwnerTableAlias(), this.onDatum.getOwnerColumnName(), this.onDatum.getOwnerColumnAlias(), this.onDatum.getOwnerMappingFieldName());
            onDatum.setOnType(OnType.NOT_EQUAL);
            onDatum.setOnValueType(OnValueType.JOIN);
            onDatum.setTargetTableName(columnDatum.getTableName());
            onDatum.setTargetTableAlias(tableAlias == null ? columnDatum.getTableAlias() : tableAlias);
            onDatum.setTargetColumnName(columnDatum.getColumnName());
            onDatum.setTargetColumnAlias(columnDatum.getColumnAlias());
            onDatum.setTargetMappingFieldName(columnDatum.getMappingFieldName());
            this.addSqlPartDatum(onDatum);
        }
        return this.getHelper();
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T greaterThan(Class<S> tableHelperClass, String tableAlias, OnColumnCallback<SC> callback) {
        this.onDatum.setOnType(OnType.GREATER);
        this.onDatum.setOnValueType(OnValueType.JOIN);
        SC sc = BeanUtils.tableHelper(tableHelperClass).newColumnHelper();
        Set<ColumnDatum> columnData = callback.apply(sc).takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return this.getHelper();
        }
        OnDatum onDatum;
        for (ColumnDatum columnDatum : columnData) {
            onDatum = new OnDatum(this.onDatum.getOwnerTableName(), this.onDatum.getOwnerTableAlias(), this.onDatum.getOwnerColumnName(), this.onDatum.getOwnerColumnAlias(), this.onDatum.getOwnerMappingFieldName());
            onDatum.setOnType(OnType.GREATER);
            onDatum.setOnValueType(OnValueType.JOIN);
            onDatum.setTargetTableName(columnDatum.getTableName());
            onDatum.setTargetTableAlias(tableAlias == null ? columnDatum.getTableAlias() : tableAlias);
            onDatum.setTargetColumnName(columnDatum.getColumnName());
            onDatum.setTargetColumnAlias(columnDatum.getColumnAlias());
            onDatum.setTargetMappingFieldName(columnDatum.getMappingFieldName());
            this.addSqlPartDatum(onDatum);
        }
        return this.getHelper();
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T greaterThanAndEqualTo(Class<S> tableHelperClass, String tableAlias, OnColumnCallback<SC> callback) {
        this.onDatum.setOnType(OnType.GREATER_EQUAL);
        this.onDatum.setOnValueType(OnValueType.JOIN);
        SC sc = BeanUtils.tableHelper(tableHelperClass).newColumnHelper();
        Set<ColumnDatum> columnData = callback.apply(sc).takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return this.getHelper();
        }
        OnDatum onDatum;
        for (ColumnDatum columnDatum : columnData) {
            onDatum = new OnDatum(this.onDatum.getOwnerTableName(), this.onDatum.getOwnerTableAlias(), this.onDatum.getOwnerColumnName(), this.onDatum.getOwnerColumnAlias(), this.onDatum.getOwnerMappingFieldName());
            onDatum.setOnType(OnType.GREATER_EQUAL);
            onDatum.setOnValueType(OnValueType.JOIN);
            onDatum.setTargetTableName(columnDatum.getTableName());
            onDatum.setTargetTableAlias(tableAlias == null ? columnDatum.getTableAlias() : tableAlias);
            onDatum.setTargetColumnName(columnDatum.getColumnName());
            onDatum.setTargetColumnAlias(columnDatum.getColumnAlias());
            onDatum.setTargetMappingFieldName(columnDatum.getMappingFieldName());
            this.addSqlPartDatum(onDatum);
        }
        return this.getHelper();
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T lessThan(Class<S> tableHelperClass, String tableAlias, OnColumnCallback<SC> callback) {
        this.onDatum.setOnType(OnType.LESS);
        this.onDatum.setOnValueType(OnValueType.JOIN);
        SC sc = BeanUtils.tableHelper(tableHelperClass).newColumnHelper();
        Set<ColumnDatum> columnData = callback.apply(sc).takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return this.getHelper();
        }
        OnDatum onDatum;
        for (ColumnDatum columnDatum : columnData) {
            onDatum = new OnDatum(this.onDatum.getOwnerTableName(), this.onDatum.getOwnerTableAlias(), this.onDatum.getOwnerColumnName(), this.onDatum.getOwnerColumnAlias(), this.onDatum.getOwnerMappingFieldName());
            onDatum.setOnType(OnType.LESS);
            onDatum.setOnValueType(OnValueType.JOIN);
            onDatum.setTargetTableName(columnDatum.getTableName());
            onDatum.setTargetTableAlias(tableAlias == null ? columnDatum.getTableAlias() : tableAlias);
            onDatum.setTargetColumnName(columnDatum.getColumnName());
            onDatum.setTargetColumnAlias(columnDatum.getColumnAlias());
            onDatum.setTargetMappingFieldName(columnDatum.getMappingFieldName());
            this.addSqlPartDatum(onDatum);
        }
        return this.getHelper();
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SS extends SortHelper<SS>> T lessThanAndEqualTo(Class<S> tableHelperClass, String tableAlias, OnColumnCallback<SC> callback) {
        this.onDatum.setOnType(OnType.LESS_EQUAL);
        this.onDatum.setOnValueType(OnValueType.JOIN);
        SC sc = BeanUtils.tableHelper(tableHelperClass).newColumnHelper();
        Set<ColumnDatum> columnData = callback.apply(sc).takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return this.getHelper();
        }
        OnDatum onDatum;
        for (ColumnDatum columnDatum : columnData) {
            onDatum = new OnDatum(this.onDatum.getOwnerTableName(), this.onDatum.getOwnerTableAlias(), this.onDatum.getOwnerColumnName(), this.onDatum.getOwnerColumnAlias(), this.onDatum.getOwnerMappingFieldName());
            onDatum.setOnType(OnType.LESS_EQUAL);
            onDatum.setOnValueType(OnValueType.JOIN);
            onDatum.setTargetTableName(columnDatum.getTableName());
            onDatum.setTargetTableAlias(tableAlias == null ? columnDatum.getTableAlias() : tableAlias);
            onDatum.setTargetColumnName(columnDatum.getColumnName());
            onDatum.setTargetColumnAlias(columnDatum.getColumnAlias());
            onDatum.setTargetMappingFieldName(columnDatum.getMappingFieldName());
            this.addSqlPartDatum(onDatum);
        }
        return this.getHelper();
    }

}
