package pub.avalon.sqlhelper.spring.core;

import org.springframework.jdbc.core.*;
import org.springframework.lang.Nullable;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

/**
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class CollectionArgumentPreparedStatementSetter implements PreparedStatementSetter, ParameterDisposer {

    @Nullable
    private Collection<?> args;

    public CollectionArgumentPreparedStatementSetter(@Nullable Collection<?> args) {
        this.args = args;
    }

    @Override
    public void setValues(@Nullable PreparedStatement ps) throws SQLException {
        if (this.args != null) {
            int i = 1;
            for (Object arg : this.args) {
                doSetValue(ps, i++, arg);
            }
        }
    }

    private void doSetValue(PreparedStatement ps, int parameterPosition, Object argValue) throws SQLException {
        if (argValue instanceof SqlParameterValue) {
            SqlParameterValue paramValue = (SqlParameterValue) argValue;
            StatementCreatorUtils.setParameterValue(ps, parameterPosition, paramValue, paramValue.getValue());
        } else {
            StatementCreatorUtils.setParameterValue(ps, parameterPosition, SqlTypeValue.TYPE_UNKNOWN, argValue);
        }
    }

    @Override
    public void cleanupParameters() {
        //不清空
    }
}
