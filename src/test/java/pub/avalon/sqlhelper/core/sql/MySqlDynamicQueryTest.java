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
 * MySql动态引擎 - 查询 - 条件查询
 */
public class MySqlDynamicQueryTest extends AbstractTest {

    @Test
    void TestWhere() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())))
                .query();
        setSqlBuilder(sqlBuilder, "select SysUserDTO.`id` `id`,SysUserDTO.`user_name` `userName`,SysUserDTO.`login_name` `loginName` from `sys_user` SysUserDTO where SysUserDTO.`user_name` = ?");

        sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())
                                .loginName().equalTo(arg())))
                .query();
        setSqlBuilder(sqlBuilder, "select SysUserDTO.`id` `id`,SysUserDTO.`user_name` `userName`,SysUserDTO.`login_name` `loginName` from `sys_user` SysUserDTO where SysUserDTO.`user_name` = ? and SysUserDTO.`login_name` = ?");

        sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())
                                .loginName().equalTo(arg()))
                        .or(mainTable.id().greaterThan(arg())))
                .query();
        setSqlBuilder(sqlBuilder, "select SysUserDTO.`id` `id`,SysUserDTO.`user_name` `userName`,SysUserDTO.`login_name` `loginName` from `sys_user` SysUserDTO where SysUserDTO.`user_name` = ? and SysUserDTO.`login_name` = ? or SysUserDTO.`id` > ?");

        sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())
                                .loginName().equalTo(arg()))
                        .or(mainTable.id().greaterThan(arg())
                                .loginName().between(arg(), arg()))
                        .and(mainTable.loginName().like(arg())))
                .query();
        setSqlBuilder(sqlBuilder, "select SysUserDTO.`id` `id`,SysUserDTO.`user_name` `userName`,SysUserDTO.`login_name` `loginName` from `sys_user` SysUserDTO where SysUserDTO.`user_name` = ? and SysUserDTO.`login_name` = ? or (SysUserDTO.`id` > ? and SysUserDTO.`login_name` between ? and ?) and SysUserDTO.`login_name` like ?");

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
                .query();
        setSqlBuilder(sqlBuilder, "select SysUserDTO.`id` `id`,SysUserDTO.`user_name` `userName`,SysUserDTO.`login_name` `loginName` from `sys_user` SysUserDTO where (SysUserDTO.`user_name` != ? or SysUserDTO.`login_name` > ?) or (SysUserDTO.`id` > ? and SysUserDTO.`user_name` between ? and ?) and (SysUserDTO.`user_name` = ? or SysUserDTO.`login_name` = ?)");

        sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
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
        setSqlBuilder(sqlBuilder, "select SysUserDTO.`id` `id`,SysUserDTO.`user_name` `userName`,SysUserDTO.`login_name` `loginName` from `sys_user` SysUserDTO where ((SysUserDTO.`user_name` != ? or SysUserDTO.`user_name` > ?) or (SysUserDTO.`id` > ? and SysUserDTO.`user_name` between ? and ?) and (SysUserDTO.`user_name` = ? or SysUserDTO.`login_name` = ?)) and (SysUserDTO.`user_name` >= ? or SysUserDTO.`login_name` <= ?)");
    }

    @Test
    void TestJoin() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .innerJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .query();
        setSqlBuilder(sqlBuilder, "select SysUserDTO.`id` `id`,SysUserDTO.`user_name` `userName`,SysUserDTO.`login_name` `loginName` from `sys_user` SysUserDTO inner join `user_role` UserRoleDTO on UserRoleDTO.`role_id` = SysUserDTO.`id`");

        sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .innerJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id()).roleName().equalTo(arg())))
                .query();
        setSqlBuilder(sqlBuilder, "select SysUserDTO.`id` `id`,SysUserDTO.`user_name` `userName`,SysUserDTO.`login_name` `loginName` from `sys_user` SysUserDTO inner join `user_role` UserRoleDTO on UserRoleDTO.`role_id` = SysUserDTO.`id` and UserRoleDTO.`role_name` = ?");

        sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .innerJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id()).roleName().equalTo(arg())))
                .leftJoin(RoleResourceDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .query();
        setSqlBuilder(sqlBuilder, "select SysUserDTO.`id` `id`,SysUserDTO.`user_name` `userName`,SysUserDTO.`login_name` `loginName` from `sys_user` SysUserDTO inner join `user_role` UserRoleDTO on UserRoleDTO.`role_id` = SysUserDTO.`id` and UserRoleDTO.`role_name` = ? left join `role_resource` RoleResourceDTO on RoleResourceDTO.`role_id` = SysUserDTO.`id`");

        sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .innerJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id()).roleName().equalTo(arg())))
                .leftJoin(RoleResourceDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .rightJoin(UserRoleDTO.Helper.class, "UR", (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .query();
        setSqlBuilder(sqlBuilder, "select SysUserDTO.`id` `id`,SysUserDTO.`user_name` `userName`,SysUserDTO.`login_name` `loginName` from `sys_user` SysUserDTO inner join `user_role` UserRoleDTO on UserRoleDTO.`role_id` = SysUserDTO.`id` and UserRoleDTO.`role_name` = ? left join `role_resource` RoleResourceDTO on RoleResourceDTO.`role_id` = SysUserDTO.`id` right join `user_role` UR on UR.`role_id` = SysUserDTO.`id`");
    }

    @Test
    void TestJoinAndWhere() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .innerJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .where(UserRoleDTO.Helper.class, (condition, table, mainTable) -> condition
                        .and(table.roleName().like(arg())))
                .query();
        setSqlBuilder(sqlBuilder, "select SysUserDTO.`id` `id`,SysUserDTO.`user_name` `userName`,SysUserDTO.`login_name` `loginName` from `sys_user` SysUserDTO inner join `user_role` UserRoleDTO on UserRoleDTO.`role_id` = SysUserDTO.`id` where UserRoleDTO.`role_name` like ?");

        sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .innerJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())
                                .roleName().equalTo(arg())))
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().notEqualTo(arg()))
                        .or(mainTable.loginName().equalTo(arg())))
                .where(UserRoleDTO.Helper.class, (condition, table, mainTable) -> condition
                        .and(table.roleName().equalTo(arg())))
                .query();
        setSqlBuilder(sqlBuilder, "select SysUserDTO.`id` `id`,SysUserDTO.`user_name` `userName`,SysUserDTO.`login_name` `loginName` from `sys_user` SysUserDTO inner join `user_role` UserRoleDTO on UserRoleDTO.`role_id` = SysUserDTO.`id` and UserRoleDTO.`role_name` = ? where (SysUserDTO.`user_name` != ? or SysUserDTO.`login_name` = ?) and UserRoleDTO.`role_name` = ?");

        sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .innerJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id()).roleName().equalTo(arg())))
                .leftJoin(RoleResourceDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id()))
                        .and(joinTable.roleId().equalTo(UserRoleDTO.Helper.class, UserRoleDTO.Helper.Column::roleId)))
                .query();
        setSqlBuilder(sqlBuilder, "select SysUserDTO.`id` `id`,SysUserDTO.`user_name` `userName`,SysUserDTO.`login_name` `loginName` from `sys_user` SysUserDTO inner join `user_role` UserRoleDTO on UserRoleDTO.`role_id` = SysUserDTO.`id` and UserRoleDTO.`role_name` = ? left join `role_resource` RoleResourceDTO on RoleResourceDTO.`role_id` = SysUserDTO.`id` and RoleResourceDTO.`role_id` = UserRoleDTO.`role_id`");

        sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
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
        setSqlBuilder(sqlBuilder, "select SysUserDTO.`id` `id`,SysUserDTO.`user_name` `userName`,SysUserDTO.`login_name` `loginName` from `sys_user` SysUserDTO inner join `user_role` UserRoleDTO on UserRoleDTO.`role_id` = SysUserDTO.`id` and UserRoleDTO.`role_name` = ? right join `user_role` UR on UR.`role_id` = SysUserDTO.`id` left join `role_resource` RoleResourceDTO on RoleResourceDTO.`role_id` = SysUserDTO.`id` and RoleResourceDTO.`role_id` < UR.`role_id` where UR.`role_name` = ?");
    }

    @Test
    void TestJoinWhereAndOr() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
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
        setSqlBuilder(sqlBuilder, "select SysUserDTO.`id` `id`,SysUserDTO.`user_name` `userName`,SysUserDTO.`login_name` `loginName` from `sys_user` SysUserDTO inner join `user_role` UserRoleDTO on UserRoleDTO.`role_id` = SysUserDTO.`id` and UserRoleDTO.`role_name` = ? right join `user_role` UR on UR.`role_id` = SysUserDTO.`id` left join `role_resource` RoleResourceDTO on RoleResourceDTO.`role_id` = SysUserDTO.`id` and RoleResourceDTO.`role_id` < UR.`role_id` where (UR.`role_name` = ? or UR.`role_id` = ?) and UR.`sort_index` = ?");
    }

    @Test
    void TestGroup() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .group(SysUserDTO.Helper.Group::userName)
                .query();
        setSqlBuilder(sqlBuilder, "select SysUserDTO.`id` `id`,SysUserDTO.`user_name` `userName`,SysUserDTO.`login_name` `loginName` from `sys_user` SysUserDTO group by SysUserDTO.`user_name`");

        sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .group(table -> table.userName().id())
                .query();
        setSqlBuilder(sqlBuilder, "select SysUserDTO.`id` `id`,SysUserDTO.`user_name` `userName`,SysUserDTO.`login_name` `loginName` from `sys_user` SysUserDTO group by SysUserDTO.`user_name`,SysUserDTO.`id`");
    }

    @Test
    void TestGroupAndJoinAndWhere() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())))
                .group(table -> table.id().loginName())
                .query();
        setSqlBuilder(sqlBuilder, "select SysUserDTO.`id` `id`,SysUserDTO.`user_name` `userName`,SysUserDTO.`login_name` `loginName` from `sys_user` SysUserDTO where SysUserDTO.`user_name` = ? group by SysUserDTO.`id`,SysUserDTO.`login_name`");

        sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .innerJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())))
                .group(table -> table.userName().id())
                .group(UserRoleDTO.Helper.class, UserRoleDTO.Helper.Group::roleId)
                .query();
        setSqlBuilder(sqlBuilder, "select SysUserDTO.`id` `id`,SysUserDTO.`user_name` `userName`,SysUserDTO.`login_name` `loginName` from `sys_user` SysUserDTO inner join `user_role` UserRoleDTO on UserRoleDTO.`role_id` = SysUserDTO.`id` where SysUserDTO.`user_name` = ? group by SysUserDTO.`user_name`,SysUserDTO.`id`,UserRoleDTO.`role_id`");
    }

    @Test
    void TestSort() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .sort(table -> table.id().asc())
                .query();
        setSqlBuilder(sqlBuilder, "select SysUserDTO.`id` `id`,SysUserDTO.`user_name` `userName`,SysUserDTO.`login_name` `loginName` from `sys_user` SysUserDTO order by SysUserDTO.`id` asc");

        sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .sort(table -> table.id().asc().userName().desc())
                .query();
        setSqlBuilder(sqlBuilder, "select SysUserDTO.`id` `id`,SysUserDTO.`user_name` `userName`,SysUserDTO.`login_name` `loginName` from `sys_user` SysUserDTO order by SysUserDTO.`id` asc,SysUserDTO.`user_name` desc");

        sqlBuilder = MySqlDynamicEngine.table("sys_user_201903", SysUserDTO.Helper.class)
                .sort(table -> table.id().asc().userName().desc())
                .query();
        setSqlBuilder(sqlBuilder, "select SysUserDTO.`id` `id`,SysUserDTO.`user_name` `userName`,SysUserDTO.`login_name` `loginName` from `sys_user_201903` SysUserDTO order by SysUserDTO.`id` asc,SysUserDTO.`user_name` desc");

        sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .innerJoin(UserRoleDTO.Helper.class, "user_role_201903", (on, joinTable, mainTable) -> on
                        .and(joinTable.userId().equalTo(mainTable.id())))
                .sort(table -> table.id().asc().userName().desc())
                .sort(UserRoleDTO.Helper.class, "UserRoleAlias", table -> table.sortIndex().desc())
                .query();
        setSqlBuilder(sqlBuilder, "select SysUserDTO.`id` `id`,SysUserDTO.`user_name` `userName`,SysUserDTO.`login_name` `loginName` from `sys_user` SysUserDTO order by SysUserDTO.`id` asc,SysUserDTO.`user_name` desc");


    }

    @Test
    void TestSortAndJoinAndWhere() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())))
                .sort(table -> table.id().desc())
                .query();
        setSqlBuilder(sqlBuilder, "select SysUserDTO.`id` `id`,SysUserDTO.`user_name` `userName`,SysUserDTO.`login_name` `loginName` from `sys_user` SysUserDTO where SysUserDTO.`user_name` = ? order by SysUserDTO.`id` desc");

        sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .innerJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .where((condition, mainTable) -> condition
                        .and(mainTable.id().equalTo(arg())))
                .sort(UserRoleDTO.Helper.class, table -> table.id().desc())
                .query();
        setSqlBuilder(sqlBuilder, "select SysUserDTO.`id` `id`,SysUserDTO.`user_name` `userName`,SysUserDTO.`login_name` `loginName` from `sys_user` SysUserDTO inner join `user_role` UserRoleDTO on UserRoleDTO.`role_id` = SysUserDTO.`id` where SysUserDTO.`id` = ? order by UserRoleDTO.`id` desc");
    }

    @Test
    void TestLimit() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .limit(arg(0), arg(10))
                .query();
        setSqlBuilder(sqlBuilder, "select SysUserDTO.`id` `id`,SysUserDTO.`user_name` `userName`,SysUserDTO.`login_name` `loginName` from `sys_user` SysUserDTO limit ?,?");

        sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .limit(200, 10, 20)
                .query();
        Pagination pagination = new Pagination(DataBaseType.MYSQL, 200, 10, 20);
        arg(pagination.getLimitStart());
        arg(pagination.getLimitEnd());
        setSqlBuilder(sqlBuilder, "select SysUserDTO.`id` `id`,SysUserDTO.`user_name` `userName`,SysUserDTO.`login_name` `loginName` from `sys_user` SysUserDTO limit ?,?");
    }

    @Test
    void TestJoinOn() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .innerJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(o -> o
                                .and(joinTable.id().equalTo(mainTable.userName()))
                                .or(joinTable.roleName().equalTo(mainTable.primaryKey())))
                        .or(joinTable.id().equalTo(mainTable.loginName())))
                .query();
        setSqlBuilder(sqlBuilder, "select SysUserDTO.`id` `id`,SysUserDTO.`user_name` `userName`,SysUserDTO.`login_name` `loginName` from `sys_user` SysUserDTO inner join `user_role` UserRoleDTO on (UserRoleDTO.`id` = SysUserDTO.`user_name` or UserRoleDTO.`role_name` = SysUserDTO.`id`) or UserRoleDTO.`id` = SysUserDTO.`login_name`");

        sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
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
        setSqlBuilder(sqlBuilder, "select SysUserDTO.`id` `id`,SysUserDTO.`user_name` `userName`,SysUserDTO.`login_name` `loginName` from `sys_user` SysUserDTO inner join `user_role` UserRoleDTO on (UserRoleDTO.`id` = SysUserDTO.`user_name` and UserRoleDTO.`role_id` = ? or UserRoleDTO.`role_name` = SysUserDTO.`login_name` and UserRoleDTO.`id` = ? and UserRoleDTO.`role_name` = ?) or UserRoleDTO.`role_name` = ? or UserRoleDTO.`id` = SysUserDTO.`user_name`");
    }

    @Test
    void TestWhereJoin() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .innerJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(o -> o
                                .and(joinTable.id().equalTo(mainTable.userName()))
                                .or(joinTable.roleName().equalTo(mainTable.primaryKey())))
                        .or(joinTable.id().equalTo(mainTable.loginName())))
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(UserRoleDTO.Helper.class, UserRoleDTO.Helper.Column::roleName)))
                .query();
        setSqlBuilder(sqlBuilder, "select SysUserDTO.`id` `id`,SysUserDTO.`user_name` `userName`,SysUserDTO.`login_name` `loginName` from `sys_user` SysUserDTO inner join `user_role` UserRoleDTO on (UserRoleDTO.`id` = SysUserDTO.`user_name` or UserRoleDTO.`role_name` = SysUserDTO.`id`) or UserRoleDTO.`id` = SysUserDTO.`login_name` where SysUserDTO.`user_name` = UserRoleDTO.`role_name`");

        sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .innerJoin("user_role_20190413", UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(o -> o
                                .and(joinTable.id().equalTo(mainTable.userName()))
                                .or(joinTable.roleName().equalTo(mainTable.primaryKey())))
                        .or(joinTable.id().equalTo(mainTable.loginName())))
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(UserRoleDTO.Helper.class, UserRoleDTO.Helper.Column::id)))
                .query();
        setSqlBuilder(sqlBuilder, "select SysUserDTO.`id` `id`,SysUserDTO.`user_name` `userName`,SysUserDTO.`login_name` `loginName` from `sys_user` SysUserDTO inner join `user_role_20190413` UserRoleDTO on (UserRoleDTO.`id` = SysUserDTO.`user_name` or UserRoleDTO.`role_name` = SysUserDTO.`id`) or UserRoleDTO.`id` = SysUserDTO.`login_name` where SysUserDTO.`user_name` = UserRoleDTO.`id`");
    }

/*    @Test
    void TestSubQuery() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
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
        setSqlBuilder(sqlBuilder, "select (select AA.`id` `id` from `user_role` AA where AA.`role_id` = ? and AA.`role_name` = SysUserDTO.`user_name` and SysUserDTO.`id` = AA.`id` and AA.`sort_index` = UserRoleDTO.`id` limit ?,?) subQuery, SysUserDTO.`id` `id`,SysUserDTO.`user_name` `userName`,SysUserDTO.`login_name` `loginName` from `sys_user` SysUserDTO inner join `user_role` UserRoleDTO on UserRoleDTO.`user_id` = SysUserDTO.`id` where SysUserDTO.`user_name` = ?");
    }*/

/*    @Test
    void TestWhereSubQuery() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
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
        setSqlBuilder(sqlBuilder, "select SysUserDTO.`id` `id`,SysUserDTO.`user_name` `userName`,SysUserDTO.`login_name` `loginName` from `sys_user` SysUserDTO inner join `user_role` UserRoleDTO on UserRoleDTO.`user_id` = SysUserDTO.`id` where SysUserDTO.`user_name` like (select UserRoleDTO.`id` `id` from `user_role` UserRoleDTO where UserRoleDTO.`role_name` = ? limit ?,?)");

        sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
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
        setSqlBuilder(sqlBuilder, "select SysUserDTO.`id` `id`,SysUserDTO.`user_name` `userName`,SysUserDTO.`login_name` `loginName` from `sys_user` SysUserDTO inner join `user_role` UserRoleDTO on UserRoleDTO.`user_id` = SysUserDTO.`id` where SysUserDTO.`user_name` like (select UserRoleDTO.`id` `id` from `user_role` UserRoleDTO where UserRoleDTO.`user_id` between ? and ? limit ?,?)");
    }*/

    @Test
    void TestWhereSqlPart() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.loginName().sqlPart("=NOW()")))
                .query();
        setSqlBuilder(sqlBuilder, "select SysUserDTO.`id` `id`,SysUserDTO.`user_name` `userName`,SysUserDTO.`login_name` `loginName` from `sys_user` SysUserDTO where SysUserDTO.`login_name` =NOW()");
    }

}
