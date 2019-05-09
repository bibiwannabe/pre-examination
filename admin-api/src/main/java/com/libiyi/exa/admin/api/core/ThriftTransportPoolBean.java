package com.libiyi.exa.admin.api.core;

import com.libiyi.exa.common.client.ServerInfo;
import com.libiyi.exa.common.client.ThriftTransportPool;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
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
        pool = new ThriftTransportPool(20, 5, 5, 10, getServers());
        System.out.println("老子初始化了");
    }

    private static List<ServerInfo> getServers() {
        List<ServerInfo> servers = new ArrayList<ServerInfo>();
        servers.add(new ServerInfo("localhost", 7911));
        servers.add(new ServerInfo("localhost", 7932));
        return servers;
    }

    public void destroyClient(TSocket client) {
        pool.release(client);
    }
}
