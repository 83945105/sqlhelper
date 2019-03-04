package pub.avalon.sqlhelper.core.engine;

import pub.avalon.beans.DataBaseType;
import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.data.FinalSqlData;
import pub.avalon.sqlhelper.core.data.JoinTableData;
import pub.avalon.sqlhelper.core.data.MainTableData;
import pub.avalon.sqlhelper.core.norm.Model;
import pub.avalon.sqlhelper.core.norm.On;

/**
 * 连接引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class JoinIntactEngine<M extends Model<M, MC, MO, MW, MS, MG>,
        MC extends ColumnModel<M, MC, MO, MW, MS, MG>,
        MO extends OnModel<M, MC, MO, MW, MS, MG>,
        MW extends WhereModel<M, MC, MO, MW, MS, MG>,
        MS extends SortModel<M, MC, MO, MW, MS, MG>,
        MG extends GroupModel<M, MC, MO, MW, MS, MG>> extends ColumnIntactEngine<M, MC, MO, MW, MS, MG> {

    public JoinIntactEngine(Class<M> mainClass, DataBaseType dataBaseType) {
        super(mainClass, dataBaseType);
    }

    public JoinIntactEngine(String tableName, Class<M> mainClass, DataBaseType dataBaseType) {
        super(tableName, mainClass, dataBaseType);
    }

    public JoinIntactEngine(String tableName, Class<M> mainClass, String alias, DataBaseType dataBaseType) {
        super(tableName, mainClass, alias, dataBaseType);
    }

    public <J extends Model<J, JC, JO, JW, JS, JG>,
            JC extends ColumnModel<J, JC, JO, JW, JS, JG>,
            JO extends OnModel<J, JC, JO, JW, JS, JG>,
            JW extends WhereModel<J, JC, JO, JW, JS, JG>,
            JS extends SortModel<J, JC, JO, JW, JS, JG>,
            JG extends GroupModel<J, JC, JO, JW, JS, JG>> JoinIntactEngine<M, MC, MO, MW, MS, MG> join(String tableName,
                                                                                                       Class<J> joinClass,
                                                                                                       String alias,
                                                                                                       JoinType joinType,
                                                                                                       On<M, MC, MO, MW, MS, MG, J, JC, JO, JW, JS, JG> on) {
        MainTableData<M> mainTableData = this.sqlData.getMainTableData();
        JoinTableData<J> joinTableData = new JoinTableData<>(joinClass);
        joinTableData.setTableName(tableName);
        joinTableData.setTableAlias(alias);
        joinTableData.setJoinType(joinType);
        this.sqlData.addJoinTableData(joinTableData);
        MO mo = mainTableData.getTableModel().getOnModel();
        mo.getOnBuilder().setOwnerTableData(mainTableData);
        mo.setSqlData(this.sqlData);
        OnLink<M, MC, MO, MW, MS, MG, J, JC, JO, JW, JS, JG> onLink = new OnLinkIntact<>(this.sqlData, joinClass, alias);
        JO jo = joinTableData.getTableModel().getOnModel();
        jo.getOnBuilder().setOwnerTableData(joinTableData);
        //TODO 创建新的mainTable
        jo.setSqlData(this.sqlData.fission(joinClass));
        OnLink link = on.apply(onLink, jo, mo);
        joinTableData.addOnDataLinkerList(link.getAndResetOnDataLinkerList());
        return this;
    }

    public <J extends Model<J, JC, JO, JW, JS, JG>,
            JC extends ColumnModel<J, JC, JO, JW, JS, JG>,
            JO extends OnModel<J, JC, JO, JW, JS, JG>,
            JW extends WhereModel<J, JC, JO, JW, JS, JG>,
            JS extends SortModel<J, JC, JO, JW, JS, JG>,
            JG extends GroupModel<J, JC, JO, JW, JS, JG>> JoinIntactEngine<M, MC, MO, MW, MS, MG> join(String tableName,
                                                                                                       Class<J> joinClass,
                                                                                                       JoinType joinType,
                                                                                                       On<M, MC, MO, MW, MS, MG, J, JC, JO, JW, JS, JG> on) {
        return join(tableName, joinClass, null, joinType, on);
    }

    public <J extends Model<J, JC, JO, JW, JS, JG>,
            JC extends ColumnModel<J, JC, JO, JW, JS, JG>,
            JO extends OnModel<J, JC, JO, JW, JS, JG>,
            JW extends WhereModel<J, JC, JO, JW, JS, JG>,
            JS extends SortModel<J, JC, JO, JW, JS, JG>,
            JG extends GroupModel<J, JC, JO, JW, JS, JG>> JoinIntactEngine<M, MC, MO, MW, MS, MG> join(Class<J> joinClass,
                                                                                                       String alias,
                                                                                                       JoinType joinType,
                                                                                                       On<M, MC, MO, MW, MS, MG, J, JC, JO, JW, JS, JG> on) {
        return join(null, joinClass, alias, joinType, on);
    }

    public <J extends Model<J, JC, JO, JW, JS, JG>,
            JC extends ColumnModel<J, JC, JO, JW, JS, JG>,
            JO extends OnModel<J, JC, JO, JW, JS, JG>,
            JW extends WhereModel<J, JC, JO, JW, JS, JG>,
            JS extends SortModel<J, JC, JO, JW, JS, JG>,
            JG extends GroupModel<J, JC, JO, JW, JS, JG>> JoinIntactEngine<M, MC, MO, MW, MS, MG> join(Class<J> joinClass,
                                                                                                       JoinType joinType,
                                                                                                       On<M, MC, MO, MW, MS, MG, J, JC, JO, JW, JS, JG> on) {
        return join(null, joinClass, null, joinType, on);
    }

    public <J extends Model<J, JC, JO, JW, JS, JG>,
            JC extends ColumnModel<J, JC, JO, JW, JS, JG>,
            JO extends OnModel<J, JC, JO, JW, JS, JG>,
            JW extends WhereModel<J, JC, JO, JW, JS, JG>,
            JS extends SortModel<J, JC, JO, JW, JS, JG>,
            JG extends GroupModel<J, JC, JO, JW, JS, JG>> JoinIntactEngine<M, MC, MO, MW, MS, MG> innerJoin(String tableName,
                                                                                                            Class<J> joinClass,
                                                                                                            String alias,
                                                                                                            On<M, MC, MO, MW, MS, MG, J, JC, JO, JW, JS, JG> on) {
        return join(tableName, joinClass, alias, JoinType.INNER, on);
    }

    public <J extends Model<J, JC, JO, JW, JS, JG>,
            JC extends ColumnModel<J, JC, JO, JW, JS, JG>,
            JO extends OnModel<J, JC, JO, JW, JS, JG>,
            JW extends WhereModel<J, JC, JO, JW, JS, JG>,
            JS extends SortModel<J, JC, JO, JW, JS, JG>,
            JG extends GroupModel<J, JC, JO, JW, JS, JG>> JoinIntactEngine<M, MC, MO, MW, MS, MG> innerJoin(String tableName,
                                                                                                            Class<J> joinClass,
                                                                                                            On<M, MC, MO, MW, MS, MG, J, JC, JO, JW, JS, JG> on) {
        return join(tableName, joinClass, null, JoinType.INNER, on);
    }

    public <J extends Model<J, JC, JO, JW, JS, JG>,
            JC extends ColumnModel<J, JC, JO, JW, JS, JG>,
            JO extends OnModel<J, JC, JO, JW, JS, JG>,
            JW extends WhereModel<J, JC, JO, JW, JS, JG>,
            JS extends SortModel<J, JC, JO, JW, JS, JG>,
            JG extends GroupModel<J, JC, JO, JW, JS, JG>> JoinIntactEngine<M, MC, MO, MW, MS, MG> innerJoin(Class<J> joinClass,
                                                                                                            String alias,
                                                                                                            On<M, MC, MO, MW, MS, MG, J, JC, JO, JW, JS, JG> on) {
        return join(null, joinClass, alias, JoinType.INNER, on);
    }

    public <J extends Model<J, JC, JO, JW, JS, JG>,
            JC extends ColumnModel<J, JC, JO, JW, JS, JG>,
            JO extends OnModel<J, JC, JO, JW, JS, JG>,
            JW extends WhereModel<J, JC, JO, JW, JS, JG>,
            JS extends SortModel<J, JC, JO, JW, JS, JG>,
            JG extends GroupModel<J, JC, JO, JW, JS, JG>> JoinIntactEngine<M, MC, MO, MW, MS, MG> innerJoin(Class<J> joinClass,
                                                                                                            On<M, MC, MO, MW, MS, MG, J, JC, JO, JW, JS, JG> on) {
        return join(null, joinClass, null, JoinType.INNER, on);
    }

    public <J extends Model<J, JC, JO, JW, JS, JG>,
            JC extends ColumnModel<J, JC, JO, JW, JS, JG>,
            JO extends OnModel<J, JC, JO, JW, JS, JG>,
            JW extends WhereModel<J, JC, JO, JW, JS, JG>,
            JS extends SortModel<J, JC, JO, JW, JS, JG>,
            JG extends GroupModel<J, JC, JO, JW, JS, JG>> JoinIntactEngine<M, MC, MO, MW, MS, MG> leftJoin(String tableName,
                                                                                                           Class<J> joinClass,
                                                                                                           String alias,
                                                                                                           On<M, MC, MO, MW, MS, MG, J, JC, JO, JW, JS, JG> on) {
        return join(tableName, joinClass, alias, JoinType.LEFT, on);
    }

    public <J extends Model<J, JC, JO, JW, JS, JG>,
            JC extends ColumnModel<J, JC, JO, JW, JS, JG>,
            JO extends OnModel<J, JC, JO, JW, JS, JG>,
            JW extends WhereModel<J, JC, JO, JW, JS, JG>,
            JS extends SortModel<J, JC, JO, JW, JS, JG>,
            JG extends GroupModel<J, JC, JO, JW, JS, JG>> JoinIntactEngine<M, MC, MO, MW, MS, MG> leftJoin(String tableName,
                                                                                                           Class<J> joinClass,
                                                                                                           On<M, MC, MO, MW, MS, MG, J, JC, JO, JW, JS, JG> on) {
        return join(tableName, joinClass, null, JoinType.LEFT, on);
    }

    public <J extends Model<J, JC, JO, JW, JS, JG>,
            JC extends ColumnModel<J, JC, JO, JW, JS, JG>,
            JO extends OnModel<J, JC, JO, JW, JS, JG>,
            JW extends WhereModel<J, JC, JO, JW, JS, JG>,
            JS extends SortModel<J, JC, JO, JW, JS, JG>,
            JG extends GroupModel<J, JC, JO, JW, JS, JG>> JoinIntactEngine<M, MC, MO, MW, MS, MG> leftJoin(Class<J> joinClass,
                                                                                                           String alias,
                                                                                                           On<M, MC, MO, MW, MS, MG, J, JC, JO, JW, JS, JG> on) {
        return join(null, joinClass, alias, JoinType.LEFT, on);
    }

    public <J extends Model<J, JC, JO, JW, JS, JG>,
            JC extends ColumnModel<J, JC, JO, JW, JS, JG>,
            JO extends OnModel<J, JC, JO, JW, JS, JG>,
            JW extends WhereModel<J, JC, JO, JW, JS, JG>,
            JS extends SortModel<J, JC, JO, JW, JS, JG>,
            JG extends GroupModel<J, JC, JO, JW, JS, JG>> JoinIntactEngine<M, MC, MO, MW, MS, MG> leftJoin(Class<J> joinClass,
                                                                                                           On<M, MC, MO, MW, MS, MG, J, JC, JO, JW, JS, JG> on) {
        return join(null, joinClass, null, JoinType.LEFT, on);
    }

    public <J extends Model<J, JC, JO, JW, JS, JG>,
            JC extends ColumnModel<J, JC, JO, JW, JS, JG>,
            JO extends OnModel<J, JC, JO, JW, JS, JG>,
            JW extends WhereModel<J, JC, JO, JW, JS, JG>,
            JS extends SortModel<J, JC, JO, JW, JS, JG>,
            JG extends GroupModel<J, JC, JO, JW, JS, JG>> JoinIntactEngine<M, MC, MO, MW, MS, MG> rightJoin(String tableName,
                                                                                                            Class<J> joinClass,
                                                                                                            String alias,
                                                                                                            On<M, MC, MO, MW, MS, MG, J, JC, JO, JW, JS, JG> on) {
        return join(tableName, joinClass, alias, JoinType.RIGHT, on);
    }

    public <J extends Model<J, JC, JO, JW, JS, JG>,
            JC extends ColumnModel<J, JC, JO, JW, JS, JG>,
            JO extends OnModel<J, JC, JO, JW, JS, JG>,
            JW extends WhereModel<J, JC, JO, JW, JS, JG>,
            JS extends SortModel<J, JC, JO, JW, JS, JG>,
            JG extends GroupModel<J, JC, JO, JW, JS, JG>> JoinIntactEngine<M, MC, MO, MW, MS, MG> rightJoin(String tableName,
                                                                                                            Class<J> joinClass,
                                                                                                            On<M, MC, MO, MW, MS, MG, J, JC, JO, JW, JS, JG> on) {
        return join(tableName, joinClass, null, JoinType.RIGHT, on);
    }

    public <J extends Model<J, JC, JO, JW, JS, JG>,
            JC extends ColumnModel<J, JC, JO, JW, JS, JG>,
            JO extends OnModel<J, JC, JO, JW, JS, JG>,
            JW extends WhereModel<J, JC, JO, JW, JS, JG>,
            JS extends SortModel<J, JC, JO, JW, JS, JG>,
            JG extends GroupModel<J, JC, JO, JW, JS, JG>> JoinIntactEngine<M, MC, MO, MW, MS, MG> rightJoin(Class<J> joinClass,
                                                                                                            String alias,
                                                                                                            On<M, MC, MO, MW, MS, MG, J, JC, JO, JW, JS, JG> on) {
        return join(null, joinClass, alias, JoinType.RIGHT, on);
    }

    public <J extends Model<J, JC, JO, JW, JS, JG>,
            JC extends ColumnModel<J, JC, JO, JW, JS, JG>,
            JO extends OnModel<J, JC, JO, JW, JS, JG>,
            JW extends WhereModel<J, JC, JO, JW, JS, JG>,
            JS extends SortModel<J, JC, JO, JW, JS, JG>,
            JG extends GroupModel<J, JC, JO, JW, JS, JG>> JoinIntactEngine<M, MC, MO, MW, MS, MG> rightJoin(Class<J> joinClass,
                                                                                                            On<M, MC, MO, MW, MS, MG, J, JC, JO, JW, JS, JG> on) {
        return join(null, joinClass, null, JoinType.RIGHT, on);
    }

}
