package pub.avalon.sqlhelper.core.helper;

import pub.avalon.sqlhelper.core.builder.HavingSqlPartDatumBuilder;
import pub.avalon.sqlhelper.core.data.HavingDatum;

/**
 * 分组条件助手
 *
 * @author 白超
 * @date 2019/7/18
 */
public class HavingHelper<T extends HavingHelper<T>> extends Helper<T, HavingDatum> {

    public HavingHelper(String tableAlias) {
        super(tableAlias, new HavingSqlPartDatumBuilder<>(tableAlias));
    }

    @Override
    protected HavingSqlPartDatumBuilder<T> apply(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias) {
        return (HavingSqlPartDatumBuilder<T>) super.apply(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias);
    }

}
