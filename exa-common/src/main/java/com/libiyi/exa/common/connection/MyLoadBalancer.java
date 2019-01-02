package com.libiyi.exa.common.connection;

import java.util.List;

public class MyLoadBalancer implements ILoadBalancer {
    @Override
    public ServerInstance getServerInstance(List<ServerInstance> all, List<ServerInstance> blacklist) {
        return all.get(0);
    }
}
