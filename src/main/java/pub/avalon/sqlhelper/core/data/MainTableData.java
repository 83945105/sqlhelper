package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.modelbuilder.TableModel;

/**
 * 主表数据
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class MainTableData<T extends TableModel> extends AbstractTableData<T> {

    public MainTableData(Class<T> tableClass) {
        super(tableClass);
    }

}
