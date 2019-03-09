package com.libiyi.exa.server.core;

import com.libiyi.exa.common.service.ExaServerService;
import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.TProcessor;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TServerSocket;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ThriftServer {

    public static void start(TProcessor tProcessor, int port) {
        //classes HelloServiceImpl.class
        try {
            /*TServerSocket serverTransport = new TServerSocket(port);
            TBinaryProtocol.Factory protocolFactory = new TBinaryProtocol.Factory();
            TThreadPoolServer.Args args = new TThreadPoolServer.Args(serverTransport);
            args.protocolFactory(protocolFactory);
            args.processor(tProcessor);
            args.minWorkerThreads(1000);
            args.maxWorkerThreads(1000);
            TServer server = new TThreadPoolServer(args);*/
            // 非阻塞式的，配合TFramedTransport使用
            TNonblockingServerTransport serverTransport = new TNonblockingServerSocket(port);
            // 目前Thrift提供的最高级的模式，可并发处理客户端请求
            TThreadedSelectorServer.Args args = new TThreadedSelectorServer.Args(serverTransport);
            args.processor(tProcessor);
            // 设置协议工厂，高效率的、密集的二进制编码格式进行数据传输协议
            args.protocolFactory(new TCompactProtocol.Factory());
            // 设置传输工厂，使用非阻塞方式，按块的大小进行传输，类似于Java中的NIO
            args.transportFactory(new TFramedTransport.Factory());
            // 设置处理器工厂,只返回一个单例实例
            args.processorFactory(new TProcessorFactory(tProcessor));
            // 多个线程，主要负责客户端的IO处理
            args.selectorThreads(10);
            // 工作线程池
            ExecutorService pool = Executors.newFixedThreadPool(11);
            args.executorService(pool);
            TThreadedSelectorServer server = new TThreadedSelectorServer(args);
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
