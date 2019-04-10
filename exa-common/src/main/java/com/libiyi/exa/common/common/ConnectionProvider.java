package com.libiyi.exa.common.common;

import org.apache.thrift.transport.TSocket;

public interface ConnectionProvider {

    TSocket getConnection();

    void returnCon(TSocket socket);

}
