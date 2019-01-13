package com.libiyi.exa.server.dao;

import com.libiyi.exa.server.dto.QuestionModifyDto;
import com.libiyi.exa.server.entity.QuestionInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuestionInfMapper {

    Integer createQuestionInfo(@Param("q") QuestionInfo q);

    List<QuestionInfo> getBySubjectOffset(@Param("subjectId") Integer subjectId, @Param("offset") Integer offset,  @Param("size") Integer size);

    Integer updateById(@Param("q") QuestionModifyDto q);

    Integer updateCorrectNumById(@Param("id") Integer id);

    Integer updateCountsById(@Param("id") Integer id);

}
