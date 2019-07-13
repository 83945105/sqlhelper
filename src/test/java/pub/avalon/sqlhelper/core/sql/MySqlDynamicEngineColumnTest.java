package pub.avalon.sqlhelper.core.sql;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pub.avalon.sqlhelper.core.beans.ComparisonRule;
import pub.avalon.sqlhelper.core.beans.JoinType;
import pub.avalon.sqlhelper.core.sqlbuilder.SqlBuilder;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalon.sqlhelper.readme.entity.RoleResourceDTO;
import pub.avalon.sqlhelper.readme.entity.SysUserDTO;
import pub.avalon.sqlhelper.readme.entity.UserRoleDTO;

import java.util.Arrays;

/**
 * MySql动态引擎 - 列测试
 */
public class MySqlDynamicEngineColumnTest {

    /**
     * 测试列 - 传统方式
     */
//    @Test
    void Test_column() {
        SysUserDTO.Helper.Column column = SysUserDTO.Helper.column().id().loginName();
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class, "A")
                .column(column)
                .query();
        Assertions.assertEquals("delete from `sys_user` where `id` = ?", sqlBuilder.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{"1"}, sqlBuilder.getPreparedStatementArgs().toArray());
    }

    /**
     * 测试列 - lambda方式
     */
    @Test
    void Test_lambdaColumn() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .column(table -> table.id().loginName())
                .query();
        Assertions.assertEquals("select SysUser.`id` `id`,SysUser.`login_name` `loginName` from `sys_user` SysUser", sqlBuilder.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilder.getPreparedStatementArgs().toArray());
    }

    /**
     * 测试列 - lambda方式 & 指定表名
     */
    @Test
    void Test_lambdaColumn_assignTableName() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table("sys_user_custom", SysUserDTO.Helper.class)
                .column(table -> table.id().loginName())
                .query();
        Assertions.assertEquals("select SysUser.`id` `id`,SysUser.`login_name` `loginName` from `sys_user_custom` SysUser", sqlBuilder.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilder.getPreparedStatementArgs().toArray());
    }

    /**
     * 测试列 - lambda方式 & 指定表别名
     */
    @Test
    void Test_lambdaColumn_assignTableAlias() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class, "A")
                .column(table -> table.id().loginName())
                .query();
        Assertions.assertEquals("select A.`id` `id`,A.`login_name` `loginName` from `sys_user` A", sqlBuilder.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilder.getPreparedStatementArgs().toArray());
    }




}
