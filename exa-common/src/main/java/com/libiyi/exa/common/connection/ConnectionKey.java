package com.libiyi.exa.common.connection;

public class ConnectionKey {

    private Class<?> iClientClass;

    private ServerInstance serverInstance;

    private int timeout;

    public ConnectionKey(Class<?> iClientClass, ServerInstance serverInstance, int timeout) {
        this.iClientClass = iClientClass;
        this.serverInstance = serverInstance;
        this.timeout = timeout;
    }

    public Class<?> getiClientClass() {
        return iClientClass;
    }

    public void setiClientClass(Class<?> iClientClass) {
        this.iClientClass = iClientClass;
    }

    public ServerInstance getServerInstance() {
        return serverInstance;
    }

    public void setServerInstance(ServerInstance serverInstance) {
        this.serverInstance = serverInstance;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeOut(int timeout) {
        this.timeout = timeout;
    }
}
