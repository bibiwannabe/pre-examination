package com.libiyi.exa.common.connection;

import java.util.List;

public interface ILoadBalancer {
    /**
     * choose one thriftServer instance
     *
     * @param all       all available thriftServer instances
     * @param blacklist thriftServer instance can't be connected during one request
     * @return
     */
    ServerInstance getServerInstance(List<ServerInstance> all, List<ServerInstance> blacklist);

}
