package pub.avalon.sqlhelper.core.engine;

/**
 * @author baichao
 */
public interface JdbcEngine<R> extends
        ColumnEngine<R>,
        JoinEngine<R>,
        WhereEngine<R>,
        GroupEngine<R>,
        HavingEngine<R>,
        SortEngine<R>,
        LimitEngine<R> {

}