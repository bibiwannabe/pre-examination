package com.libiyi.exa.server.service;


import com.libiyi.exa.common.thrift.TRResponse;
import com.libiyi.exa.common.thrift.TRSubjectTagList;
import com.libiyi.exa.server.entity.SubjectTag;

public interface SubjectTagService {

    TRResponse createSubjectTag(String tagName);

    TRSubjectTagList getAllSubject();

    SubjectTag getById(Integer id);
}
