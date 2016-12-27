package com.mugen.admin.model;

import java.io.Serializable;


/**
 * 分销发放明细表
 * Created by aaron on 2016/11/25.
 */
public class OperationLog implements Serializable {

    private long operationId;    //操作ID
    private String content;    //内容
    private int operationTime;    //操作时间
    private String operator;    //操作人

    public long getOperationId() {
        return operationId;
    }

    public void setOperationId(long operationId) {
        this.operationId = operationId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(int operationTime) {
        this.operationTime = operationTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}