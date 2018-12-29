package com.libiyi.exa.server.dao;

import com.libiyi.exa.server.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Mapper
public interface UserInfoMapper {

   Integer insert(@Param("u") UserInfo userInfo);

}
