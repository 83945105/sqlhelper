package pub.avalon.sqlhelper.core.builder;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 模组数据构建器
 *
 * @author 白超
 * @date 2019/5/2
 */
public abstract class AbstractModelDataBuilder<T, E> implements ModelDataBuilder<T> {

    private Set<E> modelData = null;

    /**
     * 添加模组数据
     *
     * @param modelDatum 模组数据
     */
    protected void addModelData(E modelDatum) {
        if (modelDatum == null) {
            return;
        }
        if (this.modelData == null) {
            this.modelData = new LinkedHashSet<>();
        }
        this.modelData.add(modelDatum);
    }

    /**
     * 取出模组数据
     * 取出后将清空模组数据
     *
     * @return
     */
    public Set<E> takeoutModelData() {
        Set<E> modelData = this.modelData;
        this.modelData = null;
        return modelData;
    }

}
