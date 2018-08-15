package com.dt.core.bean;

import com.dt.core.data.AbstractTableData;
import com.dt.core.data.WhereData;
import com.dt.core.exception.ComparisonException;
import com.dt.core.norm.ComparisonOperator;
import com.dt.core.norm.Model;

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
public final class WhereBuilder<C extends Model<C, CL, CO, CC, CS, CG>,
        CL extends ColumnModel<C, CL, CO, CC, CS, CG>,
        CO extends OnModel<C, CL, CO, CC, CS, CG>,
        CC extends WhereModel<C, CL, CO, CC, CS, CG>,
        CS extends SortModel<C, CL, CO, CC, CS, CG>,
        CG extends GroupModel<C, CL, CO, CC, CS, CG>> implements ComparisonOperator<CC> {

    private CC handleModel;

    public WhereBuilder(CC handleModel) {
        this.handleModel = handleModel;
    }

    private WhereData whereData;

    private List<WhereData> whereDataList = new ArrayList<>();

    public List<WhereData> getWhereDataList() {
        List<WhereData> whereDataList = this.whereDataList;
        /**
         * 每次取走whereDataList,重置集合
         */
        this.whereDataList = new ArrayList<>();
        return whereDataList;
    }

    private AbstractTableData ownerTableData;

    public WhereBuilder<C, CL, CO, CC, CS, CG> handler(String ownerTableName, String ownerAlias, String ownerColumnName) {
        this.whereData = new WhereData();
        this.whereData.setOwnerTableName(ownerTableName);
        this.whereData.setOwnerAlias(ownerAlias);
        this.whereData.setOwnerColumnName(ownerColumnName);
        this.whereData.setOwnerAlias(this.ownerTableData.getTableAlias());
        return this;
    }

    @Override
    public CC isNull() {
        this.whereData.setWhereType(WhereType.IS_NULL);
        this.whereData.setValueCount(0);
        this.whereDataList.add(this.whereData);
        return this.handleModel;
    }

    @Override
    public CC isNotNull() {
        this.whereData.setWhereType(WhereType.IS_NOT_NULL);
        this.whereData.setValueCount(0);
        this.whereDataList.add(this.whereData);
        return this.handleModel;
    }

    @Override
    public CC equalToValue(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.handleModel;
                case NOT_NULL:
                    throw new ComparisonException("table alias [" + this.whereData.getOwnerAlias() + "] column [" + this.whereData.getOwnerColumnName() + "] equalTo, the value can not be null.");
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
    public CC notEqualToValue(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.handleModel;
                case NOT_NULL:
                    throw new ComparisonException("table alias [" + this.whereData.getOwnerAlias() + "] column [" + this.whereData.getOwnerColumnName() + "] notEqualTo, the value can not be null.");
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
    public CC greaterThanValue(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.handleModel;
                case NOT_NULL:
                    throw new ComparisonException("table alias [" + this.whereData.getOwnerAlias() + "] column [" + this.whereData.getOwnerColumnName() + "] greaterThan, the value can not be null.");
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
    public CC greaterThanAndEqualToValue(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.handleModel;
                case NOT_NULL:
                    throw new ComparisonException("table alias [" + this.whereData.getOwnerAlias() + "] column [" + this.whereData.getOwnerColumnName() + "] greaterThanAndEqualTo, the value can not be null.");
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
    public CC lessThanValue(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.handleModel;
                case NOT_NULL:
                    throw new ComparisonException("table alias [" + this.whereData.getOwnerAlias() + "] column [" + this.whereData.getOwnerColumnName() + "] lessThan, the value can not be null.");
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
    public CC lessThanAndEqualToValue(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.handleModel;
                case NOT_NULL:
                    throw new ComparisonException("table alias [" + this.whereData.getOwnerAlias() + "] column [" + this.whereData.getOwnerColumnName() + "] lessThanAndEqualTo, the value can not be null.");
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
    public CC betweenValue(Object value, Object secondValue, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.handleModel;
                case NOT_NULL:
                    throw new ComparisonException("table alias [" + this.whereData.getOwnerAlias() + "] column [" + this.whereData.getOwnerColumnName() + "] between, the value can not be null.");
                default:
                    return null;
            }
        }
        if (secondValue == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.handleModel;
                case NOT_NULL:
                    throw new ComparisonException("table alias [" + this.whereData.getOwnerAlias() + "] column [" + this.whereData.getOwnerColumnName() + "] between, the secondValue can not be null.");
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
    public CC likeValue(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.handleModel;
                case NOT_NULL:
                    throw new ComparisonException("table alias [" + this.whereData.getOwnerAlias() + "] column [" + this.whereData.getOwnerColumnName() + "] like, the value can not be null.");
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
    public CC inValue(Object[] values, ComparisonRule comparisonRule) {
        if (values == null || values.length == 0) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.handleModel;
                case NOT_NULL:
                    throw new ComparisonException("table alias [" + this.whereData.getOwnerAlias() + "] column [" + this.whereData.getOwnerColumnName() + "] in, the values can not be null or size = 0.");
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
    public CC inValue(Collection<?> values, ComparisonRule comparisonRule) {
        if (values == null || values.size() == 0) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.handleModel;
                case NOT_NULL:
                    throw new ComparisonException("table alias [" + this.whereData.getOwnerAlias() + "] column [" + this.whereData.getOwnerColumnName() + "] in, the values can not be null or size = 0.");
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

    public void setOwnerTableData(AbstractTableData ownerTableData) {
        this.ownerTableData = ownerTableData;
    }
}
