package com.libiyi.exa.common.connection;

public class ThriftClientConfig {
    public MyLoadBalancer getLoadBalancer() {
        return loadBalancer;
    }

    public void setLoadBalancer(MyLoadBalancer loadBalancer) {
        this.loadBalancer = loadBalancer;
    }

    public IConnectionPoolFactory getConnectionPoolFactory() {
        return connectionPoolFactory;
    }

    public void setConnectionPoolFactory(IConnectionPoolFactory connectionPoolFactory) {
        this.connectionPoolFactory = connectionPoolFactory;
    }

    public ServerInstance getServiceDiscovery() {
        return serviceDiscovery;
    }

    public void setServiceDiscovery(ServerInstance serviceDiscovery) {
        this.serviceDiscovery = serviceDiscovery;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    private MyLoadBalancer loadBalancer;
    private IConnectionPoolFactory connectionPoolFactory;
    private ServerInstance serviceDiscovery;
    private int timeout;
}
