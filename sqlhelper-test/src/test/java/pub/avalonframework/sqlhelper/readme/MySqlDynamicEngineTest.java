package pub.avalonframework.sqlhelper.readme;

import pub.avalonframework.sqlhelper.AbstractTest;
import pub.avalonframework.sqlhelper.core.beans.JoinType;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.SqlBuilderResult;
import pub.avalonframework.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalonframework.sqlhelper.readme.entity.SysUserHelper;
import pub.avalonframework.sqlhelper.readme.entity.UserRoleHelper;

import java.sql.SQLException;

/**
 * 第一个demo，主键查询
 */
public class MySqlDynamicEngineTest extends AbstractTest {

    /**
     * 1、准备工作
     */
    void ready() throws SQLException {
/*        JdbcSourceEngine engine = JdbcSourceEngine.newMySqlEngine(
                "com.mysql.cj.jdbc.Driver",
                "jdbc:mysql://localhost:3306/sqlhelper?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai",
                "root", "root");

        new ModelTemplateEngine(engine, new HumpConverter())
                .addTable("sys_user", "SysUser")
                .addTable("user_role", "UserRole")
                .addTable("role_resource", "RoleResource")
                .process("/", "pub.avalon.sqlhelper.readme.model");*/
    }

    /**
     * 2、第一个demo，主键查询
     */
//    @Test
    void TestByPrimaryKey() {
        //使用MySql动态引擎查询SysUserModel对应的表
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                //调用主键查询接口
                .queryByPrimaryKey(arg());
        //TODO 你可以将产出的预编译sql和参数传入如Spring JDBC的JdbcTemplate相关方法中使用
        //TODO 或者可以使用我们集成好的项目 sqlhelper-spring 该项目提供了很多强大的通用增删改查接口

        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser where SysUser.`id` = ?");
    }

    /**
     * 3、指定查询列
     */
//    @Test
    void TestColumn() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                //指定查询表的id、userName字段并给userName字段取个别名userNameAlias
                .column(table -> table.id().userName("userNameAlias"))
                .queryByPrimaryKey(arg());

        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userNameAlias` from `sys_user` SysUser where SysUser.`id` = ?");

        sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                //table表示查询所有字段，如果不指定任何column，默认也是这样
                .column(table -> table)
                .queryByPrimaryKey(arg());

        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser where SysUser.`id` = ?");
    }

    /**
     * 4、条件查询
     */
//    @Test
    void TestWhere() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(table -> table.id().userName("userNameAlias"))
                //使用where条件,你将有俩个参数可用。
                //condition - 条件，第一次可以调出and条件，之后可以调出or条件
                //mainTable - 主表,也就是你操作的出发表，这里为对应SysUserModel对应的表
                .where((condition, mainTable) -> condition
                        //添加一个and条件，条件为当主表的userName字段模糊匹配“白”开头的字符
                        .and(mainTable.userName().like(arg("白%"))))
                //查询，注意这里就用不了queryByPrimaryKey方法了
                .query();

        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userNameAlias` from `sys_user` SysUser where SysUser.`user_name` like ?");

        //上面的例子只是一个条件，那么多条件查询该如何写呢
        sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(table -> table.id().userName("userNameAlias"))
                .where((condition, mainTable) -> condition
                        //添加一个and条件 userName 等于 1
                        .and(mainTable.userName().equalTo(arg("1"))
                                //继续追加一个and条件 loginName 等于2
                                .loginName().equalTo(arg("2"))))
                .query();

        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userNameAlias` from `sys_user` SysUser where SysUser.`user_name` = ? and SysUser.`login_name` = ?");

        //你也可以这么写
        sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(table -> table.id().userName("userNameAlias"))
                .where((condition, mainTable) -> condition
                        //添加一个and条件 userName 等于 1
                        .and(mainTable.userName().equalTo(arg("1")))
                        //继续追加一个and条件 loginName 等于2
                        .and(mainTable.loginName().equalTo(arg("2"))))
                .query();

        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userNameAlias` from `sys_user` SysUser where SysUser.`user_name` = ? and SysUser.`login_name` = ?");

        //or条件
        sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(table -> table.id().userName("userNameAlias"))
                .where((condition, mainTable) -> condition
                        //添加一个and条件 userName 等于 1
                        .and(mainTable.userName().equalTo(arg("1")))
                        //继续追加一个or条件 loginName 等于2
                        .or(mainTable.loginName().equalTo(arg("2"))))
                .query();

        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userNameAlias` from `sys_user` SysUser where SysUser.`user_name` = ? or SysUser.`login_name` = ?");

    }

    /**
     * 5、复杂的条件查询
     */
//    @Test
    void TestWhereAndOr() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(table -> table.id().userName("userNameAlias"))
                .where((condition, mainTable) -> condition
                        // and条件继续使用lambda获取新的condition取名为cd(防止重名)、新的mainTable取名为mt
                        // 同样or条件也支持, 这里就不举例了
                        .and(cd -> cd
                                .and(mainTable.userName().like(arg()))
                                .or(mainTable.loginName().like(arg())))
                        .and(mainTable.userName().equalTo(arg())))
                .query();

        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userNameAlias` from `sys_user` SysUser where (SysUser.`user_name` like ? or SysUser.`login_name` like ?) and SysUser.`user_name` = ?");


    }

    /**
     * 6、连接查询
     */
//    @Test
    void TestJoin() {
        SqlBuilderResult sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                .column(table -> table.id().userName("userNameAlias"))
                // 连接UserRoleModel对应的表 , 设置连接类型为inner , 或者直接使用innerJoin可以省略该参数
                .join(JoinType.INNER, UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        // 从lambda函数获取参数 on - on条件、joinTable - 当前连接表(UserRoleModel对应的表)、mainTable - 主表(SysUserModel对应的表)
                        // 表示内连接user_role表,连接条件是user_role表的user_id字段等于主表的id字段
                        .and(joinTable.userId().equalTo(mainTable.id())))
                // 使用innerJoin可以省略连接类型参数
                //.innerJoin(UserRoleModel.class, (on, joinTable, mainTable) -> on
                //        .and(joinTable.userId().equalTo(mainTable.id())))
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().like(arg())))
                .query();

        setSqlBuilder(sqlBuilderResult, "select SysUser.`id` `id`,SysUser.`user_name` `userNameAlias` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`user_id` = SysUser.`id` where SysUser.`user_name` like ?");

        //从产出的sql来看，我们只查询了主表的字段，那么如果我们想同时查询出连接表的字段该如何做呢？
        sqlBuilderResult = MySqlDynamicEngine.table(SysUserHelper.class)
                //将column定位到连接表模型，指定要查询的列，注意，为了防止列名重复，必要字段请自行取别名，这里将连接表的主键id更名为userRoleId
                .column(UserRoleHelper.class, table -> table.id("userRoleId").roleId().roleName())
                //注意，如果指定了column、functionColumn(见下文)、virtualColumn(见下文)等
                //将默认不会查询主表字段，此时如果你想查询出主表字段需手动声明，这里的写法(table -> table)表示查询出主表所有字段
                .column(table -> table)
                .join(JoinType.INNER, UserRoleHelper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.userId().equalTo(mainTable.id())))
                .where((condition, mainTable) -> condition
                        .and(mainTable.userName().like(arg())))
                .query();

        setSqlBuilder(sqlBuilderResult, "select UserRole.`id` `userRoleId`,UserRole.`role_id` `roleId`,UserRole.`role_name` `roleName`,SysUser.`id` `id`,SysUser.`user_name` `userName`,SysUser.`login_name` `loginName` from `sys_user` SysUser inner join `user_role` UserRole on UserRole.`user_id` = SysUser.`id` where SysUser.`user_name` like ?");

    }

}
