package pub.avalon.sqlhelper.core.builder;

import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.build.SqlBuilder;
import pub.avalon.sqlhelper.core.data.AbstractTableData;
import pub.avalon.sqlhelper.core.data.ColumnDatum;
import pub.avalon.sqlhelper.core.data.JoinTableData;
import pub.avalon.sqlhelper.core.data.WhereData;
import pub.avalon.sqlhelper.core.exception.ComparisonException;
import pub.avalon.sqlhelper.core.exception.SqlException;
import pub.avalon.sqlhelper.core.norm.*;

import java.util.Collection;
import java.util.Set;

/**
 * 条件构建器
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class WhereDataBuilder<M extends Model<M, MC, MO, MW, MS, MG>,
        MC extends ColumnModel<M, MC, MO, MW, MS, MG>,
        MO extends OnModel<M, MC, MO, MW, MS, MG>,
        MW extends WhereModel<M, MC, MO, MW, MS, MG>,
        MS extends SortModel<M, MC, MO, MW, MS, MG>,
        MG extends GroupModel<M, MC, MO, MW, MS, MG>> extends AbstractModelDataBuilder<WhereDataBuilder, WhereData> implements ComparisonOperator<MW>, WhereComparisonOperator<M, MC, MO, MW, MS, MG>, WhereComparisonOperatorSubQuery<M, MC, MO, MW, MS, MG> {

    private MW handleModel;

    public WhereDataBuilder(MW handleModel) {
        this.handleModel = handleModel;
    }

    protected WhereData whereData;

    private AbstractTableData ownerTableData;

    public void setOwnerTableData(AbstractTableData<M> ownerTableData) {
        this.ownerTableData = ownerTableData;
    }

    @Override
    public WhereDataBuilder apply(String tableName, String tableAlias, String columnName, String columnAlias) {
        this.whereData = new WhereData();
        this.whereData.setOwnerTableName(tableName);
        this.whereData.setOwnerTableAlias(tableAlias);
        this.whereData.setOwnerColumnName(columnName);
        if (this.ownerTableData == null) {
            return this;
        }
        this.whereData.setOwnerTableAlias(this.ownerTableData.getTableAlias());
        return this;
    }

    /**
     * sql片段
     * 直接写入sql片段
     *
     * @param sqlPart
     * @return
     */
    public MW sqlPart(String sqlPart) {
        this.whereData.setWhereValueType(WhereValueType.SQL_PART);
        this.whereData.setSqlPart(sqlPart);
        this.addModelData(this.whereData);
        return this.handleModel;
    }

    @Override
    public MW isNull() {
        this.whereData.setWhereType(WhereType.IS_NULL);
        this.whereData.setValueCount(0);
        this.addModelData(this.whereData);
        return this.handleModel;
    }

    @Override
    public MW isNotNull() {
        this.whereData.setWhereType(WhereType.IS_NOT_NULL);
        this.whereData.setValueCount(0);
        this.addModelData(this.whereData);
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
        this.addModelData(this.whereData);
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
        this.addModelData(this.whereData);
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
        this.addModelData(this.whereData);
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
        this.addModelData(this.whereData);
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
        this.addModelData(this.whereData);
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
        this.addModelData(this.whereData);
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
        this.addModelData(this.whereData);
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
        this.addModelData(this.whereData);
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
        this.addModelData(this.whereData);
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
        this.addModelData(this.whereData);
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
        this.addModelData(this.whereData);
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
        this.addModelData(this.whereData);
        return this.handleModel;
    }

    @Override
    public <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW equalTo(Class<T> onClass, String alias, ColumnModelValue<T, TC, TO, TW, TS, TG> columnModelValue) {
        this.whereData.setWhereType(WhereType.EQUAL);
        this.whereData.setWhereValueType(WhereValueType.JOIN);
        JoinTableData<T> joinTableData = this.handleModel.getSqlData().getJoinTableData(alias, onClass);
        TC columnModel = joinTableData.getTableModel().getColumnModel();
        columnModel = columnModelValue.apply(columnModel);
        Set<ColumnDatum> columnData = columnModel.modelDataBuilder.takeoutModelData();
        if (columnData.size() > 1) {
            throw new SqlException("you can not set more than one column for equalTo.");
        }
        ColumnDatum columnDatum = columnData.iterator().next();
        this.whereData.setTargetTableName(columnDatum.getOwnerTableName());
        this.whereData.setTargetTableAlias(columnDatum.getOwnerTableAlias());
        this.whereData.setTargetColumnName(columnDatum.getOwnerColumnName());
        this.addModelData(this.whereData);
        return this.handleModel;
    }

    @Override
    public <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW notEqualTo(Class<T> onClass, String alias, ColumnModelValue<T, TC, TO, TW, TS, TG> columnModelValue) {
        this.whereData.setWhereType(WhereType.NOT_EQUAL);
        this.whereData.setWhereValueType(WhereValueType.JOIN);
        JoinTableData<T> joinTableData = this.handleModel.getSqlData().getJoinTableData(alias, onClass);
        TC columnModel = joinTableData.getTableModel().getColumnModel();
        columnModel = columnModelValue.apply(columnModel);
        Set<ColumnDatum> columnData = columnModel.modelDataBuilder.takeoutModelData();
        if (columnData.size() > 1) {
            throw new SqlException("you can not set more than one column for notEqualTo.");
        }
        ColumnDatum columnDatum = columnData.iterator().next();
        this.whereData.setTargetTableName(columnDatum.getOwnerTableName());
        this.whereData.setTargetTableAlias(columnDatum.getOwnerTableAlias());
        this.whereData.setTargetColumnName(columnDatum.getOwnerColumnName());
        this.addModelData(this.whereData);
        return this.handleModel;
    }

    @Override
    public <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW greaterThan(Class<T> onClass, String alias, ColumnModelValue<T, TC, TO, TW, TS, TG> columnModelValue) {
        this.whereData.setWhereType(WhereType.GREATER);
        this.whereData.setWhereValueType(WhereValueType.JOIN);
        JoinTableData<T> joinTableData = this.handleModel.getSqlData().getJoinTableData(alias, onClass);
        TC columnModel = joinTableData.getTableModel().getColumnModel();
        columnModel = columnModelValue.apply(columnModel);
        Set<ColumnDatum> columnData = columnModel.modelDataBuilder.takeoutModelData();
        if (columnData.size() > 1) {
            throw new SqlException("you can not set more than one column for greaterThan.");
        }
        ColumnDatum columnDatum = columnData.iterator().next();
        this.whereData.setTargetTableName(columnDatum.getOwnerTableName());
        this.whereData.setTargetTableAlias(columnDatum.getOwnerTableAlias());
        this.whereData.setTargetColumnName(columnDatum.getOwnerColumnName());
        this.addModelData(this.whereData);
        return this.handleModel;
    }

    @Override
    public <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW greaterThanAndEqualTo(Class<T> onClass, String alias, ColumnModelValue<T, TC, TO, TW, TS, TG> columnModelValue) {
        this.whereData.setWhereType(WhereType.GREATER_EQUAL);
        this.whereData.setWhereValueType(WhereValueType.JOIN);
        JoinTableData<T> joinTableData = this.handleModel.getSqlData().getJoinTableData(alias, onClass);
        TC columnModel = joinTableData.getTableModel().getColumnModel();
        columnModel = columnModelValue.apply(columnModel);
        Set<ColumnDatum> columnData = columnModel.modelDataBuilder.takeoutModelData();
        if (columnData.size() > 1) {
            throw new SqlException("you can not set more than one column for greaterThanAndEqualTo.");
        }
        ColumnDatum columnDatum = columnData.iterator().next();
        this.whereData.setTargetTableName(columnDatum.getOwnerTableName());
        this.whereData.setTargetTableAlias(columnDatum.getOwnerTableAlias());
        this.whereData.setTargetColumnName(columnDatum.getOwnerColumnName());
        this.addModelData(this.whereData);
        return this.handleModel;
    }

    @Override
    public <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW lessThan(Class<T> onClass, String alias, ColumnModelValue<T, TC, TO, TW, TS, TG> columnModelValue) {
        this.whereData.setWhereType(WhereType.LESS);
        this.whereData.setWhereValueType(WhereValueType.JOIN);
        JoinTableData<T> joinTableData = this.handleModel.getSqlData().getJoinTableData(alias, onClass);
        TC columnModel = joinTableData.getTableModel().getColumnModel();
        columnModel = columnModelValue.apply(columnModel);
        Set<ColumnDatum> columnData = columnModel.modelDataBuilder.takeoutModelData();
        if (columnData.size() > 1) {
            throw new SqlException("you can not set more than one column for lessThan.");
        }
        ColumnDatum columnDatum = columnData.iterator().next();
        this.whereData.setTargetTableName(columnDatum.getOwnerTableName());
        this.whereData.setTargetTableAlias(columnDatum.getOwnerTableAlias());
        this.whereData.setTargetColumnName(columnDatum.getOwnerColumnName());
        this.addModelData(this.whereData);
        return this.handleModel;
    }

    @Override
    public <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW lessThanAndEqualTo(Class<T> onClass, String alias, ColumnModelValue<T, TC, TO, TW, TS, TG> columnModelValue) {
        this.whereData.setWhereType(WhereType.LESS_EQUAL);
        this.whereData.setWhereValueType(WhereValueType.JOIN);
        JoinTableData<T> joinTableData = this.handleModel.getSqlData().getJoinTableData(alias, onClass);
        TC columnModel = joinTableData.getTableModel().getColumnModel();
        columnModel = columnModelValue.apply(columnModel);
        Set<ColumnDatum> columnData = columnModel.modelDataBuilder.takeoutModelData();
        if (columnData.size() > 1) {
            throw new SqlException("you can not set more than one column for lessThanAndEqualTo.");
        }
        ColumnDatum columnDatum = columnData.iterator().next();
        this.whereData.setTargetTableName(columnDatum.getOwnerTableName());
        this.whereData.setTargetTableAlias(columnDatum.getOwnerTableAlias());
        this.whereData.setTargetColumnName(columnDatum.getOwnerColumnName());
        this.addModelData(this.whereData);
        return this.handleModel;
    }

    @Override
    public <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW equalToSubQuery(String tableName, Class<T> modelClass, String alias, SubQuery<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> subQuery) {
        SqlBuilder sqlBuilder = SubQuery.execute(this.handleModel.getSqlData(), tableName, modelClass, alias, subQuery);
        this.whereData.setWhereType(WhereType.EQUAL);
        this.whereData.setWhereValueType(WhereValueType.SUB_QUERY);
        this.whereData.setTargetSubQuery(sqlBuilder);
        this.addModelData(this.whereData);
        return this.handleModel;
    }

    @Override
    public <T extends Model<T, TC, TO, TW, TS, TG>, TC extends ColumnModel<T, TC, TO, TW, TS, TG>, TO extends OnModel<T, TC, TO, TW, TS, TG>, TW extends WhereModel<T, TC, TO, TW, TS, TG>, TS extends SortModel<T, TC, TO, TW, TS, TG>, TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW notEqualToSubQuery(String tableName, Class<T> modelClass, String alias, SubQuery<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> subQuery) {
        SqlBuilder sqlBuilder = SubQuery.execute(this.handleModel.getSqlData(), tableName, modelClass, alias, subQuery);
        this.whereData.setWhereType(WhereType.NOT_EQUAL);
        this.whereData.setWhereValueType(WhereValueType.SUB_QUERY);
        this.whereData.setTargetSubQuery(sqlBuilder);
        this.addModelData(this.whereData);
        return this.handleModel;
    }

    @Override
    public <T extends Model<T, TC, TO, TW, TS, TG>, TC extends ColumnModel<T, TC, TO, TW, TS, TG>, TO extends OnModel<T, TC, TO, TW, TS, TG>, TW extends WhereModel<T, TC, TO, TW, TS, TG>, TS extends SortModel<T, TC, TO, TW, TS, TG>, TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW greaterThanSubQuery(String tableName, Class<T> modelClass, String alias, SubQuery<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> subQuery) {
        SqlBuilder sqlBuilder = SubQuery.execute(this.handleModel.getSqlData(), tableName, modelClass, alias, subQuery);
        this.whereData.setWhereType(WhereType.GREATER);
        this.whereData.setWhereValueType(WhereValueType.SUB_QUERY);
        this.whereData.setTargetSubQuery(sqlBuilder);
        this.addModelData(this.whereData);
        return this.handleModel;
    }

    @Override
    public <T extends Model<T, TC, TO, TW, TS, TG>, TC extends ColumnModel<T, TC, TO, TW, TS, TG>, TO extends OnModel<T, TC, TO, TW, TS, TG>, TW extends WhereModel<T, TC, TO, TW, TS, TG>, TS extends SortModel<T, TC, TO, TW, TS, TG>, TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW greaterThanAndEqualToSubQuery(String tableName, Class<T> modelClass, String alias, SubQuery<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> subQuery) {
        SqlBuilder sqlBuilder = SubQuery.execute(this.handleModel.getSqlData(), tableName, modelClass, alias, subQuery);
        this.whereData.setWhereType(WhereType.GREATER_EQUAL);
        this.whereData.setWhereValueType(WhereValueType.SUB_QUERY);
        this.whereData.setTargetSubQuery(sqlBuilder);
        this.addModelData(this.whereData);
        return this.handleModel;
    }

    @Override
    public <T extends Model<T, TC, TO, TW, TS, TG>, TC extends ColumnModel<T, TC, TO, TW, TS, TG>, TO extends OnModel<T, TC, TO, TW, TS, TG>, TW extends WhereModel<T, TC, TO, TW, TS, TG>, TS extends SortModel<T, TC, TO, TW, TS, TG>, TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW lessThanSubQuery(String tableName, Class<T> modelClass, String alias, SubQuery<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> subQuery) {
        SqlBuilder sqlBuilder = SubQuery.execute(this.handleModel.getSqlData(), tableName, modelClass, alias, subQuery);
        this.whereData.setWhereType(WhereType.LESS);
        this.whereData.setWhereValueType(WhereValueType.SUB_QUERY);
        this.whereData.setTargetSubQuery(sqlBuilder);
        this.addModelData(this.whereData);
        return this.handleModel;
    }

    @Override
    public <T extends Model<T, TC, TO, TW, TS, TG>, TC extends ColumnModel<T, TC, TO, TW, TS, TG>, TO extends OnModel<T, TC, TO, TW, TS, TG>, TW extends WhereModel<T, TC, TO, TW, TS, TG>, TS extends SortModel<T, TC, TO, TW, TS, TG>, TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW lessThanAndEqualToSubQuery(String tableName, Class<T> modelClass, String alias, SubQuery<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> subQuery) {
        SqlBuilder sqlBuilder = SubQuery.execute(this.handleModel.getSqlData(), tableName, modelClass, alias, subQuery);
        this.whereData.setWhereType(WhereType.LESS_EQUAL);
        this.whereData.setWhereValueType(WhereValueType.SUB_QUERY);
        this.whereData.setTargetSubQuery(sqlBuilder);
        this.addModelData(this.whereData);
        return this.handleModel;
    }

    @Override
    public <T extends Model<T, TC, TO, TW, TS, TG>, TC extends ColumnModel<T, TC, TO, TW, TS, TG>, TO extends OnModel<T, TC, TO, TW, TS, TG>, TW extends WhereModel<T, TC, TO, TW, TS, TG>, TS extends SortModel<T, TC, TO, TW, TS, TG>, TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW likeSubQuery(String tableName, Class<T> modelClass, String alias, SubQuery<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> subQuery) {
        SqlBuilder sqlBuilder = SubQuery.execute(this.handleModel.getSqlData(), tableName, modelClass, alias, subQuery);
        this.whereData.setWhereType(WhereType.LIKE);
        this.whereData.setWhereValueType(WhereValueType.SUB_QUERY);
        this.whereData.setTargetSubQuery(sqlBuilder);
        this.addModelData(this.whereData);
        return this.handleModel;
    }

    @Override
    public <T extends Model<T, TC, TO, TW, TS, TG>, TC extends ColumnModel<T, TC, TO, TW, TS, TG>, TO extends OnModel<T, TC, TO, TW, TS, TG>, TW extends WhereModel<T, TC, TO, TW, TS, TG>, TS extends SortModel<T, TC, TO, TW, TS, TG>, TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW inSubQuery(String tableName, Class<T> modelClass, String alias, SubQuery<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> subQuery) {
        SqlBuilder sqlBuilder = SubQuery.execute(this.handleModel.getSqlData(), tableName, modelClass, alias, subQuery);
        this.whereData.setWhereType(WhereType.IN);
        this.whereData.setWhereValueType(WhereValueType.SUB_QUERY);
        this.whereData.setTargetSubQuery(sqlBuilder);
        this.addModelData(this.whereData);
        return this.handleModel;
    }

    @Override
    public <T extends Model<T, TC, TO, TW, TS, TG>, TC extends ColumnModel<T, TC, TO, TW, TS, TG>, TO extends OnModel<T, TC, TO, TW, TS, TG>, TW extends WhereModel<T, TC, TO, TW, TS, TG>, TS extends SortModel<T, TC, TO, TW, TS, TG>, TG extends GroupModel<T, TC, TO, TW, TS, TG>> MW notInSubQuery(String tableName, Class<T> modelClass, String alias, SubQuery<M, MC, MO, MW, MS, MG, T, TC, TO, TW, TS, TG> subQuery) {
        SqlBuilder sqlBuilder = SubQuery.execute(this.handleModel.getSqlData(), tableName, modelClass, alias, subQuery);
        this.whereData.setWhereType(WhereType.NOT_IN);
        this.whereData.setWhereValueType(WhereValueType.SUB_QUERY);
        this.whereData.setTargetSubQuery(sqlBuilder);
        this.addModelData(this.whereData);
        return this.handleModel;
    }

}
