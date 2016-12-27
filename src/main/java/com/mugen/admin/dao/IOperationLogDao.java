package com.mugen.admin.dao;

import com.mugen.admin.model.OperationLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 操作日志Dao
 * Created by aaron on 2016/11/25.
 */
@Mapper
public interface IOperationLogDao {


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
     *        content, operator, operation_time
     */
    @Insert("INSERT INTO DS_OPERATION_LOG (content, operator, operation_time) VALUES (#{content}, #{operator}, #{operationTime})")
    @Options(useGeneratedKeys = true)
    void addLog2(OperationLog operationLog);



    void addLog(OperationLog operationLog);
}
