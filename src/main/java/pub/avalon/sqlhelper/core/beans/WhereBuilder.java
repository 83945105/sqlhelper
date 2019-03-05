package pub.avalon.sqlhelper.core.beans;

import pub.avalon.sqlhelper.core.build.SqlBuilder;
import pub.avalon.sqlhelper.core.data.AbstractTableData;
import pub.avalon.sqlhelper.core.data.JoinTableData;
import pub.avalon.sqlhelper.core.data.WhereData;
import pub.avalon.sqlhelper.core.exception.ComparisonException;
import pub.avalon.sqlhelper.core.norm.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 条件构建器
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class WhereBuilder<M extends Model<M, MC, MO, MW, MS, MG>,
        MC extends ColumnModel<M, MC, MO, MW, MS, MG>,
        MO extends OnModel<M, MC, MO, MW, MS, MG>,
        MW extends WhereModel<M, MC, MO, MW, MS, MG>,
        MS extends SortModel<M, MC, MO, MW, MS, MG>,
        MG extends GroupModel<M, MC, MO, MW, MS, MG>> implements ComparisonOperator<MW>, WhereComparisonOperator<M, MC, MO, MW, MS, MG>, WhereComparisonOperatorSubQuery<M, MC, MO, MW, MS, MG> {

    private MW handleModel;

    public WhereBuilder(MW handleModel) {
        this.handleModel = handleModel;
    }

    private WhereData whereData;

    private List<WhereData> whereDataList = new ArrayList<>();

    /**
     * 获取并重置where条件数据集合
     * 每次获取必须重置,防止条件重复
     *
     * @return where条件集合
     */
    List<WhereData> getAndResetWhereDataList() {
        List<WhereData> whereDataList = this.whereDataList;
        this.whereDataList = new ArrayList<>();
        return whereDataList;
    }

    private AbstractTableData ownerTableData;

    public void setOwnerTableData(AbstractTableData<M> ownerTableData) {
        this.ownerTableData = ownerTableData;
    }

    public WhereBuilder<M, MC, MO, MW, MS, MG> handler(String ownerTableName, String ownerAlias, String ownerColumnName) {
        this.whereData = new WhereData();
        this.whereData.setOwnerTableName(ownerTableName);
        this.whereData.setOwnerTableAlias(ownerAlias);
        this.whereData.setOwnerColumnName(ownerColumnName);
        if (this.ownerTableData == null) {
            return this;
        }
        this.whereData.setOwnerTableAlias(this.ownerTableData.getTableAlias());
        return this;
    }

    @Override
    public MW isNull() {
        this.whereData.setWhereType(WhereType.IS_NULL);
        this.whereData.setValueCount(0);
        this.whereDataList.add(this.whereData);
        return this.handleModel;
    }

    @Override
    public MW isNotNull() {
        this.whereData.setWhereType(WhereType.IS_NOT_NULL);
        this.whereData.setValueCount(0);
        this.whereDataList.add(this.whereData);
        return this.handleModel;
    }

    @Override
    public MW equalToValue(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.handleModel;
                case NOT_NULL:
                    throw new ComparisonException("table alias [" + this.whereData.getOwnerTableAlias() + "] column [" + this.whereData.getOwnerColumnName() + "] equalTo, the value can not be null.");
                default:
                    return null;
            }
        }
        this.whereData.setWhereType(WhereType.EQUAL);
        this.whereData.setValueCount(1);
        this.whereData.setTargetValue(value);
        this.whereDataList.add(this.whereData);
        return this.handleModel;
    }

    @Override
    public MW notEqualToValue(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.handleModel;
                case NOT_NULL:
                    throw new ComparisonException("table alias [" + this.whereData.getOwnerTableAlias() + "] column [" + this.whereData.getOwnerColumnName() + "] notEqualTo, the value can not be null.");
                default:
                    return null;
            }
        }
        this.whereData.setWhereType(WhereType.NOT_EQUAL);
        this.whereData.setValueCount(1);
        this.whereData.setTargetValue(value);
        this.whereDataList.add(this.whereData);
        return this.handleModel;
    }

    @Override
    public MW greaterThanValue(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.handleModel;
                case NOT_NULL:
                    throw new ComparisonException("table alias [" + this.whereData.getOwnerTableAlias() + "] column [" + this.whereData.getOwnerColumnName() + "] greaterThan, the value can not be null.");
                default:
                    return null;
            }
        }
        this.whereData.setWhereType(WhereType.GREATER);
        this.whereData.setValueCount(1);
        this.whereData.setTargetValue(value);
        this.whereDataList.add(this.whereData);
        return this.handleModel;
    }

    @Override
    public MW greaterThanAndEqualToValue(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.handleModel;
                case NOT_NULL:
                    throw new ComparisonException("table alias [" + this.whereData.getOwnerTableAlias() + "] column [" + this.whereData.getOwnerColumnName() + "] greaterThanAndEqualTo, the value can not be null.");
                default:
                    return null;
            }
        }
        this.whereData.setWhereType(WhereType.GREATER_EQUAL);
        this.whereData.setValueCount(1);
        this.whereData.setTargetValue(value);
        this.whereDataList.add(this.whereData);
        return this.handleModel;
    }

    @Override
    public MW lessThanValue(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.handleModel;
                case NOT_NULL:
                    throw new ComparisonException("table alias [" + this.whereData.getOwnerTableAlias() + "] column [" + this.whereData.getOwnerColumnName() + "] lessThan, the value can not be null.");
                default:
                    return null;
            }
        }
        this.whereData.setWhereType(WhereType.LESS);
        this.whereData.setValueCount(1);
        this.whereData.setTargetValue(value);
        this.whereDataList.add(this.whereData);
        return this.handleModel;
    }

    @Override
    public MW lessThanAndEqualToValue(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.handleModel;
                case NOT_NULL:
                    throw new ComparisonException("table alias [" + this.whereData.getOwnerTableAlias() + "] column [" + this.whereData.getOwnerColumnName() + "] lessThanAndEqualTo, the value can not be null.");
                default:
                    return null;
            }
        }
        this.whereData.setWhereType(WhereType.LESS_EQUAL);
        this.whereData.setValueCount(1);
        this.whereData.setTargetValue(value);
        this.whereDataList.add(this.whereData);
        return this.handleModel;
    }

    @Override
    public MW betweenValue(Object value, Object secondValue, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.handleModel;
                case NOT_NULL:
                    throw new ComparisonException("table alias [" + this.whereData.getOwnerTableAlias() + "] column [" + this.whereData.getOwnerColumnName() + "] between, the value can not be null.");
                default:
                    return null;
            }
        }
        if (secondValue == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.handleModel;
                case NOT_NULL:
                    throw new ComparisonException("table alias [" + this.whereData.getOwnerTableAlias() + "] column [" + this.whereData.getOwnerColumnName() + "] between, the secondValue can not be null.");
                default:
                    return null;
            }
        }
        this.whereData.setWhereType(WhereType.BETWEEN);
        this.whereData.setValueCount(2);
        this.whereData.setTargetValue(value);
        this.whereData.setTargetSecondValue(secondValue);
        this.whereDataList.add(this.whereData);
        return this.handleModel;
    }

    @Override
    public MW likeValue(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.handleModel;
                case NOT_NULL:
                    throw new ComparisonException("table alias [" + this.whereData.getOwnerTableAlias() + "] column [" + this.whereData.getOwnerColumnName() + "] like, the value can not be null.");
                default:
                    return null;
            }
        }
        this.whereData.setWhereType(WhereType.LIKE);
        this.whereData.setValueCount(1);
        this.whereData.setTargetValue(value);
        this.whereDataList.add(this.whereData);
        return this.handleModel;
    }

    @Override
    public MW inValue(Object[] values, ComparisonRule comparisonRule) {
        if (values == null || values.length == 0) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.handleModel;
                case NOT_NULL:
                    throw new ComparisonException("table alias [" + this.whereData.getOwnerTableAlias() + "] column [" + this.whereData.getOwnerColumnName() + "] in, the values can not be null or size = 0.");
                default:
                    return null;
            }
        }
        this.whereData.setWhereType(WhereType.IN);
        this.whereData.setValueCount(values.length);
        this.whereData.setTargetValue(values);
        this.whereDataList.add(this.whereData);
        return this.handleModel;
    }

    @Override
    public MW inValue(Collection<?> values, ComparisonRule comparisonRule) {
        if (values == null || values.size() == 0) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.handleModel;
                case NOT_NULL:
                    throw new ComparisonException("table alias [" + this.whereData.getOwnerTableAlias() + "] column [" + this.whereData.getOwnerColumnName() + "] in, the values can not be null or size = 0.");
                default:
                    return null;
            }
        }
        this.whereData.setWhereType(WhereType.IN);
        this.whereData.setValueCount(values.size());
        this.whereData.setTargetValue(values);
        this.whereDataList.add(this.whereData);
        return this.handleModel;
    }

    @Override
    public MW notInValue(Object[] values, ComparisonRule comparisonRule) {
        if (values == null || values.length == 0) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.handleModel;
                case NOT_NULL:
                    throw new ComparisonException("table alias [" + this.whereData.getOwnerTableAlias() + "] column [" + this.whereData.getOwnerColumnName() + "] in, the values can not be null or size = 0.");
                default:
                    return null;
            }
        }
        this.whereData.setWhereType(WhereType.NOT_IN);
        this.whereData.setValueCount(values.length);
        this.whereData.setTargetValue(values);
        this.whereDataList.add(this.whereData);
        return this.handleModel;
    }

    @Override
    public MW notInValue(Collection<?> values, ComparisonRule comparisonRule) {
        if (values == null || values.size() == 0) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.handleModel;
                case NOT_NULL:
                    throw new ComparisonException("table alias [" + this.whereData.getOwnerTableAlias() + "] column [" + this.whereData.getOwnerColumnName() + "] in, the values can not be null or size = 0.");
                default:
                    return null;
            }
        }
        this.whereData.setWhereType(WhereType.NOT_IN);
        this.whereData.setValueCount(values.size());
        this.whereData.setTargetValue(values);
        this.whereDataList.add(this.whereData);
        return this.handleModel;
    }

    @Override
    public MW equalTo(WhereBuilder whereBuilder) {
        this.whereData.setWhereType(WhereType.EQUAL);
        this.whereData.setWhereValueType(WhereValueType.JOIN);
        WhereData targetWhereData = whereBuilder.whereData;
        this.whereData.setTargetTableName(targetWhereData.getOwnerTableName());
        this.whereData.setTargetTableAlias(targetWhereData.getOwnerTableAlias());
        this.whereData.setTargetColumnName(targetWhereData.getOwnerColumnName());
        this.whereDataList.add(this.whereData);
        return this.handleModel;
    }

    @Override
    public MW notEqualTo(WhereBuilder whereBuilder) {
        this.whereData.setWhereType(WhereType.NOT_EQUAL);
        this.whereData.setWhereValueType(WhereValueType.JOIN);
        WhereData targetWhereData = whereBuilder.whereData;
        this.whereData.setTargetTableName(targetWhereData.getOwnerTableName());
        this.whereData.setTargetTableAlias(targetWhereData.getOwnerTableAlias());
        this.whereData.setTargetColumnName(targetWhereData.getOwnerColumnName());
        this.whereDataList.add(this.whereData);
        return this.handleModel;
    }

    @Override
    public MW greaterThan(WhereBuilder whereBuilder) {
        this.whereData.setWhereType(WhereType.GREATER);
        this.whereData.setWhereValueType(WhereValueType.JOIN);
        WhereData targetWhereData = whereBuilder.whereData;
        this.whereData.setTargetTableName(targetWhereData.getOwnerTableName());
        this.whereData.setTargetTableAlias(targetWhereData.getOwnerTableAlias());
        this.whereData.setTargetColumnName(targetWhereData.getOwnerColumnName());
        this.whereDataList.add(this.whereData);
        return this.handleModel;
    }

    @Override
    public MW greaterThanAndEqualTo(WhereBuilder whereBuilder) {
        this.whereData.setWhereType(WhereType.GREATER_EQUAL);
        this.whereData.setWhereValueType(WhereValueType.JOIN);
        WhereData targetWhereData = whereBuilder.whereData;
        this.whereData.setTargetTableName(targetWhereData.getOwnerTableName());
        this.whereData.setTargetTableAlias(targetWhereData.getOwnerTableAlias());
        this.whereData.setTargetColumnName(targetWhereData.getOwnerColumnName());
        this.whereDataList.add(this.whereData);
        return this.handleModel;
    }

    @Override
    public MW lessThan(WhereBuilder whereBuilder) {
        this.whereData.setWhereType(WhereType.LESS);
        this.whereData.setWhereValueType(WhereValueType.JOIN);
        WhereData targetWhereData = whereBuilder.whereData;
        this.whereData.setTargetTableName(targetWhereData.getOwnerTableName());
        this.whereData.setTargetTableAlias(targetWhereData.getOwnerTableAlias());
        this.whereData.setTargetColumnName(targetWhereData.getOwnerColumnName());
        this.whereDataList.add(this.whereData);
        return this.handleModel;
    }

    @Override
    public MW lessThanAndEqualTo(WhereBuilder whereBuilder) {
        this.whereData.setWhereType(WhereType.LESS_EQUAL);
        this.whereData.setWhereValueType(WhereValueType.JOIN);
        WhereData targetWhereData = whereBuilder.whereData;
        this.whereData.setTargetTableName(targetWhereData.getOwnerTableName());
        this.whereData.setTargetTableAlias(targetWhereData.getOwnerTableAlias());
        this.whereData.setTargetColumnName(targetWhereData.getOwnerColumnName());
        this.whereDataList.add(this.whereData);
        return this.handleModel;
    }

    @Override
    public <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW equalTo(Class<T> onClass, String alias, WhereModelValue<T, TC, TO, TW, TS, TG> whereModelValue) {
        this.whereData.setWhereType(WhereType.EQUAL);
        this.whereData.setWhereValueType(WhereValueType.JOIN);
        JoinTableData<T> joinTableData = this.handleModel.getSqlData().getJoinTableData(alias, onClass);
        TW whereModel = joinTableData.getTableModel().getWhereModel();
        WhereData targetWhereData = whereModelValue.apply(whereModel).whereData;
        this.whereData.setTargetTableName(targetWhereData.getOwnerTableName());
        this.whereData.setTargetTableAlias(joinTableData.getTableAlias());
        this.whereData.setTargetColumnName(targetWhereData.getOwnerColumnName());
        this.whereDataList.add(this.whereData);
        return this.handleModel;
    }

    @Override
    public <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW notEqualTo(Class<T> onClass, String alias, WhereModelValue<T, TC, TO, TW, TS, TG> whereModelValue) {
        this.whereData.setWhereType(WhereType.NOT_EQUAL);
        this.whereData.setWhereValueType(WhereValueType.JOIN);
        JoinTableData<T> joinTableData = this.handleModel.getSqlData().getJoinTableData(alias, onClass);
        TW whereModel = joinTableData.getTableModel().getWhereModel();
        WhereData targetWhereData = whereModelValue.apply(whereModel).whereData;
        this.whereData.setTargetTableName(targetWhereData.getOwnerTableName());
        this.whereData.setTargetTableAlias(joinTableData.getTableAlias());
        this.whereData.setTargetColumnName(targetWhereData.getOwnerColumnName());
        this.whereDataList.add(this.whereData);
        return this.handleModel;
    }

    @Override
    public <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW greaterThan(Class<T> onClass, String alias, WhereModelValue<T, TC, TO, TW, TS, TG> whereModelValue) {
        this.whereData.setWhereType(WhereType.GREATER);
        this.whereData.setWhereValueType(WhereValueType.JOIN);
        JoinTableData<T> joinTableData = this.handleModel.getSqlData().getJoinTableData(alias, onClass);
        TW whereModel = joinTableData.getTableModel().getWhereModel();
        WhereData targetWhereData = whereModelValue.apply(whereModel).whereData;
        this.whereData.setTargetTableName(targetWhereData.getOwnerTableName());
        this.whereData.setTargetTableAlias(joinTableData.getTableAlias());
        this.whereData.setTargetColumnName(targetWhereData.getOwnerColumnName());
        this.whereDataList.add(this.whereData);
        return this.handleModel;
    }

    @Override
    public <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW greaterThanAndEqualTo(Class<T> onClass, String alias, WhereModelValue<T, TC, TO, TW, TS, TG> whereModelValue) {
        this.whereData.setWhereType(WhereType.GREATER_EQUAL);
        this.whereData.setWhereValueType(WhereValueType.JOIN);
        JoinTableData<T> joinTableData = this.handleModel.getSqlData().getJoinTableData(alias, onClass);
        TW whereModel = joinTableData.getTableModel().getWhereModel();
        WhereData targetWhereData = whereModelValue.apply(whereModel).whereData;
        this.whereData.setTargetTableName(targetWhereData.getOwnerTableName());
        this.whereData.setTargetTableAlias(joinTableData.getTableAlias());
        this.whereData.setTargetColumnName(targetWhereData.getOwnerColumnName());
        this.whereDataList.add(this.whereData);
        return this.handleModel;
    }

    @Override
    public <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW lessThan(Class<T> onClass, String alias, WhereModelValue<T, TC, TO, TW, TS, TG> whereModelValue) {
        this.whereData.setWhereType(WhereType.LESS);
        this.whereData.setWhereValueType(WhereValueType.JOIN);
        JoinTableData<T> joinTableData = this.handleModel.getSqlData().getJoinTableData(alias, onClass);
        TW whereModel = joinTableData.getTableModel().getWhereModel();
        WhereData targetWhereData = whereModelValue.apply(whereModel).whereData;
        this.whereData.setTargetTableName(targetWhereData.getOwnerTableName());
        this.whereData.setTargetTableAlias(joinTableData.getTableAlias());
        this.whereData.setTargetColumnName(targetWhereData.getOwnerColumnName());
        this.whereDataList.add(this.whereData);
        return this.handleModel;
    }

    @Override
    public <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW lessThanAndEqualTo(Class<T> onClass, String alias, WhereModelValue<T, TC, TO, TW, TS, TG> whereModelValue) {
        this.whereData.setWhereType(WhereType.LESS_EQUAL);
        this.whereData.setWhereValueType(WhereValueType.JOIN);
        JoinTableData<T> joinTableData = this.handleModel.getSqlData().getJoinTableData(alias, onClass);
        TW whereModel = joinTableData.getTableModel().getWhereModel();
        WhereData targetWhereData = whereModelValue.apply(whereModel).whereData;
        this.whereData.setTargetTableName(targetWhereData.getOwnerTableName());
        this.whereData.setTargetTableAlias(joinTableData.getTableAlias());
        this.whereData.setTargetColumnName(targetWhereData.getOwnerColumnName());
        this.whereDataList.add(this.whereData);
        return this.handleModel;
    }

    @Override
    public <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW equalTo(String tableName, Class<T> modelClass, String alias, SubQuery<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> subQuery) {
        SqlBuilder sqlBuilder = SubQuery.execute(this.handleModel.getSqlData(), tableName, modelClass, alias, subQuery);
        this.whereData.setWhereType(WhereType.EQUAL);
        this.whereData.setWhereValueType(WhereValueType.SUB_QUERY);
        this.whereData.setTargetSubQuery(sqlBuilder);
        this.whereDataList.add(this.whereData);
        return this.handleModel;
    }

    @Override
    public <T extends Model<T, TC, TO, TW, TS, TG>, TC extends ColumnModel<T, TC, TO, TW, TS, TG>, TO extends OnModel<T, TC, TO, TW, TS, TG>, TW extends WhereModel<T, TC, TO, TW, TS, TG>, TS extends SortModel<T, TC, TO, TW, TS, TG>, TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW notEqualTo(String tableName, Class<T> modelClass, String alias, SubQuery<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> subQuery) {
        SqlBuilder sqlBuilder = SubQuery.execute(this.handleModel.getSqlData(), tableName, modelClass, alias, subQuery);
        this.whereData.setWhereType(WhereType.NOT_EQUAL);
        this.whereData.setWhereValueType(WhereValueType.SUB_QUERY);
        this.whereData.setTargetSubQuery(sqlBuilder);
        this.whereDataList.add(this.whereData);
        return this.handleModel;
    }

    @Override
    public <T extends Model<T, TC, TO, TW, TS, TG>, TC extends ColumnModel<T, TC, TO, TW, TS, TG>, TO extends OnModel<T, TC, TO, TW, TS, TG>, TW extends WhereModel<T, TC, TO, TW, TS, TG>, TS extends SortModel<T, TC, TO, TW, TS, TG>, TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW greaterThan(String tableName, Class<T> modelClass, String alias, SubQuery<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> subQuery) {
        SqlBuilder sqlBuilder = SubQuery.execute(this.handleModel.getSqlData(), tableName, modelClass, alias, subQuery);
        this.whereData.setWhereType(WhereType.GREATER);
        this.whereData.setWhereValueType(WhereValueType.SUB_QUERY);
        this.whereData.setTargetSubQuery(sqlBuilder);
        this.whereDataList.add(this.whereData);
        return this.handleModel;
    }

    @Override
    public <T extends Model<T, TC, TO, TW, TS, TG>, TC extends ColumnModel<T, TC, TO, TW, TS, TG>, TO extends OnModel<T, TC, TO, TW, TS, TG>, TW extends WhereModel<T, TC, TO, TW, TS, TG>, TS extends SortModel<T, TC, TO, TW, TS, TG>, TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW greaterThanAndEqualTo(String tableName, Class<T> modelClass, String alias, SubQuery<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> subQuery) {
        SqlBuilder sqlBuilder = SubQuery.execute(this.handleModel.getSqlData(), tableName, modelClass, alias, subQuery);
        this.whereData.setWhereType(WhereType.GREATER_EQUAL);
        this.whereData.setWhereValueType(WhereValueType.SUB_QUERY);
        this.whereData.setTargetSubQuery(sqlBuilder);
        this.whereDataList.add(this.whereData);
        return this.handleModel;
    }

    @Override
    public <T extends Model<T, TC, TO, TW, TS, TG>, TC extends ColumnModel<T, TC, TO, TW, TS, TG>, TO extends OnModel<T, TC, TO, TW, TS, TG>, TW extends WhereModel<T, TC, TO, TW, TS, TG>, TS extends SortModel<T, TC, TO, TW, TS, TG>, TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW lessThan(String tableName, Class<T> modelClass, String alias, SubQuery<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> subQuery) {
        SqlBuilder sqlBuilder = SubQuery.execute(this.handleModel.getSqlData(), tableName, modelClass, alias, subQuery);
        this.whereData.setWhereType(WhereType.LESS);
        this.whereData.setWhereValueType(WhereValueType.SUB_QUERY);
        this.whereData.setTargetSubQuery(sqlBuilder);
        this.whereDataList.add(this.whereData);
        return this.handleModel;
    }

    @Override
    public <T extends Model<T, TC, TO, TW, TS, TG>, TC extends ColumnModel<T, TC, TO, TW, TS, TG>, TO extends OnModel<T, TC, TO, TW, TS, TG>, TW extends WhereModel<T, TC, TO, TW, TS, TG>, TS extends SortModel<T, TC, TO, TW, TS, TG>, TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW lessThanAndEqualTo(String tableName, Class<T> modelClass, String alias, SubQuery<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> subQuery) {
        SqlBuilder sqlBuilder = SubQuery.execute(this.handleModel.getSqlData(), tableName, modelClass, alias, subQuery);
        this.whereData.setWhereType(WhereType.LESS_EQUAL);
        this.whereData.setWhereValueType(WhereValueType.SUB_QUERY);
        this.whereData.setTargetSubQuery(sqlBuilder);
        this.whereDataList.add(this.whereData);
        return this.handleModel;
    }

    @Override
    public <T extends Model<T, TC, TO, TW, TS, TG>, TC extends ColumnModel<T, TC, TO, TW, TS, TG>, TO extends OnModel<T, TC, TO, TW, TS, TG>, TW extends WhereModel<T, TC, TO, TW, TS, TG>, TS extends SortModel<T, TC, TO, TW, TS, TG>, TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW like(String tableName, Class<T> modelClass, String alias, SubQuery<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> subQuery) {
        SqlBuilder sqlBuilder = SubQuery.execute(this.handleModel.getSqlData(), tableName, modelClass, alias, subQuery);
        this.whereData.setWhereType(WhereType.LIKE);
        this.whereData.setWhereValueType(WhereValueType.SUB_QUERY);
        this.whereData.setTargetSubQuery(sqlBuilder);
        this.whereDataList.add(this.whereData);
        return this.handleModel;
    }

    @Override
    public <T extends Model<T, TC, TO, TW, TS, TG>, TC extends ColumnModel<T, TC, TO, TW, TS, TG>, TO extends OnModel<T, TC, TO, TW, TS, TG>, TW extends WhereModel<T, TC, TO, TW, TS, TG>, TS extends SortModel<T, TC, TO, TW, TS, TG>, TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW in(String tableName, Class<T> modelClass, String alias, SubQuery<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> subQuery) {
        SqlBuilder sqlBuilder = SubQuery.execute(this.handleModel.getSqlData(), tableName, modelClass, alias, subQuery);
        this.whereData.setWhereType(WhereType.IN);
        this.whereData.setWhereValueType(WhereValueType.SUB_QUERY);
        this.whereData.setTargetSubQuery(sqlBuilder);
        this.whereDataList.add(this.whereData);
        return this.handleModel;
    }

    @Override
    public <T extends Model<T, TC, TO, TW, TS, TG>, TC extends ColumnModel<T, TC, TO, TW, TS, TG>, TO extends OnModel<T, TC, TO, TW, TS, TG>, TW extends WhereModel<T, TC, TO, TW, TS, TG>, TS extends SortModel<T, TC, TO, TW, TS, TG>, TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW notIn(String tableName, Class<T> modelClass, String alias, SubQuery<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> subQuery) {
        SqlBuilder sqlBuilder = SubQuery.execute(this.handleModel.getSqlData(), tableName, modelClass, alias, subQuery);
        this.whereData.setWhereType(WhereType.NOT_IN);
        this.whereData.setWhereValueType(WhereValueType.SUB_QUERY);
        this.whereData.setTargetSubQuery(sqlBuilder);
        this.whereDataList.add(this.whereData);
        return this.handleModel;
    }

}
