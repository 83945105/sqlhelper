package pub.avalon.sqlhelper.core.sqlbuilder.template;

import pub.avalon.beans.Pagination;
import pub.avalon.sqlhelper.core.beans.BeanUtils;
import pub.avalon.sqlhelper.core.beans.ColumnHandler;
import pub.avalon.sqlhelper.core.beans.LinkType;
import pub.avalon.sqlhelper.core.data.*;
import pub.avalon.sqlhelper.core.exception.SqlException;
import pub.avalon.sqlhelper.core.sqlbuilder.beans.FinalSqlBuilderResult;
import pub.avalon.sqlhelper.core.sqlbuilder.beans.SqlBuilderResult;
import pub.avalon.sqlhelper.core.utils.ExceptionUtils;

import java.util.*;

/**
 * 默认MySql片段构建器模板
 *
 * @author baichao
 * @date 2018/8/23
 */
public final class DefaultMySqlPartBuilderTemplate implements MySqlPartBuilderTemplate {

    public final static DefaultMySqlPartBuilderTemplate DEFAULT_DEFAULT_MY_SQL_PART_BUILDER_TEMPLATE = new DefaultMySqlPartBuilderTemplate();

    @Override
    public SqlBuilderResult buildColumn(SqlDataConsumer sqlDataConsumer) {
        List<TableColumnDatum> tableColumnData = sqlDataConsumer.getTableColumnData();
        boolean hasC = tableColumnData != null && tableColumnData.size() != 0;
        StringBuilder sql = new StringBuilder(128);
        List<Object> args = new ArrayList<>(16);
        if (!hasC) {
            this.appendColumnSqlArgs(sql, args, BeanUtils.getColumnData(sqlDataConsumer.getMainTableDatum()));
            return FinalSqlBuilderResult.newInstance(sql.toString(), args);
        }
        this.appendTableColumnSqlArgs(sql, args, tableColumnData);
        return FinalSqlBuilderResult.newInstance(sql.toString(), args);
    }

    @Override
    public SqlBuilderResult buildJoin(SqlDataConsumer sqlDataConsumer) {
        LinkedHashMap<String, JoinTableDatum> joinTableDataAliasMap = sqlDataConsumer.getAliasJoinTableData();
        if (joinTableDataAliasMap == null || joinTableDataAliasMap.size() == 0) {
            return FinalSqlBuilderResult.NONE;
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
        return FinalSqlBuilderResult.newInstance(sql.toString(), args);
    }

    @Override
    public SqlBuilderResult buildWhere(SqlDataConsumer sqlDataConsumer) {
        List<TableWhereDatum> tableWhereData = sqlDataConsumer.getTableWhereData();
        if (tableWhereData == null || tableWhereData.size() == 0) {
            return FinalSqlBuilderResult.NONE;
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
        return FinalSqlBuilderResult.newInstance(sql.toString(), args);
    }

    @Override
    public SqlBuilderResult buildGroup(SqlDataConsumer sqlDataConsumer) {
        List<TableGroupDatum> tableGroupData = sqlDataConsumer.getTableGroupData();
        if (tableGroupData == null || tableGroupData.size() == 0) {
            return FinalSqlBuilderResult.NONE;
        }
        StringBuilder sql = new StringBuilder(32);
        sql.append(" group by ");
        int i = 0;
        List<GroupDatum> groupData;
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
        return FinalSqlBuilderResult.newInstance(sql.toString());
    }

    @Override
    public SqlBuilderResult buildSort(SqlDataConsumer sqlDataConsumer) {
        List<TableSortDatum> tableSortData = sqlDataConsumer.getTableSortData();
        if (tableSortData == null || tableSortData.size() == 0) {
            return FinalSqlBuilderResult.NONE;
        }
        StringBuilder sql = new StringBuilder(32);
        sql.append(" order by ");
        int i = 0;
        List<SortDatum> sortData;
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
        return FinalSqlBuilderResult.newInstance(sql.toString());
    }

    @Override
    public SqlBuilderResult buildLimit(SqlDataConsumer sqlDataConsumer) {
        LimitDatum limitDatum = sqlDataConsumer.getLimitDatum();
        if (limitDatum == null) {
            return FinalSqlBuilderResult.NONE;
        }
        Pagination pagination = new Pagination(sqlDataConsumer.getDataBaseType(), limitDatum.getTotal(), limitDatum.getCurrentPage(), limitDatum.getPageSize());
        return FinalSqlBuilderResult.newInstance(" limit ?,?", Arrays.asList(pagination.getLimitStartNum(), pagination.getLimitEndNum()));
    }

    private void appendColumnSqlArgs(StringBuilder sql, List<Object> args, List<ColumnDatum> columnData) {
        int i = 0;
        for (ColumnDatum columnDatum : columnData) {
            if (i++ > 0) {
                sql.append(",");
            } else {
                sql.append(" ");
            }
            switch (columnDatum.getType()) {
                case DEFAULT:
                    sql.append(columnDatum.getTableAlias())
                            .append(".`")
                            .append(columnDatum.getColumnName())
                            .append("` `")
                            .append(columnDatum.getColumnAlias())
                            .append("`");
                    continue;
                case VIRTUAL:
                    Object columnValue = columnDatum.getColumnValue();
                    if (columnValue == null) {
                        sql.append("null");
                    } else if (columnValue instanceof Integer) {
                        sql.append(String.valueOf(columnValue));
                    } else if (columnValue instanceof Long) {
                        sql.append(String.valueOf(columnValue));
                    } else if (columnValue instanceof Double) {
                        sql.append(String.valueOf(columnValue));
                    } else {
                        sql.append("'").append(columnValue).append("'");
                    }
                    sql.append(" `").append(columnDatum.getColumnAlias()).append("`");
                    continue;
                case SUB_QUERY:
                    sql.append("(");
                    SqlBuilderResult sqlBuilderResult = columnDatum.getSqlBuilderResult();
                    sql.append(sqlBuilderResult.getPreparedStatementSql()).append(") `").append(columnDatum.getColumnAlias()).append("`");
                    args.addAll(sqlBuilderResult.getPreparedStatementArgs());
                    continue;
                case HANDLER:
                    ColumnHandler[] columnHandlers = columnDatum.getColumnHandlers();
                    String columnSql = columnDatum.getTableAlias() + ".`" + columnDatum.getColumnName() + "`";
                    if (columnHandlers != null && columnHandlers.length > 0) {
                        for (ColumnHandler columnHandler : columnHandlers) {
                            columnSql = columnHandler.execute(columnSql);
                        }
                    }
                    sql.append(columnSql)
                            .append(" `")
                            .append(columnDatum.getColumnAlias())
                            .append("`");
                    continue;
                case SQL_PART:
                    sql.append(columnDatum.getSqlPart());
                    continue;
                default:
                    ExceptionUtils.columnTypeNotSupportException();
            }
        }
    }

    private void appendTableColumnSqlArgs(StringBuilder sql, List<Object> args, List<TableColumnDatum> tableColumnData) {
        int i = 0;
        List<ColumnDatum> columnData;
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
                switch (columnDatum.getType()) {
                    case DEFAULT:
                        sql.append(columnDatum.getTableAlias())
                                .append(".`")
                                .append(columnDatum.getColumnName())
                                .append("` `")
                                .append(columnDatum.getColumnAlias())
                                .append("`");
                        continue;
                    case VIRTUAL:
                        Object columnValue = columnDatum.getColumnValue();
                        if (columnValue == null) {
                            sql.append("null");
                        } else if (columnValue instanceof Integer) {
                            sql.append(String.valueOf(columnValue));
                        } else if (columnValue instanceof Long) {
                            sql.append(String.valueOf(columnValue));
                        } else if (columnValue instanceof Double) {
                            sql.append(String.valueOf(columnValue));
                        } else {
                            sql.append("'").append(columnValue).append("'");
                        }
                        sql.append(" `").append(columnDatum.getColumnAlias()).append("`");
                        continue;
                    case SUB_QUERY:
                        sql.append("(");
                        SqlBuilderResult sqlBuilderResult = columnDatum.getSqlBuilderResult();
                        sql.append(sqlBuilderResult.getPreparedStatementSql()).append(") `").append(columnDatum.getColumnAlias()).append("`");
                        args.addAll(sqlBuilderResult.getPreparedStatementArgs());
                        continue;
                    case HANDLER:
                        ColumnHandler[] columnHandlers = columnDatum.getColumnHandlers();
                        String columnSql = columnDatum.getTableAlias() + ".`" + columnDatum.getColumnName() + "`";
                        if (columnHandlers != null && columnHandlers.length > 0) {
                            for (ColumnHandler columnHandler : columnHandlers) {
                                columnSql = columnHandler.execute(columnSql);
                            }
                        }
                        sql.append(columnSql)
                                .append(" `")
                                .append(columnDatum.getColumnAlias())
                                .append("`");
                        continue;
                    case SQL_PART:
                        sql.append(columnDatum.getSqlPart());
                        continue;
                    default:
                        ExceptionUtils.columnTypeNotSupportException();
                }
            }
        }
    }

    private void appendOnDataSqlArgs(StringBuilder sql, List<Object> args, List<OnDatum> onData, LinkType linkType) {
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
            sql.append(onDatum.getTableAlias())
                    .append(".`")
                    .append(onDatum.getColumnName())
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
            switch (onDatum.getType()) {
                case VALUE:
                    sql.append("?");
                    args.add(onDatum.getTargetValue());
                    continue;
                case JOIN_ON:
                    List<OnDatum> onDatumList = onDatum.getTargetOnData();
                    // TODO 暂时只支持单个对象条件
                    sql.append(onDatumList.get(0).getTableAlias())
                            .append(".`")
                            .append(onDatumList.get(0).getColumnName())
                            .append("`");
                    continue;
                case JOIN_COLUMN:
                    List<ColumnDatum> columnData = onDatum.getTargetColumnData();
                    // TODO 暂时只支持单个对象条件
                    sql.append(columnData.get(0).getTableAlias())
                            .append(".`")
                            .append(columnData.get(0).getColumnName())
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
        List<OnDatum> onData;
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

    private void appendWhereDataJoinWhereSqlArgs(StringBuilder sql, List<Object> args, WhereDatum whereDatum) {
        List<WhereDatum> targetWhereData = whereDatum.getTargetWhereData();
        switch (whereDatum.getWhereType()) {
            case IS_NULL:
                sql.append(" is null");
                break;
            case IS_NOT_NULL:
                sql.append(" is not null");
                break;
            case EQUAL:
                sql.append(" = ")
                        .append(targetWhereData.get(0).getTableAlias())
                        .append(".`")
                        .append(targetWhereData.get(0).getColumnName())
                        .append("`");
                break;
            case NOT_EQUAL:
                sql.append(" != ")
                        .append(targetWhereData.get(0).getTableAlias())
                        .append(".`")
                        .append(targetWhereData.get(0).getColumnName())
                        .append("`");
                break;
            case GREATER:
                sql.append(" > ")
                        .append(targetWhereData.get(0).getTableAlias())
                        .append(".`")
                        .append(targetWhereData.get(0).getColumnName())
                        .append("`");
                break;
            case GREATER_EQUAL:
                sql.append(" >= ")
                        .append(targetWhereData.get(0).getTableAlias())
                        .append(".`")
                        .append(targetWhereData.get(0).getColumnName())
                        .append("`");
                break;
            case LESS:
                sql.append(" < ")
                        .append(targetWhereData.get(0).getTableAlias())
                        .append(".`")
                        .append(targetWhereData.get(0).getColumnName())
                        .append("`");
                break;
            case LESS_EQUAL:
                sql.append(" <= ")
                        .append(targetWhereData.get(0).getTableAlias())
                        .append(".`")
                        .append(targetWhereData.get(0).getColumnName())
                        .append("`");
                break;
            case BETWEEN:
                sql.append(" between ")
                        .append(targetWhereData.get(0).getTableAlias())
                        .append(".`")
                        .append(targetWhereData.get(0).getColumnName())
                        .append("` and ")
                        .append(targetWhereData.get(1).getTableAlias())
                        .append(".`")
                        .append(targetWhereData.get(1).getColumnName())
                        .append("`");
                break;
            case LIKE:
                sql.append(" like ")
                        .append(targetWhereData.get(0).getTableAlias())
                        .append(".`")
                        .append(targetWhereData.get(0).getColumnName())
                        .append("`");
                break;
            case IN:
                sql.append(" in (");
                for (int i = 0; i < targetWhereData.size(); i++) {
                    if (i > 0) {
                        sql.append(",");
                    }
                    sql.append(targetWhereData.get(i).getTableAlias())
                            .append(".`")
                            .append(targetWhereData.get(i).getColumnName())
                            .append("`");
                }
                sql.append(")");
                break;
            case NOT_IN:
                sql.append(" not in (");
                for (int i = 0; i < targetWhereData.size(); i++) {
                    if (i > 0) {
                        sql.append(",");
                    }
                    sql.append(targetWhereData.get(i).getTableAlias())
                            .append(".`")
                            .append(targetWhereData.get(i).getColumnName())
                            .append("`");
                }
                sql.append(")");
                break;
            default:
                throw new SqlException("the WhereType is wrong.");
        }
    }

    private void appendWhereDataJoinColumnSqlArgs(StringBuilder sql, List<Object> args, WhereDatum whereDatum) {
        List<ColumnDatum> columnData = whereDatum.getTargetColumnData();
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
        switch (whereDatum.getType()) {
            case VALUE:
                this.appendWhereDataValueSqlArgs(sql, args, whereDatum);
                break;
            case JOIN_WHERE:
                this.appendWhereDataJoinWhereSqlArgs(sql, args, whereDatum);
                break;
            case JOIN_COLUMN:
                this.appendWhereDataJoinColumnSqlArgs(sql, args, whereDatum);
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

    private void appendWhereDataListSqlArgs(StringBuilder sql, List<Object> args, List<WhereDatum> whereData, LinkType linkType) {
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
            sql.append(whereDatum.getTableAlias())
                    .append(".`")
                    .append(whereDatum.getColumnName())
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
        List<WhereDatum> whereData;
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
