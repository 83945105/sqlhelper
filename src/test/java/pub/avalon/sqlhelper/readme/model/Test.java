package pub.avalon.sqlhelper.readme.model;

import pub.avalon.sqlhelper.core.beans.FunctionColumnType;
import pub.avalon.sqlhelper.core.beans.JoinType;
import pub.avalon.sqlhelper.core.engine.LimitIntactEngine;
import pub.avalon.sqlhelper.core.engine.SqlEngine;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalon.sqlhelper.factory.SqlDynamicEngine;
import pub.avalon.sqlhelper.readme.entity.RoleResource;
import pub.avalon.sqlhelper.readme.entity.SysUser;
import pub.avalon.sqlhelper.readme.entity.UserRole;

/**
 * Created by 白超 on 2019/5/9.
 */
public class Test {

    public static void main(String[] args) {
        RoleResource.Helper.Column column = RoleResource.Helper.column().id().id();
        SysUser.Helper.Column joinColumn = SysUser.Helper.column().userName().userName("");
        RoleResource.Helper.Where where = RoleResource.Helper.where().id().equalTo("1").id().like("");
        RoleResource.Helper.Group group = RoleResource.Helper.group().id().id();
        SysUser.Helper.Group joinGroup = SysUser.Helper.group().userName().userName();
        RoleResource.Helper.Sort sort = RoleResource.Helper.sort().id().asc().id().desc();
        SysUser.Helper.Sort joinSort = SysUser.Helper.sort().userName().asc().userName().desc();

        SqlEngine sqlEngine = SqlDynamicEngine.table("", RoleResource.Helper.class)
                .innerJoin(SysUser.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.userName().equalTo(mainTable.id()))
                        .and(mainTable.id().equalTo(joinTable.userName()))
                        .or(joinTable.userName().equalTo("")))
                .join(JoinType.INNER, UserRole.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(SysUser.Helper.class, table -> table.userName().userName())))
                .innerJoin(SysUser.Helper.class, (on, joinTable, mainTable) -> on
                        .and(o -> o
                                .and(joinTable.userName().equalTo(mainTable.id()))
                                .or(mainTable.id().equalTo(joinTable.userName())))
                        .or(o -> o
                                .and(joinTable.userName().equalTo(mainTable.id()))
                                .or(mainTable.id().equalTo(joinTable.userName()))))
                .column(table -> table.id().id())
                .column(table -> table.id().id().id(""))
                .column(SysUser.Helper.class, table -> table.userName().userName(""))
                .column(SysUser.Helper.class, "", table -> table.userName().userName(""))
                .column(table -> column)
                .column(column)
                .column(column, column, column, joinColumn)
                .column(SysUser.Helper.class, table -> joinColumn)
/*                .subQuery("sys_user", SysUser.Helper.class, "SysUser", query -> query
                        .where((condition, mainTable) -> condition
                                .and(mainTable.userName().equalTo(""))).query(), "subQuery")*/
                .virtualColumn(1, "")
                .functionColumn(FunctionColumnType.COUNT, table -> table.id("").id(""))
                .functionColumn(SysUser.Helper.class, FunctionColumnType.MIN, table -> table.userName().userName(""))
                .functionColumn(SysUser.Helper.class, "", FunctionColumnType.MIN, table -> table.userName().userName(""))
                .where((condition, mainTable) -> condition
                        .and(mainTable.id().equalTo("")
                                .id().greaterThan("")))
                .where((condition, mainTable) -> condition
                        .and(cd -> cd
                                .and(mainTable.id().equalTo(""))
                                .or(mainTable.id().equalTo(""))))
                .where(SysUser.Helper.class, (condition, joinTable, mainTable) -> condition
                        .and(mainTable.id().equalTo(joinTable.userName()))
                        .and(joinTable.userName().equalTo(mainTable.id()))
                        .or(joinTable.userName().equalTo(mainTable.id())))
                .where(SysUser.Helper.class, (condition, joinTable, mainTable) -> condition
                        .and(cd -> cd
                                .and(joinTable.userName().equalTo(mainTable.id()))
                                .or(joinTable.userName().equalTo(mainTable.id())))
                        .or(cd -> cd
                                .and(joinTable.userName().equalTo(mainTable.id()))))
                .where((condition, mainTable) -> condition
                        .and(UserRole.Helper.class, (cd, joinTable) -> cd
                                .and(joinTable.roleId().equalTo(""))
                                .and(joinTable.roleId().equalTo(mainTable.id()))
                                .and(mainTable.id().equalTo(joinTable.roleId()))))
                .where(SysUser.Helper.class, (condition, joinTable, mainTable) -> condition
                        .and(UserRole.Helper.class, (cd, UserRoleTable) -> cd
                                .and(joinTable.userName().equalTo(UserRoleTable.roleId())))
                        .or(UserRole.Helper.class, (cd, UserRoleTable) -> cd
                                .and(joinTable.userName().equalTo(UserRoleTable.roleId()))))
                .where((condition, mainTable) -> condition
                        .and(mainTable.id().equalTo(SysUser.Helper.class, table -> table.userName().userName())))
/*                .where((condition, mainTable) -> condition
                        .and(mainTable.id().equalToSubQuery("sys_user", SysUser.Helper.class, "SysUser", query -> query
                                .where((cd, mt) -> cd
                                        .and(mt.userName().equalTo(""))).query())))*/
                .where((condition, mainTable) -> condition.and(where))
                .group(table -> table.id().id())
                .group(SysUser.Helper.class, table -> table.userName().userName())
                .group(table -> group)
                .group(SysUser.Helper.class, table -> joinGroup)
                .group(group, joinGroup)
                .sort(table -> table.id().asc().id().desc())
                .sort(SysUser.Helper.class, table -> table.userName().asc().userName().desc())
                .sort(table -> sort)
                .sort(SysUser.Helper.class, table -> joinSort)
                .sort(sort, joinSort)
                .limitOne();


        sqlEngine.query();
        sqlEngine.updateArgsByPrimaryKey("", "");

    }

}

