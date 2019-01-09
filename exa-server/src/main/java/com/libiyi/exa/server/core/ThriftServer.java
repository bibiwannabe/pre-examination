package com.libiyi.exa.server.core;

import com.libiyi.exa.common.service.ExaServerService;
import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;


public class ThriftServer {

    public static void start(TProcessor tProcessor, int port) {
        //classes HelloServiceImpl.class
        try {
            TServerSocket serverTransport = new TServerSocket(port);
            TBinaryProtocol.Factory protocolFactory = new TBinaryProtocol.Factory();
            TThreadPoolServer.Args args = new TThreadPoolServer.Args(serverTransport);
            args.protocolFactory(protocolFactory);
            args.processor(tProcessor);
            args.minWorkerThreads(1000);
            args.maxWorkerThreads(1000);
            TServer server = new TThreadPoolServer(args);
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
