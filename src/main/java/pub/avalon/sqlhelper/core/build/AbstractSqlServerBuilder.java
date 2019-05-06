package pub.avalon.sqlhelper.core.build;

import pub.avalon.beans.LimitHandler;
import pub.avalon.sqlhelper.core.beans.LinkType;
import pub.avalon.sqlhelper.core.data.*;
import pub.avalon.sqlhelper.core.exception.SqlException;
import pub.avalon.sqlhelper.core.exception.TableDataException;
import pub.avalon.sqlhelper.core.norm.Model;
import pub.avalon.sqlhelper.core.sql.SqlSplicer;

import java.util.*;

/**
 * @author 白超
 * @date 2018/9/10
 */
public abstract class AbstractSqlServerBuilder<M extends Model> extends AbstractSqlBuilder {

    private Map<String, Boolean> aliasSingleValidator = new HashMap<>(32);

    protected SqlData<M> sqlData;

    public AbstractSqlServerBuilder(SqlData<M> sqlData) {
        this.sqlData = sqlData;
    }

    private SqlSplicer appendMainTableAllColumnSql(SqlSplicer sqlSplicer) {
        Set<ColumnDatum> columnData = this.sqlData.getMainTableData().buildTableColumnData();
        int i = 0;
        for (ColumnDatum columnDatum : columnData) {
            if (i++ > 0) {
                sqlSplicer.append(",");
            } else {
                sqlSplicer.append(" ");
            }
            sqlSplicer.append(columnDatum.getOwnerTableAlias())
                    .append(".[")
                    .append(columnDatum.getOwnerColumnName())
                    .append("] [")
                    .append(columnDatum.getOwnerColumnAlias())
                    .append("]");
        }
        return sqlSplicer;
    }

    private SqlSplicer appendSubQuerySql(SqlSplicer sqlSplicer) {
        Map<String, SqlBuilder> subQueryAliasMap = this.sqlData.getSubQueryDataMap();
        if (subQueryAliasMap == null) {
            return sqlSplicer;
        }
        int i = 0;
        for (Map.Entry<String, SqlBuilder> entry : subQueryAliasMap.entrySet()) {
            String alias = entry.getKey();
            SqlBuilder sqlBuilder = entry.getValue();
            if (this.aliasSingleValidator.get(alias) != null) {
                throw new TableDataException("SubQueryColumn alias [" + alias + "] is already be used, please set another alias.");
            }
            if (i++ > 0) {
                sqlSplicer.append(",(");
            } else {
                sqlSplicer.append(" (");
            }
            sqlSplicer.append(sqlBuilder.getPreparedStatementSql()).append(") ").append(alias);
            this.sqlArgs.addAll(sqlBuilder.getPreparedStatementArgs());
        }
        return sqlSplicer;
    }

    private SqlSplicer appendFunctionColumnSql(SqlSplicer sqlSplicer) {
        int i = 0;
        for (FunctionColumnData fcData : this.sqlData.getFunctionColumnDataList()) {
            if (this.aliasSingleValidator.get(fcData.getColumnAlias()) != null) {
                throw new TableDataException("FunctionColumn alias [" + fcData.getColumnAlias() + "] is already be used, please set another alias.");
            }
            if (i++ > 0) {
                sqlSplicer.append(",");
            } else {
                sqlSplicer.append(" ");
            }
            switch (fcData.getFunctionColumnType()) {
                case MIN:
                    sqlSplicer.append("min(");
                    break;
                case MAX:
                    sqlSplicer.append("max(");
                    break;
                case COUNT:
                    sqlSplicer.append("count(");
                    break;
                case SUM:
                    sqlSplicer.append("sum(");
                    break;
                default:
                    throw new SqlException("the functionColumnType is wrong.");
            }
            sqlSplicer.append(fcData.getTableData().getTableAlias())
                    .append(".[")
                    .append(fcData.getColumnName())
                    .append("]) [")
                    .append(fcData.getColumnAlias())
                    .append("]");
            this.aliasSingleValidator.put(fcData.getColumnAlias(), true);
        }
        return sqlSplicer;
    }

    private SqlSplicer appendVirtualColumnSql(SqlSplicer sqlSplicer) {
        Object value;
        String alias;
        int i = 0;
        for (VirtualFieldData data : this.sqlData.getVirtualFieldDataSet()) {
            if (i++ > 0) {
                sqlSplicer.append(",");
            } else {
                sqlSplicer.append(" ");
            }
            value = data.getValue();
            alias = data.getAlias();
            if (this.aliasSingleValidator.get(alias) != null) {
                throw new TableDataException("VirtualField alias [" + alias + "] is already be used, please set another alias.");
            }
            if (value == null) {
                sqlSplicer.append("null");
            } else if (value instanceof String) {
                sqlSplicer.append("'").append((String) value).append("'");
            } else if (value instanceof Integer) {
                sqlSplicer.append(String.valueOf(value));
            } else if (value instanceof Long) {
                sqlSplicer.append(String.valueOf(value));
            } else if (value instanceof Double) {
                sqlSplicer.append(String.valueOf(value));
            } else {
                throw new SqlException("the VirtualFieldData value type is wrong.");
            }
            if (alias != null) {
                sqlSplicer.append(" [").append(alias).append("]");
                this.aliasSingleValidator.put(alias, true);
            } else {
                this.aliasSingleValidator.put(value + "", true);
            }
        }
        return sqlSplicer;
    }

    private SqlSplicer appendTableColumnSql(SqlSplicer sqlSplicer) {
        int i = 0;
        TableData tableData;
        Set<ColumnDatum> columnData;
        for (TableColumnData tableColumnData : this.sqlData.getTableColumnDataSet()) {
            columnData = tableColumnData.getColumnData();
            if (columnData.size() == 0) {
                continue;
            }
            tableData = tableColumnData.getTableData();
            for (ColumnDatum columnDatum : columnData) {
                if (this.aliasSingleValidator.get(columnDatum.getOwnerColumnAlias()) != null) {
                    throw new TableDataException("table alias [" + tableData.getTableAlias() + "] column alias [" + columnDatum.getOwnerColumnAlias() + "] is already be used, please set another alias.");
                }
                if (i++ > 0) {
                    sqlSplicer.append(",");
                } else {
                    sqlSplicer.append(" ");
                }
                sqlSplicer.append(columnDatum.getOwnerTableAlias())
                        .append(".[")
                        .append(columnDatum.getOwnerColumnName())
                        .append("] [")
                        .append(columnDatum.getOwnerColumnAlias())
                        .append("]");
                this.aliasSingleValidator.put(columnDatum.getOwnerColumnAlias(), true);
            }
        }
        return sqlSplicer;
    }

    protected SqlSplicer appendColumnSql(SqlSplicer sqlSplicer) {
        Map<String, SqlBuilder> subQueryAliasMap = this.sqlData.getSubQueryDataMap();
        List<FunctionColumnData> functionColumnDataList = this.sqlData.getFunctionColumnDataList();
        Set<VirtualFieldData> virtualFieldDataSet = this.sqlData.getVirtualFieldDataSet();
        Set<TableColumnData> tableColumnDataSet = this.sqlData.getTableColumnDataSet();
        boolean hasS = subQueryAliasMap != null && subQueryAliasMap.size() != 0;
        boolean hasF = functionColumnDataList != null && functionColumnDataList.size() != 0;
        boolean hasV = virtualFieldDataSet != null && virtualFieldDataSet.size() != 0;
        boolean hasC = tableColumnDataSet != null && tableColumnDataSet.size() != 0;
        if (!hasS && !hasF && !hasV && !hasC) {
            return this.appendMainTableAllColumnSql(sqlSplicer);
        }
        if (hasS) {
            sqlSplicer = this.appendSubQuerySql(sqlSplicer);
        }
        if (hasF) {
            if (hasS) {
                sqlSplicer.append(",");
            }
            sqlSplicer = this.appendFunctionColumnSql(sqlSplicer);
        }
        if (hasV) {
            if (hasS || hasF) {
                sqlSplicer.append(",");
            }
            sqlSplicer = this.appendVirtualColumnSql(sqlSplicer);
        }
        if (hasC) {
            if (hasS || hasF || hasV) {
                sqlSplicer.append(",");
            }
            sqlSplicer = this.appendTableColumnSql(sqlSplicer);
        }
        return sqlSplicer;
    }

    private SqlSplicer appendOnDataList(SqlSplicer sqlSplicer, List<OnData> onDataList, LinkType linkType) {
        if (onDataList == null || onDataList.size() == 0) {
            return sqlSplicer;
        }
        if (linkType == LinkType.OR && onDataList.size() > 1) {
            sqlSplicer.append("(");
        }
        int i = 0;
        for (OnData onData : onDataList) {
            if (i++ > 0) {
                sqlSplicer.append(" and ");
            }
            sqlSplicer.append(onData.getOwnerTableAlias())
                    .append(".[")
                    .append(onData.getOwnerColumnName())
                    .append("]");
            switch (onData.getOnType()) {
                case IS_NULL:
                    sqlSplicer.append(" is null");
                    continue;
                case IS_NOT_NULL:
                    sqlSplicer.append(" is not null");
                    continue;
                case EQUAL:
                    sqlSplicer.append(" = ");
                    break;
                case NOT_EQUAL:
                    sqlSplicer.append(" != ");
                    break;
                case GREATER:
                    sqlSplicer.append(" > ");
                    break;
                case GREATER_EQUAL:
                    sqlSplicer.append(" >= ");
                    break;
                case LESS:
                    sqlSplicer.append(" < ");
                    break;
                case LESS_EQUAL:
                    sqlSplicer.append(" <= ");
                    break;
                case BETWEEN:
                    sqlSplicer.append(" between ? and ?");
                    this.sqlArgs.add(onData.getTargetValue());
                    this.sqlArgs.add(onData.getTargetSecondValue());
                    continue;
                case LIKE:
                    sqlSplicer.append(" like ?");
                    this.sqlArgs.add(onData.getTargetValue());
                    continue;
                case IN:
                    int count = onData.getValueCount();
                    sqlSplicer.append(" in (");
                    for (; count > 0; count--) {
                        if (count == 1) {
                            sqlSplicer.append("?");
                        } else {
                            sqlSplicer.append("?,");
                        }
                    }
                    sqlSplicer.append(")");
                    Object value = onData.getTargetValue();
                    if (value instanceof Collection) {
                        this.sqlArgs.addAll((Collection) value);
                    } else if (value.getClass().isArray()) {
                        this.sqlArgs.addAll(Arrays.asList((Object[]) value));
                    } else {
                        throw new SqlException("the value type can only be Array or Collection.");
                    }
                    continue;
                case NOT_IN:
                    count = onData.getValueCount();
                    sqlSplicer.append(" not in (");
                    for (; count > 0; count--) {
                        if (count == 1) {
                            sqlSplicer.append("?");
                        } else {
                            sqlSplicer.append("?,");
                        }
                    }
                    sqlSplicer.append(")");
                    value = onData.getTargetValue();
                    if (value instanceof Collection) {
                        this.sqlArgs.addAll((Collection) value);
                    } else if (value.getClass().isArray()) {
                        this.sqlArgs.addAll(Arrays.asList((Object[]) value));
                    } else {
                        throw new SqlException("the value type can only be Array or Collection.");
                    }
                    continue;
                default:
                    throw new SqlException("the WhereType is wrong.");
            }
            switch (onData.getOnValueType()) {
                case VALUE:
                    sqlSplicer.append("?");
                    this.sqlArgs.add(onData.getTargetValue());
                    continue;
                case JOIN:
                    sqlSplicer.append(onData.getTargetTableAlias())
                            .append(".[")
                            .append(onData.getTargetColumnName())
                            .append("]");
                    continue;
                default:
                    throw new SqlException("the OnValueType is wrong.");
            }
        }
        if (linkType == LinkType.OR && onDataList.size() > 1) {
            sqlSplicer.append(")");
        }
        return sqlSplicer;
    }


    private SqlSplicer appendOnDataLinkerList(SqlSplicer sqlSplicer, List<OnDataLinker> onDataLinkerList, LinkType linkType, boolean checkBrackets) {

        if (onDataLinkerList == null || onDataLinkerList.size() == 0) {
            return sqlSplicer;
        }
        int length = sqlSplicer.length();
        List<OnData> onDataList;
        int i = 0;
        boolean brackets = false;
        for (OnDataLinker onDataLinker : onDataLinkerList) {
            onDataList = onDataLinker.getOnDataList();
            List<OnDataLinker> childOnDataLinkerList = onDataLinker.getOnDataLinkerList();
            if (onDataList != null && onDataList.size() > 0) {
                switch (onDataLinker.getLinkType()) {
                    case AND:
                        if (i++ > 0) {
                            sqlSplicer.append(" and ");
                        }
                        this.appendOnDataList(sqlSplicer, onDataList, LinkType.AND);
                        continue;
                    case OR:
                        if (i++ > 0) {
                            sqlSplicer.append(" or ");
                            brackets = checkBrackets;
                        }
                        this.appendOnDataList(sqlSplicer, onDataList, LinkType.OR);
                        continue;
                    default:
                        throw new SqlException("the LinkType is wrong.");
                }
            } else if (childOnDataLinkerList != null && childOnDataLinkerList.size() > 0) {
                switch (onDataLinker.getLinkType()) {
                    case AND:
                        if (i++ > 0) {
                            sqlSplicer.append(" and ");
                        }
                        sqlSplicer = this.appendOnDataLinkerList(sqlSplicer, childOnDataLinkerList, LinkType.AND, true);
                        continue;
                    case OR:
                        if (i++ > 0) {
                            sqlSplicer.append(" or ");
                            brackets = checkBrackets;
                        }
                        sqlSplicer = this.appendOnDataLinkerList(sqlSplicer, childOnDataLinkerList, LinkType.OR, true);
                        continue;
                    default:
                        throw new SqlException("the LinkType is wrong.");
                }
            }
        }
        if (!checkBrackets) {
            return sqlSplicer;
        }
        brackets = brackets || linkType == LinkType.OR && i > 1;
        if (!brackets) {
            return sqlSplicer;
        }
        sqlSplicer.insert(length, "(").append(")");
        return sqlSplicer;
    }

    protected SqlSplicer appendJoinSql(SqlSplicer sqlSplicer) {
        Map<String, JoinTableData<? extends Model>> joinTableDataAliasMap = this.sqlData.getJoinTableDataMap();
        if (joinTableDataAliasMap == null || joinTableDataAliasMap.size() == 0) {
            return sqlSplicer;
        }
        JoinTableData<? extends Model> joinTableData;
        for (Map.Entry<String, JoinTableData<? extends Model>> entry : joinTableDataAliasMap.entrySet()) {
            joinTableData = entry.getValue();
            switch (joinTableData.getJoinType()) {
                case INNER:
                    sqlSplicer.append(" inner join ");
                    break;
                case LEFT:
                    sqlSplicer.append(" left join ");
                    break;
                case RIGHT:
                    sqlSplicer.append(" right join ");
                    break;
                default:
                    continue;
            }
            sqlSplicer.append("[")
                    .append(joinTableData.getTableName())
                    .append("] ")
                    .append(joinTableData.getTableAlias());
            List<OnDataLinker> onDataLinkerList = joinTableData.getOnDataLinkerList();
            if (onDataLinkerList != null && onDataLinkerList.size() > 0) {
                sqlSplicer.append(" on ");
                this.appendOnDataLinkerList(sqlSplicer, onDataLinkerList, LinkType.AND, false);
            }
        }
        return sqlSplicer;
    }

    private SqlSplicer appendWhereDataValueSql(SqlSplicer sqlSplicer, WhereData whereData) {
        switch (whereData.getWhereType()) {
            case IS_NULL:
                sqlSplicer.append(" is null");
                break;
            case IS_NOT_NULL:
                sqlSplicer.append(" is not null");
                break;
            case BETWEEN:
                sqlSplicer.append(" between ? and ?");
                this.sqlArgs.add(whereData.getTargetValue());
                this.sqlArgs.add(whereData.getTargetSecondValue());
                break;
            case EQUAL:
                sqlSplicer.append(" = ?");
                this.sqlArgs.add(whereData.getTargetValue());
                break;
            case NOT_EQUAL:
                sqlSplicer.append(" != ?");
                this.sqlArgs.add(whereData.getTargetValue());
                break;
            case GREATER:
                sqlSplicer.append(" > ?");
                this.sqlArgs.add(whereData.getTargetValue());
                break;
            case GREATER_EQUAL:
                sqlSplicer.append(" >= ?");
                this.sqlArgs.add(whereData.getTargetValue());
                break;
            case LESS:
                sqlSplicer.append(" < ?");
                this.sqlArgs.add(whereData.getTargetValue());
                break;
            case LESS_EQUAL:
                sqlSplicer.append(" <= ?");
                this.sqlArgs.add(whereData.getTargetValue());
                break;
            case LIKE:
                sqlSplicer.append(" like ?");
                this.sqlArgs.add(whereData.getTargetValue());
                break;
            case IN:
                sqlSplicer.append(" in (");
                int count = whereData.getValueCount();
                for (; count > 0; count--) {
                    if (count == 1) {
                        sqlSplicer.append("?");
                    } else {
                        sqlSplicer.append("?,");
                    }
                }
                sqlSplicer.append(")");
                Object value = whereData.getTargetValue();
                if (value instanceof Collection) {
                    this.sqlArgs.addAll((Collection) value);
                } else if (value.getClass().isArray()) {
                    this.sqlArgs.addAll(Arrays.asList((Object[]) value));
                } else {
                    throw new SqlException("the value type can only be Array or Collection.");
                }
                break;
            case NOT_IN:
                sqlSplicer.append(" not in (");
                count = whereData.getValueCount();
                for (; count > 0; count--) {
                    if (count == 1) {
                        sqlSplicer.append("?");
                    } else {
                        sqlSplicer.append("?,");
                    }
                }
                sqlSplicer.append(")");
                value = whereData.getTargetValue();
                if (value instanceof Collection) {
                    this.sqlArgs.addAll((Collection) value);
                } else if (value.getClass().isArray()) {
                    this.sqlArgs.addAll(Arrays.asList((Object[]) value));
                } else {
                    throw new SqlException("the value type can only be Array or Collection.");
                }
                break;
            default:
                throw new SqlException("the WhereType is wrong.");
        }
        return sqlSplicer;
    }

    private SqlSplicer appendWhereDataJoinSql(SqlSplicer sqlSplicer, WhereData whereData) {
        switch (whereData.getWhereType()) {
            case IS_NULL:
                sqlSplicer.append(" is null");
                break;
            case IS_NOT_NULL:
                sqlSplicer.append(" is not null");
                break;
            case BETWEEN:
                sqlSplicer.append(" between ? and ?");
                // TODO 后续添加
                throw new SqlException("暂不支持");
                //TODO 别忘记break
            case EQUAL:
                sqlSplicer.append(" = ")
                        .append(whereData.getTargetTableAlias())
                        .append(".[")
                        .append(whereData.getTargetColumnName())
                        .append("]");
                break;
            case NOT_EQUAL:
                sqlSplicer.append(" != ")
                        .append(whereData.getTargetTableAlias())
                        .append(".[")
                        .append(whereData.getTargetColumnName())
                        .append("]");
                break;
            case GREATER:
                sqlSplicer.append(" > ")
                        .append(whereData.getTargetTableAlias())
                        .append(".[")
                        .append(whereData.getTargetColumnName())
                        .append("]");
                break;
            case GREATER_EQUAL:
                sqlSplicer.append(" >= ")
                        .append(whereData.getTargetTableAlias())
                        .append(".[")
                        .append(whereData.getTargetColumnName())
                        .append("]");
                break;
            case LESS:
                sqlSplicer.append(" < ")
                        .append(whereData.getTargetTableAlias())
                        .append(".[")
                        .append(whereData.getTargetColumnName())
                        .append("]");
                break;
            case LESS_EQUAL:
                sqlSplicer.append(" <= ")
                        .append(whereData.getTargetTableAlias())
                        .append(".[")
                        .append(whereData.getTargetColumnName())
                        .append("]");
                break;
            case LIKE:
                sqlSplicer.append(" like ")
                        .append(whereData.getTargetTableAlias())
                        .append(".[")
                        .append(whereData.getTargetColumnName())
                        .append("]");
                break;
            case IN:
                sqlSplicer.append(" in ");
                // TODO 后续添加
                throw new SqlException("暂不支持");
                //TODO 别忘记break
            case NOT_IN:
                sqlSplicer.append(" not in ");
                // TODO 后续添加
                throw new SqlException("暂不支持");
                //TODO 别忘记break
            default:
                throw new SqlException("the WhereType is wrong.");
        }
        return sqlSplicer;
    }

    private SqlSplicer appendWhereDataSubQuerySql(SqlSplicer sqlSplicer, WhereData whereData) {
        switch (whereData.getWhereType()) {
            case IS_NULL:
                sqlSplicer.append(" is null");
                break;
            case IS_NOT_NULL:
                sqlSplicer.append(" is not null");
                break;
            case BETWEEN:
                sqlSplicer.append(" between ? and ?");
                // TODO 后续添加
                throw new SqlException("暂不支持");
                // TODO 别忘记break
            case EQUAL:
                sqlSplicer.append(" = (")
                        .append(whereData.getTargetSubQuery().getPreparedStatementSql())
                        .append(")");
                this.sqlArgs.addAll(whereData.getTargetSubQuery().getPreparedStatementArgs());
                break;
            case NOT_EQUAL:
                sqlSplicer.append(" != (")
                        .append(whereData.getTargetSubQuery().getPreparedStatementSql())
                        .append(")");
                this.sqlArgs.addAll(whereData.getTargetSubQuery().getPreparedStatementArgs());
                break;
            case GREATER:
                sqlSplicer.append(" > (")
                        .append(whereData.getTargetSubQuery().getPreparedStatementSql())
                        .append(")");
                this.sqlArgs.addAll(whereData.getTargetSubQuery().getPreparedStatementArgs());
                break;
            case GREATER_EQUAL:
                sqlSplicer.append(" >= (")
                        .append(whereData.getTargetSubQuery().getPreparedStatementSql())
                        .append(")");
                this.sqlArgs.addAll(whereData.getTargetSubQuery().getPreparedStatementArgs());
                break;
            case LESS:
                sqlSplicer.append(" < (")
                        .append(whereData.getTargetSubQuery().getPreparedStatementSql())
                        .append(")");
                this.sqlArgs.addAll(whereData.getTargetSubQuery().getPreparedStatementArgs());
                break;
            case LESS_EQUAL:
                sqlSplicer.append(" <= (")
                        .append(whereData.getTargetSubQuery().getPreparedStatementSql())
                        .append(")");
                this.sqlArgs.addAll(whereData.getTargetSubQuery().getPreparedStatementArgs());
                break;
            case LIKE:
                sqlSplicer.append(" like (")
                        .append(whereData.getTargetSubQuery().getPreparedStatementSql())
                        .append(")");
                this.sqlArgs.addAll(whereData.getTargetSubQuery().getPreparedStatementArgs());
                break;
            case IN:
                sqlSplicer.append(" in (")
                        .append(whereData.getTargetSubQuery().getPreparedStatementSql())
                        .append(")");
                this.sqlArgs.addAll(whereData.getTargetSubQuery().getPreparedStatementArgs());
                break;
            case NOT_IN:
                sqlSplicer.append(" not in (")
                        .append(whereData.getTargetSubQuery().getPreparedStatementSql())
                        .append(")");
                this.sqlArgs.addAll(whereData.getTargetSubQuery().getPreparedStatementArgs());
                break;
            default:
                throw new SqlException("the WhereType is wrong.");
        }
        return sqlSplicer;
    }

    private SqlSplicer appendWhereDataSqlPartSql(SqlSplicer sqlSplicer, WhereData whereData) {
        sqlSplicer.append(" ").append(whereData.getSqlPart());
        return sqlSplicer;
    }

    private SqlSplicer appendWhereDataSql(SqlSplicer sqlSplicer, WhereData whereData) {
        switch (whereData.getWhereValueType()) {
            case VALUE:
                this.appendWhereDataValueSql(sqlSplicer, whereData);
                break;
            case JOIN:
                this.appendWhereDataJoinSql(sqlSplicer, whereData);
                break;
            case SUB_QUERY:
                this.appendWhereDataSubQuerySql(sqlSplicer, whereData);
                break;
            case SQL_PART:
                this.appendWhereDataSqlPartSql(sqlSplicer, whereData);
                break;
            default:
                throw new SqlException("the WhereValueType is wrong.");
        }
        return sqlSplicer;
    }

    private SqlSplicer appendWhereDataList(SqlSplicer sqlSplicer, Set<WhereData> whereDataList, LinkType linkType) {
        if (whereDataList == null || whereDataList.size() == 0) {
            return sqlSplicer;
        }
        if (linkType == LinkType.OR && whereDataList.size() > 1) {
            sqlSplicer.append("(");
        }
        int i = 0;
        for (WhereData whereData : whereDataList) {
            if (i++ > 0) {
                sqlSplicer.append(" and ");
            }
            sqlSplicer.append(whereData.getOwnerTableAlias())
                    .append(".[")
                    .append(whereData.getOwnerColumnName())
                    .append("]");
            this.appendWhereDataSql(sqlSplicer, whereData);
        }
        if (linkType == LinkType.OR && whereDataList.size() > 1) {
            sqlSplicer.append(")");
        }
        return sqlSplicer;
    }

    private SqlSplicer appendWhereDataLinkerList(SqlSplicer sqlSplicer, List<WhereDataLinker> whereDataLinkerList, LinkType linkType, boolean checkBrackets) {
        if (whereDataLinkerList == null || whereDataLinkerList.size() == 0) {
            return sqlSplicer;
        }
        int length = sqlSplicer.length();
        Set<WhereData> whereDataList;
        int i = 0;
        boolean brackets = false;
        for (WhereDataLinker whereDataLinker : whereDataLinkerList) {
            whereDataList = whereDataLinker.getWhereDataList();
            List<WhereDataLinker> childWhereDataLinkerList = whereDataLinker.getWhereDataLinkerList();
            if (whereDataList != null && whereDataList.size() > 0) {
                switch (whereDataLinker.getLinkType()) {
                    case AND:
                        if (i++ > 0) {
                            sqlSplicer.append(" and ");
                        }
                        this.appendWhereDataList(sqlSplicer, whereDataList, LinkType.AND);
                        continue;
                    case OR:
                        if (i++ > 0) {
                            sqlSplicer.append(" or ");
                            brackets = checkBrackets;
                        }
                        this.appendWhereDataList(sqlSplicer, whereDataList, LinkType.OR);
                        continue;
                    default:
                        throw new SqlException("the LinkType is wrong.");
                }
            } else if (childWhereDataLinkerList != null && childWhereDataLinkerList.size() > 0) {
                switch (whereDataLinker.getLinkType()) {
                    case AND:
                        if (i++ > 0) {
                            sqlSplicer.append(" and ");
                        }
                        sqlSplicer = this.appendWhereDataLinkerList(sqlSplicer, childWhereDataLinkerList, LinkType.AND, true);
                        continue;
                    case OR:
                        if (i++ > 0) {
                            sqlSplicer.append(" or ");
                            brackets = checkBrackets;
                        }
                        sqlSplicer = this.appendWhereDataLinkerList(sqlSplicer, childWhereDataLinkerList, LinkType.OR, true);
                        continue;
                    default:
                        throw new SqlException("the LinkType is wrong.");
                }
            }
        }
        if (!checkBrackets) {
            return sqlSplicer;
        }
        brackets = brackets || linkType == LinkType.OR && i > 1;
        if (!brackets) {
            return sqlSplicer;
        }
        sqlSplicer.insert(length, "(").append(")");
        return sqlSplicer;
    }

    protected SqlSplicer appendWhereSql(SqlSplicer sqlSplicer) {
        List<List<WhereDataLinker>> whereDataLinkerListList = this.sqlData.getWhereDataLinkerListList();
        if (whereDataLinkerListList == null || whereDataLinkerListList.size() == 0) {
            return sqlSplicer;
        }
        sqlSplicer.append(" where ");
        int i = 0;
        for (List<WhereDataLinker> whereDataLinkerList : whereDataLinkerListList) {
            if (i++ > 0) {
                sqlSplicer.append(" and ");
            }
            sqlSplicer = this.appendWhereDataLinkerList(sqlSplicer, whereDataLinkerList, LinkType.AND, whereDataLinkerListList.size() > 1);
        }
        return sqlSplicer;
    }

    protected SqlSplicer appendGroupSql(SqlSplicer sqlSplicer) {
        Set<TableGroupData> tableGroupDataSet = this.sqlData.getTableGroupDataSet();
        if (tableGroupDataSet == null || tableGroupDataSet.size() == 0) {
            return sqlSplicer;
        }
        sqlSplicer.append(" group by ");
        String alias;
        int i = 0;
        Set<GroupDatum> groupData;
        for (TableGroupData tableGroupData : tableGroupDataSet) {
            groupData = tableGroupData.getGroupData();
            if (groupData == null || groupData.size() == 0) {
                continue;
            }
            for (GroupDatum columnName : groupData) {
                if (i++ > 0) {
                    sqlSplicer.append(",");
                }
                sqlSplicer.append(columnName.getOwnerTableAlias())
                        .append(".[")
                        .append(columnName.getOwnerColumnName())
                        .append("]");
            }
        }
        return sqlSplicer;
    }

    protected SqlSplicer appendSortSql(SqlSplicer sqlSplicer) {
        Set<TableSortData> tableSortDataSet = this.sqlData.getTableSortDataSet();
        if (tableSortDataSet == null || tableSortDataSet.size() == 0) {
            return sqlSplicer;
        }
        sqlSplicer.append(" order by ");
        int i = 0;
        Set<SortDatum> sortData;
        for (TableSortData tableSortData : tableSortDataSet) {
            sortData = tableSortData.getSortData();
            if (sortData == null || sortData.size() == 0) {
                continue;
            }
            for (SortDatum sortDatum : sortData) {
                if (i++ > 0) {
                    sqlSplicer.append(",");
                }
                sqlSplicer.append(sortDatum.getOwnerTableAlias())
                        .append(".[")
                        .append(sortDatum.getOwnerColumnName())
                        .append("]");
                switch (sortDatum.getSortType()) {
                    case ASC:
                        sqlSplicer.append(" asc");
                        continue;
                    case DESC:
                        sqlSplicer.append(" desc");
                        continue;
                    default:
                        throw new SqlException("the SortType is wrong.");
                }
            }
        }
        return sqlSplicer;
    }

    protected SqlSplicer appendLimitSql(SqlSplicer sqlSplicer) {
        LimitHandler limit = this.sqlData.getLimitData();
        if (limit == null) {
            return sqlSplicer;
        }
        SqlSplicer rowNumSql = new SqlSplicer(64);
        rowNumSql.append("row_number() over(");
        int len = rowNumSql.length();
        this.appendSortSql(rowNumSql);
        if (len == rowNumSql.length()) {
            rowNumSql.append("order by ")
                    .append(this.sqlData.getMainTableData().getTableAlias())
                    .append(".[")
                    .append(this.sqlData.getMainTableData().getTableModel().getPrimaryKeyName())
                    .append("] asc");
        }
        rowNumSql.append(") n, ");

        sqlSplicer.insert(7, rowNumSql.getSql())
                .insert(0, "select * from (")
                .append(") rn where rn.n >= ? and rn.n <= ?");
        this.sqlArgs.add(limit.getLimitStart());
        this.sqlArgs.add(limit.getLimitEnd());
        return sqlSplicer;
    }

}
