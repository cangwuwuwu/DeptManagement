package com.cangwu.mapper;

import com.cangwu.entity.Staff;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Cangwu
 * @Date: 2019/5/9 19:14
 */
@Repository("staffMapper")
public interface StaffMapper {

    @Insert({"insert into staff(id,account," +
            "password,status,did,name,sex," +
            "id_number,work_time,born_date,info) " +
            "values(#{id},#{account},#{password}," +
            "#{status},#{did},#{name},#{sex},#{idNumber}," +
            "#{workTime},#{bornDate},#{info})"})
    void insert(Staff staff);

    @Delete({"delete from staff where id=#{id}"})
    void delete(Integer id);

    @Update({"update staff set leave_time=#{leaveTime}," +
            "status=#{status} where id=#{id}"})
    void leaveWork(Staff staff);

    @Update({"update staff set status=#{status}," +
            "did=#{did},name=#{name},sex=#{sex}," +
            "password=#{password},id_number=#{idNumber}," +
            "born_date=#{bornDate},info=#{info} where id=#{id}"})
    void update(Staff staff);

    @Results(id = "staff",value = {
            @Result(column = "id",property = "id",id=true,jdbcType = JdbcType.INTEGER),
            @Result(column = "account",property = "account",jdbcType = JdbcType.VARCHAR),
            @Result(column = "password",property = "password",jdbcType = JdbcType.VARCHAR),
            @Result(column = "status",property = "status",jdbcType = JdbcType.VARCHAR),
            @Result(column = "did",property = "did",jdbcType = JdbcType.INTEGER),
            @Result(column = "name",property = "name",jdbcType = JdbcType.VARCHAR),
            @Result(column = "sex",property = "sex",jdbcType = JdbcType.VARCHAR),
            @Result(column = "id_Number",property = "idNumber",jdbcType = JdbcType.VARCHAR),
            @Result(column = "work_time",property = "workTime",jdbcType = JdbcType.DATE),
            @Result(column = "leave_time",property = "leaveTime",jdbcType = JdbcType.DATE),
            @Result(column = "born_date",property = "bornDate",jdbcType = JdbcType.DATE),
            @Result(column = "info",property = "info",jdbcType = JdbcType.VARCHAR),
            @Result(property = "department",column = "did",
                    many = @Many(
                            select="com.cangwu.mapper.DepartmentMapper.selectById",
                            fetchType = FetchType.LAZY
                    ))
    })
    @Select({"select * from staff"})
    List<Staff> selectAll();

    @ResultMap("staff")
    @Select({"select * from staff where id = #{id}"})
    Staff selectById(Integer id);
}
