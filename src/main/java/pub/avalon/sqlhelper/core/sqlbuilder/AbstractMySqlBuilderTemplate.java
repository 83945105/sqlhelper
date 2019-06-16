package pub.avalon.sqlhelper.core.sqlbuilder;

import pub.avalon.beans.LimitHandler;
import pub.avalon.sqlhelper.core.beans.BeanUtils;
import pub.avalon.sqlhelper.core.beans.FunctionColumnType;
import pub.avalon.sqlhelper.core.beans.LinkType;
import pub.avalon.sqlhelper.core.data.*;
import pub.avalon.sqlhelper.core.exception.SqlException;
import pub.avalon.sqlhelper.core.exception.TableDataException;
import pub.avalon.sqlhelper.core.helper.TableHelper;

import java.util.*;

/**
 * @author 白超
 * @date 2018/8/23
 */
public abstract class AbstractMySqlBuilderTemplate implements MySqlBuilderTemplate {

    private Map<String, Boolean> aliasSingleValidator = new HashMap<>(32);

    /*@Override
    public String getSql() {
        //TODO 待实现 通过正则表达式将预编译参数替换进预编译sql
        return null;
    }

    @Override
    public String getPreparedStatementSql() {
        String sql = this.preparedStatementSql.toString();
        if (this.sqlEnabled && this.logger.isDebugEnabled()) {
            if (this.colour) {
                logger.debug("sqlhelper PreparedStatementSQL  [" + sql + "]");
            } else {
                logger.debug(Ansi.ansi().eraseScreen()
                        .fg(Ansi.Color.CYAN)
                        .a("sqlhelper ")
                        .fg(Ansi.Color.YELLOW)
                        .a("PreparedStatementSQL  [" + sql + "]")
                        .reset());
            }
        }
        return sql;
    }

    @Override
    public List<Object> getPreparedStatementArgs() {
        if (this.argsEnabled && this.logger.isDebugEnabled()) {
            if (this.colour) {
                logger.debug("sqlhelper PreparedStatementArgs " + this.preparedStatementArgs.toString());
            } else {
                logger.debug(Ansi.ansi().eraseScreen()
                        .fg(Ansi.Color.CYAN)
                        .a("sqlhelper ")
                        .fg(Ansi.Color.RED)
                        .a("PreparedStatementArgs " + this.preparedStatementArgs.toString())
                        .reset());
            }
        }
        return this.preparedStatementArgs;
    }*/

    protected void appendColumnSqlArgs(StringBuilder sql, List<Object> args, Set<ColumnDatum> columnData) {
        int i = 0;
        for (ColumnDatum columnDatum : columnData) {
            if (i++ > 0) {
                sql.append(",");
            } else {
                sql.append(" ");
            }
            sql.append(columnDatum.getOwnerTableAlias())
                    .append(".`")
                    .append(columnDatum.getOwnerColumnName())
                    .append("` `")
                    .append(columnDatum.getOwnerColumnAlias())
                    .append("`");
        }
    }

    protected void appendSubQuerySqlArgs(StringBuilder sql, List<Object> args, Map<String, SqlBuilder> subQueryAliasMap) {
        if (subQueryAliasMap == null) {
            return;
        }
        int i = 0;
        for (Map.Entry<String, SqlBuilder> entry : subQueryAliasMap.entrySet()) {
            String alias = entry.getKey();
            SqlBuilder sqlBuilder = entry.getValue();
            if (this.aliasSingleValidator.get(alias) != null) {
                throw new TableDataException("SubQueryColumn alias [" + alias + "] is already be used, please set another alias.");
            }
            if (i++ > 0) {
                sql.append(",(");
            } else {
                sql.append(" (");
            }
            sql.append(sqlBuilder.getPreparedStatementSql()).append(") ").append(alias);
            args.addAll(sqlBuilder.getPreparedStatementArgs());
        }
    }

    protected void appendTableFunctionColumnSqlArgs(StringBuilder sql, List<Object> args, Set<TableFunctionColumnDatum> tableFunctionColumnData) {
        int i = 0;
        FunctionColumnType functionColumnType;
        Set<ColumnDatum> columnData;
        for (TableFunctionColumnDatum tableFunctionColumnDatum : tableFunctionColumnData) {
            columnData = tableFunctionColumnDatum.getColumnData();
            if (columnData == null || columnData.size() == 0) {
                continue;
            }
            functionColumnType = tableFunctionColumnDatum.getFunctionColumnType();
            for (ColumnDatum columnDatum : columnData) {
                if (this.aliasSingleValidator.get(columnDatum.getOwnerColumnAlias()) != null) {
                    throw new TableDataException("FunctionColumn alias [" + columnDatum.getOwnerColumnAlias() + "] is already be used, please set another alias.");
                }
                if (i++ > 0) {
                    sql.append(",");
                } else {
                    sql.append(" ");
                }
                switch (functionColumnType) {
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
                        .append(columnDatum.getOwnerColumnName())
                        .append("`) `")
                        .append(columnDatum.getOwnerColumnAlias())
                        .append("`");
                this.aliasSingleValidator.put(columnDatum.getOwnerColumnAlias(), true);
            }
        }
    }

    protected void appendVirtualColumnSqlArgs(StringBuilder sql, List<Object> args, Set<VirtualFieldDatum> virtualFieldData) {
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
            if (this.aliasSingleValidator.get(alias) != null) {
                throw new TableDataException("VirtualField alias [" + alias + "] is already be used, please set another alias.");
            }
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
                this.aliasSingleValidator.put(alias, true);
            } else {
                this.aliasSingleValidator.put(value + "", true);
            }
        }
    }

    protected void appendTableColumnSqlArgs(StringBuilder sql, List<Object> args, Set<TableColumnDatum> tableColumnData) {
        int i = 0;
        TableData tableData;
        Set<ColumnDatum> columnData;
        for (TableColumnDatum tableColumnDatum : tableColumnData) {
            columnData = tableColumnDatum.getColumnData();
            if (columnData.size() == 0) {
                continue;
            }
            for (ColumnDatum columnDatum : columnData) {
                if (this.aliasSingleValidator.get(columnDatum.getOwnerColumnAlias()) != null) {
                    throw new TableDataException("table alias [" + tableColumnDatum.getTableAlias() + "] column alias [" + columnDatum.getOwnerColumnAlias() + "] is already be used, please set another alias.");
                }
                if (i++ > 0) {
                    sql.append(",");
                } else {
                    sql.append(" ");
                }
                sql.append(columnDatum.getOwnerTableAlias())
                        .append(".`")
                        .append(columnDatum.getOwnerColumnName())
                        .append("` `")
                        .append(columnDatum.getOwnerColumnAlias())
                        .append("`");
                this.aliasSingleValidator.put(columnDatum.getOwnerColumnAlias(), true);
            }
        }
    }

    protected void appendColumnSqlArgs(StringBuilder sql, List<Object> args, SqlDataConsumer sqlDataConsumer) {
        Map<String, SqlBuilder> subQueryAliasMap = sqlDataConsumer.getSubQueryDataMap();
        Set<TableFunctionColumnDatum> tableFunctionColumnData = sqlDataConsumer.getTableFunctionColumnData();
        Set<VirtualFieldDatum> virtualFieldData = sqlDataConsumer.getVirtualFieldData();
        Set<TableColumnDatum> tableColumnData = sqlDataConsumer.getTableColumnData();
        boolean hasS = subQueryAliasMap != null && subQueryAliasMap.size() != 0;
        boolean hasF = tableFunctionColumnData != null && tableFunctionColumnData.size() != 0;
        boolean hasV = virtualFieldData != null && virtualFieldData.size() != 0;
        boolean hasC = tableColumnData != null && tableColumnData.size() != 0;
        if (!hasS && !hasF && !hasV && !hasC) {
            this.appendColumnSqlArgs(sql, args, BeanUtils.getColumnData(sqlDataConsumer.getMainTableData().getTableHelper()));
            return;
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
    }

    protected void appendOnDataListSqlArgs(StringBuilder sql, List<Object> args, Set<OnDatum> onData, LinkType linkType) {
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


    protected void appendOnDataLinkerListSqlArgs(StringBuilder sql, List<Object> args, List<OnDataLinker> onDataLinkerList, LinkType linkType, boolean checkBrackets) {
        if (onDataLinkerList == null || onDataLinkerList.size() == 0) {
            return;
        }
        int length = sql.length();
        Set<OnDatum> onData;
        int i = 0;
        boolean brackets = false;
        for (OnDataLinker onDataLinker : onDataLinkerList) {
            onData = onDataLinker.getOnData();
            List<OnDataLinker> childOnDataLinkerList = onDataLinker.getOnDataLinkerList();
            if (onData != null && onData.size() > 0) {
                switch (onDataLinker.getLinkType()) {
                    case AND:
                        if (i++ > 0) {
                            sql.append(" and ");
                        }
                        this.appendOnDataListSqlArgs(sql, args, onData, LinkType.AND);
                        continue;
                    case OR:
                        if (i++ > 0) {
                            sql.append(" or ");
                            brackets = checkBrackets;
                        }
                        this.appendOnDataListSqlArgs(sql, args, onData, LinkType.OR);
                        continue;
                    default:
                        throw new SqlException("the LinkType is wrong.");
                }
            } else if (childOnDataLinkerList != null && childOnDataLinkerList.size() > 0) {
                switch (onDataLinker.getLinkType()) {
                    case AND:
                        if (i++ > 0) {
                            sql.append(" and ");
                        }
                        this.appendOnDataLinkerListSqlArgs(sql, args, childOnDataLinkerList, LinkType.AND, true);
                        continue;
                    case OR:
                        if (i++ > 0) {
                            sql.append(" or ");
                            brackets = checkBrackets;
                        }
                        this.appendOnDataLinkerListSqlArgs(sql, args, childOnDataLinkerList, LinkType.OR, true);
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

    protected void appendJoinSqlArgs(StringBuilder sql, List<Object> args, Map<String, JoinTableData<? extends TableHelper>> joinTableDataAliasMap) {
        if (joinTableDataAliasMap == null || joinTableDataAliasMap.size() == 0) {
            return;
        }
        JoinTableData<? extends TableHelper> joinTableData;
        for (Map.Entry<String, JoinTableData<? extends TableHelper>> entry : joinTableDataAliasMap.entrySet()) {
            joinTableData = entry.getValue();
            switch (joinTableData.getJoinType()) {
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
                    .append(joinTableData.getTableName())
                    .append("` ")
                    .append(joinTableData.getTableAlias());
            List<OnDataLinker> onDataLinkers = joinTableData.getTableOnDatum().getOnDataLinkers();
            if (onDataLinkers != null && onDataLinkers.size() > 0) {
                sql.append(" on ");
                this.appendOnDataLinkerListSqlArgs(sql, args, onDataLinkers, LinkType.AND, false);
            }
        }
    }

    protected void appendWhereDataValueSqlArgs(StringBuilder sql, List<Object> args, WhereDatum whereDatum) {
        switch (whereDatum.getWhereType()) {
            case IS_NULL:
                sql.append(" is null");
                break;
            case IS_NOT_NULL:
                sql.append(" is not null");
                break;
            case BETWEEN:
                sql.append(" between ? and ?");
                args.add(whereDatum.getTargetValue());
                args.add(whereDatum.getTargetSecondValue());
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

    protected void appendWhereDataJoinSqlArgs(StringBuilder sql, List<Object> args, WhereDatum whereDatum) {
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
                //TODO 别忘记break
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
            case LIKE:
                sql.append(" like ")
                        .append(whereDatum.getTargetTableAlias())
                        .append(".`")
                        .append(whereDatum.getTargetColumnName())
                        .append("`");
                break;
            case IN:
                sql.append(" in ");
                // TODO 后续添加
                throw new SqlException("暂不支持");
                //TODO 别忘记break
            case NOT_IN:
                sql.append(" not in ");
                // TODO 后续添加
                throw new SqlException("暂不支持");
                //TODO 别忘记break
            default:
                throw new SqlException("the WhereType is wrong.");
        }
    }

    protected void appendWhereDataSubQuerySqlArgs(StringBuilder sql, List<Object> args, WhereDatum whereDatum) {
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

    protected void appendWhereDataSqlPartSqlArgs(StringBuilder sql, List<Object> args, WhereDatum whereDatum) {
        sql.append(" ").append(whereDatum.getSqlPart());
    }

    protected void appendWhereDataSqlArgs(StringBuilder sql, List<Object> args, WhereDatum whereDatum) {
        switch (whereDatum.getWhereValueType()) {
            case VALUE:
                this.appendWhereDataValueSqlArgs(sql, args, whereDatum);
                break;
            case JOIN:
                this.appendWhereDataJoinSqlArgs(sql, args, whereDatum);
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

    protected void appendWhereDataListSqlArgs(StringBuilder sql, List<Object> args, Set<WhereDatum> whereData, LinkType linkType) {
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

    protected void appendWhereDataLinkerListSqlArgs(StringBuilder sql, List<Object> args, List<WhereDataLinker> whereDataLinkerList, LinkType linkType, boolean checkBrackets) {
        if (whereDataLinkerList == null || whereDataLinkerList.size() == 0) {
            return;
        }
        int length = sql.length();
        Set<WhereDatum> whereData;
        int i = 0;
        boolean brackets = false;
        for (WhereDataLinker whereDataLinker : whereDataLinkerList) {
            whereData = whereDataLinker.getWhereData();
            List<WhereDataLinker> childWhereDataLinkerList = whereDataLinker.getWhereDataLinkerList();
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
            } else if (childWhereDataLinkerList != null && childWhereDataLinkerList.size() > 0) {
                switch (whereDataLinker.getLinkType()) {
                    case AND:
                        if (i++ > 0) {
                            sql.append(" and ");
                        }
                        this.appendWhereDataLinkerListSqlArgs(sql, args, childWhereDataLinkerList, LinkType.AND, true);
                        continue;
                    case OR:
                        if (i++ > 0) {
                            sql.append(" or ");
                            brackets = checkBrackets;
                        }
                        this.appendWhereDataLinkerListSqlArgs(sql, args, childWhereDataLinkerList, LinkType.OR, true);
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

    protected void appendWhereSqlArgs(StringBuilder sql, List<Object> args, Set<TableWhereDatum> tableWhereData) {
        if (tableWhereData == null || tableWhereData.size() == 0) {
            return;
        }
        sql.append(" where ");
        int i = 0;
        for (TableWhereDatum tableWhereDatum : tableWhereData) {
            if (i++ > 0) {
                sql.append(" and ");
            }
            this.appendWhereDataLinkerListSqlArgs(sql, args, tableWhereDatum.getWhereDataLinkers(), LinkType.AND, tableWhereData.size() > 1);
        }
    }

    protected void appendGroupSqlArgs(StringBuilder sql, List<Object> args, Set<TableGroupDatum> tableGroupDataSet) {
        if (tableGroupDataSet == null || tableGroupDataSet.size() == 0) {
            return;
        }
        sql.append(" group by ");
        int i = 0;
        Set<GroupDatum> groupData;
        for (TableGroupDatum tableGroupDatum : tableGroupDataSet) {
            groupData = tableGroupDatum.getGroupData();
            if (groupData == null || groupData.size() == 0) {
                continue;
            }
            for (GroupDatum columnName : groupData) {
                if (i++ > 0) {
                    sql.append(",");
                }
                sql.append(columnName.getOwnerTableAlias())
                        .append(".`")
                        .append(columnName.getOwnerColumnName())
                        .append("`");
            }
        }
    }

    protected void appendSortSqlArgs(StringBuilder sql, List<Object> args, Set<TableSortDatum> tableSortDataSet) {
        if (tableSortDataSet == null || tableSortDataSet.size() == 0) {
            return;
        }
        sql.append(" order by ");
        int i = 0;
        Set<SortDatum> sortData;
        for (TableSortDatum tableSortDatum : tableSortDataSet) {
            sortData = tableSortDatum.getSortData();
            if (sortData == null || sortData.size() == 0) {
                continue;
            }
            for (SortDatum sortDatum : sortData) {
                if (i++ > 0) {
                    sql.append(",");
                }
                sql.append(sortDatum.getOwnerTableAlias())
                        .append(".`")
                        .append(sortDatum.getOwnerColumnName())
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
    }

    protected void appendLimitSqlArgs(StringBuilder sql, List<Object> args, LimitHandler limit) {
        if (limit == null) {
            return;
        }
        sql.append(" limit ?,?");
        args.add(limit.getLimitStart());
        args.add(limit.getLimitEnd());
    }

}
