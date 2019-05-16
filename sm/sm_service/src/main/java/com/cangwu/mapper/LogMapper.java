package com.cangwu.mapper;

import com.cangwu.entity.Log;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Cangwu
 * @Date: 2019/5/15 14:15
 */
@Repository("logMapper")
public interface LogMapper {

    @Insert("insert into log" +
            " values(#{oprTime},#{type}," +
            "#{operator},#{moudle}," +
            "#{operation},#{result})")
    void insertLog(Log log);

    @Results(id = "staff",value = {
            @Result(property = "oprTime", column="opr_time", jdbcType = JdbcType.DATE),
            @Result(property = "type", column = "type", jdbcType = JdbcType.VARCHAR),
            @Result(property = "operator", column = "operater", jdbcType = JdbcType.VARCHAR),
            @Result(property = "moudle", column = "model", jdbcType = JdbcType.VARCHAR),
            @Result(property = "operation", column = "operation", jdbcType = JdbcType.VARCHAR),
            @Result(property = "result", column = "result", jdbcType = JdbcType.VARCHAR)
    })
    @Select({"select * from log where type=#{type} order by opr_time desc"})
    List<Log> selectByType(String type);
}
