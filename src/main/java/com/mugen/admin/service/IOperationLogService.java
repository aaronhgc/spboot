package com.mugen.admin.service;



import com.mugen.admin.model.OperationLog;

import java.util.List;
import java.util.Map;

/**
 * 积分明细服务
 * Created by aaron on 2016/11/25.
 */
public interface IOperationLogService {

    /**
     * 操作日志查询
     *
     * @param param
     * @return
     */
    List<OperationLog> getOperationLogList(Map param);

    /**
     * 操作日志列表数量
     *
     * @param param
     * @return
     */
    int getOperationLogCount(Map param);

    /**
     * 新增日志
     *
     */
    void addLog(OperationLog operationLog);
}
