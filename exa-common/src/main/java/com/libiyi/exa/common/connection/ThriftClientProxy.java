package com.libiyi.exa.common.connection;

import org.apache.thrift.TServiceClient;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

class ThriftClientProxy implements InvocationHandler {
    private Class<?> ifaceClass;
    private Class<?> iClientClass;
    private ThriftClientConfig config;
    private IConnectionPool<ConnectionKey, TServiceClient> connectionPool;
    private ServerInstance serviceInstance;

    public ThriftClientProxy(Class<?> ifaceClass, ThriftClientConfig config) {
        this.ifaceClass = ifaceClass;
        this.config = config;
        this.iClientClass = getIClientClass(ifaceClass);
        this.connectionPool = config.getConnectionPoolFactory().getConnectionPool(ifaceClass);
        serviceInstance = config.getServiceDiscovery();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        List<ServerInstance> invalidInstances = new ArrayList<ServerInstance>();
        while (true) {

            if (serviceInstance == null) {
                throw new RuntimeException("Can't find a valid server instance!");
            }
            ConnectionKey connectionKey = new ConnectionKey(iClientClass, serviceInstance, config.getTimeout());
            TServiceClient client = this.connectionPool.borrowObject(connectionKey);
            if (client == null) {
                throw new RuntimeException("Can't borrow client from server: " + serviceInstance);
            }
            boolean exceptionOccurred = false;
            try {
                return method.invoke(client, args);
            } catch (Exception e) {
                exceptionOccurred = true;
                this.connectionPool.discardObject(connectionKey, client);
                throw new RuntimeException(e);
            } finally {
                if (!exceptionOccurred) {
                    this.connectionPool.returnObject(connectionKey, client);
                }
            }
        }
    }

    public Class<?> getIfaceClass() {
        return ifaceClass;
    }

    public void setIfaceClass(Class<?> ifaceClass) {
        this.ifaceClass = ifaceClass;
    }

    public Class<?> getIClientClass() {
        return iClientClass;
    }

    public void setIclientClass(Class<?> iclientClass) {
        this.iClientClass = iclientClass;
    }

    public ThriftClientConfig getConfig() {
        return config;
    }

    public void setConfig(ThriftClientConfig config) {
        this.config = config;
    }

    public IConnectionPool<ConnectionKey, TServiceClient> getConnectionPool() {
        return connectionPool;
    }

    public void setConnectionPool(IConnectionPool<ConnectionKey, TServiceClient> connectionPool) {
        this.connectionPool = connectionPool;
    }

    public ServerInstance getServiceDiscovery() {
        return serviceInstance;
    }

    public void setServiceDiscovery(ServerInstance serviceDiscovery) {
        this.serviceInstance = serviceDiscovery;
    }

    public Class<?> getIClientClass(Class<?> ifaceClass) {
        //get client class name
        String simpleName = ifaceClass.getName();
        String name = simpleName;
        if (simpleName.endsWith("$Iface")) {
            name = simpleName.substring(0, simpleName.indexOf("$Iface"));
        }
        name = name + "$Client";
        //verify client class exist
        Class<?> clientClass;
        try {
            clientClass = Class.forName(name);
            return clientClass;
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + name);
            return null;
        }
    }
}
