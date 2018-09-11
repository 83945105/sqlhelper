package pub.avalon.sqlhelper.core.sql;

import com.shiro.JurRoleModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pub.avalon.sqlhelper.core.build.SqlBuilder;
import pub.avalon.sqlhelper.factory.SqlServerDynamicEngine;

import java.util.UUID;

/**
 * Created by 白超 on 2018/9/11.
 */
public class SqlServerDynamicTableTest {

    @Test
    void TestCopyTable() {
        String tableName = "jur_role_" + UUID.randomUUID().toString().replaceAll("-", "");

        SqlBuilder sqlBuilder = SqlServerDynamicEngine.table(JurRoleModel.class)
                .copyTable(tableName, false);

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "select * into " + tableName + " from jur_role where 1 = 2");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 0);

        tableName = "jur_role_" + UUID.randomUUID().toString().replaceAll("-", "");

        sqlBuilder = SqlServerDynamicEngine.table(JurRoleModel.class)
                .copyTable(tableName, true);

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "select * into " + tableName + " from jur_role");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 0);
    }

    @Test
    void TestDeleteTable() {
        SqlBuilder sqlBuilder = SqlServerDynamicEngine.table("jur_role_delete", JurRoleModel.class)
                .deleteTable();

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "drop table jur_role_delete");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 0);
    }

    @Test
    void TestRenameTable() {
        SqlBuilder sqlBuilder = SqlServerDynamicEngine.table("jur_role", JurRoleModel.class)
                .renameTable("jur_role_new");

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "exec sp_rename 'jur_role', 'jur_role_new'");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 0);
    }

    @Test
    void TestIsTableExist() {
        SqlBuilder sqlBuilder = SqlServerDynamicEngine.table("jur_role", JurRoleModel.class)
                .isTableExist();

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "select [name] from sysobjects where type='u' and [name] = 'jur_role'");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 0);
    }

}
