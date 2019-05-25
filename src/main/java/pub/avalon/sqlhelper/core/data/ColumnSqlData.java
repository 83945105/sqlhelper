package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.sqlbuilder.SqlBuilder;

import java.util.Map;
import java.util.Set;

/**
 * @author 白超
 * @date 2019/5/25
 */
public interface ColumnSqlData {

    Set<ColumnDatum> getMainTableColumnData();

    Map<String, SqlBuilder> getSubQueryDataMap();

    Set<VirtualFieldDatum> getVirtualFieldDataSet();


}
