package pub.avalon.sqlhelper.readme.model;

import pub.avalon.beans.DataBaseType;
import pub.avalon.sqlhelper.core.beans.GroupType;
import pub.avalon.sqlhelper.core.beans.JoinType;
import pub.avalon.sqlhelper.core.engine.Sql;
import pub.avalon.sqlhelper.core.engine.SqlColumn;
import pub.avalon.sqlhelper.core.engine.SqlHelperEngine;
import pub.avalon.sqlhelper.core.engine.SqlJoin;
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

        SqlHelperEngine sqlEngine = SqlDynamicEngine.table(DataBaseType.MYSQL, "", RoleResourceDTO.Helper.class)

                .sql(new Sql<RoleResourceDTO.Helper, RoleResourceDTO.Helper.Join, RoleResourceDTO.Helper.Column, RoleResourceDTO.Helper.Where, RoleResourceDTO.Helper.Group, RoleResourceDTO.Helper.Having, RoleResourceDTO.Helper.Sort>() {{

                    if (true) {
                        column(table -> table.id().resourceName());
                    }

                }})
                .sql(new RoleResourceDTO.Helper.Sql())


                .sqlColumn(new SqlColumn<RoleResourceDTO.Helper.Column>() {
                }.column(table -> table.id().primaryKey()))
                .sqlColumn(new SqlColumn<RoleResourceDTO.Helper.Column>() {{

                    if (true) {
                        column(table -> table.id().resourceName());
                    }

                }})
                .sqlColumn(new RoleResourceDTO.Helper.SqlColumn().column(table -> table.id().resourceId()))
                .sqlColumn(new SqlColumn<SysUserDTO.Helper.Column>() {
                }.column(table -> table.id().loginName()))
                .sqlColumn(new RoleResourceDTO.Helper.SqlColumn() {{

                    column(SysUserDTO.Helper.class, table -> table.userName().userName(""));

                }})

                .sqlJoin(new SqlJoin<SysUserDTO.Helper.Join>() {{


                }})

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
                .groupColumn(GroupType.COUNT, table -> table.id("").id(""))
                .groupColumn(SysUserDTO.Helper.class, GroupType.MIN, table -> table.userName().userName(""))
                .groupColumn(SysUserDTO.Helper.class, "", GroupType.MIN, table -> table.userName().userName(""))
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

