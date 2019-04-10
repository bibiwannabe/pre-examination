package com.libiyi.exa.server.core;

import com.libiyi.exa.common.service.ExaServerService;
import com.libiyi.exa.server.thriftServer.ServerServiceImpl;
import org.apache.thrift.TProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ThriftServerStartBean {

    @Autowired
    private ServerServiceImpl serverService;

    @PostConstruct
    public void start() {
        new Thread() {
            @Override
            public void run() {
                TProcessor processor = new ExaServerService.Processor(serverService);
                ThriftServer.start(processor, 7911);
            }
        }.start();
    }
}
