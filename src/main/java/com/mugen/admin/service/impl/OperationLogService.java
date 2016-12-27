package com.mugen.admin.service.impl;


import com.mugen.admin.dao.IOperationLogDao;
import com.mugen.admin.model.OperationLog;
import com.mugen.admin.service.IOperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 积分明细服务
 * Created by aaron on 2016/11/25.
 */
@Service
public class OperationLogService implements IOperationLogService {

    @Autowired
    private IOperationLogDao operationLogDao;

    /**
     * 操作日志查询
     *
     * @param param
     * @return
     */
    @Override
    public List<OperationLog> getOperationLogList(Map param) {
        return operationLogDao.getOperationLogList(param);
    }

    /**
     * 操作日志列表数量
     *
     * @param param
     * @return
     */
    @Override
    public int getOperationLogCount(Map param) {
        return operationLogDao.getOperationLogCount(param);
    }

    /**
     * 新增日志
     *
     * @param operationLog
     */
    @Override
    public void addLog(OperationLog operationLog) {
        operationLogDao.addLog(operationLog);
    }
}
