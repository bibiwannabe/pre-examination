package com.libiyi.exa.common.client;

import com.libiyi.exa.common.service.ExaServerService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.*;

public class ExaServerClient {
    public static ExaServerService.Client getClient(TSocket socket) {
        //使用非阻塞方式，按块的大小进行传输，类似于Java中的NIO。记得调用close释放资源
        TTransport transport =
                new TFramedTransport(socket);
        //高效率的、密集的二进制编码格式进行数据传输协议
        TProtocol protocol = new TCompactProtocol(transport);
            return new ExaServerService.Client(protocol);
    }
}
