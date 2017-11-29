package com.framework.distributed.common.base;

/**
 * @Author: Jingyan
 * @Time: 2017-11-29 12:18
 * @Description:
 */
public class BaseVo {

    //全局唯一,生产者产生
    private String uuid;
    //消费状态，默认需要消费
    private boolean consumeStatus = true;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public boolean isConsumeStatus() {
        return consumeStatus;
    }

    public void setConsumeStatus(boolean consumeStatus) {
        this.consumeStatus = consumeStatus;
    }
}