package pub.avalon.sqlhelper.core.beans;

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
public final class WhereBuilder<M extends Model<M, ML, MO, MC, MS, MG>,
        ML extends ColumnModel<M, ML, MO, MC, MS, MG>,
        MO extends OnModel<M, ML, MO, MC, MS, MG>,
        MC extends WhereModel<M, ML, MO, MC, MS, MG>,
        MS extends SortModel<M, ML, MO, MC, MS, MG>,
        MG extends GroupModel<M, ML, MO, MC, MS, MG>> implements ComparisonOperator<MC>, WhereComparisonOperator<M, ML, MO, MC, MS, MG>, WhereComparisonOperatorSubQuery<M, ML, MO, MC, MS, MG> {

    private MC handleModel;

    public WhereBuilder(MC handleModel) {
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

    public WhereBuilder<M, ML, MO, MC, MS, MG> handler(String ownerTableName, String ownerAlias, String ownerColumnName) {
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
    public MC isNull() {
        this.whereData.setWhereType(WhereType.IS_NULL);
        this.whereData.setValueCount(0);
        this.whereDataList.add(this.whereData);
        return this.handleModel;
    }

    @Override
    public MC isNotNull() {
        this.whereData.setWhereType(WhereType.IS_NOT_NULL);
        this.whereData.setValueCount(0);
        this.whereDataList.add(this.whereData);
        return this.handleModel;
    }

    @Override
    public MC equalToValue(Object value, ComparisonRule comparisonRule) {
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
    public MC notEqualToValue(Object value, ComparisonRule comparisonRule) {
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
    public MC greaterThanValue(Object value, ComparisonRule comparisonRule) {
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
    public MC greaterThanAndEqualToValue(Object value, ComparisonRule comparisonRule) {
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
    public MC lessThanValue(Object value, ComparisonRule comparisonRule) {
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
    public MC lessThanAndEqualToValue(Object value, ComparisonRule comparisonRule) {
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
    public MC betweenValue(Object value, Object secondValue, ComparisonRule comparisonRule) {
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
    public MC likeValue(Object value, ComparisonRule comparisonRule) {
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
    public MC inValue(Object[] values, ComparisonRule comparisonRule) {
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
    public MC inValue(Collection<?> values, ComparisonRule comparisonRule) {
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
    public MC notInValue(Object[] values, ComparisonRule comparisonRule) {
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
    public MC notInValue(Collection<?> values, ComparisonRule comparisonRule) {
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
    public MC equalTo(WhereBuilder whereBuilder) {
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
    public MC notEqualTo(WhereBuilder whereBuilder) {
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
    public MC greaterThan(WhereBuilder whereBuilder) {
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
    public MC greaterThanAndEqualTo(WhereBuilder whereBuilder) {
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
    public MC lessThan(WhereBuilder whereBuilder) {
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
    public MC lessThanAndEqualTo(WhereBuilder whereBuilder) {
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
    public <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> MC equalTo(Class<T> onClass, String alias, WhereModelValue<T, TL, TO, TC, TS, TG> whereModelValue) {
        this.whereData.setWhereType(WhereType.EQUAL);
        this.whereData.setWhereValueType(WhereValueType.JOIN);
        JoinTableData<T> joinTableData = this.handleModel.getSqlData().getJoinTableData(alias, onClass);
        TC whereModel = joinTableData.getTableModel().getWhereModel();
        WhereData targetWhereData = whereModelValue.apply(whereModel).whereData;
        this.whereData.setTargetTableName(targetWhereData.getOwnerTableName());
        this.whereData.setTargetTableAlias(joinTableData.getTableAlias());
        this.whereData.setTargetColumnName(targetWhereData.getOwnerColumnName());
        this.whereDataList.add(this.whereData);
        return this.handleModel;
    }

    @Override
    public <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> MC notEqualTo(Class<T> onClass, String alias, WhereModelValue<T, TL, TO, TC, TS, TG> whereModelValue) {
        this.whereData.setWhereType(WhereType.NOT_EQUAL);
        this.whereData.setWhereValueType(WhereValueType.JOIN);
        JoinTableData<T> joinTableData = this.handleModel.getSqlData().getJoinTableData(alias, onClass);
        TC whereModel = joinTableData.getTableModel().getWhereModel();
        WhereData targetWhereData = whereModelValue.apply(whereModel).whereData;
        this.whereData.setTargetTableName(targetWhereData.getOwnerTableName());
        this.whereData.setTargetTableAlias(joinTableData.getTableAlias());
        this.whereData.setTargetColumnName(targetWhereData.getOwnerColumnName());
        this.whereDataList.add(this.whereData);
        return this.handleModel;
    }

    @Override
    public <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> MC greaterThan(Class<T> onClass, String alias, WhereModelValue<T, TL, TO, TC, TS, TG> whereModelValue) {
        this.whereData.setWhereType(WhereType.GREATER);
        this.whereData.setWhereValueType(WhereValueType.JOIN);
        JoinTableData<T> joinTableData = this.handleModel.getSqlData().getJoinTableData(alias, onClass);
        TC whereModel = joinTableData.getTableModel().getWhereModel();
        WhereData targetWhereData = whereModelValue.apply(whereModel).whereData;
        this.whereData.setTargetTableName(targetWhereData.getOwnerTableName());
        this.whereData.setTargetTableAlias(joinTableData.getTableAlias());
        this.whereData.setTargetColumnName(targetWhereData.getOwnerColumnName());
        this.whereDataList.add(this.whereData);
        return this.handleModel;
    }

    @Override
    public <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> MC greaterThanAndEqualTo(Class<T> onClass, String alias, WhereModelValue<T, TL, TO, TC, TS, TG> whereModelValue) {
        this.whereData.setWhereType(WhereType.GREATER_EQUAL);
        this.whereData.setWhereValueType(WhereValueType.JOIN);
        JoinTableData<T> joinTableData = this.handleModel.getSqlData().getJoinTableData(alias, onClass);
        TC whereModel = joinTableData.getTableModel().getWhereModel();
        WhereData targetWhereData = whereModelValue.apply(whereModel).whereData;
        this.whereData.setTargetTableName(targetWhereData.getOwnerTableName());
        this.whereData.setTargetTableAlias(joinTableData.getTableAlias());
        this.whereData.setTargetColumnName(targetWhereData.getOwnerColumnName());
        this.whereDataList.add(this.whereData);
        return this.handleModel;
    }

    @Override
    public <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> MC lessThan(Class<T> onClass, String alias, WhereModelValue<T, TL, TO, TC, TS, TG> whereModelValue) {
        this.whereData.setWhereType(WhereType.LESS);
        this.whereData.setWhereValueType(WhereValueType.JOIN);
        JoinTableData<T> joinTableData = this.handleModel.getSqlData().getJoinTableData(alias, onClass);
        TC whereModel = joinTableData.getTableModel().getWhereModel();
        WhereData targetWhereData = whereModelValue.apply(whereModel).whereData;
        this.whereData.setTargetTableName(targetWhereData.getOwnerTableName());
        this.whereData.setTargetTableAlias(joinTableData.getTableAlias());
        this.whereData.setTargetColumnName(targetWhereData.getOwnerColumnName());
        this.whereDataList.add(this.whereData);
        return this.handleModel;
    }

    @Override
    public <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> MC lessThanAndEqualTo(Class<T> onClass, String alias, WhereModelValue<T, TL, TO, TC, TS, TG> whereModelValue) {
        this.whereData.setWhereType(WhereType.LESS_EQUAL);
        this.whereData.setWhereValueType(WhereValueType.JOIN);
        JoinTableData<T> joinTableData = this.handleModel.getSqlData().getJoinTableData(alias, onClass);
        TC whereModel = joinTableData.getTableModel().getWhereModel();
        WhereData targetWhereData = whereModelValue.apply(whereModel).whereData;
        this.whereData.setTargetTableName(targetWhereData.getOwnerTableName());
        this.whereData.setTargetTableAlias(joinTableData.getTableAlias());
        this.whereData.setTargetColumnName(targetWhereData.getOwnerColumnName());
        this.whereDataList.add(this.whereData);
        return this.handleModel;
    }

    //TODO 待编写
    @Override
    public <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> MC equalTo(String tableName, Class<T> mainClass, String alias, SubQuery<M, ML, MO, MC, MS, MG, T, TL, TO, TC, TS, TG> subQuery) {
        /*QueryEngine<T, TL, TO, TC, TS, TG> queryEngine;
        switch (this.handleModel.getSqlData().getDataBaseType()) {
            case MYSQL:
                queryEngine = new QueryEngine<>(tableName, mainClass, alias, DataBaseType.MYSQL);
                break;
            case SQLSERVER:
                queryEngine = new QueryEngine<>(tableName, mainClass, alias, DataBaseType.SQLSERVER);
                break;
            default:
                throw new SqlException("SubQuery do not support this database type temporarily.");
        }


        Map<String, JoinTableData> joinTableDataAliasMap = this.sqlData.getJoinTableDataAliasMap();
        if (joinTableDataAliasMap != null && joinTableDataAliasMap.size() > 0) {
            for (Map.Entry<String, JoinTableData> entry : joinTableDataAliasMap.entrySet()) {
                queryEngine.sqlData.addSubQueryJoinTableData(entry.getValue());
            }
        }
        MainTableData tableData = this.sqlData.getMainTableData();
        MC mc = (MC) tableData.getTableModel().getWhereModel();
        SqlBuilder sqlBuilder = subQuery.apply(mc, queryEngine);
        this.sqlData.addSubQueryAliasMap(columnAlias, sqlBuilder);*/
        return null;
    }
}
