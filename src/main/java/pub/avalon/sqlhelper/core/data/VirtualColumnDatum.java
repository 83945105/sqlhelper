package pub.avalon.sqlhelper.core.data;

/**
 * 虚拟属性数据
 *
 * @author baichao
 * @since 2018/7/10
 */
public final class VirtualColumnDatum {

    private Object value;

    private String alias;

    public Object getValue() {
        return value;
    }

    public VirtualColumnDatum setValue(Object value) {
        this.value = value;
        return this;
    }

    public String getAlias() {
        return alias;
    }

    public VirtualColumnDatum setAlias(String alias) {
        this.alias = alias;
        return this;
    }

}
