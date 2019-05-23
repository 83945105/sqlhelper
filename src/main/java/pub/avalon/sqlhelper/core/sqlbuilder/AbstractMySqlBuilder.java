package pub.avalon.sqlhelper.core.sqlbuilder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.fusesource.jansi.Ansi;
import pub.avalon.beans.LimitHandler;
import pub.avalon.sqlhelper.core.beans.LinkType;
import pub.avalon.sqlhelper.core.data.*;
import pub.avalon.sqlhelper.core.exception.SqlException;
import pub.avalon.sqlhelper.core.exception.TableDataException;
import pub.avalon.sqlhelper.core.modelbuilder.TableModel;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.*;

/**
 * @author 白超
 * @date 2018/8/23
 */
public abstract class AbstractMySqlBuilder implements MySqlBuilder {

    private final Log logger = LogFactory.getLog(getClass());

    private boolean sqlEnabled;

    private boolean argsEnabled;

    private boolean colour;

    protected SqlBuilderOptions sqlBuilderOptions;

    public AbstractMySqlBuilder(SqlBuilderOptions sqlBuilderOptions) {
        this.sqlBuilderOptions = sqlBuilderOptions;
        this.sqlEnabled = sqlBuilderOptions.getSqlPrintOptions().isEnabled() && sqlBuilderOptions.getSqlPrintOptions().isSqlEnabled();
        this.argsEnabled = sqlBuilderOptions.getSqlPrintOptions().isEnabled() && sqlBuilderOptions.getSqlPrintOptions().isArgsEnabled();
        this.colour = sqlBuilderOptions.getSqlPrintOptions().isColour();
    }

    protected SqlData<?> sqlData;

    @Override
    public MySqlBuilder setSqlData(SqlData<?> sqlData) {
        this.sqlData = sqlData;
        return this;
    }

    /**
     * 预编译sql
     */
    protected StringBuilder preparedStatementSql;
    /**
     * 预编译参数
     */
    protected List<Object> preparedStatementArgs;

    private Map<String, Boolean> aliasSingleValidator = new HashMap<>(32);

    @Override
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
    }

    private StringBuilder appendMainTableAllColumnSql(StringBuilder stringBuilder) {
        Set<ColumnDatum> columnData = this.sqlData.getMainTableData().buildTableColumnData();
        int i = 0;
        for (ColumnDatum columnDatum : columnData) {
            if (i++ > 0) {
                stringBuilder.append(",");
            } else {
                stringBuilder.append(" ");
            }
            stringBuilder.append(columnDatum.getOwnerTableAlias())
                    .append(".`")
                    .append(columnDatum.getOwnerColumnName())
                    .append("` `")
                    .append(columnDatum.getOwnerColumnAlias())
                    .append("`");
        }
        return stringBuilder;
    }

    private StringBuilder appendSubQuerySql(StringBuilder stringBuilder) {
        Map<String, SqlBuilder> subQueryAliasMap = this.sqlData.getSubQueryDataMap();
        if (subQueryAliasMap == null) {
            return stringBuilder;
        }
        int i = 0;
        for (Map.Entry<String, SqlBuilder> entry : subQueryAliasMap.entrySet()) {
            String alias = entry.getKey();
            SqlBuilder sqlBuilder = entry.getValue();
            if (this.aliasSingleValidator.get(alias) != null) {
                throw new TableDataException("SubQueryColumn alias [" + alias + "] is already be used, please set another alias.");
            }
            if (i++ > 0) {
                stringBuilder.append(",(");
            } else {
                stringBuilder.append(" (");
            }
            stringBuilder.append(sqlBuilder.getPreparedStatementSql()).append(") ").append(alias);
            this.preparedStatementArgs.addAll(sqlBuilder.getPreparedStatementArgs());
        }
        return stringBuilder;
    }

    private StringBuilder appendFunctionColumnSql(StringBuilder stringBuilder) {
        int i = 0;
        for (FunctionColumnData fcData : this.sqlData.getFunctionColumnDataList()) {
            if (this.aliasSingleValidator.get(fcData.getColumnAlias()) != null) {
                throw new TableDataException("FunctionColumn alias [" + fcData.getColumnAlias() + "] is already be used, please set another alias.");
            }
            if (i++ > 0) {
                stringBuilder.append(",");
            } else {
                stringBuilder.append(" ");
            }
            switch (fcData.getFunctionColumnType()) {
                case MIN:
                    stringBuilder.append("min(");
                    break;
                case MAX:
                    stringBuilder.append("max(");
                    break;
                case COUNT:
                    stringBuilder.append("count(");
                    break;
                case SUM:
                    stringBuilder.append("sum(");
                    break;
                default:
                    throw new SqlException("the functionColumnType is wrong.");
            }
            stringBuilder.append(fcData.getTableData().getTableAlias())
                    .append(".`")
                    .append(fcData.getColumnName())
                    .append("`) `")
                    .append(fcData.getColumnAlias())
                    .append("`");
            this.aliasSingleValidator.put(fcData.getColumnAlias(), true);
        }
        return stringBuilder;
    }

    private StringBuilder appendVirtualColumnSql(StringBuilder stringBuilder) {
        Object value;
        String alias;
        int i = 0;
        for (VirtualFieldData data : this.sqlData.getVirtualFieldDataSet()) {
            if (i++ > 0) {
                stringBuilder.append(",");
            } else {
                stringBuilder.append(" ");
            }
            value = data.getValue();
            alias = data.getAlias();
            if (this.aliasSingleValidator.get(alias) != null) {
                throw new TableDataException("VirtualField alias [" + alias + "] is already be used, please set another alias.");
            }
            if (value == null) {
                stringBuilder.append("null");
            } else if (value instanceof String) {
                stringBuilder.append("'").append((String) value).append("'");
            } else if (value instanceof Integer) {
                stringBuilder.append(String.valueOf(value));
            } else if (value instanceof Long) {
                stringBuilder.append(String.valueOf(value));
            } else if (value instanceof Double) {
                stringBuilder.append(String.valueOf(value));
            } else {
                throw new SqlException("the VirtualFieldData value type is wrong.");
            }
            if (alias != null) {
                stringBuilder.append(" `").append(alias).append("`");
                this.aliasSingleValidator.put(alias, true);
            } else {
                this.aliasSingleValidator.put(value + "", true);
            }
        }
        return stringBuilder;
    }

    private StringBuilder appendTableColumnSql(StringBuilder stringBuilder) {
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
                    stringBuilder.append(",");
                } else {
                    stringBuilder.append(" ");
                }
                stringBuilder.append(columnDatum.getOwnerTableAlias())
                        .append(".`")
                        .append(columnDatum.getOwnerColumnName())
                        .append("` `")
                        .append(columnDatum.getOwnerColumnAlias())
                        .append("`");
                this.aliasSingleValidator.put(columnDatum.getOwnerColumnAlias(), true);
            }
        }
        return stringBuilder;
    }

    protected StringBuilder appendColumnSql(StringBuilder stringBuilder) {
        Map<String, SqlBuilder> subQueryAliasMap = this.sqlData.getSubQueryDataMap();
        List<FunctionColumnData> functionColumnDataList = this.sqlData.getFunctionColumnDataList();
        Set<VirtualFieldData> virtualFieldDataSet = this.sqlData.getVirtualFieldDataSet();
        Set<TableColumnData> tableColumnDataSet = this.sqlData.getTableColumnDataSet();
        boolean hasS = subQueryAliasMap != null && subQueryAliasMap.size() != 0;
        boolean hasF = functionColumnDataList != null && functionColumnDataList.size() != 0;
        boolean hasV = virtualFieldDataSet != null && virtualFieldDataSet.size() != 0;
        boolean hasC = tableColumnDataSet != null && tableColumnDataSet.size() != 0;
        if (!hasS && !hasF && !hasV && !hasC) {
            return this.appendMainTableAllColumnSql(stringBuilder);
        }
        if (hasS) {
            stringBuilder = this.appendSubQuerySql(stringBuilder);
        }
        if (hasF) {
            if (hasS) {
                stringBuilder.append(",");
            }
            stringBuilder = this.appendFunctionColumnSql(stringBuilder);
        }
        if (hasV) {
            if (hasS || hasF) {
                stringBuilder.append(",");
            }
            stringBuilder = this.appendVirtualColumnSql(stringBuilder);
        }
        if (hasC) {
            if (hasS || hasF || hasV) {
                stringBuilder.append(",");
            }
            stringBuilder = this.appendTableColumnSql(stringBuilder);
        }
        return stringBuilder;
    }

    private StringBuilder appendOnDataList(StringBuilder stringBuilder, Set<OnDatum> onData, LinkType linkType) {
        if (onData == null || onData.size() == 0) {
            return stringBuilder;
        }
        if (linkType == LinkType.OR && onData.size() > 1) {
            stringBuilder.append("(");
        }
        int i = 0;
        for (OnDatum onDatum : onData) {
            if (i++ > 0) {
                stringBuilder.append(" and ");
            }
            stringBuilder.append(onDatum.getOwnerTableAlias())
                    .append(".`")
                    .append(onDatum.getOwnerColumnName())
                    .append("`");
            switch (onDatum.getOnType()) {
                case IS_NULL:
                    stringBuilder.append(" is null");
                    continue;
                case IS_NOT_NULL:
                    stringBuilder.append(" is not null");
                    continue;
                case EQUAL:
                    stringBuilder.append(" = ");
                    break;
                case NOT_EQUAL:
                    stringBuilder.append(" != ");
                    break;
                case GREATER:
                    stringBuilder.append(" > ");
                    break;
                case GREATER_EQUAL:
                    stringBuilder.append(" >= ");
                    break;
                case LESS:
                    stringBuilder.append(" < ");
                    break;
                case LESS_EQUAL:
                    stringBuilder.append(" <= ");
                    break;
                case BETWEEN:
                    stringBuilder.append(" between ? and ?");
                    this.preparedStatementArgs.add(onDatum.getTargetValue());
                    this.preparedStatementArgs.add(onDatum.getTargetSecondValue());
                    continue;
                case LIKE:
                    stringBuilder.append(" like ?");
                    this.preparedStatementArgs.add(onDatum.getTargetValue());
                    continue;
                case IN:
                    int count = onDatum.getValueCount();
                    stringBuilder.append(" in (");
                    for (; count > 0; count--) {
                        if (count == 1) {
                            stringBuilder.append("?");
                        } else {
                            stringBuilder.append("?,");
                        }
                    }
                    stringBuilder.append(")");
                    Object value = onDatum.getTargetValue();
                    if (value instanceof Collection) {
                        this.preparedStatementArgs.addAll((Collection) value);
                    } else if (value.getClass().isArray()) {
                        this.preparedStatementArgs.addAll(Arrays.asList((Object[]) value));
                    } else {
                        throw new SqlException("the value type can only be Array or Collection.");
                    }
                    continue;
                case NOT_IN:
                    count = onDatum.getValueCount();
                    stringBuilder.append(" not in (");
                    for (; count > 0; count--) {
                        if (count == 1) {
                            stringBuilder.append("?");
                        } else {
                            stringBuilder.append("?,");
                        }
                    }
                    stringBuilder.append(")");
                    value = onDatum.getTargetValue();
                    if (value instanceof Collection) {
                        this.preparedStatementArgs.addAll((Collection) value);
                    } else if (value.getClass().isArray()) {
                        this.preparedStatementArgs.addAll(Arrays.asList((Object[]) value));
                    } else {
                        throw new SqlException("the value type can only be Array or Collection.");
                    }
                    continue;
                default:
                    throw new SqlException("the WhereType is wrong.");
            }
            switch (onDatum.getOnValueType()) {
                case VALUE:
                    stringBuilder.append("?");
                    this.preparedStatementArgs.add(onDatum.getTargetValue());
                    continue;
                case JOIN:
                    stringBuilder.append(onDatum.getTargetTableAlias())
                            .append(".`")
                            .append(onDatum.getTargetColumnName())
                            .append("`");
                    continue;
                default:
                    throw new SqlException("the OnValueType is wrong.");
            }
        }
        if (linkType == LinkType.OR && onData.size() > 1) {
            stringBuilder.append(")");
        }
        return stringBuilder;
    }


    private StringBuilder appendOnDataLinkerList(StringBuilder stringBuilder, List<OnDataLinker> onDataLinkerList, LinkType linkType, boolean checkBrackets) {

        if (onDataLinkerList == null || onDataLinkerList.size() == 0) {
            return stringBuilder;
        }
        int length = stringBuilder.length();
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
                            stringBuilder.append(" and ");
                        }
                        this.appendOnDataList(stringBuilder, onData, LinkType.AND);
                        continue;
                    case OR:
                        if (i++ > 0) {
                            stringBuilder.append(" or ");
                            brackets = checkBrackets;
                        }
                        this.appendOnDataList(stringBuilder, onData, LinkType.OR);
                        continue;
                    default:
                        throw new SqlException("the LinkType is wrong.");
                }
            } else if (childOnDataLinkerList != null && childOnDataLinkerList.size() > 0) {
                switch (onDataLinker.getLinkType()) {
                    case AND:
                        if (i++ > 0) {
                            stringBuilder.append(" and ");
                        }
                        stringBuilder = this.appendOnDataLinkerList(stringBuilder, childOnDataLinkerList, LinkType.AND, true);
                        continue;
                    case OR:
                        if (i++ > 0) {
                            stringBuilder.append(" or ");
                            brackets = checkBrackets;
                        }
                        stringBuilder = this.appendOnDataLinkerList(stringBuilder, childOnDataLinkerList, LinkType.OR, true);
                        continue;
                    default:
                        throw new SqlException("the LinkType is wrong.");
                }
            }
        }
        if (!checkBrackets) {
            return stringBuilder;
        }
        brackets = brackets || linkType == LinkType.OR && i > 1;
        if (!brackets) {
            return stringBuilder;
        }
        stringBuilder.insert(length, "(").append(")");
        return stringBuilder;
    }

    protected StringBuilder appendJoinSql(StringBuilder stringBuilder) {
        Map<String, JoinTableData<? extends TableModel>> joinTableDataAliasMap = this.sqlData.getJoinTableDataMap();
        if (joinTableDataAliasMap == null || joinTableDataAliasMap.size() == 0) {
            return stringBuilder;
        }
        JoinTableData<? extends TableModel> joinTableData;
        for (Map.Entry<String, JoinTableData<? extends TableModel>> entry : joinTableDataAliasMap.entrySet()) {
            joinTableData = entry.getValue();
            switch (joinTableData.getJoinType()) {
                case INNER:
                    stringBuilder.append(" inner join ");
                    break;
                case LEFT:
                    stringBuilder.append(" left join ");
                    break;
                case RIGHT:
                    stringBuilder.append(" right join ");
                    break;
                default:
                    continue;
            }
            stringBuilder.append("`")
                    .append(joinTableData.getTableName())
                    .append("` ")
                    .append(joinTableData.getTableAlias());
            List<OnDataLinker> onDataLinkerList = joinTableData.getOnDataLinkerList();
            if (onDataLinkerList != null && onDataLinkerList.size() > 0) {
                stringBuilder.append(" on ");
                this.appendOnDataLinkerList(stringBuilder, onDataLinkerList, LinkType.AND, false);
            }
        }
        return stringBuilder;
    }

    private StringBuilder appendWhereDataValueSql(StringBuilder stringBuilder, WhereDatum whereDatum) {
        switch (whereDatum.getWhereType()) {
            case IS_NULL:
                stringBuilder.append(" is null");
                break;
            case IS_NOT_NULL:
                stringBuilder.append(" is not null");
                break;
            case BETWEEN:
                stringBuilder.append(" between ? and ?");
                this.preparedStatementArgs.add(whereDatum.getTargetValue());
                this.preparedStatementArgs.add(whereDatum.getTargetSecondValue());
                break;
            case EQUAL:
                stringBuilder.append(" = ?");
                this.preparedStatementArgs.add(whereDatum.getTargetValue());
                break;
            case NOT_EQUAL:
                stringBuilder.append(" != ?");
                this.preparedStatementArgs.add(whereDatum.getTargetValue());
                break;
            case GREATER:
                stringBuilder.append(" > ?");
                this.preparedStatementArgs.add(whereDatum.getTargetValue());
                break;
            case GREATER_EQUAL:
                stringBuilder.append(" >= ?");
                this.preparedStatementArgs.add(whereDatum.getTargetValue());
                break;
            case LESS:
                stringBuilder.append(" < ?");
                this.preparedStatementArgs.add(whereDatum.getTargetValue());
                break;
            case LESS_EQUAL:
                stringBuilder.append(" <= ?");
                this.preparedStatementArgs.add(whereDatum.getTargetValue());
                break;
            case LIKE:
                stringBuilder.append(" like ?");
                this.preparedStatementArgs.add(whereDatum.getTargetValue());
                break;
            case IN:
                stringBuilder.append(" in (");
                int count = whereDatum.getValueCount();
                for (; count > 0; count--) {
                    if (count == 1) {
                        stringBuilder.append("?");
                    } else {
                        stringBuilder.append("?,");
                    }
                }
                stringBuilder.append(")");
                Object value = whereDatum.getTargetValue();
                if (value instanceof Collection) {
                    this.preparedStatementArgs.addAll((Collection) value);
                } else if (value.getClass().isArray()) {
                    this.preparedStatementArgs.addAll(Arrays.asList((Object[]) value));
                } else {
                    throw new SqlException("the value type can only be Array or Collection.");
                }
                break;
            case NOT_IN:
                stringBuilder.append(" not in (");
                count = whereDatum.getValueCount();
                for (; count > 0; count--) {
                    if (count == 1) {
                        stringBuilder.append("?");
                    } else {
                        stringBuilder.append("?,");
                    }
                }
                stringBuilder.append(")");
                value = whereDatum.getTargetValue();
                if (value instanceof Collection) {
                    this.preparedStatementArgs.addAll((Collection) value);
                } else if (value.getClass().isArray()) {
                    this.preparedStatementArgs.addAll(Arrays.asList((Object[]) value));
                } else {
                    throw new SqlException("the value type can only be Array or Collection.");
                }
                break;
            default:
                throw new SqlException("the WhereType is wrong.");
        }
        return stringBuilder;
    }

    private StringBuilder appendWhereDataJoinSql(StringBuilder stringBuilder, WhereDatum whereDatum) {
        switch (whereDatum.getWhereType()) {
            case IS_NULL:
                stringBuilder.append(" is null");
                break;
            case IS_NOT_NULL:
                stringBuilder.append(" is not null");
                break;
            case BETWEEN:
                stringBuilder.append(" between ? and ?");
                // TODO 后续添加
                throw new SqlException("暂不支持");
                //TODO 别忘记break
            case EQUAL:
                stringBuilder.append(" = ")
                        .append(whereDatum.getTargetTableAlias())
                        .append(".`")
                        .append(whereDatum.getTargetColumnName())
                        .append("`");
                break;
            case NOT_EQUAL:
                stringBuilder.append(" != ")
                        .append(whereDatum.getTargetTableAlias())
                        .append(".`")
                        .append(whereDatum.getTargetColumnName())
                        .append("`");
                break;
            case GREATER:
                stringBuilder.append(" > ")
                        .append(whereDatum.getTargetTableAlias())
                        .append(".`")
                        .append(whereDatum.getTargetColumnName())
                        .append("`");
                break;
            case GREATER_EQUAL:
                stringBuilder.append(" >= ")
                        .append(whereDatum.getTargetTableAlias())
                        .append(".`")
                        .append(whereDatum.getTargetColumnName())
                        .append("`");
                break;
            case LESS:
                stringBuilder.append(" < ")
                        .append(whereDatum.getTargetTableAlias())
                        .append(".`")
                        .append(whereDatum.getTargetColumnName())
                        .append("`");
                break;
            case LESS_EQUAL:
                stringBuilder.append(" <= ")
                        .append(whereDatum.getTargetTableAlias())
                        .append(".`")
                        .append(whereDatum.getTargetColumnName())
                        .append("`");
                break;
            case LIKE:
                stringBuilder.append(" like ")
                        .append(whereDatum.getTargetTableAlias())
                        .append(".`")
                        .append(whereDatum.getTargetColumnName())
                        .append("`");
                break;
            case IN:
                stringBuilder.append(" in ");
                // TODO 后续添加
                throw new SqlException("暂不支持");
                //TODO 别忘记break
            case NOT_IN:
                stringBuilder.append(" not in ");
                // TODO 后续添加
                throw new SqlException("暂不支持");
                //TODO 别忘记break
            default:
                throw new SqlException("the WhereType is wrong.");
        }
        return stringBuilder;
    }

    private StringBuilder appendWhereDataSubQuerySql(StringBuilder stringBuilder, WhereDatum whereDatum) {
        switch (whereDatum.getWhereType()) {
            case IS_NULL:
                stringBuilder.append(" is null");
                break;
            case IS_NOT_NULL:
                stringBuilder.append(" is not null");
                break;
            case BETWEEN:
                stringBuilder.append(" between ? and ?");
                // TODO 后续添加
                throw new SqlException("暂不支持");
                // TODO 别忘记break
            case EQUAL:
                stringBuilder.append(" = (")
                        .append(whereDatum.getTargetSubQuery().getPreparedStatementSql())
                        .append(")");
                this.preparedStatementArgs.addAll(whereDatum.getTargetSubQuery().getPreparedStatementArgs());
                break;
            case NOT_EQUAL:
                stringBuilder.append(" != (")
                        .append(whereDatum.getTargetSubQuery().getPreparedStatementSql())
                        .append(")");
                this.preparedStatementArgs.addAll(whereDatum.getTargetSubQuery().getPreparedStatementArgs());
                break;
            case GREATER:
                stringBuilder.append(" > (")
                        .append(whereDatum.getTargetSubQuery().getPreparedStatementSql())
                        .append(")");
                this.preparedStatementArgs.addAll(whereDatum.getTargetSubQuery().getPreparedStatementArgs());
                break;
            case GREATER_EQUAL:
                stringBuilder.append(" >= (")
                        .append(whereDatum.getTargetSubQuery().getPreparedStatementSql())
                        .append(")");
                this.preparedStatementArgs.addAll(whereDatum.getTargetSubQuery().getPreparedStatementArgs());
                break;
            case LESS:
                stringBuilder.append(" < (")
                        .append(whereDatum.getTargetSubQuery().getPreparedStatementSql())
                        .append(")");
                this.preparedStatementArgs.addAll(whereDatum.getTargetSubQuery().getPreparedStatementArgs());
                break;
            case LESS_EQUAL:
                stringBuilder.append(" <= (")
                        .append(whereDatum.getTargetSubQuery().getPreparedStatementSql())
                        .append(")");
                this.preparedStatementArgs.addAll(whereDatum.getTargetSubQuery().getPreparedStatementArgs());
                break;
            case LIKE:
                stringBuilder.append(" like (")
                        .append(whereDatum.getTargetSubQuery().getPreparedStatementSql())
                        .append(")");
                this.preparedStatementArgs.addAll(whereDatum.getTargetSubQuery().getPreparedStatementArgs());
                break;
            case IN:
                stringBuilder.append(" in (")
                        .append(whereDatum.getTargetSubQuery().getPreparedStatementSql())
                        .append(")");
                this.preparedStatementArgs.addAll(whereDatum.getTargetSubQuery().getPreparedStatementArgs());
                break;
            case NOT_IN:
                stringBuilder.append(" not in (")
                        .append(whereDatum.getTargetSubQuery().getPreparedStatementSql())
                        .append(")");
                this.preparedStatementArgs.addAll(whereDatum.getTargetSubQuery().getPreparedStatementArgs());
                break;
            default:
                throw new SqlException("the WhereType is wrong.");
        }
        return stringBuilder;
    }

    private StringBuilder appendWhereDataSqlPartSql(StringBuilder stringBuilder, WhereDatum whereDatum) {
        stringBuilder.append(" ").append(whereDatum.getSqlPart());
        return stringBuilder;
    }

    private StringBuilder appendWhereDataSql(StringBuilder stringBuilder, WhereDatum whereDatum) {
        switch (whereDatum.getWhereValueType()) {
            case VALUE:
                this.appendWhereDataValueSql(stringBuilder, whereDatum);
                break;
            case JOIN:
                this.appendWhereDataJoinSql(stringBuilder, whereDatum);
                break;
            case SUB_QUERY:
                this.appendWhereDataSubQuerySql(stringBuilder, whereDatum);
                break;
            case SQL_PART:
                this.appendWhereDataSqlPartSql(stringBuilder, whereDatum);
                break;
            default:
                throw new SqlException("the WhereValueType is wrong.");
        }
        return stringBuilder;
    }

    private StringBuilder appendWhereDataList(StringBuilder stringBuilder, Set<WhereDatum> whereData, LinkType linkType) {
        if (whereData == null || whereData.size() == 0) {
            return stringBuilder;
        }
        if (linkType == LinkType.OR && whereData.size() > 1) {
            stringBuilder.append("(");
        }
        int i = 0;
        for (WhereDatum whereDatum : whereData) {
            if (i++ > 0) {
                stringBuilder.append(" and ");
            }
            stringBuilder.append(whereDatum.getOwnerTableAlias())
                    .append(".`")
                    .append(whereDatum.getOwnerColumnName())
                    .append("`");
            this.appendWhereDataSql(stringBuilder, whereDatum);
        }
        if (linkType == LinkType.OR && whereData.size() > 1) {
            stringBuilder.append(")");
        }
        return stringBuilder;
    }

    private StringBuilder appendWhereDataLinkerList(StringBuilder stringBuilder, List<WhereDataLinker> whereDataLinkerList, LinkType linkType, boolean checkBrackets) {
        if (whereDataLinkerList == null || whereDataLinkerList.size() == 0) {
            return stringBuilder;
        }
        int length = stringBuilder.length();
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
                            stringBuilder.append(" and ");
                        }
                        this.appendWhereDataList(stringBuilder, whereData, LinkType.AND);
                        continue;
                    case OR:
                        if (i++ > 0) {
                            stringBuilder.append(" or ");
                            brackets = checkBrackets;
                        }
                        this.appendWhereDataList(stringBuilder, whereData, LinkType.OR);
                        continue;
                    default:
                        throw new SqlException("the LinkType is wrong.");
                }
            } else if (childWhereDataLinkerList != null && childWhereDataLinkerList.size() > 0) {
                switch (whereDataLinker.getLinkType()) {
                    case AND:
                        if (i++ > 0) {
                            stringBuilder.append(" and ");
                        }
                        stringBuilder = this.appendWhereDataLinkerList(stringBuilder, childWhereDataLinkerList, LinkType.AND, true);
                        continue;
                    case OR:
                        if (i++ > 0) {
                            stringBuilder.append(" or ");
                            brackets = checkBrackets;
                        }
                        stringBuilder = this.appendWhereDataLinkerList(stringBuilder, childWhereDataLinkerList, LinkType.OR, true);
                        continue;
                    default:
                        throw new SqlException("the LinkType is wrong.");
                }
            }
        }
        if (!checkBrackets) {
            return stringBuilder;
        }
        brackets = brackets || linkType == LinkType.OR && i > 1;
        if (!brackets) {
            return stringBuilder;
        }
        stringBuilder.insert(length, "(").append(")");
        return stringBuilder;
    }

    protected StringBuilder appendWhereSql(StringBuilder stringBuilder) {
        List<List<WhereDataLinker>> whereDataLinkerListList = this.sqlData.getWhereDataLinkerListList();
        if (whereDataLinkerListList == null || whereDataLinkerListList.size() == 0) {
            return stringBuilder;
        }
        stringBuilder.append(" where ");
        int i = 0;
        for (List<WhereDataLinker> whereDataLinkerList : whereDataLinkerListList) {
            if (i++ > 0) {
                stringBuilder.append(" and ");
            }
            stringBuilder = this.appendWhereDataLinkerList(stringBuilder, whereDataLinkerList, LinkType.AND, whereDataLinkerListList.size() > 1);
        }
        return stringBuilder;
    }

    protected StringBuilder appendGroupSql(StringBuilder stringBuilder) {
        Set<TableGroupData> tableGroupDataSet = this.sqlData.getTableGroupDataSet();
        if (tableGroupDataSet == null || tableGroupDataSet.size() == 0) {
            return stringBuilder;
        }
        stringBuilder.append(" group by ");
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
                    stringBuilder.append(",");
                }
                stringBuilder.append(columnName.getOwnerTableAlias())
                        .append(".`")
                        .append(columnName.getOwnerColumnName())
                        .append("`");
            }
        }
        return stringBuilder;
    }

    protected StringBuilder appendSortSql(StringBuilder stringBuilder) {
        Set<TableSortData> tableSortDataSet = this.sqlData.getTableSortDataSet();
        if (tableSortDataSet == null || tableSortDataSet.size() == 0) {
            return stringBuilder;
        }
        stringBuilder.append(" order by ");
        int i = 0;
        Set<SortDatum> sortData;
        for (TableSortData tableSortData : tableSortDataSet) {
            sortData = tableSortData.getSortData();
            if (sortData == null || sortData.size() == 0) {
                continue;
            }
            for (SortDatum sortDatum : sortData) {
                if (i++ > 0) {
                    stringBuilder.append(",");
                }
                stringBuilder.append(sortDatum.getOwnerTableAlias())
                        .append(".`")
                        .append(sortDatum.getOwnerColumnName())
                        .append("`");
                switch (sortDatum.getSortType()) {
                    case ASC:
                        stringBuilder.append(" asc");
                        continue;
                    case DESC:
                        stringBuilder.append(" desc");
                        continue;
                    default:
                        throw new SqlException("the SortType is wrong.");
                }
            }
        }
        return stringBuilder;
    }

    protected StringBuilder appendLimitSql(StringBuilder stringBuilder) {
        LimitHandler limit = this.sqlData.getLimitData();
        if (limit == null) {
            return stringBuilder;
        }
        stringBuilder.append(" limit ?,?");
        this.preparedStatementArgs.add(limit.getLimitStart());
        this.preparedStatementArgs.add(limit.getLimitEnd());
        return stringBuilder;
    }

    protected Set<ColumnDatum> getMainTableColumnData() {
        Set<ColumnDatum> columnData = this.sqlData.getMainTableData().getColumnData();
        if (columnData == null || columnData.size() == 0) {
            return this.sqlData.getMainTableData().buildTableColumnData();
        }
        return columnData;
    }

}
