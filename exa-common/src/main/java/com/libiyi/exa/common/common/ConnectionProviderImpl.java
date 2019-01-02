package com.libiyi.exa.common.common;

import com.sun.media.jfxmediaimpl.MediaDisposer;
import com.sun.org.apache.xml.internal.utils.ObjectPool;
import javafx.fxml.Initializable;
import org.apache.thrift.transport.TSocket;

import java.net.URL;
import java.util.ResourceBundle;

public class ConnectionProviderImpl implements ConnectionProvider, Initializable, MediaDisposer.Disposable {
    private String serviceIP;

    private int servicePort;

    private int conTimeOut;

    private int maxActive = 15;

    private int maxIdle = 10;

    private int minIdle = 1;

    private long maxWait = 60000;

    private boolean testOnBorrow = true;

    private boolean testOnReturn = true;

    private boolean testWhileIdle = true;

    private ObjectPool objectPool = null;

    @Override
    public TSocket getConnection() {
        return null;
    }

    @Override
    public void returnCon(TSocket socket) {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
