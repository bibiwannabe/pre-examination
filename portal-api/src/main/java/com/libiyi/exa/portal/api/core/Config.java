package com.libiyi.exa.portal.api.core;

import com.libiyi.exa.common.connection.*;
import com.libiyi.exa.common.service.ExaServerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class Config {

    @Bean("exaServerService")
    public ExaServerService.Iface exaServerService(){
        ThriftClientConfig thriftClientConfig = new ThriftClientConfig();
        thriftClientConfig.setConnectionPoolFactory(new DefaultConnectionPoolFactory());
        thriftClientConfig.setTimeout(60000);
        thriftClientConfig.setServiceDiscovery(new ServerInstance("localhost", 7911));
        return ThriftClientFactory.getClient(ExaServerService.Iface.class, thriftClientConfig);
    }
}
