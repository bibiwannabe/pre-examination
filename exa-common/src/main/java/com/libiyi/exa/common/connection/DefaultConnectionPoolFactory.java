package com.libiyi.exa.common.connection;

public class DefaultConnectionPoolFactory implements IConnectionPoolFactory {
    @Override
    public IConnectionPool getConnectionPool(Class ifaceClazz) {
        return new ThriftConnectionPool(new ConnectionPoolConfig());
    }
}
