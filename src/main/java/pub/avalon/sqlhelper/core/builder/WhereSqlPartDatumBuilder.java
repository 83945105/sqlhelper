package pub.avalon.sqlhelper.core.builder;

import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.callback.SubQueryCallback;
import pub.avalon.sqlhelper.core.callback.WhereColumnCallback;
import pub.avalon.sqlhelper.core.comparison.WhereComparisonOperator;
import pub.avalon.sqlhelper.core.data.ColumnDatum;
import pub.avalon.sqlhelper.core.data.WhereDatum;
import pub.avalon.sqlhelper.core.exception.ComparisonException;
import pub.avalon.sqlhelper.core.helper.*;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;
import pub.avalon.sqlhelper.core.utils.ExceptionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 条件Sql片段数据构建器
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class WhereSqlPartDatumBuilder<T extends Helper> extends AbstractSqlPartDatumBuilder<T, WhereDatum> implements WhereComparisonOperator<T> {

    private WhereDatum whereDatum;

    private SqlBuilderOptions sqlBuilderOptions = SqlBuilderOptions.DEFAULT_SQL_BUILDER_OPTIONS;

    public WhereSqlPartDatumBuilder(String tableAlias, T helper) {
        super(tableAlias, helper);
    }

    @Override
    public void accept(String templateTableName, String templateTableAlias, String sqlPart) {

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
                    throw new ComparisonException("table alias [" + this.whereDatum.getColumnName() + "] column [" + this.whereDatum.getColumnName() + "] equalTo, the value can not be null.");
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
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
                    throw new ComparisonException("table alias [" + this.whereDatum.getColumnName() + "] column [" + this.whereDatum.getColumnName() + "] notEqualTo, the value can not be null.");
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
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
                    throw new ComparisonException("table alias [" + this.whereDatum.getColumnName() + "] column [" + this.whereDatum.getColumnName() + "] greaterThan, the value can not be null.");
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
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
                    throw new ComparisonException("table alias [" + this.whereDatum.getColumnName() + "] column [" + this.whereDatum.getColumnName() + "] greaterThanAndEqualTo, the value can not be null.");
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
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
                    throw new ComparisonException("table alias [" + this.whereDatum.getColumnName() + "] column [" + this.whereDatum.getColumnName() + "] lessThan, the value can not be null.");
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
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
                    throw new ComparisonException("table alias [" + this.whereDatum.getColumnName() + "] column [" + this.whereDatum.getColumnName() + "] lessThanAndEqualTo, the value can not be null.");
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
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
                    throw new ComparisonException("table alias [" + this.whereDatum.getColumnName() + "] column [" + this.whereDatum.getColumnName() + "] between, the value can not be null.");
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
            }
        }
        if (secondValue == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    throw new ComparisonException("table alias [" + this.whereDatum.getColumnName() + "] column [" + this.whereDatum.getColumnName() + "] between, the secondValue can not be null.");
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
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
                    throw new ComparisonException("table alias [" + this.whereDatum.getColumnName() + "] column [" + this.whereDatum.getColumnName() + "] like, the value can not be null.");
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
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
                    throw new ComparisonException("table alias [" + this.whereDatum.getColumnName() + "] column [" + this.whereDatum.getColumnName() + "] in, the values can not be null or size = 0.");
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
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
                    throw new ComparisonException("table alias [" + this.whereDatum.getColumnName() + "] column [" + this.whereDatum.getColumnName() + "] in, the values can not be null or size = 0.");
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
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
                    throw new ComparisonException("table alias [" + this.whereDatum.getColumnName() + "] column [" + this.whereDatum.getColumnName() + "] in, the values can not be null or size = 0.");
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
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
                    throw new ComparisonException("table alias [" + this.whereDatum.getColumnName() + "] column [" + this.whereDatum.getColumnName() + "] in, the values can not be null or size = 0.");
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
            }
        }
        this.whereDatum.setWhereType(WhereType.NOT_IN);
        this.whereDatum.setValueCount(values.size());
        this.whereDatum.setTargetValue(values);
        this.addSqlPartDatum(this.whereDatum);
        return this.getHelper();
    }

    @Override
    public T equalTo(WhereSqlPartDatumBuilder whereSqlPartDatumBuilder) {
        this.whereDatum.setWhereType(WhereType.EQUAL);
        this.whereDatum.setWhereValueType(WhereValueType.JOIN_WHERE);
        this.whereDatum.setTargetWhereData(Collections.singletonList(whereSqlPartDatumBuilder.whereDatum));
        this.addSqlPartDatum(this.whereDatum);
        return this.getHelper();
    }

    @Override
    public T notEqualTo(WhereSqlPartDatumBuilder whereSqlPartDatumBuilder) {
        this.whereDatum.setWhereType(WhereType.NOT_EQUAL);
        this.whereDatum.setWhereValueType(WhereValueType.JOIN_WHERE);
        this.whereDatum.setTargetWhereData(Collections.singletonList(whereSqlPartDatumBuilder.whereDatum));
        ;
        this.addSqlPartDatum(this.whereDatum);
        return this.getHelper();
    }

    @Override
    public T greaterThan(WhereSqlPartDatumBuilder whereSqlPartDatumBuilder) {
        this.whereDatum.setWhereType(WhereType.GREATER);
        this.whereDatum.setWhereValueType(WhereValueType.JOIN_WHERE);
        this.whereDatum.setTargetWhereData(Collections.singletonList(whereSqlPartDatumBuilder.whereDatum));
        this.addSqlPartDatum(this.whereDatum);
        return this.getHelper();
    }

    @Override
    public T greaterThanAndEqualTo(WhereSqlPartDatumBuilder whereSqlPartDatumBuilder) {
        this.whereDatum.setWhereType(WhereType.GREATER_EQUAL);
        this.whereDatum.setWhereValueType(WhereValueType.JOIN_WHERE);
        this.whereDatum.setTargetWhereData(Collections.singletonList(whereSqlPartDatumBuilder.whereDatum));
        this.addSqlPartDatum(this.whereDatum);
        return this.getHelper();
    }

    @Override
    public T lessThan(WhereSqlPartDatumBuilder whereSqlPartDatumBuilder) {
        this.whereDatum.setWhereType(WhereType.LESS);
        this.whereDatum.setWhereValueType(WhereValueType.JOIN_WHERE);
        this.whereDatum.setTargetWhereData(Collections.singletonList(whereSqlPartDatumBuilder.whereDatum));
        this.addSqlPartDatum(this.whereDatum);
        return this.getHelper();
    }

    @Override
    public T lessThanAndEqualTo(WhereSqlPartDatumBuilder whereSqlPartDatumBuilder) {
        this.whereDatum.setWhereType(WhereType.LESS_EQUAL);
        this.whereDatum.setWhereValueType(WhereValueType.JOIN_WHERE);
        this.whereDatum.setTargetWhereData(Collections.singletonList(whereSqlPartDatumBuilder.whereDatum));
        this.addSqlPartDatum(this.whereDatum);
        return this.getHelper();
    }

    @Override
    public T between(WhereSqlPartDatumBuilder whereSqlPartDatumBuilder, WhereSqlPartDatumBuilder secondWhereSqlPartDatumBuilder) {
        this.whereDatum.setWhereType(WhereType.BETWEEN);
        this.whereDatum.setWhereValueType(WhereValueType.JOIN_WHERE);
        this.whereDatum.setTargetWhereData(new ArrayList<WhereDatum>(2) {{
            add(whereSqlPartDatumBuilder.whereDatum);
            add(secondWhereSqlPartDatumBuilder.whereDatum);
        }});
        this.addSqlPartDatum(this.whereDatum);
        return this.getHelper();
    }

    @Override
    public T like(WhereSqlPartDatumBuilder whereSqlPartDatumBuilder) {
        this.whereDatum.setWhereType(WhereType.LIKE);
        this.whereDatum.setWhereValueType(WhereValueType.JOIN_WHERE);
        this.whereDatum.setTargetWhereData(Collections.singletonList(whereSqlPartDatumBuilder.whereDatum));
        this.addSqlPartDatum(this.whereDatum);
        return this.getHelper();
    }

    @Override
    public T in(WhereSqlPartDatumBuilder... whereSqlPartDatumBuilders) {
        this.whereDatum.setWhereType(WhereType.IN);
        this.whereDatum.setWhereValueType(WhereValueType.JOIN_WHERE);
        this.whereDatum.setTargetWhereData(Arrays.stream(whereSqlPartDatumBuilders).map(b -> b.whereDatum).collect(Collectors.toList()));
        this.addSqlPartDatum(this.whereDatum);
        return this.getHelper();
    }

    @Override
    public T notIn(WhereSqlPartDatumBuilder... whereSqlPartDatumBuilders) {
        this.whereDatum.setWhereType(WhereType.NOT_IN);
        this.whereDatum.setWhereValueType(WhereValueType.JOIN_WHERE);
        this.whereDatum.setTargetWhereData(Arrays.stream(whereSqlPartDatumBuilders).map(b -> b.whereDatum).collect(Collectors.toList()));
        this.addSqlPartDatum(this.whereDatum);
        return this.getHelper();
    }

    @Override
    public T equalTo(ColumnHelper<?> columnHelper) {
        List<ColumnDatum> columnData = columnHelper.takeoutSqlPartData();
        if (columnData == null || columnData.size() == 0) {
            return this.getHelper();
        }
        this.whereDatum.setWhereType(WhereType.EQUAL);
        this.whereDatum.setWhereValueType(WhereValueType.JOIN_COLUMN);
        this.whereDatum.setTargetColumnData(columnData);
        this.addSqlPartDatum(this.whereDatum);
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
        S s = BeanUtils.tableHelper(tableHelperClass);
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
        this.whereDatum.setWhereType(WhereType.NOT_EQUAL);
        this.whereDatum.setWhereValueType(WhereValueType.JOIN_COLUMN);
        this.whereDatum.setTargetColumnData(columnData);
        this.addSqlPartDatum(this.whereDatum);
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
        S s = BeanUtils.tableHelper(tableHelperClass);
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
        this.whereDatum.setWhereType(WhereType.GREATER);
        this.whereDatum.setWhereValueType(WhereValueType.JOIN_COLUMN);
        this.whereDatum.setTargetColumnData(columnData);
        this.addSqlPartDatum(this.whereDatum);
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
        S s = BeanUtils.tableHelper(tableHelperClass);
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
        this.whereDatum.setWhereType(WhereType.GREATER_EQUAL);
        this.whereDatum.setWhereValueType(WhereValueType.JOIN_COLUMN);
        this.whereDatum.setTargetColumnData(columnData);
        this.addSqlPartDatum(this.whereDatum);
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
        S s = BeanUtils.tableHelper(tableHelperClass);
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
        this.whereDatum.setWhereType(WhereType.LESS);
        this.whereDatum.setWhereValueType(WhereValueType.JOIN_COLUMN);
        this.whereDatum.setTargetColumnData(columnData);
        this.addSqlPartDatum(this.whereDatum);
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
        S s = BeanUtils.tableHelper(tableHelperClass);
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
        this.whereDatum.setWhereType(WhereType.LESS_EQUAL);
        this.whereDatum.setWhereValueType(WhereValueType.JOIN_COLUMN);
        this.whereDatum.setTargetColumnData(columnData);
        this.addSqlPartDatum(this.whereDatum);
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
        S s = BeanUtils.tableHelper(tableHelperClass);
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
        this.whereDatum.setWhereType(WhereType.BETWEEN);
        this.whereDatum.setWhereValueType(WhereValueType.JOIN_COLUMN);
        this.whereDatum.setTargetColumnData(columnData);
        this.addSqlPartDatum(this.whereDatum);
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
        S s = BeanUtils.tableHelper(tableHelperClass);
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
        this.whereDatum.setWhereType(WhereType.LIKE);
        this.whereDatum.setWhereValueType(WhereValueType.JOIN_COLUMN);
        this.whereDatum.setTargetColumnData(columnData);
        this.addSqlPartDatum(this.whereDatum);
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
        S s = BeanUtils.tableHelper(tableHelperClass);
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
        this.whereDatum.setWhereType(WhereType.IN);
        this.whereDatum.setWhereValueType(WhereValueType.JOIN_COLUMN);
        this.whereDatum.setTargetColumnData(columnData);
        this.addSqlPartDatum(this.whereDatum);
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
        S s = BeanUtils.tableHelper(tableHelperClass);
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
        this.whereDatum.setWhereType(WhereType.NOT_IN);
        this.whereDatum.setWhereValueType(WhereValueType.JOIN_COLUMN);
        this.whereDatum.setTargetColumnData(columnData);
        this.addSqlPartDatum(this.whereDatum);
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
        S s = BeanUtils.tableHelper(tableHelperClass);
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
