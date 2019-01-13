package com.libiyi.exa.server.dao;

import com.libiyi.exa.server.entity.SubjectTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SubjectTagMapper {
    Integer createSubjectTag(@Param("t")SubjectTag t);

    SubjectTag getById(@Param("id") Integer id);

    List<SubjectTag> getAll();
}
