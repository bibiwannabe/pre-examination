package com.libiyi.exa.server.dao;

import com.libiyi.exa.server.entity.AdminInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminInfoMapper {
    Integer createAdminInfo(@Param("i") AdminInfo adminInfo);

    AdminInfo getByUserId(@Param("userId") Integer userId);
}
