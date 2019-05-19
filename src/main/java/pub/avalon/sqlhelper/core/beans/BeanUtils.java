package pub.avalon.sqlhelper.core.beans;

import pub.avalon.sqlhelper.core.modelbuilder.TableModel;

/**
 * @author 白超
 * @date 2019/5/19
 */
public class BeanUtils {

    private BeanUtils() {
    }

    public static <T extends TableModel> T tableModel(Class<T> clazz) {
        return org.springframework.beans.BeanUtils.instantiateClass(clazz);
    }

    public static void copyProperties(Object source, Object target) {
        org.springframework.beans.BeanUtils.copyProperties(source, target);
    }
}
