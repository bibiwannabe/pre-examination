package com.libiyi.exa.common.client;

public class ThriftProxyFactory {
    public static Object newInstance(Class classT, String[] hostPorts) {
        ThriftProxy thriftProxy = new ThriftProxy();
        return thriftProxy.newInstance(classT, hostPorts);
    }
}
