package pub.avalon.sqlhelper.core.sql;

import org.junit.jupiter.api.Test;
import pub.avalon.sqlhelper.AbstractTest;
import pub.avalon.sqlhelper.core.sqlbuilder.SqlBuilder;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalon.sqlhelper.readme.entity.SysUserDTO;

/**
 * MySql动态引擎 - 表格 - 操作
 */
public class MySqlDynamicTableTest extends AbstractTest {

    @Test
    void TestCopyTable01() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .copyTable("copyTable", false);
        setSqlBuilder(sqlBuilder, "create table `copyTable` like `sys_user`");
    }

    @Test
    void TestCopyTable02() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .copyTable("copyTable", true);
        setSqlBuilder(sqlBuilder, "create table `copyTable` like `sys_user`; insert into `copyTable` select * from `sys_user`");
    }

    @Test
    void TestDeleteTable01() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .deleteTable();
        setSqlBuilder(sqlBuilder, "drop table `sys_user`");
    }

    @Test
    void TestDeleteTable02() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .column(table -> table.id().userName())
                .deleteTable();
        setSqlBuilder(sqlBuilder, "drop table `sys_user`");
    }

    @Test
    void TestRenameTable01() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .renameTable("newTableName");
        setSqlBuilder(sqlBuilder, "rename table `sys_user` to `newTableName`");
    }

    @Test
    void TestIsTableExist01() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .isTableExist();
        setSqlBuilder(sqlBuilder, "select table_name from information_schema.TABLES where table_name = 'sys_user' and table_schema = (select database())");
    }

}
