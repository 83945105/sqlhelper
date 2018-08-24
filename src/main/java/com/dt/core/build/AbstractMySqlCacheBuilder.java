package com.dt.core.build;

import com.dt.core.data.SqlData;
import com.dt.core.norm.Model;
import com.dt.core.sql.SqlSplicer;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

/**
 * @author 白超
 * @date 2018/8/24
 */
public abstract class AbstractMySqlCacheBuilder<M extends Model> extends AbstractMySqlBuilder<M> {

    public AbstractMySqlCacheBuilder(SqlData<M> sqlData) {
        super(sqlData);
    }

    private static final String SQL_SPLICER_CACHE_NAME = "SqlSplicerCache";
    private static final Cache<String, SqlSplicer> SQL_SPLICER_CACHE;

    static {
        CacheManager cacheManager = CacheManagerBuilder
                .newCacheManagerBuilder()
                .withCache(SQL_SPLICER_CACHE_NAME,
                        CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, SqlSplicer.class,
                                ResourcePoolsBuilder.heap(100)).build())
                .build(true);
        SQL_SPLICER_CACHE = cacheManager.getCache(SQL_SPLICER_CACHE_NAME, String.class, SqlSplicer.class);
    }
}
