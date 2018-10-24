package pub.avalon.sqlhelper.core.sql;

import com.shiro.JurRoleModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pub.avalon.sqlhelper.core.build.SqlBuilder;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;

import java.util.UUID;

/**
 * Created by 白超 on 2018/9/11.
 */
public class MySqlDynamicTableTest {

    @Test
    void TestCopyTable() {
        String tableName = "jur_role_" + UUID.randomUUID().toString().replaceAll("-", "");

        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(JurRoleModel.class)
                .copyTable(tableName, false);

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "create table `" + tableName + "` like `jur_role`");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 0);

        tableName = "jur_role_" + UUID.randomUUID().toString().replaceAll("-", "");

        sqlBuilder = MySqlDynamicEngine.table(JurRoleModel.class)
                .copyTable(tableName, true);

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "create table `" + tableName + "` like `jur_role`; insert into `" + tableName + "` select * from `jur_role`");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 0);
    }

    @Test
    void TestDeleteTable() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table("jur_role_delete", JurRoleModel.class)
                .deleteTable();

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "drop table jur_role_delete");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 0);
    }

    @Test
    void TestRenameTable() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table("jur_role", JurRoleModel.class)
                .renameTable("jur_role_new");

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "rename table jur_role to jur_role_new");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 0);
    }

    @Test
    void TestIsTableExist() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table("jur_role", JurRoleModel.class)
                .isTableExist();

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "select table_name from information_schema.TABLES where table_name = 'jur_role' and table_schema = (select database())");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 0);
    }

}
