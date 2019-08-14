package pub.avalon.sqlhelper.core.engine.callback;

import pub.avalon.sqlhelper.core.helper.*;

/**
 * jdbc回调引擎
 *
 * @author baichao
 * @date 2019/6/26
 */
public interface JdbcCallbackEngine<TJ extends JoinHelper<TJ>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>,
        R> extends
        ColumnCallbackEngine<TC, R>,
        JoinCallbackEngine<TJ, R>,
        WhereCallbackEngine<TW, R>,
        GroupCallbackEngine<TG, R>,
        HavingCallbackEngine<TH, R>,
        SortCallbackEngine<TS, R> {

}
