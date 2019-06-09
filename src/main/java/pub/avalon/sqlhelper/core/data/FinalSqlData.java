package pub.avalon.sqlhelper.core.data;

import pub.avalon.sqlhelper.core.helper.TableHelper;

/**
 * @author 白超
 * @date 2018/8/20
 */
public final class FinalSqlData<T extends TableHelper> extends AbstractSqlData<T> {

    public FinalSqlData(MainTableData<T> mainTableData) {
        super(mainTableData);
    }

}
