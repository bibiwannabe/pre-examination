package com.libiyi.exa.portal.api.core;

import com.libiyi.exa.common.client.ServerInfo;
import com.libiyi.exa.common.client.ThriftTransportPool;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class ThriftTransportPoolBean {

    public ThriftTransportPool getPool() {
        return pool;
    }

    ThriftTransportPool pool;

    @PostConstruct
    public void init() {
        pool = new ThriftTransportPool(15, 1, 5, 10, getServers());
        System.out.println("老子初始化了");
    }

    private static List<ServerInfo> getServers() {
        List<ServerInfo> servers = new ArrayList<ServerInfo>();
        servers.add(new ServerInfo("localhost", 7911));
        return servers;
    }
}
