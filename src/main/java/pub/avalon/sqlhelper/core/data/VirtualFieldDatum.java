package pub.avalon.sqlhelper.core.data;

import java.util.Objects;

/**
 * 虚拟属性数据
 *
 * @author 白超
 * @version 1.0
 * @since 2018/7/10
 */
public final class VirtualFieldDatum {

    private Object value;

    private String alias;

    public Object getValue() {
        return value;
    }

    public VirtualFieldDatum setValue(Object value) {
        this.value = value;
        return this;
    }

    public String getAlias() {
        return alias;
    }

    public VirtualFieldDatum setAlias(String alias) {
        this.alias = alias;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VirtualFieldDatum that = (VirtualFieldDatum) o;
        return Objects.equals(getValue(), that.getValue()) &&
                Objects.equals(getAlias(), that.getAlias());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue(), getAlias());
    }
}
