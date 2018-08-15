package com.dt.core.parser;

import com.dt.core.bean.LinkType;
import com.dt.core.data.LinkWhereData;
import com.dt.core.data.ParseData;
import com.dt.core.data.WhereData;
import com.dt.core.exception.DtException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 条件数据解析器
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class WhereParser {

    private WhereParser() {
    }

    private static final WhereParser WHERE_PARSER = new WhereParser();

    public static WhereParser getInstance() {
        return WHERE_PARSER;
    }

    private ParseData parseWhereData(List<WhereData> whereDataList, LinkType ownerLinkType) {
        if (whereDataList == null || whereDataList.size() == 0) {
            return null;
        }
        StringBuilder sql = new StringBuilder(64);
        List<Object> args = new ArrayList<>();
        for (WhereData whereData : whereDataList) {
            sql.append(" and ")
                    .append(whereData.getOwnerAlias())
                    .append(".")
                    .append(whereData.getOwnerColumnName());
            switch (whereData.getWhereType()) {
                case IS_NULL:
                    sql.append(" is null");
                    continue;
                case IS_NOT_NULL:
                    sql.append(" is not null");
                    continue;
                case EQUAL:
                    sql.append(" = ?");
                    args.add(whereData.getTargetValue());
                    continue;
                case NOT_EQUAL:
                    sql.append(" != ?");
                    args.add(whereData.getTargetValue());
                    continue;
                case GREATER:
                    sql.append(" > ?");
                    args.add(whereData.getTargetValue());
                    continue;
                case GREATER_EQUAL:
                    sql.append(" >= ?");
                    args.add(whereData.getTargetValue());
                    continue;
                case LESS:
                    sql.append(" < ?");
                    args.add(whereData.getTargetValue());
                    continue;
                case LESS_EQUAL:
                    sql.append(" <= ?");
                    args.add(whereData.getTargetValue());
                    continue;
                case BETWEEN:
                    sql.append(" between ? and ?");
                    args.add(whereData.getTargetValue());
                    args.add(whereData.getTargetSecondValue());
                    continue;
                case LIKE:
                    sql.append(" like ?");
                    args.add(whereData.getTargetValue());
                    continue;
                case IN:
                    int count = whereData.getValueCount();
                    sql.append(" in (");
                    for (; count > 0; count--) {
                        if (count == 1) {
                            sql.append("?");
                        } else {
                            sql.append("?,");
                        }
                    }
                    sql.append(")");
                    Object value = whereData.getTargetValue();
                    if (value instanceof Collection) {
                        args.addAll((Collection<?>) value);
                    } else if (value.getClass().isArray()) {
                        for (Object o : (Object[]) value) {
                            args.add(o);
                        }
                    }
                    continue;
                default:
                    throw new DtException("the WhereType is wrong.");
            }
        }
        ParseData parseData = null;
        switch (ownerLinkType) {
            case AND:
                parseData = new ParseData();
                parseData.setSql(sql.length() > 4 ? sql.substring(5) : null);
                parseData.setArgs(args);
                break;
            case OR:
                parseData = new ParseData();
                parseData.setArgs(args);
                if (whereDataList.size() > 1) {
                    parseData.setSql(sql.length() > 4 ? sql.replace(0, 5, "(").append(")").toString() : null);
                } else {
                    parseData.setSql(sql.length() > 4 ? sql.substring(5) : null);
                }
                break;
            default:
                return null;
        }
        return parseData;
    }

    private ParseData parseLinkWhereData(List<LinkWhereData> linkWhereDataList, LinkType ownerLinkType) {
        if (linkWhereDataList == null || linkWhereDataList.size() == 0) {
            return null;
        }
        ParseData data;
        int i = 0;
        int orCount = 0;
        StringBuilder sql = new StringBuilder(128);
        List<Object> args = new ArrayList<>();
        for (LinkWhereData linkWhereData : linkWhereDataList) {
            List<WhereData> whereDataList = linkWhereData.getWhereDataList();
            List<LinkWhereData> list = linkWhereData.getLinkWhereDataList();
            if (whereDataList != null && whereDataList.size() > 0) {
                switch (linkWhereData.getLinkType()) {
                    case AND:
                        data = parseWhereData(whereDataList, LinkType.AND);
                        if (data != null && data.getSql() != null) {
                            sql.append(" and ").append(data.getSql());
                            args.addAll(data.getArgs());
                            i++;
                        }
                        continue;
                    case OR:
                        data = parseWhereData(whereDataList, LinkType.OR);
                        if (data != null && data.getSql() != null) {
                            sql.append(" or ").append(data.getSql());
                            args.addAll(data.getArgs());
                            i++;
                            orCount++;
                        }
                        continue;
                    default:
                        return null;
                }
            } else if (list != null && list.size() > 0) {
                switch (linkWhereData.getLinkType()) {
                    case AND:
                        data = parseLinkWhereData(list, LinkType.AND);
                        if (data != null && data.getSql() != null) {
                            sql.append(" and ").append(data.getSql());
                            args.addAll(data.getArgs());
                            i++;
                        }
                        continue;
                    case OR:
                        data = parseLinkWhereData(list, LinkType.OR);
                        if (data != null && data.getSql() != null) {
                            sql.append(" or ").append(data.getSql());
                            args.addAll(data.getArgs());
                            i++;
                            orCount++;
                        }
                        continue;
                    default:
                        return null;
                }
            }
        }
        ParseData parseData = null;
        switch (ownerLinkType) {
            case AND:
                parseData = new ParseData();
                parseData.setArgs(args);
                if (orCount > 0) {
                    parseData.setSql(sql.length() > 4 ? sql.replace(0, 5, "(").append(")").toString() : null);
                } else {
                    parseData.setSql(sql.length() > 4 ? sql.substring(5) : null);
                }
                break;
            case OR:
                parseData = new ParseData();
                parseData.setArgs(args);
                if (i > 1) {
                    parseData.setSql(sql.length() > 4 ? sql.replace(0, 5, "(").append(")").toString() : null);
                } else {
                    parseData.setSql(sql.length() > 4 ? sql.substring(5) : null);
                }
                break;
            default:
                return null;
        }
        return parseData;
    }

    public ParseData parse(List<List<LinkWhereData>> linkWhereDataList) {
        if (linkWhereDataList == null || linkWhereDataList.size() == 0) {
            return null;
        }
        StringBuilder sql = new StringBuilder(256);
        ParseData data;
        List<Object> args = new ArrayList<>();
        for (List<LinkWhereData> linkWhereData : linkWhereDataList) {
            data = parseLinkWhereData(linkWhereData, LinkType.AND);
            if (data != null && data.getSql() != null) {
                sql.append(" and ").append(data.getSql());
                args.addAll(data.getArgs());
            }
        }
        ParseData parseData = new ParseData();
        parseData.setSql(sql.length() > 4 ? sql.replace(0, 4, "where").toString() : "");
        parseData.setArgs(args);
        return parseData;
    }

}
