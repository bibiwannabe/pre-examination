package com.libiyi.exa.common.connection;

import org.apache.commons.pool2.BaseKeyedPooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.thrift.TServiceClient;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import java.lang.reflect.Constructor;

public class ThriftConnectionKeyedPooledObjectFactory extends BaseKeyedPooledObjectFactory<ConnectionKey, TServiceClient> {
    @Override
    public TServiceClient create(ConnectionKey key) throws Exception {
        //create client
        try {
            TSocket socket = new TSocket(key.getServerInstance().getIp(), key.getServerInstance().getPort(), key.getTimeout());
            TTransport transport = new TFramedTransport(socket);
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            Constructor<?> cons = key.getiClientClass().getConstructor(TProtocol.class);
            TServiceClient res = (TServiceClient) cons.newInstance(protocol);
            return res;
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PooledObject<TServiceClient> wrap(TServiceClient value) {
        if (value == null) {
            return null;
        }
        return new DefaultPooledObject<TServiceClient>(value);
    }
}
