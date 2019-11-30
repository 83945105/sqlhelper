package pub.avalonframework.sqlhelper.core.engine.callback;

import pub.avalonframework.sqlhelper.core.helper.*;

/**
 * @author baichao
 */
public interface JdbcCallbackEngine<TO extends OnHelper<TO>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>,
        R> extends
        ColumnCallbackEngine<TC, R>,
        JoinCallbackEngine<TO, R>,
        OnCallbackEngine<TO, R>,
        WhereCallbackEngine<TW, R>,
        GroupCallbackEngine<TG, R>,
        HavingCallbackEngine<TH, R>,
        SortCallbackEngine<TS, R> {

}