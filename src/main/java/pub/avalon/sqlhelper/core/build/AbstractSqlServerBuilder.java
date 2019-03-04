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
        MainTableData<M> mainTableData = this.sqlData.getMainTableData();
        Map<String, String> columnAliasMap = mainTableData.getTableModel().getColumnAliasMap();
        int i = 0;
        for (Map.Entry<String, String> entry : columnAliasMap.entrySet()) {
            if (i++ > 0) {
                sqlSplicer.append(",");
            } else {
                sqlSplicer.append(" ");
            }
            sqlSplicer.append(mainTableData.getTableAlias())
                    .append(".[")
                    .append(entry.getKey())
                    .append("] [")
                    .append(entry.getValue())
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
        AbstractTableData tableData;
        int i = 0;
        for (FunctionColumnData fcData : this.sqlData.getFunctionColumnDataList()) {
            tableData = fcData.getTableData();
            String alias = fcData.getAlias();
            if (this.aliasSingleValidator.get(alias) != null) {
                throw new TableDataException("FunctionColumn alias [" + alias + "] is already be used, please set another alias.");
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
            sqlSplicer.append(tableData.getTableAlias())
                    .append(".[")
                    .append(fcData.getColumn())
                    .append("]) [")
                    .append(alias)
                    .append("]");
            this.aliasSingleValidator.put(alias, true);
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
        Map<String, String> columnAliasMap;
        String tableAlias;
        Class mainTableClass = this.sqlData.getMainTableData().getTableClass();
        int i = 0;
        for (AbstractTableData tableData : this.sqlData.getColumnDataSet()) {
            columnAliasMap = tableData.getColumnAliasMap();
            if (columnAliasMap.size() == 0) {
                columnAliasMap = tableData.getTableModel().getColumnAliasMap();
                tableAlias = tableData.getTableAlias();
                if (tableData.getTableClass() == mainTableClass) {
                    for (Map.Entry<String, String> entry : columnAliasMap.entrySet()) {
                        if (i++ > 0) {
                            sqlSplicer.append(",");
                        } else {
                            sqlSplicer.append(" ");
                        }
                        sqlSplicer.append(tableAlias)
                                .append(".[")
                                .append(entry.getKey())
                                .append("] [")
                                .append(entry.getValue())
                                .append("]");
                    }
                } else {
                    for (Map.Entry<String, String> entry : columnAliasMap.entrySet()) {
                        if (i++ > 0) {
                            sqlSplicer.append(",");
                        } else {
                            sqlSplicer.append(" ");
                        }
                        sqlSplicer.append(tableAlias)
                                .append(".[")
                                .append(entry.getKey())
                                .append("]");
                    }
                }
                continue;
            }
            for (Map.Entry<String, String> entry : columnAliasMap.entrySet()) {
                if (this.aliasSingleValidator.get(entry.getValue()) != null) {
                    throw new TableDataException("table alias [" + tableData.getTableAlias() + "] column alias [" + entry.getValue() + "] is already be used, please set another alias.");
                }
                if (i++ > 0) {
                    sqlSplicer.append(",");
                } else {
                    sqlSplicer.append(" ");
                }
                sqlSplicer.append(tableData.getTableAlias())
                        .append(".[")
                        .append(entry.getKey())
                        .append("] [")
                        .append(entry.getValue())
                        .append("]");
                this.aliasSingleValidator.put(entry.getValue(), true);
            }
        }
        return sqlSplicer;
    }

    protected SqlSplicer appendColumnSql(SqlSplicer sqlSplicer) {
        Map<String, SqlBuilder> subQueryAliasMap = this.sqlData.getSubQueryDataMap();
        List<FunctionColumnData> functionColumnDataList = this.sqlData.getFunctionColumnDataList();
        Set<VirtualFieldData> virtualFieldDataSet = this.sqlData.getVirtualFieldDataSet();
        Set<AbstractTableData> columnDataSet = this.sqlData.getColumnDataSet();
        boolean hasS = subQueryAliasMap != null && subQueryAliasMap.size() != 0;
        boolean hasF = functionColumnDataList != null && functionColumnDataList.size() != 0;
        boolean hasV = virtualFieldDataSet != null && virtualFieldDataSet.size() != 0;
        boolean hasC = columnDataSet != null && columnDataSet.size() != 0;
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


    private SqlSplicer appendLinkOnDataList(SqlSplicer sqlSplicer, List<LinkOnData> linkOnDataList, LinkType linkType, boolean checkBrackets) {

        if (linkOnDataList == null || linkOnDataList.size() == 0) {
            return sqlSplicer;
        }
        int length = sqlSplicer.length();
        List<OnData> onDataList;
        int i = 0;
        boolean brackets = false;
        for (LinkOnData linkOnData : linkOnDataList) {
            onDataList = linkOnData.getOnDataList();
            List<LinkOnData> childLinkOnDataList = linkOnData.getLinkOnDataList();
            if (onDataList != null && onDataList.size() > 0) {
                switch (linkOnData.getLinkType()) {
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
            } else if (childLinkOnDataList != null && childLinkOnDataList.size() > 0) {
                switch (linkOnData.getLinkType()) {
                    case AND:
                        if (i++ > 0) {
                            sqlSplicer.append(" and ");
                        }
                        sqlSplicer = this.appendLinkOnDataList(sqlSplicer, childLinkOnDataList, LinkType.AND, true);
                        continue;
                    case OR:
                        if (i++ > 0) {
                            sqlSplicer.append(" or ");
                            brackets = checkBrackets;
                        }
                        sqlSplicer = this.appendLinkOnDataList(sqlSplicer, childLinkOnDataList, LinkType.OR, true);
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
        JoinTableData joinTableData;
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
            sqlSplicer.append(joinTableData.getTableName())
                    .append(" ")
                    .append(joinTableData.getTableAlias());
            List<LinkOnData> linkOnDataList = joinTableData.getLinkOnDataList();
            if (linkOnDataList != null && linkOnDataList.size() > 0) {
                sqlSplicer.append(" on ");
                this.appendLinkOnDataList(sqlSplicer, linkOnDataList, LinkType.AND, false);
            }
        }
        return sqlSplicer;
    }

    private SqlSplicer appendWhereDataList(SqlSplicer sqlSplicer, List<WhereData> whereDataList, LinkType linkType) {
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
            switch (whereData.getWhereType()) {
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
                    this.sqlArgs.add(whereData.getTargetValue());
                    this.sqlArgs.add(whereData.getTargetSecondValue());
                    continue;
                case LIKE:
                    sqlSplicer.append(" like ?");
                    this.sqlArgs.add(whereData.getTargetValue());
                    continue;
                case IN:
                    int count = whereData.getValueCount();
                    sqlSplicer.append(" in (");
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
                    continue;
                case NOT_IN:
                    count = whereData.getValueCount();
                    sqlSplicer.append(" not in (");
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
                    continue;
                default:
                    throw new SqlException("the WhereType is wrong.");
            }
            switch (whereData.getWhereValueType()) {
                case VALUE:
                    sqlSplicer.append("?");
                    this.sqlArgs.add(whereData.getTargetValue());
                    continue;
                case JOIN:
                    sqlSplicer.append(whereData.getTargetTableAlias())
                            .append(".[")
                            .append(whereData.getTargetColumnName())
                            .append("]");
                    continue;
                default:
                    throw new SqlException("the WhereValueType is wrong.");
            }
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
        List<WhereData> whereDataList;
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
        List<GroupData> groupDataList = this.sqlData.getGroupDataList();
        if (groupDataList == null || groupDataList.size() == 0) {
            return sqlSplicer;
        }
        sqlSplicer.append(" group by ");
        String alias;
        int i = 0;
        for (GroupData groupData : groupDataList) {
            alias = groupData.getTableData().getTableAlias();
            for (String columnName : groupData.getColumnNames()) {
                if (i++ > 0) {
                    sqlSplicer.append(",");
                }
                sqlSplicer.append(alias)
                        .append(".")
                        .append(columnName);
            }
        }

        return sqlSplicer;
    }

    protected SqlSplicer appendSortSql(SqlSplicer sqlSplicer) {
        List<List<SortData>> sortDataList = this.sqlData.getSortDataListList();
        if (sortDataList == null || sortDataList.size() == 0) {
            return sqlSplicer;
        }
        sqlSplicer.append(" order by ");
        int i = 0;
        for (List<SortData> sortData : sortDataList) {
            for (SortData data : sortData) {
                if (i++ > 0) {
                    sqlSplicer.append(",");
                }
                sqlSplicer.append(data.getTableData().getTableAlias())
                        .append(".[")
                        .append(data.getColumnName())
                        .append("]");
                switch (data.getSortType()) {
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
                    .append(this.sqlData.getMainTableData().getPrimaryKeyName())
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


    protected Map<String, String> getColumnAliasMap() {
        Map<String, String> columnAliasMap = this.sqlData.getMainTableData().getColumnAliasMap();
        if (columnAliasMap == null || columnAliasMap.size() == 0) {
            columnAliasMap = this.sqlData.getMainTableData().getTableModel().getColumnAliasMap();
        }
        return columnAliasMap;
    }
}
