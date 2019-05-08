package pub.avalon.sqlhelper.core.sql;

import org.junit.jupiter.api.Test;
import pub.avalon.beans.DataBaseType;
import pub.avalon.beans.Pagination;
import pub.avalon.sqlhelper.AbstractTest;
import pub.avalon.sqlhelper.core.builder.SqlBuilder;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalon.sqlhelper.readme.model.RoleResourceModel;
import pub.avalon.sqlhelper.readme.model.SysUserModel;
import pub.avalon.sqlhelper.readme.model.UserRoleModel;

/**
 * MySql动态引擎 - 查询 - 条件查询
 */
public class MySqlDynamicQueryTest extends AbstractTest {

    @Test
    void TestWhere() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())))
                .query();
        setSqlBuilder(sqlBuilder, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser where SysUser.`user_name` = ?");

        sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())
                                .loginName().equalTo(arg())))
                .query();
        setSqlBuilder(sqlBuilder, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser where SysUser.`user_name` = ? and SysUser.`login_name` = ?");

        sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())
                                .loginName().equalTo(arg()))
                        .or(mainTable.id().greaterThan(arg())))
                .query();
        setSqlBuilder(sqlBuilder, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser where SysUser.`user_name` = ? and SysUser.`login_name` = ? or SysUser.`id` > ?");

        sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())
                                .loginName().equalTo(arg()))
                        .or(mainTable.id().greaterThan(arg())
                                .loginName().between(arg(), arg()))
                        .and(mainTable.loginName().like(arg())))
                .query();
        setSqlBuilder(sqlBuilder, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser where SysUser.`user_name` = ? and SysUser.`login_name` = ? or (SysUser.`id` > ? and SysUser.`login_name` between ? and ?) and SysUser.`login_name` like ?");

        sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .where((condition, mainTable) -> condition
                        .and((cd, mt) -> cd
                                .and(mt.userName().notEqualTo(arg()))
                                .or(mt.loginName().greaterThan(arg())))
                        .or(mainTable.id().greaterThan(arg())
                                .userName().between(arg(), arg()))
                        .and((cd, mt) -> cd
                                .and(mt.userName().equalTo(arg()))
                                .or(mt.loginName().equalTo(arg()))))
                .query();
        setSqlBuilder(sqlBuilder, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser where (SysUser.`user_name` != ? or SysUser.`login_name` > ?) or (SysUser.`id` > ? and SysUser.`user_name` between ? and ?) and (SysUser.`user_name` = ? or SysUser.`login_name` = ?)");

        sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .where((condition, mainTable) -> condition
                        .and((cd, mt) -> cd
                                .and(mt.userName().notEqualTo(arg()))
                                .or(mt.userName().greaterThan(arg())))
                        .or(mainTable.id().greaterThan(arg())
                                .userName().between(arg(), arg()))
                        .and((cd, mt) -> cd
                                .and(mt.userName().equalTo(arg()))
                                .or(mt.loginName().equalTo(arg()))))
                .where((condition, mainTable) -> condition
                        .and((cd, mt) -> cd
                                .and(mt.userName().greaterThanAndEqualTo(arg()))
                                .or(mt.loginName().lessThanAndEqualTo(arg()))))
                .query();
        setSqlBuilder(sqlBuilder, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser where ((SysUser.`user_name` != ? or SysUser.`user_name` > ?) or (SysUser.`id` > ? and SysUser.`user_name` between ? and ?) and (SysUser.`user_name` = ? or SysUser.`login_name` = ?)) and (SysUser.`user_name` >= ? or SysUser.`login_name` <= ?)");
    }

    @Test
    void TestJoin() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .innerJoin(UserRoleModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .query();
        setSqlBuilder(sqlBuilder, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id`");

        sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .innerJoin(UserRoleModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id()).roleName().equalTo(arg())))
                .query();
        setSqlBuilder(sqlBuilder, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` and UserRole.`role_name` = ?");

        sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .innerJoin(UserRoleModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id()).roleName().equalTo(arg())))
                .leftJoin(RoleResourceModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .query();
        setSqlBuilder(sqlBuilder, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` and UserRole.`role_name` = ? left join `role_resource` RoleResource on RoleResource.`role_id` = SysUser.`id`");

        sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .innerJoin(UserRoleModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id()).roleName().equalTo(arg())))
                .leftJoin(RoleResourceModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .rightJoin(UserRoleModel.class, "UR", (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .query();
        setSqlBuilder(sqlBuilder, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` and UserRole.`role_name` = ? left join `role_resource` RoleResource on RoleResource.`role_id` = SysUser.`id` right join `user_role` UR on UR.`role_id` = SysUser.`id`");
    }

    @Test
    void TestJoinAndWhere() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .innerJoin(UserRoleModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .where(UserRoleModel.class, (condition, table, mainTable) -> condition
                        .and(table.roleName().like(arg())))
                .query();
        setSqlBuilder(sqlBuilder, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` where UserRole.`role_name` like ?");

        sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .innerJoin(UserRoleModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())
                                .roleName().equalTo(arg())))
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().notEqualTo(arg()))
                        .or(mainTable.loginName().equalTo(arg())))
                .where(UserRoleModel.class, (condition, table, mainTable) -> condition
                        .and(table.roleName().equalTo(arg())))
                .query();
        setSqlBuilder(sqlBuilder, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` and UserRole.`role_name` = ? where (SysUser.`user_name` != ? or SysUser.`login_name` = ?) and UserRole.`role_name` = ?");

        sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .innerJoin(UserRoleModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id()).roleName().equalTo(arg())))
                .leftJoin(RoleResourceModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id()))
                        .and(joinTable.roleId().equalTo(UserRoleModel.class, UserRoleModel.On::roleId)))
                .query();
        setSqlBuilder(sqlBuilder, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` and UserRole.`role_name` = ? left join `role_resource` RoleResource on RoleResource.`role_id` = SysUser.`id` and RoleResource.`role_id` = UserRole.`role_id`");

        sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .innerJoin(UserRoleModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())
                                .roleName().equalTo(arg())))
                .rightJoin(UserRoleModel.class, "UR", (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .leftJoin(RoleResourceModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())
                                .roleId().lessThan(UserRoleModel.class, "UR", UserRoleModel.On::roleId)))
                .where(UserRoleModel.class, "UR", (condition, table, mainTable) -> condition
                        .and(table.roleName().equalTo(arg())))
                .query();
        setSqlBuilder(sqlBuilder, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` and UserRole.`role_name` = ? right join `user_role` UR on UR.`role_id` = SysUser.`id` left join `role_resource` RoleResource on RoleResource.`role_id` = SysUser.`id` and RoleResource.`role_id` < UR.`role_id` where UR.`role_name` = ?");
    }

    @Test
    void TestJoinWhereAndOr() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .innerJoin(UserRoleModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())
                                .roleName().equalTo(arg())))
                .rightJoin(UserRoleModel.class, "UR", (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .leftJoin(RoleResourceModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())
                                .roleId().lessThan(UserRoleModel.class, "UR", UserRoleModel.On::roleId)))
                .where(UserRoleModel.class, "UR", (condition, table, mainTable) -> condition
                        .and((cd, mt) -> cd
                                .and(table.roleName().equalTo(arg()))
                                .or(table.roleId().equalTo(arg())))
                        .and(table.sortIndex().equalTo(arg())))
                .query();
        setSqlBuilder(sqlBuilder, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` and UserRole.`role_name` = ? right join `user_role` UR on UR.`role_id` = SysUser.`id` left join `role_resource` RoleResource on RoleResource.`role_id` = SysUser.`id` and RoleResource.`role_id` < UR.`role_id` where (UR.`role_name` = ? or UR.`role_id` = ?) and UR.`sort_index` = ?");
    }

    @Test
    void TestGroup() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .group(SysUserModel.Group::userName)
                .query();
        setSqlBuilder(sqlBuilder, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser group by SysUser.`user_name`");

        sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .group(table -> table.userName().id())
                .query();
        setSqlBuilder(sqlBuilder, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser group by SysUser.`user_name`,SysUser.`id`");
    }

    @Test
    void TestGroupAndJoinAndWhere() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())))
                .group(table -> table.id().loginName())
                .query();
        setSqlBuilder(sqlBuilder, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser where SysUser.`user_name` = ? group by SysUser.`id`,SysUser.`login_name`");

        sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .innerJoin(UserRoleModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())))
                .group(table -> table.userName().id())
                .group(UserRoleModel.class, UserRoleModel.Group::roleId)
                .query();
        setSqlBuilder(sqlBuilder, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` where SysUser.`user_name` = ? group by SysUser.`user_name`,SysUser.`id`,UserRole.`role_id`");
    }

    @Test
    void TestSort() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .sort(table -> table.id().asc())
                .query();
        setSqlBuilder(sqlBuilder, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser order by SysUser.`id` asc");

        sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .sort(table -> table.id().asc().userName().desc())
                .query();
        setSqlBuilder(sqlBuilder, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser order by SysUser.`id` asc,SysUser.`user_name` desc");

        sqlBuilder = MySqlDynamicEngine.query("sys_user_201903", SysUserModel.class)
                .sort(table -> table.id().asc().userName().desc())
                .query();
        setSqlBuilder(sqlBuilder, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user_201903` SysUser order by SysUser.`id` asc,SysUser.`user_name` desc");

        sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .innerJoin(UserRoleModel.class, "user_role_201903", (on, joinTable, mainTable) -> on
                        .and(joinTable.userId().equalTo(mainTable.id())))
                .sort(table -> table.id().asc().userName().desc())
                .sort(UserRoleModel.class, "UserRoleAlias", table -> table.sortIndex().desc())
                .query();
        setSqlBuilder(sqlBuilder, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser order by SysUser.`id` asc,SysUser.`user_name` desc");


    }

    @Test
    void TestSortAndJoinAndWhere() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())))
                .sort(table -> table.id().desc())
                .query();
        setSqlBuilder(sqlBuilder, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser where SysUser.`user_name` = ? order by SysUser.`id` desc");

        sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .innerJoin(UserRoleModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .where((condition, mainTable) -> condition
                        .and(mainTable.id().equalTo(arg())))
                .sort(UserRoleModel.class, table -> table.id().desc())
                .query();
        setSqlBuilder(sqlBuilder, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` where SysUser.`id` = ? order by UserRole.`id` desc");
    }

    @Test
    void TestLimit() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .limit(arg(0), arg(10))
                .query();
        setSqlBuilder(sqlBuilder, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser limit ?,?");

        sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .limit(200, 10, 20)
                .query();
        Pagination pagination = new Pagination(DataBaseType.MYSQL, 200, 10, 20);
        arg(pagination.getLimitStart());
        arg(pagination.getLimitEnd());
        setSqlBuilder(sqlBuilder, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser limit ?,?");
    }

    @Test
    void TestJoinOn() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .innerJoin(UserRoleModel.class, (on, joinTable, mainTable) -> on
                        .and((o, jt, mt) -> on
                                .and(jt.id().equalTo(mt.userName()))
                                .or(jt.roleName().equalTo(mt.primaryKey())))
                        .or(joinTable.id().equalTo(mainTable.loginName())))
                .query();
        setSqlBuilder(sqlBuilder, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on (UserRole.`id` = SysUser.`user_name` or UserRole.`role_name` = SysUser.`id`) or UserRole.`id` = SysUser.`login_name`");

        sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .innerJoin(UserRoleModel.class, (on, joinTable, mainTable) -> on
                        .and((on1, jt1, mt1) -> on1
                                .and(jt1.id().equalTo(mt1.userName()).roleId().equalTo(arg()))
                                .or(jt1.roleName().equalTo(mt1.loginName()))
                                .and((on2, jt2, mt2) -> on2
                                        .and(jt2.id().equalTo(arg())
                                                .roleName().equalTo(arg()))))
                        .or(joinTable.roleName().equalTo(arg()))
                        .or(joinTable.id().equalTo(mainTable.userName())))
                .query();
        setSqlBuilder(sqlBuilder, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on (UserRole.`id` = SysUser.`user_name` and UserRole.`role_id` = ? or UserRole.`role_name` = SysUser.`login_name` and UserRole.`id` = ? and UserRole.`role_name` = ?) or UserRole.`role_name` = ? or UserRole.`id` = SysUser.`user_name`");
    }

    @Test
    void TestWhereJoin() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .innerJoin(UserRoleModel.class, (on, joinTable, mainTable) -> on
                        .and((o, jt, mt) -> on
                                .and(jt.id().equalTo(mt.userName()))
                                .or(jt.roleName().equalTo(mt.primaryKey())))
                        .or(joinTable.id().equalTo(mainTable.loginName())))
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(UserRoleModel.class, UserRoleModel.Column::roleName)))
                .query();
        setSqlBuilder(sqlBuilder, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on (UserRole.`id` = SysUser.`user_name` or UserRole.`role_name` = SysUser.`id`) or UserRole.`id` = SysUser.`login_name` where SysUser.`user_name` = UserRole.`role_name`");

        sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .innerJoin("user_role_20190413", UserRoleModel.class, (on, joinTable, mainTable) -> on
                        .and((o, jt, mt) -> on
                                .and(jt.id().equalTo(mt.userName()))
                                .or(jt.roleName().equalTo(mt.primaryKey())))
                        .or(joinTable.id().equalTo(mainTable.loginName())))
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(UserRoleModel.class, UserRoleModel.Column::id)))
                .query();
        setSqlBuilder(sqlBuilder, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role_20190413` UserRole on (UserRole.`id` = SysUser.`user_name` or UserRole.`role_name` = SysUser.`id`) or UserRole.`id` = SysUser.`login_name` where SysUser.`user_name` = UserRole.`id`");
    }

    //    @Test
    void TestSubQuery() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .innerJoin(UserRoleModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.userId().equalTo(mainTable.id())))
                .subQuery(UserRoleModel.class, "AA", (mainTable, query) -> query
                        .column(UserRoleModel.Column::id)
                        .where((cd, mt) -> cd
/*                                .and(mt.roleId().equalTo(arg())
                                        .roleName().equalTo(mainTable.userName()))
                                .and(mainTable.id().equalTo(mt.id()))*/
                                .and(mt.sortIndex().equalTo(UserRoleModel.class, UserRoleModel.Column::id)))
                        .limit(arg(1), arg(1)).query(), "subQuery")
                .column(table -> table)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())))
                .query();
//        setSqlBuilder(sqlBuilder, "select (select AA.`id` `id` from `user_role` AA where AA.`role_id` = ? and AA.`role_name` = SysUser.`user_name` and SysUser.`id` = AA.`id` and AA.`sort_index` = UserRole.`id` limit ?,?) subQuery, SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`user_id` = SysUser.`id` where SysUser.`user_name` = ?");
    }

    @Test
    void TestWhereSubQuery() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .innerJoin(UserRoleModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.userId().equalTo(mainTable.id())))
                .column(table -> table)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().likeSubQuery(UserRoleModel.class, (mt, query) -> query
                                .column(UserRoleModel.Column::id)
                                .where((ct, m) -> ct
                                        .and(m.roleName().equalTo(arg())))
                                .limitOne()
                                .query())))
                .query();
        arg(0);
        arg(1);
        setSqlBuilder(sqlBuilder, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`user_id` = SysUser.`id` where SysUser.`user_name` like (select UserRole.`id` `id` from `user_role` UserRole where UserRole.`role_name` = ? limit ?,?)");

        sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .innerJoin(UserRoleModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.userId().equalTo(mainTable.id())))
                .column(table -> table)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().likeSubQuery(UserRoleModel.class, (mt, query) -> query
                                .column(UserRoleModel.Column::id)
                                .where((ct, m) -> ct
                                        .and(m.userId().between(arg(), arg())))
                                .limitOne()
                                .query())))
                .query();
        arg(0);
        arg(1);
        setSqlBuilder(sqlBuilder, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`user_id` = SysUser.`id` where SysUser.`user_name` like (select UserRole.`id` `id` from `user_role` UserRole where UserRole.`user_id` between ? and ? limit ?,?)");
    }

    @Test
    void TestWhereSqlPart() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.loginName().sqlPart("=NOW()")))
                .query();
        setSqlBuilder(sqlBuilder, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser where SysUser.`login_name` =NOW()");
    }

}
