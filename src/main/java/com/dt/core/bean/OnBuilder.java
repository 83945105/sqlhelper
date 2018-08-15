package com.dt.core.bean;

import com.dt.core.data.JoinTableData;
import com.dt.core.data.OnData;
import com.dt.core.exception.ComparisonException;
import com.dt.core.norm.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * On条件构建器
 *
 * @author 白超
 * @version 1.0
 * @see ComparisonOperator
 * @see OnComparisonOperator
 * @since 2018/7/10
 */
public final class OnBuilder<M extends Model<M, ML, MO, MC, MS, MG>,
        ML extends ColumnModel<M, ML, MO, MC, MS, MG>,
        MO extends OnModel<M, ML, MO, MC, MS, MG>,
        MC extends WhereModel<M, ML, MO, MC, MS, MG>,
        MS extends SortModel<M, ML, MO, MC, MS, MG>,
        MG extends GroupModel<M, ML, MO, MC, MS, MG>> implements ComparisonOperator<MO>, OnComparisonOperator<M, ML, MO, MC, MS, MG> {

    /**
     * 当然操作的模组
     */
    private MO handleModel;

    public OnBuilder(MO handleModel) {
        this.handleModel = handleModel;
    }

    /**
     * 待构建的OnData
     */
    private OnData onData;

    private List<OnData> onDataList = new ArrayList<>();

    /**
     * 用于新建OnData并预先存于一部分信息
     *
     * @param ownerTableName  所属表名
     * @param ownerTableAlias 所属表别名
     * @param ownerColumnName 所属字段名
     * @return 当前On条件构建起 {@link OnBuilder}
     */
    public OnBuilder<M, ML, MO, MC, MS, MG> handler(String ownerTableName, String ownerTableAlias, String ownerColumnName) {
        this.onData = new OnData();
        this.onData.setOwnerTableName(ownerTableName);
        this.onData.setOwnerTableAlias(ownerTableAlias);
        this.onData.setOwnerColumnName(ownerColumnName);
        return this;
    }

    @Override
    public MO isNull() {
        this.onData.setOnType(OnType.IS_NULL);
        this.onData.setOnValueType(OnValueType.VALUE);
        this.onData.setValueCount(0);
        this.onDataList.add(onData);
        return this.handleModel;
    }

    @Override
    public MO isNotNull() {
        this.onData.setOnType(OnType.IS_NOT_NULL);
        this.onData.setOnValueType(OnValueType.VALUE);
        this.onData.setValueCount(0);
        this.onDataList.add(this.onData);
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
        this.onDataList.add(this.onData);
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
        this.onDataList.add(this.onData);
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
        this.onDataList.add(this.onData);
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
        this.onDataList.add(this.onData);
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
        this.onDataList.add(this.onData);
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
        this.onDataList.add(this.onData);
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
        this.onDataList.add(this.onData);
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
        this.onDataList.add(this.onData);
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
        this.onDataList.add(this.onData);
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
        this.onDataList.add(this.onData);
        return this.handleModel;
    }

    @Override
    public MO equalTo(OnBuilder onBuilder) {
        this.onData.setOnType(OnType.EQUAL);
        this.onData.setOnValueType(OnValueType.JOIN);
        OnData targetOnData = onBuilder.onData;
        this.onData.setTargetTableName(targetOnData.getOwnerTableName());
        this.onData.setTargetAlias(targetOnData.getOwnerTableAlias());
        this.onData.setTargetColumnName(targetOnData.getOwnerColumnName());
        this.onDataList.add(this.onData);
        return this.handleModel;
    }

    @Override
    public MO notEqualTo(OnBuilder onBuilder) {
        this.onData.setOnType(OnType.NOT_EQUAL);
        this.onData.setOnValueType(OnValueType.JOIN);
        OnData targetOnData = onBuilder.onData;
        this.onData.setTargetTableName(targetOnData.getOwnerTableName());
        this.onData.setTargetAlias(targetOnData.getOwnerTableAlias());
        this.onData.setTargetColumnName(targetOnData.getOwnerColumnName());
        this.onDataList.add(this.onData);
        return this.handleModel;
    }

    @Override
    public MO greaterThan(OnBuilder onBuilder) {
        this.onData.setOnType(OnType.GREATER);
        this.onData.setOnValueType(OnValueType.JOIN);
        OnData targetOnData = onBuilder.onData;
        this.onData.setTargetTableName(targetOnData.getOwnerTableName());
        this.onData.setTargetAlias(targetOnData.getOwnerTableAlias());
        this.onData.setTargetColumnName(targetOnData.getOwnerColumnName());
        this.onDataList.add(this.onData);
        return this.handleModel;
    }

    @Override
    public MO greaterThanAndEqualTo(OnBuilder onBuilder) {
        this.onData.setOnType(OnType.GREATER_EQUAL);
        this.onData.setOnValueType(OnValueType.JOIN);
        OnData targetOnData = onBuilder.onData;
        this.onData.setTargetTableName(targetOnData.getOwnerTableName());
        this.onData.setTargetAlias(targetOnData.getOwnerTableAlias());
        this.onData.setTargetColumnName(targetOnData.getOwnerColumnName());
        this.onDataList.add(this.onData);
        return this.handleModel;
    }

    @Override
    public MO lessThan(OnBuilder onBuilder) {
        this.onData.setOnType(OnType.LESS);
        this.onData.setOnValueType(OnValueType.JOIN);
        OnData targetOnData = onBuilder.onData;
        this.onData.setTargetTableName(targetOnData.getOwnerTableName());
        this.onData.setTargetAlias(targetOnData.getOwnerTableAlias());
        this.onData.setTargetColumnName(targetOnData.getOwnerColumnName());
        this.onDataList.add(this.onData);
        return this.handleModel;
    }

    @Override
    public MO lessThanAndEqualTo(OnBuilder onBuilder) {
        this.onData.setOnType(OnType.LESS_EQUAL);
        this.onData.setOnValueType(OnValueType.JOIN);
        OnData targetOnData = onBuilder.onData;
        this.onData.setTargetTableName(targetOnData.getOwnerTableName());
        this.onData.setTargetAlias(targetOnData.getOwnerTableAlias());
        this.onData.setTargetColumnName(targetOnData.getOwnerColumnName());
        this.onDataList.add(this.onData);
        return this.handleModel;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> MO equalTo(String alias,
                                                                     Class<T> onClass,
                                                                     OnB<T, TL, TO, TC, TS, TG> on) {
        this.onData.setOnType(OnType.EQUAL);
        this.onData.setOnValueType(OnValueType.JOIN);
        JoinTableData<T, TL, TO, TC, TS, TG> joinTableData = this.handleModel.getData().getJoinTableData(alias, onClass);
        TO to = (TO) joinTableData.getTable().getOn();
        OnData targetOnData = on.apply(to).onData;
        this.onData.setTargetTableName(targetOnData.getOwnerTableName());
        this.onData.setTargetAlias(joinTableData.getTableAlias());
        this.onData.setTargetColumnName(targetOnData.getOwnerColumnName());
        this.onDataList.add(this.onData);
        return this.handleModel;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> MO notEqualTo(String alias, Class<T> onClass, OnB<T, TL, TO, TC, TS, TG> on) {
        this.onData.setOnType(OnType.NOT_EQUAL);
        this.onData.setOnValueType(OnValueType.JOIN);
        JoinTableData<T, TL, TO, TC, TS, TG> joinTableData = this.handleModel.getData().getJoinTableData(alias, onClass);
        TO to = (TO) joinTableData.getTable().getOn();
        OnData targetOnData = on.apply(to).onData;
        this.onData.setTargetTableName(targetOnData.getOwnerTableName());
        this.onData.setTargetAlias(joinTableData.getTableAlias());
        this.onData.setTargetColumnName(targetOnData.getOwnerColumnName());
        this.onDataList.add(this.onData);
        return this.handleModel;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> MO greaterThan(String alias, Class<T> onClass, OnB<T, TL, TO, TC, TS, TG> on) {
        this.onData.setOnType(OnType.GREATER);
        this.onData.setOnValueType(OnValueType.JOIN);
        JoinTableData<T, TL, TO, TC, TS, TG> joinTableData = this.handleModel.getData().getJoinTableData(alias, onClass);
        TO to = (TO) joinTableData.getTable().getOn();
        OnData targetOnData = on.apply(to).onData;
        this.onData.setTargetTableName(targetOnData.getOwnerTableName());
        this.onData.setTargetAlias(joinTableData.getTableAlias());
        this.onData.setTargetColumnName(targetOnData.getOwnerColumnName());
        this.onDataList.add(this.onData);
        return this.handleModel;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> MO greaterThanAndEqualTo(String alias, Class<T> onClass, OnB<T, TL, TO, TC, TS, TG> on) {
        this.onData.setOnType(OnType.GREATER_EQUAL);
        this.onData.setOnValueType(OnValueType.JOIN);
        JoinTableData<T, TL, TO, TC, TS, TG> joinTableData = this.handleModel.getData().getJoinTableData(alias, onClass);
        TO to = (TO) joinTableData.getTable().getOn();
        OnData targetOnData = on.apply(to).onData;
        this.onData.setTargetTableName(targetOnData.getOwnerTableName());
        this.onData.setTargetAlias(joinTableData.getTableAlias());
        this.onData.setTargetColumnName(targetOnData.getOwnerColumnName());
        this.onDataList.add(this.onData);
        return this.handleModel;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> MO lessThan(String alias, Class<T> onClass, OnB<T, TL, TO, TC, TS, TG> on) {
        this.onData.setOnType(OnType.LESS);
        this.onData.setOnValueType(OnValueType.JOIN);
        JoinTableData<T, TL, TO, TC, TS, TG> joinTableData = this.handleModel.getData().getJoinTableData(alias, onClass);
        TO to = (TO) joinTableData.getTable().getOn();
        OnData targetOnData = on.apply(to).onData;
        this.onData.setTargetTableName(targetOnData.getOwnerTableName());
        this.onData.setTargetAlias(joinTableData.getTableAlias());
        this.onData.setTargetColumnName(targetOnData.getOwnerColumnName());
        this.onDataList.add(this.onData);
        return this.handleModel;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Model<T, TL, TO, TC, TS, TG>,
            TL extends ColumnModel<T, TL, TO, TC, TS, TG>,
            TO extends OnModel<T, TL, TO, TC, TS, TG>,
            TC extends WhereModel<T, TL, TO, TC, TS, TG>,
            TS extends SortModel<T, TL, TO, TC, TS, TG>,
            TG extends GroupModel<T, TL, TO, TC, TS, TG>> MO lessThanAndEqualTo(String alias, Class<T> onClass, OnB<T, TL, TO, TC, TS, TG> on) {
        this.onData.setOnType(OnType.LESS_EQUAL);
        this.onData.setOnValueType(OnValueType.JOIN);
        JoinTableData<T, TL, TO, TC, TS, TG> joinTableData = this.handleModel.getData().getJoinTableData(alias, onClass);
        TO to = (TO) joinTableData.getTable().getOn();
        OnData targetOnData = on.apply(to).onData;
        this.onData.setTargetTableName(targetOnData.getOwnerTableName());
        this.onData.setTargetAlias(joinTableData.getTableAlias());
        this.onData.setTargetColumnName(targetOnData.getOwnerColumnName());
        this.onDataList.add(this.onData);
        return this.handleModel;
    }

    /**
     * 获取OnData集合
     *
     * @return ArrayList {@link ArrayList}
     */
    public List<OnData> getOnDataList() {
        return this.onDataList;
    }
}
