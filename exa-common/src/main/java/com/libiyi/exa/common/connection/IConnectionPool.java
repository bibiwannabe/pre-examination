package com.libiyi.exa.common.connection;

public interface IConnectionPool<K, V> {
    V borrowObject(K key);

    void returnObject(K key, V value);

    void discardObject(K key, V value);

    void shutdown();
}
