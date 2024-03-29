package com.libiyi.exa.server.dao;

import com.libiyi.exa.server.dto.QuestionModifyDto;
import com.libiyi.exa.server.entity.QuestionInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuestionInfoMapper {

    Integer addQuestionInfo(QuestionInfo q);

    List<QuestionInfo> getByParam(@Param("subjectId") Integer subjectId, @Param("type") Integer type ,@Param("offset") Integer offset,  @Param("size") Integer size);

    Integer updateById(@Param("q") QuestionModifyDto q);

    Integer getCountsByParam(@Param("subjectId") Integer subjectId, @Param("type") Integer type);

    Integer updateCorrectNumById(@Param("id") Integer id);

    Integer updateCountsById(@Param("id") Integer id);

    List<QuestionInfo> getByIdList(@Param("idList") List<Integer> idList);

    QuestionInfo getById(@Param("id")Integer id);

    List<QuestionInfo> getBySearchKeywords(@Param("subjectId") Integer subjectId, @Param("type") Integer type ,@Param("keyWords") String keyWords);

    Integer batchCreateQuestion(List<QuestionInfo> questionInfoList);
}
