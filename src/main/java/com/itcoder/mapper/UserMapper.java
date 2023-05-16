package com.itcoder.mapper;

import com.itcoder.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository("userMapper")
public interface UserMapper {
    @Select("select user_id," +
            "user_name," +
            "user_password," +
            "user_email," +
            "user_role," +
            "user_status " +
            "from user " +
            "where user_email = #{userEmail}")
    @Results({
            @Result(column = "user_id",property = "userId"),
            @Result(column = "user_name",property = "userName"),
            @Result(column = "user_password",property = "userPassword"),
            @Result(column = "user_email",property = "userEmail"),
            @Result(column = "user_role",property = "userRole"),
            @Result(column = "user_status",property = "userStatus")
    })
    User getUserByEmail(@Param("userEmail") String userEmail);
}
