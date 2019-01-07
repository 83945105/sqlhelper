# sqlhelper

## 一、介绍

**sql助手，这是一款用于快速编写生成sql的工具，生成的sql可以用于各种ORM框架如Mybatis、Spring JDBC等，对于Spring JDBC我们已经有了一个集成项目sqlhelper-spring，本文只介绍sqlhelper用法，如对sqlhelper-spring有兴趣可以点击下方链接**

[sqlhelper-spring](https://github.com/83945105/sqlhelper-spring )



## 二、理念

**首先本项目并不是一个ORM框架，我们不提供任何ORM框架功能，因为市面上已经有很多成熟的ORM框架，我们没必要去重复造轮子，我们只为了解决编写、维护sql语句这一个痛点，因此这只是一个单纯用于生产sql语句的工具。你可以将生产出的sql语句用于各种ORM框架。我们秉承着使用简单，零配置，所见即所得的思想进行开发。实际上，对于像Hibernate、MyBatis之类的框架也提供了相关编写sql语句的方式，如在实体Bean中加入注解，使用xml编写之类的方式，但这些方式无一例外都有一些痛点，我们会在下文详细说明这些问题。本工具并不是万能的，我们的目标是解决掉一个项目中90%的sql，对于一些复杂的sql如统计sql，我们不对其进行支持，当然这并不是因为实现不了，而是对于过于复杂的sql，使用本工具提供的方式反而会适得其反，我们永远遵循着所见即所得，简单明了，一目了然的思想，当你看完本文档应该能够明白。**



## 三、特色

-  纯Java代码，无需任何文件配合。纯天然，无污染。
-  无任何注解。注解把代码逻辑分散的到处都是，好烦有木有，维护好痛苦。
-  多数据库支持，可以一键切换sql语句生成方式，生成支持对应数据库语法的sql，不用担心换库问题。
-  无需手动填入列名、字段名 ，再也不用担心写sql的过程中忘记表中有哪些字段。。。字段名叫啥了。。。也不用担心手抖写错了。。。
-  支持多表连接操作、支持子查询、支持批量语句。
-  结构清晰，所见即所得，熟悉本工具之后，一眼望过去，你就知道你写的这段代码对应的sql是什么样子。后续维护加个条件啥的易如反掌。
-  动态适配字段，啥？表加字段？删字段？改字段名？这都不是问题，本工具可以轻松解决这些开发中常见问题。
-  支持动态表名，支持动态表之间连接操作。分表啥的不是问题。
-  支持重写相关方法实现自定义产出sql格式，如果你感觉我们生产的sql写的太low。。。你可以以重写相关类的方式实现你自己的逻辑。
-  更多特色待添加



## 四、数据库支持

*目前仅支持MySql、SqlServer,更多数据库支持会陆续添加*



## 五、文档

> <a name="doc">目录</a>
>
> 1. <a href="#doc1">准备工作</a>
> 2. <a href="#doc2">第一个demo，主键查询</a>

**<a name="doc1" href="#doc">1、准备工作</a>**

使用本工具前，你需要先使用本工具提供的模板工具为每一张数据库表生成一个模型类，MySql示例代码如下：

```
        JdbcSourceEngine engine = JdbcSourceEngine.newMySqlEngine(
                "com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/数据库名?useSSL=false",
                "账号", "密码");
		//构建模板引擎,设置映射方式为驼峰转换(目前唯一支持)
        new ModelTemplateEngine(engine, new HumpConverter())
        		//设置模板后缀名,默认Model
        		.setTemplateSuffix("Model")
        		//添加要生成的表名和对应生成的类名
                .addTable("表名", "类名")
                .addTable("sys_user", "SysUser")
				//开始处理,第一个参数为项目路径,第二个参数为包路径
                .process("/", "pub.avalon.sys.model");         
```

注：模板实现使用了thymeleaf，如果你使用了spring boot，可能会和spring boot内置的spring-boot-start-thymeleaf包冲突，如果你不善于解决maven依赖冲突，你可以为生成模型类的这段代码单独创建一个项目，每次生成完模型类后手动copy至开发项目中。

**<a name="doc2" href="#doc">2、第一个demo，主键查询</a>**

有了模型类后，我们就可以开始愉快的写代码了，我们先来个简单的MySql主键查询示例

```
        //使用MySql动态引擎查询SysUserModel对应的表
        SqlBuilder sqlBuilder = MySqlDynamicEngine.query(SysUserModel.class)
                //调用主键查询接口
                .queryByPrimaryKey("主键ID");
        //产出预编译sql
        String sql = sqlBuilder.getPreparedStatementSql();
        //产出预编译sql参数
        List<Object> args = sqlBuilder.getPreparedStatementArgs();
        //TODO 你可以将产出的预编译sql和参数传入如Spring JDBC的JdbcTemplate相关方法中使用
        //TODO 或者可以使用我们集成好的项目 sqlhelper-spring 该项目提供了很多强大的通用增删改查接口
```

产出的sql如下

```
SELECT
	SysUser.`ID` `id`,
	SysUser.`USER_NAME` `userName`,
	SysUser.`LOGIN_NAME` `loginName`
FROM
	sys_user SysUser 
WHERE
	SysUser.`ID` = ?
```

