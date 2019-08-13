package pub.avalon.sqlhelper.core.sql;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pub.avalon.sqlhelper.core.beans.GroupType;
import pub.avalon.sqlhelper.core.engine.builder.SqlColumn;
import pub.avalon.sqlhelper.core.sqlbuilder.beans.SqlBuilderResult;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalon.sqlhelper.readme.entity.SysUserDTO;
import pub.avalon.sqlhelper.readme.entity.UserRoleDTO;

/**
 * MySql动态引擎 - 列测试
 */
public class MySqlDynamicEngineColumnTest {

    /**
     * 测试列 - 默认列
     */
    @Test
    void Test_column_default() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .query();
        Assertions.assertEquals("select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 测试列 - 传统方式
     */
    @Test
    void Test_column() {
        SysUserDTO.Helper.Column column = SysUserDTO.Helper.column().id().loginName();
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .column(column)
                .query();
        Assertions.assertEquals("select SysUser.`id` `id`,SysUser.`login_name` `loginName` from `sys_user` SysUser", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 测试列 - 指定表名
     */
    @Test
    void Test_column_assignTableName() {
        SysUserDTO.Helper.Column column = SysUserDTO.Helper.column().id().loginName();
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table("sys_user_custom", SysUserDTO.Helper.class)
                .column(column)
                .query();
        Assertions.assertEquals("select SysUser.`id` `id`,SysUser.`login_name` `loginName` from `sys_user_custom` SysUser", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 测试列 - 指定表别名
     */
    @Test
    void Test_column_assignTableAlias() {
        SysUserDTO.Helper.Column column = SysUserDTO.Helper.column("A").id().loginName();
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table("sys_user_custom", SysUserDTO.Helper.class, "A")
                .column(column)
                .query();
        Assertions.assertEquals("select A.`id` `id`,A.`login_name` `loginName` from `sys_user_custom` A", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 测试列 - 指定表名 & 指定表别名
     */
    @Test
    void Test_column_assignTableName_assignTableAlias() {
        SysUserDTO.Helper.Column column = SysUserDTO.Helper.column("A").id().loginName();
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class, "A")
                .column(column)
                .query();
        Assertions.assertEquals("select A.`id` `id`,A.`login_name` `loginName` from `sys_user` A", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 测试列 - 指定列别名
     */
    @Test
    void Test_column_assignColumnAlias() {
        SysUserDTO.Helper.Column column = SysUserDTO.Helper.column().id("idAlias").loginName("loginNameAlias");
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .column(column)
                .query();
        Assertions.assertEquals("select SysUser.`id` `idAlias`,SysUser.`login_name` `loginNameAlias` from `sys_user` SysUser", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 测试多列 - 指定列别名
     */
    @Test
    void Test_multiColumn_assignColumnAlias() {
        SysUserDTO.Helper.Column column1 = SysUserDTO.Helper.column().id().loginName();
        SysUserDTO.Helper.Column column2 = SysUserDTO.Helper.column().id("idAlias").loginName("loginNameAlias");
        UserRoleDTO.Helper.Column column3 = UserRoleDTO.Helper.column().roleId().id("userRoleId");
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .column(column1, column2, column3)
                .innerJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.userId().equalTo(mainTable.id())))
                .query();
        Assertions.assertEquals("select SysUser.`id` `id`,SysUser.`login_name` `loginName`,SysUser.`id` `idAlias`,SysUser.`login_name` `loginNameAlias`,UserRole.`role_id` `roleId`,UserRole.`id` `userRoleId` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`user_id` = SysUser.`id`", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 测试多列 - 指定表别名 & 指定列别名
     */
    @Test
    void Test_multiColumn_assignTableAlias_assignColumnAlias() {
        SysUserDTO.Helper.Column column1 = SysUserDTO.Helper.column("A").id().loginName();
        SysUserDTO.Helper.Column column2 = SysUserDTO.Helper.column("A").id("idAlias").loginName("loginNameAlias");
        UserRoleDTO.Helper.Column column3 = UserRoleDTO.Helper.column("B").roleId().id("userRoleId");
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class, "A")
                .column(column1, column2, column3)
                .innerJoin(UserRoleDTO.Helper.class, "B", (on, joinTable, mainTable) -> on
                        .and(joinTable.userId().equalTo(mainTable.id())))
                .query();
        Assertions.assertEquals("select A.`id` `id`,A.`login_name` `loginName`,A.`id` `idAlias`,A.`login_name` `loginNameAlias`,B.`role_id` `roleId`,B.`id` `userRoleId` from `sys_user` A inner join `user_role` B on B.`user_id` = A.`id`", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 测试多列 - 默认列
     */
    @Test
    void Test_multiColumn_default() {
        UserRoleDTO.Helper.Column column = UserRoleDTO.Helper.column();
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .column(column)
                .innerJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.userId().equalTo(mainTable.id())))
                .query();
        Assertions.assertEquals("select UserRole.`id` `id`,UserRole.`user_id` `userId`,UserRole.`role_id` `roleId`,UserRole.`role_name` `roleName`,UserRole.`sort_index` `sortIndex` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`user_id` = SysUser.`id`", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 测试列操作
     */
    @Test
    void Test_columnHandler() {
        SysUserDTO.Helper.Column column = SysUserDTO.Helper.column().id(GroupType.COUNT);
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .column(column)
                .query();
        Assertions.assertEquals("select count(SysUser.`id`) `id` from `sys_user` SysUser", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 测试sql片段列
     */
    @Test
    void Test_sqlPartColumn() {
        SysUserDTO.Helper.Column column = SysUserDTO.Helper.column().sqlPart("id `sqlPartColumn`");
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .column(column)
                .query();
        Assertions.assertEquals("select id `sqlPartColumn` from `sys_user` SysUser", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 测试lambda列
     */
    @Test
    void Test_lambdaColumn() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .column(table -> table.id().loginName())
                .query();
        Assertions.assertEquals("select SysUser.`id` `id`,SysUser.`login_name` `loginName` from `sys_user` SysUser", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 测试lambda列 - 指定表名
     */
    @Test
    void Test_lambdaColumn_assignTableName() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table("sys_user_custom", SysUserDTO.Helper.class)
                .column(table -> table.id().loginName())
                .query();
        Assertions.assertEquals("select SysUser.`id` `id`,SysUser.`login_name` `loginName` from `sys_user_custom` SysUser", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 测试lambda列 - 指定表别名
     */
    @Test
    void Test_lambdaColumn_assignTableAlias() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class, "A")
                .column(table -> table.id().loginName())
                .query();
        Assertions.assertEquals("select A.`id` `id`,A.`login_name` `loginName` from `sys_user` A", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 测试lambda多列 - 指定列别名
     */
    @Test
    void Test_lambdaMultiColumn_assignColumnAlias() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .column(table -> table.id().loginName())
                .column(UserRoleDTO.Helper.class, table -> table.id("userRoleId").roleId())
                .innerJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.userId().equalTo(mainTable.id())))
                .query();
        Assertions.assertEquals("select SysUser.`id` `id`,SysUser.`login_name` `loginName`,UserRole.`id` `userRoleId`,UserRole.`role_id` `roleId` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`user_id` = SysUser.`id`", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 测试lambda多列 - 默认列
     */
    @Test
    void Test_lambdaMultiColumn_default() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .column(UserRoleDTO.Helper.class, table -> table)
                .innerJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.userId().equalTo(mainTable.id())))
                .query();
        Assertions.assertEquals("select UserRole.`id` `id`,UserRole.`user_id` `userId`,UserRole.`role_id` `roleId`,UserRole.`role_name` `roleName`,UserRole.`sort_index` `sortIndex` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`user_id` = SysUser.`id`", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 测试虚拟列
     */
    @Test
    void Test_virtualColumn() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .virtualColumn(null, "null")
                .virtualColumn("0", null)
                .virtualColumn(1, "1")
                .virtualColumn(2L, "2")
                .virtualColumn(3.0, "3.0")
                .virtualColumn("4", "4")
                .query();
        Assertions.assertEquals("select null `null`,1 `1`,2 `2`,3.0 `3.0`,'4' `4` from `sys_user` SysUser", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 测试聚合列 - 指定列别名
     */
    @Test
    void Test_groupColumn_assignColumnAlias() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .groupColumn(GroupType.COUNT, table -> table.id("countId"))
                .groupColumn(GroupType.MIN, table -> table.id("minId"))
                .groupColumn(GroupType.MAX, table -> table.id("maxId"))
                .groupColumn(GroupType.SUM, table -> table.id("sumId"))
                .groupColumn(GroupType.AVG, table -> table.id("avgId"))
                .groupColumn(GroupType.STDDEV, table -> table.id("stddevId"))
                .groupColumn(GroupType.VARIANCE, table -> table.id("varianceId"))
                .query();
        Assertions.assertEquals("select count(SysUser.`id`) `countId`,min(SysUser.`id`) `minId`,max(SysUser.`id`) `maxId`,sum(SysUser.`id`) `sumId`,avg(SysUser.`id`) `avgId`,stddev(SysUser.`id`) `stddevId`,variance(SysUser.`id`) `varianceId` from `sys_user` SysUser", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 测试子查询列
     */
    @Test
    void Test_subQueryColumn() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .subQueryColumn("subColumn", parentTable ->
                        MySqlDynamicEngine.table(UserRoleDTO.Helper.class)
                                .column(table -> table.id("userRoleId"))
                                .where((condition, mainTable) -> condition
                                        .and(mainTable.userId().equalTo(parentTable.id())))
                                .query()
                )
                .query();
        Assertions.assertEquals("select (select UserRole.`id` `userRoleId` from `user_role` UserRole where UserRole.`user_id` = SysUser.`id`) `subColumn` from `sys_user` SysUser", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 测试子查询列 - 指定表名
     */
    @Test
    void Test_subQueryColumn_assignTableName() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table("sys_user_custom", SysUserDTO.Helper.class)
                .subQueryColumn("subColumn", parentTable ->
                        MySqlDynamicEngine.table("user_role_custom", UserRoleDTO.Helper.class)
                                .column(table -> table.id("userRoleId"))
                                .where((condition, mainTable) -> condition
                                        .and(mainTable.id().equalTo("1")))
                                .query()
                )
                .query();
        Assertions.assertEquals("select (select UserRole.`id` `userRoleId` from `user_role_custom` UserRole where UserRole.`id` = ?) `subColumn` from `sys_user_custom` SysUser", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{"1"}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 测试子查询列 - 指定表别名
     */
    @Test
    void Test_subQueryColumn_assignTableAlias() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class, "A")
                .subQueryColumn("subColumn", parentTable ->
                        MySqlDynamicEngine.table(UserRoleDTO.Helper.class, "B")
                                .column(table -> table.id("userRoleId"))
                                .where((condition, mainTable) -> condition
                                        .and(mainTable.userId().equalTo(parentTable.id())))
                                .query()
                )
                .query();
        Assertions.assertEquals("select (select B.`id` `userRoleId` from `user_role` B where B.`user_id` = A.`id`) `subColumn` from `sys_user` A", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 测试Sql列
     */
    @Test
    void Test_sqlColumn() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .sqlColumn(new SqlColumn<SysUserDTO.Helper.Column>() {
                }.column(table -> table.id().userName()))
                .query();
        Assertions.assertEquals("select SysUser.`id` `id`,SysUser.`user_name` `userName` from `sys_user` SysUser", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 测试Sql列 - 指定表名
     */
    @Test
    void Test_sqlColumn_assignTableName() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table("sys_user_custom", SysUserDTO.Helper.class)
                .sqlColumn(new SqlColumn<SysUserDTO.Helper.Column>() {
                }.column(table -> table.id().userName()))
                .query();
        Assertions.assertEquals("select SysUser.`id` `id`,SysUser.`user_name` `userName` from `sys_user_custom` SysUser", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 测试Sql列 - 指定表别名
     */
    @Test
    void Test_sqlColumn_assignTableAlias() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class, "A")
                .sqlColumn(new SqlColumn<SysUserDTO.Helper.Column>("A") {
                }.column(table -> table.id().userName()))
                .query();
        Assertions.assertEquals("select A.`id` `id`,A.`user_name` `userName` from `sys_user` A", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

}
