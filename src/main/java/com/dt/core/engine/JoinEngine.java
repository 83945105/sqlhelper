package com.dt.core.engine;

import com.dt.core.bean.*;
import com.dt.core.data.JoinTableData;
import com.dt.core.data.MainTableData;
import com.dt.core.data.ParseData;
import com.dt.core.norm.Data;
import com.dt.core.norm.Model;
import com.dt.core.norm.OnA;
import com.dt.core.parser.JoinParser;

/**
 * 连接引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class JoinEngine<M extends Model<M, ML, MO, MC, MS, MG>,
        ML extends ColumnModel<M, ML, MO, MC, MS, MG>,
        MO extends OnModel<M, ML, MO, MC, MS, MG>,
        MC extends WhereModel<M, ML, MO, MC, MS, MG>,
        MS extends SortModel<M, ML, MO, MC, MS, MG>,
        MG extends GroupModel<M, ML, MO, MC, MS, MG>> extends ColumnIntactEngine<M, ML, MO, MC, MS, MG> {

    private JoinParser joinParser = JoinParser.getInstance();

    public JoinEngine(Class<M> mainClass) {
        super(mainClass);
    }

    JoinEngine(Class<M> mainClass, String tableName) {
        super(mainClass, tableName);
    }

    @SuppressWarnings("unchecked")
    public <J extends Model<J, JL, JO, JC, JS, JG>,
            JL extends ColumnModel<J, JL, JO, JC, JS, JG>,
            JO extends OnModel<J, JL, JO, JC, JS, JG>,
            JC extends WhereModel<J, JL, JO, JC, JS, JG>,
            JS extends SortModel<J, JL, JO, JC, JS, JG>,
            JG extends GroupModel<J, JL, JO, JC, JS, JG>> JoinEngine<M, ML, MO, MC, MS, MG> join(String tableName,
                                                                                                 Class<J> joinClass,
                                                                                                 String alias,
                                                                                                 JoinType joinType,
                                                                                                 OnA<M, ML, MO, MC, MS, MG, J, JL, JO, JC, JS, JG> on) {
        MainTableData<M, ML, MO, MC, MS, MG> mainTableData = this.data.getMainTableData();
        JoinTableData<J, JL, JO, JC, JS, JG> joinTableData = new JoinTableData<>(joinClass);
        joinTableData.setTableName(tableName);
        joinTableData.setTableAlias(alias);
        joinTableData.setJoinType(joinType);
        OnLink<J, JL, JO, JC, JS, JG> onLink = new OnLink<>();
        JO jo = (JO) joinTableData.getTable().getOn();
        jo.setData(this.data);
        MO mo = (MO) mainTableData.getTable().getOn();
        OnLink link = on.apply(onLink, jo, mo);
        joinTableData.addLinkOnDataMap(link.getLinkOnDataMap());
        this.data.addJoinTableData(joinTableData);
        return this;
    }

    public <J extends Model<J, JL, JO, JC, JS, JG>,
            JL extends ColumnModel<J, JL, JO, JC, JS, JG>,
            JO extends OnModel<J, JL, JO, JC, JS, JG>,
            JC extends WhereModel<J, JL, JO, JC, JS, JG>,
            JS extends SortModel<J, JL, JO, JC, JS, JG>,
            JG extends GroupModel<J, JL, JO, JC, JS, JG>> JoinEngine<M, ML, MO, MC, MS, MG> join(String tableName,
                                                                                                 Class<J> joinClass,
                                                                                                 JoinType joinType,
                                                                                                 OnA<M, ML, MO, MC, MS, MG, J, JL, JO, JC, JS, JG> on) {
        return join(tableName, joinClass, null, joinType, on);
    }

    public <J extends Model<J, JL, JO, JC, JS, JG>,
            JL extends ColumnModel<J, JL, JO, JC, JS, JG>,
            JO extends OnModel<J, JL, JO, JC, JS, JG>,
            JC extends WhereModel<J, JL, JO, JC, JS, JG>,
            JS extends SortModel<J, JL, JO, JC, JS, JG>,
            JG extends GroupModel<J, JL, JO, JC, JS, JG>> JoinEngine<M, ML, MO, MC, MS, MG> join(Class<J> joinClass,
                                                                                                 String alias,
                                                                                                 JoinType joinType,
                                                                                                 OnA<M, ML, MO, MC, MS, MG, J, JL, JO, JC, JS, JG> on) {
        return join(null, joinClass, alias, joinType, on);
    }

    public <J extends Model<J, JL, JO, JC, JS, JG>,
            JL extends ColumnModel<J, JL, JO, JC, JS, JG>,
            JO extends OnModel<J, JL, JO, JC, JS, JG>,
            JC extends WhereModel<J, JL, JO, JC, JS, JG>,
            JS extends SortModel<J, JL, JO, JC, JS, JG>,
            JG extends GroupModel<J, JL, JO, JC, JS, JG>> JoinEngine<M, ML, MO, MC, MS, MG> join(Class<J> joinClass,
                                                                                                 JoinType joinType,
                                                                                                 OnA<M, ML, MO, MC, MS, MG, J, JL, JO, JC, JS, JG> on) {
        return join(null, joinClass, null, joinType, on);
    }

    public <J extends Model<J, JL, JO, JC, JS, JG>,
            JL extends ColumnModel<J, JL, JO, JC, JS, JG>,
            JO extends OnModel<J, JL, JO, JC, JS, JG>,
            JC extends WhereModel<J, JL, JO, JC, JS, JG>,
            JS extends SortModel<J, JL, JO, JC, JS, JG>,
            JG extends GroupModel<J, JL, JO, JC, JS, JG>> JoinEngine<M, ML, MO, MC, MS, MG> innerJoin(String tableName,
                                                                                                      Class<J> joinClass,
                                                                                                      String alias,
                                                                                                      OnA<M, ML, MO, MC, MS, MG, J, JL, JO, JC, JS, JG> on) {
        return join(tableName, joinClass, alias, JoinType.INNER, on);
    }

    public <J extends Model<J, JL, JO, JC, JS, JG>,
            JL extends ColumnModel<J, JL, JO, JC, JS, JG>,
            JO extends OnModel<J, JL, JO, JC, JS, JG>,
            JC extends WhereModel<J, JL, JO, JC, JS, JG>,
            JS extends SortModel<J, JL, JO, JC, JS, JG>,
            JG extends GroupModel<J, JL, JO, JC, JS, JG>> JoinEngine<M, ML, MO, MC, MS, MG> innerJoin(String tableName,
                                                                                                      Class<J> joinClass,
                                                                                                      OnA<M, ML, MO, MC, MS, MG, J, JL, JO, JC, JS, JG> on) {
        return join(tableName, joinClass, null, JoinType.INNER, on);
    }

    public <J extends Model<J, JL, JO, JC, JS, JG>,
            JL extends ColumnModel<J, JL, JO, JC, JS, JG>,
            JO extends OnModel<J, JL, JO, JC, JS, JG>,
            JC extends WhereModel<J, JL, JO, JC, JS, JG>,
            JS extends SortModel<J, JL, JO, JC, JS, JG>,
            JG extends GroupModel<J, JL, JO, JC, JS, JG>> JoinEngine<M, ML, MO, MC, MS, MG> innerJoin(Class<J> joinClass,
                                                                                                      String alias,
                                                                                                      OnA<M, ML, MO, MC, MS, MG, J, JL, JO, JC, JS, JG> on) {
        return join(null, joinClass, alias, JoinType.INNER, on);
    }

    public <J extends Model<J, JL, JO, JC, JS, JG>,
            JL extends ColumnModel<J, JL, JO, JC, JS, JG>,
            JO extends OnModel<J, JL, JO, JC, JS, JG>,
            JC extends WhereModel<J, JL, JO, JC, JS, JG>,
            JS extends SortModel<J, JL, JO, JC, JS, JG>,
            JG extends GroupModel<J, JL, JO, JC, JS, JG>> JoinEngine<M, ML, MO, MC, MS, MG> innerJoin(Class<J> joinClass,
                                                                                                      OnA<M, ML, MO, MC, MS, MG, J, JL, JO, JC, JS, JG> on) {
        return join(null, joinClass, null, JoinType.INNER, on);
    }

    public <J extends Model<J, JL, JO, JC, JS, JG>,
            JL extends ColumnModel<J, JL, JO, JC, JS, JG>,
            JO extends OnModel<J, JL, JO, JC, JS, JG>,
            JC extends WhereModel<J, JL, JO, JC, JS, JG>,
            JS extends SortModel<J, JL, JO, JC, JS, JG>,
            JG extends GroupModel<J, JL, JO, JC, JS, JG>> JoinEngine<M, ML, MO, MC, MS, MG> leftJoin(String tableName,
                                                                                                     Class<J> joinClass,
                                                                                                     String alias,
                                                                                                     OnA<M, ML, MO, MC, MS, MG, J, JL, JO, JC, JS, JG> on) {
        return join(tableName, joinClass, alias, JoinType.LEFT, on);
    }

    public <J extends Model<J, JL, JO, JC, JS, JG>,
            JL extends ColumnModel<J, JL, JO, JC, JS, JG>,
            JO extends OnModel<J, JL, JO, JC, JS, JG>,
            JC extends WhereModel<J, JL, JO, JC, JS, JG>,
            JS extends SortModel<J, JL, JO, JC, JS, JG>,
            JG extends GroupModel<J, JL, JO, JC, JS, JG>> JoinEngine<M, ML, MO, MC, MS, MG> leftJoin(String tableName,
                                                                                                     Class<J> joinClass,
                                                                                                     OnA<M, ML, MO, MC, MS, MG, J, JL, JO, JC, JS, JG> on) {
        return join(tableName, joinClass, null, JoinType.LEFT, on);
    }

    public <J extends Model<J, JL, JO, JC, JS, JG>,
            JL extends ColumnModel<J, JL, JO, JC, JS, JG>,
            JO extends OnModel<J, JL, JO, JC, JS, JG>,
            JC extends WhereModel<J, JL, JO, JC, JS, JG>,
            JS extends SortModel<J, JL, JO, JC, JS, JG>,
            JG extends GroupModel<J, JL, JO, JC, JS, JG>> JoinEngine<M, ML, MO, MC, MS, MG> leftJoin(Class<J> joinClass,
                                                                                                     String alias,
                                                                                                     OnA<M, ML, MO, MC, MS, MG, J, JL, JO, JC, JS, JG> on) {
        return join(null, joinClass, alias, JoinType.LEFT, on);
    }

    public <J extends Model<J, JL, JO, JC, JS, JG>,
            JL extends ColumnModel<J, JL, JO, JC, JS, JG>,
            JO extends OnModel<J, JL, JO, JC, JS, JG>,
            JC extends WhereModel<J, JL, JO, JC, JS, JG>,
            JS extends SortModel<J, JL, JO, JC, JS, JG>,
            JG extends GroupModel<J, JL, JO, JC, JS, JG>> JoinEngine<M, ML, MO, MC, MS, MG> leftJoin(Class<J> joinClass,
                                                                                                     OnA<M, ML, MO, MC, MS, MG, J, JL, JO, JC, JS, JG> on) {
        return join(null, joinClass, null, JoinType.LEFT, on);
    }

    public <J extends Model<J, JL, JO, JC, JS, JG>,
            JL extends ColumnModel<J, JL, JO, JC, JS, JG>,
            JO extends OnModel<J, JL, JO, JC, JS, JG>,
            JC extends WhereModel<J, JL, JO, JC, JS, JG>,
            JS extends SortModel<J, JL, JO, JC, JS, JG>,
            JG extends GroupModel<J, JL, JO, JC, JS, JG>> JoinEngine<M, ML, MO, MC, MS, MG> rightJoin(String tableName,
                                                                                                      Class<J> joinClass,
                                                                                                      String alias,
                                                                                                      OnA<M, ML, MO, MC, MS, MG, J, JL, JO, JC, JS, JG> on) {
        return join(tableName, joinClass, alias, JoinType.RIGHT, on);
    }

    public <J extends Model<J, JL, JO, JC, JS, JG>,
            JL extends ColumnModel<J, JL, JO, JC, JS, JG>,
            JO extends OnModel<J, JL, JO, JC, JS, JG>,
            JC extends WhereModel<J, JL, JO, JC, JS, JG>,
            JS extends SortModel<J, JL, JO, JC, JS, JG>,
            JG extends GroupModel<J, JL, JO, JC, JS, JG>> JoinEngine<M, ML, MO, MC, MS, MG> rightJoin(String tableName,
                                                                                                      Class<J> joinClass,
                                                                                                      OnA<M, ML, MO, MC, MS, MG, J, JL, JO, JC, JS, JG> on) {
        return join(tableName, joinClass, null, JoinType.RIGHT, on);
    }

    public <J extends Model<J, JL, JO, JC, JS, JG>,
            JL extends ColumnModel<J, JL, JO, JC, JS, JG>,
            JO extends OnModel<J, JL, JO, JC, JS, JG>,
            JC extends WhereModel<J, JL, JO, JC, JS, JG>,
            JS extends SortModel<J, JL, JO, JC, JS, JG>,
            JG extends GroupModel<J, JL, JO, JC, JS, JG>> JoinEngine<M, ML, MO, MC, MS, MG> rightJoin(Class<J> joinClass,
                                                                                                      String alias,
                                                                                                      OnA<M, ML, MO, MC, MS, MG, J, JL, JO, JC, JS, JG> on) {
        return join(null, joinClass, alias, JoinType.RIGHT, on);
    }

    public <J extends Model<J, JL, JO, JC, JS, JG>,
            JL extends ColumnModel<J, JL, JO, JC, JS, JG>,
            JO extends OnModel<J, JL, JO, JC, JS, JG>,
            JC extends WhereModel<J, JL, JO, JC, JS, JG>,
            JS extends SortModel<J, JL, JO, JC, JS, JG>,
            JG extends GroupModel<J, JL, JO, JC, JS, JG>> JoinEngine<M, ML, MO, MC, MS, MG> rightJoin(Class<J> joinClass,
                                                                                                      OnA<M, ML, MO, MC, MS, MG, J, JL, JO, JC, JS, JG> on) {
        return join(null, joinClass, null, JoinType.RIGHT, on);
    }

    @Override
    public ParseData getJoinParseData() {
        return this.joinParser.parse(this.data.getJoinTableDataAliasMap());
    }

}
