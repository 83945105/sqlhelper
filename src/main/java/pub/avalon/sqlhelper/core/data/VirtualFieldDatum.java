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

    public void setValue(String value) {
        this.value = value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
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