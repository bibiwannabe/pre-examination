package com.libiyi.exa.server.dao;

import com.libiyi.exa.server.dto.PaperInfoModifyDto;
import com.libiyi.exa.server.entity.PaperInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PaperInfoMapper {

    Integer createPaperInfo(@Param("p") PaperInfo p);

    List<PaperInfo> getByParam(@Param("subjectId") Integer subjectId, @Param("offset") Integer offset, @Param("size") Integer size);

    Integer updateById(@Param("p") PaperInfoModifyDto p);

    Integer getCountsByParam(@Param("subjectId") Integer subjectId);

    Integer updateAvgAndCountsByIdAndPoints(@Param("points") Integer points);

    PaperInfo getById(@Param("id") Integer id);

}
