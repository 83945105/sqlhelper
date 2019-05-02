package pub.avalon.sqlhelper.core.beans;

import pub.avalon.sqlhelper.core.norm.Model;
import pub.avalon.sqlhelper.core.norm.SubQueryComparisonOperator;

/**
 * 条件构建器
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class SubQueryWhereBuilder<M extends Model<M, MC, MO, MW, MS, MG>,
        MC extends ColumnModel<M, MC, MO, MW, MS, MG>,
        MO extends OnModel<M, MC, MO, MW, MS, MG>,
        MW extends WhereModel<M, MC, MO, MW, MS, MG>,
        MS extends SortModel<M, MC, MO, MW, MS, MG>,
        MG extends GroupModel<M, MC, MO, MW, MS, MG>> extends WhereBuilder<M, MC, MO, MW, MS, MG> implements SubQueryComparisonOperator<M, MC, MO, MW, MS, MG> {

    public SubQueryWhereBuilder(MW handleModel) {
        super(handleModel);
    }

    @Override
    public MW equalTo(ColumnModel columnModel) {
/*        this.whereData.setWhereType(WhereType.EQUAL);
        this.whereData.setWhereValueType(WhereValueType.JOIN);
        Map<String, String> columnAliasMap = columnModel.getColumnAliasMap();
        if (columnAliasMap.size() > 1) {
            throw new SqlException("you can not set more than one column for equalTo.");
        }
        WhereData targetWhereData = whereBuilder.whereData;
        this.whereData.setTargetTableName(targetWhereData.getOwnerTableName());
        this.whereData.setTargetTableAlias(targetWhereData.getOwnerTableAlias());
        this.whereData.setTargetColumnName(targetWhereData.getOwnerColumnName());
        this.whereDataList.add(this.whereData);
        return this.handleModel;*/
        return null;
    }

    @Override
    public MW notEqualTo(ColumnModel columnModel) {
        return null;
    }

    @Override
    public MW greaterThan(ColumnModel columnModel) {
        return null;
    }

    @Override
    public MW greaterThanAndEqualTo(ColumnModel columnModel) {
        return null;
    }

    @Override
    public MW lessThan(ColumnModel columnModel) {
        return null;
    }

    @Override
    public MW lessThanAndEqualTo(ColumnModel columnModel) {
        return null;
    }

/*    public MW notEqualTo(WhereBuilder whereBuilder) {
        this.whereData.setWhereType(WhereType.NOT_EQUAL);
        this.whereData.setWhereValueType(WhereValueType.JOIN);
        WhereData targetWhereData = whereBuilder.whereData;
        this.whereData.setTargetTableName(targetWhereData.getOwnerTableName());
        this.whereData.setTargetTableAlias(targetWhereData.getOwnerTableAlias());
        this.whereData.setTargetColumnName(targetWhereData.getOwnerColumnName());
        this.whereDataList.add(this.whereData);
        return this.handleModel;
    }*/

/*    public MW greaterThan(WhereBuilder whereBuilder) {
        this.whereData.setWhereType(WhereType.GREATER);
        this.whereData.setWhereValueType(WhereValueType.JOIN);
        WhereData targetWhereData = whereBuilder.whereData;
        this.whereData.setTargetTableName(targetWhereData.getOwnerTableName());
        this.whereData.setTargetTableAlias(targetWhereData.getOwnerTableAlias());
        this.whereData.setTargetColumnName(targetWhereData.getOwnerColumnName());
        this.whereDataList.add(this.whereData);
        return this.handleModel;
    }*/

/*    public MW greaterThanAndEqualTo(WhereBuilder whereBuilder) {
        this.whereData.setWhereType(WhereType.GREATER_EQUAL);
        this.whereData.setWhereValueType(WhereValueType.JOIN);
        WhereData targetWhereData = whereBuilder.whereData;
        this.whereData.setTargetTableName(targetWhereData.getOwnerTableName());
        this.whereData.setTargetTableAlias(targetWhereData.getOwnerTableAlias());
        this.whereData.setTargetColumnName(targetWhereData.getOwnerColumnName());
        this.whereDataList.add(this.whereData);
        return this.handleModel;
    }*/

/*    public MW lessThan(WhereBuilder whereBuilder) {
        this.whereData.setWhereType(WhereType.LESS);
        this.whereData.setWhereValueType(WhereValueType.JOIN);
        WhereData targetWhereData = whereBuilder.whereData;
        this.whereData.setTargetTableName(targetWhereData.getOwnerTableName());
        this.whereData.setTargetTableAlias(targetWhereData.getOwnerTableAlias());
        this.whereData.setTargetColumnName(targetWhereData.getOwnerColumnName());
        this.whereDataList.add(this.whereData);
        return this.handleModel;
    }*/

/*    public MW lessThanAndEqualTo(WhereBuilder whereBuilder) {
        this.whereData.setWhereType(WhereType.LESS_EQUAL);
        this.whereData.setWhereValueType(WhereValueType.JOIN);
        WhereData targetWhereData = whereBuilder.whereData;
        this.whereData.setTargetTableName(targetWhereData.getOwnerTableName());
        this.whereData.setTargetTableAlias(targetWhereData.getOwnerTableAlias());
        this.whereData.setTargetColumnName(targetWhereData.getOwnerColumnName());
        this.whereDataList.add(this.whereData);
        return this.handleModel;
    }*/

/*    public MW equalTo(MC columnModel) {
        this.whereData.setWhereType(WhereType.EQUAL);
        this.whereData.setWhereValueType(WhereValueType.JOIN);
        Map<String, String> columnAliasMap = columnModel.getColumnAliasMap();
        if (columnAliasMap.size() > 1) {
            throw new SqlException("you can not set more than one column for equalTo.");
        }
        WhereData targetWhereData = whereBuilder.whereData;
        this.whereData.setTargetTableName(targetWhereData.getOwnerTableName());
        this.whereData.setTargetTableAlias(targetWhereData.getOwnerTableAlias());
        this.whereData.setTargetColumnName(targetWhereData.getOwnerColumnName());
        this.whereDataList.add(this.whereData);
        return this.handleModel;
    }*/

}
