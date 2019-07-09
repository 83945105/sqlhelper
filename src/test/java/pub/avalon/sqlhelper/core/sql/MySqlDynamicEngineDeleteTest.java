package pub.avalon.sqlhelper.core.sql;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pub.avalon.sqlhelper.core.beans.ComparisonRule;
import pub.avalon.sqlhelper.core.sqlbuilder.SqlBuilder;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalon.sqlhelper.readme.entity.SysUserDTO;

import java.util.Arrays;

/**
 * MySql动态引擎 - 删除
 */
public class MySqlDynamicEngineDeleteTest {

    /**
     * 主键删除
     */
    @Test
    void Test_deleteByPrimaryKey() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .deleteByPrimaryKey("1");
        Assertions.assertEquals("delete from `sys_user` where `id` = ?", sqlBuilder.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{"1"}, sqlBuilder.getPreparedStatementArgs().toArray());
    }

    /**
     * 主键删除 - 指定表名
     */
    @Test
    void Test_deleteByPrimaryKey_assignTableName() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table("sys_user_custom", SysUserDTO.Helper.class)
                .deleteByPrimaryKey("1");
        Assertions.assertEquals("delete from `sys_user_custom` where `id` = ?", sqlBuilder.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{"1"}, sqlBuilder.getPreparedStatementArgs().toArray());
    }

    /**
     * 主键批量删除
     */
    @Test
    void Test_batchDeleteByPrimaryKeys() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .batchDeleteByPrimaryKeys("1", "2");
        Assertions.assertEquals("delete from `sys_user` where `id` in (?,?)", sqlBuilder.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{"1", "2"}, sqlBuilder.getPreparedStatementArgs().toArray());
    }

    /**
     * 主键批量删除 - 指定表名
     */
    @Test
    void Test_batchDeleteByPrimaryKeys_assignTableName() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table("sys_user_custom", SysUserDTO.Helper.class)
                .batchDeleteByPrimaryKeys("1", "2");
        Assertions.assertEquals("delete from `sys_user_custom` where `id` in (?,?)", sqlBuilder.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{"1", "2"}, sqlBuilder.getPreparedStatementArgs().toArray());
    }

    /**
     * 条件删除
     */
    @Test
    void Test_delete() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .delete();
        Assertions.assertEquals("delete SysUser from `sys_user` SysUser", sqlBuilder.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilder.getPreparedStatementArgs().toArray());
    }

    /**
     * 条件删除 - 指定表名
     */
    @Test
    void Test_delete_assignTableName() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table("sys_user_custom", SysUserDTO.Helper.class)
                .delete();
        Assertions.assertEquals("delete SysUser from `sys_user_custom` SysUser", sqlBuilder.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{}, sqlBuilder.getPreparedStatementArgs().toArray());
    }

    /**
     * 条件删除 - 所有Where条件
     */
    @Test
    void Test_delete_allWhere() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable

                                .id().isNull()

                                .id().isNotNull()

                                .id().equalTo("A-1")
                                .id().equalTo("A-2", ComparisonRule.NULL_SKIP)

                                .id().notEqualTo("B-1")
                                .id().notEqualTo("B-2", ComparisonRule.NULL_SKIP)

                                .id().greaterThan("C-1")
                                .id().greaterThan("C-2", ComparisonRule.NULL_SKIP)

                                .id().greaterThanAndEqualTo("D-1")
                                .id().greaterThanAndEqualTo("D-2", ComparisonRule.NULL_SKIP)

                                .id().lessThan("E-1")
                                .id().lessThan("E-2", ComparisonRule.NULL_SKIP)

                                .id().lessThanAndEqualTo("F-1")
                                .id().lessThanAndEqualTo("F-2", ComparisonRule.NULL_SKIP)

                                .id().between("G-1", "G-2")
                                .id().between("G-3", "G-4", ComparisonRule.NULL_SKIP)

                                .id().like("H-1")
                                .id().like("H-2", ComparisonRule.NULL_SKIP)

                                .id().in(new Object[]{"I-1", "I-2"}, ComparisonRule.NULL_SKIP)

                                .id().in(ComparisonRule.NULL_SKIP)
                                .id().in(ComparisonRule.NULL_SKIP, "J-1")
                                .id().in(ComparisonRule.NULL_SKIP, new Object[]{"J-2", "J-3"})
                                .id().in("J-4")
                                .id().in(new Object[]{"J-5", "J-6"})
                                .id().in("J-7", "J-8")

                                .id().in(Arrays.asList("K-1", "K-2"))
                                .id().in(Arrays.asList("K-3", "K-4"), ComparisonRule.NULL_SKIP)

                                .id().notIn(new Object[]{"L-1", "L-2"}, ComparisonRule.NULL_SKIP)

                                .id().notIn(ComparisonRule.NULL_SKIP)
                                .id().notIn(ComparisonRule.NULL_SKIP, "M-1")
                                .id().notIn(ComparisonRule.NULL_SKIP, new Object[]{"M-2", "M-3"})
                                .id().notIn("M-4")
                                .id().notIn(new Object[]{"M-5", "M-6"})
                                .id().notIn("M-7", "M-8")

                                .id().notIn(Arrays.asList("N-1", "N-2"))
                                .id().notIn(Arrays.asList("N-3", "N-4"), ComparisonRule.NULL_SKIP)
                        ))
                .delete();
        Assertions.assertEquals("delete SysUser from `sys_user` SysUser where SysUser.`id` is null and SysUser.`id` is not null and SysUser.`id` = ? and SysUser.`id` = ? and SysUser.`id` != ? and SysUser.`id` != ? and SysUser.`id` > ? and SysUser.`id` > ? and SysUser.`id` >= ? and SysUser.`id` >= ? and SysUser.`id` < ? and SysUser.`id` < ? and SysUser.`id` <= ? and SysUser.`id` <= ? and SysUser.`id` between ? and ? and SysUser.`id` between ? and ? and SysUser.`id` like ? and SysUser.`id` like ? and SysUser.`id` in (?,?) and SysUser.`id` in (?) and SysUser.`id` in (?,?) and SysUser.`id` in (?) and SysUser.`id` in (?,?) and SysUser.`id` in (?,?) and SysUser.`id` in (?,?) and SysUser.`id` in (?,?) and SysUser.`id` not in (?,?) and SysUser.`id` not in (?) and SysUser.`id` not in (?,?) and SysUser.`id` not in (?) and SysUser.`id` not in (?,?) and SysUser.`id` not in (?,?) and SysUser.`id` not in (?,?) and SysUser.`id` not in (?,?)",
                sqlBuilder.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{
                "A-1", "A-2",
                "B-1", "B-2",
                "C-1", "C-2",
                "D-1", "D-2",
                "E-1", "E-2",
                "F-1", "F-2",
                "G-1", "G-2", "G-3", "G-4",
                "H-1", "H-2",
                "I-1", "I-2",
                "J-1", "J-2", "J-3", "J-4", "J-5", "J-6", "J-7", "J-8",
                "K-1", "K-2", "K-3", "K-4",
                "L-1", "L-2",
                "M-1", "M-2", "M-3", "M-4", "M-5", "M-6", "M-7", "M-8",
                "N-1", "N-2", "N-3", "N-4"
        }, sqlBuilder.getPreparedStatementArgs().toArray());
    }

    /**
     * 条件删除 - 组合Where条件
     */
    @Test
    void Test_delete_combinationWhere() {
        SqlBuilder sqlBuilder = MySqlDynamicEngine.table(SysUserDTO.Helper.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable
                                .id().greaterThan("A-1").id().greaterThan("A-2")
                                .id().greaterThanAndEqualTo("B-1").id().greaterThanAndEqualTo("B-2"))
                        .and(mainTable
                                .id().lessThan("C-1").id().lessThan("C-2")
                                .id().lessThanAndEqualTo("D-1").id().lessThanAndEqualTo("D-2"))
                        .or(mainTable
                                .id().equalTo("E-1"))

                        .or(mainTable
                                .id().equalTo("F-1").id().equalTo("F-2"))
                        .or(cd -> cd
                                .and(mainTable.id().equalTo("G-1").id().equalTo("G-2"))
                                .and(mainTable.id().equalTo("H-1")))
                        .and(cd -> cd
                                .and(mainTable.id().equalTo("I-1"))
                                .or(mainTable.id().equalTo("J-1"))))
                .where((condition, mainTable) -> condition
                        .and(mainTable.id().equalTo("K-1")))
                .delete();
        Assertions.assertEquals("delete SysUser from `sys_user` SysUser where (SysUser.`id` > ? and SysUser.`id` > ? and SysUser.`id` >= ? and SysUser.`id` >= ? and SysUser.`id` < ? and SysUser.`id` < ? and SysUser.`id` <= ? and SysUser.`id` <= ? or SysUser.`id` = ? or (SysUser.`id` = ? and SysUser.`id` = ?) or (SysUser.`id` = ? and SysUser.`id` = ? and SysUser.`id` = ?) and (SysUser.`id` = ? or SysUser.`id` = ?)) and SysUser.`id` = ?",
                sqlBuilder.getPreparedStatementSql());
        Assertions.assertArrayEquals(new Object[]{
                "A-1", "A-2",
                "B-1", "B-2",
                "C-1", "C-2",
                "D-1", "D-2",
                "E-1",
                "F-1", "F-2",
                "G-1", "G-2",
                "H-1",
                "I-1",
                "J-1",
                "K-1"
        }, sqlBuilder.getPreparedStatementArgs().toArray());
    }
}
