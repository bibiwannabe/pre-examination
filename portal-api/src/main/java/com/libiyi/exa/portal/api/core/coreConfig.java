package com.libiyi.exa.portal.api.core;

import com.libiyi.exa.common.client.ExaServerClient;
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

    @Bean("ExaServerService")
    @Scope("prototype")
    public ExaServerService.Iface exaServerService() {
        TSocket socket = (TSocket)thriftTransportPoolBean.getPool().get();
        ExaServerService.Iface client = ExaServerClient.getClient(socket);
        return client;
    }

}
