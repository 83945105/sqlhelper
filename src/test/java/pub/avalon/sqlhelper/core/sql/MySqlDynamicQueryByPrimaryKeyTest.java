package pub.avalon.sqlhelper.core.sql;

import org.junit.jupiter.api.Test;
import pub.avalon.sqlhelper.AbstractTest;
import pub.avalon.sqlhelper.core.beans.GroupType;
import pub.avalon.sqlhelper.core.sqlbuilder.SqlBuilder;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalon.sqlhelper.readme.entity.SysUserDTO;

/**
 * MySql动态引擎 - 查询 - 主键查询
 */
public class MySqlDynamicQueryByPrimaryKeyTest extends AbstractTest {

    @Test
    void TestColumn() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .column(table -> table)
                .queryByPrimaryKey(arg());
        setSqlBuilder(sqlBuilder, "select SysUserDTO.`id` `id`,SysUserDTO.`user_name` `userName`,SysUserDTO.`login_name` `loginName` from `sys_user` SysUserDTO where SysUserDTO.`id` = ?");

        sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .column(SysUserDTO.Helper.Column::id)
                .queryByPrimaryKey(arg());
        setSqlBuilder(sqlBuilder, "select SysUserDTO.`id` `id` from `sys_user` SysUserDTO where SysUserDTO.`id` = ?");

        sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .column(table -> table.loginName().userName("AA"))
                .queryByPrimaryKey(arg());
        setSqlBuilder(sqlBuilder, "select SysUserDTO.`login_name` `loginName`,SysUserDTO.`user_name` `AA` from `sys_user` SysUserDTO where SysUserDTO.`id` = ?");
    }

    @Test
    void TestVirtualColumn() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .virtualColumn("1", "AA")
                .virtualColumn(1, "BB")
                .virtualColumn(1L, "CC")
                .virtualColumn(null, "DD")
                .queryByPrimaryKey(arg());
        setSqlBuilder(sqlBuilder, "select '1' `AA`,1 `BB`,1 `CC`,null `DD` from `sys_user` SysUserDTO where SysUserDTO.`id` = ?");
    }

    @Test
    void TestColumnAndVirtualColumn() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .virtualColumn(1, "AA")
                .column(table -> table)
                .queryByPrimaryKey(arg());
        setSqlBuilder(sqlBuilder, "select 1 `AA`, SysUserDTO.`id` `id`,SysUserDTO.`user_name` `userName`,SysUserDTO.`login_name` `loginName` from `sys_user` SysUserDTO where SysUserDTO.`id` = ?");
    }

    @Test
    void TestFunctionColumn() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .functionColumn(GroupType.COUNT, SysUserDTO.Helper.Column::id)
                .queryByPrimaryKey(arg());
        setSqlBuilder(sqlBuilder, "select count(SysUserDTO.`id`) `id` from `sys_user` SysUserDTO where SysUserDTO.`id` = ?");
    }

    @Test
    void TestFunctionColumnAndVirtualColumn() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .functionColumn(GroupType.COUNT, SysUserDTO.Helper.Column::id)
                .virtualColumn(1, "AA")
                .queryByPrimaryKey(arg());
        setSqlBuilder(sqlBuilder, "select count(SysUserDTO.`id`) `id`, 1 `AA` from `sys_user` SysUserDTO where SysUserDTO.`id` = ?");
    }

    @Test
    void TestFunctionColumnAndVirtualColumnAndColumn() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .functionColumn(GroupType.COUNT, table -> table.id("idCount"))
                .virtualColumn(1, "AA")
                .column(table -> table)
                .queryByPrimaryKey(arg());
        setSqlBuilder(sqlBuilder, "select count(SysUserDTO.`id`) `idCount`, 1 `AA`, SysUserDTO.`id` `id`,SysUserDTO.`user_name` `userName`,SysUserDTO.`login_name` `loginName` from `sys_user` SysUserDTO where SysUserDTO.`id` = ?");
    }

}
