package pub.avalon.sqlhelper.core.build;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.fusesource.jansi.Ansi;
import pub.avalon.sqlhelper.core.sql.SqlSplicer;

import java.util.*;

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
                    .fg(Ansi.Color.YELLOW)
                    .a("GET PreparedStatementSQL  [" + this.sqlSplicer.getSql() + "]")
                    .reset());
        }
        return this.sqlSplicer.getSql();
    }

    @Override
    public List<Object> getPreparedStatementArgs() {
        if (this.logger.isDebugEnabled()) {
            logger.debug(Ansi.ansi().eraseScreen()
                    .fg(Ansi.Color.RED)
                    .a("GET PreparedStatementArgs " + this.sqlArgs.toString())
                    .reset());
        }
        return this.sqlArgs;
    }

}
