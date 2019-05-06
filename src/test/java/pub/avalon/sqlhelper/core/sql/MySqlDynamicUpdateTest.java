package pub.avalon.sqlhelper.core.sql;

import org.junit.jupiter.api.Test;
import pub.avalon.sqlhelper.AbstractTest;
import pub.avalon.sqlhelper.core.builder.SqlBuilder;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalon.sqlhelper.readme.entity.SysUser;
import pub.avalon.sqlhelper.readme.model.SysUserModel;
import pub.avalon.sqlhelper.readme.model.UserRoleModel;

import java.util.ArrayList;
import java.util.List;

/**
 * MySql动态引擎 - 更新 - 条件更新
 */
public class MySqlDynamicUpdateTest extends AbstractTest {

    @Test
    void TestUpdateJavaBean() {
        SysUser javaBean = new SysUser();
        javaBean.setId(arg());
        javaBean.setUserName(arg());
        arg(null);

        SqlBuilder sqlBuilder = MySqlDynamicEngine.update(SysUserModel.class)
                .column(table -> table)
                .updateJavaBean(javaBean);
        setSqlBuilder(sqlBuilder, "update `sys_user` SysUser set SysUser.`id` = ?,SysUser.`user_name` = ?,SysUser.`login_name` = ?");
    }

    @Test
    void TestUpdateJavaBeanAndJoinAndWhere() {
        SysUser javaBean = new SysUser();
        javaBean.setId("666");
        javaBean.setUserName(arg());

        SqlBuilder sqlBuilder = MySqlDynamicEngine.update(SysUserModel.class)
                .innerJoin(UserRoleModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .column(table -> table.userName().userName())
                .where(UserRoleModel.class, (condition, table, mainTable) -> condition
                        .and(table.roleName().equalTo(arg())))
                .updateJavaBean(javaBean);
        setSqlBuilder(sqlBuilder, "update `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` set SysUser.`user_name` = ? where UserRole.`role_name` = ?");
    }

    @Test
    void TestUpdateJavaBeanAndJoinAndWhereSelective() {
        SysUser javaBean = new SysUser();
        javaBean.setUserName(arg());

        SqlBuilder sqlBuilder = MySqlDynamicEngine.update(SysUserModel.class)
                .innerJoin(UserRoleModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .where(UserRoleModel.class, (condition, table, mainTable) -> condition
                        .and(table.roleName().equalTo(arg())))
                .updateJavaBeanSelective(javaBean);
        setSqlBuilder(sqlBuilder, "update `sys_user` SysUser inner join `user_role` UserRole on UserRole.`role_id` = SysUser.`id` set SysUser.`user_name` = ? where UserRole.`role_name` = ?");
    }

    @Test
    void TestUpdateOrInsertJavaBeans() {
        List<SysUser> javaBeans = new ArrayList<>();
        SysUser sysUser = new SysUser();
        arg(sysUser.getId());
        sysUser.setUserName(arg());
        arg(sysUser.getLoginName());
        javaBeans.add(sysUser);
        sysUser = new SysUser();
        arg(sysUser.getId());
        sysUser.setUserName(arg());
        arg(sysUser.getLoginName());
        javaBeans.add(sysUser);
        sysUser = new SysUser();
        arg(sysUser.getId());
        sysUser.setUserName(arg());
        arg(sysUser.getLoginName());
        javaBeans.add(sysUser);

        SqlBuilder sqlBuilder = MySqlDynamicEngine.update(SysUserModel.class)
                .updateOrInsertJavaBeans(javaBeans);
        setSqlBuilder(sqlBuilder, "insert into sys_user (`id`,`user_name`,`login_name`) values (?,?,?),(?,?,?),(?,?,?) on duplicate key update `id` = values(`id`),`user_name` = values(`user_name`),`login_name` = values(`login_name`)");

        javaBeans = new ArrayList<>();
        sysUser = new SysUser();
        javaBeans.add(sysUser);
        sysUser.setUserName(arg());

        sysUser = new SysUser();
        sysUser.setUserName(arg());
        javaBeans.add(sysUser);

        sysUser = new SysUser();
        sysUser.setUserName(arg());
        javaBeans.add(sysUser);
        sqlBuilder = MySqlDynamicEngine.update(SysUserModel.class)
                .column(SysUserModel.Column::userName)
                .updateOrInsertJavaBeans(javaBeans);
        setSqlBuilder(sqlBuilder, "insert into sys_user (`user_name`) values (?),(?),(?) on duplicate key update `user_name` = values(`user_name`)");

    }
}
