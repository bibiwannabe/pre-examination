package com.libiyi.exa.server.dao;


import com.libiyi.exa.server.entity.PaperQuestionData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PaperQuestionDataMapper {

    Integer createData(@Param("d") PaperQuestionData d);

    List<PaperQuestionData> getFiveByPaperId(@Param("paperId") Integer paperId);
}
