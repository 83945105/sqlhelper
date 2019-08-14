package pub.avalon.sqlhelper.core.builder;

import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.callback.OnColumnCallback;
import pub.avalon.sqlhelper.core.comparison.OnComparisonOperator;
import pub.avalon.sqlhelper.core.data.ColumnDatum;
import pub.avalon.sqlhelper.core.data.OnDatum;
import pub.avalon.sqlhelper.core.exception.ComparisonException;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;
import pub.avalon.sqlhelper.core.utils.ExceptionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Join Sql片段数据构建器
 *
 * @author baichao
 * @since 2018/7/10
 */
public final class JoinSqlPartDatumBuilder<T extends Helper> extends AbstractSqlPartDatumBuilder<T, OnDatum> implements OnComparisonOperator<T> {

    private OnDatum onDatum;

    private SqlBuilderOptions sqlBuilderOptions = SqlBuilderOptions.DEFAULT_SQL_BUILDER_OPTIONS;

    public JoinSqlPartDatumBuilder(String tableAlias, T helper) {
        super(tableAlias, helper);
    }

    @Override
    public void accept(String templateTableName, String templateTableAlias, String sqlPart) {

    }

    @Override
    public void accept(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName, ColumnHandler... columnHandlers) {
        this.onDatum = new OnDatum(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName)
                .setTableAlias(this.tableAlias);
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
        this.addSqlPartDatum(this.onDatum
                .setOnType(OnType.IS_NULL)
                .setOnValueType(OnValueType.VALUE)
                .setValueCount(0));
        return this.getHelper();
    }

    @Override
    public T isNotNull() {
        this.addSqlPartDatum(this.onDatum
                .setOnType(OnType.IS_NOT_NULL)
                .setOnValueType(OnValueType.VALUE)
                .setValueCount(0));
        return this.getHelper();
    }

    @Override
    public T equalTo(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonException("join table alias [" + this.onDatum.getTableAlias() + "] column [" + this.onDatum.getColumnName() + "] equalTo, the value can not be null.");
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
            }
        }
        this.addSqlPartDatum(this.onDatum
                .setOnType(OnType.EQUAL)
                .setOnValueType(OnValueType.VALUE)
                .setValueCount(1)
                .setTargetValue(value));
        return this.getHelper();
    }

    @Override
    public T notEqualTo(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonException("join table alias [" + this.onDatum.getTableAlias() + "] column [" + this.onDatum.getColumnName() + "] notEqualTo, the value can not be null.");
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
            }
        }
        this.addSqlPartDatum(this.onDatum
                .setOnType(OnType.NOT_EQUAL)
                .setOnValueType(OnValueType.VALUE)
                .setValueCount(1)
                .setTargetValue(value));
        return this.getHelper();
    }

    @Override
    public T greaterThan(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonException("join table alias [" + this.onDatum.getTableAlias() + "] column [" + this.onDatum.getColumnName() + "] greaterThan, the value can not be null.");
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
            }
        }
        this.addSqlPartDatum(this.onDatum
                .setOnType(OnType.GREATER)
                .setOnValueType(OnValueType.VALUE)
                .setValueCount(1)
                .setTargetValue(value));
        return this.getHelper();
    }

    @Override
    public T greaterThanAndEqualTo(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonException("join table alias [" + this.onDatum.getTableAlias() + "] column [" + this.onDatum.getColumnName() + "] greaterThanAndEqualTo, the value can not be null.");
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
            }
        }
        this.addSqlPartDatum(this.onDatum
                .setOnType(OnType.GREATER_EQUAL)
                .setOnValueType(OnValueType.VALUE)
                .setValueCount(1)
                .setTargetValue(value));
        return this.getHelper();
    }

    @Override
    public T lessThan(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonException("join table alias [" + this.onDatum.getTableAlias() + "] column [" + this.onDatum.getColumnName() + "] lessThan, the value can not be null.");
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
            }
        }
        this.addSqlPartDatum(this.onDatum
                .setOnType(OnType.LESS)
                .setOnValueType(OnValueType.VALUE)
                .setValueCount(1)
                .setTargetValue(value));
        return this.getHelper();
    }

    @Override
    public T lessThanAndEqualTo(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonException("join table alias [" + this.onDatum.getTableAlias() + "] column [" + this.onDatum.getColumnName() + "] lessThanAndEqualTo, the value can not be null.");
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
            }
        }
        this.addSqlPartDatum(this.onDatum
                .setOnType(OnType.LESS_EQUAL)
                .setOnValueType(OnValueType.VALUE)
                .setValueCount(1)
                .setTargetValue(value));
        return this.getHelper();
    }

    @Override
    public T between(Object value, Object secondValue, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonException("join table alias [" + this.onDatum.getTableAlias() + "] column [" + this.onDatum.getColumnName() + "] between, the value can not be null.");
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
            }
        }
        if (secondValue == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonException("join table alias [" + this.onDatum.getTableAlias() + "] column [" + this.onDatum.getColumnName() + "] between, the secondValue can not be null.");
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
            }
        }
        this.addSqlPartDatum(this.onDatum
                .setOnType(OnType.BETWEEN)
                .setOnValueType(OnValueType.VALUE)
                .setValueCount(2)
                .setTargetValue(value)
                .setTargetSecondValue(secondValue));
        return this.getHelper();
    }

    @Override
    public T like(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonException("join table alias [" + this.onDatum.getTableAlias() + "] column [" + this.onDatum.getColumnName() + "] like, the value can not be null.");
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
            }
        }
        this.addSqlPartDatum(this.onDatum
                .setOnType(OnType.LIKE)
                .setOnValueType(OnValueType.VALUE)
                .setValueCount(1)
                .setTargetValue(value));
        return this.getHelper();
    }

    @Override
    public T in(Object[] values, ComparisonRule comparisonRule) {
        if (values == null || values.length == 0) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonException("join table alias [" + this.onDatum.getTableAlias() + "] column [" + this.onDatum.getColumnName() + "] in, the values can not be null or size = 0.");
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
            }
        }
        this.addSqlPartDatum(this.onDatum
                .setOnType(OnType.IN)
                .setOnValueType(OnValueType.VALUE)
                .setValueCount(values.length)
                .setTargetValue(values));
        return this.getHelper();
    }

    @Override
    public T in(Collection<Object> values, ComparisonRule comparisonRule) {
        if (values == null || values.size() == 0) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonException("join table alias [" + this.onDatum.getTableAlias() + "] column [" + this.onDatum.getColumnName() + "] in, the values can not be null or size = 0.");
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
            }
        }
        this.addSqlPartDatum(this.onDatum
                .setOnType(OnType.IN)
                .setOnValueType(OnValueType.VALUE)
                .setValueCount(values.size())
                .setTargetValue(values));
        return this.getHelper();
    }

    @Override
    public T notIn(Object[] values, ComparisonRule comparisonRule) {
        if (values == null || values.length == 0) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonException("join table alias [" + this.onDatum.getTableAlias() + "] column [" + this.onDatum.getColumnName() + "] in, the values can not be null or size = 0.");
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
            }
        }
        this.addSqlPartDatum(this.onDatum
                .setOnType(OnType.NOT_IN)
                .setOnValueType(OnValueType.VALUE)
                .setValueCount(values.length)
                .setTargetValue(values));
        return this.getHelper();
    }

    @Override
    public T notIn(Collection<Object> values, ComparisonRule comparisonRule) {
        if (values == null || values.size() == 0) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonException("join table alias [" + this.onDatum.getTableAlias() + "] column [" + this.onDatum.getColumnName() + "] in, the values can not be null or size = 0.");
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
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
    public T equalTo(JoinSqlPartDatumBuilder onSqlDataBuilder) {
        this.onDatum.setOnType(OnType.EQUAL);
        this.onDatum.setOnValueType(OnValueType.JOIN_ON);
        this.onDatum.setTargetOnData(Collections.singletonList(onSqlDataBuilder.onDatum));
        this.addSqlPartDatum(this.onDatum);
        return this.getHelper();
    }

    @Override
    public T notEqualTo(JoinSqlPartDatumBuilder onSqlDataBuilder) {
        this.onDatum.setOnType(OnType.NOT_EQUAL);
        this.onDatum.setOnValueType(OnValueType.JOIN_ON);
        this.onDatum.setTargetOnData(Collections.singletonList(onSqlDataBuilder.onDatum));
        this.addSqlPartDatum(this.onDatum);
        return this.getHelper();
    }

    @Override
    public T greaterThan(JoinSqlPartDatumBuilder onSqlDataBuilder) {
        this.onDatum.setOnType(OnType.GREATER);
        this.onDatum.setOnValueType(OnValueType.JOIN_ON);
        this.onDatum.setTargetOnData(Collections.singletonList(onSqlDataBuilder.onDatum));
        this.addSqlPartDatum(this.onDatum);
        return this.getHelper();
    }

    @Override
    public T greaterThanAndEqualTo(JoinSqlPartDatumBuilder onSqlDataBuilder) {
        this.onDatum.setOnType(OnType.GREATER_EQUAL);
        this.onDatum.setOnValueType(OnValueType.JOIN_ON);
        this.onDatum.setTargetOnData(Collections.singletonList(onSqlDataBuilder.onDatum));
        this.addSqlPartDatum(this.onDatum);
        return this.getHelper();
    }

    @Override
    public T lessThan(JoinSqlPartDatumBuilder onSqlDataBuilder) {
        this.onDatum.setOnType(OnType.LESS);
        this.onDatum.setOnValueType(OnValueType.JOIN_ON);
        this.onDatum.setTargetOnData(Collections.singletonList(onSqlDataBuilder.onDatum));
        this.addSqlPartDatum(this.onDatum);
        return this.getHelper();
    }

    @Override
    public T lessThanAndEqualTo(JoinSqlPartDatumBuilder onSqlDataBuilder) {
        this.onDatum.setOnType(OnType.LESS_EQUAL);
        this.onDatum.setOnValueType(OnValueType.JOIN_ON);
        this.onDatum.setTargetOnData(Collections.singletonList(onSqlDataBuilder.onDatum));
        this.addSqlPartDatum(this.onDatum);
        return this.getHelper();
    }

    @Override
    public <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T equalTo(Class<S> tableHelperClass, String tableAlias, OnColumnCallback<SC> callback) {
        this.onDatum.setOnType(OnType.EQUAL);
        this.onDatum.setOnValueType(OnValueType.JOIN_COLUMN);
        S s = BeanUtils.tableHelper(tableHelperClass);
        SC sc = s.newColumnHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        List<ColumnDatum> columnData = callback.apply(sc).takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return this.getHelper();
        }
        this.onDatum.setTargetColumnData(columnData);
        this.addSqlPartDatum(this.onDatum);
        return this.getHelper();
    }

    @Override
    public <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T notEqualTo(Class<S> tableHelperClass, String tableAlias, OnColumnCallback<SC> callback) {
        this.onDatum.setOnType(OnType.NOT_EQUAL);
        this.onDatum.setOnValueType(OnValueType.JOIN_COLUMN);
        S s = BeanUtils.tableHelper(tableHelperClass);
        SC sc = s.newColumnHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        List<ColumnDatum> columnData = callback.apply(sc).takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return this.getHelper();
        }
        this.onDatum.setTargetColumnData(columnData);
        this.addSqlPartDatum(this.onDatum);
        return this.getHelper();
    }

    @Override
    public <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T greaterThan(Class<S> tableHelperClass, String tableAlias, OnColumnCallback<SC> callback) {
        this.onDatum.setOnType(OnType.GREATER);
        this.onDatum.setOnValueType(OnValueType.JOIN_COLUMN);
        S s = BeanUtils.tableHelper(tableHelperClass);
        SC sc = s.newColumnHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        List<ColumnDatum> columnData = callback.apply(sc).takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return this.getHelper();
        }
        this.onDatum.setTargetColumnData(columnData);
        this.addSqlPartDatum(this.onDatum);
        return this.getHelper();
    }

    @Override
    public <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T greaterThanAndEqualTo(Class<S> tableHelperClass, String tableAlias, OnColumnCallback<SC> callback) {
        this.onDatum.setOnType(OnType.GREATER_EQUAL);
        this.onDatum.setOnValueType(OnValueType.JOIN_COLUMN);
        S s = BeanUtils.tableHelper(tableHelperClass);
        SC sc = s.newColumnHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        List<ColumnDatum> columnData = callback.apply(sc).takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return this.getHelper();
        }
        this.onDatum.setTargetColumnData(columnData);
        this.addSqlPartDatum(this.onDatum);
        return this.getHelper();
    }

    @Override
    public <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T lessThan(Class<S> tableHelperClass, String tableAlias, OnColumnCallback<SC> callback) {
        this.onDatum.setOnType(OnType.LESS);
        this.onDatum.setOnValueType(OnValueType.JOIN_COLUMN);
        S s = BeanUtils.tableHelper(tableHelperClass);
        SC sc = s.newColumnHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        List<ColumnDatum> columnData = callback.apply(sc).takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return this.getHelper();
        }
        this.onDatum.setTargetColumnData(columnData);
        this.addSqlPartDatum(this.onDatum);
        return this.getHelper();
    }

    @Override
    public <S extends TableHelper<S, SJ, SC, SW, SG, SH, SS>,
            SJ extends JoinHelper<SJ>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T lessThanAndEqualTo(Class<S> tableHelperClass, String tableAlias, OnColumnCallback<SC> callback) {
        this.onDatum.setOnType(OnType.LESS_EQUAL);
        this.onDatum.setOnValueType(OnValueType.JOIN_COLUMN);
        S s = BeanUtils.tableHelper(tableHelperClass);
        SC sc = s.newColumnHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        List<ColumnDatum> columnData = callback.apply(sc).takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return this.getHelper();
        }
        this.onDatum.setTargetColumnData(columnData);
        this.addSqlPartDatum(this.onDatum);
        return this.getHelper();
    }

}
