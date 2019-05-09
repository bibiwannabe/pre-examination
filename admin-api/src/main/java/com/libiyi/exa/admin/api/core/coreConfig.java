package com.libiyi.exa.admin.api.core;

import com.libiyi.exa.common.client.ExaServerClient;
import com.libiyi.exa.common.client.ThriftTransportPool;
import com.libiyi.exa.common.service.ExaServerService;
import org.apache.thrift.transport.TSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class coreConfig {

    @Autowired
    private ThriftTransportPoolBean thriftTransportPoolBean;

    private TSocket client;

    @Bean(value = "ExaServerService")
    @Scope("prototype")
    public ExaServerService.Iface exaServerService() {
        TSocket socket = (TSocket)thriftTransportPoolBean.getPool().get();
        client = socket;
        ExaServerService.Iface client = ExaServerClient.getClient(socket);
        return client;
    }

    public void destroyClient() {
        thriftTransportPoolBean.destroyClient(client);
    }
}
