package pub.avalon.sqlhelper.core.sql;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pub.avalon.beans.DataBaseType;
import pub.avalon.beans.Pagination;
import pub.avalon.sqlhelper.AbstractTest;
import pub.avalon.sqlhelper.core.beans.ComparisonRule;
import pub.avalon.sqlhelper.core.beans.GroupType;
import pub.avalon.sqlhelper.core.beans.JoinType;
import pub.avalon.sqlhelper.core.sqlbuilder.beans.SqlBuilderResult;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalon.sqlhelper.readme.entity.RoleResourceDTO;
import pub.avalon.sqlhelper.readme.entity.SysUserDTO;
import pub.avalon.sqlhelper.readme.entity.UserRoleDTO;

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
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .queryCount();
        Assertions.assertEquals("select count(1) from `sys_user` SysUser", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 查询总数 - 指定表名
     */
    @Test
    void Test_count_assignTableName() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table("sys_user_custom", SysUserDTO.Helper.class)
                .queryCount();
        Assertions.assertEquals("select count(1) from `sys_user_custom` SysUser", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 查询总数 - 指定表别名
     */
    @Test
    void Test_count_assignTableAlias() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class, "A")
                .queryCount();
        Assertions.assertEquals("select count(1) from `sys_user` A", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 查询总数 - 所有where条件
     */
    @Test
    void Test_count_allWhere() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
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
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .join(JoinType.INNER, UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
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
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table("sys_user_custom", SysUserDTO.Helper.class)
                .join(JoinType.INNER, "user_role_custom", UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
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
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table("sys_user_custom", SysUserDTO.Helper.class, "A")
                .join(JoinType.INNER, "user_role_custom", UserRoleDTO.Helper.class, "B", (on, joinTable, mainTable) -> on
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
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .join(JoinType.INNER, UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
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
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .innerJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
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
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .leftJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
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
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .rightJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .queryCount();
        Assertions.assertEquals("select count(1) from `sys_user` SysUser right join `user_role` UserRole on UserRole.`role_id` = SysUser.`id`", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    @Test
    void TestColumn01() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .column(table -> table)
                .queryByPrimaryKey(arg());
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser where SysUser.`id` = ?");
    }

    @Test
    void TestColumn02() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .column(SysUserDTO.Helper.Column::id)
                .queryByPrimaryKey(arg());
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id` from `sys_user` SysUser where SysUser.`id` = ?");
    }

    @Test
    void TestColumn03() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .column(table -> table.loginName().userName("AA"))
                .queryByPrimaryKey(arg());
        setSqlBuilder(sqlBuilderResult, "select SysUser.`login_name` `loginName`,SysUser.`user_name` `AA` from `sys_user` SysUser where SysUser.`id` = ?");
    }

    @Test
    void TestVirtualColumn01() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .virtualColumn("1", "AA")
                .virtualColumn(1, "BB")
                .virtualColumn(1L, "CC")
                .virtualColumn(null, "DD")
                .queryByPrimaryKey(arg());
        setSqlBuilder(sqlBuilderResult, "select '1' `AA`,1 `BB`,1 `CC`,null `DD` from `sys_user` SysUser where SysUser.`id` = ?");
    }

    @Test
    void TestColumnAndVirtualColumn01() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .virtualColumn(1, "AA")
                .column(table -> table)
                .queryByPrimaryKey(arg());
        setSqlBuilder(sqlBuilderResult, "select 1 `AA`, SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser where SysUser.`id` = ?");
    }

    @Test
    void TestGroupColumn01() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .groupColumn(GroupType.COUNT, SysUserDTO.Helper.Column::id)
                .queryByPrimaryKey(arg());
        setSqlBuilder(sqlBuilderResult, "select count(SysUser.`id`) `id` from `sys_user` SysUser where SysUser.`id` = ?");
    }

    @Test
    void TestGroupColumnAndVirtualColumn01() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .groupColumn(GroupType.COUNT, SysUserDTO.Helper.Column::id)
                .virtualColumn(1, "AA")
                .queryByPrimaryKey(arg());
        setSqlBuilder(sqlBuilderResult, "select count(SysUser.`id`) `id`, 1 `AA` from `sys_user` SysUser where SysUser.`id` = ?");
    }

    @Test
    void TestGroupColumnAndVirtualColumnAndColumn01() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .groupColumn(GroupType.COUNT, table -> table.id("idCount"))
                .virtualColumn(1, "AA")
                .column(table -> table)
                .queryByPrimaryKey(arg());
        setSqlBuilder(sqlBuilderResult, "select count(SysUser.`id`) `idCount`, 1 `AA`, SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser where SysUser.`id` = ?");
    }

    @Test
    void TestWhere01() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser where SysUser.`user_name` = ?");
    }

    @Test
    void TestWhere02() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())
                                .loginName().equalTo(arg())))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser where SysUser.`user_name` = ? and SysUser.`login_name` = ?");
    }

    @Test
    void TestWhere03() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())
                                .loginName().equalTo(arg()))
                        .or(mainTable.id().greaterThan(arg())))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser where SysUser.`user_name` = ? and SysUser.`login_name` = ? or SysUser.`id` > ?");
    }

    @Test
    void TestWhere04() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
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
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
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
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
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
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .innerJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id`");
    }

    @Test
    void TestJoin02() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .innerJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id()).roleName().equalTo(arg())))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` and UserRole.`role_name` = ?");
    }

    @Test
    void TestJoin03() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .innerJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id()).roleName().equalTo(arg())))
                .leftJoin(RoleResourceDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` and UserRole.`role_name` = ? left join `role_resource` RoleResource on RoleResource.`role_id` = SysUser.`id`");
    }

    @Test
    void TestJoin04() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .innerJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id()).roleName().equalTo(arg())))
                .leftJoin(RoleResourceDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .rightJoin(UserRoleDTO.Helper.class, "UR", (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` and UserRole.`role_name` = ? left join `role_resource` RoleResource on RoleResource.`role_id` = SysUser.`id` right join `user_role` UR on UR.`role_id` = SysUser.`id`");
    }

    @Test
    void TestJoinAndWhere01() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .innerJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .where(UserRoleDTO.Helper.class, (condition, table, mainTable) -> condition
                        .and(table.roleName().like(arg())))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` where UserRole.`role_name` like ?");

    }

    @Test
    void TestJoinAndWhere02() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .innerJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())
                                .roleName().equalTo(arg())))
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().notEqualTo(arg()))
                        .or(mainTable.loginName().equalTo(arg())))
                .where(UserRoleDTO.Helper.class, (condition, table, mainTable) -> condition
                        .and(table.roleName().equalTo(arg())))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` and UserRole.`role_name` = ? where (SysUser.`user_name` != ? or SysUser.`login_name` = ?) and UserRole.`role_name` = ?");
    }

    @Test
    void TestJoinAndWhere03() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .innerJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id()).roleName().equalTo(arg())))
                .leftJoin(RoleResourceDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id()))
                        .and(joinTable.roleId().equalTo(UserRoleDTO.Helper.class, UserRoleDTO.Helper.Column::roleId)))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` and UserRole.`role_name` = ? left join `role_resource` RoleResource on RoleResource.`role_id` = SysUser.`id` and RoleResource.`role_id` = UserRole.`role_id`");
    }

    @Test
    void TestJoinAndWhere04() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .innerJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())
                                .roleName().equalTo(arg())))
                .rightJoin(UserRoleDTO.Helper.class, "UR", (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .leftJoin(RoleResourceDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())
                                .roleId().lessThan(UserRoleDTO.Helper.class, "UR", UserRoleDTO.Helper.Column::roleId)))
                .where(UserRoleDTO.Helper.class, "UR", (condition, table, mainTable) -> condition
                        .and(table.roleName().equalTo(arg())))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` and UserRole.`role_name` = ? right join `user_role` UR on UR.`role_id` = SysUser.`id` left join `role_resource` RoleResource on RoleResource.`role_id` = SysUser.`id` and RoleResource.`role_id` < UR.`role_id` where UR.`role_name` = ?");
    }

    @Test
    void TestJoinWhereAndOr01() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .innerJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())
                                .roleName().equalTo(arg())))
                .rightJoin(UserRoleDTO.Helper.class, "UR", (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .leftJoin(RoleResourceDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())
                                .roleId().lessThan(UserRoleDTO.Helper.class, "UR", UserRoleDTO.Helper.Column::roleId)))
                .where(UserRoleDTO.Helper.class, "UR", (condition, table, mainTable) -> condition
                        .and(cd -> cd
                                .and(table.roleName().equalTo(arg()))
                                .or(table.roleId().equalTo(arg())))
                        .and(table.sortIndex().equalTo(arg())))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` and UserRole.`role_name` = ? right join `user_role` UR on UR.`role_id` = SysUser.`id` left join `role_resource` RoleResource on RoleResource.`role_id` = SysUser.`id` and RoleResource.`role_id` < UR.`role_id` where (UR.`role_name` = ? or UR.`role_id` = ?) and UR.`sort_index` = ?");
    }

    @Test
    void TestGroup01() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .group(SysUserDTO.Helper.Group::userName)
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser group by SysUser.`user_name`");

    }

    @Test
    void TestGroup02() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .group(table -> table.userName().id())
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser group by SysUser.`user_name`,SysUser.`id`");
    }

    @Test
    void TestGroupAndJoinAndWhere01() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())))
                .group(table -> table.id().loginName())
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser where SysUser.`user_name` = ? group by SysUser.`id`,SysUser.`login_name`");

    }

    @Test
    void TestGroupAndJoinAndWhere02() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .innerJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())))
                .group(table -> table.userName().id())
                .group(UserRoleDTO.Helper.class, UserRoleDTO.Helper.Group::roleId)
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` where SysUser.`user_name` = ? group by SysUser.`user_name`,SysUser.`id`,UserRole.`role_id`");
    }

    @Test
    void TestSort01() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .sort(table -> table.id().asc())
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser order by SysUser.`id` asc");
    }

    @Test
    void TestSort02() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .sort(table -> table.id().asc().userName().desc())
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser order by SysUser.`id` asc,SysUser.`user_name` desc");
    }

    @Test
    void TestSort03() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table("sys_user_201903", SysUserDTO.Helper.class)
                .sort(table -> table.id().asc().userName().desc())
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user_201903` SysUser order by SysUser.`id` asc,SysUser.`user_name` desc");
    }

    @Test
    void TestSort04() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .innerJoin(UserRoleDTO.Helper.class, "UserRoleAlias", (on, joinTable, mainTable) -> on
                        .and(joinTable.userId().equalTo(mainTable.id())))
                .sort(table -> table.id().asc().userName().desc())
                .sort(UserRoleDTO.Helper.class, "UserRoleAlias", table -> table.sortIndex().desc())
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRoleAlias on UserRoleAlias.`user_id` = SysUser.`id` order by SysUser.`id` asc,SysUser.`user_name` desc,UserRoleAlias.`sort_index` desc");
    }

    @Test
    void TestSortAndJoinAndWhere01() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())))
                .sort(table -> table.id().desc())
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser where SysUser.`user_name` = ? order by SysUser.`id` desc");

    }

    @Test
    void TestSortAndJoinAndWhere02() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .innerJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .where((condition, mainTable) -> condition
                        .and(mainTable.id().equalTo(arg())))
                .sort(UserRoleDTO.Helper.class, table -> table.id().desc())
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` where SysUser.`id` = ? order by UserRole.`id` desc");
    }

    @Test
    void TestLimit02() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .limit(200L, 10L, 20L)
                .query();
        Pagination pagination = new Pagination(DataBaseType.MYSQL, 200L, 10L, 20L);
        arg(pagination.getLimitStartNum());
        arg(pagination.getLimitEndNum());
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser limit ?,?");
    }

    @Test
    void TestJoinOn01() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .innerJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(o -> o
                                .and(joinTable.id().equalTo(mainTable.userName()))
                                .or(joinTable.roleName().equalTo(mainTable.primaryKey())))
                        .or(joinTable.id().equalTo(mainTable.loginName())))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on (UserRole.`id` = SysUser.`user_name` or UserRole.`role_name` = SysUser.`id`) or UserRole.`id` = SysUser.`login_name`");

    }

    @Test
    void TestJoinOn02() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .innerJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
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
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .innerJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(o -> o
                                .and(joinTable.id().equalTo(mainTable.userName()))
                                .or(joinTable.roleName().equalTo(mainTable.primaryKey())))
                        .or(joinTable.id().equalTo(mainTable.loginName())))
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(UserRoleDTO.Helper.class, UserRoleDTO.Helper.Column::roleName)))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on (UserRole.`id` = SysUser.`user_name` or UserRole.`role_name` = SysUser.`id`) or UserRole.`id` = SysUser.`login_name` where SysUser.`user_name` = UserRole.`role_name`");
    }

    @Test
    void TestWhereJoin02() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .innerJoin("user_role_20190413", UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(o -> o
                                .and(joinTable.id().equalTo(mainTable.userName()))
                                .or(joinTable.roleName().equalTo(mainTable.primaryKey())))
                        .or(joinTable.id().equalTo(mainTable.loginName())))
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(UserRoleDTO.Helper.class, UserRoleDTO.Helper.Column::id)))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role_20190413` UserRole on (UserRole.`id` = SysUser.`user_name` or UserRole.`role_name` = SysUser.`id`) or UserRole.`id` = SysUser.`login_name` where SysUser.`user_name` = UserRole.`id`");
    }

/*    @Test
    void TestSubQuery() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .innerJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.userId().equalTo(mainTable.id())))
                .subQuery(UserRoleDTO.Helper.class, "AA", (mainTable, query) -> query
                        .column(UserRoleDTO.Helper.Column::id)
                        .where((cd, mt) -> cd
                                .and(mt.roleId().equalTo(arg())
                                        .roleName().equalTo(mainTable.userName()))
                                .and(mainTable.id().equalTo(mt.id()))
                                .and(mt.sortIndex().equalTo(UserRoleDTO.Helper.class, UserRoleDTO.Helper.Column::id)))
                        .limit(arg(1), arg(1)).table(), "subQuery")
                .column(table -> table)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())))
                .query();
        setSqlBuilder(sqlBuilderResult, "select (select AA.`id` `id` from `user_role` AA where AA.`role_id` = ? and AA.`role_name` = SysUser.`user_name` and SysUser.`id` = AA.`id` and AA.`sort_index` = UserRole.`id` limit ?,?) subQuery, SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`user_id` = SysUser.`id` where SysUser.`user_name` = ?");
    }*/

/*    @Test
    void TestWhereSubQuery() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .innerJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.userId().equalTo(mainTable.id())))
                .column(table -> table)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().likeSubQuery(UserRoleDTO.Helper.class, (mt, query) -> query
                                .column(UserRoleDTO.Helper.Column::id)
                                .where((ct, m) -> ct
                                        .and(m.roleName().equalTo(arg())))
                                .limitOne()
                                .table())))
                .query();
        arg(0);
        arg(1);
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`user_id` = SysUser.`id` where SysUser.`user_name` like (select UserRole.`id` `id` from `user_role` UserRole where UserRole.`role_name` = ? limit ?,?)");

        sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .innerJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.userId().equalTo(mainTable.id())))
                .column(table -> table)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().likeSubQuery(UserRoleDTO.Helper.class, (mt, query) -> query
                                .column(UserRoleDTO.Helper.Column::id)
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
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.loginName().sqlPart("=NOW()")))
                .query();
        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser where SysUser.`login_name` =NOW()");
    }

}
