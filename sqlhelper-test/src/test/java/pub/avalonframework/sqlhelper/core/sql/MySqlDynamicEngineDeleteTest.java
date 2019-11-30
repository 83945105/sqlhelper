package pub.avalonframework.sqlhelper.core.sql;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pub.avalonframework.sqlhelper.core.beans.ComparisonRule;
import pub.avalonframework.sqlhelper.core.beans.JoinType;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.SqlBuilderResult;
import pub.avalonframework.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalonframework.sqlhelper.readme.entity.RoleResourceHelper;
import pub.avalonframework.sqlhelper.readme.entity.SysUserHelper;
import pub.avalonframework.sqlhelper.readme.entity.UserRoleHelper;

import java.util.Arrays;

/**
 * MySql动态引擎 - 删除
 */
public class MySqlDynamicEngineDeleteTest {

    /**
     * 主键删除
     */
    @Test
    void Test_deleteByPrimaryKey() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .deleteByPrimaryKey("1");
        Assertions.assertEquals("delete from `sys_user` where `id` = ?", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{"1"}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 主键删除 - 指定表名
     */
    @Test
    void Test_deleteByPrimaryKey_assignTableName() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table("sys_user_custom", SysUserHelper.class)
                .deleteByPrimaryKey("1");
        Assertions.assertEquals("delete from `sys_user_custom` where `id` = ?", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{"1"}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 主键批量删除
     */
    @Test
    void Test_batchDeleteByPrimaryKeys() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .batchDeleteByPrimaryKeys("1", "2");
        Assertions.assertEquals("delete from `sys_user` where `id` in (?,?)", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{"1", "2"}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 主键批量删除 - 指定表名
     */
    @Test
    void Test_batchDeleteByPrimaryKeys_assignTableName() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table("sys_user_custom", SysUserHelper.class)
                .batchDeleteByPrimaryKeys("1", "2");
        Assertions.assertEquals("delete from `sys_user_custom` where `id` in (?,?)", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{"1", "2"}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 条件删除
     */
    @Test
    void Test_delete() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .delete();
        Assertions.assertEquals("delete SysUser from `sys_user` SysUser", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 条件删除 - 指定表名
     */
    @Test
    void Test_delete_assignTableName() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table("sys_user_custom", SysUserHelper.class)
                .delete();
        Assertions.assertEquals("delete SysUser from `sys_user_custom` SysUser", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 条件删除 - 所有Where条件
     */
    @Test
    void Test_delete_allWhere() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable

                                .id().isNull()

                                .id().isNotNull()

                                .id().equalTo("A-1")
                                .id().equalTo("A-2", ComparisonRule.NULL_SKIP)

                                .id().notEqualTo("B-1")
                                .id().notEqualTo("B-2", ComparisonRule.NULL_SKIP)

                                .id().greaterThan("C-1")
                                .id().greaterThan("C-2", ComparisonRule.NULL_SKIP)

                                .id().greaterThanAndEqualTo("D-1")
                                .id().greaterThanAndEqualTo("D-2", ComparisonRule.NULL_SKIP)

                                .id().lessThan("E-1")
                                .id().lessThan("E-2", ComparisonRule.NULL_SKIP)

                                .id().lessThanAndEqualTo("F-1")
                                .id().lessThanAndEqualTo("F-2", ComparisonRule.NULL_SKIP)

                                .id().between("G-1", "G-2")
                                .id().between("G-3", "G-4", ComparisonRule.NULL_SKIP)

                                .id().like("H-1")
                                .id().like("H-2", ComparisonRule.NULL_SKIP)

                                .id().in(new Object[]{"I-1", "I-2"}, ComparisonRule.NULL_SKIP)

                                .id().in(ComparisonRule.NULL_SKIP)
                                .id().in(ComparisonRule.NULL_SKIP, "J-1")
                                .id().in(ComparisonRule.NULL_SKIP, new Object[]{"J-2", "J-3"})
                                .id().in("J-4")
                                .id().in(new Object[]{"J-5", "J-6"})
                                .id().in("J-7", "J-8")

                                .id().in(Arrays.asList("K-1", "K-2"))
                                .id().in(Arrays.asList("K-3", "K-4"), ComparisonRule.NULL_SKIP)

                                .id().notIn(new Object[]{"L-1", "L-2"}, ComparisonRule.NULL_SKIP)

                                .id().notIn(ComparisonRule.NULL_SKIP)
                                .id().notIn(ComparisonRule.NULL_SKIP, "M-1")
                                .id().notIn(ComparisonRule.NULL_SKIP, new Object[]{"M-2", "M-3"})
                                .id().notIn("M-4")
                                .id().notIn(new Object[]{"M-5", "M-6"})
                                .id().notIn("M-7", "M-8")

                                .id().notIn(Arrays.asList("N-1", "N-2"))
                                .id().notIn(Arrays.asList("N-3", "N-4"), ComparisonRule.NULL_SKIP)
                        ))
                .delete();
        Assertions.assertEquals("delete SysUser from `sys_user` SysUser where SysUser.`id` is null and SysUser.`id` is not null and SysUser.`id` = ? and SysUser.`id` = ? and SysUser.`id` != ? and SysUser.`id` != ? and SysUser.`id` > ? and SysUser.`id` > ? and SysUser.`id` >= ? and SysUser.`id` >= ? and SysUser.`id` < ? and SysUser.`id` < ? and SysUser.`id` <= ? and SysUser.`id` <= ? and SysUser.`id` between ? and ? and SysUser.`id` between ? and ? and SysUser.`id` like ? and SysUser.`id` like ? and SysUser.`id` in (?,?) and SysUser.`id` in (?) and SysUser.`id` in (?,?) and SysUser.`id` in (?) and SysUser.`id` in (?,?) and SysUser.`id` in (?,?) and SysUser.`id` in (?,?) and SysUser.`id` in (?,?) and SysUser.`id` not in (?,?) and SysUser.`id` not in (?) and SysUser.`id` not in (?,?) and SysUser.`id` not in (?) and SysUser.`id` not in (?,?) and SysUser.`id` not in (?,?) and SysUser.`id` not in (?,?) and SysUser.`id` not in (?,?)",
                sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{
                "A-1", "A-2",
                "B-1", "B-2",
                "C-1", "C-2",
                "D-1", "D-2",
                "E-1", "E-2",
                "F-1", "F-2",
                "G-1", "G-2", "G-3", "G-4",
                "H-1", "H-2",
                "I-1", "I-2",
                "J-1", "J-2", "J-3", "J-4", "J-5", "J-6", "J-7", "J-8",
                "K-1", "K-2", "K-3", "K-4",
                "L-1", "L-2",
                "M-1", "M-2", "M-3", "M-4", "M-5", "M-6", "M-7", "M-8",
                "N-1", "N-2", "N-3", "N-4"
        }, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 条件删除 - 组合Where条件
     */
    @Test
    void Test_delete_combinationWhere() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable
                                .id().greaterThan("A-1").id().greaterThan("A-2")
                                .id().greaterThanAndEqualTo("B-1").id().greaterThanAndEqualTo("B-2"))
                        .and(mainTable
                                .id().lessThan("C-1").id().lessThan("C-2")
                                .id().lessThanAndEqualTo("D-1").id().lessThanAndEqualTo("D-2"))
                        .or(mainTable
                                .id().equalTo("E-1"))

                        .or(mainTable
                                .id().equalTo("F-1").id().equalTo("F-2"))
                        .or(cd -> cd
                                .and(mainTable.id().equalTo("G-1").id().equalTo("G-2"))
                                .and(mainTable.id().equalTo("H-1")))
                        .and(cd -> cd
                                .and(mainTable.id().equalTo("I-1"))
                                .or(mainTable.id().equalTo("J-1"))))
                .where((condition, mainTable) -> condition
                        .and(mainTable.id().equalTo("K-1")))
                .delete();
        Assertions.assertEquals("delete SysUser from `sys_user` SysUser where (SysUser.`id` > ? and SysUser.`id` > ? and SysUser.`id` >= ? and SysUser.`id` >= ? and SysUser.`id` < ? and SysUser.`id` < ? and SysUser.`id` <= ? and SysUser.`id` <= ? or SysUser.`id` = ? or (SysUser.`id` = ? and SysUser.`id` = ?) or (SysUser.`id` = ? and SysUser.`id` = ? and SysUser.`id` = ?) and (SysUser.`id` = ? or SysUser.`id` = ?)) and SysUser.`id` = ?",
                sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{
                "A-1", "A-2",
                "B-1", "B-2",
                "C-1", "C-2",
                "D-1", "D-2",
                "E-1",
                "F-1", "F-2",
                "G-1", "G-2",
                "H-1",
                "I-1",
                "J-1",
                "K-1"
        }, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 条件删除 - 指定内连接
     */
    @Test
    void Test_delete_assignInnerJoin() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .join(JoinType.INNER, UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.userId().equalTo(mainTable.id())))
                .delete();
        Assertions.assertEquals("delete SysUser from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`user_id` = SysUser.`id`",
                sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 条件删除 - 指定左连接
     */
    @Test
    void Test_delete_assignLeftJoin() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .join(JoinType.LEFT, UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.userId().equalTo(mainTable.id())))
                .delete();
        Assertions.assertEquals("delete SysUser from `sys_user` SysUser left join `user_role` UserRole on UserRole.`user_id` = SysUser.`id`",
                sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 条件删除 - 指定右连接
     */
    @Test
    void Test_delete_assignRightJoin() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .join(JoinType.RIGHT, UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.userId().equalTo(mainTable.id())))
                .delete();
        Assertions.assertEquals("delete SysUser from `sys_user` SysUser right join `user_role` UserRole on UserRole.`user_id` = SysUser.`id`",
                sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 条件删除 - 所有Where条件关联表
     */
    @Test
    void Test_delete_allWhereJoin() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .join(JoinType.INNER, UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.userId().equalTo(mainTable.id())))
                .where(UserRoleHelper.class, (condition, joinTable, mainTable) -> condition
                        .and(joinTable
                                .userId().isNull()
                                .userId().isNotNull()
                                .userId().equalTo(mainTable.id())
                                .userId().notEqualTo(mainTable.id())
                                .userId().greaterThan(mainTable.id())
                                .userId().greaterThanAndEqualTo(mainTable.id())
                                .userId().lessThan(mainTable.id())
                                .userId().lessThanAndEqualTo(mainTable.id())
                                .userId().between(mainTable.id(), mainTable.id())
                                .userId().like(mainTable.id())
                                .userId().in(mainTable.id())
                                .userId().notIn(mainTable.id())
                        ))
                .delete();
        Assertions.assertEquals("delete SysUser from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`user_id` = SysUser.`id` where UserRole.`user_id` is null and UserRole.`user_id` is not null and UserRole.`user_id` = SysUser.`id` and UserRole.`user_id` != SysUser.`id` and UserRole.`user_id` > SysUser.`id` and UserRole.`user_id` >= SysUser.`id` and UserRole.`user_id` < SysUser.`id` and UserRole.`user_id` <= SysUser.`id` and UserRole.`user_id` between SysUser.`id` and SysUser.`id` and UserRole.`user_id` like SysUser.`id` and UserRole.`user_id` in (SysUser.`id`) and UserRole.`user_id` not in (SysUser.`id`)",
                sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 条件删除 - 所有Where条件列
     */
    @Test
    void Test_delete_allWhereColumn() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .join(JoinType.INNER, UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.userId().equalTo(mainTable.id())))
                .join(JoinType.INNER, RoleResourceHelper.class, "RR", (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(UserRoleHelper.class, UserRoleHelper.Column::id)))
                .where((condition, mainTable) -> condition
                        .and(mainTable
                                .id().equalTo(UserRoleHelper.class, table -> table.roleId().roleName())
                                .id().notEqualTo(UserRoleHelper.class, table -> table.roleId().roleName())
                                .id().greaterThan(UserRoleHelper.class, table -> table.roleId().roleName())
                                .id().greaterThanAndEqualTo(UserRoleHelper.class, table -> table.roleId().roleName())
                                .id().lessThan(UserRoleHelper.class, table -> table.roleId().roleName())
                                .id().lessThanAndEqualTo(UserRoleHelper.class, table -> table.roleId().roleName())
                                .id().between(UserRoleHelper.class, table -> table.roleId().roleName(), table -> table.roleId().roleId())
                                .id().like(UserRoleHelper.class, table -> table.roleId().roleName())
                                .id().in(UserRoleHelper.class, table -> table.roleId().roleName())
                                .id().notIn(UserRoleHelper.class, table -> table.roleId().roleName())

                                .id().equalTo(RoleResourceHelper.class, "RR", table -> table.resourceId().resourceName())
                                .id().notEqualTo(RoleResourceHelper.class, "RR", table -> table.resourceId().resourceName())
                                .id().greaterThan(RoleResourceHelper.class, "RR", table -> table.resourceId().resourceName())
                                .id().greaterThanAndEqualTo(RoleResourceHelper.class, "RR", table -> table.resourceId().resourceName())
                                .id().lessThan(RoleResourceHelper.class, "RR", table -> table.resourceId().resourceName())
                                .id().lessThanAndEqualTo(RoleResourceHelper.class, "RR", table -> table.resourceId().resourceName())
                                .id().between(RoleResourceHelper.class, "RR", table -> table.resourceId().resourceName(), table -> table.resourceName().resourceName())
                                .id().like(RoleResourceHelper.class, "RR", table -> table.resourceId().resourceName())
                                .id().in(RoleResourceHelper.class, "RR", table -> table.resourceId().resourceName())
                                .id().notIn(RoleResourceHelper.class, "RR", table -> table.resourceId().resourceName())
                        ))
                .delete();
        Assertions.assertEquals("delete SysUser from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`user_id` = SysUser.`id` inner join `role_resource` RR on RR.`role_id` = UserRole.`id` where SysUser.`id` = UserRole.`role_id` and SysUser.`id` = UserRole.`role_name` and SysUser.`id` != UserRole.`role_id` and SysUser.`id` != UserRole.`role_name` and SysUser.`id` > UserRole.`role_id` and SysUser.`id` > UserRole.`role_name` and SysUser.`id` >= UserRole.`role_id` and SysUser.`id` >= UserRole.`role_name` and SysUser.`id` < UserRole.`role_id` and SysUser.`id` < UserRole.`role_name` and SysUser.`id` <= UserRole.`role_id` and SysUser.`id` <= UserRole.`role_name` and SysUser.`id` between UserRole.`role_id` and UserRole.`role_id` and SysUser.`id` between UserRole.`role_name` and UserRole.`role_id` and SysUser.`id` like UserRole.`role_id` and SysUser.`id` like UserRole.`role_name` and SysUser.`id` in (UserRole.`role_id`,UserRole.`role_name`) and SysUser.`id` not in (UserRole.`role_id`,UserRole.`role_name`) and SysUser.`id` = RR.`resource_id` and SysUser.`id` = RR.`resource_name` and SysUser.`id` != RR.`resource_id` and SysUser.`id` != RR.`resource_name` and SysUser.`id` > RR.`resource_id` and SysUser.`id` > RR.`resource_name` and SysUser.`id` >= RR.`resource_id` and SysUser.`id` >= RR.`resource_name` and SysUser.`id` < RR.`resource_id` and SysUser.`id` < RR.`resource_name` and SysUser.`id` <= RR.`resource_id` and SysUser.`id` <= RR.`resource_name` and SysUser.`id` between RR.`resource_id` and RR.`resource_name` and SysUser.`id` between RR.`resource_name` and RR.`resource_name` and SysUser.`id` like RR.`resource_id` and SysUser.`id` like RR.`resource_name` and SysUser.`id` in (RR.`resource_id`,RR.`resource_name`) and SysUser.`id` not in (RR.`resource_id`,RR.`resource_name`)",
                sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 条件删除 - 所有Where条件子查询
     */
//    @Test
/*    void Test_delete_allWhereSubQuery() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .join(JoinType.INNER, UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.userId().equalTo(mainTable.id())))
                .join(JoinType.INNER, RoleResourceHelper.class, "RR", (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(UserRoleHelper.class, UserRoleHelper.Column::id)))
                .where((condition, mainTable) -> condition
                        .and(mainTable
                                .id().equalToSubQuery(UserRoleHelper.class, query -> query)
                        ))
                .delete();
        Assertions.assertEquals("",
                sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }*/

}
