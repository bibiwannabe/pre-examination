package com.libiyi.exa.common.connection;

public class ConnectionPoolConfig {

    private boolean blockWhenExhausted = false;

    private long maxWaitTimeInMillis = 10;

    private int maxTotalPerKey = 100;

    private int maxIdlePerKey = 5;

    private long timeBetweenEvictionRunsMillis = 5000;

    private int maxTotal = 500;

    private boolean testOnBorrow = false;

    public boolean isBlockWhenExhausted() {
        return blockWhenExhausted;
    }

    public void setBlockWhenExhausted(boolean blockWhenExhausted) {
        this.blockWhenExhausted = blockWhenExhausted;
    }

    public long getMaxWaitTimeInMillis() {
        return maxWaitTimeInMillis;
    }

    public void setMaxWaitTimeInMills(long maxWaitTimeInMillis) {
        this.maxWaitTimeInMillis = maxWaitTimeInMillis;
    }

    public int getMaxTotalPerKey() {
        return maxTotalPerKey;
    }

    public void setMaxTotalPerKey(int maxTotalPerKey) {
        this.maxTotalPerKey = maxTotalPerKey;
    }

    public int getMaxIdlePerKey() {
        return maxIdlePerKey;
    }

    public void setMaxIdlePerKey(int maxIdlePerKey) {
        this.maxIdlePerKey = maxIdlePerKey;
    }

    public long getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public void setTimeBetweenEvictionRunsMillis(long timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }
}
