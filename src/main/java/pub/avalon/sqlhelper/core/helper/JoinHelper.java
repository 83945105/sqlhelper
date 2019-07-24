package pub.avalon.sqlhelper.core.helper;

import pub.avalon.sqlhelper.core.builder.JoinSqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.builder.SqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.data.OnDatum;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.List;
import java.util.Set;

/**
 * Join 助手
 *
 * @author 白超
 * @date 2019/5/18
 */
public abstract class JoinHelper<T extends JoinHelper<T>> extends Helper {

    private JoinSqlPartDatumBuilder<T> joinSqlPartDatumBuilder;

    public JoinHelper(String tableAlias) {
        super(tableAlias);
        this.joinSqlPartDatumBuilder = new JoinSqlPartDatumBuilder<>(tableAlias, (T) this);
    }

    /**
     * 接收数据
     *
     * @param templateTableName   模板表名
     * @param templateTableAlias  模板表别名
     * @param templateColumnName  模板列名
     * @param templateColumnAlias 模板列别名
     * @return {@link SqlPartDatumBuilder}
     */
    protected JoinSqlPartDatumBuilder<T> apply(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias) {
        this.joinSqlPartDatumBuilder.accept(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias);
        return this.joinSqlPartDatumBuilder;
    }

    public List<OnDatum> takeoutSqlPartData() {
        return this.joinSqlPartDatumBuilder.takeoutSqlPartData();
    }

    /**
     * 设置Sql构建配置
     *
     * @param sqlBuilderOptions {@link SqlBuilderOptions}
     */
    public void setSqlBuilderOptions(SqlBuilderOptions sqlBuilderOptions) {
        this.joinSqlPartDatumBuilder.setSqlBuilderOptions(sqlBuilderOptions);
    }
}
