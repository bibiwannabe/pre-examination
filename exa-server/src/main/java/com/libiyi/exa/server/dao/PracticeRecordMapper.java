package com.libiyi.exa.server.dao;

import com.libiyi.exa.server.entity.PracticeRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PracticeRecordMapper {

    Integer createRecord(@Param("r")PracticeRecord r);

    PracticeRecord getById(@Param("id")Integer id);

    PracticeRecord getRecentOne(@Param("userId")Integer userId);
}
