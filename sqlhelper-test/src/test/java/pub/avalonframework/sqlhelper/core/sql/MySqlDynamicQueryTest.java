package pub.avalonframework.sqlhelper.core.sql;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pub.avalonframework.sqlhelper.AbstractTest;
import pub.avalonframework.sqlhelper.core.beans.ComparisonRule;
import pub.avalonframework.sqlhelper.core.beans.GroupType;
import pub.avalonframework.sqlhelper.core.beans.JoinType;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.SqlBuilderResult;
import pub.avalonframework.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalonframework.sqlhelper.readme.entity.RoleResourceHelper;
import pub.avalonframework.sqlhelper.readme.entity.SysUserHelper;
import pub.avalonframework.sqlhelper.readme.entity.UserRoleHelper;

import java.util.Arrays;

/**
 * MySql动态引擎 - 查询
 */
public class MySqlDynamicQueryTest extends AbstractTest {

    /**
     * 查询总数
     */
    @Test
    void Test_count() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .queryCount();
        Assertions.assertEquals("select count(1) from `sys_user` SysUser", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 查询总数 - 指定表名
     */
    @Test
    void Test_count_assignTableName() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table("sys_user_custom", SysUserHelper.class)
                .queryCount();
        Assertions.assertEquals("select count(1) from `sys_user_custom` SysUser", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 查询总数 - 指定表别名
     */
    @Test
    void Test_count_assignTableAlias() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class, "A")
                .queryCount();
        Assertions.assertEquals("select count(1) from `sys_user` A", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 查询总数 - 所有where条件
     */
    @Test
    void Test_count_allWhere() {
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
                .queryCount();
        Assertions.assertEquals("select count(1) from `sys_user` SysUser where SysUser.`id` is null and SysUser.`id` is not null and SysUser.`id` = ? and SysUser.`id` = ? and SysUser.`id` != ? and SysUser.`id` != ? and SysUser.`id` > ? and SysUser.`id` > ? and SysUser.`id` >= ? and SysUser.`id` >= ? and SysUser.`id` < ? and SysUser.`id` < ? and SysUser.`id` <= ? and SysUser.`id` <= ? and SysUser.`id` between ? and ? and SysUser.`id` between ? and ? and SysUser.`id` like ? and SysUser.`id` like ? and SysUser.`id` in (?,?) and SysUser.`id` in (?) and SysUser.`id` in (?,?) and SysUser.`id` in (?) and SysUser.`id` in (?,?) and SysUser.`id` in (?,?) and SysUser.`id` in (?,?) and SysUser.`id` in (?,?) and SysUser.`id` not in (?,?) and SysUser.`id` not in (?) and SysUser.`id` not in (?,?) and SysUser.`id` not in (?) and SysUser.`id` not in (?,?) and SysUser.`id` not in (?,?) and SysUser.`id` not in (?,?) and SysUser.`id` not in (?,?)", sqlBuilderResult.getPreparedStatementSql());
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
     * 查询总数 - 指定关联表
     */
    @Test
    void Test_count_assignJoin() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .join(JoinType.INNER, UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .queryCount();
        Assertions.assertEquals("select count(1) from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id`", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 查询总数 - 指定表名 & 指定关联表
     */
    @Test
    void Test_count_assignTableName_assignJoin() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table("sys_user_custom", SysUserHelper.class)
                .join(JoinType.INNER, "user_role_custom", UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .queryCount();
        Assertions.assertEquals("select count(1) from `sys_user_custom` SysUser inner join `user_role_custom` UserRole on UserRole.`role_id` = SysUser.`id`", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 查询总数 - 指定表名 & 指定表别名 & 指定关联表
     */
    @Test
    void Test_count_assignTableName_assignTableAlias_assignJoin() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table("sys_user_custom", SysUserHelper.class, "A")
                .join(JoinType.INNER, "user_role_custom", UserRoleHelper.class, "B", (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .queryCount();
        Assertions.assertEquals("select count(1) from `sys_user_custom` A inner join `user_role_custom` B on B.`role_id` = A.`id`", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 查询总数 - 全部on条件
     */
    @Test
    void Test_count_join_allOn() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .join(JoinType.INNER, UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable
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
                .queryCount();
        Assertions.assertEquals("select count(1) from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`id` is null and UserRole.`id` is not null and UserRole.`id` = ? and UserRole.`id` = ? and UserRole.`id` != ? and UserRole.`id` != ? and UserRole.`id` > ? and UserRole.`id` > ? and UserRole.`id` >= ? and UserRole.`id` >= ? and UserRole.`id` < ? and UserRole.`id` < ? and UserRole.`id` <= ? and UserRole.`id` <= ? and UserRole.`id` between ? and ? and UserRole.`id` between ? and ? and UserRole.`id` like ? and UserRole.`id` like ? and UserRole.`id` in (?,?) and UserRole.`id` in (?) and UserRole.`id` in (?,?) and UserRole.`id` in (?) and UserRole.`id` in (?,?) and UserRole.`id` in (?,?) and UserRole.`id` in (?,?) and UserRole.`id` in (?,?) and UserRole.`id` not in (?,?) and UserRole.`id` not in (?) and UserRole.`id` not in (?,?) and UserRole.`id` not in (?) and UserRole.`id` not in (?,?) and UserRole.`id` not in (?,?) and UserRole.`id` not in (?,?) and UserRole.`id` not in (?,?)", sqlBuilderResult.getPreparedStatementSql());
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
     * 查询总数 - 指定内连接关联表
     */
    @Test
    void Test_count_assignInnerJoin() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .innerJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .queryCount();
        Assertions.assertEquals("select count(1) from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id`", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 查询总数 - 指定左连接关联表
     */
    @Test
    void Test_count_assignLeftJoin() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .leftJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .queryCount();
        Assertions.assertEquals("select count(1) from `sys_user` SysUser left join `user_role` UserRole on UserRole.`role_id` = SysUser.`id`", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 查询总数 - 指定右连接关联表
     */
    @Test
    void Test_count_assignRightJoin() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .rightJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .queryCount();
        Assertions.assertEquals("select count(1) from `sys_user` SysUser right join `user_role` UserRole on UserRole.`role_id` = SysUser.`id`", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    @Test
    void TestColumn01() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(table -> table)
                .queryByPrimaryKey(arg());
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser where SysUser.`id` = ?");
    }

    @Test
    void TestColumn02() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(SysUserHelper.Column::id)
                .queryByPrimaryKey(arg());
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id` from `sys_user` SysUser where SysUser.`id` = ?");
    }

    @Test
    void TestColumn03() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(table -> table.loginName().userName("AA"))
                .queryByPrimaryKey(arg());
        setSqlBuilder(sqlBuilderResult, "select SysUser.`login_name` `loginName`,SysUser.`user_name` `AA` from `sys_user` SysUser where SysUser.`id` = ?");
    }

    @Test
    void TestVirtualColumn01() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .virtualColumn("1", "AA")
                .virtualColumn(1, "BB")
                .virtualColumn(1L, "CC")
                .virtualColumn(null, "DD")
                .queryByPrimaryKey(arg());
        setSqlBuilder(sqlBuilderResult, "select '1' `AA`,1 `BB`,1 `CC`,null `DD` from `sys_user` SysUser where SysUser.`id` = ?");
    }

    @Test
    void TestColumnAndVirtualColumn01() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .virtualColumn(1, "AA")
                .column(table -> table)
                .queryByPrimaryKey(arg());
        setSqlBuilder(sqlBuilderResult, "select 1 `AA`,SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser where SysUser.`id` = ?");
    }

    @Test
    void TestGroupColumn01() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .groupColumn(GroupType.COUNT, SysUserHelper.Column::id)
                .queryByPrimaryKey(arg());
        setSqlBuilder(sqlBuilderResult, "select count(SysUser.`id`) `id` from `sys_user` SysUser where SysUser.`id` = ?");
    }

    @Test
    void TestGroupColumnAndVirtualColumn01() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .groupColumn(GroupType.COUNT, SysUserHelper.Column::id)
                .virtualColumn(1, "AA")
                .queryByPrimaryKey(arg());
        setSqlBuilder(sqlBuilderResult, "select count(SysUser.`id`) `id`,1 `AA` from `sys_user` SysUser where SysUser.`id` = ?");
    }

    @Test
    void TestGroupColumnAndVirtualColumnAndColumn01() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .groupColumn(GroupType.COUNT, table -> table.id("idCount"))
                .virtualColumn(1, "AA")
                .column(table -> table)
                .queryByPrimaryKey(arg());
        setSqlBuilder(sqlBuilderResult, "select count(SysUser.`id`) `idCount`,1 `AA`,SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser where SysUser.`id` = ?");
    }

    @Test
    void TestWhere01() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(table -> table)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser where SysUser.`user_name` = ?");
    }

    @Test
    void TestWhere02() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(table -> table)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())
                                .loginName().equalTo(arg())))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser where SysUser.`user_name` = ? and SysUser.`login_name` = ?");
    }

    @Test
    void TestWhere03() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(table -> table)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())
                                .loginName().equalTo(arg()))
                        .or(mainTable.id().greaterThan(arg())))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser where SysUser.`user_name` = ? and SysUser.`login_name` = ? or SysUser.`id` > ?");
    }

    @Test
    void TestWhere04() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(table -> table)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())
                                .loginName().equalTo(arg()))
                        .or(mainTable.id().greaterThan(arg())
                                .loginName().between(arg(), arg()))
                        .and(mainTable.loginName().like(arg())))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser where SysUser.`user_name` = ? and SysUser.`login_name` = ? or (SysUser.`id` > ? and SysUser.`login_name` between ? and ?) and SysUser.`login_name` like ?");
    }

    @Test
    void TestWhere05() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(table -> table)
                .where((condition, mainTable) -> condition
                        .and(cd -> cd
                                .and(mainTable.userName().notEqualTo(arg()))
                                .or(mainTable.loginName().greaterThan(arg())))
                        .or(mainTable.id().greaterThan(arg())
                                .userName().between(arg(), arg()))
                        .and(cd -> cd
                                .and(mainTable.userName().equalTo(arg()))
                                .or(mainTable.loginName().equalTo(arg()))))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser where (SysUser.`user_name` != ? or SysUser.`login_name` > ?) or (SysUser.`id` > ? and SysUser.`user_name` between ? and ?) and (SysUser.`user_name` = ? or SysUser.`login_name` = ?)");
    }

    @Test
    void TestWhere06() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(table -> table)
                .where((condition, mainTable) -> condition
                        .and(cd -> cd
                                .and(mainTable.userName().notEqualTo(arg()))
                                .or(mainTable.userName().greaterThan(arg())))
                        .or(mainTable.id().greaterThan(arg())
                                .userName().between(arg(), arg()))
                        .and(cd -> cd
                                .and(mainTable.userName().equalTo(arg()))
                                .or(mainTable.loginName().equalTo(arg()))))
                .where((condition, mainTable) -> condition
                        .and(cd -> cd
                                .and(mainTable.userName().greaterThanAndEqualTo(arg()))
                                .or(mainTable.loginName().lessThanAndEqualTo(arg()))))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser where ((SysUser.`user_name` != ? or SysUser.`user_name` > ?) or (SysUser.`id` > ? and SysUser.`user_name` between ? and ?) and (SysUser.`user_name` = ? or SysUser.`login_name` = ?)) and (SysUser.`user_name` >= ? or SysUser.`login_name` <= ?)");
    }

    @Test
    void TestJoin01() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(table -> table)
                .innerJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id`");
    }

    @Test
    void TestJoin02() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(table -> table)
                .innerJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id()).roleName().equalTo(arg())))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` and UserRole.`role_name` = ?");
    }

    @Test
    void TestJoin03() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(table -> table)
                .innerJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id()).roleName().equalTo(arg())))
                .leftJoin(RoleResourceHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` and UserRole.`role_name` = ? left join `role_resource` RoleResource on RoleResource.`role_id` = SysUser.`id`");
    }

    @Test
    void TestJoin04() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(table -> table)
                .innerJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id()).roleName().equalTo(arg())))
                .leftJoin(RoleResourceHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .rightJoin(UserRoleHelper.class, "UR", (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` and UserRole.`role_name` = ? left join `role_resource` RoleResource on RoleResource.`role_id` = SysUser.`id` right join `user_role` UR on UR.`role_id` = SysUser.`id`");
    }

    @Test
    void TestJoinAndWhere01() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(table -> table)
                .innerJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .where(UserRoleHelper.class, (condition, table, mainTable) -> condition
                        .and(table.roleName().like(arg())))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` where UserRole.`role_name` like ?");

    }

    @Test
    void TestJoinAndWhere02() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(table -> table)
                .innerJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())
                                .roleName().equalTo(arg())))
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().notEqualTo(arg()))
                        .or(mainTable.loginName().equalTo(arg())))
                .where(UserRoleHelper.class, (condition, table, mainTable) -> condition
                        .and(table.roleName().equalTo(arg())))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` and UserRole.`role_name` = ? where (SysUser.`user_name` != ? or SysUser.`login_name` = ?) and UserRole.`role_name` = ?");
    }

    @Test
    void TestJoinAndWhere03() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(table -> table)
                .innerJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id()).roleName().equalTo(arg())))
                .leftJoin(RoleResourceHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id()))
                        .and(joinTable.roleId().equalTo(UserRoleHelper.class, UserRoleHelper.Column::roleId)))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` and UserRole.`role_name` = ? left join `role_resource` RoleResource on RoleResource.`role_id` = SysUser.`id` and RoleResource.`role_id` = UserRole.`role_id`");
    }

    @Test
    void TestJoinAndWhere04() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(table -> table)
                .innerJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())
                                .roleName().equalTo(arg())))
                .rightJoin(UserRoleHelper.class, "UR", (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .leftJoin(RoleResourceHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())
                                .roleId().lessThan(UserRoleHelper.class, "UR", UserRoleHelper.Column::roleId)))
                .where(UserRoleHelper.class, "UR", (condition, table, mainTable) -> condition
                        .and(table.roleName().equalTo(arg())))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` and UserRole.`role_name` = ? right join `user_role` UR on UR.`role_id` = SysUser.`id` left join `role_resource` RoleResource on RoleResource.`role_id` = SysUser.`id` and RoleResource.`role_id` < UR.`role_id` where UR.`role_name` = ?");
    }

    @Test
    void TestJoinWhereAndOr01() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(table -> table)
                .innerJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())
                                .roleName().equalTo(arg())))
                .rightJoin(UserRoleHelper.class, "UR", (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .leftJoin(RoleResourceHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())
                                .roleId().lessThan(UserRoleHelper.class, "UR", UserRoleHelper.Column::roleId)))
                .where(UserRoleHelper.class, "UR", (condition, table, mainTable) -> condition
                        .and(cd -> cd
                                .and(table.roleName().equalTo(arg()))
                                .or(table.roleId().equalTo(arg())))
                        .and(table.sortIndex().equalTo(arg())))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` and UserRole.`role_name` = ? right join `user_role` UR on UR.`role_id` = SysUser.`id` left join `role_resource` RoleResource on RoleResource.`role_id` = SysUser.`id` and RoleResource.`role_id` < UR.`role_id` where (UR.`role_name` = ? or UR.`role_id` = ?) and UR.`sort_index` = ?");
    }

    @Test
    void TestGroup01() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(table -> table)
                .groupBy(SysUserHelper.Group::userName)
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser group by SysUser.`user_name`");

    }

    @Test
    void TestGroup02() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(table -> table)
                .groupBy(table -> table.userName().id())
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser group by SysUser.`user_name`,SysUser.`id`");
    }

    @Test
    void TestGroupAndJoinAndWhere01() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(table -> table)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())))
                .groupBy(table -> table.id().loginName())
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser where SysUser.`user_name` = ? group by SysUser.`id`,SysUser.`login_name`");

    }

    @Test
    void TestGroupAndJoinAndWhere02() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(table -> table)
                .innerJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())))
                .groupBy(table -> table.userName().id())
                .groupBy(UserRoleHelper.class, UserRoleHelper.Group::roleId)
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` where SysUser.`user_name` = ? group by SysUser.`user_name`,SysUser.`id`,UserRole.`role_id`");
    }

    @Test
    void TestSort01() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(table -> table)
                .orderBy(table -> table.id().asc())
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser order by SysUser.`id` asc");
    }

    @Test
    void TestSort02() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(table -> table)
                .orderBy(table -> table.id().asc().userName().desc())
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser order by SysUser.`id` asc,SysUser.`user_name` desc");
    }

    @Test
    void TestSort03() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table("sys_user_201903", SysUserHelper.class)
                .column(table -> table)
                .orderBy(table -> table.id().asc().userName().desc())
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user_201903` SysUser order by SysUser.`id` asc,SysUser.`user_name` desc");
    }

    @Test
    void TestSort04() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(table -> table)
                .innerJoin(UserRoleHelper.class, "UserRoleAlias", (on, joinTable, mainTable) -> on
                        .and(joinTable.userId().equalTo(mainTable.id())))
                .orderBy(table -> table.id().asc().userName().desc())
                .orderBy(UserRoleHelper.class, "UserRoleAlias", table -> table.sortIndex().desc())
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRoleAlias on UserRoleAlias.`user_id` = SysUser.`id` order by SysUser.`id` asc,SysUser.`user_name` desc,UserRoleAlias.`sort_index` desc");
    }

    @Test
    void TestSortAndJoinAndWhere01() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(table -> table)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())))
                .orderBy(table -> table.id().desc())
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser where SysUser.`user_name` = ? order by SysUser.`id` desc");

    }

    @Test
    void TestSortAndJoinAndWhere02() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(table -> table)
                .innerJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .where((condition, mainTable) -> condition
                        .and(mainTable.id().equalTo(arg())))
                .orderBy(UserRoleHelper.class, table -> table.id().desc())
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` where SysUser.`id` = ? order by UserRole.`id` desc");
    }

    @Test
    void TestLimit02() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(table -> table)
                .limit(arg(10L))
                .offset(arg(20L))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser limit ? offset ?");
    }

    @Test
    void TestJoinOn01() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(table -> table)
                .innerJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(o -> o
                                .and(joinTable.id().equalTo(mainTable.userName()))
                                .or(joinTable.roleName().equalTo(mainTable.primaryKey())))
                        .or(joinTable.id().equalTo(mainTable.loginName())))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on (UserRole.`id` = SysUser.`user_name` or UserRole.`role_name` = SysUser.`id`) or UserRole.`id` = SysUser.`login_name`");

    }

    @Test
    void TestJoinOn02() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(table -> table)
                .innerJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(o -> o
                                .and(joinTable.id().equalTo(mainTable.userName()).roleId().equalTo(arg()))
                                .or(joinTable.roleName().equalTo(mainTable.loginName()))
                                .and(o1 -> o1
                                        .and(joinTable.id().equalTo(arg())
                                                .roleName().equalTo(arg()))))
                        .or(joinTable.roleName().equalTo(arg()))
                        .or(joinTable.id().equalTo(mainTable.userName())))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on (UserRole.`id` = SysUser.`user_name` and UserRole.`role_id` = ? or UserRole.`role_name` = SysUser.`login_name` and UserRole.`id` = ? and UserRole.`role_name` = ?) or UserRole.`role_name` = ? or UserRole.`id` = SysUser.`user_name`");
    }

    @Test
    void TestWhereJoin01() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(table -> table)
                .innerJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(o -> o
                                .and(joinTable.id().equalTo(mainTable.userName()))
                                .or(joinTable.roleName().equalTo(mainTable.primaryKey())))
                        .or(joinTable.id().equalTo(mainTable.loginName())))
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(UserRoleHelper.class, UserRoleHelper.Column::roleName)))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on (UserRole.`id` = SysUser.`user_name` or UserRole.`role_name` = SysUser.`id`) or UserRole.`id` = SysUser.`login_name` where SysUser.`user_name` = UserRole.`role_name`");
    }

    @Test
    void TestWhereJoin02() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(table -> table)
                .innerJoin("user_role_20190413", UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(o -> o
                                .and(joinTable.id().equalTo(mainTable.userName()))
                                .or(joinTable.roleName().equalTo(mainTable.primaryKey())))
                        .or(joinTable.id().equalTo(mainTable.loginName())))
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(UserRoleHelper.class, UserRoleHelper.Column::id)))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role_20190413` UserRole on (UserRole.`id` = SysUser.`user_name` or UserRole.`role_name` = SysUser.`id`) or UserRole.`id` = SysUser.`login_name` where SysUser.`user_name` = UserRole.`id`");
    }

/*    @Test
    void TestSubQuery() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .innerJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.userId().equalTo(mainTable.id())))
                .subQuery(UserRoleHelper.class, "AA", (mainTable, query) -> query
                        .column(UserRoleHelper.Column::id)
                        .where((cd, mt) -> cd
                                .and(mt.roleId().equalTo(arg())
                                        .roleName().equalTo(mainTable.userName()))
                                .and(mainTable.id().equalTo(mt.id()))
                                .and(mt.sortIndex().equalTo(UserRoleHelper.class, UserRoleHelper.Column::id)))
                        .limit(arg(1), arg(1)).table(), "subQuery")
                .column(table -> table)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())))
                .query();
        setSqlBuilder(sqlBuilderResult, "select (select AA.`id` `id` from `user_role` AA where AA.`role_id` = ? and AA.`role_name` = SysUser.`user_name` and SysUser.`id` = AA.`id` and AA.`sort_index` = UserRole.`id` limit ?,?) subQuery, SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`user_id` = SysUser.`id` where SysUser.`user_name` = ?");
    }*/

/*    @Test
    void TestWhereSubQuery() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .innerJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.userId().equalTo(mainTable.id())))
                .column(table -> table)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().likeSubQuery(UserRoleHelper.class, (mt, query) -> query
                                .column(UserRoleHelper.Column::id)
                                .where((ct, m) -> ct
                                        .and(m.roleName().equalTo(arg())))
                                .limitOne()
                                .table())))
                .query();
        arg(0);
        arg(1);
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`user_id` = SysUser.`id` where SysUser.`user_name` like (select UserRole.`id` `id` from `user_role` UserRole where UserRole.`role_name` = ? limit ?,?)");

        sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .innerJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.userId().equalTo(mainTable.id())))
                .column(table -> table)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().likeSubQuery(UserRoleHelper.class, (mt, query) -> query
                                .column(UserRoleHelper.Column::id)
                                .where((ct, m) -> ct
                                        .and(m.userId().between(arg(), arg())))
                                .limitOne()
                                .table())))
                .query();
        arg(0);
        arg(1);
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`user_id` = SysUser.`id` where SysUser.`user_name` like (select UserRole.`id` `id` from `user_role` UserRole where UserRole.`user_id` between ? and ? limit ?,?)");
    }*/

    @Test
    void TestWhereSqlPart() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(table -> table)
                .where((condition, mainTable) -> condition
                        .and(mainTable.loginName().sqlPart(" =NOW()")))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser where SysUser.`login_name` =NOW()");
    }

}
