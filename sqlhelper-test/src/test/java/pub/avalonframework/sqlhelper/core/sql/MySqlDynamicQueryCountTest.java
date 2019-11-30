package pub.avalonframework.sqlhelper.core.sql;

/**
 * MySql动态引擎 - 查询 - 总数
 */
public class MySqlDynamicQueryCountTest {

/*
    @Test
    void TestJoin03() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .innerJoin(RoleResourceHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id()).resourceName().equalTo(arg())))
                .leftJoin(RoleResourceHelper.class, "RR", (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .queryCount();
        setSqlBuilder(sqlBuilderResult, "select count(1) from `sys_user` SysUser inner join `role_resource` RoleResource on RoleResource.`role_id` = SysUser.`id` and RoleResource.`resource_name` = ? left join `role_resource` RR on RR.`role_id` = SysUser.`id`");
    }

    @Test
    void TestJoin04() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .innerJoin(RoleResourceHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id()).resourceName().equalTo(arg())))
                .leftJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .rightJoin(RoleResourceHelper.class, "CC", (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .queryCount();
        setSqlBuilder(sqlBuilderResult, "select count(1) from `sys_user` SysUser inner join `role_resource` RoleResource on RoleResource.`role_id` = SysUser.`id` and RoleResource.`resource_name` = ? left join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` right join `role_resource` CC on CC.`role_id` = SysUser.`id`");
    }

    @Test
    void TestJoinAndWhere01() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .innerJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .where(UserRoleHelper.class, (condition, table, mainTable) -> condition
                        .and(table.roleName().like(arg())))
                .queryCount();
        setSqlBuilder(sqlBuilderResult, "select count(1) from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` where UserRole.`role_name` like ?");
    }

    @Test
    void TestJoinAndWhere02() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .innerJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())
                                .roleName().equalTo(arg())))
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().notEqualTo(arg()))
                        .or(mainTable.loginName().equalTo(arg())))
                .where(UserRoleHelper.class, (condition, table, mainTable) -> condition
                        .and(table.roleName().equalTo(arg())))
                .queryCount();
        setSqlBuilder(sqlBuilderResult, "select count(1) from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` and UserRole.`role_name` = ? where (SysUser.`user_name` != ? or SysUser.`login_name` = ?) and UserRole.`role_name` = ?");
    }

    @Test
    void TestJoinAndWhere03() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .innerJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id()).roleName().equalTo(arg())))
                .leftJoin(RoleResourceHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(UserRoleHelper.class, UserRoleHelper.Column::roleId)))
                .where(RoleResourceHelper.class, (condition, table, mainTable) -> condition
                        .and(table.resourceName().like(arg())))
                .queryCount();
        setSqlBuilder(sqlBuilderResult, "select count(1) from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` and UserRole.`role_name` = ? left join `role_resource` RoleResource on RoleResource.`role_id` = UserRole.`role_id` where RoleResource.`resource_name` like ?");
    }

    @Test
    void TestJoinAndWhere04() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .innerJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id()).roleName().equalTo(arg())))
                .rightJoin(UserRoleHelper.class, "UR", (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .leftJoin(RoleResourceHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())
                                .roleId().lessThan(UserRoleHelper.class, "UR", UserRoleHelper.Column::roleId)))
                .where(UserRoleHelper.class, "UR", (condition, table, mainTable) -> condition
                        .and(table.roleName().equalTo(arg())))
                .queryCount();
        setSqlBuilder(sqlBuilderResult, "select count(1) from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` and UserRole.`role_name` = ? right join `user_role` UR on UR.`role_id` = SysUser.`id` left join `role_resource` RoleResource on RoleResource.`role_id` = SysUser.`id` and RoleResource.`role_id` < UR.`role_id` where UR.`role_name` = ?");
    }

    @Test
    void TestGroup01() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .group(SysUserHelper.Group::id)
                .queryCount();
        setSqlBuilder(sqlBuilderResult, "select count(1) from (select SysUser.`id` from `sys_user` SysUser group by SysUser.`id`) C");
    }

    @Test
    void TestGroup02() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .group(table -> table.id().userName())
                .queryCount();
        setSqlBuilder(sqlBuilderResult, "select count(1) from (select SysUser.`id` from `sys_user` SysUser group by SysUser.`id`,SysUser.`user_name`) C");
    }

    @Test
    void TestGroupAndJoinAndWhere01() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())))
                .group(table -> table.id().loginName())
                .queryCount();
        setSqlBuilder(sqlBuilderResult, "select count(1) from (select SysUser.`id` from `sys_user` SysUser where SysUser.`user_name` = ? group by SysUser.`id`,SysUser.`login_name`) C");

    }

    @Test
    void TestGroupAndJoinAndWhere02() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .innerJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())))
                .group(table -> table.id().id())
                .group(UserRoleHelper.class, UserRoleHelper.Group::roleId)
                .queryCount();
        setSqlBuilder(sqlBuilderResult, "select count(1) from (select SysUser.`id` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` where SysUser.`user_name` = ? group by SysUser.`id`,UserRole.`role_id`) C");
    }

    @Test
    void TestSort01() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .sort(table -> table.id().asc())
                .queryCount();
        setSqlBuilder(sqlBuilderResult, "select count(1) from `sys_user` SysUser");
    }

    @Test
    void TestSort02() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .sort(table -> table.id().asc().userName().desc())
                .queryCount();
        setSqlBuilder(sqlBuilderResult, "select count(1) from `sys_user` SysUser");
    }

    @Test
    void TestSortAndJoinAndWhere01() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())))
                .sort(table -> table.id().desc())
                .queryCount();
        setSqlBuilder(sqlBuilderResult, "select count(1) from `sys_user` SysUser where SysUser.`user_name` = ?");
    }

    @Test
    void TestSortAndJoinAndWhere02() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .innerJoin(UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().equalTo(arg())))
                .sort(UserRoleHelper.class, table -> table.roleId().desc())
                .queryCount();
        setSqlBuilder(sqlBuilderResult, "select count(1) from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` where SysUser.`user_name` = ?");
    }

    @Test
    void TestLimit01() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .limit(200L, 10L, 20L)
                .queryCount();
        Pagination pagination = new Pagination(DataBaseType.MYSQL);
        pagination.setTotal(200L);
        pagination.setCurrentPage(10L);
        pagination.setPageSize(20L);
        arg(pagination.getLimitStartNum());
        arg(pagination.getLimitEndNum());
        setSqlBuilder(sqlBuilderResult, "select count(1) from (select SysUser.`id` from `sys_user` SysUser limit ?,?) C");
    }*/

}
