package pub.avalon.sqlhelper.core.sql;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pub.avalon.beans.DataBaseType;
import pub.avalon.beans.Pagination;
import pub.avalon.sqlhelper.AbstractTest;
import pub.avalon.sqlhelper.core.beans.ComparisonRule;
import pub.avalon.sqlhelper.core.beans.JoinType;
import pub.avalon.sqlhelper.core.sqlbuilder.SqlBuilder;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalon.sqlhelper.readme.entity.RoleResourceDTO;
import pub.avalon.sqlhelper.readme.entity.SysUserDTO;
import pub.avalon.sqlhelper.readme.entity.UserRoleDTO;

import java.util.Arrays;

/**
 * MySql动态引擎 - 查询 - 总数
 */
public class MySqlDynamicQueryCountTest {

    @Test
    void Test_count_join_allWhere() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .join(JoinType.INNER, UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id()).roleName().like("1")))
                .queryCount();
        Assertions.assertEquals("select count(1) from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` and UserRole.`role_name` like ?", sqlBuilder.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{"1"}, sqlBuilder.getPreparedStatementArgs().toArray());
    }
/*
    @Test
    void TestJoin03() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .innerJoin(RoleResourceDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id()).resourceName().equalTo(arg())))
                .leftJoin(RoleResourceDTO.Helper.class, "RR", (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUser inner join `role_resource` RoleResource on RoleResource.`role_id` = SysUser.`id` and RoleResource.`resource_name` = ? left join `role_resource` RR on RR.`role_id` = SysUser.`id`");
    }

    @Test
    void TestJoin04() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .innerJoin(RoleResourceDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id()).resourceName().equalTo(arg())))
                .leftJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .rightJoin(RoleResourceDTO.Helper.class, "CC", (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUser inner join `role_resource` RoleResource on RoleResource.`role_id` = SysUser.`id` and RoleResource.`resource_name` = ? left join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` right join `role_resource` CC on CC.`role_id` = SysUser.`id`");
    }

    @Test
    void TestJoinAndWhere01() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .innerJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .where(UserRoleDTO.Helper.class, (condition, table, mainTable) -> condition
                        .and(table.roleName().like(arg())))
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` where UserRole.`role_name` like ?");
    }

    @Test
    void TestJoinAndWhere02() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .innerJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())
                                .roleName().equalTo(arg())))
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().notEqualTo(arg()))
                        .or(mainTable.loginName().equalTo(arg())))
                .where(UserRoleDTO.Helper.class, (condition, table, mainTable) -> condition
                        .and(table.roleName().equalTo(arg())))
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` and UserRole.`role_name` = ? where (SysUser.`user_name` != ? or SysUser.`login_name` = ?) and UserRole.`role_name` = ?");
    }

    @Test
    void TestJoinAndWhere03() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .innerJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id()).roleName().equalTo(arg())))
                .leftJoin(RoleResourceDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(UserRoleDTO.Helper.class, UserRoleDTO.Helper.Column::roleId)))
                .where(RoleResourceDTO.Helper.class, (condition, table, mainTable) -> condition
                        .and(table.resourceName().like(arg())))
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` and UserRole.`role_name` = ? left join `role_resource` RoleResource on RoleResource.`role_id` = UserRole.`role_id` where RoleResource.`resource_name` like ?");
    }

    @Test
    void TestJoinAndWhere04() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .innerJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id()).roleName().equalTo(arg())))
                .rightJoin(UserRoleDTO.Helper.class, "UR", (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .leftJoin(RoleResourceDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())
                                .roleId().lessThan(UserRoleDTO.Helper.class, "UR", UserRoleDTO.Helper.Column::roleId)))
                .where(UserRoleDTO.Helper.class, "UR", (condition, table, mainTable) -> condition
                        .and(table.roleName().equalTo(arg())))
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` and UserRole.`role_name` = ? right join `user_role` UR on UR.`role_id` = SysUser.`id` left join `role_resource` RoleResource on RoleResource.`role_id` = SysUser.`id` and RoleResource.`role_id` < UR.`role_id` where UR.`role_name` = ?");
    }

    @Test
    void TestGroup01() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .group(SysUserDTO.Helper.Group::id)
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from (select SysUser.`id` from `sys_user` SysUser group by SysUser.`id`) C");
    }

    @Test
    void TestGroup02() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .group(table -> table.id().userName())
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from (select SysUser.`id` from `sys_user` SysUser group by SysUser.`id`,SysUser.`user_name`) C");
    }

    @Test
    void TestGroupAndJoinAndWhere01() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())))
                .group(table -> table.id().loginName())
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from (select SysUser.`id` from `sys_user` SysUser where SysUser.`user_name` = ? group by SysUser.`id`,SysUser.`login_name`) C");

    }

    @Test
    void TestGroupAndJoinAndWhere02() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .innerJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())))
                .group(table -> table.id().id())
                .group(UserRoleDTO.Helper.class, UserRoleDTO.Helper.Group::roleId)
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from (select SysUser.`id` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` where SysUser.`user_name` = ? group by SysUser.`id`,UserRole.`role_id`) C");
    }

    @Test
    void TestSort01() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .sort(table -> table.id().asc())
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUser");
    }

    @Test
    void TestSort02() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .sort(table -> table.id().asc().userName().desc())
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUser");
    }

    @Test
    void TestSortAndJoinAndWhere01() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())))
                .sort(table -> table.id().desc())
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUser where SysUser.`user_name` = ?");
    }

    @Test
    void TestSortAndJoinAndWhere02() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .innerJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())))
                .sort(UserRoleDTO.Helper.class, table -> table.roleId().desc())
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` where SysUser.`user_name` = ?");
    }

    @Test
    void TestLimit01() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .limit(200L, 10L, 20L)
                .queryCount();
        Pagination pagination = new Pagination(DataBaseType.MYSQL);
        pagination.setTotal(200L);
        pagination.setCurrentPage(10L);
        pagination.setPageSize(20L);
        arg(pagination.getLimitStartNum());
        arg(pagination.getLimitEndNum());
        setSqlBuilder(sqlBuilder, "select count(1) from (select SysUser.`id` from `sys_user` SysUser limit ?,?) C");
    }*/

}
