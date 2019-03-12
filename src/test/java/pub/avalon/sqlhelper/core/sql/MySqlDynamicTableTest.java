package pub.avalon.sqlhelper.core.sql;

import org.junit.jupiter.api.Test;
import pub.avalon.sqlhelper.AbstractTest;
import pub.avalon.sqlhelper.core.build.SqlBuilder;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalon.sqlhelper.readme.model.SysUserModel;

/**
 * MySql动态引擎 - 表格 - 操作
 */
public class MySqlDynamicTableTest extends AbstractTest {

    @Test
    void TestCopyTable() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserModel.class)
                .copyTable("copyTable", false);
        setSqlBuilder(sqlBuilder, "create table `copyTable` like `sys_user`");

        sqlBuilder = MySqlDynamicEngine.table(SysUserModel.class)
                .copyTable("copyTable", true);
        setSqlBuilder(sqlBuilder, "create table `copyTable` like `sys_user`; insert into `copyTable` select * from `sys_user`");
    }

    @Test
    void TestDeleteTable() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserModel.class)
                .deleteTable();
        setSqlBuilder(sqlBuilder, "drop table `sys_user`");
    }

    @Test
    void TestRenameTable() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserModel.class)
                .renameTable("newTableName");
        setSqlBuilder(sqlBuilder, "rename table `sys_user` to `newTableName`");
    }

    @Test
    void TestIsTableExist() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserModel.class)
                .isTableExist();
        setSqlBuilder(sqlBuilder, "select table_name from information_schema.TABLES where table_name = 'sys_user' and table_schema = (select database())");
    }

}
