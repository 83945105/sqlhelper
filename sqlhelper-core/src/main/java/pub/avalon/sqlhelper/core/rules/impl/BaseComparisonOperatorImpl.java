package pub.avalon.sqlhelper.core.rules.impl;

import pub.avalon.sqlhelper.core.beans.ComparisonRule;
import pub.avalon.sqlhelper.core.data.AbstractComparisonSqlPartDatum;
import pub.avalon.sqlhelper.core.data.ComparisonType;
import pub.avalon.sqlhelper.core.data.SqlPartDatum;
import pub.avalon.sqlhelper.core.helper.Helper;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;
import pub.avalon.sqlhelper.core.rules.BaseComparisonOperator;
import pub.avalon.sqlhelper.core.utils.ExceptionUtils;

import java.util.Collection;

/**
 * @author baichao
 */
public interface BaseComparisonOperatorImpl<T, S extends AbstractComparisonSqlPartDatum<S>> extends BaseComparisonOperator<T> {

    /**
     * get helper
     *
     * @return extends {@link Helper} object
     */
    T getHelper();

    /**
     * get abstract comparison sql part datum
     *
     * @return extends {@link AbstractComparisonSqlPartDatum}
     */
    AbstractComparisonSqlPartDatum<S> getAbstractComparisonSqlPartDatum();

    /**
     * get sql builder options
     *
     * @return {@link SqlBuilderOptions}
     */
    SqlBuilderOptions getSqlBuilderOptions();

    /**
     * add sql part datum
     *
     * @param sqlPartDatum implements {@link SqlPartDatum} object
     */
    void addSqlPartDatum(S sqlPartDatum);

    @Override
    default ComparisonRule getDefaultComparisonRule() {
        return this.getSqlBuilderOptions().getSqlPartDatumBuilderOptions().getDefaultWhereComparisonRule();
    }

    @Override
    default T sqlPart(String targetSqlPart) {
        this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum()
                .setTargetSqlPart(targetSqlPart));
        return this.getHelper();
    }

    @Override
    default T isNull() {
        this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetNoneValue(ComparisonType.IS_NULL));
        return this.getHelper();
    }

    @Override
    default T isNotNull() {
        this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetNoneValue(ComparisonType.IS_NOT_NULL));
        return this.getHelper();
    }

    @Override
    default T equalTo(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    ExceptionUtils.comparisonRuleNullException(BaseComparisonOperatorImpl.class, "equalTo", this.getAbstractComparisonSqlPartDatum().getTableName(), this.getAbstractComparisonSqlPartDatum().getTableAlias(), this.getAbstractComparisonSqlPartDatum().getColumnName(), this.getAbstractComparisonSqlPartDatum().getColumnAlias());
                    break;
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
            }
        }
        this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSingleValue(ComparisonType.EQUAL, value));
        return this.getHelper();
    }

    @Override
    default T notEqualTo(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    ExceptionUtils.comparisonRuleNullException(BaseComparisonOperatorImpl.class, "notEqualTo", this.getAbstractComparisonSqlPartDatum().getTableName(), this.getAbstractComparisonSqlPartDatum().getTableAlias(), this.getAbstractComparisonSqlPartDatum().getColumnName(), this.getAbstractComparisonSqlPartDatum().getColumnAlias());
                    break;
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
            }
        }
        this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSingleValue(ComparisonType.NOT_EQUAL, value));
        return this.getHelper();
    }

    @Override
    default T greaterThan(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    ExceptionUtils.comparisonRuleNullException(BaseComparisonOperatorImpl.class, "greaterThan", this.getAbstractComparisonSqlPartDatum().getTableName(), this.getAbstractComparisonSqlPartDatum().getTableAlias(), this.getAbstractComparisonSqlPartDatum().getColumnName(), this.getAbstractComparisonSqlPartDatum().getColumnAlias());
                    break;
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
            }
        }
        this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSingleValue(ComparisonType.GREATER, value));
        return this.getHelper();
    }

    @Override
    default T greaterThanAndEqualTo(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    ExceptionUtils.comparisonRuleNullException(BaseComparisonOperatorImpl.class, "greaterThanAndEqualTo", this.getAbstractComparisonSqlPartDatum().getTableName(), this.getAbstractComparisonSqlPartDatum().getTableAlias(), this.getAbstractComparisonSqlPartDatum().getColumnName(), this.getAbstractComparisonSqlPartDatum().getColumnAlias());
                    break;
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
            }
        }
        this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSingleValue(ComparisonType.GREATER_EQUAL, value));
        return this.getHelper();
    }

    @Override
    default T lessThan(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    ExceptionUtils.comparisonRuleNullException(BaseComparisonOperatorImpl.class, "lessThan", this.getAbstractComparisonSqlPartDatum().getTableName(), this.getAbstractComparisonSqlPartDatum().getTableAlias(), this.getAbstractComparisonSqlPartDatum().getColumnName(), this.getAbstractComparisonSqlPartDatum().getColumnAlias());
                    break;
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
            }
        }
        this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSingleValue(ComparisonType.LESS, value));
        return this.getHelper();
    }

    @Override
    default T lessThanAndEqualTo(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    ExceptionUtils.comparisonRuleNullException(BaseComparisonOperatorImpl.class, "lessThanAndEqualTo", this.getAbstractComparisonSqlPartDatum().getTableName(), this.getAbstractComparisonSqlPartDatum().getTableAlias(), this.getAbstractComparisonSqlPartDatum().getColumnName(), this.getAbstractComparisonSqlPartDatum().getColumnAlias());
                    break;
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
            }
        }
        this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSingleValue(ComparisonType.LESS_EQUAL, value));
        return this.getHelper();
    }

    @Override
    default T between(Object value, Object secondValue, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    ExceptionUtils.comparisonRuleNullException(BaseComparisonOperatorImpl.class, "between", this.getAbstractComparisonSqlPartDatum().getTableName(), this.getAbstractComparisonSqlPartDatum().getTableAlias(), this.getAbstractComparisonSqlPartDatum().getColumnName(), this.getAbstractComparisonSqlPartDatum().getColumnAlias());
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
                    ExceptionUtils.comparisonRuleNullException(BaseComparisonOperatorImpl.class, "secondValue", this.getAbstractComparisonSqlPartDatum().getTableName(), this.getAbstractComparisonSqlPartDatum().getTableAlias(), this.getAbstractComparisonSqlPartDatum().getColumnName(), this.getAbstractComparisonSqlPartDatum().getColumnAlias());
                    break;
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
            }
        }
        this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetPairValue(ComparisonType.BETWEEN, value, secondValue));
        return this.getHelper();
    }

    @Override
    default T like(Object value, ComparisonRule comparisonRule) {
        if (value == null) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    ExceptionUtils.comparisonRuleNullException(BaseComparisonOperatorImpl.class, "like", this.getAbstractComparisonSqlPartDatum().getTableName(), this.getAbstractComparisonSqlPartDatum().getTableAlias(), this.getAbstractComparisonSqlPartDatum().getColumnName(), this.getAbstractComparisonSqlPartDatum().getColumnAlias());
                    break;
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
            }
        }
        this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSingleValue(ComparisonType.LIKE, value));
        return this.getHelper();
    }

    @Override
    default T in(Object[] values, ComparisonRule comparisonRule) {
        if (values == null || values.length == 0) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    ExceptionUtils.comparisonRuleNullException(BaseComparisonOperatorImpl.class, "in", this.getAbstractComparisonSqlPartDatum().getTableName(), this.getAbstractComparisonSqlPartDatum().getTableAlias(), this.getAbstractComparisonSqlPartDatum().getColumnName(), this.getAbstractComparisonSqlPartDatum().getColumnAlias());
                    break;
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
            }
        }
        this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetMultiValue(ComparisonType.IN, values));
        return this.getHelper();
    }

    @Override
    default T in(Collection<Object> values, ComparisonRule comparisonRule) {
        if (values == null || values.size() == 0) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    ExceptionUtils.comparisonRuleNullException(BaseComparisonOperatorImpl.class, "in", this.getAbstractComparisonSqlPartDatum().getTableName(), this.getAbstractComparisonSqlPartDatum().getTableAlias(), this.getAbstractComparisonSqlPartDatum().getColumnName(), this.getAbstractComparisonSqlPartDatum().getColumnAlias());
                    break;
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
            }
        }
        this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetMultiValue(ComparisonType.IN, values));
        return this.getHelper();
    }

    @Override
    default T notIn(Object[] values, ComparisonRule comparisonRule) {
        if (values == null || values.length == 0) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    ExceptionUtils.comparisonRuleNullException(BaseComparisonOperatorImpl.class, "notIn", this.getAbstractComparisonSqlPartDatum().getTableName(), this.getAbstractComparisonSqlPartDatum().getTableAlias(), this.getAbstractComparisonSqlPartDatum().getColumnName(), this.getAbstractComparisonSqlPartDatum().getColumnAlias());
                    break;
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
            }
        }
        this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetMultiValue(ComparisonType.NOT_IN, values));
        return this.getHelper();
    }

    @Override
    default T notIn(Collection<Object> values, ComparisonRule comparisonRule) {
        if (values == null || values.size() == 0) {
            switch (comparisonRule) {
                case NULL_SKIP:
                    return this.getHelper();
                case NULL_THROW_EXCEPTION:
                    ExceptionUtils.comparisonRuleNullException(BaseComparisonOperatorImpl.class, "notIn", this.getAbstractComparisonSqlPartDatum().getTableName(), this.getAbstractComparisonSqlPartDatum().getTableAlias(), this.getAbstractComparisonSqlPartDatum().getColumnName(), this.getAbstractComparisonSqlPartDatum().getColumnAlias());
                    break;
                default:
                    ExceptionUtils.comparisonRuleNotSupportException();
            }
        }
        this.addSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetMultiValue(ComparisonType.NOT_IN, values));
        return this.getHelper();
    }
}