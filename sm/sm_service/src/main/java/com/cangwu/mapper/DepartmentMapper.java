package com.cangwu.mapper;

import com.cangwu.entity.Department;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Cangwu
 * @Date: 2019/4/29 21:02
 */
@Repository("departmentMapper")
public interface DepartmentMapper {

    @Insert({"insert into department(id,name,address) values(#{id},#{name},#{address})"})
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void insert(Department department);

    @Delete({"delete from department where id=#{id}"})
    void delete(Integer id);

    @Update({"update department set name=#{name},address=#{address} where id=#{id}"})
    void update(Department department);

    /**
     * 当column与property不一致时 使用@result注解 一致则忽略
     */
    @Results(id = "department",value = {
            @Result(column = "id",property = "id",id=true,jdbcType = JdbcType.INTEGER),
            @Result(column = "name",property = "name",jdbcType = JdbcType.VARCHAR),
            @Result(column = "address",property = "address",jdbcType = JdbcType.VARCHAR),
    })
    @Select({"select * from department where id=#{id}"})
    Department selectById(Integer id);

    /**
     * 查询所有数据
     * @return
     */
    @ResultMap("department")
    @Select({"select * from department"})
    List<Department> selectAll();
}
