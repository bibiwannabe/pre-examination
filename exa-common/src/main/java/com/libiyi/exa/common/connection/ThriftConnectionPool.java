package com.libiyi.exa.common.connection;

import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import org.apache.thrift.TServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ThriftConnectionPool implements IConnectionPool<ConnectionKey, TServiceClient> {
    private static Logger logger = LoggerFactory.getLogger(ThriftConnectionPool.class);
    private GenericKeyedObjectPool<ConnectionKey, TServiceClient> connectionPool;
    private ConnectionPoolConfig config;

    public ThriftConnectionPool(ConnectionPoolConfig config) {
        this.connectionPool = new GenericKeyedObjectPool<ConnectionKey, TServiceClient>(new ThriftConnectionKeyedPooledObjectFactory());
        this.config = config;
        this.connectionPool.setMaxTotal(config.getMaxTotal());
        this.connectionPool.setMaxTotalPerKey(config.getMaxTotalPerKey());
        this.connectionPool.setMaxIdlePerKey(config.getMaxIdlePerKey());
        this.connectionPool.setBlockWhenExhausted(config.isBlockWhenExhausted());
        this.connectionPool.setMaxWaitMillis(config.getMaxWaitTimeInMillis());
        this.connectionPool.setTestOnBorrow(config.isTestOnBorrow());
        this.connectionPool.setTestOnReturn(true);
        this.connectionPool.setTimeBetweenEvictionRunsMillis(config.getTimeBetweenEvictionRunsMillis());
    }

    @Override
    public TServiceClient borrowObject(ConnectionKey key) {
        TServiceClient client = null;
        try {
            client = this.connectionPool.borrowObject(key);
        } catch (Exception e) {
            logger.error("borrow client with key={} failed, Exception: {}", key, e);
            return null;
        }
        return client;
    }

    @Override
    public void returnObject(ConnectionKey key, TServiceClient client) {
        this.connectionPool.returnObject(key, client);
    }

    @Override
    public void discardObject(ConnectionKey key, TServiceClient value) {
        try {
            this.connectionPool.invalidateObject(key, value);
        } catch (Exception e) {
            logger.warn("discard client failed! key={}, Exception={}", key, e);
        }
    }

    @Override
    public void shutdown() {
        this.connectionPool.close();
    }

}
