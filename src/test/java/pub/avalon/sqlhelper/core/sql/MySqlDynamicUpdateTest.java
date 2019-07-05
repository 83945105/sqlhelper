package pub.avalon.sqlhelper.core.sql;

import org.junit.jupiter.api.Test;
import pub.avalon.sqlhelper.AbstractTest;
import pub.avalon.sqlhelper.core.sqlbuilder.SqlBuilder;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalon.sqlhelper.readme.entity.SysUserDTO;
import pub.avalon.sqlhelper.readme.entity.UserRoleDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * MySql动态引擎 - 更新 - 条件更新
 */
public class MySqlDynamicUpdateTest extends AbstractTest {

    @Test
    void TestUpdateJavaBean01() {
        SysUserDTO javaBean = new SysUserDTO();
        javaBean.setId(arg());
        javaBean.setUserName(arg());
        arg(null);

        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .column(table -> table)
                .updateJavaBean(javaBean);
        setSqlBuilder(sqlBuilder, "update `sys_user` SysUser set SysUser.`id` = ?,SysUser.`user_name` = ?,SysUser.`login_name` = ?");
    }

    @Test
    void TestUpdateJavaBeanAndJoinAndWhere01() {
        SysUserDTO javaBean = new SysUserDTO();
        javaBean.setId("666");
        javaBean.setUserName(arg());

        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .column(table -> table.userName().userName())
                .innerJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .where(UserRoleDTO.Helper.class, (condition, table, mainTable) -> condition
                        .and(table.roleName().equalTo(arg())))
                .updateJavaBean(javaBean);
        setSqlBuilder(sqlBuilder, "update `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` set SysUser.`user_name` = ? where UserRole.`role_name` = ?");
    }

    @Test
    void TestUpdateJavaBeanAndJoinAndWhereSelective01() {
        SysUserDTO javaBean = new SysUserDTO();
        javaBean.setUserName(arg());

        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .innerJoin(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .where(UserRoleDTO.Helper.class, (condition, table, mainTable) -> condition
                        .and(table.roleName().equalTo(arg())))
                .updateJavaBeanSelective(javaBean);
        setSqlBuilder(sqlBuilder, "update `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` set SysUser.`user_name` = ? where UserRole.`role_name` = ?");
    }

    @Test
    void TestUpdateOrInsertJavaBeans01() {
        List<SysUserDTO> javaBeans = new ArrayList<>();
        SysUserDTO sysUser = new SysUserDTO();
        arg(sysUser.getId());
        sysUser.setUserName(arg());
        arg(sysUser.getLoginName());
        javaBeans.add(sysUser);
        sysUser = new SysUserDTO();
        arg(sysUser.getId());
        sysUser.setUserName(arg());
        arg(sysUser.getLoginName());
        javaBeans.add(sysUser);
        sysUser = new SysUserDTO();
        arg(sysUser.getId());
        sysUser.setUserName(arg());
        arg(sysUser.getLoginName());
        javaBeans.add(sysUser);

        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .updateOrInsertJavaBeans(javaBeans);
        setSqlBuilder(sqlBuilder, "insert into sys_user (`id`,`user_name`,`login_name`) values (?,?,?),(?,?,?),(?,?,?) on duplicate key update `id` = values(`id`),`user_name` = values(`user_name`),`login_name` = values(`login_name`)");
    }

    @Test
    void TestUpdateOrInsertJavaBeans02() {
        List<SysUserDTO> javaBeans = new ArrayList<>();
        SysUserDTO sysUser = new SysUserDTO();
        javaBeans.add(sysUser);
        sysUser.setUserName(arg());

        sysUser = new SysUserDTO();
        sysUser.setUserName(arg());
        javaBeans.add(sysUser);

        sysUser = new SysUserDTO();
        sysUser.setUserName(arg());
        javaBeans.add(sysUser);
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .column(SysUserDTO.Helper.Column::userName)
                .updateOrInsertJavaBeans(javaBeans);
        setSqlBuilder(sqlBuilder, "insert into sys_user (`user_name`) values (?),(?),(?) on duplicate key update `user_name` = values(`user_name`)");
    }
}
