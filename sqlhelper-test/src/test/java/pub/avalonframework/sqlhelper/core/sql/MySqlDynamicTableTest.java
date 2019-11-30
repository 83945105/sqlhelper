package pub.avalonframework.sqlhelper.core.sql;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.SqlBuilderResult;
import pub.avalonframework.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalonframework.sqlhelper.readme.entity.SysUserHelper;

/**
 * MySql动态引擎 - 表
 */
public class MySqlDynamicTableTest {

    /**
     * 复制表
     */
    @Test
    void Test_copyTable() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .copyTable("copyTable", false);
        Assertions.assertEquals("create table `copyTable` like `sys_user`", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new String[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 复制表 - 指定表名
     */
    @Test
    void Test_copyTable_assignTableName() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table("sys_user_custom", SysUserHelper.class)
                .copyTable("copyTable", false);
        Assertions.assertEquals("create table `copyTable` like `sys_user_custom`", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new String[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 复制表 - 复制数据
     */
    @Test
    void Test_copyTable_copyData() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .copyTable("copyTable", true);
        Assertions.assertEquals("create table `copyTable` like `sys_user`; insert into `copyTable` select * from `sys_user`", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new String[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 复制表 - 指定表名 & 复制数据
     */
    @Test
    void Test_copyTable_assignTableName_copyData() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table("sys_user_custom", SysUserHelper.class)
                .copyTable("copyTable", true);
        Assertions.assertEquals("create table `copyTable` like `sys_user_custom`; insert into `copyTable` select * from `sys_user_custom`", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new String[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 删除表
     */
    @Test
    void Test_deleteTable() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .deleteTable();
        Assertions.assertEquals("drop table `sys_user`", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new String[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 删除表 - 指定表名
     */
    @Test
    void Test_deleteTable_assignTableName() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table("sys_user_custom", SysUserHelper.class)
                .deleteTable();
        Assertions.assertEquals("drop table `sys_user_custom`", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new String[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 重命名表
     */
    @Test
    void Test_renameTable() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .renameTable("newTableName");
        Assertions.assertEquals("rename table `sys_user` to `newTableName`", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new String[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 重命名表 - 指定表名
     */
    @Test
    void Test_renameTable_assignTableName() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table("sys_user_custom", SysUserHelper.class)
                .renameTable("newTableName");
        Assertions.assertEquals("rename table `sys_user_custom` to `newTableName`", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new String[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 判断表是否存在
     */
    @Test
    void Test_isTableExist() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .isTableExist();
        Assertions.assertEquals("select table_name from information_schema.TABLES where table_name = 'sys_user' and table_schema = (select database())", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new String[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

    /**
     * 判断表是否存在 - 指定表名
     */
    @Test
    void Test_isTableExist_assignTableName() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table("sys_user_custom", SysUserHelper.class)
                .isTableExist();
        Assertions.assertEquals("select table_name from information_schema.TABLES where table_name = 'sys_user_custom' and table_schema = (select database())", sqlBuilderResult.getPreparedStatementSql());
        Assertions.assertArrayEquals(new String[]{}, sqlBuilderResult.getPreparedStatementArgs().toArray());
    }

}
