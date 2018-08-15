package com.dt.core.parser;

import com.dt.core.data.SortData;
import com.dt.core.data.AbstractTableData;
import com.dt.core.exception.DtException;

import java.util.List;

/**
 * 排序数据解析器
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class SortParser {

    private SortParser() {
    }

    private static final SortParser SORT_PARSER = new SortParser();

    public static SortParser getInstance() {
        return SORT_PARSER;
    }

    public String parse(List<List<SortData>> sortDataList) {
        if (sortDataList == null || sortDataList.size() == 0) {
            return null;
        }
        StringBuilder sql = new StringBuilder(64);
        AbstractTableData tableData;
        for (List<SortData> sortData : sortDataList) {
            for (SortData data : sortData) {
                tableData = data.getTableData();
                sql.append(", ")
                        .append(tableData.getTableAlias())
                        .append(".")
                        .append(data.getColumnName());
                switch (data.getSortType()) {
                    case ASC:
                        sql.append(" asc");
                        continue;
                    case DESC:
                        sql.append(" desc");
                        continue;
                    default:
                        throw new DtException("the SortType is wrong.");
                }
            }
        }
        return sql.length() > 2 ? sql.replace(0, 2, "order by ").toString() : "";
    }
}
