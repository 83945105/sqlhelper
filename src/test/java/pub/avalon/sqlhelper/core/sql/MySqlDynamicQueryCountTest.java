package pub.avalon.sqlhelper.core.sql;

import org.junit.jupiter.api.Test;
import pub.avalon.beans.DataBaseType;
import pub.avalon.beans.Pagination;
import pub.avalon.sqlhelper.AbstractTest;
import pub.avalon.sqlhelper.core.build.SqlBuilder;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalon.sqlhelper.readme.model.RoleResourceModel;
import pub.avalon.sqlhelper.readme.model.SysUserModel;
import pub.avalon.sqlhelper.readme.model.UserRoleModel;

/**
 * MySql动态引擎 - 查询 - 总数查询
 */
public class MySqlDynamicQueryCountTest extends AbstractTest {

    @Test
    void TestWhere() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.loginName().equalTo(arg())))
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUser where SysUser.`login_name` = ?");

        sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())
                                .loginName().equalTo(arg())))
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUser where SysUser.`user_name` = ? and SysUser.`login_name` = ?");

        sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.loginName().equalTo(arg())
                                .userName().equalTo(arg()))
                        .or(mainTable.id().greaterThan(arg())))
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUser where SysUser.`login_name` = ? and SysUser.`user_name` = ? or SysUser.`id` > ?");

        sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())
                                .loginName().equalTo(arg()))
                        .or(mainTable.userName().greaterThan(arg())
                                .id().between(arg(), arg()))
                        .and(mainTable.loginName().like(arg())))
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUser where SysUser.`user_name` = ? and SysUser.`login_name` = ? or (SysUser.`user_name` > ? and SysUser.`id` between ? and ?) and SysUser.`login_name` like ?");

        sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .where((condition, mainTable) -> condition
                        .and((ct, mt) -> ct
                                .and(mt.userName().notEqualTo(arg()))
                                .or(mt.loginName().greaterThan(arg())))
                        .or(mainTable.id().greaterThan(arg())
                                .userName().between(arg(), arg()))
                        .and((ct, mt) -> ct
                                .and(mt.userName().equalTo(arg()))
                                .or(mt.loginName().equalTo(arg()))))
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUser where (SysUser.`user_name` != ? or SysUser.`login_name` > ?) or (SysUser.`id` > ? and SysUser.`user_name` between ? and ?) and (SysUser.`user_name` = ? or SysUser.`login_name` = ?)");

        sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .where((condition, mainTable) -> condition
                        .and((cd, mt) -> cd
                                .and(mt.userName().notEqualTo(arg()))
                                .or(mt.loginName().greaterThan(arg())))
                        .or(mainTable.id().greaterThan(arg())
                                .id().between(arg(), arg()))
                        .and((cd, mt) -> cd
                                .and(mt.userName().equalTo(arg()))
                                .or(mt.loginName().equalTo(arg()))))
                .where((condition, mainTable) -> condition
                        .and((cd, mt) -> cd
                                .and(mt.loginName().greaterThanAndEqualTo(arg()))
                                .or(mt.userName().lessThanAndEqualTo(arg()))))
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUser where ((SysUser.`user_name` != ? or SysUser.`login_name` > ?) or (SysUser.`id` > ? and SysUser.`id` between ? and ?) and (SysUser.`user_name` = ? or SysUser.`login_name` = ?)) and (SysUser.`login_name` >= ? or SysUser.`user_name` <= ?)");
    }

    @Test
    void TestJoin() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .innerJoin(UserRoleModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id`");

        sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .innerJoin(UserRoleModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id()).roleName().like(arg())))
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` and UserRole.`role_name` like ?");

        sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .innerJoin(RoleResourceModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id()).resourceName().equalTo(arg())))
                .leftJoin(RoleResourceModel.class, "RR", (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUser inner join `role_resource` RoleResource on RoleResource.`role_id` = SysUser.`id` and RoleResource.`resource_name` = ? left join `role_resource` RR on RR.`role_id` = SysUser.`id`");

        sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .innerJoin(RoleResourceModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id()).resourceName().equalTo(arg())))
                .leftJoin(UserRoleModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .rightJoin(RoleResourceModel.class, "CC", (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUser inner join `role_resource` RoleResource on RoleResource.`role_id` = SysUser.`id` and RoleResource.`resource_name` = ? left join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` right join `role_resource` CC on CC.`role_id` = SysUser.`id`");
    }

    @Test
    void TestJoinAndWhere() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .innerJoin(UserRoleModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .where(UserRoleModel.class, (condition, table, mainTable) -> condition
                        .and(table.roleName().like(arg())))
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` where UserRole.`role_name` like ?");

        sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .innerJoin(UserRoleModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())
                                .roleName().equalTo(arg())))
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().notEqualTo(arg()))
                        .or(mainTable.loginName().equalTo(arg())))
                .where(UserRoleModel.class, (condition, table, mainTable) -> condition
                        .and(table.roleName().equalTo(arg())))
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` and UserRole.`role_name` = ? where (SysUser.`user_name` != ? or SysUser.`login_name` = ?) and UserRole.`role_name` = ?");

        sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .innerJoin(UserRoleModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id()).roleName().equalTo(arg())))
                .leftJoin(RoleResourceModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(UserRoleModel.class, UserRoleModel.On::roleId)))
                .where(RoleResourceModel.class, (condition, table, mainTable) -> condition
                        .and(table.resourceName().like(arg())))
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` and UserRole.`role_name` = ? left join `role_resource` RoleResource on RoleResource.`role_id` = UserRole.`role_id` where RoleResource.`resource_name` like ?");

        sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .innerJoin(UserRoleModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id()).roleName().equalTo(arg())))
                .rightJoin(UserRoleModel.class, "UR", (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .leftJoin(RoleResourceModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())
                                .roleId().lessThan(UserRoleModel.class, "UR", UserRoleModel.On::roleId)))
                .where(UserRoleModel.class, "UR", (condition, table, mainTable) -> condition
                        .and(table.roleName().equalTo(arg())))
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` and UserRole.`role_name` = ? right join `user_role` UR on UR.`role_id` = SysUser.`id` left join `role_resource` RoleResource on RoleResource.`role_id` = SysUser.`id` and RoleResource.`role_id` < UR.`role_id` where UR.`role_name` = ?");
    }

    @Test
    void TestGroup() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .group(SysUserModel.Group::id)
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from (select SysUser.`id` from `sys_user` SysUser group by SysUser.id) C");

        sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .group(table -> table.id().userName())
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from (select SysUser.`id` from `sys_user` SysUser group by SysUser.id,SysUser.user_name) C");
    }

    @Test
    void TestGroupAndJoinAndWhere() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())))
                .group(table -> table.id().loginName())
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from (select SysUser.`id` from `sys_user` SysUser where SysUser.`user_name` = ? group by SysUser.id,SysUser.login_name) C");

        sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .innerJoin(UserRoleModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())))
                .group(table -> table.id().id())
                .group(UserRoleModel.class, UserRoleModel.Group::roleId)
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from (select SysUser.`id` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` where SysUser.`user_name` = ? group by SysUser.id,SysUser.id,UserRole.role_id) C");
    }

    @Test
    void TestSort() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .sort(table -> table.id().asc())
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUser");

        sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .sort(table -> table.id().asc().userName().desc())
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUser");
    }

    @Test
    void TestSortAndJoinAndWhere() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())))
                .sort(table -> table.id().desc())
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUser where SysUser.`user_name` = ?");

        sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .innerJoin(UserRoleModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())))
                .sort(UserRoleModel.class, table -> table.roleId().desc())
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` where SysUser.`user_name` = ?");
    }

    @Test
    void TestLimit() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .limit(arg(0), arg(10))
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from (select SysUser.`id` from `sys_user` SysUser limit ?,?) C");

        sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .limit(200, 10, 20)
                .queryCount();
        Pagination pagination = new Pagination(DataBaseType.MYSQL);
        pagination.setTotal(200);
        pagination.setCurrentPage(10);
        pagination.setPageSize(20);
        arg(pagination.getLimitStart());
        arg(pagination.getLimitEnd());
        setSqlBuilder(sqlBuilder, "select count(1) from (select SysUser.`id` from `sys_user` SysUser limit ?,?) C");
    }
}
