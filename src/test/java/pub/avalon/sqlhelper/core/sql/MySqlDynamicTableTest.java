package pub.avalon.sqlhelper.core.sql;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pub.avalon.sqlhelper.core.sqlbuilder.SqlBuilder;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalon.sqlhelper.readme.entity.SysUserDTO;

/**
 * MySql动态引擎 - 表
 */
public class MySqlDynamicTableTest {

    /**
     * 复制表
     */
    @Test
    void Test_copyTable() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .copyTable("copyTable", false);
        Assertions.assertEquals("create table `copyTable` like `sys_user`", sqlBuilder.getPreparedStatementSql());
        Assertions.assertArrayEquals(new String[]{}, sqlBuilder.getPreparedStatementArgs().toArray());
    }

    /**
     * 复制表 - 复制数据
     */
    @Test
    void Test_copyTable_copyData() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .copyTable("copyTable", true);
        Assertions.assertEquals("create table `copyTable` like `sys_user`; insert into `copyTable` select * from `sys_user`", sqlBuilder.getPreparedStatementSql());
        Assertions.assertArrayEquals(new String[]{}, sqlBuilder.getPreparedStatementArgs().toArray());
    }

    /**
     * 删除表
     */
    @Test
    void Test_deleteTable() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .deleteTable();
        Assertions.assertEquals("drop table `sys_user`", sqlBuilder.getPreparedStatementSql());
        Assertions.assertArrayEquals(new String[]{}, sqlBuilder.getPreparedStatementArgs().toArray());
    }

    /**
     * 重命名表
     */
    @Test
    void TestRenameTable() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .renameTable("newTableName");
        Assertions.assertEquals("rename table `sys_user` to `newTableName`", sqlBuilder.getPreparedStatementSql());
        Assertions.assertArrayEquals(new String[]{}, sqlBuilder.getPreparedStatementArgs().toArray());
    }

    /**
     * 判断表是否存在
     */
    @Test
    void TestIsTableExist() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .isTableExist();
        Assertions.assertEquals("select table_name from information_schema.TABLES where table_name = 'sys_user' and table_schema = (select database())", sqlBuilder.getPreparedStatementSql());
        Assertions.assertArrayEquals(new String[]{}, sqlBuilder.getPreparedStatementArgs().toArray());
    }

}
