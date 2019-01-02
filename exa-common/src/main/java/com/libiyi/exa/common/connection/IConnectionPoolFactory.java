package com.libiyi.exa.common.connection;

public interface IConnectionPoolFactory<K, V> {
    /**
     * get a connection pool based on provided ifaceClazz
     *
     * @param ifaceClazz  (server class definition)
     * @return
     */
    IConnectionPool<K,V> getConnectionPool(Class<?> ifaceClazz);
}
