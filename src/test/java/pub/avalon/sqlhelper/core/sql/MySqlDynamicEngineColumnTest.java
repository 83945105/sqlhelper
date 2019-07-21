package pub.avalon.sqlhelper.core.sql;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pub.avalon.sqlhelper.core.beans.GroupType;
import pub.avalon.sqlhelper.core.beans.Keyword;
import pub.avalon.sqlhelper.core.engine.SqlColumn;
import pub.avalon.sqlhelper.core.sqlbuilder.SqlBuilder;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalon.sqlhelper.readme.entity.SysUserDTO;
import pub.avalon.sqlhelper.readme.entity.UserRoleDTO;
import pub.avalon.sqlhelper.readme.model.SysUserColumn;

/**
 * MySql动态引擎 - 列测试
 */
public class MySqlDynamicEngineColumnTest {

    /**
     * 测试列 - 默认
     */
    @Test
    void Test_column_default() {
        SysUserDTO.Helper.Column column = SysUserDTO.Helper.column().id().loginName();
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .query();
        Assertions.assertEquals("select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser", sqlBuilder.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilder.getPreparedStatementArgs().toArray());
    }

    /**
     * 测试列 - 传统方式
     */
    @Test
    void Test_column() {
        SysUserDTO.Helper.Column column = SysUserDTO.Helper.column().id().loginName();
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .column(column)
                .query();
        Assertions.assertEquals("select SysUser.`id` `id`,SysUser.`login_name` `loginName` from `sys_user` SysUser", sqlBuilder.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilder.getPreparedStatementArgs().toArray());
    }

    /**
     * 测试列 - 指定表名
     */
    @Test
    void Test_column_assignTableName() {
        SysUserDTO.Helper.Column column = SysUserDTO.Helper.column().id().loginName();
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table("sys_user_custom", SysUserDTO.Helper.class)
                .column(column)
                .query();
        Assertions.assertEquals("select SysUser.`id` `id`,SysUser.`login_name` `loginName` from `sys_user_custom` SysUser", sqlBuilder.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilder.getPreparedStatementArgs().toArray());
    }

    /**
     * 测试列 - 指定表别名
     */
    @Test
    void Test_column_assignTableAlias() {
        SysUserDTO.Helper.Column column = SysUserDTO.Helper.column("A").id().loginName();
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table("sys_user_custom", SysUserDTO.Helper.class, "A")
                .column(column)
                .query();
        Assertions.assertEquals("select A.`id` `id`,A.`login_name` `loginName` from `sys_user_custom` A", sqlBuilder.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilder.getPreparedStatementArgs().toArray());
    }

    /**
     * 测试列 - 指定表名 & 指定表别名
     */
    @Test
    void Test_column_assignTableName_assignTableAlias() {
        SysUserDTO.Helper.Column column = SysUserDTO.Helper.column("A").id().loginName();
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class, "A")
                .column(column)
                .query();
        Assertions.assertEquals("select A.`id` `id`,A.`login_name` `loginName` from `sys_user` A", sqlBuilder.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilder.getPreparedStatementArgs().toArray());
    }

    /**
     * 测试列 - 指定列别名
     */
    @Test
    void Test_column_assignColumnAlias() {
        SysUserDTO.Helper.Column column = SysUserDTO.Helper.column().id("idAlias").loginName("loginNameAlias");
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .column(column)
                .query();
        Assertions.assertEquals("select SysUser.`id` `idAlias`,SysUser.`login_name` `loginNameAlias` from `sys_user` SysUser", sqlBuilder.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilder.getPreparedStatementArgs().toArray());
    }

    /**
     * 测试多列 - 指定列别名
     */
    @Test
    void Test_multiColumn_assignColumnAlias() {
        SysUserDTO.Helper.Column column1 = SysUserDTO.Helper.column().id().loginName();
        SysUserDTO.Helper.Column column2 = SysUserDTO.Helper.column().id("idAlias").loginName("loginNameAlias");
        UserRoleDTO.Helper.Column column3 = UserRoleDTO.Helper.column().roleId().id("userRoleId");
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .column(column1, column2, column3)
                .innerJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.userId().equalTo(mainTable.id())))
                .query();
        Assertions.assertEquals("select SysUser.`id` `id`,SysUser.`login_name` `loginName`,SysUser.`id` `idAlias`,SysUser.`login_name` `loginNameAlias`,UserRole.`role_id` `roleId`,UserRole.`id` `userRoleId` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`user_id` = SysUser.`id`", sqlBuilder.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilder.getPreparedStatementArgs().toArray());
    }

    /**
     * 测试lambda列
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
     * 测试lambda列 - 指定表名
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
     * 测试lambda列 - 指定表别名
     */
    @Test
    void Test_lambdaColumn_assignTableAlias() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class, "A")
                .column(table -> table.id().loginName())
                .query();
        Assertions.assertEquals("select A.`id` `id`,A.`login_name` `loginName` from `sys_user` A", sqlBuilder.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilder.getPreparedStatementArgs().toArray());
    }

    /**
     * 测试lambda多列 - 指定列别名
     */
    @Test
    void Test_lambdaMultiColumn_assignColumnAlias() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .column(table -> table.id().loginName())
                .column(UserRoleDTO.Helper.class, table -> table.id("userRoleId").roleId())
                .innerJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.userId().equalTo(mainTable.id())))
                .query();
        Assertions.assertEquals("select SysUser.`id` `id`,SysUser.`login_name` `loginName`,UserRole.`id` `userRoleId`,UserRole.`role_id` `roleId` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`user_id` = SysUser.`id`", sqlBuilder.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilder.getPreparedStatementArgs().toArray());
    }

    /**
     * 测试虚拟列
     */
    @Test
    void Test_virtualColumn() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .virtualColumn(null, "null")
                .virtualColumn("0", null)
                .virtualColumn(1, "1")
                .virtualColumn(2L, "2")
                .virtualColumn(3.0, "3.0")
                .virtualColumn("4", "4")
                .query();
        Assertions.assertEquals("select null `null`,1 `1`,2 `2`,3.0 `3.0`,'4' `4` from `sys_user` SysUser", sqlBuilder.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilder.getPreparedStatementArgs().toArray());
    }

    /**
     * 测试聚合列 - 指定列别名
     */
    @Test
    void Test_groupColumn_assignColumnAlias() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .groupColumn(GroupType.COUNT, table -> table.id("countId"))
                .groupColumn(GroupType.MIN, table -> table.id("minId"))
                .groupColumn(GroupType.MAX, table -> table.id("maxId"))
                .groupColumn(GroupType.SUM, table -> table.id("sumId"))
                .groupColumn(GroupType.AVG, table -> table.id("avgId"))
                .groupColumn(GroupType.STDDEV, table -> table.id("stddevId"))
                .groupColumn(GroupType.VARIANCE, table -> table.id("varianceId"))
                .query();
        Assertions.assertEquals("select count(SysUser.`id`) `countId`,min(SysUser.`id`) `minId`,max(SysUser.`id`) `maxId`,sum(SysUser.`id`) `sumId`,avg(SysUser.`id`) `avgId`,stddev(SysUser.`id`) `stddevId`,variance(SysUser.`id`) `varianceId` from `sys_user` SysUser", sqlBuilder.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilder.getPreparedStatementArgs().toArray());
    }

    /**
     * 测试Sql列
     */
    @Test
    void Test_sqlColumn() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .sqlColumn(new SqlColumn<SysUserDTO.Helper.Column>() {
                }.column(table -> table.id().userName()))
                .query();
        Assertions.assertEquals("select SysUser.`id` `id`,SysUser.`user_name` `userName` from `sys_user` SysUser", sqlBuilder.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilder.getPreparedStatementArgs().toArray());
    }


    @Test
    void Test() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)

                .sqlColumn(new SysUserColumn() {{

                    column(table -> table.id().loginName());

                }})

                .sqlColumn(new SysUserColumn())

                .sqlColumn(new SysUserColumn() {
                })

                .sqlColumn(new SysUserDTO.Helper.SqlColumn())

                .sqlColumn(new SysUserDTO.Helper.SqlColumn() {
                })

                .sqlColumn(new SysUserDTO.Helper.SqlColumn() {{
                }})

                .sqlColumn(new SqlColumn<SysUserDTO.Helper.Column>() {
                })

                .sqlColumn(new SqlColumn<SysUserDTO.Helper.Column>() {{
                }})

                .query();
        Assertions.assertEquals("select SysUser.`id` `id`,SysUser.`login_name` `loginName` from `sys_user` SysUser", sqlBuilder.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilder.getPreparedStatementArgs().toArray());

    }

}
