package pub.avalon.sqlhelper.core.builder;

import pub.avalon.sqlhelper.core.beans.BeanUtils;
import pub.avalon.sqlhelper.core.beans.ColumnHandler;
import pub.avalon.sqlhelper.core.beans.ComparisonRule;
import pub.avalon.sqlhelper.core.beans.OnType;
import pub.avalon.sqlhelper.core.callback.OnColumnCallback;
import pub.avalon.sqlhelper.core.comparison.OnComparisonOperator;
import pub.avalon.sqlhelper.core.data.ColumnDatum;
import pub.avalon.sqlhelper.core.data.OnDatum;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;
import pub.avalon.sqlhelper.core.utils.ExceptionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author baichao
 */
public final class JoinSqlPartDatumBuilder<T extends Helper> extends AbstractSqlPartDatumBuilder<T, OnDatum> implements OnComparisonOperator<T> {

    private OnDatum onDatum;

    private SqlBuilderOptions sqlBuilderOptions = SqlBuilderOptions.DEFAULT_SQL_BUILDER_OPTIONS;

    public JoinSqlPartDatumBuilder(String tableAlias, T helper) {
        super(tableAlias, helper);
    }

    @Override
    public void accept(String templateTableName, String templateTableAlias, String sqlPart) {
        this.onDatum = new OnDatum(templateTableName, templateTableAlias, sqlPart)
                .setTableAlias(this.tableAlias);
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
                .setType(OnDatum.Type.VALUE)
                .setValueCount(0));
        return this.getHelper();
    }

    @Override
    public T isNotNull() {
        this.addSqlPartDatum(this.onDatum
                .setOnType(OnType.IS_NOT_NULL)
                .setType(OnDatum.Type.VALUE)
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
                    ExceptionUtils.comparisonRuleNullException(WhereSqlPartDatumBuilder.class, "equalTo", this.onDatum.getTableName(), this.onDatum.getTableAlias(), this.onDatum.getColumnName(), this.onDatum.getColumnAlias());
                    break;
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
            }
        }
        this.addSqlPartDatum(this.onDatum
                .setOnType(OnType.EQUAL)
                .setType(OnDatum.Type.VALUE)
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
                    ExceptionUtils.comparisonRuleNullException(WhereSqlPartDatumBuilder.class, "notEqualTo", this.onDatum.getTableName(), this.onDatum.getTableAlias(), this.onDatum.getColumnName(), this.onDatum.getColumnAlias());
                    break;
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
            }
        }
        this.addSqlPartDatum(this.onDatum
                .setOnType(OnType.NOT_EQUAL)
                .setType(OnDatum.Type.VALUE)
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
                    ExceptionUtils.comparisonRuleNullException(WhereSqlPartDatumBuilder.class, "greaterThan", this.onDatum.getTableName(), this.onDatum.getTableAlias(), this.onDatum.getColumnName(), this.onDatum.getColumnAlias());
                    break;
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
            }
        }
        this.addSqlPartDatum(this.onDatum
                .setOnType(OnType.GREATER)
                .setType(OnDatum.Type.VALUE)
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
                    ExceptionUtils.comparisonRuleNullException(WhereSqlPartDatumBuilder.class, "greaterThanAndEqualTo", this.onDatum.getTableName(), this.onDatum.getTableAlias(), this.onDatum.getColumnName(), this.onDatum.getColumnAlias());
                    break;
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
            }
        }
        this.addSqlPartDatum(this.onDatum
                .setOnType(OnType.GREATER_EQUAL)
                .setType(OnDatum.Type.VALUE)
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
                    ExceptionUtils.comparisonRuleNullException(WhereSqlPartDatumBuilder.class, "lessThan", this.onDatum.getTableName(), this.onDatum.getTableAlias(), this.onDatum.getColumnName(), this.onDatum.getColumnAlias());
                    break;
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
            }
        }
        this.addSqlPartDatum(this.onDatum
                .setOnType(OnType.LESS)
                .setType(OnDatum.Type.VALUE)
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
                    ExceptionUtils.comparisonRuleNullException(WhereSqlPartDatumBuilder.class, "lessThanAndEqualTo", this.onDatum.getTableName(), this.onDatum.getTableAlias(), this.onDatum.getColumnName(), this.onDatum.getColumnAlias());
                    break;
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
            }
        }
        this.addSqlPartDatum(this.onDatum
                .setOnType(OnType.LESS_EQUAL)
                .setType(OnDatum.Type.VALUE)
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
                    ExceptionUtils.comparisonRuleNullException(WhereSqlPartDatumBuilder.class, "between", this.onDatum.getTableName(), this.onDatum.getTableAlias(), this.onDatum.getColumnName(), this.onDatum.getColumnAlias());
                    break;
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
            }
        }
        if (secondValue == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    ExceptionUtils.comparisonRuleNullException(WhereSqlPartDatumBuilder.class, "between", this.onDatum.getTableName(), this.onDatum.getTableAlias(), this.onDatum.getColumnName(), this.onDatum.getColumnAlias());
                    break;
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
            }
        }
        this.addSqlPartDatum(this.onDatum
                .setOnType(OnType.BETWEEN)
                .setType(OnDatum.Type.VALUE)
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
                    ExceptionUtils.comparisonRuleNullException(WhereSqlPartDatumBuilder.class, "like", this.onDatum.getTableName(), this.onDatum.getTableAlias(), this.onDatum.getColumnName(), this.onDatum.getColumnAlias());
                    break;
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
            }
        }
        this.addSqlPartDatum(this.onDatum
                .setOnType(OnType.LIKE)
                .setType(OnDatum.Type.VALUE)
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
                    ExceptionUtils.comparisonRuleNullException(WhereSqlPartDatumBuilder.class, "in", this.onDatum.getTableName(), this.onDatum.getTableAlias(), this.onDatum.getColumnName(), this.onDatum.getColumnAlias());
                    break;
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
            }
        }
        this.addSqlPartDatum(this.onDatum
                .setOnType(OnType.IN)
                .setType(OnDatum.Type.VALUE)
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
                    ExceptionUtils.comparisonRuleNullException(WhereSqlPartDatumBuilder.class, "in", this.onDatum.getTableName(), this.onDatum.getTableAlias(), this.onDatum.getColumnName(), this.onDatum.getColumnAlias());
                    break;
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
            }
        }
        this.addSqlPartDatum(this.onDatum
                .setOnType(OnType.IN)
                .setType(OnDatum.Type.VALUE)
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
                    ExceptionUtils.comparisonRuleNullException(WhereSqlPartDatumBuilder.class, "notIn", this.onDatum.getTableName(), this.onDatum.getTableAlias(), this.onDatum.getColumnName(), this.onDatum.getColumnAlias());
                    break;
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
            }
        }
        this.addSqlPartDatum(this.onDatum
                .setOnType(OnType.NOT_IN)
                .setType(OnDatum.Type.VALUE)
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
                    ExceptionUtils.comparisonRuleNullException(WhereSqlPartDatumBuilder.class, "notIn", this.onDatum.getTableName(), this.onDatum.getTableAlias(), this.onDatum.getColumnName(), this.onDatum.getColumnAlias());
                    break;
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
            }
        }
        this.addSqlPartDatum(this.onDatum
                .setOnType(OnType.NOT_IN)
                .setType(OnDatum.Type.VALUE)
                .setValueCount(values.size())
                .setTargetValue(values));
        return this.getHelper();
    }

    @Override
    public T equalTo(JoinSqlPartDatumBuilder onSqlDataBuilder) {
        this.addSqlPartDatum(this.onDatum
                .setOnType(OnType.EQUAL)
                .setType(OnDatum.Type.JOIN_ON)
                .setTargetOnData(Collections.singletonList(onSqlDataBuilder.onDatum)));
        return this.getHelper();
    }

    @Override
    public T notEqualTo(JoinSqlPartDatumBuilder onSqlDataBuilder) {
        this.addSqlPartDatum(this.onDatum
                .setOnType(OnType.NOT_EQUAL)
                .setType(OnDatum.Type.JOIN_ON)
                .setTargetOnData(Collections.singletonList(onSqlDataBuilder.onDatum)));
        return this.getHelper();
    }

    @Override
    public T greaterThan(JoinSqlPartDatumBuilder onSqlDataBuilder) {
        this.addSqlPartDatum(this.onDatum
                .setOnType(OnType.GREATER)
                .setType(OnDatum.Type.JOIN_ON)
                .setTargetOnData(Collections.singletonList(onSqlDataBuilder.onDatum)));
        return this.getHelper();
    }

    @Override
    public T greaterThanAndEqualTo(JoinSqlPartDatumBuilder onSqlDataBuilder) {
        this.addSqlPartDatum(this.onDatum
                .setOnType(OnType.GREATER_EQUAL)
                .setType(OnDatum.Type.JOIN_ON)
                .setTargetOnData(Collections.singletonList(onSqlDataBuilder.onDatum)));
        return this.getHelper();
    }

    @Override
    public T lessThan(JoinSqlPartDatumBuilder onSqlDataBuilder) {
        this.addSqlPartDatum(this.onDatum
                .setOnType(OnType.LESS)
                .setType(OnDatum.Type.JOIN_ON)
                .setTargetOnData(Collections.singletonList(onSqlDataBuilder.onDatum)));
        return this.getHelper();
    }

    @Override
    public T lessThanAndEqualTo(JoinSqlPartDatumBuilder onSqlDataBuilder) {
        this.addSqlPartDatum(this.onDatum
                .setOnType(OnType.LESS_EQUAL)
                .setType(OnDatum.Type.JOIN_ON)
                .setTargetOnData(Collections.singletonList(onSqlDataBuilder.onDatum)));
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
        S s = BeanUtils.tableHelper(tableHelperClass);
        SC sc = s.newColumnHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        List<ColumnDatum> columnData = callback.apply(sc).takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return this.getHelper();
        }
        this.addSqlPartDatum(this.onDatum
                .setOnType(OnType.EQUAL)
                .setType(OnDatum.Type.JOIN_COLUMN)
                .setTargetColumnData(columnData));
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
        S s = BeanUtils.tableHelper(tableHelperClass);
        SC sc = s.newColumnHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        List<ColumnDatum> columnData = callback.apply(sc).takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return this.getHelper();
        }
        this.addSqlPartDatum(this.onDatum
                .setOnType(OnType.NOT_EQUAL)
                .setType(OnDatum.Type.JOIN_COLUMN)
                .setTargetColumnData(columnData));
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
        S s = BeanUtils.tableHelper(tableHelperClass);
        SC sc = s.newColumnHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        List<ColumnDatum> columnData = callback.apply(sc).takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return this.getHelper();
        }
        this.addSqlPartDatum(this.onDatum
                .setOnType(OnType.GREATER)
                .setType(OnDatum.Type.JOIN_COLUMN)
                .setTargetColumnData(columnData));
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
        S s = BeanUtils.tableHelper(tableHelperClass);
        SC sc = s.newColumnHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        List<ColumnDatum> columnData = callback.apply(sc).takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return this.getHelper();
        }
        this.addSqlPartDatum(this.onDatum
                .setOnType(OnType.GREATER_EQUAL)
                .setType(OnDatum.Type.JOIN_COLUMN)
                .setTargetColumnData(columnData));
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
        S s = BeanUtils.tableHelper(tableHelperClass);
        SC sc = s.newColumnHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        List<ColumnDatum> columnData = callback.apply(sc).takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return this.getHelper();
        }
        this.addSqlPartDatum(this.onDatum
                .setOnType(OnType.LESS)
                .setType(OnDatum.Type.JOIN_COLUMN)
                .setTargetColumnData(columnData));
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
        S s = BeanUtils.tableHelper(tableHelperClass);
        SC sc = s.newColumnHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        List<ColumnDatum> columnData = callback.apply(sc).takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return this.getHelper();
        }
        this.addSqlPartDatum(this.onDatum
                .setOnType(OnType.LESS_EQUAL)
                .setType(OnDatum.Type.JOIN_COLUMN)
                .setTargetColumnData(columnData));
        return this.getHelper();
    }
}