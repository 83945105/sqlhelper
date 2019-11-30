package pub.avalonframework.sqlhelper.core.engine;

/**
 * @author baichao
 */
public interface JdbcEngine<R> extends
        ColumnEngine<R>,
        JoinEngine<R>,
        OnEngine<R>,
        WhereEngine<R>,
        GroupEngine<R>,
        HavingEngine<R>,
        SortEngine<R>,
        LimitEngine<R> {
}