package pub.avalon.sqlhelper.core.sqlbuilder;

import pub.avalon.beans.LimitSql;
import pub.avalon.sqlhelper.core.beans.BeanUtils;
import pub.avalon.sqlhelper.core.beans.GroupType;
import pub.avalon.sqlhelper.core.beans.LinkType;
import pub.avalon.sqlhelper.core.beans.SqlBuilderResult;
import pub.avalon.sqlhelper.core.data.*;
import pub.avalon.sqlhelper.core.exception.SqlException;

import java.util.*;

/**
 * 默认Sql片段构建器模板
 *
 * @author 白超
 * @date 2018/8/23
 */
public class DefaultSqlPartBuilderTemplate implements SqlPartBuilderTemplate {

    public final static DefaultSqlPartBuilderTemplate DEFAULT_SQL_PART_BUILDER_TEMPLATE = new DefaultSqlPartBuilderTemplate();

    @Override
    public SqlBuilderResult buildColumn(SqlDataConsumer sqlDataConsumer) {
        Map<String, SqlBuilder> subQueryAliasMap = sqlDataConsumer.getSubQueryDataMap();
        List<TableFunctionColumnDatum> tableFunctionColumnData = sqlDataConsumer.getTableFunctionColumnData();
        Set<VirtualFieldDatum> virtualFieldData = sqlDataConsumer.getVirtualFieldData();
        List<TableColumnDatum> tableColumnData = sqlDataConsumer.getTableColumnData();
        boolean hasS = subQueryAliasMap != null && subQueryAliasMap.size() != 0;
        boolean hasF = tableFunctionColumnData != null && tableFunctionColumnData.size() != 0;
        boolean hasV = virtualFieldData != null && virtualFieldData.size() != 0;
        boolean hasC = tableColumnData != null && tableColumnData.size() != 0;
        StringBuilder sql = new StringBuilder(128);
        List<Object> args = new ArrayList<>(16);
        if (!hasS && !hasF && !hasV && !hasC) {
            this.appendColumnSqlArgs(sql, args, BeanUtils.getColumnData(sqlDataConsumer.getMainTableDatum()));
            return SqlBuilderResult.newInstance(sql.toString(), args);
        }
        if (hasS) {
            this.appendSubQuerySqlArgs(sql, args, subQueryAliasMap);
        }
        if (hasF) {
            if (hasS) {
                sql.append(",");
            }
            this.appendTableFunctionColumnSqlArgs(sql, args, tableFunctionColumnData);
        }
        if (hasV) {
            if (hasS || hasF) {
                sql.append(",");
            }
            this.appendVirtualColumnSqlArgs(sql, args, virtualFieldData);
        }
        if (hasC) {
            if (hasS || hasF || hasV) {
                sql.append(",");
            }
            this.appendTableColumnSqlArgs(sql, args, tableColumnData);
        }
        return SqlBuilderResult.newInstance(sql.toString(), args);
    }

    @Override
    public SqlBuilderResult buildJoin(SqlDataConsumer sqlDataConsumer) {
        LinkedHashMap<String, JoinTableDatum> joinTableDataAliasMap = sqlDataConsumer.getAliasJoinTableData();
        if (joinTableDataAliasMap == null || joinTableDataAliasMap.size() == 0) {
            return SqlBuilderResult.NONE;
        }
        StringBuilder sql = new StringBuilder(128);
        List<Object> args = new ArrayList<>(16);
        JoinTableDatum joinTableDatum;
        for (Map.Entry<String, JoinTableDatum> entry : joinTableDataAliasMap.entrySet()) {
            joinTableDatum = entry.getValue();
            switch (joinTableDatum.getJoinType()) {
                case INNER:
                    sql.append(" inner join ");
                    break;
                case LEFT:
                    sql.append(" left join ");
                    break;
                case RIGHT:
                    sql.append(" right join ");
                    break;
                default:
                    continue;
            }
            sql.append("`")
                    .append(joinTableDatum.getTableName())
                    .append("` ")
                    .append(joinTableDatum.getTableAlias());
            List<OnDataLinker> onDataLinkers = joinTableDatum.getTableOnDatum().getOnDataLinkers();
            if (onDataLinkers != null && onDataLinkers.size() > 0) {
                sql.append(" on ");
                this.appendOnDataLinkersSqlArgs(sql, args, onDataLinkers, LinkType.AND, false);
            }
        }
        return SqlBuilderResult.newInstance(sql.toString(), args);
    }

    @Override
    public SqlBuilderResult buildWhere(SqlDataConsumer sqlDataConsumer) {
        List<TableWhereDatum> tableWhereData = sqlDataConsumer.getTableWhereData();
        if (tableWhereData == null || tableWhereData.size() == 0) {
            return SqlBuilderResult.NONE;
        }
        StringBuilder sql = new StringBuilder(128);
        List<Object> args = new ArrayList<>(16);
        sql.append(" where ");
        int i = 0;
        for (TableWhereDatum tableWhereDatum : tableWhereData) {
            if (i++ > 0) {
                sql.append(" and ");
            }
            this.appendWhereDataLinkerListSqlArgs(sql, args, tableWhereDatum.getWhereDataLinkers(), LinkType.AND, tableWhereData.size() > 1);
        }
        return SqlBuilderResult.newInstance(sql.toString(), args);
    }

    @Override
    public SqlBuilderResult buildGroup(SqlDataConsumer sqlDataConsumer) {
        List<TableGroupDatum> tableGroupData = sqlDataConsumer.getTableGroupData();
        if (tableGroupData == null || tableGroupData.size() == 0) {
            return SqlBuilderResult.NONE;
        }
        StringBuilder sql = new StringBuilder(32);
        sql.append(" group by ");
        int i = 0;
        Set<GroupDatum> groupData;
        for (TableGroupDatum tableGroupDatum : tableGroupData) {
            groupData = tableGroupDatum.getGroupData();
            if (groupData == null || groupData.size() == 0) {
                continue;
            }
            for (GroupDatum columnName : groupData) {
                if (i++ > 0) {
                    sql.append(",");
                }
                sql.append(columnName.getTableAlias())
                        .append(".`")
                        .append(columnName.getColumnName())
                        .append("`");
            }
        }
        return SqlBuilderResult.newInstance(sql.toString());
    }

    @Override
    public SqlBuilderResult buildSort(SqlDataConsumer sqlDataConsumer) {
        List<TableSortDatum> tableSortData = sqlDataConsumer.getTableSortData();
        if (tableSortData == null || tableSortData.size() == 0) {
            return SqlBuilderResult.NONE;
        }
        StringBuilder sql = new StringBuilder(32);
        sql.append(" order by ");
        int i = 0;
        Set<SortDatum> sortData;
        for (TableSortDatum tableSortDatum : tableSortData) {
            sortData = tableSortDatum.getSortData();
            if (sortData == null || sortData.size() == 0) {
                continue;
            }
            for (SortDatum sortDatum : sortData) {
                if (i++ > 0) {
                    sql.append(",");
                }
                sql.append(sortDatum.getTableAlias())
                        .append(".`")
                        .append(sortDatum.getColumnName())
                        .append("`");
                switch (sortDatum.getSortType()) {
                    case ASC:
                        sql.append(" asc");
                        continue;
                    case DESC:
                        sql.append(" desc");
                        continue;
                    default:
                        throw new SqlException("the SortType is wrong.");
                }
            }
        }
        return SqlBuilderResult.newInstance(sql.toString());
    }

    @Override
    public SqlBuilderResult buildLimit(SqlDataConsumer sqlDataConsumer) {
        LimitSql limit = sqlDataConsumer.getLimitData();
        if (limit == null) {
            return SqlBuilderResult.NONE;
        }
        return SqlBuilderResult.newInstance(" limit ?,?", Arrays.asList(limit.getLimitStartNum(), limit.getLimitEndNum()));
    }

    private void appendColumnSqlArgs(StringBuilder sql, List<Object> args, Set<ColumnDatum> columnData) {
        int i = 0;
        for (ColumnDatum columnDatum : columnData) {
            if (i++ > 0) {
                sql.append(",");
            } else {
                sql.append(" ");
            }
            sql.append(columnDatum.getTableAlias())
                    .append(".`")
                    .append(columnDatum.getColumnName())
                    .append("` `")
                    .append(columnDatum.getColumnAlias())
                    .append("`");
        }
    }

    private void appendSubQuerySqlArgs(StringBuilder sql, List<Object> args, Map<String, SqlBuilder> subQueryAliasMap) {
        if (subQueryAliasMap == null) {
            return;
        }
        int i = 0;
        for (Map.Entry<String, SqlBuilder> entry : subQueryAliasMap.entrySet()) {
            String alias = entry.getKey();
            SqlBuilder sqlBuilder = entry.getValue();
            if (i++ > 0) {
                sql.append(",(");
            } else {
                sql.append(" (");
            }
            sql.append(sqlBuilder.getPreparedStatementSql()).append(") ").append(alias);
            args.addAll(sqlBuilder.getPreparedStatementArgs());
        }
    }

    private void appendTableFunctionColumnSqlArgs(StringBuilder sql, List<Object> args, List<TableFunctionColumnDatum> tableFunctionColumnData) {
        int i = 0;
        GroupType groupType;
        Set<ColumnDatum> columnData;
        for (TableFunctionColumnDatum tableFunctionColumnDatum : tableFunctionColumnData) {
            columnData = tableFunctionColumnDatum.getColumnData();
            if (columnData == null || columnData.size() == 0) {
                continue;
            }
            groupType = tableFunctionColumnDatum.getGroupType();
            for (ColumnDatum columnDatum : columnData) {
                if (i++ > 0) {
                    sql.append(",");
                } else {
                    sql.append(" ");
                }
                switch (groupType) {
                    case MIN:
                        sql.append("min(");
                        break;
                    case MAX:
                        sql.append("max(");
                        break;
                    case COUNT:
                        sql.append("count(");
                        break;
                    case SUM:
                        sql.append("sum(");
                        break;
                    default:
                        throw new SqlException("the functionColumnType is wrong.");
                }
                sql.append(tableFunctionColumnDatum.getTableAlias())
                        .append(".`")
                        .append(columnDatum.getColumnName())
                        .append("`) `")
                        .append(columnDatum.getColumnAlias())
                        .append("`");
            }
        }
    }

    private void appendVirtualColumnSqlArgs(StringBuilder sql, List<Object> args, Set<VirtualFieldDatum> virtualFieldData) {
        Object value;
        String alias;
        int i = 0;
        for (VirtualFieldDatum virtualFieldDatum : virtualFieldData) {
            if (i++ > 0) {
                sql.append(",");
            } else {
                sql.append(" ");
            }
            value = virtualFieldDatum.getValue();
            alias = virtualFieldDatum.getAlias();
            if (value == null) {
                sql.append("null");
            } else if (value instanceof String) {
                sql.append("'").append((String) value).append("'");
            } else if (value instanceof Integer) {
                sql.append(String.valueOf(value));
            } else if (value instanceof Long) {
                sql.append(String.valueOf(value));
            } else if (value instanceof Double) {
                sql.append(String.valueOf(value));
            } else {
                throw new SqlException("the VirtualFieldData value type is wrong.");
            }
            if (alias != null) {
                sql.append(" `").append(alias).append("`");
            }
        }
    }

    private void appendTableColumnSqlArgs(StringBuilder sql, List<Object> args, List<TableColumnDatum> tableColumnData) {
        int i = 0;
        TableDatum tableDatum;
        Set<ColumnDatum> columnData;
        for (TableColumnDatum tableColumnDatum : tableColumnData) {
            columnData = tableColumnDatum.getColumnData();
            if (columnData.size() == 0) {
                continue;
            }
            for (ColumnDatum columnDatum : columnData) {
                if (i++ > 0) {
                    sql.append(",");
                } else {
                    sql.append(" ");
                }
                sql.append(columnDatum.getTableAlias())
                        .append(".`")
                        .append(columnDatum.getColumnName())
                        .append("` `")
                        .append(columnDatum.getColumnAlias())
                        .append("`");
            }
        }
    }

    private void appendOnDataSqlArgs(StringBuilder sql, List<Object> args, Set<OnDatum> onData, LinkType linkType) {
        if (onData == null || onData.size() == 0) {
            return;
        }
        if (linkType == LinkType.OR && onData.size() > 1) {
            sql.append("(");
        }
        int i = 0;
        for (OnDatum onDatum : onData) {
            if (i++ > 0) {
                sql.append(" and ");
            }
            sql.append(onDatum.getOwnerTableAlias())
                    .append(".`")
                    .append(onDatum.getOwnerColumnName())
                    .append("`");
            switch (onDatum.getOnType()) {
                case IS_NULL:
                    sql.append(" is null");
                    continue;
                case IS_NOT_NULL:
                    sql.append(" is not null");
                    continue;
                case EQUAL:
                    sql.append(" = ");
                    break;
                case NOT_EQUAL:
                    sql.append(" != ");
                    break;
                case GREATER:
                    sql.append(" > ");
                    break;
                case GREATER_EQUAL:
                    sql.append(" >= ");
                    break;
                case LESS:
                    sql.append(" < ");
                    break;
                case LESS_EQUAL:
                    sql.append(" <= ");
                    break;
                case BETWEEN:
                    sql.append(" between ? and ?");
                    args.add(onDatum.getTargetValue());
                    args.add(onDatum.getTargetSecondValue());
                    continue;
                case LIKE:
                    sql.append(" like ?");
                    args.add(onDatum.getTargetValue());
                    continue;
                case IN:
                    int count = onDatum.getValueCount();
                    sql.append(" in (");
                    for (; count > 0; count--) {
                        if (count == 1) {
                            sql.append("?");
                        } else {
                            sql.append("?,");
                        }
                    }
                    sql.append(")");
                    Object value = onDatum.getTargetValue();
                    if (value instanceof Collection) {
                        args.addAll((Collection) value);
                    } else if (value.getClass().isArray()) {
                        args.addAll(Arrays.asList((Object[]) value));
                    } else {
                        throw new SqlException("the value type can only be Array or Collection.");
                    }
                    continue;
                case NOT_IN:
                    count = onDatum.getValueCount();
                    sql.append(" not in (");
                    for (; count > 0; count--) {
                        if (count == 1) {
                            sql.append("?");
                        } else {
                            sql.append("?,");
                        }
                    }
                    sql.append(")");
                    value = onDatum.getTargetValue();
                    if (value instanceof Collection) {
                        args.addAll((Collection) value);
                    } else if (value.getClass().isArray()) {
                        args.addAll(Arrays.asList((Object[]) value));
                    } else {
                        throw new SqlException("the value type can only be Array or Collection.");
                    }
                    continue;
                default:
                    throw new SqlException("the WhereType is wrong.");
            }
            switch (onDatum.getOnValueType()) {
                case VALUE:
                    sql.append("?");
                    args.add(onDatum.getTargetValue());
                    continue;
                case JOIN:
                    sql.append(onDatum.getTargetTableAlias())
                            .append(".`")
                            .append(onDatum.getTargetColumnName())
                            .append("`");
                    continue;
                default:
                    throw new SqlException("the OnValueType is wrong.");
            }
        }
        if (linkType == LinkType.OR && onData.size() > 1) {
            sql.append(")");
        }
    }

    private void appendOnDataLinkersSqlArgs(StringBuilder sql, List<Object> args, List<OnDataLinker> onDataLinkers, LinkType linkType, boolean checkBrackets) {
        if (onDataLinkers == null || onDataLinkers.size() == 0) {
            return;
        }
        int length = sql.length();
        Set<OnDatum> onData;
        int i = 0;
        boolean brackets = false;
        for (OnDataLinker onDataLinker : onDataLinkers) {
            onData = onDataLinker.getOnData();
            List<OnDataLinker> childOnDataLinkers = onDataLinker.getOnDataLinkers();
            if (onData != null && onData.size() > 0) {
                switch (onDataLinker.getLinkType()) {
                    case AND:
                        if (i++ > 0) {
                            sql.append(" and ");
                        }
                        this.appendOnDataSqlArgs(sql, args, onData, LinkType.AND);
                        continue;
                    case OR:
                        if (i++ > 0) {
                            sql.append(" or ");
                            brackets = checkBrackets;
                        }
                        this.appendOnDataSqlArgs(sql, args, onData, LinkType.OR);
                        continue;
                    default:
                        throw new SqlException("the LinkType is wrong.");
                }
            } else if (childOnDataLinkers != null && childOnDataLinkers.size() > 0) {
                switch (onDataLinker.getLinkType()) {
                    case AND:
                        if (i++ > 0) {
                            sql.append(" and ");
                        }
                        this.appendOnDataLinkersSqlArgs(sql, args, childOnDataLinkers, LinkType.AND, true);
                        continue;
                    case OR:
                        if (i++ > 0) {
                            sql.append(" or ");
                            brackets = checkBrackets;
                        }
                        this.appendOnDataLinkersSqlArgs(sql, args, childOnDataLinkers, LinkType.OR, true);
                        continue;
                    default:
                        throw new SqlException("the LinkType is wrong.");
                }
            }
        }
        if (!checkBrackets) {
            return;
        }
        brackets = brackets || linkType == LinkType.OR && i > 1;
        if (!brackets) {
            return;
        }
        sql.insert(length, "(").append(")");
    }

    private void appendWhereDataValueSqlArgs(StringBuilder sql, List<Object> args, WhereDatum whereDatum) {
        switch (whereDatum.getWhereType()) {
            case IS_NULL:
                sql.append(" is null");
                break;
            case IS_NOT_NULL:
                sql.append(" is not null");
                break;
            case EQUAL:
                sql.append(" = ?");
                args.add(whereDatum.getTargetValue());
                break;
            case NOT_EQUAL:
                sql.append(" != ?");
                args.add(whereDatum.getTargetValue());
                break;
            case GREATER:
                sql.append(" > ?");
                args.add(whereDatum.getTargetValue());
                break;
            case GREATER_EQUAL:
                sql.append(" >= ?");
                args.add(whereDatum.getTargetValue());
                break;
            case LESS:
                sql.append(" < ?");
                args.add(whereDatum.getTargetValue());
                break;
            case LESS_EQUAL:
                sql.append(" <= ?");
                args.add(whereDatum.getTargetValue());
                break;
            case BETWEEN:
                sql.append(" between ? and ?");
                args.add(whereDatum.getTargetValue());
                args.add(whereDatum.getTargetSecondValue());
                break;
            case LIKE:
                sql.append(" like ?");
                args.add(whereDatum.getTargetValue());
                break;
            case IN:
                sql.append(" in (");
                int count = whereDatum.getValueCount();
                for (; count > 0; count--) {
                    if (count == 1) {
                        sql.append("?");
                    } else {
                        sql.append("?,");
                    }
                }
                sql.append(")");
                Object value = whereDatum.getTargetValue();
                if (value instanceof Collection) {
                    args.addAll((Collection) value);
                } else if (value.getClass().isArray()) {
                    args.addAll(Arrays.asList((Object[]) value));
                } else {
                    throw new SqlException("the value type can only be Array or Collection.");
                }
                break;
            case NOT_IN:
                sql.append(" not in (");
                count = whereDatum.getValueCount();
                for (; count > 0; count--) {
                    if (count == 1) {
                        sql.append("?");
                    } else {
                        sql.append("?,");
                    }
                }
                sql.append(")");
                value = whereDatum.getTargetValue();
                if (value instanceof Collection) {
                    args.addAll((Collection) value);
                } else if (value.getClass().isArray()) {
                    args.addAll(Arrays.asList((Object[]) value));
                } else {
                    throw new SqlException("the value type can only be Array or Collection.");
                }
                break;
            default:
                throw new SqlException("the WhereType is wrong.");
        }
    }

    private void appendWhereDataJoinSqlArgs(StringBuilder sql, List<Object> args, WhereDatum whereDatum) {
        switch (whereDatum.getWhereType()) {
            case IS_NULL:
                sql.append(" is null");
                break;
            case IS_NOT_NULL:
                sql.append(" is not null");
                break;
            case EQUAL:
                sql.append(" = ")
                        .append(whereDatum.getTargetTableAlias())
                        .append(".`")
                        .append(whereDatum.getTargetColumnName())
                        .append("`");
                break;
            case NOT_EQUAL:
                sql.append(" != ")
                        .append(whereDatum.getTargetTableAlias())
                        .append(".`")
                        .append(whereDatum.getTargetColumnName())
                        .append("`");
                break;
            case GREATER:
                sql.append(" > ")
                        .append(whereDatum.getTargetTableAlias())
                        .append(".`")
                        .append(whereDatum.getTargetColumnName())
                        .append("`");
                break;
            case GREATER_EQUAL:
                sql.append(" >= ")
                        .append(whereDatum.getTargetTableAlias())
                        .append(".`")
                        .append(whereDatum.getTargetColumnName())
                        .append("`");
                break;
            case LESS:
                sql.append(" < ")
                        .append(whereDatum.getTargetTableAlias())
                        .append(".`")
                        .append(whereDatum.getTargetColumnName())
                        .append("`");
                break;
            case LESS_EQUAL:
                sql.append(" <= ")
                        .append(whereDatum.getTargetTableAlias())
                        .append(".`")
                        .append(whereDatum.getTargetColumnName())
                        .append("`");
                break;
            case BETWEEN:
                sql.append(" between ")
                        .append(whereDatum.getTargetTableAlias())
                        .append(".`")
                        .append(whereDatum.getTargetColumnName())
                        .append("` and ")
                        .append(whereDatum.getTargetSecondTableAlias())
                        .append(".`")
                        .append(whereDatum.getTargetSecondColumnName())
                        .append("`");
                break;
            case LIKE:
                sql.append(" like ")
                        .append(whereDatum.getTargetTableAlias())
                        .append(".`")
                        .append(whereDatum.getTargetColumnName())
                        .append("`");
                break;
            case IN:
                WhereDatum[] whereData = whereDatum.getTargetWhereData();
                if (whereData == null || whereData.length == 0) {
                    break;
                }
                sql.append(" in (");
                for (int i = 0; i < whereData.length; i++) {
                    if (i > 0) {
                        sql.append(",");
                    }
                    sql.append(whereData[i].getOwnerTableAlias())
                            .append(".`")
                            .append(whereData[i].getOwnerColumnName())
                            .append("`");
                }
                sql.append(")");
                break;
            case NOT_IN:
                whereData = whereDatum.getTargetWhereData();
                if (whereData == null || whereData.length == 0) {
                    break;
                }
                sql.append(" not in (");
                for (int i = 0; i < whereData.length; i++) {
                    if (i > 0) {
                        sql.append(",");
                    }
                    sql.append(whereData[i].getOwnerTableAlias())
                            .append(".`")
                            .append(whereData[i].getOwnerColumnName())
                            .append("`");
                }
                sql.append(")");
                break;
            default:
                throw new SqlException("the WhereType is wrong.");
        }
    }

    private void appendWhereDataColumnSqlArgs(StringBuilder sql, List<Object> args, WhereDatum whereDatum) {
        Set<ColumnDatum> columnData = whereDatum.getTargetColumnData();
        if (columnData == null || columnData.size() == 0) {
            return;
        }
        int i = 0;
        switch (whereDatum.getWhereType()) {
            case IS_NULL:
                sql.append(" is null");
                break;
            case IS_NOT_NULL:
                sql.append(" is not null");
                break;
            case EQUAL:
                sql.append(" = ");
                for (ColumnDatum columnDatum : columnData) {
                    if (i++ > 0) {
                        sql.append(" and ");
                    }
                    sql.append(columnDatum.getTableAlias())
                            .append(".`")
                            .append(columnDatum.getColumnName())
                            .append("`");
                }
                break;
            case NOT_EQUAL:
                sql.append(" != ");
                for (ColumnDatum columnDatum : columnData) {
                    if (i++ > 0) {
                        sql.append(" and ");
                    }
                    sql.append(columnDatum.getTableAlias())
                            .append(".`")
                            .append(columnDatum.getColumnName())
                            .append("`");
                }
                break;
            case GREATER:
                sql.append(" > ");
                for (ColumnDatum columnDatum : columnData) {
                    if (i++ > 0) {
                        sql.append(" and ");
                    }
                    sql.append(columnDatum.getTableAlias())
                            .append(".`")
                            .append(columnDatum.getColumnName())
                            .append("`");
                }
                break;
            case GREATER_EQUAL:
                sql.append(" >= ");
                for (ColumnDatum columnDatum : columnData) {
                    if (i++ > 0) {
                        sql.append(" and ");
                    }
                    sql.append(columnDatum.getTableAlias())
                            .append(".`")
                            .append(columnDatum.getColumnName())
                            .append("`");
                }
                break;
            case LESS:
                sql.append(" < ");
                for (ColumnDatum columnDatum : columnData) {
                    if (i++ > 0) {
                        sql.append(" and ");
                    }
                    sql.append(columnDatum.getTableAlias())
                            .append(".`")
                            .append(columnDatum.getColumnName())
                            .append("`");
                }
                break;
            case LESS_EQUAL:
                sql.append(" <= ");
                for (ColumnDatum columnDatum : columnData) {
                    if (i++ > 0) {
                        sql.append(" and ");
                    }
                    sql.append(columnDatum.getTableAlias())
                            .append(".`")
                            .append(columnDatum.getColumnName())
                            .append("`");
                }
                break;
            case BETWEEN:
                sql.append(" between ");
                for (ColumnDatum columnDatum : columnData) {
                    if (i++ > 0) {
                        sql.append(" and ");
                    }
                    sql.append(columnDatum.getTableAlias())
                            .append(".`")
                            .append(columnDatum.getColumnName())
                            .append("`");
                    if (i >= 2) {
                        break;
                    }
                }
                break;
            case LIKE:
                sql.append(" like ");
                for (ColumnDatum columnDatum : columnData) {
                    if (i++ > 0) {
                        sql.append(" and ");
                    }
                    sql.append(columnDatum.getTableAlias())
                            .append(".`")
                            .append(columnDatum.getColumnName())
                            .append("`");
                }
                break;
            case IN:
                sql.append(" in (");
                for (ColumnDatum columnDatum : columnData) {
                    if (i++ > 0) {
                        sql.append(",");
                    }
                    sql.append(columnDatum.getTableAlias())
                            .append(".`")
                            .append(columnDatum.getColumnName())
                            .append("`");
                }
                sql.append(")");
                break;
            case NOT_IN:
                sql.append(" not in (");
                for (ColumnDatum columnDatum : columnData) {
                    if (i++ > 0) {
                        sql.append(",");
                    }
                    sql.append(columnDatum.getTableAlias())
                            .append(".`")
                            .append(columnDatum.getColumnName())
                            .append("`");
                }
                sql.append(")");
                break;
            default:
                throw new SqlException("the WhereType is wrong.");
        }
    }

    private void appendWhereDataSubQuerySqlArgs(StringBuilder sql, List<Object> args, WhereDatum whereDatum) {
        switch (whereDatum.getWhereType()) {
            case IS_NULL:
                sql.append(" is null");
                break;
            case IS_NOT_NULL:
                sql.append(" is not null");
                break;
            case BETWEEN:
                sql.append(" between ? and ?");
                // TODO 后续添加
                throw new SqlException("暂不支持");
                // TODO 别忘记break
            case EQUAL:
                sql.append(" = (")
                        .append(whereDatum.getTargetSubQuery().getPreparedStatementSql())
                        .append(")");
                args.addAll(whereDatum.getTargetSubQuery().getPreparedStatementArgs());
                break;
            case NOT_EQUAL:
                sql.append(" != (")
                        .append(whereDatum.getTargetSubQuery().getPreparedStatementSql())
                        .append(")");
                args.addAll(whereDatum.getTargetSubQuery().getPreparedStatementArgs());
                break;
            case GREATER:
                sql.append(" > (")
                        .append(whereDatum.getTargetSubQuery().getPreparedStatementSql())
                        .append(")");
                args.addAll(whereDatum.getTargetSubQuery().getPreparedStatementArgs());
                break;
            case GREATER_EQUAL:
                sql.append(" >= (")
                        .append(whereDatum.getTargetSubQuery().getPreparedStatementSql())
                        .append(")");
                args.addAll(whereDatum.getTargetSubQuery().getPreparedStatementArgs());
                break;
            case LESS:
                sql.append(" < (")
                        .append(whereDatum.getTargetSubQuery().getPreparedStatementSql())
                        .append(")");
                args.addAll(whereDatum.getTargetSubQuery().getPreparedStatementArgs());
                break;
            case LESS_EQUAL:
                sql.append(" <= (")
                        .append(whereDatum.getTargetSubQuery().getPreparedStatementSql())
                        .append(")");
                args.addAll(whereDatum.getTargetSubQuery().getPreparedStatementArgs());
                break;
            case LIKE:
                sql.append(" like (")
                        .append(whereDatum.getTargetSubQuery().getPreparedStatementSql())
                        .append(")");
                args.addAll(whereDatum.getTargetSubQuery().getPreparedStatementArgs());
                break;
            case IN:
                sql.append(" in (")
                        .append(whereDatum.getTargetSubQuery().getPreparedStatementSql())
                        .append(")");
                args.addAll(whereDatum.getTargetSubQuery().getPreparedStatementArgs());
                break;
            case NOT_IN:
                sql.append(" not in (")
                        .append(whereDatum.getTargetSubQuery().getPreparedStatementSql())
                        .append(")");
                args.addAll(whereDatum.getTargetSubQuery().getPreparedStatementArgs());
                break;
            default:
                throw new SqlException("the WhereType is wrong.");
        }
    }

    private void appendWhereDataSqlPartSqlArgs(StringBuilder sql, List<Object> args, WhereDatum whereDatum) {
        sql.append(" ").append(whereDatum.getSqlPart());
    }

    private void appendWhereDataSqlArgs(StringBuilder sql, List<Object> args, WhereDatum whereDatum) {
        switch (whereDatum.getWhereValueType()) {
            case VALUE:
                this.appendWhereDataValueSqlArgs(sql, args, whereDatum);
                break;
            case JOIN:
                this.appendWhereDataJoinSqlArgs(sql, args, whereDatum);
                break;
            case COLUMN:
                this.appendWhereDataColumnSqlArgs(sql, args, whereDatum);
                break;
            case SUB_QUERY:
                this.appendWhereDataSubQuerySqlArgs(sql, args, whereDatum);
                break;
            case SQL_PART:
                this.appendWhereDataSqlPartSqlArgs(sql, args, whereDatum);
                break;
            default:
                throw new SqlException("the WhereValueType is wrong.");
        }
    }

    private void appendWhereDataListSqlArgs(StringBuilder sql, List<Object> args, Set<WhereDatum> whereData, LinkType linkType) {
        if (whereData == null || whereData.size() == 0) {
            return;
        }
        if (linkType == LinkType.OR && whereData.size() > 1) {
            sql.append("(");
        }
        int i = 0;
        for (WhereDatum whereDatum : whereData) {
            if (i++ > 0) {
                sql.append(" and ");
            }
            sql.append(whereDatum.getOwnerTableAlias())
                    .append(".`")
                    .append(whereDatum.getOwnerColumnName())
                    .append("`");
            this.appendWhereDataSqlArgs(sql, args, whereDatum);
        }
        if (linkType == LinkType.OR && whereData.size() > 1) {
            sql.append(")");
        }
    }

    private void appendWhereDataLinkerListSqlArgs(StringBuilder sql, List<Object> args, List<WhereDataLinker> whereDataLinkerList, LinkType linkType, boolean checkBrackets) {
        if (whereDataLinkerList == null || whereDataLinkerList.size() == 0) {
            return;
        }
        int length = sql.length();
        Set<WhereDatum> whereData;
        int i = 0;
        boolean brackets = false;
        for (WhereDataLinker whereDataLinker : whereDataLinkerList) {
            whereData = whereDataLinker.getWhereData();
            List<WhereDataLinker> childWhereDataLinkers = whereDataLinker.getWhereDataLinkers();
            if (whereData != null && whereData.size() > 0) {
                switch (whereDataLinker.getLinkType()) {
                    case AND:
                        if (i++ > 0) {
                            sql.append(" and ");
                        }
                        this.appendWhereDataListSqlArgs(sql, args, whereData, LinkType.AND);
                        continue;
                    case OR:
                        if (i++ > 0) {
                            sql.append(" or ");
                            brackets = checkBrackets;
                        }
                        this.appendWhereDataListSqlArgs(sql, args, whereData, LinkType.OR);
                        continue;
                    default:
                        throw new SqlException("the LinkType is wrong.");
                }
            } else if (childWhereDataLinkers != null && childWhereDataLinkers.size() > 0) {
                switch (whereDataLinker.getLinkType()) {
                    case AND:
                        if (i++ > 0) {
                            sql.append(" and ");
                        }
                        this.appendWhereDataLinkerListSqlArgs(sql, args, childWhereDataLinkers, LinkType.AND, true);
                        continue;
                    case OR:
                        if (i++ > 0) {
                            sql.append(" or ");
                            brackets = checkBrackets;
                        }
                        this.appendWhereDataLinkerListSqlArgs(sql, args, childWhereDataLinkers, LinkType.OR, true);
                        continue;
                    default:
                        throw new SqlException("the LinkType is wrong.");
                }
            }
        }
        if (!checkBrackets) {
            return;
        }
        brackets = brackets || linkType == LinkType.OR && i > 1;
        if (!brackets) {
            return;
        }
        sql.insert(length, "(").append(")");
    }


}
