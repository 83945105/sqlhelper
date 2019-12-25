package pub.avalonframework.sqlhelper.core.sqlbuilder.template;

import pub.avalonframework.sqlhelper.core.beans.ColumnHandler;
import pub.avalonframework.sqlhelper.core.beans.LinkType;
import pub.avalonframework.sqlhelper.core.data.*;
import pub.avalonframework.sqlhelper.core.data.beans.ColumnType;
import pub.avalonframework.sqlhelper.core.data.beans.Type;
import pub.avalonframework.sqlhelper.core.data.beans.ValueType;
import pub.avalonframework.sqlhelper.core.exception.SqlException;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.FinalSqlBuilderResult;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.SqlBuilderResult;
import pub.avalonframework.sqlhelper.core.utils.ExceptionUtils;
import pub.avalonframework.sqlhelper.core.utils.HelperManager;

import java.util.*;

/**
 * @author baichao
 */
public final class DefaultMySqlPartBuilderTemplate implements MySqlPartBuilderTemplate {

    public final static DefaultMySqlPartBuilderTemplate DEFAULT_DEFAULT_MY_SQL_PART_BUILDER_TEMPLATE = new DefaultMySqlPartBuilderTemplate();

    @Override
    public SqlBuilderResult buildSelectColumn(SqlDataConsumer sqlDataConsumer) {
        List<TableColumnDatum> tableColumnData = sqlDataConsumer.getSelectTableColumnData();
        boolean hasC = tableColumnData != null && tableColumnData.size() != 0;
        StringBuilder sql = new StringBuilder(128);
        List<Object> args = new ArrayList<>(16);
        if (!hasC) {
            SqlBuilderOptions sqlBuilderOptions = sqlDataConsumer.getSqlBuilderOptions();
            boolean selectAllColumnForMainTable = sqlBuilderOptions.getSqlPartDatumBuilderOptions().isSelectAllColumnForMainTable();
            boolean selectAllColumnForJoinTable = sqlBuilderOptions.getSqlPartDatumBuilderOptions().isSelectAllColumnForJoinTable();
            if (!selectAllColumnForMainTable && !selectAllColumnForJoinTable) {
                ExceptionUtils.selectColumnNullException();
            }
            if (selectAllColumnForMainTable) {
                this.appendColumnSqlArgs(sql, args, HelperManager.defaultColumnData(sqlDataConsumer.getMainTableDatum().getTableHelperClass(), sqlDataConsumer.getMainTableDatum().getTableAlias()));
            }
            if (selectAllColumnForJoinTable) {
                LinkedHashMap<String, JoinTableDatum> aliasJoinTableData = sqlDataConsumer.getAliasJoinTableData();
                if (aliasJoinTableData != null && aliasJoinTableData.size() > 0) {
                    for (Map.Entry<String, JoinTableDatum> entry : aliasJoinTableData.entrySet()) {
                        this.appendColumnSqlArgs(sql, args, HelperManager.defaultColumnData(entry.getValue().getTableHelperClass(), entry.getKey()));
                    }
                }
            }
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
            List<ComparisonSqlPartDataLinker> comparisonSqlPartDataLinkers = joinTableDatum.getTableOnDatum().getComparisonSqlPartDataLinkers();
            if (comparisonSqlPartDataLinkers != null && comparisonSqlPartDataLinkers.size() > 0) {
                sql.append(" on ");
                this.appendComparisonSqlPartDataLinkersSqlArgs(sql, args, comparisonSqlPartDataLinkers, LinkType.AND, false);
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
            this.appendComparisonSqlPartDataLinkersSqlArgs(sql, args, tableWhereDatum.getComparisonSqlPartDataLinkers(), LinkType.AND, tableWhereData.size() > 1);
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
    public SqlBuilderResult buildHaving(SqlDataConsumer sqlDataConsumer) {
        List<TableHavingDatum> tableHavingData = sqlDataConsumer.getTableHavingData();
        if (tableHavingData == null || tableHavingData.size() == 0) {
            return FinalSqlBuilderResult.NONE;
        }
        StringBuilder sql = new StringBuilder(128);
        List<Object> args = new ArrayList<>(16);
        sql.append(" having ");
        int i = 0;
        for (TableHavingDatum tableHavingDatum : tableHavingData) {
            if (i++ > 0) {
                sql.append(" and ");
            }
            this.appendComparisonSqlPartDataLinkersSqlArgs(sql, args, tableHavingDatum.getComparisonSqlPartDataLinkers(), LinkType.AND, tableHavingData.size() > 1);
        }
        return FinalSqlBuilderResult.newInstance(sql.toString(), args);
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
        Long limit = sqlDataConsumer.getLimit();
        Long offset = sqlDataConsumer.getOffset();
        if (limit == null) {
            return FinalSqlBuilderResult.NONE;
        }
        if (offset == null) {
            return FinalSqlBuilderResult.newInstance(" limit ?", Collections.singletonList(limit));
        }
        return FinalSqlBuilderResult.newInstance(" limit ? offset ?", Arrays.asList(limit, offset));
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

    private void appendType(StringBuilder sql, AbstractComparisonSqlPartDatum sqlPartDatum) {
        Type type = sqlPartDatum.getType();
        switch (type) {
            case DEFAULT:
                return;
            case SQL_PART:
                sql.append(sqlPartDatum.getSqlPart());
                return;
            default:
                ExceptionUtils.unsupportedTypeException(type);
        }
    }

    private void appendColumnType(StringBuilder sql, AbstractComparisonSqlPartDatum sqlPartDatum) {
        ColumnType columnType = sqlPartDatum.getColumnType();
        switch (columnType) {
            case DEFAULT:
                sql.append(sqlPartDatum.getTableAlias())
                        .append(".`")
                        .append(sqlPartDatum.getColumnName())
                        .append("`");
                return;
            case HANDLER:
                sql.append(sqlPartDatum.getColumnHandler().execute(sqlPartDatum.getTableAlias() + ".`" + sqlPartDatum.getColumnName() + "`"));
                return;
            default:
                ExceptionUtils.unsupportedColumnTypeException(columnType);
        }
    }

    private void appendComparisonType(StringBuilder sql, AbstractComparisonSqlPartDatum sqlPartDatum) {
        ComparisonType comparisonType = sqlPartDatum.getComparisonType();
        switch (comparisonType) {
            case NONE:
                return;
            case IS_NULL:
                sql.append(" is null");
                return;
            case IS_NOT_NULL:
                sql.append(" is not null");
                return;
            case EQUAL:
                sql.append(" = ");
                return;
            case NOT_EQUAL:
                sql.append(" != ");
                return;
            case GREATER:
                sql.append(" > ");
                return;
            case GREATER_EQUAL:
                sql.append(" >= ");
                return;
            case LESS:
                sql.append(" < ");
                return;
            case LESS_EQUAL:
                sql.append(" <= ");
                return;
            case BETWEEN:
                sql.append(" between ");
                return;
            case LIKE:
                sql.append(" like ");
                return;
            case IN:
                sql.append(" in ");
                return;
            case NOT_IN:
                sql.append(" not in ");
                return;
            default:
                ExceptionUtils.unsupportedComparisonTypeException(comparisonType);
        }
    }

    @SuppressWarnings("unchecked")
    private void appendValueType(StringBuilder sql, List<Object> args, AbstractComparisonSqlPartDatum sqlPartDatum) {
        ValueType valueType = sqlPartDatum.getValueType();
        switch (valueType) {
            case NONE_VALUE:
                return;
            case SINGLE_VALUE:
                sql.append("?");
                args.add(sqlPartDatum.getTargetValue());
                return;
            case PAIR_VALUE:
                sql.append("? and ?");
                args.add(sqlPartDatum.getTargetValue());
                args.add(sqlPartDatum.getTargetSecondValue());
                return;
            case MULTI_VALUE:
                Object value = sqlPartDatum.getTargetValue();
                sql.append("(");
                int i = 0;
                if (value instanceof Collection) {
                    for (Object val : (Collection) value) {
                        if (i++ > 0) {
                            sql.append(",");
                        }
                        sql.append("?");
                        args.add(val);
                    }
                } else if (value.getClass().isArray()) {
                    for (Object val : (Object[]) value) {
                        if (i++ > 0) {
                            sql.append(",");
                        }
                        sql.append("?");
                        args.add(val);
                    }
                } else {
                    ExceptionUtils.errorValueTypeException(value);
                }
                sql.append(")");
                return;
            case SUB_QUERY:
                SqlBuilderResult sqlBuilderResult = sqlPartDatum.getTargetSubQuery();
                sql.append("(").append(sqlBuilderResult.getPreparedStatementSql()).append(")");
                args.addAll(sqlBuilderResult.getPreparedStatementArgs());
                return;
            case SQL_PART:
                sql.append(sqlPartDatum.getTargetSqlPart());
                return;
            case SINGLE_SQL_PART_DATUM:
                AbstractSqlPartDatum targetSqlPartDatum = sqlPartDatum.getTargetSqlPartDatum();
                sql.append(targetSqlPartDatum.getTableAlias())
                        .append(".`")
                        .append(targetSqlPartDatum.getColumnName())
                        .append("`");
                return;
            case PAIR_SQL_PART_DATUM:
                targetSqlPartDatum = sqlPartDatum.getTargetSqlPartDatum();
                AbstractSqlPartDatum targetSecondSqlPartDatum = sqlPartDatum.getTargetSecondSqlPartDatum();
                sql.append(targetSqlPartDatum.getTableAlias())
                        .append(".`")
                        .append(targetSqlPartDatum.getColumnName())
                        .append("` and ")
                        .append(targetSecondSqlPartDatum.getTableAlias())
                        .append(".`")
                        .append(targetSecondSqlPartDatum.getColumnName())
                        .append("`");
                return;
            case MULTI_SQL_PART_DATUM:
                List<AbstractSqlPartDatum> sqlPartData = sqlPartDatum.getTargetMultiSqlPartDatum();
                sql.append("(");
                int j = 0;
                for (AbstractSqlPartDatum spd : sqlPartData) {
                    if (j++ > 0) {
                        sql.append(",");
                    }
                    sql.append(spd.getTableAlias())
                            .append(".`")
                            .append(spd.getColumnName())
                            .append("`");
                }
                sql.append(")");
                return;
            default:
                ExceptionUtils.unsupportedValueTypeException(valueType);
        }
    }

    private void appendSqlPartData(StringBuilder sql, List<Object> args, List<? extends AbstractComparisonSqlPartDatum> sqlPartData, LinkType linkType) {
        if (sqlPartData == null || sqlPartData.size() == 0) {
            return;
        }
        if (linkType == LinkType.OR && sqlPartData.size() > 1) {
            sql.append("(");
        }
        int i = 0;
        for (AbstractComparisonSqlPartDatum sqlPartDatum : sqlPartData) {
            if (i++ > 0) {
                sql.append(" and ");
            }
            appendType(sql, sqlPartDatum);
            appendColumnType(sql, sqlPartDatum);
            appendComparisonType(sql, sqlPartDatum);
            appendValueType(sql, args, sqlPartDatum);
        }
        if (linkType == LinkType.OR && sqlPartData.size() > 1) {
            sql.append(")");
        }
    }

    private void appendComparisonSqlPartDataLinkersSqlArgs(StringBuilder sql, List<Object> args, List<ComparisonSqlPartDataLinker> onDataLinkers, LinkType linkType, boolean checkBrackets) {
        if (onDataLinkers == null || onDataLinkers.size() == 0) {
            return;
        }
        int length = sql.length();
        List<? extends AbstractComparisonSqlPartDatum> comparisonSqlPartData;
        int i = 0;
        boolean brackets = false;
        for (ComparisonSqlPartDataLinker comparisonSqlPartDataLinker : onDataLinkers) {
            comparisonSqlPartData = comparisonSqlPartDataLinker.getComparisonSqlPartData();
            List<ComparisonSqlPartDataLinker> childComparisonSqlPartDataLinkers = comparisonSqlPartDataLinker.getComparisonSqlPartDataLinkers();
            if (comparisonSqlPartData != null && comparisonSqlPartData.size() > 0) {
                switch (comparisonSqlPartDataLinker.getLinkType()) {
                    case AND:
                        if (i++ > 0) {
                            sql.append(" and ");
                        }
                        this.appendSqlPartData(sql, args, comparisonSqlPartData, LinkType.AND);
                        continue;
                    case OR:
                        if (i++ > 0) {
                            sql.append(" or ");
                            brackets = checkBrackets;
                        }
                        this.appendSqlPartData(sql, args, comparisonSqlPartData, LinkType.OR);
                        continue;
                    default:
                        //TODO
                        throw new SqlException("the LinkType is wrong.");
                }
            } else if (childComparisonSqlPartDataLinkers != null && childComparisonSqlPartDataLinkers.size() > 0) {
                switch (comparisonSqlPartDataLinker.getLinkType()) {
                    case AND:
                        if (i++ > 0) {
                            sql.append(" and ");
                        }
                        this.appendComparisonSqlPartDataLinkersSqlArgs(sql, args, childComparisonSqlPartDataLinkers, LinkType.AND, true);
                        continue;
                    case OR:
                        if (i++ > 0) {
                            sql.append(" or ");
                            brackets = checkBrackets;
                        }
                        this.appendComparisonSqlPartDataLinkersSqlArgs(sql, args, childComparisonSqlPartDataLinkers, LinkType.OR, true);
                        continue;
                    default:
                        //TODO
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