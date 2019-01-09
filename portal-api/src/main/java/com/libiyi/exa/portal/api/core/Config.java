package com.libiyi.exa.portal.api.core;

import com.libiyi.exa.common.client.ExaServerClient;
import com.libiyi.exa.common.client.ThriftProxyFactory;
import com.libiyi.exa.common.service.ExaServerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean("exaServerService")
    public ExaServerService.Iface exaServerService(){
        ExaServerService.Iface client = ExaServerClient.getClient();
        //String[] hostPorts = new String[]{"127.0.0.1:7911"};
        //ExaServerService.Iface client = (ExaServerService.Iface) ThriftProxyFactory.newInstance(ExaServerService.class, hostPorts);
        return client;
    }
}
