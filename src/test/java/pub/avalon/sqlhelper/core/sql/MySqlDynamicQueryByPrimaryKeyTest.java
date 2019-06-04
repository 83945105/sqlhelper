package pub.avalon.sqlhelper.core.sql;

import org.junit.jupiter.api.Test;
import pub.avalon.sqlhelper.AbstractTest;
import pub.avalon.sqlhelper.core.beans.FunctionColumnType;
import pub.avalon.sqlhelper.core.sqlbuilder.SqlBuilder;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalon.sqlhelper.readme.entity.SysUser;

/**
 * MySql动态引擎 - 查询 - 主键查询
 */
public class MySqlDynamicQueryByPrimaryKeyTest extends AbstractTest {

    @Test
    void TestColumn() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUser.Helper.class)
                .column(table -> table)
                .queryByPrimaryKey(arg());
        setSqlBuilder(sqlBuilder, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser where SysUser.`id` = ?");

        sqlBuilder = MySqlDynamicEngine.table(SysUser.Helper.class)
                .column(SysUser.Helper.Column::id)
                .queryByPrimaryKey(arg());
        setSqlBuilder(sqlBuilder, "select SysUser.`id` `id` from `sys_user` SysUser where SysUser.`id` = ?");

        sqlBuilder = MySqlDynamicEngine.table(SysUser.Helper.class)
                .column(table -> table.loginName().userName("AA"))
                .queryByPrimaryKey(arg());
        setSqlBuilder(sqlBuilder, "select SysUser.`login_name` `loginName`,SysUser.`user_name` `AA` from `sys_user` SysUser where SysUser.`id` = ?");
    }

    @Test
    void TestVirtualColumn() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUser.Helper.class)
                .virtualColumn("1", "AA")
                .virtualColumn(1, "BB")
                .virtualColumn(1L, "CC")
                .virtualColumn(null, "DD")
                .queryByPrimaryKey(arg());
        setSqlBuilder(sqlBuilder, "select '1' `AA`,1 `BB`,1 `CC`,null `DD` from `sys_user` SysUser where SysUser.`id` = ?");
    }

    @Test
    void TestColumnAndVirtualColumn() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUser.Helper.class)
                .virtualColumn(1, "AA")
                .column(table -> table)
                .queryByPrimaryKey(arg());
        setSqlBuilder(sqlBuilder, "select 1 `AA`, SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser where SysUser.`id` = ?");
    }

    @Test
    void TestFunctionColumn() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUser.Helper.class)
                .functionColumn(FunctionColumnType.COUNT, SysUser.Helper.Column::id)
                .queryByPrimaryKey(arg());
        setSqlBuilder(sqlBuilder, "select count(SysUser.`id`) `id` from `sys_user` SysUser where SysUser.`id` = ?");
    }

    @Test
    void TestFunctionColumnAndVirtualColumn() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUser.Helper.class)
                .functionColumn(FunctionColumnType.COUNT, SysUser.Helper.Column::id)
                .virtualColumn(1, "AA")
                .queryByPrimaryKey(arg());
        setSqlBuilder(sqlBuilder, "select count(SysUser.`id`) `id`, 1 `AA` from `sys_user` SysUser where SysUser.`id` = ?");
    }

    @Test
    void TestFunctionColumnAndVirtualColumnAndColumn() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUser.Helper.class)
                .functionColumn(FunctionColumnType.COUNT, table -> table.id("idCount"))
                .virtualColumn(1, "AA")
                .column(table -> table)
                .queryByPrimaryKey(arg());
        setSqlBuilder(sqlBuilder, "select count(SysUser.`id`) `idCount`, 1 `AA`, SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser where SysUser.`id` = ?");
    }

}
