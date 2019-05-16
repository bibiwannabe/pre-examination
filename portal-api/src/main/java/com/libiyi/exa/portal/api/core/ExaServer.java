package com.libiyi.exa.portal.api.core;

import com.libiyi.exa.common.service.ExaServerService;
import org.apache.thrift.transport.TSocket;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by bibi on 2019/5/15.
 */
public class ExaServer {

    @Autowired
    private ThriftTransportPoolBean thriftTransportPoolBean;

    private TSocket client;

    public ExaServerService.Client Iface;

    public ExaServer(TSocket client, ExaServerService.Client iface) {
        this.client = client;
        Iface = iface;
    }

    public TSocket getClient() {
        return client;
    }

    public void setClient(TSocket client) {
        this.client = client;
    }

    public ExaServerService.Client getIface() {
        return Iface;
    }

    public void setIface(ExaServerService.Client iface) {
        Iface = iface;
    }

}
