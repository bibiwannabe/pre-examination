package com.libiyi.exa.server.dao;

import com.libiyi.exa.server.entity.WrongRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WrongRecordMapper {

    List<WrongRecord> getListByUserAndSubjectId(@Param("userId") Integer userId, @Param("subjectId") Integer subjectId, @Param("offset") Integer offset, @Param("size") Integer size);

    Integer getCountByUserAndSubjectId(@Param("userId") Integer userId, @Param("subjectId") Integer subjectId);

    Integer createRecord(@Param("r") WrongRecord r);
}
