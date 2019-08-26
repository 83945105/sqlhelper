package pub.avalon.sqlhelper.core.cache;

/**
 * @author baichao
 */
public interface CacheConfiguration<K, V> {

    Class<K> getKeyType();

    Class<V> getValueType();
}