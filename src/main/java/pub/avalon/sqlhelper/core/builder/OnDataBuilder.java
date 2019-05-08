package pub.avalon.sqlhelper.core.builder;

import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.data.AbstractTableData;
import pub.avalon.sqlhelper.core.data.JoinTableData;
import pub.avalon.sqlhelper.core.data.OnData;
import pub.avalon.sqlhelper.core.exception.ComparisonException;
import pub.avalon.sqlhelper.core.norm.ComparisonOperator;
import pub.avalon.sqlhelper.core.norm.Model;
import pub.avalon.sqlhelper.core.norm.OnComparisonOperator;
import pub.avalon.sqlhelper.core.norm.OnModelValue;

import java.util.Collection;

/**
 * On条件构建器
 *
 * @author 白超
 * @version 1.0
 * @see ComparisonOperator
 * @see OnComparisonOperator
 * @since 2018/7/10
 */
public final class OnDataBuilder<M extends Model<M, MC, MO, MW, MS, MG>,
        MC extends ColumnModel<M, MC, MO, MW, MS, MG>,
        MO extends OnModel<M, MC, MO, MW, MS, MG>,
        MW extends WhereModel<M, MC, MO, MW, MS, MG>,
        MS extends SortModel<M, MC, MO, MW, MS, MG>,
        MG extends GroupModel<M, MC, MO, MW, MS, MG>> extends AbstractModelDataBuilder<OnDataBuilder, OnData> implements ComparisonOperator<MO>, OnComparisonOperator<M, MC, MO, MW, MS, MG> {

    /**
     * 当然操作的模组
     */
    private MO handleModel;

    public OnDataBuilder(MO handleModel) {
        this.handleModel = handleModel;
    }

    /**
     * 待构建的OnData
     */
    private OnData onData;

    private AbstractTableData ownerTableData;

    public void setOwnerTableData(AbstractTableData ownerTableData) {
        this.ownerTableData = ownerTableData;
    }

    @Override
    public OnDataBuilder apply(String tableName, String tableAlias, String columnName, String columnAlias) {
        this.onData = new OnData();
        this.onData.setOwnerTableName(tableName);
        this.onData.setOwnerTableAlias(tableAlias);
        this.onData.setOwnerColumnName(columnName);
        if (this.ownerTableData == null) {
            return this;
        }
        this.onData.setOwnerTableAlias(this.ownerTableData.getTableAlias());
        return this;
    }

    @Override
    public MO isNull() {
        this.onData.setOnType(OnType.IS_NULL);
        this.onData.setOnValueType(OnValueType.VALUE);
        this.onData.setValueCount(0);
        this.addModelData(this.onData);
        return this.handleModel;
    }

    @Override
    public MO isNotNull() {
        this.onData.setOnType(OnType.IS_NOT_NULL);
        this.onData.setOnValueType(OnValueType.VALUE);
        this.onData.setValueCount(0);
        this.addModelData(this.onData);
        return this.handleModel;
    }

    @Override
    public MO equalToValue(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.handleModel;
                case NOT_NULL:
                    throw new ComparisonException("join table alias [" + this.onData.getOwnerTableAlias() + "] column [" + this.onData.getOwnerColumnName() + "] equalTo, the value can not be null.");
                default:
                    return null;
            }
        }
        this.onData.setOnType(OnType.EQUAL);
        this.onData.setOnValueType(OnValueType.VALUE);
        this.onData.setValueCount(1);
        this.onData.setTargetValue(value);
        this.addModelData(this.onData);
        return this.handleModel;
    }

    @Override
    public MO notEqualToValue(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.handleModel;
                case NOT_NULL:
                    throw new ComparisonException("join table alias [" + this.onData.getOwnerTableAlias() + "] column [" + this.onData.getOwnerColumnName() + "] notEqualTo, the value can not be null.");
                default:
                    return null;
            }
        }
        this.onData.setOnType(OnType.NOT_EQUAL);
        this.onData.setOnValueType(OnValueType.VALUE);
        this.onData.setValueCount(1);
        this.onData.setTargetValue(value);
        this.addModelData(this.onData);
        return this.handleModel;
    }

    @Override
    public MO greaterThanValue(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.handleModel;
                case NOT_NULL:
                    throw new ComparisonException("join table alias [" + this.onData.getOwnerTableAlias() + "] column [" + this.onData.getOwnerColumnName() + "] greaterThan, the value can not be null.");
                default:
                    return null;
            }
        }
        this.onData.setOnType(OnType.GREATER);
        this.onData.setOnValueType(OnValueType.VALUE);
        this.onData.setValueCount(1);
        this.onData.setTargetValue(value);
        this.addModelData(this.onData);
        return this.handleModel;
    }

    @Override
    public MO greaterThanAndEqualToValue(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.handleModel;
                case NOT_NULL:
                    throw new ComparisonException("join table alias [" + this.onData.getOwnerTableAlias() + "] column [" + this.onData.getOwnerColumnName() + "] greaterThanAndEqualTo, the value can not be null.");
                default:
                    return null;
            }
        }
        this.onData.setOnType(OnType.GREATER_EQUAL);
        this.onData.setOnValueType(OnValueType.VALUE);
        this.onData.setValueCount(1);
        this.onData.setTargetValue(value);
        this.addModelData(this.onData);
        return this.handleModel;
    }

    @Override
    public MO lessThanValue(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.handleModel;
                case NOT_NULL:
                    throw new ComparisonException("join table alias [" + this.onData.getOwnerTableAlias() + "] column [" + this.onData.getOwnerColumnName() + "] lessThan, the value can not be null.");
                default:
                    return null;
            }
        }
        this.onData.setOnType(OnType.LESS);
        this.onData.setOnValueType(OnValueType.VALUE);
        this.onData.setValueCount(1);
        this.onData.setTargetValue(value);
        this.addModelData(this.onData);
        return this.handleModel;
    }

    @Override
    public MO lessThanAndEqualToValue(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.handleModel;
                case NOT_NULL:
                    throw new ComparisonException("join table alias [" + this.onData.getOwnerTableAlias() + "] column [" + this.onData.getOwnerColumnName() + "] lessThanAndEqualTo, the value can not be null.");
                default:
                    return null;
            }
        }
        this.onData.setOnType(OnType.LESS_EQUAL);
        this.onData.setOnValueType(OnValueType.VALUE);
        this.onData.setValueCount(1);
        this.onData.setTargetValue(value);
        this.addModelData(this.onData);
        return this.handleModel;
    }

    @Override
    public MO betweenValue(Object value, Object secondValue, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.handleModel;
                case NOT_NULL:
                    throw new ComparisonException("join table alias [" + this.onData.getOwnerTableAlias() + "] column [" + this.onData.getOwnerColumnName() + "] between, the value can not be null.");
                default:
                    return null;
            }
        }
        if (secondValue == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.handleModel;
                case NOT_NULL:
                    throw new ComparisonException("join table alias [" + this.onData.getOwnerTableAlias() + "] column [" + this.onData.getOwnerColumnName() + "] between, the secondValue can not be null.");
                default:
                    return null;
            }
        }
        this.onData.setOnType(OnType.BETWEEN);
        this.onData.setOnValueType(OnValueType.VALUE);
        this.onData.setValueCount(2);
        this.onData.setTargetValue(value);
        this.onData.setTargetSecondValue(secondValue);
        this.addModelData(this.onData);
        return this.handleModel;
    }

    @Override
    public MO likeValue(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.handleModel;
                case NOT_NULL:
                    throw new ComparisonException("join table alias [" + this.onData.getOwnerTableAlias() + "] column [" + this.onData.getOwnerColumnName() + "] like, the value can not be null.");
                default:
                    return null;
            }
        }
        this.onData.setOnType(OnType.LIKE);
        this.onData.setOnValueType(OnValueType.VALUE);
        this.onData.setValueCount(1);
        this.onData.setTargetValue(value);
        this.addModelData(this.onData);
        return this.handleModel;
    }

    @Override
    public MO inValue(Object[] values, ComparisonRule comparisonRule) {
        if (values == null || values.length == 0) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.handleModel;
                case NOT_NULL:
                    throw new ComparisonException("join table alias [" + this.onData.getOwnerTableAlias() + "] column [" + this.onData.getOwnerColumnName() + "] in, the values can not be null or size = 0.");
                default:
                    return null;
            }
        }
        this.onData.setOnType(OnType.IN);
        this.onData.setOnValueType(OnValueType.VALUE);
        this.onData.setValueCount(values.length);
        this.onData.setTargetValue(values);
        this.addModelData(this.onData);
        return this.handleModel;
    }

    @Override
    public MO inValue(Collection<?> values, ComparisonRule comparisonRule) {
        if (values == null || values.size() == 0) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.handleModel;
                case NOT_NULL:
                    throw new ComparisonException("join table alias [" + this.onData.getOwnerTableAlias() + "] column [" + this.onData.getOwnerColumnName() + "] in, the values can not be null or size = 0.");
                default:
                    return null;
            }
        }
        this.onData.setOnType(OnType.IN);
        this.onData.setOnValueType(OnValueType.VALUE);
        this.onData.setValueCount(values.size());
        this.onData.setTargetValue(values);
        this.addModelData(this.onData);
        return this.handleModel;
    }

    @Override
    public MO notInValue(Object[] values, ComparisonRule comparisonRule) {
        if (values == null || values.length == 0) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.handleModel;
                case NOT_NULL:
                    throw new ComparisonException("join table alias [" + this.onData.getOwnerTableAlias() + "] column [" + this.onData.getOwnerColumnName() + "] in, the values can not be null or size = 0.");
                default:
                    return null;
            }
        }
        this.onData.setOnType(OnType.NOT_IN);
        this.onData.setOnValueType(OnValueType.VALUE);
        this.onData.setValueCount(values.length);
        this.onData.setTargetValue(values);
        this.addModelData(this.onData);
        return this.handleModel;
    }

    @Override
    public MO notInValue(Collection<?> values, ComparisonRule comparisonRule) {
        if (values == null || values.size() == 0) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.handleModel;
                case NOT_NULL:
                    throw new ComparisonException("join table alias [" + this.onData.getOwnerTableAlias() + "] column [" + this.onData.getOwnerColumnName() + "] in, the values can not be null or size = 0.");
                default:
                    return null;
            }
        }
        this.onData.setOnType(OnType.NOT_IN);
        this.onData.setOnValueType(OnValueType.VALUE);
        this.onData.setValueCount(values.size());
        this.onData.setTargetValue(values);
        this.addModelData(this.onData);
        return this.handleModel;
    }

    @Override
    public MO equalTo(OnDataBuilder onDataBuilder) {
        this.onData.setOnType(OnType.EQUAL);
        this.onData.setOnValueType(OnValueType.JOIN);
        OnData targetOnData = onDataBuilder.onData;
        this.onData.setTargetTableName(targetOnData.getOwnerTableName());
        this.onData.setTargetTableAlias(targetOnData.getOwnerTableAlias());
        this.onData.setTargetColumnName(targetOnData.getOwnerColumnName());
        this.addModelData(this.onData);
        return this.handleModel;
    }

    @Override
    public MO notEqualTo(OnDataBuilder onDataBuilder) {
        this.onData.setOnType(OnType.NOT_EQUAL);
        this.onData.setOnValueType(OnValueType.JOIN);
        OnData targetOnData = onDataBuilder.onData;
        this.onData.setTargetTableName(targetOnData.getOwnerTableName());
        this.onData.setTargetTableAlias(targetOnData.getOwnerTableAlias());
        this.onData.setTargetColumnName(targetOnData.getOwnerColumnName());
        this.addModelData(this.onData);
        return this.handleModel;
    }

    @Override
    public MO greaterThan(OnDataBuilder onDataBuilder) {
        this.onData.setOnType(OnType.GREATER);
        this.onData.setOnValueType(OnValueType.JOIN);
        OnData targetOnData = onDataBuilder.onData;
        this.onData.setTargetTableName(targetOnData.getOwnerTableName());
        this.onData.setTargetTableAlias(targetOnData.getOwnerTableAlias());
        this.onData.setTargetColumnName(targetOnData.getOwnerColumnName());
        this.addModelData(this.onData);
        return this.handleModel;
    }

    @Override
    public MO greaterThanAndEqualTo(OnDataBuilder onDataBuilder) {
        this.onData.setOnType(OnType.GREATER_EQUAL);
        this.onData.setOnValueType(OnValueType.JOIN);
        OnData targetOnData = onDataBuilder.onData;
        this.onData.setTargetTableName(targetOnData.getOwnerTableName());
        this.onData.setTargetTableAlias(targetOnData.getOwnerTableAlias());
        this.onData.setTargetColumnName(targetOnData.getOwnerColumnName());
        this.addModelData(this.onData);
        return this.handleModel;
    }

    @Override
    public MO lessThan(OnDataBuilder onDataBuilder) {
        this.onData.setOnType(OnType.LESS);
        this.onData.setOnValueType(OnValueType.JOIN);
        OnData targetOnData = onDataBuilder.onData;
        this.onData.setTargetTableName(targetOnData.getOwnerTableName());
        this.onData.setTargetTableAlias(targetOnData.getOwnerTableAlias());
        this.onData.setTargetColumnName(targetOnData.getOwnerColumnName());
        this.addModelData(this.onData);
        return this.handleModel;
    }

    @Override
    public MO lessThanAndEqualTo(OnDataBuilder onDataBuilder) {
        this.onData.setOnType(OnType.LESS_EQUAL);
        this.onData.setOnValueType(OnValueType.JOIN);
        OnData targetOnData = onDataBuilder.onData;
        this.onData.setTargetTableName(targetOnData.getOwnerTableName());
        this.onData.setTargetTableAlias(targetOnData.getOwnerTableAlias());
        this.onData.setTargetColumnName(targetOnData.getOwnerColumnName());
        this.addModelData(this.onData);
        return this.handleModel;
    }

    @Override
    public <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MO equalTo(Class<T> onClass, String alias, OnModelValue<T, TC, TO, TW, TS, TG> onModelValue) {
        this.onData.setOnType(OnType.EQUAL);
        this.onData.setOnValueType(OnValueType.JOIN);
        JoinTableData<T> joinTableData = this.handleModel.getSqlData().getJoinTableData(alias, onClass);
        TO onModel = joinTableData.getTableModel().getOnModel();
        OnData targetOnData = onModelValue.apply(onModel).onData;
        this.onData.setTargetTableName(targetOnData.getOwnerTableName());
        this.onData.setTargetTableAlias(joinTableData.getTableAlias());
        this.onData.setTargetColumnName(targetOnData.getOwnerColumnName());
        this.addModelData(this.onData);
        return this.handleModel;
    }

    @Override
    public <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MO notEqualTo(Class<T> onClass, String alias, OnModelValue<T, TC, TO, TW, TS, TG> onModelValue) {
        this.onData.setOnType(OnType.NOT_EQUAL);
        this.onData.setOnValueType(OnValueType.JOIN);
        JoinTableData<T> joinTableData = this.handleModel.getSqlData().getJoinTableData(alias, onClass);
        TO onModel = joinTableData.getTableModel().getOnModel();
        OnData targetOnData = onModelValue.apply(onModel).onData;
        this.onData.setTargetTableName(targetOnData.getOwnerTableName());
        this.onData.setTargetTableAlias(joinTableData.getTableAlias());
        this.onData.setTargetColumnName(targetOnData.getOwnerColumnName());
        this.addModelData(this.onData);
        return this.handleModel;
    }

    @Override
    public <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MO greaterThan(Class<T> onClass, String alias, OnModelValue<T, TC, TO, TW, TS, TG> onModelValue) {
        this.onData.setOnType(OnType.GREATER);
        this.onData.setOnValueType(OnValueType.JOIN);
        JoinTableData<T> joinTableData = this.handleModel.getSqlData().getJoinTableData(alias, onClass);
        TO onModel = joinTableData.getTableModel().getOnModel();
        OnData targetOnData = onModelValue.apply(onModel).onData;
        this.onData.setTargetTableName(targetOnData.getOwnerTableName());
        this.onData.setTargetTableAlias(joinTableData.getTableAlias());
        this.onData.setTargetColumnName(targetOnData.getOwnerColumnName());
        this.addModelData(this.onData);
        return this.handleModel;
    }

    @Override
    public <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MO greaterThanAndEqualTo(Class<T> onClass, String alias, OnModelValue<T, TC, TO, TW, TS, TG> onModelValue) {
        this.onData.setOnType(OnType.GREATER_EQUAL);
        this.onData.setOnValueType(OnValueType.JOIN);
        JoinTableData<T> joinTableData = this.handleModel.getSqlData().getJoinTableData(alias, onClass);
        TO onModel = joinTableData.getTableModel().getOnModel();
        OnData targetOnData = onModelValue.apply(onModel).onData;
        this.onData.setTargetTableName(targetOnData.getOwnerTableName());
        this.onData.setTargetTableAlias(joinTableData.getTableAlias());
        this.onData.setTargetColumnName(targetOnData.getOwnerColumnName());
        this.addModelData(this.onData);
        return this.handleModel;
    }

    @Override
    public <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MO lessThan(Class<T> onClass, String alias, OnModelValue<T, TC, TO, TW, TS, TG> onModelValue) {
        this.onData.setOnType(OnType.LESS);
        this.onData.setOnValueType(OnValueType.JOIN);
        JoinTableData<T> joinTableData = this.handleModel.getSqlData().getJoinTableData(alias, onClass);
        TO onModel = joinTableData.getTableModel().getOnModel();
        OnData targetOnData = onModelValue.apply(onModel).onData;
        this.onData.setTargetTableName(targetOnData.getOwnerTableName());
        this.onData.setTargetTableAlias(joinTableData.getTableAlias());
        this.onData.setTargetColumnName(targetOnData.getOwnerColumnName());
        this.addModelData(this.onData);
        return this.handleModel;
    }

    @Override
    public <T extends Model<T, TC, TO, TW, TS, TG>,
            TC extends ColumnModel<T, TC, TO, TW, TS, TG>,
            TO extends OnModel<T, TC, TO, TW, TS, TG>,
            TW extends WhereModel<T, TC, TO, TW, TS, TG>,
            TS extends SortModel<T, TC, TO, TW, TS, TG>,
            TG extends GroupModel<T, TC, TO, TW, TS, TG>> MO lessThanAndEqualTo(Class<T> onClass, String alias, OnModelValue<T, TC, TO, TW, TS, TG> onModelValue) {
        this.onData.setOnType(OnType.LESS_EQUAL);
        this.onData.setOnValueType(OnValueType.JOIN);
        JoinTableData<T> joinTableData = this.handleModel.getSqlData().getJoinTableData(alias, onClass);
        TO onModel = joinTableData.getTableModel().getOnModel();
        OnData targetOnData = onModelValue.apply(onModel).onData;
        this.onData.setTargetTableName(targetOnData.getOwnerTableName());
        this.onData.setTargetTableAlias(joinTableData.getTableAlias());
        this.onData.setTargetColumnName(targetOnData.getOwnerColumnName());
        this.addModelData(this.onData);
        return this.handleModel;
    }

}