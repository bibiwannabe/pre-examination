package com.libiyi.exa.server.dao;

import com.libiyi.exa.server.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserInfoMapper {

   Integer insert(@Param("u") UserInfo userInfo);

   Integer getEmailNumCount(@Param("email") String email);

    UserInfo getByEmail(@Param("email") String email);

    UserInfo getById(@Param("id") Integer id);

    Integer updateNameById(@Param("name") String name, @Param("id") Integer id);
}
