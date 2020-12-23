package com.fuguoliang.druiddemo.demo.mapper;

import com.fuguoliang.druiddemo.demo.model.OperationLogs;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OperationLogsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OperationLogs record);

    int insertSelective(OperationLogs record);

    OperationLogs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OperationLogs record);

    int updateByPrimaryKey(OperationLogs record);
}