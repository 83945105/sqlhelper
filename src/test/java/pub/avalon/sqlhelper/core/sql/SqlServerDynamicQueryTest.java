package pub.avalon.sqlhelper.core.sql;

import com.shiro.JurResModel;
import com.shiro.JurRoleModel;
import com.shiro.JurRoleResModel;
import com.shiro.JurRoleUserModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pub.avalon.sqlhelper.core.build.SqlBuilder;
import pub.avalon.sqlhelper.factory.SqlServerDynamicEngine;

/**
 * Created by 白超 on 2018/8/25.
 */
public class SqlServerDynamicQueryTest {

    @Test
    void TestWhere() {
        SqlBuilder sqlBuilder = SqlServerDynamicEngine.query(JurRoleModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.role().equalTo("admin")))
                .query();

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "select JurRole.[id] [id],JurRole.[name] [name],JurRole.[role] [role],JurRole.[description] [description],JurRole.[parent_id] [parentId],JurRole.[parent_ids] [parentIds],JurRole.[type] [type],JurRole.[index] [index],JurRole.[status] [status],JurRole.[create_time] [createTime],JurRole.[update_time] [updateTime],JurRole.[delete_time] [deleteTime],JurRole.[create_time_stamp] [createTimeStamp],JurRole.[update_time_stamp] [updateTimeStamp],JurRole.[delete_time_stamp] [deleteTimeStamp] from jur_role JurRole where JurRole.[role] = ?");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 1);
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().get(0), "admin");

        sqlBuilder = SqlServerDynamicEngine.query(JurRoleModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.role().equalTo("admin").createTime().equalTo(2)))
                .query();

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "select JurRole.[id] [id],JurRole.[name] [name],JurRole.[role] [role],JurRole.[description] [description],JurRole.[parent_id] [parentId],JurRole.[parent_ids] [parentIds],JurRole.[type] [type],JurRole.[index] [index],JurRole.[status] [status],JurRole.[create_time] [createTime],JurRole.[update_time] [updateTime],JurRole.[delete_time] [deleteTime],JurRole.[create_time_stamp] [createTimeStamp],JurRole.[update_time_stamp] [updateTimeStamp],JurRole.[delete_time_stamp] [deleteTimeStamp] from jur_role JurRole where JurRole.[role] = ? and JurRole.[create_time] = ?");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 2);
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().get(1), 2);

        sqlBuilder = SqlServerDynamicEngine.query(JurRoleModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.role().equalTo("admin").createTime().equalTo(2))
                        .or(mainTable.index().greaterThan(6)))
                .query();

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "select JurRole.[id] [id],JurRole.[name] [name],JurRole.[role] [role],JurRole.[description] [description],JurRole.[parent_id] [parentId],JurRole.[parent_ids] [parentIds],JurRole.[type] [type],JurRole.[index] [index],JurRole.[status] [status],JurRole.[create_time] [createTime],JurRole.[update_time] [updateTime],JurRole.[delete_time] [deleteTime],JurRole.[create_time_stamp] [createTimeStamp],JurRole.[update_time_stamp] [updateTimeStamp],JurRole.[delete_time_stamp] [deleteTimeStamp] from jur_role JurRole where JurRole.[role] = ? and JurRole.[create_time] = ? or JurRole.[index] > ?");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 3);
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().get(2), 6);

        sqlBuilder = SqlServerDynamicEngine.query(JurRoleModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.role().equalTo("admin").createTime().equalTo(2))
                        .or(mainTable.index().greaterThan(6).parentId().between(1, 2))
                        .and(mainTable.parentId().like("233")))
                .query();

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "select JurRole.[id] [id],JurRole.[name] [name],JurRole.[role] [role],JurRole.[description] [description],JurRole.[parent_id] [parentId],JurRole.[parent_ids] [parentIds],JurRole.[type] [type],JurRole.[index] [index],JurRole.[status] [status],JurRole.[create_time] [createTime],JurRole.[update_time] [updateTime],JurRole.[delete_time] [deleteTime],JurRole.[create_time_stamp] [createTimeStamp],JurRole.[update_time_stamp] [updateTimeStamp],JurRole.[delete_time_stamp] [deleteTimeStamp] from jur_role JurRole where JurRole.[role] = ? and JurRole.[create_time] = ? or (JurRole.[index] > ? and JurRole.[parent_id] between ? and ?) and JurRole.[parent_id] like ?");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 6);
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().get(2), 6);


        sqlBuilder = SqlServerDynamicEngine.query(JurRoleModel.class)
                .where((condition, mainTable) -> condition
                        .and((condition1, mainTable1) -> condition1
                                .and(mainTable1.role().notEqualTo("5"))
                                .or(mainTable1.name().greaterThan(2)))
                        .or(mainTable.index().greaterThan(6).parentId().between(1, 2))
                        .and((condition1, mainTable1) -> condition1
                                .and(mainTable1.name().equalTo(2))
                                .or(mainTable1.role().equalTo(5))))
                .query();

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "select JurRole.[id] [id],JurRole.[name] [name],JurRole.[role] [role],JurRole.[description] [description],JurRole.[parent_id] [parentId],JurRole.[parent_ids] [parentIds],JurRole.[type] [type],JurRole.[index] [index],JurRole.[status] [status],JurRole.[create_time] [createTime],JurRole.[update_time] [updateTime],JurRole.[delete_time] [deleteTime],JurRole.[create_time_stamp] [createTimeStamp],JurRole.[update_time_stamp] [updateTimeStamp],JurRole.[delete_time_stamp] [deleteTimeStamp] from jur_role JurRole where (JurRole.[role] != ? or JurRole.[name] > ?) or (JurRole.[index] > ? and JurRole.[parent_id] between ? and ?) and (JurRole.[name] = ? or JurRole.[role] = ?)");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 7);
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().get(2), 6);

        sqlBuilder = SqlServerDynamicEngine.query(JurRoleModel.class)
                .where((condition, mainTable) -> condition
                        .and((condition1, mainTable1) -> condition1
                                .and(mainTable1.role().notEqualTo("5"))
                                .or(mainTable1.name().greaterThan(2)))
                        .or(mainTable.index().greaterThan(6).parentId().between(1, 2))
                        .and((condition1, mainTable1) -> condition1
                                .and(mainTable1.name().equalTo(2))
                                .or(mainTable1.role().equalTo(5))))
                .where((condition, mainTable) -> condition
                        .and((condition1, mainTable1) -> condition1
                                .and(mainTable1.role().greaterThanAndEqualTo(8))
                                .or(mainTable1.name().lessThanAndEqualTo(10))))
                .query();

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "select JurRole.[id] [id],JurRole.[name] [name],JurRole.[role] [role],JurRole.[description] [description],JurRole.[parent_id] [parentId],JurRole.[parent_ids] [parentIds],JurRole.[type] [type],JurRole.[index] [index],JurRole.[status] [status],JurRole.[create_time] [createTime],JurRole.[update_time] [updateTime],JurRole.[delete_time] [deleteTime],JurRole.[create_time_stamp] [createTimeStamp],JurRole.[update_time_stamp] [updateTimeStamp],JurRole.[delete_time_stamp] [deleteTimeStamp] from jur_role JurRole where ((JurRole.[role] != ? or JurRole.[name] > ?) or (JurRole.[index] > ? and JurRole.[parent_id] between ? and ?) and (JurRole.[name] = ? or JurRole.[role] = ?)) and (JurRole.[role] >= ? or JurRole.[name] <= ?)");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 9);
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().get(2), 6);
    }

    @Test
    void TestJoin() {
        SqlBuilder sqlBuilder = SqlServerDynamicEngine.query(JurRoleModel.class)
                .innerJoin(JurRoleResModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .query();

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "select JurRole.[id] [id],JurRole.[name] [name],JurRole.[role] [role],JurRole.[description] [description],JurRole.[parent_id] [parentId],JurRole.[parent_ids] [parentIds],JurRole.[type] [type],JurRole.[index] [index],JurRole.[status] [status],JurRole.[create_time] [createTime],JurRole.[update_time] [updateTime],JurRole.[delete_time] [deleteTime],JurRole.[create_time_stamp] [createTimeStamp],JurRole.[update_time_stamp] [updateTimeStamp],JurRole.[delete_time_stamp] [deleteTimeStamp] from jur_role JurRole inner join jur_role_res JurRoleRes on JurRoleRes.[role_id] = JurRole.[id]");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 0);

        sqlBuilder = SqlServerDynamicEngine.query(JurRoleModel.class)
                .innerJoin(JurRoleResModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id()).resName().equalTo(1)))
                .query();

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "select JurRole.[id] [id],JurRole.[name] [name],JurRole.[role] [role],JurRole.[description] [description],JurRole.[parent_id] [parentId],JurRole.[parent_ids] [parentIds],JurRole.[type] [type],JurRole.[index] [index],JurRole.[status] [status],JurRole.[create_time] [createTime],JurRole.[update_time] [updateTime],JurRole.[delete_time] [deleteTime],JurRole.[create_time_stamp] [createTimeStamp],JurRole.[update_time_stamp] [updateTimeStamp],JurRole.[delete_time_stamp] [deleteTimeStamp] from jur_role JurRole inner join jur_role_res JurRoleRes on JurRoleRes.[role_id] = JurRole.[id] and JurRoleRes.[res_name] = ?");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 1);
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().get(0), 1);

        sqlBuilder = SqlServerDynamicEngine.query(JurRoleModel.class)
                .innerJoin(JurRoleResModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id()).resName().equalTo(1)))
                .leftJoin(JurRoleUserModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .query();

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "select JurRole.[id] [id],JurRole.[name] [name],JurRole.[role] [role],JurRole.[description] [description],JurRole.[parent_id] [parentId],JurRole.[parent_ids] [parentIds],JurRole.[type] [type],JurRole.[index] [index],JurRole.[status] [status],JurRole.[create_time] [createTime],JurRole.[update_time] [updateTime],JurRole.[delete_time] [deleteTime],JurRole.[create_time_stamp] [createTimeStamp],JurRole.[update_time_stamp] [updateTimeStamp],JurRole.[delete_time_stamp] [deleteTimeStamp] from jur_role JurRole inner join jur_role_res JurRoleRes on JurRoleRes.[role_id] = JurRole.[id] and JurRoleRes.[res_name] = ? left join jur_role_user JurRoleUser on JurRoleUser.[role_id] = JurRole.[id]");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 1);
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().get(0), 1);

        sqlBuilder = SqlServerDynamicEngine.query(JurRoleModel.class)
                .innerJoin(JurRoleResModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id()).resName().equalTo(1)))
                .leftJoin(JurRoleUserModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .rightJoin(JurRoleResModel.class, "JurRoleRes2", (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .query();

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "select JurRole.[id] [id],JurRole.[name] [name],JurRole.[role] [role],JurRole.[description] [description],JurRole.[parent_id] [parentId],JurRole.[parent_ids] [parentIds],JurRole.[type] [type],JurRole.[index] [index],JurRole.[status] [status],JurRole.[create_time] [createTime],JurRole.[update_time] [updateTime],JurRole.[delete_time] [deleteTime],JurRole.[create_time_stamp] [createTimeStamp],JurRole.[update_time_stamp] [updateTimeStamp],JurRole.[delete_time_stamp] [deleteTimeStamp] from jur_role JurRole inner join jur_role_res JurRoleRes on JurRoleRes.[role_id] = JurRole.[id] and JurRoleRes.[res_name] = ? left join jur_role_user JurRoleUser on JurRoleUser.[role_id] = JurRole.[id] right join jur_role_res JurRoleRes2 on JurRoleRes2.[role_id] = JurRole.[id]");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 1);
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().get(0), 1);
    }

    @Test
    void TestJoinAndWhere() {
        SqlBuilder sqlBuilder = SqlServerDynamicEngine.query(JurRoleModel.class)
                .innerJoin(JurRoleResModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .where(JurRoleResModel.class, (condition, table, mainTable) -> condition
                        .and(table.resType().like("233")))
                .query();

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "select JurRole.[id] [id],JurRole.[name] [name],JurRole.[role] [role],JurRole.[description] [description],JurRole.[parent_id] [parentId],JurRole.[parent_ids] [parentIds],JurRole.[type] [type],JurRole.[index] [index],JurRole.[status] [status],JurRole.[create_time] [createTime],JurRole.[update_time] [updateTime],JurRole.[delete_time] [deleteTime],JurRole.[create_time_stamp] [createTimeStamp],JurRole.[update_time_stamp] [updateTimeStamp],JurRole.[delete_time_stamp] [deleteTimeStamp] from jur_role JurRole inner join jur_role_res JurRoleRes on JurRoleRes.[role_id] = JurRole.[id] where JurRoleRes.[res_type] like ?");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 1);
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().get(0), "233");

        sqlBuilder = SqlServerDynamicEngine.query(JurRoleModel.class)
                .innerJoin(JurRoleResModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id()).resName().equalTo(1)))
                .where((condition, mainTable) -> condition
                        .and(mainTable.name().notEqualTo(9))
                        .or(mainTable.role().equalTo(10)))
                .where(JurRoleResModel.class, (condition, table, mainTable) -> condition
                        .and(table.resType().equalTo(11)))
                .query();

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "select JurRole.[id] [id],JurRole.[name] [name],JurRole.[role] [role],JurRole.[description] [description],JurRole.[parent_id] [parentId],JurRole.[parent_ids] [parentIds],JurRole.[type] [type],JurRole.[index] [index],JurRole.[status] [status],JurRole.[create_time] [createTime],JurRole.[update_time] [updateTime],JurRole.[delete_time] [deleteTime],JurRole.[create_time_stamp] [createTimeStamp],JurRole.[update_time_stamp] [updateTimeStamp],JurRole.[delete_time_stamp] [deleteTimeStamp] from jur_role JurRole inner join jur_role_res JurRoleRes on JurRoleRes.[role_id] = JurRole.[id] and JurRoleRes.[res_name] = ? where (JurRole.[name] != ? or JurRole.[role] = ?) and JurRoleRes.[res_type] = ?");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 4);
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().get(3), 11);

        sqlBuilder = SqlServerDynamicEngine.query(JurRoleModel.class)
                .innerJoin(JurRoleResModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id()).resName().equalTo(1)))
                .leftJoin(JurRoleUserModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id()))
                        .and(joinTable.roleId().equalTo(JurRoleResModel.class, JurRoleResModel.On::resId)))
                .query();

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "select JurRole.[id] [id],JurRole.[name] [name],JurRole.[role] [role],JurRole.[description] [description],JurRole.[parent_id] [parentId],JurRole.[parent_ids] [parentIds],JurRole.[type] [type],JurRole.[index] [index],JurRole.[status] [status],JurRole.[create_time] [createTime],JurRole.[update_time] [updateTime],JurRole.[delete_time] [deleteTime],JurRole.[create_time_stamp] [createTimeStamp],JurRole.[update_time_stamp] [updateTimeStamp],JurRole.[delete_time_stamp] [deleteTimeStamp] from jur_role JurRole inner join jur_role_res JurRoleRes on JurRoleRes.[role_id] = JurRole.[id] and JurRoleRes.[res_name] = ? left join jur_role_user JurRoleUser on JurRoleUser.[role_id] = JurRole.[id] and JurRoleUser.[role_id] = JurRoleRes.[res_id]");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 1);
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().get(0), 1);

        sqlBuilder = SqlServerDynamicEngine.query(JurRoleModel.class)
                .innerJoin(JurRoleResModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id()).resName().equalTo(1)))
                .rightJoin(JurRoleResModel.class, "JurRoleRes2", (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .leftJoin(JurRoleUserModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id()).roleId().lessThan(JurRoleResModel.class, "JurRoleRes2", JurRoleResModel.On::roleId)))
                .where(JurRoleResModel.class, "JurRoleRes2", (condition, table, mainTable) -> condition
                        .and(table.resType().equalTo(3)))
                .query();

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "select JurRole.[id] [id],JurRole.[name] [name],JurRole.[role] [role],JurRole.[description] [description],JurRole.[parent_id] [parentId],JurRole.[parent_ids] [parentIds],JurRole.[type] [type],JurRole.[index] [index],JurRole.[status] [status],JurRole.[create_time] [createTime],JurRole.[update_time] [updateTime],JurRole.[delete_time] [deleteTime],JurRole.[create_time_stamp] [createTimeStamp],JurRole.[update_time_stamp] [updateTimeStamp],JurRole.[delete_time_stamp] [deleteTimeStamp] from jur_role JurRole inner join jur_role_res JurRoleRes on JurRoleRes.[role_id] = JurRole.[id] and JurRoleRes.[res_name] = ? right join jur_role_res JurRoleRes2 on JurRoleRes2.[role_id] = JurRole.[id] left join jur_role_user JurRoleUser on JurRoleUser.[role_id] = JurRole.[id] and JurRoleUser.[role_id] < JurRoleRes2.[role_id] where JurRoleRes2.[res_type] = ?");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 2);
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().get(1), 3);
    }

    @Test
    void TestGroup() {
        SqlBuilder sqlBuilder = SqlServerDynamicEngine.query(JurRoleModel.class)
                .column(JurRoleModel.Column::createTime)
                .group(JurRoleModel.Group::createTime)
                .query();

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "select JurRole.[create_time] [createTime] from jur_role JurRole group by JurRole.create_time");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 0);

        sqlBuilder = SqlServerDynamicEngine.query(JurRoleModel.class)
                .column(table -> table.createTime().deleteTime())
                .group(table -> table.createTime().deleteTime())
                .query();

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "select JurRole.[create_time] [createTime],JurRole.[delete_time] [deleteTime] from jur_role JurRole group by JurRole.create_time,JurRole.delete_time");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 0);
    }

    @Test
    void TestGroupAndJoinAndWhere() {
        SqlBuilder sqlBuilder = SqlServerDynamicEngine.query(JurRoleModel.class)
                .column(table -> table.createTime().deleteTime())
                .where((condition, mainTable) -> condition
                        .and(mainTable.role().equalTo(2)))
                .group(table -> table.createTime().deleteTime())
                .query();

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "select JurRole.[create_time] [createTime],JurRole.[delete_time] [deleteTime] from jur_role JurRole where JurRole.[role] = ? group by JurRole.create_time,JurRole.delete_time");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 1);
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().get(0), 2);

        sqlBuilder = SqlServerDynamicEngine.query(JurRoleModel.class)
                .innerJoin(JurRoleResModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .column(table -> table.createTime().deleteTime())
                .column(JurRoleResModel.class, table -> table.resName("resName2"))
                .where((condition, mainTable) -> condition
                        .and(mainTable.role().equalTo(2)))
                .group(table -> table.createTime().deleteTime())
                .group(JurRoleResModel.class, JurRoleResModel.Group::resName)
                .query();

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "select JurRole.[create_time] [createTime],JurRole.[delete_time] [deleteTime],JurRoleRes.[res_name] [resName2] from jur_role JurRole inner join jur_role_res JurRoleRes on JurRoleRes.[role_id] = JurRole.[id] where JurRole.[role] = ? group by JurRole.create_time,JurRole.delete_time,JurRoleRes.res_name");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 1);
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().get(0), 2);
    }

    @Test
    void TestSort() {
        SqlBuilder sqlBuilder = SqlServerDynamicEngine.query(JurRoleModel.class)
                .sort(table -> table.createTime().asc())
                .query();

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "select JurRole.[id] [id],JurRole.[name] [name],JurRole.[role] [role],JurRole.[description] [description],JurRole.[parent_id] [parentId],JurRole.[parent_ids] [parentIds],JurRole.[type] [type],JurRole.[index] [index],JurRole.[status] [status],JurRole.[create_time] [createTime],JurRole.[update_time] [updateTime],JurRole.[delete_time] [deleteTime],JurRole.[create_time_stamp] [createTimeStamp],JurRole.[update_time_stamp] [updateTimeStamp],JurRole.[delete_time_stamp] [deleteTimeStamp] from jur_role JurRole order by JurRole.[create_time] asc");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 0);

        sqlBuilder = SqlServerDynamicEngine.query(JurRoleModel.class)
                .sort(table -> table.createTime().asc().deleteTime().desc())
                .query();

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "select JurRole.[id] [id],JurRole.[name] [name],JurRole.[role] [role],JurRole.[description] [description],JurRole.[parent_id] [parentId],JurRole.[parent_ids] [parentIds],JurRole.[type] [type],JurRole.[index] [index],JurRole.[status] [status],JurRole.[create_time] [createTime],JurRole.[update_time] [updateTime],JurRole.[delete_time] [deleteTime],JurRole.[create_time_stamp] [createTimeStamp],JurRole.[update_time_stamp] [updateTimeStamp],JurRole.[delete_time_stamp] [deleteTimeStamp] from jur_role JurRole order by JurRole.[create_time] asc,JurRole.[delete_time] desc");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 0);
    }

    @Test
    void TestSortAndJoinAndWhere() {
        SqlBuilder sqlBuilder = SqlServerDynamicEngine.query(JurRoleModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.role().equalTo(2)))
                .sort(table -> table.deleteTime().desc())
                .query();

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "select JurRole.[id] [id],JurRole.[name] [name],JurRole.[role] [role],JurRole.[description] [description],JurRole.[parent_id] [parentId],JurRole.[parent_ids] [parentIds],JurRole.[type] [type],JurRole.[index] [index],JurRole.[status] [status],JurRole.[create_time] [createTime],JurRole.[update_time] [updateTime],JurRole.[delete_time] [deleteTime],JurRole.[create_time_stamp] [createTimeStamp],JurRole.[update_time_stamp] [updateTimeStamp],JurRole.[delete_time_stamp] [deleteTimeStamp] from jur_role JurRole where JurRole.[role] = ? order by JurRole.[delete_time] desc");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 1);
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().get(0), 2);

        sqlBuilder = SqlServerDynamicEngine.query(JurRoleModel.class)
                .innerJoin(JurRoleResModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.id())))
                .where((condition, mainTable) -> condition
                        .and(mainTable.role().equalTo(2)))
                .sort(JurRoleResModel.class, table -> table.createTime().desc())
                .query();

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "select JurRole.[id] [id],JurRole.[name] [name],JurRole.[role] [role],JurRole.[description] [description],JurRole.[parent_id] [parentId],JurRole.[parent_ids] [parentIds],JurRole.[type] [type],JurRole.[index] [index],JurRole.[status] [status],JurRole.[create_time] [createTime],JurRole.[update_time] [updateTime],JurRole.[delete_time] [deleteTime],JurRole.[create_time_stamp] [createTimeStamp],JurRole.[update_time_stamp] [updateTimeStamp],JurRole.[delete_time_stamp] [deleteTimeStamp] from jur_role JurRole inner join jur_role_res JurRoleRes on JurRoleRes.[role_id] = JurRole.[id] where JurRole.[role] = ? order by JurRoleRes.[create_time] desc");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 1);
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().get(0), 2);
    }

    @Test
    void TestLimit() {
        SqlBuilder sqlBuilder = SqlServerDynamicEngine.query(JurRoleModel.class)
                .limit(1, 10)
                .query();

        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "select * from (select row_number() over(order by JurRole.[id] asc) n, JurRole.[id] [id],JurRole.[name] [name],JurRole.[role] [role],JurRole.[description] [description],JurRole.[parent_id] [parentId],JurRole.[parent_ids] [parentIds],JurRole.[type] [type],JurRole.[index] [index],JurRole.[status] [status],JurRole.[create_time] [createTime],JurRole.[update_time] [updateTime],JurRole.[delete_time] [deleteTime],JurRole.[create_time_stamp] [createTimeStamp],JurRole.[update_time_stamp] [updateTimeStamp],JurRole.[delete_time_stamp] [deleteTimeStamp] from jur_role JurRole) rn where rn.n >= ? and rn.n <= ?");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 2);
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().get(0), 1);

        sqlBuilder = SqlServerDynamicEngine.query(JurRoleModel.class)
                .limit(200, 10, 20)
                .query();
        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "select * from (select row_number() over(order by JurRole.[id] asc) n, JurRole.[id] [id],JurRole.[name] [name],JurRole.[role] [role],JurRole.[description] [description],JurRole.[parent_id] [parentId],JurRole.[parent_ids] [parentIds],JurRole.[type] [type],JurRole.[index] [index],JurRole.[status] [status],JurRole.[create_time] [createTime],JurRole.[update_time] [updateTime],JurRole.[delete_time] [deleteTime],JurRole.[create_time_stamp] [createTimeStamp],JurRole.[update_time_stamp] [updateTimeStamp],JurRole.[delete_time_stamp] [deleteTimeStamp] from jur_role JurRole) rn where rn.n >= ? and rn.n <= ?");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 2);
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().get(0), 181);
    }

    @Test
    void TestJoinOn() {
        SqlBuilder sqlBuilder = SqlServerDynamicEngine.query(JurRoleResModel.class)
                .innerJoin(JurRoleModel.class, (on, joinTable, mainTable) -> on
                        .and((o, jt, mt) -> on
                                .and(jt.id().equalTo(mt.roleId()))
                                .or(jt.role().equalTo(mt.role())))
                        .or(joinTable.id().equalTo(mainTable.roleId())))
                .query();
        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "select JurRoleRes.[id] [id],JurRoleRes.[role_id] [roleId],JurRoleRes.[role] [role],JurRoleRes.[role_name] [roleName],JurRoleRes.[role_type] [roleType],JurRoleRes.[res_id] [resId],JurRoleRes.[res_name] [resName],JurRoleRes.[res_url] [resUrl],JurRoleRes.[res_type] [resType],JurRoleRes.[index] [index],JurRoleRes.[status] [status],JurRoleRes.[create_time] [createTime],JurRoleRes.[update_time] [updateTime],JurRoleRes.[delete_time] [deleteTime],JurRoleRes.[create_time_stamp] [createTimeStamp],JurRoleRes.[update_time_stamp] [updateTimeStamp],JurRoleRes.[delete_time_stamp] [deleteTimeStamp] from jur_role_res JurRoleRes inner join jur_role JurRole on (JurRole.[id] = JurRoleRes.[role_id] or JurRole.[role] = JurRoleRes.[role]) or JurRole.[id] = JurRoleRes.[role_id]");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 0);
        sqlBuilder = SqlServerDynamicEngine.query(JurRoleResModel.class)
                .innerJoin(JurRoleModel.class, (on, joinTable, mainTable) -> on
                        .and((on1, jt1, mt1) -> on1
                                .and(jt1.id().equalTo(mt1.roleId()).role().equalTo("teacher"))
                                .or(jt1.role().equalTo(mt1.role()))
                                .and((on2, jt2, mt2) -> on2
                                        .and(jt2.id().equalTo("1").role().equalTo("admin"))))
                        .or(joinTable.createTime().equalTo("666"))
                        .or(joinTable.id().equalTo(mainTable.roleId())))
                .query();
        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "select JurRoleRes.[id] [id],JurRoleRes.[role_id] [roleId],JurRoleRes.[role] [role],JurRoleRes.[role_name] [roleName],JurRoleRes.[role_type] [roleType],JurRoleRes.[res_id] [resId],JurRoleRes.[res_name] [resName],JurRoleRes.[res_url] [resUrl],JurRoleRes.[res_type] [resType],JurRoleRes.[index] [index],JurRoleRes.[status] [status],JurRoleRes.[create_time] [createTime],JurRoleRes.[update_time] [updateTime],JurRoleRes.[delete_time] [deleteTime],JurRoleRes.[create_time_stamp] [createTimeStamp],JurRoleRes.[update_time_stamp] [updateTimeStamp],JurRoleRes.[delete_time_stamp] [deleteTimeStamp] from jur_role_res JurRoleRes inner join jur_role JurRole on (JurRole.[id] = JurRoleRes.[role_id] and JurRole.[role] = ? or JurRole.[role] = JurRoleRes.[role] and JurRole.[id] = ? and JurRole.[role] = ?) or JurRole.[create_time] = ? or JurRole.[id] = JurRoleRes.[role_id]");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 4);
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().get(2), "admin");
    }

    @Test
    void TestSubQuery() {
        SqlBuilder sqlBuilder = SqlServerDynamicEngine.query(JurRoleResModel.class)
                .innerJoin(JurRoleModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.createTime().equalTo(mainTable.roleId())))
                .subQuery(JurResModel.class, (mainTable, query) -> query
                        .column(JurResModel.Column::id)
                        .where((cd, mt) -> cd
                                .and(mt.createTime().equalTo(666)
                                        .createTime().equalTo(mainTable.createTime()))
                                .and(mainTable.createTime().equalTo(mt.createTime()))
                                .and(mt.createTime().equalTo(JurRoleModel.class, JurRoleModel.Where::createTime)))
                        .limit(1, 1), "subQuery")
                .column(table -> table)
                .where((condition, mainTable) -> condition
                        .and(mainTable.createTime().equalTo("233")))
                .query();
        Assertions.assertEquals(sqlBuilder.getPreparedStatementSql(), "select (select * from (select row_number() over(order by JurRes.[id] asc) n, JurRes.[id] [id] from jur_res JurRes where JurRes.[create_time] = ? and JurRes.[create_time] = JurRoleRes.[create_time] and JurRoleRes.[create_time] = JurRes.[create_time] and JurRes.[create_time] = JurRole.[create_time]) rn where rn.n >= ? and rn.n <= ?) subQuery, JurRoleRes.[id] [id],JurRoleRes.[role_id] [roleId],JurRoleRes.[role] [role],JurRoleRes.[role_name] [roleName],JurRoleRes.[role_type] [roleType],JurRoleRes.[res_id] [resId],JurRoleRes.[res_name] [resName],JurRoleRes.[res_url] [resUrl],JurRoleRes.[res_type] [resType],JurRoleRes.[index] [index],JurRoleRes.[status] [status],JurRoleRes.[create_time] [createTime],JurRoleRes.[update_time] [updateTime],JurRoleRes.[delete_time] [deleteTime],JurRoleRes.[create_time_stamp] [createTimeStamp],JurRoleRes.[update_time_stamp] [updateTimeStamp],JurRoleRes.[delete_time_stamp] [deleteTimeStamp] from jur_role_res JurRoleRes inner join jur_role JurRole on JurRole.[create_time] = JurRoleRes.[role_id] where JurRoleRes.[create_time] = ?");
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().size(), 4);
        Assertions.assertEquals(sqlBuilder.getPreparedStatementArgs().get(0), 666);
    }
}
