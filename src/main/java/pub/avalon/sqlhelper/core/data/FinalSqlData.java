package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.modelbuilder.TableModel;

/**
 * @author 白超
 * @date 2018/8/20
 */
public final class FinalSqlData<T extends TableModel> extends AbstractSqlData<T> {

    public FinalSqlData(MainTableData<T> mainTableData) {
        super(mainTableData);
    }

}
