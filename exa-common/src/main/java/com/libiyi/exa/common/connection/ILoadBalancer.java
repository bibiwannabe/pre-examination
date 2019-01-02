package com.libiyi.exa.common.connection;

import java.util.List;

public interface ILoadBalancer {
    /**
     * choose one server instance
     *
     * @param all       all available server instances
     * @param blacklist server instance can't be connected during one request
     * @return
     */
    ServerInstance getServerInstance(List<ServerInstance> all, List<ServerInstance> blacklist);

}
