package pub.avalon.sqlhelper.core.sql;

import pub.avalon.sqlhelper.core.beans.FunctionColumnType;
import pub.avalon.sqlhelper.core.build.SqlBuilder;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;
import com.shiro.JurRoleModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by 白超 on 2018/8/25.
 */
public class MySqlDynamicQueryByPrimaryKeyTest {

    @Test
    void TestColumn() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.query(JurRoleModel.class)
                .column(table -> table)
                .queryByPrimaryKey("1");

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "select JurRole.`id` `id`,JurRole.`name` `name`,JurRole.`role` `role`,JurRole.`description` `description`,JurRole.`parent_id` `parentId`,JurRole.`parent_ids` `parentIds`,JurRole.`type` `type`,JurRole.`index` `index`,JurRole.`status` `status`,JurRole.`create_time` `createTime`,JurRole.`update_time` `updateTime`,JurRole.`delete_time` `deleteTime`,JurRole.`create_time_stamp` `createTimeStamp`,JurRole.`update_time_stamp` `updateTimeStamp`,JurRole.`delete_time_stamp` `deleteTimeStamp` from jur_role JurRole where JurRole.`id` = ?");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 1);
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().get(0), "1");

        sqlBuilder = MySqlDynamicEngine.query(JurRoleModel.class)
                .column(JurRoleModel.Column::id)
                .queryByPrimaryKey("2");

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "select JurRole.`id` `id` from jur_role JurRole where JurRole.`id` = ?");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 1);
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().get(0), "2");

        sqlBuilder = MySqlDynamicEngine.query(JurRoleModel.class)
                .column(table -> table.role().name())
                .queryByPrimaryKey(3);

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "select JurRole.`role` `role`,JurRole.`name` `name` from jur_role JurRole where JurRole.`id` = ?");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 1);
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().get(0), 3);
    }

    @Test
    void TestVirtualColumn() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.query(JurRoleModel.class)
                .virtualColumn(1, "1_alias")
                .virtualColumn("2", "2_alias")
                .virtualColumn(3L, "3_alias")
                .virtualColumn(4.0, "4_alias")
                .queryByPrimaryKey(3);

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "select 1 `1_alias`,'2' `2_alias`,3 `3_alias`,4.0 `4_alias` from jur_role JurRole where JurRole.`id` = ?");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 1);
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().get(0), 3);
    }

    @Test
    void TestColumnAndVirtualColumn() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.query(JurRoleModel.class)
                .virtualColumn(1, "1_alias")
                .column(table -> table)
                .queryByPrimaryKey(3);

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "select 1 `1_alias`, JurRole.`id` `id`,JurRole.`name` `name`,JurRole.`role` `role`,JurRole.`description` `description`,JurRole.`parent_id` `parentId`,JurRole.`parent_ids` `parentIds`,JurRole.`type` `type`,JurRole.`index` `index`,JurRole.`status` `status`,JurRole.`create_time` `createTime`,JurRole.`update_time` `updateTime`,JurRole.`delete_time` `deleteTime`,JurRole.`create_time_stamp` `createTimeStamp`,JurRole.`update_time_stamp` `updateTimeStamp`,JurRole.`delete_time_stamp` `deleteTimeStamp` from jur_role JurRole where JurRole.`id` = ?");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 1);
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().get(0), 3);
    }

    @Test
    void TestFunctionColumn() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.query(JurRoleModel.class)
                .functionColumn(FunctionColumnType.COUNT, JurRoleModel.Column::id)
                .queryByPrimaryKey(3);

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "select count(JurRole.`id`) `id` from jur_role JurRole where JurRole.`id` = ?");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 1);
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().get(0), 3);
    }

    @Test
    void TestFunctionColumnAndVirtualColumn() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.query(JurRoleModel.class)
                .functionColumn(FunctionColumnType.COUNT, JurRoleModel.Column::id)
                .virtualColumn(1, "1_alias")
                .queryByPrimaryKey(3);

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "select count(JurRole.`id`) `id`, 1 `1_alias` from jur_role JurRole where JurRole.`id` = ?");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 1);
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().get(0), 3);
    }

    @Test
    void TestFunctionColumnAndVirtualColumnAndColumn() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.query(JurRoleModel.class)
                .functionColumn(FunctionColumnType.COUNT, table -> table.id("idCount"))
                .virtualColumn(1, "1_alias")
                .column(table -> table)
                .queryByPrimaryKey(3);

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "select count(JurRole.`id`) `idCount`, 1 `1_alias`, JurRole.`id` `id`,JurRole.`name` `name`,JurRole.`role` `role`,JurRole.`description` `description`,JurRole.`parent_id` `parentId`,JurRole.`parent_ids` `parentIds`,JurRole.`type` `type`,JurRole.`index` `index`,JurRole.`status` `status`,JurRole.`create_time` `createTime`,JurRole.`update_time` `updateTime`,JurRole.`delete_time` `deleteTime`,JurRole.`create_time_stamp` `createTimeStamp`,JurRole.`update_time_stamp` `updateTimeStamp`,JurRole.`delete_time_stamp` `deleteTimeStamp` from jur_role JurRole where JurRole.`id` = ?");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 1);
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().get(0), 3);
    }

}
