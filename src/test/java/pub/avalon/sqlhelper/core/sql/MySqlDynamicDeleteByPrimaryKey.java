package pub.avalon.sqlhelper.core.sql;

import com.shiro.JurRoleModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pub.avalon.sqlhelper.core.build.SqlBuilder;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;

import java.util.Arrays;

/**
 * Created by 白超 on 2018/8/27.
 */
public class MySqlDynamicDeleteByPrimaryKey {

    @Test
    void TestDeleteByPrimaryKey() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.delete(JurRoleModel.class)
                .deleteByPrimaryKey("1");

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "delete from jur_role where id = ?");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 1);
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().get(0), "1");
    }

    @Test
    void TestBatchDeleteByPrimaryKeys() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.delete(JurRoleModel.class)
                .batchDeleteByPrimaryKeys(Arrays.asList("1", "2"));

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "delete from jur_role where id in (?,?)");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 2);
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().get(1), "2");
    }
}
