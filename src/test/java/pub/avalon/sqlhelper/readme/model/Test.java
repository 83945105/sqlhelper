package pub.avalon.sqlhelper.readme.model;

import pub.avalon.sqlhelper.core.beans.FunctionColumnType;
import pub.avalon.sqlhelper.core.beans.JoinType;
import pub.avalon.sqlhelper.core.engine.LimitIntactEngine;
import pub.avalon.sqlhelper.core.engine.SqlEngine;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalon.sqlhelper.factory.SqlDynamicEngine;
import pub.avalon.sqlhelper.readme.entity.RoleResourceDTO;
import pub.avalon.sqlhelper.readme.entity.SysUserDTO;
import pub.avalon.sqlhelper.readme.entity.UserRoleDTO;

/**
 * Created by 白超 on 2019/5/9.
 */
public class Test {

    public static void main(String[] args) {
        RoleResourceDTO.Helper.Column column = RoleResourceDTO.Helper.column().id().id();
        SysUserDTO.Helper.Column joinColumn = SysUserDTO.Helper.column().userName().userName("");
        RoleResourceDTO.Helper.Where where = RoleResourceDTO.Helper.where().id().equalTo("1").id().like("");
        RoleResourceDTO.Helper.Group group = RoleResourceDTO.Helper.group().id().id();
        SysUserDTO.Helper.Group joinGroup = SysUserDTO.Helper.group().userName().userName();
        RoleResourceDTO.Helper.Sort sort = RoleResourceDTO.Helper.sort().id().asc().id().desc();
        SysUserDTO.Helper.Sort joinSort = SysUserDTO.Helper.sort().userName().asc().userName().desc();

        SqlEngine sqlEngine = SqlDynamicEngine.table("", RoleResourceDTO.Helper.class)
                .innerJoin(SysUserDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.userName().equalTo(mainTable.id()))
                        .and(mainTable.id().equalTo(joinTable.userName()))
                        .or(joinTable.userName().equalTo("")))
                .join(JoinType.INNER, UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(SysUserDTO.Helper.class, table -> table.userName().userName())))
                .innerJoin(SysUserDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(o -> o
                                .and(joinTable.userName().equalTo(mainTable.id()))
                                .or(mainTable.id().equalTo(joinTable.userName())))
                        .or(o -> o
                                .and(joinTable.userName().equalTo(mainTable.id()))
                                .or(mainTable.id().equalTo(joinTable.userName()))))
                .column(table -> table.id().id())
                .column(table -> table.id().id().id(""))
                .column(SysUserDTO.Helper.class, table -> table.userName().userName(""))
                .column(SysUserDTO.Helper.class, "", table -> table.userName().userName(""))
                .column(table -> column)
                .column(column)
                .column(column, column, column, joinColumn)
                .column(SysUserDTO.Helper.class, table -> joinColumn)
/*                .subQuery("sys_user", SysUserDTO.Helper.class, "SysUserDTO", query -> query
                        .where((condition, mainTable) -> condition
                                .and(mainTable.userName().equalTo(""))).query(), "subQuery")*/
                .virtualColumn(1, "")
                .functionColumn(FunctionColumnType.COUNT, table -> table.id("").id(""))
                .functionColumn(SysUserDTO.Helper.class, FunctionColumnType.MIN, table -> table.userName().userName(""))
                .functionColumn(SysUserDTO.Helper.class, "", FunctionColumnType.MIN, table -> table.userName().userName(""))
                .where((condition, mainTable) -> condition
                        .and(mainTable.id().equalTo("")
                                .id().greaterThan("")))
                .where((condition, mainTable) -> condition
                        .and(cd -> cd
                                .and(mainTable.id().equalTo(""))
                                .or(mainTable.id().equalTo(""))))
                .where(SysUserDTO.Helper.class, (condition, joinTable, mainTable) -> condition
                        .and(mainTable.id().equalTo(joinTable.userName()))
                        .and(joinTable.userName().equalTo(mainTable.id()))
                        .or(joinTable.userName().equalTo(mainTable.id())))
                .where(SysUserDTO.Helper.class, (condition, joinTable, mainTable) -> condition
                        .and(cd -> cd
                                .and(joinTable.userName().equalTo(mainTable.id()))
                                .or(joinTable.userName().equalTo(mainTable.id())))
                        .or(cd -> cd
                                .and(joinTable.userName().equalTo(mainTable.id()))))
                .where((condition, mainTable) -> condition
                        .and(UserRoleDTO.Helper.class, (cd, joinTable) -> cd
                                .and(joinTable.roleId().equalTo(""))
                                .and(joinTable.roleId().equalTo(mainTable.id()))
                                .and(mainTable.id().equalTo(joinTable.roleId()))))
                .where(SysUserDTO.Helper.class, (condition, joinTable, mainTable) -> condition
                        .and(UserRoleDTO.Helper.class, (cd, UserRoleTable) -> cd
                                .and(joinTable.userName().equalTo(UserRoleTable.roleId())))
                        .or(UserRoleDTO.Helper.class, (cd, UserRoleTable) -> cd
                                .and(joinTable.userName().equalTo(UserRoleTable.roleId()))))
                .where((condition, mainTable) -> condition
                        .and(mainTable.id().equalTo(SysUserDTO.Helper.class, table -> table.userName().userName())))
/*                .where((condition, mainTable) -> condition
                        .and(mainTable.id().equalToSubQuery("sys_user", SysUserDTO.Helper.class, "SysUserDTO", query -> query
                                .where((cd, mt) -> cd
                                        .and(mt.userName().equalTo(""))).query())))*/
                .where((condition, mainTable) -> condition.and(where))
                .group(table -> table.id().id())
                .group(SysUserDTO.Helper.class, table -> table.userName().userName())
                .group(table -> group)
                .group(SysUserDTO.Helper.class, table -> joinGroup)
                .group(group, joinGroup)
                .sort(table -> table.id().asc().id().desc())
                .sort(SysUserDTO.Helper.class, table -> table.userName().asc().userName().desc())
                .sort(table -> sort)
                .sort(SysUserDTO.Helper.class, table -> joinSort)
                .sort(sort, joinSort)
                .limitOne();


        sqlEngine.query();
        sqlEngine.updateArgsByPrimaryKey("", "");

    }

}

