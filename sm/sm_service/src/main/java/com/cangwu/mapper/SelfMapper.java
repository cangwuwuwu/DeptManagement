package com.cangwu.mapper;

import com.cangwu.entity.Staff;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Author: Cangwu
 * @Date: 2019/5/12 10:14
 */
@Repository("selfMapper")
public interface SelfMapper {

    @ResultMap("com.cangwu.mapper.StaffMapper.staff")
    @Select("select * from staff where account=#{account}")
    Staff selectByAccount(String account);
}
