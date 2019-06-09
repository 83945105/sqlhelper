package pub.avalon.sqlhelper.core.sql;

import org.junit.jupiter.api.Test;
import pub.avalon.beans.DataBaseType;
import pub.avalon.beans.Pagination;
import pub.avalon.sqlhelper.AbstractTest;
import pub.avalon.sqlhelper.core.sqlbuilder.SqlBuilder;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalon.sqlhelper.readme.entity.RoleResourceDTO;
import pub.avalon.sqlhelper.readme.entity.SysUserDTO;
import pub.avalon.sqlhelper.readme.entity.UserRoleDTO;

/**
 * MySql动态引擎 - 查询 - 总数查询
 */
public class MySqlDynamicQueryCountTest extends AbstractTest {

    @Test
    void TestWhere() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.loginName().equalTo(arg())))
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUserDTO where SysUserDTO.`login_name` = ?");

        sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())
                                .loginName().equalTo(arg())))
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUserDTO where SysUserDTO.`user_name` = ? and SysUserDTO.`login_name` = ?");

        sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.loginName().equalTo(arg())
                                .userName().equalTo(arg()))
                        .or(mainTable.id().greaterThan(arg())))
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUserDTO where SysUserDTO.`login_name` = ? and SysUserDTO.`user_name` = ? or SysUserDTO.`id` > ?");

        sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())
                                .loginName().equalTo(arg()))
                        .or(mainTable.userName().greaterThan(arg())
                                .id().between(arg(), arg()))
                        .and(mainTable.loginName().like(arg())))
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUserDTO where SysUserDTO.`user_name` = ? and SysUserDTO.`login_name` = ? or (SysUserDTO.`user_name` > ? and SysUserDTO.`id` between ? and ?) and SysUserDTO.`login_name` like ?");

        sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .where((condition, mainTable) -> condition
                        .and(cd -> cd
                                .and(mainTable.userName().notEqualTo(arg()))
                                .or(mainTable.loginName().greaterThan(arg())))
                        .or(mainTable.id().greaterThan(arg())
                                .userName().between(arg(), arg()))
                        .and(cd -> cd
                                .and(mainTable.userName().equalTo(arg()))
                                .or(mainTable.loginName().equalTo(arg()))))
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUserDTO where (SysUserDTO.`user_name` != ? or SysUserDTO.`login_name` > ?) or (SysUserDTO.`id` > ? and SysUserDTO.`user_name` between ? and ?) and (SysUserDTO.`user_name` = ? or SysUserDTO.`login_name` = ?)");

        sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .where((condition, mainTable) -> condition
                        .and(cd -> cd
                                .and(mainTable.userName().notEqualTo(arg()))
                                .or(mainTable.loginName().greaterThan(arg())))
                        .or(mainTable.id().greaterThan(arg())
                                .id().between(arg(), arg()))
                        .and(cd -> cd
                                .and(mainTable.userName().equalTo(arg()))
                                .or(mainTable.loginName().equalTo(arg()))))
                .where((condition, mainTable) -> condition
                        .and(cd -> cd
                                .and(mainTable.loginName().greaterThanAndEqualTo(arg()))
                                .or(mainTable.userName().lessThanAndEqualTo(arg()))))
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUserDTO where ((SysUserDTO.`user_name` != ? or SysUserDTO.`login_name` > ?) or (SysUserDTO.`id` > ? and SysUserDTO.`id` between ? and ?) and (SysUserDTO.`user_name` = ? or SysUserDTO.`login_name` = ?)) and (SysUserDTO.`login_name` >= ? or SysUserDTO.`user_name` <= ?)");
    }

    @Test
    void TestJoin() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .innerJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUserDTO inner join `user_role` UserRoleDTO on UserRoleDTO.`role_id` = SysUserDTO.`id`");

        sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .innerJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id()).roleName().like(arg())))
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUserDTO inner join `user_role` UserRoleDTO on UserRoleDTO.`role_id` = SysUserDTO.`id` and UserRoleDTO.`role_name` like ?");

        sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .innerJoin(RoleResourceDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id()).resourceName().equalTo(arg())))
                .leftJoin(RoleResourceDTO.Helper.class, "RR", (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUserDTO inner join `role_resource` RoleResourceDTO on RoleResourceDTO.`role_id` = SysUserDTO.`id` and RoleResourceDTO.`resource_name` = ? left join `role_resource` RR on RR.`role_id` = SysUserDTO.`id`");

        sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .innerJoin(RoleResourceDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id()).resourceName().equalTo(arg())))
                .leftJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .rightJoin(RoleResourceDTO.Helper.class, "CC", (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUserDTO inner join `role_resource` RoleResourceDTO on RoleResourceDTO.`role_id` = SysUserDTO.`id` and RoleResourceDTO.`resource_name` = ? left join `user_role` UserRoleDTO on UserRoleDTO.`role_id` = SysUserDTO.`id` right join `role_resource` CC on CC.`role_id` = SysUserDTO.`id`");
    }

    @Test
    void TestJoinAndWhere() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .innerJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .where(UserRoleDTO.Helper.class, (condition, table, mainTable) -> condition
                        .and(table.roleName().like(arg())))
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUserDTO inner join `user_role` UserRoleDTO on UserRoleDTO.`role_id` = SysUserDTO.`id` where UserRoleDTO.`role_name` like ?");

        sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .innerJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())
                                .roleName().equalTo(arg())))
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().notEqualTo(arg()))
                        .or(mainTable.loginName().equalTo(arg())))
                .where(UserRoleDTO.Helper.class, (condition, table, mainTable) -> condition
                        .and(table.roleName().equalTo(arg())))
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUserDTO inner join `user_role` UserRoleDTO on UserRoleDTO.`role_id` = SysUserDTO.`id` and UserRoleDTO.`role_name` = ? where (SysUserDTO.`user_name` != ? or SysUserDTO.`login_name` = ?) and UserRoleDTO.`role_name` = ?");

        sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .innerJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id()).roleName().equalTo(arg())))
                .leftJoin(RoleResourceDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(UserRoleDTO.Helper.class, UserRoleDTO.Helper.Column::roleId)))
                .where(RoleResourceDTO.Helper.class, (condition, table, mainTable) -> condition
                        .and(table.resourceName().like(arg())))
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUserDTO inner join `user_role` UserRoleDTO on UserRoleDTO.`role_id` = SysUserDTO.`id` and UserRoleDTO.`role_name` = ? left join `role_resource` RoleResourceDTO on RoleResourceDTO.`role_id` = UserRoleDTO.`role_id` where RoleResourceDTO.`resource_name` like ?");

        sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
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
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUserDTO inner join `user_role` UserRoleDTO on UserRoleDTO.`role_id` = SysUserDTO.`id` and UserRoleDTO.`role_name` = ? right join `user_role` UR on UR.`role_id` = SysUserDTO.`id` left join `role_resource` RoleResourceDTO on RoleResourceDTO.`role_id` = SysUserDTO.`id` and RoleResourceDTO.`role_id` < UR.`role_id` where UR.`role_name` = ?");
    }

    @Test
    void TestGroup() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .group(SysUserDTO.Helper.Group::id)
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from (select SysUserDTO.`id` from `sys_user` SysUserDTO group by SysUserDTO.`id`) C");

        sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .group(table -> table.id().userName())
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from (select SysUserDTO.`id` from `sys_user` SysUserDTO group by SysUserDTO.`id`,SysUserDTO.`user_name`) C");
    }

    @Test
    void TestGroupAndJoinAndWhere() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())))
                .group(table -> table.id().loginName())
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from (select SysUserDTO.`id` from `sys_user` SysUserDTO where SysUserDTO.`user_name` = ? group by SysUserDTO.`id`,SysUserDTO.`login_name`) C");

        sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .innerJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())))
                .group(table -> table.id().id())
                .group(UserRoleDTO.Helper.class, UserRoleDTO.Helper.Group::roleId)
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from (select SysUserDTO.`id` from `sys_user` SysUserDTO inner join `user_role` UserRoleDTO on UserRoleDTO.`role_id` = SysUserDTO.`id` where SysUserDTO.`user_name` = ? group by SysUserDTO.`id`,UserRoleDTO.`role_id`) C");
    }

    @Test
    void TestSort() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .sort(table -> table.id().asc())
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUserDTO");

        sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .sort(table -> table.id().asc().userName().desc())
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUserDTO");
    }

    @Test
    void TestSortAndJoinAndWhere() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())))
                .sort(table -> table.id().desc())
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUserDTO where SysUserDTO.`user_name` = ?");

        sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .innerJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())))
                .sort(UserRoleDTO.Helper.class, table -> table.roleId().desc())
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUserDTO inner join `user_role` UserRoleDTO on UserRoleDTO.`role_id` = SysUserDTO.`id` where SysUserDTO.`user_name` = ?");
    }

    @Test
    void TestLimit() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .limit(arg(0), arg(10))
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from (select SysUserDTO.`id` from `sys_user` SysUserDTO limit ?,?) C");

        sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .limit(200, 10, 20)
                .queryCount();
        Pagination pagination = new Pagination(DataBaseType.MYSQL);
        pagination.setTotal(200);
        pagination.setCurrentPage(10);
        pagination.setPageSize(20);
        arg(pagination.getLimitStart());
        arg(pagination.getLimitEnd());
        setSqlBuilder(sqlBuilder, "select count(1) from (select SysUserDTO.`id` from `sys_user` SysUserDTO limit ?,?) C");
    }
}
