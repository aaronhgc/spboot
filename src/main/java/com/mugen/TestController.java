package com.mugen;

import com.mugen.admin.model.OperationLog;
import com.mugen.admin.service.IOperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by aaron on 2016/12/22.
 */
@RestController
public class TestController {
    @Autowired
    IOperationLogService operationLogService;
    @RequestMapping("/")
    String home() {
        return "hello";
    }

    @RequestMapping("/log/add")
    String add_log() {
        OperationLog operationLog=new OperationLog();
        operationLog.setOperator("hgcadmin");
        operationLog.setContent("dddd45555");
        operationLog.setOperationId(1);
        operationLog.setOperationTime(4545454);
        operationLogService.addLog(operationLog);
        return "hello33";
    }

}
