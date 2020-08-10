package com.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface TestMapper {

    @Update("update like_log set new_id = #{newID} where like_log_id = #{id} ")
    void update(@Param("id") String id,@Param("newID") String newID);

    @Update("update like_log set new_id = #{newID} where browse_ip = #{ip} ")
    void update1(@Param("newID") String newID,@Param("ip") String ip);

    @Select("select * from like_log ")
    List<Map<String, Object>> select();
}
