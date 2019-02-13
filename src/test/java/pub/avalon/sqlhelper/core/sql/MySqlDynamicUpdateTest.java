package pub.avalon.sqlhelper.core.sql;

import com.shiro.JurRole;
import com.shiro.JurRoleModel;
import com.shiro.JurRoleResModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pub.avalon.sqlhelper.core.build.SqlBuilder;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 白超 on 2018/8/27.
 */
public class MySqlDynamicUpdateTest {

    @Test
    void TestUpdateJavaBean() {
        JurRole javaBean = new JurRole();
        javaBean.setId("666");
        javaBean.setRole("admin");

        SqlBuilder sqlBuilder = MySqlDynamicEngine.update(JurRoleModel.class)
                .column(table -> table)
                .updateJavaBean(javaBean);

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "update jur_role JurRole set JurRole.`id` = ?,JurRole.`name` = ?,JurRole.`role` = ?,JurRole.`description` = ?,JurRole.`parent_id` = ?,JurRole.`parent_ids` = ?,JurRole.`type` = ?,JurRole.`index` = ?,JurRole.`status` = ?,JurRole.`create_time` = ?,JurRole.`update_time` = ?,JurRole.`delete_time` = ?,JurRole.`create_time_stamp` = ?,JurRole.`update_time_stamp` = ?,JurRole.`delete_time_stamp` = ?");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 15);
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().get(0), "666");
    }

    @Test
    void TestUpdateJavaBeanAndJoinAndWhere() {
        JurRole javaBean = new JurRole();
        javaBean.setId("666");
        javaBean.setRole("admin");

        SqlBuilder sqlBuilder = MySqlDynamicEngine.update(JurRoleModel.class)
                .innerJoin(JurRoleResModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .column(table -> table.name().role())
                .where(JurRoleResModel.class, (condition, table, mainTable) -> condition
                        .and(table.resType().equalTo("7")))
                .updateJavaBean(javaBean);

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "update jur_role JurRole inner join jur_role_res JurRoleRes on JurRoleRes.`role_id` = JurRole.`id` set JurRole.`name` = ?,JurRole.`role` = ? where JurRoleRes.`res_type` = ?");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 3);
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().get(2), "7");
    }

    @Test
    void TestUpdateJavaBeanAndJoinAndWhereSelective() {
        JurRole javaBean = new JurRole();
        javaBean.setId("666");
        javaBean.setRole("admin");

        SqlBuilder sqlBuilder = MySqlDynamicEngine.update(JurRoleModel.class)
                .innerJoin(JurRoleResModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .where(JurRoleResModel.class, (condition, table, mainTable) -> condition
                        .and(table.resType().equalTo("7")))
                .updateJavaBeanSelective(javaBean);

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "update jur_role JurRole inner join jur_role_res JurRoleRes on JurRoleRes.`role_id` = JurRole.`id` set JurRole.`id` = ?,JurRole.`role` = ? where JurRoleRes.`res_type` = ?");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 3);
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().get(2), "7");
    }

    @Test
    void TestUpdateOrInsertJavaBeans() {
        List<JurRole> javaBeans = new ArrayList<>();
        JurRole role = new JurRole();
        role.setRole("admin");
        javaBeans.add(role);
        role = new JurRole();
        role.setRole("teacher");
        javaBeans.add(role);
        role = new JurRole();
        role.setRole("student");
        javaBeans.add(role);

        SqlBuilder sqlBuilder = MySqlDynamicEngine.update(JurRoleModel.class)
                .updateOrInsertJavaBeans(javaBeans);

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "insert into jur_role (`id`,`name`,`role`,`description`,`parent_id`,`parent_ids`,`type`,`index`,`status`,`create_time`,`update_time`,`delete_time`,`create_time_stamp`,`update_time_stamp`,`delete_time_stamp`) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?),(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?),(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) on duplicate key update `id` = values(`id`),`name` = values(`name`),`role` = values(`role`),`description` = values(`description`),`parent_id` = values(`parent_id`),`parent_ids` = values(`parent_ids`),`type` = values(`type`),`index` = values(`index`),`status` = values(`status`),`create_time` = values(`create_time`),`update_time` = values(`update_time`),`delete_time` = values(`delete_time`),`create_time_stamp` = values(`create_time_stamp`),`update_time_stamp` = values(`update_time_stamp`),`delete_time_stamp` = values(`delete_time_stamp`)");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 45);
        Assertions.assertNull(sqlBuilder.getPreparedStatementArgs().get(44));

        sqlBuilder = MySqlDynamicEngine.update(JurRoleModel.class)
                .column(table -> table.id().role().name())
                .updateOrInsertJavaBeans(javaBeans);

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "insert into jur_role (`id`,`role`,`name`) values (?,?,?),(?,?,?),(?,?,?) on duplicate key update `id` = values(`id`),`role` = values(`role`),`name` = values(`name`)");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 9);
        Assertions.assertNull(sqlBuilder.getPreparedStatementArgs().get(2));
    }
}
