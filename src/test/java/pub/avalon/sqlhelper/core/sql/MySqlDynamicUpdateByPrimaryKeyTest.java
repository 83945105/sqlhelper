package pub.avalon.sqlhelper.core.sql;

import com.shiro.JurRole;
import com.shiro.JurRoleModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pub.avalon.sqlhelper.core.build.SqlBuilder;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 白超 on 2018/8/27.
 */
public class MySqlDynamicUpdateByPrimaryKeyTest {

    @Test
    void TestUpdateArgsByPrimaryKey() {
        List<Object> args = new ArrayList<>();
        SqlBuilder sqlBuilder = MySqlDynamicEngine.update(JurRoleModel.class)
                .updateArgsByPrimaryKey("1", args);

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "update jur_role set `name` = ?,`role` = ?,`description` = ?,`parent_id` = ?,`parent_ids` = ?,`type` = ?,`index` = ?,`status` = ?,`create_time` = ?,`update_time` = ?,`delete_time` = ?,`create_time_stamp` = ?,`update_time_stamp` = ?,`delete_time_stamp` = ? where `id` = ?");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 1);
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().get(0), "1");

        sqlBuilder = MySqlDynamicEngine.update(JurRoleModel.class)
                .column(JurRoleModel.Column::role)
                .updateArgsByPrimaryKey("1", args);

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "update jur_role set `role` = ? where `id` = ?");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 1);
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().get(0), "1");
    }

    @Test
    void TestUpdateJavaBeanByPrimaryKey() {
        JurRole javaBean = new JurRole();
        SqlBuilder sqlBuilder = MySqlDynamicEngine.update(JurRoleModel.class)
                .updateJavaBeanByPrimaryKey("1", javaBean);

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "update jur_role set `name` = ?,`role` = ?,`description` = ?,`parent_id` = ?,`parent_ids` = ?,`type` = ?,`index` = ?,`status` = ?,`create_time` = ?,`update_time` = ?,`delete_time` = ?,`create_time_stamp` = ?,`update_time_stamp` = ?,`delete_time_stamp` = ? where `id` = ?");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 15);
        Assertions.assertNull(sqlBuilder.getPreparedStatementArgs().get(0));

        javaBean.setRole("admin");
        javaBean.setCreateTime("");
        sqlBuilder = MySqlDynamicEngine.update(JurRoleModel.class)
                .column(JurRoleModel.Column::role)
                .updateJavaBeanByPrimaryKey("1", javaBean);

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "update jur_role set `role` = ? where `id` = ?");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 2);
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().get(0), "admin");
    }

    @Test
    void TestUpdateJavaBeanByPrimaryKeySelective() {
        JurRole javaBean = new JurRole();
        javaBean.setId("666");
        javaBean.setRole("admin");

        SqlBuilder sqlBuilder = MySqlDynamicEngine.update(JurRoleModel.class)
                .updateJavaBeanByPrimaryKeySelective("1", javaBean);

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "update jur_role set `role` = ? where `id` = ?");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 2);
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().get(0), "admin");

        javaBean.setCreateTime("");
        sqlBuilder = MySqlDynamicEngine.update(JurRoleModel.class)
                .column(JurRoleModel.Column::role)
                .updateJavaBeanByPrimaryKeySelective("1", javaBean);

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "update jur_role set `role` = ? where `id` = ?");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 2);
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().get(0), "admin");
    }

    @Test
    void TestBatchUpdateJavaBeansByPrimaryKeys() {
        List<JurRole> javaBeans = new ArrayList<>();
        JurRole jurRole = new JurRole();
        jurRole.setId("1");
        javaBeans.add(jurRole);
        jurRole = new JurRole();
        jurRole.setId("2");
        javaBeans.add(jurRole);
        jurRole = new JurRole();
        jurRole.setId("3");
        javaBeans.add(jurRole);

        SqlBuilder sqlBuilder = MySqlDynamicEngine.update(JurRoleModel.class)
                .batchUpdateJavaBeansByPrimaryKeys(javaBeans);

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "update jur_role JurRole set JurRole.`name`=case JurRole.`id` when '1' then ? when '2' then ? when '3' then ?  end,JurRole.`role`=case JurRole.`id` when '1' then ? when '2' then ? when '3' then ?  end,JurRole.`description`=case JurRole.`id` when '1' then ? when '2' then ? when '3' then ?  end,JurRole.`parent_id`=case JurRole.`id` when '1' then ? when '2' then ? when '3' then ?  end,JurRole.`parent_ids`=case JurRole.`id` when '1' then ? when '2' then ? when '3' then ?  end,JurRole.`type`=case JurRole.`id` when '1' then ? when '2' then ? when '3' then ?  end,JurRole.`index`=case JurRole.`id` when '1' then ? when '2' then ? when '3' then ?  end,JurRole.`status`=case JurRole.`id` when '1' then ? when '2' then ? when '3' then ?  end,JurRole.`create_time`=case JurRole.`id` when '1' then ? when '2' then ? when '3' then ?  end,JurRole.`update_time`=case JurRole.`id` when '1' then ? when '2' then ? when '3' then ?  end,JurRole.`delete_time`=case JurRole.`id` when '1' then ? when '2' then ? when '3' then ?  end,JurRole.`create_time_stamp`=case JurRole.`id` when '1' then ? when '2' then ? when '3' then ?  end,JurRole.`update_time_stamp`=case JurRole.`id` when '1' then ? when '2' then ? when '3' then ?  end,JurRole.`delete_time_stamp`=case JurRole.`id` when '1' then ? when '2' then ? when '3' then ?  end where JurRole.`id` in (?,?,?)");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 45);
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().get(42), "1");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().get(43), "2");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().get(44), "3");
    }

}
