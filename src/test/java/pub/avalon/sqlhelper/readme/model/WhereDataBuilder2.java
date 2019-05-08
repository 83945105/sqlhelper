package pub.avalon.sqlhelper.readme.model;

import pub.avalon.sqlhelper.core.beans.ComparisonRule;
import pub.avalon.sqlhelper.core.beans.WhereType;
import pub.avalon.sqlhelper.core.beans.WhereValueType;
import pub.avalon.sqlhelper.core.builder.AbstractModelDataBuilder;
import pub.avalon.sqlhelper.core.data.WhereDatum;
import pub.avalon.sqlhelper.core.exception.ComparisonException;
import pub.avalon.sqlhelper.core.norm.ComparisonOperator;

import java.util.Collection;

/**
 * 条件构建器
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class WhereDataBuilder2<M extends Model2<M>> extends AbstractModelDataBuilder<WhereDataBuilder2, WhereDatum> implements ComparisonOperator<M> {

    private M handleModel;

    public WhereDataBuilder2(M handleModel) {
        this.handleModel = handleModel;
    }

    protected WhereDatum whereDatum;

    @Override
    public WhereDataBuilder2 apply(String tableName, String tableAlias, String columnName, String columnAlias) {
        this.whereDatum = new WhereDatum();
        this.whereDatum.setOwnerTableName(tableName);
        this.whereDatum.setOwnerTableAlias(tableAlias);
        this.whereDatum.setOwnerColumnName(columnName);
        return this;
    }

    /**
     * sql片段
     * 直接写入sql片段
     *
     * @param sqlPart
     * @return
     */
    public M sqlPart(String sqlPart) {
        this.whereDatum.setWhereValueType(WhereValueType.SQL_PART);
        this.whereDatum.setSqlPart(sqlPart);
        this.addModelData(this.whereDatum);
        return this.handleModel;
    }

    @Override
    public M isNull() {
        this.whereDatum.setWhereType(WhereType.IS_NULL);
        this.whereDatum.setValueCount(0);
        this.addModelData(this.whereDatum);
        return this.handleModel;
    }

    @Override
    public M isNotNull() {
        this.whereDatum.setWhereType(WhereType.IS_NOT_NULL);
        this.whereDatum.setValueCount(0);
        this.addModelData(this.whereDatum);
        return this.handleModel;
    }

    @Override
    public M equalToValue(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.handleModel;
                case NOT_NULL:
                    throw new ComparisonException("table alias [" + this.whereDatum.getOwnerTableAlias() + "] column [" + this.whereDatum.getOwnerColumnName() + "] equalTo, the value can not be null.");
                default:
                    return null;
            }
        }
        this.whereDatum.setWhereType(WhereType.EQUAL);
        this.whereDatum.setValueCount(1);
        this.whereDatum.setTargetValue(value);
        this.addModelData(this.whereDatum);
        return this.handleModel;
    }

    @Override
    public M notEqualToValue(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.handleModel;
                case NOT_NULL:
                    throw new ComparisonException("table alias [" + this.whereDatum.getOwnerTableAlias() + "] column [" + this.whereDatum.getOwnerColumnName() + "] notEqualTo, the value can not be null.");
                default:
                    return null;
            }
        }
        this.whereDatum.setWhereType(WhereType.NOT_EQUAL);
        this.whereDatum.setValueCount(1);
        this.whereDatum.setTargetValue(value);
        this.addModelData(this.whereDatum);
        return this.handleModel;
    }

    @Override
    public M greaterThanValue(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.handleModel;
                case NOT_NULL:
                    throw new ComparisonException("table alias [" + this.whereDatum.getOwnerTableAlias() + "] column [" + this.whereDatum.getOwnerColumnName() + "] greaterThan, the value can not be null.");
                default:
                    return null;
            }
        }
        this.whereDatum.setWhereType(WhereType.GREATER);
        this.whereDatum.setValueCount(1);
        this.whereDatum.setTargetValue(value);
        this.addModelData(this.whereDatum);
        return this.handleModel;
    }

    @Override
    public M greaterThanAndEqualToValue(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.handleModel;
                case NOT_NULL:
                    throw new ComparisonException("table alias [" + this.whereDatum.getOwnerTableAlias() + "] column [" + this.whereDatum.getOwnerColumnName() + "] greaterThanAndEqualTo, the value can not be null.");
                default:
                    return null;
            }
        }
        this.whereDatum.setWhereType(WhereType.GREATER_EQUAL);
        this.whereDatum.setValueCount(1);
        this.whereDatum.setTargetValue(value);
        this.addModelData(this.whereDatum);
        return this.handleModel;
    }

    @Override
    public M lessThanValue(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.handleModel;
                case NOT_NULL:
                    throw new ComparisonException("table alias [" + this.whereDatum.getOwnerTableAlias() + "] column [" + this.whereDatum.getOwnerColumnName() + "] lessThan, the value can not be null.");
                default:
                    return null;
            }
        }
        this.whereDatum.setWhereType(WhereType.LESS);
        this.whereDatum.setValueCount(1);
        this.whereDatum.setTargetValue(value);
        this.addModelData(this.whereDatum);
        return this.handleModel;
    }

    @Override
    public M lessThanAndEqualToValue(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.handleModel;
                case NOT_NULL:
                    throw new ComparisonException("table alias [" + this.whereDatum.getOwnerTableAlias() + "] column [" + this.whereDatum.getOwnerColumnName() + "] lessThanAndEqualTo, the value can not be null.");
                default:
                    return null;
            }
        }
        this.whereDatum.setWhereType(WhereType.LESS_EQUAL);
        this.whereDatum.setValueCount(1);
        this.whereDatum.setTargetValue(value);
        this.addModelData(this.whereDatum);
        return this.handleModel;
    }

    @Override
    public M betweenValue(Object value, Object secondValue, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.handleModel;
                case NOT_NULL:
                    throw new ComparisonException("table alias [" + this.whereDatum.getOwnerTableAlias() + "] column [" + this.whereDatum.getOwnerColumnName() + "] between, the value can not be null.");
                default:
                    return null;
            }
        }
        if (secondValue == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.handleModel;
                case NOT_NULL:
                    throw new ComparisonException("table alias [" + this.whereDatum.getOwnerTableAlias() + "] column [" + this.whereDatum.getOwnerColumnName() + "] between, the secondValue can not be null.");
                default:
                    return null;
            }
        }
        this.whereDatum.setWhereType(WhereType.BETWEEN);
        this.whereDatum.setValueCount(2);
        this.whereDatum.setTargetValue(value);
        this.whereDatum.setTargetSecondValue(secondValue);
        this.addModelData(this.whereDatum);
        return this.handleModel;
    }

    @Override
    public M likeValue(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.handleModel;
                case NOT_NULL:
                    throw new ComparisonException("table alias [" + this.whereDatum.getOwnerTableAlias() + "] column [" + this.whereDatum.getOwnerColumnName() + "] like, the value can not be null.");
                default:
                    return null;
            }
        }
        this.whereDatum.setWhereType(WhereType.LIKE);
        this.whereDatum.setValueCount(1);
        this.whereDatum.setTargetValue(value);
        this.addModelData(this.whereDatum);
        return this.handleModel;
    }

    @Override
    public M inValue(Object[] values, ComparisonRule comparisonRule) {
        if (values == null || values.length == 0) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.handleModel;
                case NOT_NULL:
                    throw new ComparisonException("table alias [" + this.whereDatum.getOwnerTableAlias() + "] column [" + this.whereDatum.getOwnerColumnName() + "] in, the values can not be null or size = 0.");
                default:
                    return null;
            }
        }
        this.whereDatum.setWhereType(WhereType.IN);
        this.whereDatum.setValueCount(values.length);
        this.whereDatum.setTargetValue(values);
        this.addModelData(this.whereDatum);
        return this.handleModel;
    }

    @Override
    public M inValue(Collection<?> values, ComparisonRule comparisonRule) {
        if (values == null || values.size() == 0) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.handleModel;
                case NOT_NULL:
                    throw new ComparisonException("table alias [" + this.whereDatum.getOwnerTableAlias() + "] column [" + this.whereDatum.getOwnerColumnName() + "] in, the values can not be null or size = 0.");
                default:
                    return null;
            }
        }
        this.whereDatum.setWhereType(WhereType.IN);
        this.whereDatum.setValueCount(values.size());
        this.whereDatum.setTargetValue(values);
        this.addModelData(this.whereDatum);
        return this.handleModel;
    }

    @Override
    public M notInValue(Object[] values, ComparisonRule comparisonRule) {
        if (values == null || values.length == 0) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.handleModel;
                case NOT_NULL:
                    throw new ComparisonException("table alias [" + this.whereDatum.getOwnerTableAlias() + "] column [" + this.whereDatum.getOwnerColumnName() + "] in, the values can not be null or size = 0.");
                default:
                    return null;
            }
        }
        this.whereDatum.setWhereType(WhereType.NOT_IN);
        this.whereDatum.setValueCount(values.length);
        this.whereDatum.setTargetValue(values);
        this.addModelData(this.whereDatum);
        return this.handleModel;
    }

    @Override
    public M notInValue(Collection<?> values, ComparisonRule comparisonRule) {
        if (values == null || values.size() == 0) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.handleModel;
                case NOT_NULL:
                    throw new ComparisonException("table alias [" + this.whereDatum.getOwnerTableAlias() + "] column [" + this.whereDatum.getOwnerColumnName() + "] in, the values can not be null or size = 0.");
                default:
                    return null;
            }
        }
        this.whereDatum.setWhereType(WhereType.NOT_IN);
        this.whereDatum.setValueCount(values.size());
        this.whereDatum.setTargetValue(values);
        this.addModelData(this.whereDatum);
        return this.handleModel;
    }

}
