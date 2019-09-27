package pub.avalon.sqlhelper.readme.model;

import pub.avalon.beans.DataBaseType;
import pub.avalon.sqlhelper.core.beans.GroupType;
import pub.avalon.sqlhelper.core.beans.JoinType;
import pub.avalon.sqlhelper.core.engine.SqlHelperEngine;
import pub.avalon.sqlhelper.core.engine.builder.SqlColumn;
import pub.avalon.sqlhelper.core.engine.builder.SqlJoin;
import pub.avalon.sqlhelper.core.engine.builder.SqlOn;
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
        SysUserDTO.Helper.On join = SysUserDTO.Helper.on().id().equalTo(column.resourceName());

        SysUserDTO.Helper.Column joinColumn = SysUserDTO.Helper.column().userName().userName("");
        RoleResourceDTO.Helper.Where where = RoleResourceDTO.Helper.where().id().equalTo("1").id().like("");
        RoleResourceDTO.Helper.Group group = RoleResourceDTO.Helper.groupBy().id().id();
        SysUserDTO.Helper.Group joinGroup = SysUserDTO.Helper.groupBy().userName().userName();
        RoleResourceDTO.Helper.Sort sort = RoleResourceDTO.Helper.orderBy().id().asc().id().desc();
        SysUserDTO.Helper.Sort joinSort = SysUserDTO.Helper.orderBy().userName().asc().userName().desc();

        SqlHelperEngine sqlEngine = SqlDynamicEngine.table(DataBaseType.MYSQL, "", RoleResourceDTO.Helper.class)

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

                .sqlJoin(new SqlJoin<SysUserDTO.Helper.On>() {{

                }})

                .sqlOn(new SqlOn<SysUserDTO.Helper.On>() {{


                }})

                .column(table -> table.id().sqlPart("").resourceId())
                .column(table -> table.id().id().id(""))
                .column(SysUserDTO.Helper.class, table -> table.userName().userName("").id(GroupType.COUNT))
                .column(SysUserDTO.Helper.class, "", table -> table.userName().userName(""))
                .column(table -> column)
                .column(column)
                .column(column, column, column, joinColumn)
                .column(SysUserDTO.Helper.class, table -> joinColumn)

                .subQueryColumn("", parentTable -> {
                    return MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                            .where((condition, mainTable) -> condition
                                    .and(mainTable.id().equalTo(parentTable.resourceName())))
                            .query();
                })

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
                .on(UserRoleDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.roleId().equalTo(mainTable.roleId())))
                .innerJoin(SysUserDTO.Helper.class, (on, joinTable, mainTable) -> on
                        .and(o -> o
                                .and(joinTable.userName().equalTo(mainTable.id()))
                                .or(mainTable.id().equalTo(joinTable.userName())))
                        .or(o -> o
                                .and(joinTable.userName().equalTo(mainTable.id()))
                                .or(mainTable.id().equalTo(joinTable.userName()))))
                .where((condition, mainTable) -> condition
                        .and(where)
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
                .where((condition, mainTable) -> condition
                        .and(mainTable.id().equalToSubQuery(() -> {


                            return MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                                    .where((cd, mt) -> cd
                                            .and(mt.id().equalTo("")));


                        })))
                .where((condition, mainTable) -> condition.and(where))
                .groupBy(table -> table.id().id())
                .groupBy(SysUserDTO.Helper.class, table -> table.userName().userName())
                .groupBy(table -> group)
                .groupBy(SysUserDTO.Helper.class, table -> joinGroup)
                .groupBy(group, joinGroup)


                .orderBy(table -> table.id().asc().id().desc())
                .orderBy(SysUserDTO.Helper.class, table -> table.userName().asc().userName().desc())
                .orderBy(table -> sort)
                .orderBy(SysUserDTO.Helper.class, table -> joinSort)
                .orderBy(sort, joinSort)
                .limitOne();


        sqlEngine.query();
        sqlEngine.updateArgsByPrimaryKey("", "");

    }

}

