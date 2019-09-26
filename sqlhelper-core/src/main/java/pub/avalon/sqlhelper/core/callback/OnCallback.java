package pub.avalon.sqlhelper.core.callback;

import pub.avalon.sqlhelper.core.beans.OnAndOr;
import pub.avalon.sqlhelper.core.beans.OnLinker;
import pub.avalon.sqlhelper.core.data.OnDataLinker;
import pub.avalon.sqlhelper.core.data.TableOnDatum;
import pub.avalon.sqlhelper.core.helper.OnHelper;
import pub.avalon.sqlhelper.core.option.SqlBuilderOptions;

import java.util.List;

/**
 * @author baichao
 */
@FunctionalInterface
public interface OnCallback<TO extends OnHelper<TO>, SO extends OnHelper<SO>> {

    OnLinker<TO, SO> apply(OnLinker<TO, SO> on, SO joinTable, TO mainTable);

    static <FO extends OnHelper<FO>,
            EO extends OnHelper<EO>> TableOnDatum execute(FO mainOnHelper, EO joinOnHelper, OnCallback<FO, EO> onCallback, SqlBuilderOptions sqlBuilderOptions) {
        if (onCallback == null) {
            return null;
        }
        mainOnHelper.setSqlBuilderOptions(sqlBuilderOptions);
        joinOnHelper.setSqlBuilderOptions(sqlBuilderOptions);
        OnLinker<FO, EO> onLinker = onCallback.apply(new OnAndOr<>(), joinOnHelper, mainOnHelper);
        List<OnDataLinker> onDataLinkers = onLinker.takeoutOnDataLinkers();
        if (onDataLinkers == null || onDataLinkers.size() == 0) {
            return null;
        }
        return new TableOnDatum(joinOnHelper.getTableAlias(), onDataLinkers);
    }
}