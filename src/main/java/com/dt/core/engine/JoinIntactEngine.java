package com.dt.core.engine;

import com.dt.core.bean.*;
import com.dt.core.data.JoinTableData;
import com.dt.core.data.MainTableData;
import com.dt.core.norm.Model;
import com.dt.core.norm.OnA;

/**
 * 连接引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class JoinIntactEngine<M extends Model<M, ML, MO, MC, MS, MG>,
        ML extends ColumnModel<M, ML, MO, MC, MS, MG>,
        MO extends OnModel<M, ML, MO, MC, MS, MG>,
        MC extends WhereModel<M, ML, MO, MC, MS, MG>,
        MS extends SortModel<M, ML, MO, MC, MS, MG>,
        MG extends GroupModel<M, ML, MO, MC, MS, MG>> extends ColumnIntactEngine<M, ML, MO, MC, MS, MG> {

    public JoinIntactEngine(Class<M> mainClass, DataBaseType dataBaseType) {
        super(mainClass, dataBaseType);
    }

    public JoinIntactEngine(Class<M> mainClass, String tableName, DataBaseType dataBaseType) {
        super(mainClass, tableName, dataBaseType);
    }

    @SuppressWarnings("unchecked")
    public <J extends Model<J, JL, JO, JC, JS, JG>,
            JL extends ColumnModel<J, JL, JO, JC, JS, JG>,
            JO extends OnModel<J, JL, JO, JC, JS, JG>,
            JC extends WhereModel<J, JL, JO, JC, JS, JG>,
            JS extends SortModel<J, JL, JO, JC, JS, JG>,
            JG extends GroupModel<J, JL, JO, JC, JS, JG>> JoinIntactEngine<M, ML, MO, MC, MS, MG> join(String tableName,
                                                                                                       Class<J> joinClass,
                                                                                                       String alias,
                                                                                                       JoinType joinType,
                                                                                                       OnA<M, ML, MO, MC, MS, MG, J, JL, JO, JC, JS, JG> on) {
        MainTableData<M> mainTableData = this.sqlData.getMainTableData();
        JoinTableData<J> joinTableData = new JoinTableData<>(joinClass);
        joinTableData.setTableName(tableName);
        joinTableData.setTableAlias(alias);
        joinTableData.setJoinType(joinType);
        OnLink<J, JL, JO, JC, JS, JG> onLink = new OnLink<>();
        JO jo = (JO) joinTableData.getTableModel().getOnModel();
        jo.setSqlData(this.sqlData);
        MO mo = (MO) mainTableData.getTableModel().getOnModel();
        OnLink link = on.apply(onLink, jo, mo);
        joinTableData.addLinkOnDataMap(link.getLinkOnDataMap());
        this.sqlData.addJoinTableData(joinTableData);
        return this;
    }

    public <J extends Model<J, JL, JO, JC, JS, JG>,
            JL extends ColumnModel<J, JL, JO, JC, JS, JG>,
            JO extends OnModel<J, JL, JO, JC, JS, JG>,
            JC extends WhereModel<J, JL, JO, JC, JS, JG>,
            JS extends SortModel<J, JL, JO, JC, JS, JG>,
            JG extends GroupModel<J, JL, JO, JC, JS, JG>> JoinIntactEngine<M, ML, MO, MC, MS, MG> join(String tableName,
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
            JG extends GroupModel<J, JL, JO, JC, JS, JG>> JoinIntactEngine<M, ML, MO, MC, MS, MG> join(Class<J> joinClass,
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
            JG extends GroupModel<J, JL, JO, JC, JS, JG>> JoinIntactEngine<M, ML, MO, MC, MS, MG> join(Class<J> joinClass,
                                                                                                       JoinType joinType,
                                                                                                       OnA<M, ML, MO, MC, MS, MG, J, JL, JO, JC, JS, JG> on) {
        return join(null, joinClass, null, joinType, on);
    }

    public <J extends Model<J, JL, JO, JC, JS, JG>,
            JL extends ColumnModel<J, JL, JO, JC, JS, JG>,
            JO extends OnModel<J, JL, JO, JC, JS, JG>,
            JC extends WhereModel<J, JL, JO, JC, JS, JG>,
            JS extends SortModel<J, JL, JO, JC, JS, JG>,
            JG extends GroupModel<J, JL, JO, JC, JS, JG>> JoinIntactEngine<M, ML, MO, MC, MS, MG> innerJoin(String tableName,
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
            JG extends GroupModel<J, JL, JO, JC, JS, JG>> JoinIntactEngine<M, ML, MO, MC, MS, MG> innerJoin(String tableName,
                                                                                                            Class<J> joinClass,
                                                                                                            OnA<M, ML, MO, MC, MS, MG, J, JL, JO, JC, JS, JG> on) {
        return join(tableName, joinClass, null, JoinType.INNER, on);
    }

    public <J extends Model<J, JL, JO, JC, JS, JG>,
            JL extends ColumnModel<J, JL, JO, JC, JS, JG>,
            JO extends OnModel<J, JL, JO, JC, JS, JG>,
            JC extends WhereModel<J, JL, JO, JC, JS, JG>,
            JS extends SortModel<J, JL, JO, JC, JS, JG>,
            JG extends GroupModel<J, JL, JO, JC, JS, JG>> JoinIntactEngine<M, ML, MO, MC, MS, MG> innerJoin(Class<J> joinClass,
                                                                                                            String alias,
                                                                                                            OnA<M, ML, MO, MC, MS, MG, J, JL, JO, JC, JS, JG> on) {
        return join(null, joinClass, alias, JoinType.INNER, on);
    }

    public <J extends Model<J, JL, JO, JC, JS, JG>,
            JL extends ColumnModel<J, JL, JO, JC, JS, JG>,
            JO extends OnModel<J, JL, JO, JC, JS, JG>,
            JC extends WhereModel<J, JL, JO, JC, JS, JG>,
            JS extends SortModel<J, JL, JO, JC, JS, JG>,
            JG extends GroupModel<J, JL, JO, JC, JS, JG>> JoinIntactEngine<M, ML, MO, MC, MS, MG> innerJoin(Class<J> joinClass,
                                                                                                            OnA<M, ML, MO, MC, MS, MG, J, JL, JO, JC, JS, JG> on) {
        return join(null, joinClass, null, JoinType.INNER, on);
    }

    public <J extends Model<J, JL, JO, JC, JS, JG>,
            JL extends ColumnModel<J, JL, JO, JC, JS, JG>,
            JO extends OnModel<J, JL, JO, JC, JS, JG>,
            JC extends WhereModel<J, JL, JO, JC, JS, JG>,
            JS extends SortModel<J, JL, JO, JC, JS, JG>,
            JG extends GroupModel<J, JL, JO, JC, JS, JG>> JoinIntactEngine<M, ML, MO, MC, MS, MG> leftJoin(String tableName,
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
            JG extends GroupModel<J, JL, JO, JC, JS, JG>> JoinIntactEngine<M, ML, MO, MC, MS, MG> leftJoin(String tableName,
                                                                                                           Class<J> joinClass,
                                                                                                           OnA<M, ML, MO, MC, MS, MG, J, JL, JO, JC, JS, JG> on) {
        return join(tableName, joinClass, null, JoinType.LEFT, on);
    }

    public <J extends Model<J, JL, JO, JC, JS, JG>,
            JL extends ColumnModel<J, JL, JO, JC, JS, JG>,
            JO extends OnModel<J, JL, JO, JC, JS, JG>,
            JC extends WhereModel<J, JL, JO, JC, JS, JG>,
            JS extends SortModel<J, JL, JO, JC, JS, JG>,
            JG extends GroupModel<J, JL, JO, JC, JS, JG>> JoinIntactEngine<M, ML, MO, MC, MS, MG> leftJoin(Class<J> joinClass,
                                                                                                           String alias,
                                                                                                           OnA<M, ML, MO, MC, MS, MG, J, JL, JO, JC, JS, JG> on) {
        return join(null, joinClass, alias, JoinType.LEFT, on);
    }

    public <J extends Model<J, JL, JO, JC, JS, JG>,
            JL extends ColumnModel<J, JL, JO, JC, JS, JG>,
            JO extends OnModel<J, JL, JO, JC, JS, JG>,
            JC extends WhereModel<J, JL, JO, JC, JS, JG>,
            JS extends SortModel<J, JL, JO, JC, JS, JG>,
            JG extends GroupModel<J, JL, JO, JC, JS, JG>> JoinIntactEngine<M, ML, MO, MC, MS, MG> leftJoin(Class<J> joinClass,
                                                                                                           OnA<M, ML, MO, MC, MS, MG, J, JL, JO, JC, JS, JG> on) {
        return join(null, joinClass, null, JoinType.LEFT, on);
    }

    public <J extends Model<J, JL, JO, JC, JS, JG>,
            JL extends ColumnModel<J, JL, JO, JC, JS, JG>,
            JO extends OnModel<J, JL, JO, JC, JS, JG>,
            JC extends WhereModel<J, JL, JO, JC, JS, JG>,
            JS extends SortModel<J, JL, JO, JC, JS, JG>,
            JG extends GroupModel<J, JL, JO, JC, JS, JG>> JoinIntactEngine<M, ML, MO, MC, MS, MG> rightJoin(String tableName,
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
            JG extends GroupModel<J, JL, JO, JC, JS, JG>> JoinIntactEngine<M, ML, MO, MC, MS, MG> rightJoin(String tableName,
                                                                                                            Class<J> joinClass,
                                                                                                            OnA<M, ML, MO, MC, MS, MG, J, JL, JO, JC, JS, JG> on) {
        return join(tableName, joinClass, null, JoinType.RIGHT, on);
    }

    public <J extends Model<J, JL, JO, JC, JS, JG>,
            JL extends ColumnModel<J, JL, JO, JC, JS, JG>,
            JO extends OnModel<J, JL, JO, JC, JS, JG>,
            JC extends WhereModel<J, JL, JO, JC, JS, JG>,
            JS extends SortModel<J, JL, JO, JC, JS, JG>,
            JG extends GroupModel<J, JL, JO, JC, JS, JG>> JoinIntactEngine<M, ML, MO, MC, MS, MG> rightJoin(Class<J> joinClass,
                                                                                                            String alias,
                                                                                                            OnA<M, ML, MO, MC, MS, MG, J, JL, JO, JC, JS, JG> on) {
        return join(null, joinClass, alias, JoinType.RIGHT, on);
    }

    public <J extends Model<J, JL, JO, JC, JS, JG>,
            JL extends ColumnModel<J, JL, JO, JC, JS, JG>,
            JO extends OnModel<J, JL, JO, JC, JS, JG>,
            JC extends WhereModel<J, JL, JO, JC, JS, JG>,
            JS extends SortModel<J, JL, JO, JC, JS, JG>,
            JG extends GroupModel<J, JL, JO, JC, JS, JG>> JoinIntactEngine<M, ML, MO, MC, MS, MG> rightJoin(Class<J> joinClass,
                                                                                                            OnA<M, ML, MO, MC, MS, MG, J, JL, JO, JC, JS, JG> on) {
        return join(null, joinClass, null, JoinType.RIGHT, on);
    }

}
