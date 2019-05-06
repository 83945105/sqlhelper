package pub.avalon.sqlhelper.core.engine;

import pub.avalon.beans.DataBaseType;
import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.builder.SqlBuilder;
import pub.avalon.sqlhelper.core.data.ColumnDatum;
import pub.avalon.sqlhelper.core.data.MainTableData;
import pub.avalon.sqlhelper.core.data.TableColumnData;
import pub.avalon.sqlhelper.core.norm.Column;
import pub.avalon.sqlhelper.core.norm.Model;
import pub.avalon.sqlhelper.core.sql.Insert;

import java.util.Collection;
import java.util.Set;

/**
 * 列引擎
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public class ColumnEngine<M extends Model<M, MC, MO, MW, MS, MG>,
        MC extends ColumnModel<M, MC, MO, MW, MS, MG>,
        MO extends OnModel<M, MC, MO, MW, MS, MG>,
        MW extends WhereModel<M, MC, MO, MW, MS, MG>,
        MS extends SortModel<M, MC, MO, MW, MS, MG>,
        MG extends GroupModel<M, MC, MO, MW, MS, MG>> extends SqlEngine<M> implements Insert {

    public ColumnEngine(Class<M> mainClass, DataBaseType dataBaseType) {
        super(mainClass, dataBaseType);
    }

    public ColumnEngine(String tableName, Class<M> mainClass, DataBaseType dataBaseType) {
        super(tableName, mainClass, dataBaseType);
    }

    public ColumnEngine(String tableName, Class<M> mainClass, String alias, DataBaseType dataBaseType) {
        super(tableName, mainClass, alias, dataBaseType);
    }

    public ColumnEngine<M, MC, MO, MW, MS, MG> column(Column<M, MC, MO, MW, MS, MG> column) {
        MainTableData<M> mainTableData = this.sqlData.getMainTableData();
        MC mc = mainTableData.getTableModel().getColumnModel();
        mc.setSqlData(this.sqlData);
        mc = column.apply(mc);
        Set<ColumnDatum> columnData = mc.modelDataBuilder.takeoutModelData();
        // 调用了column方法但是没有设置任何列,则使用该模组对应的表所有列
        if (columnData == null || columnData.size() == 0) {
            columnData = mainTableData.buildTableColumnData();
        }
        this.sqlData.addTableColumnData(new TableColumnData(mainTableData, columnData));
        return this;
    }

    @Override
    public SqlBuilder insertArgs(Collection<?> args) {
        return this.sqlBuilderProxy.insertArgs(args);
    }

    @Override
    public SqlBuilder insertJavaBean(Object javaBean) {
        return this.sqlBuilderProxy.insertJavaBean(javaBean);
    }

    @Override
    public SqlBuilder insertJavaBeanSelective(Object javaBean) {
        return this.sqlBuilderProxy.insertJavaBeanSelective(javaBean);
    }

    @Override
    public SqlBuilder batchInsertJavaBeans(Collection<?> javaBeans) {
        return this.sqlBuilderProxy.batchInsertJavaBeans(javaBeans);
    }

}
