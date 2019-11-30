package pub.avalonframework.sqlhelper.core.sql;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pub.avalonframework.sqlhelper.core.beans.GroupType;
import pub.avalonframework.sqlhelper.core.engine.builder.SqlColumn;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.SqlBuilderResult;
import pub.avalonframework.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalonframework.sqlhelper.readme.entity.SysUserHelper;
import pub.avalonframework.sqlhelper.readme.entity.UserRoleHelper;

/**
 * MySql动态引擎 - 列测试
 */
public class MySqlDynamicEngineColumnTest {

    /**
     * 测试列 - 默认列
     */
    @Test
    void Test_column_default() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(table -> table)
                .query();
        Assertions.assertEquals("select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 测试列 - 传统方式
     */
    @Test
    void Test_column() {
        SysUserHelper.Column column = SysUserHelper.column().id().loginName();
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
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
        SysUserHelper.Column column = SysUserHelper.column().id().loginName();
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table("sys_user_custom", SysUserHelper.class)
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
        SysUserHelper.Column column = SysUserHelper.column("A").id().loginName();
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table("sys_user_custom", SysUserHelper.class, "A")
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
        SysUserHelper.Column column = SysUserHelper.column("A").id().loginName();
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class, "A")
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
        SysUserHelper.Column column = SysUserHelper.column().id("idAlias").loginName("loginNameAlias");
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
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
        SysUserHelper.Column column1 = SysUserHelper.column().id().loginName();
        SysUserHelper.Column column2 = SysUserHelper.column().id("idAlias").loginName("loginNameAlias");
        UserRoleHelper.Column column3 = UserRoleHelper.column().roleId().id("userRoleId");
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(column1, column2, column3)
                .innerJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
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
        SysUserHelper.Column column1 = SysUserHelper.column("A").id().loginName();
        SysUserHelper.Column column2 = SysUserHelper.column("A").id("idAlias").loginName("loginNameAlias");
        UserRoleHelper.Column column3 = UserRoleHelper.column("B").roleId().id("userRoleId");
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class, "A")
                .column(column1, column2, column3)
                .innerJoin(UserRoleHelper.class, "B", (on, joinTable, mainTable) -> on
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
        UserRoleHelper.Column column = UserRoleHelper.column();
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(column)
                .innerJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
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
        SysUserHelper.Column column = SysUserHelper.column().id(GroupType.COUNT);
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
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
        SysUserHelper.Column column = SysUserHelper.column().sqlPart("id `sqlPartColumn`");
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
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
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
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
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table("sys_user_custom", SysUserHelper.class)
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
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class, "A")
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
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(table -> table.id().loginName())
                .column(UserRoleHelper.class, table -> table.id("userRoleId").roleId())
                .innerJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
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
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(UserRoleHelper.class, table -> table)
                .innerJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
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
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
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
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
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
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .subQueryColumn("subColumn", parentTable ->
                        MySqlDynamicEngine.table(UserRoleHelper.class)
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
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table("sys_user_custom", SysUserHelper.class)
                .subQueryColumn("subColumn", parentTable ->
                        MySqlDynamicEngine.table("user_role_custom", UserRoleHelper.class)
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
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class, "A")
                .subQueryColumn("subColumn", parentTable ->
                        MySqlDynamicEngine.table(UserRoleHelper.class, "B")
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
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .sqlColumn(new SqlColumn<SysUserHelper.Column>() {
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
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table("sys_user_custom", SysUserHelper.class)
                .sqlColumn(new SqlColumn<SysUserHelper.Column>() {
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
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class, "A")
                .sqlColumn(new SqlColumn<SysUserHelper.Column>("A") {
                }.column(table -> table.id().userName()))
                .query();
        Assertions.assertEquals("select A.`id` `id`,A.`user_name` `userName` from `sys_user` A", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 测试Sql列 - 逻辑条件
     */
    @Test
    void Test_sqlColumn_logicalConditions() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class, "A")
                .sqlColumn(new SqlColumn<SysUserHelper.Column>("A") {{
                    if (true) {
                        column(table -> table.id("id"));
                    }
                    if (false) {
                        column(table -> table.userName("userName"));
                    }
                    if (true) {
                        column(table -> table.loginName("loginName"));
                    }
                }})
                .query();
        Assertions.assertEquals("select A.`id` `id`,A.`login_name` `loginName` from `sys_user` A", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

}
