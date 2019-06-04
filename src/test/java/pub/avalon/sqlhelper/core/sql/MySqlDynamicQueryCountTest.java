package pub.avalon.sqlhelper.core.sql;

import org.junit.jupiter.api.Test;
import pub.avalon.beans.DataBaseType;
import pub.avalon.beans.Pagination;
import pub.avalon.sqlhelper.AbstractTest;
import pub.avalon.sqlhelper.core.sqlbuilder.SqlBuilder;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalon.sqlhelper.readme.entity.RoleResource;
import pub.avalon.sqlhelper.readme.entity.SysUser;
import pub.avalon.sqlhelper.readme.entity.UserRole;

/**
 * MySql动态引擎 - 查询 - 总数查询
 */
public class MySqlDynamicQueryCountTest extends AbstractTest {

    @Test
    void TestWhere() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUser.Helper.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.loginName().equalTo(arg())))
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUser where SysUser.`login_name` = ?");

        sqlBuilder = MySqlDynamicEngine.table(SysUser.Helper.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())
                                .loginName().equalTo(arg())))
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUser where SysUser.`user_name` = ? and SysUser.`login_name` = ?");

        sqlBuilder = MySqlDynamicEngine.table(SysUser.Helper.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.loginName().equalTo(arg())
                                .userName().equalTo(arg()))
                        .or(mainTable.id().greaterThan(arg())))
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUser where SysUser.`login_name` = ? and SysUser.`user_name` = ? or SysUser.`id` > ?");

        sqlBuilder = MySqlDynamicEngine.table(SysUser.Helper.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())
                                .loginName().equalTo(arg()))
                        .or(mainTable.userName().greaterThan(arg())
                                .id().between(arg(), arg()))
                        .and(mainTable.loginName().like(arg())))
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUser where SysUser.`user_name` = ? and SysUser.`login_name` = ? or (SysUser.`user_name` > ? and SysUser.`id` between ? and ?) and SysUser.`login_name` like ?");

        sqlBuilder = MySqlDynamicEngine.table(SysUser.Helper.class)
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
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUser where (SysUser.`user_name` != ? or SysUser.`login_name` > ?) or (SysUser.`id` > ? and SysUser.`user_name` between ? and ?) and (SysUser.`user_name` = ? or SysUser.`login_name` = ?)");

        sqlBuilder = MySqlDynamicEngine.table(SysUser.Helper.class)
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
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUser where ((SysUser.`user_name` != ? or SysUser.`login_name` > ?) or (SysUser.`id` > ? and SysUser.`id` between ? and ?) and (SysUser.`user_name` = ? or SysUser.`login_name` = ?)) and (SysUser.`login_name` >= ? or SysUser.`user_name` <= ?)");
    }

    @Test
    void TestJoin() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUser.Helper.class)
                .innerJoin(UserRole.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id`");

        sqlBuilder = MySqlDynamicEngine.table(SysUser.Helper.class)
                .innerJoin(UserRole.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id()).roleName().like(arg())))
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` and UserRole.`role_name` like ?");

        sqlBuilder = MySqlDynamicEngine.table(SysUser.Helper.class)
                .innerJoin(RoleResource.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id()).resourceName().equalTo(arg())))
                .leftJoin(RoleResource.Helper.class, "RR", (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUser inner join `role_resource` RoleResource on RoleResource.`role_id` = SysUser.`id` and RoleResource.`resource_name` = ? left join `role_resource` RR on RR.`role_id` = SysUser.`id`");

        sqlBuilder = MySqlDynamicEngine.table(SysUser.Helper.class)
                .innerJoin(RoleResource.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id()).resourceName().equalTo(arg())))
                .leftJoin(UserRole.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .rightJoin(RoleResource.Helper.class, "CC", (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUser inner join `role_resource` RoleResource on RoleResource.`role_id` = SysUser.`id` and RoleResource.`resource_name` = ? left join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` right join `role_resource` CC on CC.`role_id` = SysUser.`id`");
    }

    @Test
    void TestJoinAndWhere() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUser.Helper.class)
                .innerJoin(UserRole.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .where(UserRole.Helper.class, (condition, table, mainTable) -> condition
                        .and(table.roleName().like(arg())))
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` where UserRole.`role_name` like ?");

        sqlBuilder = MySqlDynamicEngine.table(SysUser.Helper.class)
                .innerJoin(UserRole.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())
                                .roleName().equalTo(arg())))
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().notEqualTo(arg()))
                        .or(mainTable.loginName().equalTo(arg())))
                .where(UserRole.Helper.class, (condition, table, mainTable) -> condition
                        .and(table.roleName().equalTo(arg())))
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` and UserRole.`role_name` = ? where (SysUser.`user_name` != ? or SysUser.`login_name` = ?) and UserRole.`role_name` = ?");

        sqlBuilder = MySqlDynamicEngine.table(SysUser.Helper.class)
                .innerJoin(UserRole.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id()).roleName().equalTo(arg())))
                .leftJoin(RoleResource.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(UserRole.Helper.class, UserRole.Helper.Column::roleId)))
                .where(RoleResource.Helper.class, (condition, table, mainTable) -> condition
                        .and(table.resourceName().like(arg())))
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` and UserRole.`role_name` = ? left join `role_resource` RoleResource on RoleResource.`role_id` = UserRole.`role_id` where RoleResource.`resource_name` like ?");

        sqlBuilder = MySqlDynamicEngine.table(SysUser.Helper.class)
                .innerJoin(UserRole.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id()).roleName().equalTo(arg())))
                .rightJoin(UserRole.Helper.class, "UR", (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .leftJoin(RoleResource.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())
                                .roleId().lessThan(UserRole.Helper.class, "UR", UserRole.Helper.Column::roleId)))
                .where(UserRole.Helper.class, "UR", (condition, table, mainTable) -> condition
                        .and(table.roleName().equalTo(arg())))
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` and UserRole.`role_name` = ? right join `user_role` UR on UR.`role_id` = SysUser.`id` left join `role_resource` RoleResource on RoleResource.`role_id` = SysUser.`id` and RoleResource.`role_id` < UR.`role_id` where UR.`role_name` = ?");
    }

    @Test
    void TestGroup() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUser.Helper.class)
                .group(SysUser.Helper.Group::id)
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from (select SysUser.`id` from `sys_user` SysUser group by SysUser.`id`) C");

        sqlBuilder = MySqlDynamicEngine.table(SysUser.Helper.class)
                .group(table -> table.id().userName())
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from (select SysUser.`id` from `sys_user` SysUser group by SysUser.`id`,SysUser.`user_name`) C");
    }

    @Test
    void TestGroupAndJoinAndWhere() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUser.Helper.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())))
                .group(table -> table.id().loginName())
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from (select SysUser.`id` from `sys_user` SysUser where SysUser.`user_name` = ? group by SysUser.`id`,SysUser.`login_name`) C");

        sqlBuilder = MySqlDynamicEngine.table(SysUser.Helper.class)
                .innerJoin(UserRole.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())))
                .group(table -> table.id().id())
                .group(UserRole.Helper.class, UserRole.Helper.Group::roleId)
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from (select SysUser.`id` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` where SysUser.`user_name` = ? group by SysUser.`id`,UserRole.`role_id`) C");
    }

    @Test
    void TestSort() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUser.Helper.class)
                .sort(table -> table.id().asc())
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUser");

        sqlBuilder = MySqlDynamicEngine.table(SysUser.Helper.class)
                .sort(table -> table.id().asc().userName().desc())
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUser");
    }

    @Test
    void TestSortAndJoinAndWhere() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUser.Helper.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())))
                .sort(table -> table.id().desc())
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUser where SysUser.`user_name` = ?");

        sqlBuilder = MySqlDynamicEngine.table(SysUser.Helper.class)
                .innerJoin(UserRole.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())))
                .sort(UserRole.Helper.class, table -> table.roleId().desc())
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` where SysUser.`user_name` = ?");
    }

    @Test
    void TestLimit() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUser.Helper.class)
                .limit(arg(0), arg(10))
                .queryCount();
        setSqlBuilder(sqlBuilder, "select count(1) from (select SysUser.`id` from `sys_user` SysUser limit ?,?) C");

        sqlBuilder = MySqlDynamicEngine.table(SysUser.Helper.class)
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
