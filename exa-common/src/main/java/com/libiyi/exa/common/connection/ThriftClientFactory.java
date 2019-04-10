package com.libiyi.exa.common.connection;

import org.apache.thrift.TServiceClient;

import java.lang.reflect.Proxy;

public class ThriftClientFactory {
    public static <T> T getClient(Class<T> ifaceClazz, ThriftClientConfig config) {
        ThriftClientProxy proxy = new ThriftClientProxy(ifaceClazz, config);
        T clientProxy = (T) Proxy.newProxyInstance(ifaceClazz.getClassLoader(),
                new Class<?>[]{ifaceClazz, TServiceClient.class}, proxy);
        return clientProxy;
    }
}
