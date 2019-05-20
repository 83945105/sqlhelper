package pub.avalon.sqlhelper.core.builder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.fusesource.jansi.Ansi;
import pub.avalon.sqlhelper.core.sql.SqlSplicer;
import pub.avalon.sqlhelper.core.sqlbuilder.SqlBuilder;

import java.util.List;

/**
 * @author 白超
 * @date 2018/8/20
 */
public abstract class AbstractSqlBuilder implements SqlBuilder {

    private final Log logger = LogFactory.getLog(getClass());

    protected SqlSplicer sqlSplicer = new SqlSplicer(128);

    protected List<Object> sqlArgs;

    @Override
    public String getPreparedStatementSql() {
        if (this.logger.isDebugEnabled()) {
            logger.debug(Ansi.ansi().eraseScreen()
                    .fg(Ansi.Color.CYAN)
                    .a("sqlhelper ")
                    .fg(Ansi.Color.YELLOW)
                    .a("PreparedStatementSQL  [" + this.sqlSplicer.getSql() + "]")
                    .reset());
        }
        return this.sqlSplicer.getSql();
    }

    @Override
    public List<Object> getPreparedStatementArgs() {
        if (this.logger.isDebugEnabled()) {
            logger.debug(Ansi.ansi().eraseScreen()
                    .fg(Ansi.Color.CYAN)
                    .a("sqlhelper ")
                    .fg(Ansi.Color.RED)
                    .a("PreparedStatementArgs " + this.sqlArgs.toString())
                    .reset());
        }
        return this.sqlArgs;
    }

}
