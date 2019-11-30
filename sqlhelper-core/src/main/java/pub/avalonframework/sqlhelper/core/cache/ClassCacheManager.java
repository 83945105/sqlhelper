package pub.avalonframework.sqlhelper.core.cache;

import com.esotericsoftware.reflectasm.ConstructorAccess;
import com.esotericsoftware.reflectasm.FieldAccess;
import com.esotericsoftware.reflectasm.MethodAccess;
import pub.avalonframework.sqlhelper.core.cache.core.CacheConfigurationBuilder;
import pub.avalonframework.sqlhelper.core.cache.core.CacheManager;
import pub.avalonframework.sqlhelper.core.cache.core.CacheManagerBuilder;
import pub.avalonframework.sqlhelper.core.spi.cache.Cache;
import pub.avalonframework.sqlhelper.core.utils.ExceptionUtils;

/**
 * @author baichao
 */
public class ClassCacheManager {

    private final static String FIELD_ACCESS_CACHE_NAME = "SQLHELPER_FIELD_ACCESS_CACHE_NAME";

    private final Cache<Class, FieldAccess> FIELD_ACCESS_CACHE;

    private final static String METHOD_ACCESS_CACHE_NAME = "SQLHELPER_METHOD_ACCESS_CACHE_NAME";

    private final Cache<Class, MethodAccess> METHOD_ACCESS_CACHE;

    private final static String CONSTRUCTOR_ACCESS_CACHE_NAME = "SQLHELPER_CONSTRUCTOR_ACCESS_CACHE_NAME";

    private final Cache<Class, ConstructorAccess> CONSTRUCTOR_ACCESS_CACHE;

    private ClassCacheManager() {
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
        FIELD_ACCESS_CACHE = cacheManager.createCache(FIELD_ACCESS_CACHE_NAME, CacheConfigurationBuilder.newCacheConfigurationBuilder(Class.class, FieldAccess.class));
        METHOD_ACCESS_CACHE = cacheManager.createCache(METHOD_ACCESS_CACHE_NAME, CacheConfigurationBuilder.newCacheConfigurationBuilder(Class.class, MethodAccess.class));
        CONSTRUCTOR_ACCESS_CACHE = cacheManager.createCache(CONSTRUCTOR_ACCESS_CACHE_NAME, CacheConfigurationBuilder.newCacheConfigurationBuilder(Class.class, ConstructorAccess.class));
    }

    private final static ClassCacheManager CLASS_CACHE_MANAGER = new ClassCacheManager();

    public static ClassCacheManager getInstance() {
        return CLASS_CACHE_MANAGER;
    }

    public FieldAccess accessFieldAccess(Class clazz) {
        if (clazz == null) {
            ExceptionUtils.classNullPointerException();
        }
        FieldAccess fieldAccess = FIELD_ACCESS_CACHE.get(clazz);
        if (fieldAccess == null) {
            synchronized (FIELD_ACCESS_CACHE) {
                fieldAccess = FieldAccess.get(clazz);
                FIELD_ACCESS_CACHE.put(clazz, fieldAccess);
            }
        }
        return fieldAccess;
    }

    public MethodAccess accessMethodAccess(Class clazz) {
        if (clazz == null) {
            ExceptionUtils.classNullPointerException();
        }
        MethodAccess methodAccess = METHOD_ACCESS_CACHE.get(clazz);
        if (methodAccess == null) {
            synchronized (METHOD_ACCESS_CACHE) {
                methodAccess = MethodAccess.get(clazz);
                METHOD_ACCESS_CACHE.put(clazz, methodAccess);
            }
        }
        return methodAccess;
    }

    @SuppressWarnings("unchecked")
    public <T> ConstructorAccess<T> accessConstructorAccess(Class<T> clazz) {
        if (clazz == null) {
            ExceptionUtils.classNullPointerException();
        }
        ConstructorAccess constructorAccess = CONSTRUCTOR_ACCESS_CACHE.get(clazz);
        if (constructorAccess == null) {
            synchronized (METHOD_ACCESS_CACHE) {
                constructorAccess = ConstructorAccess.get(clazz);
                CONSTRUCTOR_ACCESS_CACHE.put(clazz, constructorAccess);
            }
        }
        return constructorAccess;
    }

    public <T> T newInstance(Class<T> clazz) {
        return accessConstructorAccess(clazz).newInstance();
    }
}