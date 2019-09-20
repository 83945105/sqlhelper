package pub.avalon.sqlhelper.core.builder;

import pub.avalon.sqlhelper.core.beans.ColumnHandler;
import pub.avalon.sqlhelper.core.beans.ComparisonRule;
import pub.avalon.sqlhelper.core.beans.WhereType;
import pub.avalon.sqlhelper.core.callback.SubQueryCallback;
import pub.avalon.sqlhelper.core.callback.WhereColumnCallback;
import pub.avalon.sqlhelper.core.comparison.WhereComparisonOperator;
import pub.avalon.sqlhelper.core.data.ColumnDatum;
import pub.avalon.sqlhelper.core.data.WhereDatum;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;
import pub.avalon.sqlhelper.core.utils.ExceptionUtils;
import pub.avalon.sqlhelper.core.utils.HelperManager;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author baichao
 */
public final class WhereSqlPartDatumBuilder<T extends Helper> extends AbstractSqlPartDatumBuilder<T, WhereDatum> implements WhereComparisonOperator<T> {

    private WhereDatum whereDatum;

    private SqlBuilderOptions sqlBuilderOptions = SqlBuilderOptions.DEFAULT_SQL_BUILDER_OPTIONS;

    public WhereSqlPartDatumBuilder(String tableAlias, T helper) {
        super(tableAlias, helper);
    }

    @Override
    public void accept(String templateTableName, String templateTableAlias, String sqlPart) {
        this.whereDatum = new WhereDatum(templateTableName, templateTableAlias, sqlPart)
                .setTableAlias(this.tableAlias);
    }

    @Override
    public void accept(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName, ColumnHandler... columnHandlers) {
        this.whereDatum = new WhereDatum(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName)
                .setTableAlias(this.tableAlias);
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
        this.addSqlPartDatum(this.whereDatum
                .setType(WhereDatum.Type.SQL_PART)
                .setSqlPart(sqlPart));
        return this.getHelper();
    }

    @Override
    public ComparisonRule getDefaultComparisonRule() {
        return this.sqlBuilderOptions.getSqlPartDatumBuilderOptions().getDefaultWhereComparisonRule();
    }

    @Override
    public T isNull() {
        this.addSqlPartDatum(this.whereDatum
                .setWhereType(WhereType.IS_NULL)
                .setValueCount(0));
        return this.getHelper();
    }

    @Override
    public T isNotNull() {
        this.addSqlPartDatum(this.whereDatum
                .setWhereType(WhereType.IS_NOT_NULL)
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
                    ExceptionUtils.comparisonRuleNullException(WhereSqlPartDatumBuilder.class, "equalTo", this.whereDatum.getTableName(), this.whereDatum.getTableAlias(), this.whereDatum.getColumnName(), this.whereDatum.getColumnAlias());
                    break;
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
            }
        }
        this.addSqlPartDatum(this.whereDatum
                .setWhereType(WhereType.EQUAL)
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
                    ExceptionUtils.comparisonRuleNullException(WhereSqlPartDatumBuilder.class, "notEqualTo", this.whereDatum.getTableName(), this.whereDatum.getTableAlias(), this.whereDatum.getColumnName(), this.whereDatum.getColumnAlias());
                    break;
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
            }
        }
        this.addSqlPartDatum(this.whereDatum
                .setWhereType(WhereType.NOT_EQUAL)
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
                    ExceptionUtils.comparisonRuleNullException(WhereSqlPartDatumBuilder.class, "greaterThan", this.whereDatum.getTableName(), this.whereDatum.getTableAlias(), this.whereDatum.getColumnName(), this.whereDatum.getColumnAlias());
                    break;
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
            }
        }
        this.addSqlPartDatum(this.whereDatum
                .setWhereType(WhereType.GREATER)
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
                    ExceptionUtils.comparisonRuleNullException(WhereSqlPartDatumBuilder.class, "greaterThanAndEqualTo", this.whereDatum.getTableName(), this.whereDatum.getTableAlias(), this.whereDatum.getColumnName(), this.whereDatum.getColumnAlias());
                    break;
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
            }
        }
        this.addSqlPartDatum(this.whereDatum
                .setWhereType(WhereType.GREATER_EQUAL)
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
                    ExceptionUtils.comparisonRuleNullException(WhereSqlPartDatumBuilder.class, "lessThan", this.whereDatum.getTableName(), this.whereDatum.getTableAlias(), this.whereDatum.getColumnName(), this.whereDatum.getColumnAlias());
                    break;
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
            }
        }
        this.addSqlPartDatum(this.whereDatum
                .setWhereType(WhereType.LESS)
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
                    ExceptionUtils.comparisonRuleNullException(WhereSqlPartDatumBuilder.class, "lessThanAndEqualTo", this.whereDatum.getTableName(), this.whereDatum.getTableAlias(), this.whereDatum.getColumnName(), this.whereDatum.getColumnAlias());
                    break;
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
            }
        }
        this.addSqlPartDatum(this.whereDatum
                .setWhereType(WhereType.LESS_EQUAL)
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
                    ExceptionUtils.comparisonRuleNullException(WhereSqlPartDatumBuilder.class, "between", this.whereDatum.getTableName(), this.whereDatum.getTableAlias(), this.whereDatum.getColumnName(), this.whereDatum.getColumnAlias());
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
                    ExceptionUtils.comparisonRuleNullException(WhereSqlPartDatumBuilder.class, "secondValue", this.whereDatum.getTableName(), this.whereDatum.getTableAlias(), this.whereDatum.getColumnName(), this.whereDatum.getColumnAlias());
                    break;
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
            }
        }
        this.addSqlPartDatum(this.whereDatum
                .setWhereType(WhereType.BETWEEN)
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
                    ExceptionUtils.comparisonRuleNullException(WhereSqlPartDatumBuilder.class, "like", this.whereDatum.getTableName(), this.whereDatum.getTableAlias(), this.whereDatum.getColumnName(), this.whereDatum.getColumnAlias());
                    break;
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
            }
        }
        this.addSqlPartDatum(this.whereDatum
                .setWhereType(WhereType.LIKE)
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
                    ExceptionUtils.comparisonRuleNullException(WhereSqlPartDatumBuilder.class, "in", this.whereDatum.getTableName(), this.whereDatum.getTableAlias(), this.whereDatum.getColumnName(), this.whereDatum.getColumnAlias());
                    break;
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
            }
        }
        this.addSqlPartDatum(this.whereDatum
                .setWhereType(WhereType.IN)
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
                    ExceptionUtils.comparisonRuleNullException(WhereSqlPartDatumBuilder.class, "in", this.whereDatum.getTableName(), this.whereDatum.getTableAlias(), this.whereDatum.getColumnName(), this.whereDatum.getColumnAlias());
                    break;
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
            }
        }
        this.addSqlPartDatum(this.whereDatum
                .setWhereType(WhereType.IN)
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
                    ExceptionUtils.comparisonRuleNullException(WhereSqlPartDatumBuilder.class, "notIn", this.whereDatum.getTableName(), this.whereDatum.getTableAlias(), this.whereDatum.getColumnName(), this.whereDatum.getColumnAlias());
                    break;
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
            }
        }
        this.addSqlPartDatum(this.whereDatum
                .setWhereType(WhereType.NOT_IN)
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
                    ExceptionUtils.comparisonRuleNullException(WhereSqlPartDatumBuilder.class, "notIn", this.whereDatum.getTableName(), this.whereDatum.getTableAlias(), this.whereDatum.getColumnName(), this.whereDatum.getColumnAlias());
                    break;
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
            }
        }
        this.addSqlPartDatum(this.whereDatum
                .setWhereType(WhereType.NOT_IN)
                .setValueCount(values.size())
                .setTargetValue(values));
        return this.getHelper();
    }

    @Override
    public T equalTo(WhereSqlPartDatumBuilder whereSqlPartDatumBuilder) {
        this.addSqlPartDatum(this.whereDatum
                .setWhereType(WhereType.EQUAL)
                .setType(WhereDatum.Type.JOIN_WHERE)
                .setTargetWhereData(Collections.singletonList(whereSqlPartDatumBuilder.whereDatum)));
        return this.getHelper();
    }

    @Override
    public T notEqualTo(WhereSqlPartDatumBuilder whereSqlPartDatumBuilder) {
        this.addSqlPartDatum(this.whereDatum
                .setWhereType(WhereType.NOT_EQUAL)
                .setType(WhereDatum.Type.JOIN_WHERE)
                .setTargetWhereData(Collections.singletonList(whereSqlPartDatumBuilder.whereDatum)));
        return this.getHelper();
    }

    @Override
    public T greaterThan(WhereSqlPartDatumBuilder whereSqlPartDatumBuilder) {
        this.addSqlPartDatum(this.whereDatum
                .setWhereType(WhereType.GREATER)
                .setType(WhereDatum.Type.JOIN_WHERE)
                .setTargetWhereData(Collections.singletonList(whereSqlPartDatumBuilder.whereDatum)));
        return this.getHelper();
    }

    @Override
    public T greaterThanAndEqualTo(WhereSqlPartDatumBuilder whereSqlPartDatumBuilder) {
        this.addSqlPartDatum(this.whereDatum
                .setWhereType(WhereType.GREATER_EQUAL)
                .setType(WhereDatum.Type.JOIN_WHERE)
                .setTargetWhereData(Collections.singletonList(whereSqlPartDatumBuilder.whereDatum)));
        return this.getHelper();
    }

    @Override
    public T lessThan(WhereSqlPartDatumBuilder whereSqlPartDatumBuilder) {
        this.addSqlPartDatum(this.whereDatum
                .setWhereType(WhereType.LESS)
                .setType(WhereDatum.Type.JOIN_WHERE)
                .setTargetWhereData(Collections.singletonList(whereSqlPartDatumBuilder.whereDatum)));
        return this.getHelper();
    }

    @Override
    public T lessThanAndEqualTo(WhereSqlPartDatumBuilder whereSqlPartDatumBuilder) {
        this.addSqlPartDatum(this.whereDatum
                .setWhereType(WhereType.LESS_EQUAL)
                .setType(WhereDatum.Type.JOIN_WHERE)
                .setTargetWhereData(Collections.singletonList(whereSqlPartDatumBuilder.whereDatum)));
        return this.getHelper();
    }

    @Override
    public T between(WhereSqlPartDatumBuilder whereSqlPartDatumBuilder, WhereSqlPartDatumBuilder secondWhereSqlPartDatumBuilder) {
        this.addSqlPartDatum(this.whereDatum
                .setWhereType(WhereType.BETWEEN)
                .setType(WhereDatum.Type.JOIN_WHERE)
                .setTargetWhereData(new ArrayList<WhereDatum>(2) {{
                    add(whereSqlPartDatumBuilder.whereDatum);
                    add(secondWhereSqlPartDatumBuilder.whereDatum);
                }}));
        return this.getHelper();
    }

    @Override
    public T like(WhereSqlPartDatumBuilder whereSqlPartDatumBuilder) {
        this.addSqlPartDatum(this.whereDatum
                .setWhereType(WhereType.LIKE)
                .setType(WhereDatum.Type.JOIN_WHERE)
                .setTargetWhereData(Collections.singletonList(whereSqlPartDatumBuilder.whereDatum)));
        return this.getHelper();
    }

    @Override
    public T in(WhereSqlPartDatumBuilder... whereSqlPartDatumBuilders) {
        this.addSqlPartDatum(this.whereDatum
                .setWhereType(WhereType.IN)
                .setType(WhereDatum.Type.JOIN_WHERE)
                .setTargetWhereData(Arrays.stream(whereSqlPartDatumBuilders).map(whereSqlPartDatumBuilder -> whereSqlPartDatumBuilder.whereDatum).collect(Collectors.toList())));
        return this.getHelper();
    }

    @Override
    public T notIn(WhereSqlPartDatumBuilder... whereSqlPartDatumBuilders) {
        this.addSqlPartDatum(this.whereDatum
                .setWhereType(WhereType.NOT_IN)
                .setType(WhereDatum.Type.JOIN_WHERE)
                .setTargetWhereData(Arrays.stream(whereSqlPartDatumBuilders).map(whereSqlPartDatumBuilder -> whereSqlPartDatumBuilder.whereDatum).collect(Collectors.toList())));
        return this.getHelper();
    }

    @Override
    public T equalTo(ColumnHelper<?> columnHelper) {
        List<ColumnDatum> columnData = columnHelper.takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return this.getHelper();
        }
        this.addSqlPartDatum(this.whereDatum
                .setWhereType(WhereType.EQUAL)
                .setType(WhereDatum.Type.JOIN_COLUMN)
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
            SS extends SortHelper<SS>> T equalTo(Class<S> tableHelperClass, String tableAlias, WhereColumnCallback<SC> callback) {
        S s = HelperManager.defaultTableHelper(tableHelperClass);
        SC sc = s.newColumnHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        sc = callback.apply(sc);
        return equalTo(sc);
    }

    @Override
    public T notEqualTo(ColumnHelper<?> columnHelper) {
        List<ColumnDatum> columnData = columnHelper.takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return this.getHelper();
        }
        this.addSqlPartDatum(this.whereDatum
                .setWhereType(WhereType.NOT_EQUAL)
                .setType(WhereDatum.Type.JOIN_COLUMN)
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
            SS extends SortHelper<SS>> T notEqualTo(Class<S> tableHelperClass, String tableAlias, WhereColumnCallback<SC> callback) {
        S s = HelperManager.defaultTableHelper(tableHelperClass);
        SC sc = s.newColumnHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        sc = callback.apply(sc);
        return notEqualTo(sc);
    }

    @Override
    public T greaterThan(ColumnHelper<?> columnHelper) {
        List<ColumnDatum> columnData = columnHelper.takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return this.getHelper();
        }
        this.addSqlPartDatum(this.whereDatum
                .setWhereType(WhereType.GREATER)
                .setType(WhereDatum.Type.JOIN_COLUMN)
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
            SS extends SortHelper<SS>> T greaterThan(Class<S> tableHelperClass, String tableAlias, WhereColumnCallback<SC> callback) {
        S s = HelperManager.defaultTableHelper(tableHelperClass);
        SC sc = s.newColumnHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        sc = callback.apply(sc);
        return greaterThan(sc);
    }

    @Override
    public T greaterThanAndEqualTo(ColumnHelper<?> columnHelper) {
        List<ColumnDatum> columnData = columnHelper.takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return this.getHelper();
        }
        this.addSqlPartDatum(this.whereDatum
                .setWhereType(WhereType.GREATER_EQUAL)
                .setType(WhereDatum.Type.JOIN_COLUMN)
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
            SS extends SortHelper<SS>> T greaterThanAndEqualTo(Class<S> tableHelperClass, String tableAlias, WhereColumnCallback<SC> callback) {
        S s = HelperManager.defaultTableHelper(tableHelperClass);
        SC sc = s.newColumnHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        sc = callback.apply(sc);
        return greaterThanAndEqualTo(sc);
    }

    @Override
    public T lessThan(ColumnHelper<?> columnHelper) {
        List<ColumnDatum> columnData = columnHelper.takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return this.getHelper();
        }
        this.addSqlPartDatum(this.whereDatum
                .setWhereType(WhereType.LESS)
                .setType(WhereDatum.Type.JOIN_COLUMN)
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
            SS extends SortHelper<SS>> T lessThan(Class<S> tableHelperClass, String tableAlias, WhereColumnCallback<SC> callback) {
        S s = HelperManager.defaultTableHelper(tableHelperClass);
        SC sc = s.newColumnHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        sc = callback.apply(sc);
        return lessThan(sc);
    }

    @Override
    public T lessThanAndEqualTo(ColumnHelper<?> columnHelper) {
        List<ColumnDatum> columnData = columnHelper.takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return this.getHelper();
        }
        this.addSqlPartDatum(this.whereDatum
                .setWhereType(WhereType.LESS_EQUAL)
                .setType(WhereDatum.Type.JOIN_COLUMN)
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
            SS extends SortHelper<SS>> T lessThanAndEqualTo(Class<S> tableHelperClass, String tableAlias, WhereColumnCallback<SC> callback) {
        S s = HelperManager.defaultTableHelper(tableHelperClass);
        SC sc = s.newColumnHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        sc = callback.apply(sc);
        return lessThanAndEqualTo(sc);
    }

    @Override
    public T between(ColumnHelper<?> columnHelper) {
        List<ColumnDatum> columnData = columnHelper.takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return this.getHelper();
        }
        if (columnData.size() != 2) {
            throw new RuntimeException("ColumnData size must be 2 in between.");
        }
        this.addSqlPartDatum(this.whereDatum
                .setWhereType(WhereType.BETWEEN)
                .setType(WhereDatum.Type.JOIN_COLUMN)
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
            SS extends SortHelper<SS>> T between(Class<S> tableHelperClass, String tableAlias, WhereColumnCallback<SC> callback) {
        S s = HelperManager.defaultTableHelper(tableHelperClass);
        SC sc = s.newColumnHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        sc = callback.apply(sc);
        return between(sc);
    }

    @Override
    public T like(ColumnHelper<?> columnHelper) {
        List<ColumnDatum> columnData = columnHelper.takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return this.getHelper();
        }
        this.addSqlPartDatum(this.whereDatum
                .setWhereType(WhereType.LIKE)
                .setType(WhereDatum.Type.JOIN_COLUMN)
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
            SS extends SortHelper<SS>> T like(Class<S> tableHelperClass, String tableAlias, WhereColumnCallback<SC> callback) {
        S s = HelperManager.defaultTableHelper(tableHelperClass);
        SC sc = s.newColumnHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        sc = callback.apply(sc);
        return like(sc);
    }

    @Override
    public T in(ColumnHelper<?> columnHelper) {
        List<ColumnDatum> columnData = columnHelper.takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return this.getHelper();
        }
        this.addSqlPartDatum(this.whereDatum
                .setWhereType(WhereType.IN)
                .setType(WhereDatum.Type.JOIN_COLUMN)
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
            SS extends SortHelper<SS>> T in(Class<S> tableHelperClass, String tableAlias, WhereColumnCallback<SC> callback) {
        S s = HelperManager.defaultTableHelper(tableHelperClass);
        SC sc = s.newColumnHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        sc = callback.apply(sc);
        return in(sc);
    }

    @Override
    public T notIn(ColumnHelper<?> columnHelper) {
        List<ColumnDatum> columnData = columnHelper.takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return this.getHelper();
        }
        this.addSqlPartDatum(this.whereDatum
                .setWhereType(WhereType.NOT_IN)
                .setType(WhereDatum.Type.JOIN_COLUMN)
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
            SS extends SortHelper<SS>> T notIn(Class<S> tableHelperClass, String tableAlias, WhereColumnCallback<SC> callback) {
        S s = HelperManager.defaultTableHelper(tableHelperClass);
        SC sc = s.newColumnHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        sc = callback.apply(sc);
        return notIn(sc);
    }

    @Override
    public T equalToSubQuery(SubQueryCallback subQueryCallback) {
/*        SqlBuilder sqlBuilder = SubQueryCallback.execute(this.getModel().getSqlData(), tableName, modelClass, alias, callback);
        this.whereDatum.setWhereType(WhereType.EQUAL);
        this.whereDatum.setWhereValueType(WhereValueType.SUB_QUERY);
        this.whereDatum.setTargetSubQuery(sqlBuilder);
        this.addSqlModelDatum(this.whereDatum);*/
        return this.getHelper();
    }

    @Override
    public T notEqualToSubQuery(SubQueryCallback subQueryCallback) {
        return null;
    }

    @Override
    public T greaterThanSubQuery(SubQueryCallback subQueryCallback) {
        return null;
    }

    @Override
    public T greaterThanAndEqualToSubQuery(SubQueryCallback subQueryCallback) {
        return null;
    }

    @Override
    public T lessThanSubQuery(SubQueryCallback subQueryCallback) {
        return null;
    }

    @Override
    public T lessThanAndEqualToSubQuery(SubQueryCallback subQueryCallback) {
        return null;
    }

    @Override
    public T likeSubQuery(SubQueryCallback subQueryCallback) {
        return null;
    }

    @Override
    public T inSubQuery(SubQueryCallback subQueryCallback) {
        return null;
    }

    @Override
    public T notInSubQuery(SubQueryCallback subQueryCallback) {
        return null;
    }

   /* @Override

    @Override
    public <T extends Model<T>> T equalToSubQuery(String tableName, Class<T> modelClass, String alias, SubQuery<T, MC, MO, T, MS, MG, T, TC, TJ, TW, TS, TG> subQuery) {
        SqlBuilder sqlBuilder = SubQuery.execute(this.getModel().getSqlData(), tableName, modelClass, alias, subQuery);
        this.whereDatum.setWhereType(WhereType.EQUAL);
        this.whereDatum.setWhereValueType(WhereValueType.SUB_QUERY);
        this.whereDatum.setTargetSubQuery(sqlBuilder);
        this.addSqlModelDatum(this.whereDatum);
        return this.getSqlModel();
    }

    @Override
    public <T extends Model<T>, TC extends ColumnModel<T>, TJ extends OnModel<T>, TW extends WhereModel<T>, TS extends SortModel<T>, TG extends GroupModel<T, TC, TJ, TW, TS, TG>> T notEqualToSubQuery(String tableName, Class<T> modelClass, String alias, SubQuery<T, MC, MO, T, MS, MG, T, TC, TJ, TW, TS, TG> subQuery) {
        SqlBuilder sqlBuilder = SubQuery.execute(this.getModel().getSqlData(), tableName, modelClass, alias, subQuery);
        this.whereDatum.setWhereType(WhereType.NOT_EQUAL);
        this.whereDatum.setWhereValueType(WhereValueType.SUB_QUERY);
        this.whereDatum.setTargetSubQuery(sqlBuilder);
        this.addSqlModelDatum(this.whereDatum);
        return this.getSqlModel();
    }

    @Override
    public <T extends Model<T>, TC extends ColumnModel<T>, TJ extends OnModel<T>, TW extends WhereModel<T>, TS extends SortModel<T>, TG extends GroupModel<T, TC, TJ, TW, TS, TG>> T greaterThanSubQuery(String tableName, Class<T> modelClass, String alias, SubQuery<T, MC, MO, T, MS, MG, T, TC, TJ, TW, TS, TG> subQuery) {
        SqlBuilder sqlBuilder = SubQuery.execute(this.getModel().getSqlData(), tableName, modelClass, alias, subQuery);
        this.whereDatum.setWhereType(WhereType.GREATER);
        this.whereDatum.setWhereValueType(WhereValueType.SUB_QUERY);
        this.whereDatum.setTargetSubQuery(sqlBuilder);
        this.addSqlModelDatum(this.whereDatum);
        return this.getSqlModel();
    }

    @Override
    public <T extends Model<T>, TC extends ColumnModel<T>, TJ extends OnModel<T>, TW extends WhereModel<T>, TS extends SortModel<T>, TG extends GroupModel<T, TC, TJ, TW, TS, TG>> T greaterThanAndEqualToSubQuery(String tableName, Class<T> modelClass, String alias, SubQuery<T, MC, MO, T, MS, MG, T, TC, TJ, TW, TS, TG> subQuery) {
        SqlBuilder sqlBuilder = SubQuery.execute(this.getModel().getSqlData(), tableName, modelClass, alias, subQuery);
        this.whereDatum.setWhereType(WhereType.GREATER_EQUAL);
        this.whereDatum.setWhereValueType(WhereValueType.SUB_QUERY);
        this.whereDatum.setTargetSubQuery(sqlBuilder);
        this.addSqlModelDatum(this.whereDatum);
        return this.getSqlModel();
    }

    @Override
    public <T extends Model<T>, TC extends ColumnModel<T>, TJ extends OnModel<T>, TW extends WhereModel<T>, TS extends SortModel<T>, TG extends GroupModel<T, TC, TJ, TW, TS, TG>> T lessThanSubQuery(String tableName, Class<T> modelClass, String alias, SubQuery<T, MC, MO, T, MS, MG, T, TC, TJ, TW, TS, TG> subQuery) {
        SqlBuilder sqlBuilder = SubQuery.execute(this.getModel().getSqlData(), tableName, modelClass, alias, subQuery);
        this.whereDatum.setWhereType(WhereType.LESS);
        this.whereDatum.setWhereValueType(WhereValueType.SUB_QUERY);
        this.whereDatum.setTargetSubQuery(sqlBuilder);
        this.addSqlModelDatum(this.whereDatum);
        return this.getSqlModel();
    }

    @Override
    public <T extends Model<T>, TC extends ColumnModel<T>, TJ extends OnModel<T>, TW extends WhereModel<T>, TS extends SortModel<T>, TG extends GroupModel<T, TC, TJ, TW, TS, TG>> T lessThanAndEqualToSubQuery(String tableName, Class<T> modelClass, String alias, SubQuery<T, MC, MO, T, MS, MG, T, TC, TJ, TW, TS, TG> subQuery) {
        SqlBuilder sqlBuilder = SubQuery.execute(this.getModel().getSqlData(), tableName, modelClass, alias, subQuery);
        this.whereDatum.setWhereType(WhereType.LESS_EQUAL);
        this.whereDatum.setWhereValueType(WhereValueType.SUB_QUERY);
        this.whereDatum.setTargetSubQuery(sqlBuilder);
        this.addSqlModelDatum(this.whereDatum);
        return this.getSqlModel();
    }

    @Override
    public <T extends Model<T>, TC extends ColumnModel<T>, TJ extends OnModel<T>, TW extends WhereModel<T>, TS extends SortModel<T>, TG extends GroupModel<T, TC, TJ, TW, TS, TG>> T likeSubQuery(String tableName, Class<T> modelClass, String alias, SubQuery<T, MC, MO, T, MS, MG, T, TC, TJ, TW, TS, TG> subQuery) {
        SqlBuilder sqlBuilder = SubQuery.execute(this.getModel().getSqlData(), tableName, modelClass, alias, subQuery);
        this.whereDatum.setWhereType(WhereType.LIKE);
        this.whereDatum.setWhereValueType(WhereValueType.SUB_QUERY);
        this.whereDatum.setTargetSubQuery(sqlBuilder);
        this.addSqlModelDatum(this.whereDatum);
        return this.getSqlModel();
    }

    @Override
    public <T extends Model<T>, TC extends ColumnModel<T>, TJ extends OnModel<T>, TW extends WhereModel<T>, TS extends SortModel<T>, TG extends GroupModel<T, TC, TJ, TW, TS, TG>> T inSubQuery(String tableName, Class<T> modelClass, String alias, SubQuery<T, MC, MO, T, MS, MG, T, TC, TJ, TW, TS, TG> subQuery) {
        SqlBuilder sqlBuilder = SubQuery.execute(this.getModel().getSqlData(), tableName, modelClass, alias, subQuery);
        this.whereDatum.setWhereType(WhereType.IN);
        this.whereDatum.setWhereValueType(WhereValueType.SUB_QUERY);
        this.whereDatum.setTargetSubQuery(sqlBuilder);
        this.addSqlModelDatum(this.whereDatum);
        return this.getSqlModel();
    }

    @Override
    public <T extends Model<T>, TC extends ColumnModel<T>, TJ extends OnModel<T>, TW extends WhereModel<T>, TS extends SortModel<T>, TG extends GroupModel<T, TC, TJ, TW, TS, TG>> T notInSubQuery(String tableName, Class<T> modelClass, String alias, SubQuery<T, MC, MO, T, MS, MG, T, TC, TJ, TW, TS, TG> subQuery) {
        SqlBuilder sqlBuilder = SubQuery.execute(this.getModel().getSqlData(), tableName, modelClass, alias, subQuery);
        this.whereDatum.setWhereType(WhereType.NOT_IN);
        this.whereDatum.setWhereValueType(WhereValueType.SUB_QUERY);
        this.whereDatum.setTargetSubQuery(sqlBuilder);
        this.addSqlModelDatum(this.whereDatum);
        return this.getSqlModel();
    }*/

}