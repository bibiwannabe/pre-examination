/**
 * Autogenerated by Thrift Compiler (0.11.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.libiyi.exa.common.thrift;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.11.0)", date = "2019-01-17")
public class TRPortalWrongQuestionInfoList implements org.apache.thrift.TBase<TRPortalWrongQuestionInfoList, TRPortalWrongQuestionInfoList._Fields>, java.io.Serializable, Cloneable, Comparable<TRPortalWrongQuestionInfoList> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TRPortalWrongQuestionInfoList");

  private static final org.apache.thrift.protocol.TField QUESTION_INFO_LIST_FIELD_DESC = new org.apache.thrift.protocol.TField("questionInfoList", org.apache.thrift.protocol.TType.LIST, (short)1);
  private static final org.apache.thrift.protocol.TField RESPONSE_FIELD_DESC = new org.apache.thrift.protocol.TField("response", org.apache.thrift.protocol.TType.STRUCT, (short)2);
  private static final org.apache.thrift.protocol.TField PAGINATION_FIELD_DESC = new org.apache.thrift.protocol.TField("pagination", org.apache.thrift.protocol.TType.STRUCT, (short)3);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new TRPortalWrongQuestionInfoListStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new TRPortalWrongQuestionInfoListTupleSchemeFactory();

  public java.util.List<TPortalWrongQuestionInfo> questionInfoList; // required
  public TRResponse response; // required
  public TPagination pagination; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    QUESTION_INFO_LIST((short)1, "questionInfoList"),
    RESPONSE((short)2, "response"),
    PAGINATION((short)3, "pagination");

    private static final java.util.Map<String, _Fields> byName = new java.util.HashMap<String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // QUESTION_INFO_LIST
          return QUESTION_INFO_LIST;
        case 2: // RESPONSE
          return RESPONSE;
        case 3: // PAGINATION
          return PAGINATION;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.QUESTION_INFO_LIST, new org.apache.thrift.meta_data.FieldMetaData("questionInfoList", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRUCT            , "TPortalWrongQuestionInfo"))));
    tmpMap.put(_Fields.RESPONSE, new org.apache.thrift.meta_data.FieldMetaData("response", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, TRResponse.class)));
    tmpMap.put(_Fields.PAGINATION, new org.apache.thrift.meta_data.FieldMetaData("pagination", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, TPagination.class)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TRPortalWrongQuestionInfoList.class, metaDataMap);
  }

  public TRPortalWrongQuestionInfoList() {
  }

  public TRPortalWrongQuestionInfoList(
    java.util.List<TPortalWrongQuestionInfo> questionInfoList,
    TRResponse response,
    TPagination pagination)
  {
    this();
    this.questionInfoList = questionInfoList;
    this.response = response;
    this.pagination = pagination;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TRPortalWrongQuestionInfoList(TRPortalWrongQuestionInfoList other) {
    if (other.isSetQuestionInfoList()) {
      java.util.List<TPortalWrongQuestionInfo> __this__questionInfoList = new java.util.ArrayList<TPortalWrongQuestionInfo>(other.questionInfoList.size());
      for (TPortalWrongQuestionInfo other_element : other.questionInfoList) {
        __this__questionInfoList.add(new TPortalWrongQuestionInfo(other_element));
      }
      this.questionInfoList = __this__questionInfoList;
    }
    if (other.isSetResponse()) {
      this.response = new TRResponse(other.response);
    }
    if (other.isSetPagination()) {
      this.pagination = new TPagination(other.pagination);
    }
  }

  public TRPortalWrongQuestionInfoList deepCopy() {
    return new TRPortalWrongQuestionInfoList(this);
  }

  @Override
  public void clear() {
    this.questionInfoList = null;
    this.response = null;
    this.pagination = null;
  }

  public int getQuestionInfoListSize() {
    return (this.questionInfoList == null) ? 0 : this.questionInfoList.size();
  }

  public java.util.Iterator<TPortalWrongQuestionInfo> getQuestionInfoListIterator() {
    return (this.questionInfoList == null) ? null : this.questionInfoList.iterator();
  }

  public void addToQuestionInfoList(TPortalWrongQuestionInfo elem) {
    if (this.questionInfoList == null) {
      this.questionInfoList = new java.util.ArrayList<TPortalWrongQuestionInfo>();
    }
    this.questionInfoList.add(elem);
  }

  public java.util.List<TPortalWrongQuestionInfo> getQuestionInfoList() {
    return this.questionInfoList;
  }

  public TRPortalWrongQuestionInfoList setQuestionInfoList(java.util.List<TPortalWrongQuestionInfo> questionInfoList) {
    this.questionInfoList = questionInfoList;
    return this;
  }

  public void unsetQuestionInfoList() {
    this.questionInfoList = null;
  }

  /** Returns true if field questionInfoList is set (has been assigned a value) and false otherwise */
  public boolean isSetQuestionInfoList() {
    return this.questionInfoList != null;
  }

  public void setQuestionInfoListIsSet(boolean value) {
    if (!value) {
      this.questionInfoList = null;
    }
  }

  public TRResponse getResponse() {
    return this.response;
  }

  public TRPortalWrongQuestionInfoList setResponse(TRResponse response) {
    this.response = response;
    return this;
  }

  public void unsetResponse() {
    this.response = null;
  }

  /** Returns true if field response is set (has been assigned a value) and false otherwise */
  public boolean isSetResponse() {
    return this.response != null;
  }

  public void setResponseIsSet(boolean value) {
    if (!value) {
      this.response = null;
    }
  }

  public TPagination getPagination() {
    return this.pagination;
  }

  public TRPortalWrongQuestionInfoList setPagination(TPagination pagination) {
    this.pagination = pagination;
    return this;
  }

  public void unsetPagination() {
    this.pagination = null;
  }

  /** Returns true if field pagination is set (has been assigned a value) and false otherwise */
  public boolean isSetPagination() {
    return this.pagination != null;
  }

  public void setPaginationIsSet(boolean value) {
    if (!value) {
      this.pagination = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case QUESTION_INFO_LIST:
      if (value == null) {
        unsetQuestionInfoList();
      } else {
        setQuestionInfoList((java.util.List<TPortalWrongQuestionInfo>)value);
      }
      break;

    case RESPONSE:
      if (value == null) {
        unsetResponse();
      } else {
        setResponse((TRResponse)value);
      }
      break;

    case PAGINATION:
      if (value == null) {
        unsetPagination();
      } else {
        setPagination((TPagination)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case QUESTION_INFO_LIST:
      return getQuestionInfoList();

    case RESPONSE:
      return getResponse();

    case PAGINATION:
      return getPagination();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case QUESTION_INFO_LIST:
      return isSetQuestionInfoList();
    case RESPONSE:
      return isSetResponse();
    case PAGINATION:
      return isSetPagination();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TRPortalWrongQuestionInfoList)
      return this.equals((TRPortalWrongQuestionInfoList)that);
    return false;
  }

  public boolean equals(TRPortalWrongQuestionInfoList that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_questionInfoList = true && this.isSetQuestionInfoList();
    boolean that_present_questionInfoList = true && that.isSetQuestionInfoList();
    if (this_present_questionInfoList || that_present_questionInfoList) {
      if (!(this_present_questionInfoList && that_present_questionInfoList))
        return false;
      if (!this.questionInfoList.equals(that.questionInfoList))
        return false;
    }

    boolean this_present_response = true && this.isSetResponse();
    boolean that_present_response = true && that.isSetResponse();
    if (this_present_response || that_present_response) {
      if (!(this_present_response && that_present_response))
        return false;
      if (!this.response.equals(that.response))
        return false;
    }

    boolean this_present_pagination = true && this.isSetPagination();
    boolean that_present_pagination = true && that.isSetPagination();
    if (this_present_pagination || that_present_pagination) {
      if (!(this_present_pagination && that_present_pagination))
        return false;
      if (!this.pagination.equals(that.pagination))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetQuestionInfoList()) ? 131071 : 524287);
    if (isSetQuestionInfoList())
      hashCode = hashCode * 8191 + questionInfoList.hashCode();

    hashCode = hashCode * 8191 + ((isSetResponse()) ? 131071 : 524287);
    if (isSetResponse())
      hashCode = hashCode * 8191 + response.hashCode();

    hashCode = hashCode * 8191 + ((isSetPagination()) ? 131071 : 524287);
    if (isSetPagination())
      hashCode = hashCode * 8191 + pagination.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(TRPortalWrongQuestionInfoList other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetQuestionInfoList()).compareTo(other.isSetQuestionInfoList());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetQuestionInfoList()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.questionInfoList, other.questionInfoList);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetResponse()).compareTo(other.isSetResponse());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetResponse()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.response, other.response);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetPagination()).compareTo(other.isSetPagination());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPagination()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.pagination, other.pagination);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("TRPortalWrongQuestionInfoList(");
    boolean first = true;

    sb.append("questionInfoList:");
    if (this.questionInfoList == null) {
      sb.append("null");
    } else {
      sb.append(this.questionInfoList);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("response:");
    if (this.response == null) {
      sb.append("null");
    } else {
      sb.append(this.response);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("pagination:");
    if (this.pagination == null) {
      sb.append("null");
    } else {
      sb.append(this.pagination);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
    if (response != null) {
      response.validate();
    }
    if (pagination != null) {
      pagination.validate();
    }
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TRPortalWrongQuestionInfoListStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TRPortalWrongQuestionInfoListStandardScheme getScheme() {
      return new TRPortalWrongQuestionInfoListStandardScheme();
    }
  }

  private static class TRPortalWrongQuestionInfoListStandardScheme extends org.apache.thrift.scheme.StandardScheme<TRPortalWrongQuestionInfoList> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TRPortalWrongQuestionInfoList struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // QUESTION_INFO_LIST
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list16 = iprot.readListBegin();
                struct.questionInfoList = new java.util.ArrayList<TPortalWrongQuestionInfo>(_list16.size);
                TPortalWrongQuestionInfo _elem17;
                for (int _i18 = 0; _i18 < _list16.size; ++_i18)
                {
                  _elem17 = new TPortalWrongQuestionInfo();
                  _elem17.read(iprot);
                  struct.questionInfoList.add(_elem17);
                }
                iprot.readListEnd();
              }
              struct.setQuestionInfoListIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // RESPONSE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.response = new TRResponse();
              struct.response.read(iprot);
              struct.setResponseIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // PAGINATION
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.pagination = new TPagination();
              struct.pagination.read(iprot);
              struct.setPaginationIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, TRPortalWrongQuestionInfoList struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.questionInfoList != null) {
        oprot.writeFieldBegin(QUESTION_INFO_LIST_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.questionInfoList.size()));
          for (TPortalWrongQuestionInfo _iter19 : struct.questionInfoList)
          {
            _iter19.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.response != null) {
        oprot.writeFieldBegin(RESPONSE_FIELD_DESC);
        struct.response.write(oprot);
        oprot.writeFieldEnd();
      }
      if (struct.pagination != null) {
        oprot.writeFieldBegin(PAGINATION_FIELD_DESC);
        struct.pagination.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TRPortalWrongQuestionInfoListTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TRPortalWrongQuestionInfoListTupleScheme getScheme() {
      return new TRPortalWrongQuestionInfoListTupleScheme();
    }
  }

  private static class TRPortalWrongQuestionInfoListTupleScheme extends org.apache.thrift.scheme.TupleScheme<TRPortalWrongQuestionInfoList> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TRPortalWrongQuestionInfoList struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetQuestionInfoList()) {
        optionals.set(0);
      }
      if (struct.isSetResponse()) {
        optionals.set(1);
      }
      if (struct.isSetPagination()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetQuestionInfoList()) {
        {
          oprot.writeI32(struct.questionInfoList.size());
          for (TPortalWrongQuestionInfo _iter20 : struct.questionInfoList)
          {
            _iter20.write(oprot);
          }
        }
      }
      if (struct.isSetResponse()) {
        struct.response.write(oprot);
      }
      if (struct.isSetPagination()) {
        struct.pagination.write(oprot);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TRPortalWrongQuestionInfoList struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        {
          org.apache.thrift.protocol.TList _list21 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.questionInfoList = new java.util.ArrayList<TPortalWrongQuestionInfo>(_list21.size);
          TPortalWrongQuestionInfo _elem22;
          for (int _i23 = 0; _i23 < _list21.size; ++_i23)
          {
            _elem22 = new TPortalWrongQuestionInfo();
            _elem22.read(iprot);
            struct.questionInfoList.add(_elem22);
          }
        }
        struct.setQuestionInfoListIsSet(true);
      }
      if (incoming.get(1)) {
        struct.response = new TRResponse();
        struct.response.read(iprot);
        struct.setResponseIsSet(true);
      }
      if (incoming.get(2)) {
        struct.pagination = new TPagination();
        struct.pagination.read(iprot);
        struct.setPaginationIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

