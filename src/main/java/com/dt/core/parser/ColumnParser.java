package com.dt.core.parser;

import com.dt.core.data.FunctionColumnData;
import com.dt.core.data.MainTableData;
import com.dt.core.data.AbstractTableData;
import com.dt.core.data.VirtualFieldData;
import com.dt.core.exception.DtException;
import com.dt.core.exception.TableDataException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 字段数据解析器
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class ColumnParser {

    private ColumnParser() {
    }

    private static final ColumnParser COLUMN_PARSER = new ColumnParser();

    public static ColumnParser getInstance() {
        return COLUMN_PARSER;
    }

    public String parse(MainTableData mainTableData, List<FunctionColumnData> functionColumnDataSet, Set<VirtualFieldData> virtualFieldDataSet, Set<AbstractTableData> columnDataSet) {
        boolean hasF = functionColumnDataSet != null && functionColumnDataSet.size() != 0;
        boolean hasV = virtualFieldDataSet != null && virtualFieldDataSet.size() != 0;
        boolean hasC = columnDataSet != null && columnDataSet.size() != 0;
        StringBuilder sql = new StringBuilder(64);
        Map<String, String> columnAliasMap;
        String tableAlias;
        if (!hasF && !hasV && !hasC) {
            columnAliasMap = mainTableData.getTable().getColumnAliasMap();
            tableAlias = mainTableData.getTableAlias();
            for (Map.Entry<String, String> entry : columnAliasMap.entrySet()) {
                sql.append(",")
                        .append(tableAlias)
                        .append(".`")
                        .append(entry.getKey())
                        .append("` `")
                        .append(entry.getValue())
                        .append("`");
            }
            return sql.substring(1);
        }
        Map<String, Boolean> cache = new HashMap<>(32);

        if (hasF) {
            AbstractTableData tableData;
            String alias;
            for (FunctionColumnData data : functionColumnDataSet) {
                tableData = data.getTableData();
                for (Map.Entry<String, String> entry : data.getColumnAliasMap().entrySet()) {
                    alias = entry.getValue();
                    if (cache.get(alias) != null) {
                        throw new TableDataException("FunctionColumn alias [" + alias + "] is already be used, please set another alias.");
                    }
                    sql.append(",");
                    switch (data.getFunctionColumnType()) {
                        case MIN:
                            sql.append("min(");
                            break;
                        case MAX:
                            sql.append("max(");
                            break;
                        case COUNT:
                            sql.append("count(");
                            break;
                        default:
                            throw new DtException("the functionColumnType is wrong.");
                    }
                    sql.append(tableData.getTableAlias())
                            .append(".`")
                            .append(entry.getKey())
                            .append("`) `")
                            .append(alias)
                            .append("`");
                    cache.put(alias, true);
                }
            }
        }

        if (hasV) {
            Object value;
            String alias;
            for (VirtualFieldData data : virtualFieldDataSet) {
                sql.append(",");
                value = data.getValue();
                alias = data.getAlias();
                if (cache.get(alias) != null) {
                    throw new TableDataException("VirtualField alias [" + alias + "] is already be used, please set another alias.");
                }
                if (value == null) {
                    sql.append("null");
                } else if (value instanceof String) {
                    sql.append("'").append(value).append("'");
                } else if (value instanceof Integer) {
                    sql.append(value);
                } else if (value instanceof Long) {
                    sql.append(value);
                } else if (value instanceof Double) {
                    sql.append(value);
                } else {
                    throw new DtException("the VirtualFieldData value type is wrong.");
                }
                if (alias != null) {
                    sql.append(" `").append(alias).append("`");
                    cache.put(alias, true);
                } else {
                    cache.put(value + "", true);
                }
            }
        }

        if (hasC) {
            for (AbstractTableData tableData : columnDataSet) {
                columnAliasMap = tableData.getColumnAliasMap();
                if (columnAliasMap.size() == 0) {
                    columnAliasMap = tableData.getTable().getColumnAliasMap();
                    tableAlias = tableData.getTableAlias();
                    if (tableData.getTableClass() == mainTableData.getTableClass()) {
                        for (Map.Entry<String, String> entry : columnAliasMap.entrySet()) {
                            sql.append(",")
                                    .append(tableAlias)
                                    .append(".`")
                                    .append(entry.getKey())
                                    .append("` `")
                                    .append(entry.getValue())
                                    .append("`");
                        }
                    } else {
                        for (Map.Entry<String, String> entry : columnAliasMap.entrySet()) {
                            sql.append(",")
                                    .append(tableAlias)
                                    .append(".`")
                                    .append(entry.getKey())
                                    .append("`");
                        }
                    }
                    continue;
                }
                for (Map.Entry<String, String> entry : columnAliasMap.entrySet()) {
                    if (cache.get(entry.getValue()) != null) {
                        throw new TableDataException("table alias [" + tableData.getTableAlias() + "] column alias [" + entry.getValue() + "] is already be used, please set another alias.");
                    }
                    sql.append(", ")
                            .append(tableData.getTableAlias())
                            .append(".`")
                            .append(entry.getKey())
                            .append("` `")
                            .append(entry.getValue())
                            .append("`");
                    cache.put(entry.getValue(), true);
                }
            }
        }
        return sql.substring(1);
    }

}
