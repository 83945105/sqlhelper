package pub.avalon.sqlhelper.core.engine;

/**
 * jdbc引擎
 *
 * @author 白超
 * @date 2019/7/31
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
