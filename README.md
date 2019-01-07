# sqlhelper

## 一、介绍

**sql助手，这是一款用于快速编写生成sql的工具，生成的sql可以用于各种ORM框架如Mybatis、Spring JDBC等，对于Spring JDBC我们已经有了一个集成项目sqlhelper-spring，本文只介绍sqlhelper用法，如对sqlhelper-spring有兴趣可以点击下方链接**

[sqlhelper-spring](https://github.com/83945105/sqlhelper-spring )

## 二、理念

**首先本项目并不是一个ORM框架，我们不提供任何ORM框架功能，因为市面上已经有很多成熟的ORM框架，我们没必要去重复造轮子，我们只为了解决编写、维护sql语句这一个痛点，因此这只是一个单纯用于生产sql语句的工具。你可以将生产出的sql语句用于各种ORM框架。我们秉承着使用简单，零配置，所见即所得的思想进行开发。实际上，对于像Hibernate、MyBatis之类的框架也提供了相关编写sql语句的方式，如在实体Bean中加入注解，使用xml编写之类的方式，但这些方式无一例外都有一些痛点，我们会在下文详细说明这些问题。**