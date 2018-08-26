package pub.avalon.sqlhelper.core.sql;

import pub.avalon.sqlhelper.core.build.SqlBuilder;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;
import com.shiro.JurRole;
import com.shiro.JurRoleModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 白超 on 2018/8/26.
 */
public class MySqlDynamicInsertTest {

    @Test
    void TestInsertArgs() {
        List<Object> args = new ArrayList<>();
        args.add("1");

        SqlBuilder sqlBuilder = MySqlDynamicEngine.insert(JurRoleModel.class)
                .insertArgs(args);

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "insert into jur_role (`id`,`name`,`role`,`description`,`parent_id`,`parent_ids`,`type`,`index`,`status`,`create_time`,`update_time`,`delete_time`,`create_time_stamp`,`update_time_stamp`,`delete_time_stamp`) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 1);
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().get(0), "1");

        sqlBuilder = MySqlDynamicEngine.insert("root_jur_role", JurRoleModel.class)
                .insertArgs(args);

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "insert into root_jur_role (`id`,`name`,`role`,`description`,`parent_id`,`parent_ids`,`type`,`index`,`status`,`create_time`,`update_time`,`delete_time`,`create_time_stamp`,`update_time_stamp`,`delete_time_stamp`) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 1);
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().get(0), "1");

        sqlBuilder = MySqlDynamicEngine.insert(JurRoleModel.class)
                .column(JurRoleModel.Column::id)
                .insertArgs(args);

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "insert into jur_role (`id`) values (?)");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 1);
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().get(0), "1");
    }

    @Test
    void TestInsertJavaBean() {
        JurRole javaBean = new JurRole();

        SqlBuilder sqlBuilder = MySqlDynamicEngine.insert(JurRoleModel.class)
                .insertJavaBean(javaBean);

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "insert into jur_role (`id`,`name`,`role`,`description`,`parent_id`,`parent_ids`,`type`,`index`,`status`,`create_time`,`update_time`,`delete_time`,`create_time_stamp`,`update_time_stamp`,`delete_time_stamp`) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 15);
        Assertions.assertNull(sqlBuilder.getPreparedStatementArgs().get(0));

        javaBean.setId("2");
        sqlBuilder = MySqlDynamicEngine.insert(JurRoleModel.class)
                .column(table -> table.id().name())
                .insertJavaBean(javaBean);

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "insert into jur_role (`id`,`name`) values (?,?)");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 2);
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().get(0), "2");
    }

    @Test
    void TestInsertJavaBeanSelective() {
        JurRole javaBean = new JurRole();
        javaBean.setId("2");
        javaBean.setRole("admin");

        SqlBuilder sqlBuilder = MySqlDynamicEngine.insert(JurRoleModel.class)
                .insertJavaBeanSelective(javaBean);

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "insert into jur_role (`id`,`role`) values (?,?)");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 2);
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().get(0), "2");

        sqlBuilder = MySqlDynamicEngine.insert(JurRoleModel.class)
                .column(table -> table.id().name())
                .insertJavaBeanSelective(javaBean);

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "insert into jur_role (`id`) values (?)");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 1);
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().get(0), "2");
    }

    @Test
    void TestBatchInsertJavaBeans() {

        List<JurRole> javaBeans = new ArrayList<>();
        javaBeans.add(new JurRole());
        javaBeans.add(new JurRole());
        JurRole role = new JurRole();
        role.setId("666");
        javaBeans.add(role);

        SqlBuilder sqlBuilder = MySqlDynamicEngine.insert(JurRoleModel.class)
                .batchInsertJavaBeans(javaBeans);

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "insert into jur_role (`id`,`name`,`role`,`description`,`parent_id`,`parent_ids`,`type`,`index`,`status`,`create_time`,`update_time`,`delete_time`,`create_time_stamp`,`update_time_stamp`,`delete_time_stamp`) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?),(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?),(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 45);
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().get(30), "666");
    }

}
