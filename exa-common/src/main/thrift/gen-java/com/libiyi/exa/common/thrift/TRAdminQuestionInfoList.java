/**
 * Autogenerated by Thrift Compiler (0.11.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.libiyi.exa.common.thrift;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.11.0)", date = "2019-03-05")
public class TRAdminQuestionInfoList implements org.apache.thrift.TBase<TRAdminQuestionInfoList, TRAdminQuestionInfoList._Fields>, java.io.Serializable, Cloneable, Comparable<TRAdminQuestionInfoList> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TRAdminQuestionInfoList");

  private static final org.apache.thrift.protocol.TField ADMIN_QUESTION_INFO_LIST_FIELD_DESC = new org.apache.thrift.protocol.TField("adminQuestionInfoList", org.apache.thrift.protocol.TType.LIST, (short)1);
  private static final org.apache.thrift.protocol.TField RESPONSE_FIELD_DESC = new org.apache.thrift.protocol.TField("response", org.apache.thrift.protocol.TType.STRUCT, (short)2);
  private static final org.apache.thrift.protocol.TField PAGINATION_FIELD_DESC = new org.apache.thrift.protocol.TField("pagination", org.apache.thrift.protocol.TType.STRUCT, (short)3);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new TRAdminQuestionInfoListStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new TRAdminQuestionInfoListTupleSchemeFactory();

  public java.util.List<TAdminQuestionInfo> adminQuestionInfoList; // required
  public TRResponse response; // required
  public TPagination pagination; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ADMIN_QUESTION_INFO_LIST((short)1, "adminQuestionInfoList"),
    RESPONSE((short)2, "response"),
    PAGINATION((short)3, "pagination");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

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
        case 1: // ADMIN_QUESTION_INFO_LIST
          return ADMIN_QUESTION_INFO_LIST;
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
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ADMIN_QUESTION_INFO_LIST, new org.apache.thrift.meta_data.FieldMetaData("adminQuestionInfoList", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRUCT            , "TAdminQuestionInfo"))));
    tmpMap.put(_Fields.RESPONSE, new org.apache.thrift.meta_data.FieldMetaData("response", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, TRResponse.class)));
    tmpMap.put(_Fields.PAGINATION, new org.apache.thrift.meta_data.FieldMetaData("pagination", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, TPagination.class)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TRAdminQuestionInfoList.class, metaDataMap);
  }

  public TRAdminQuestionInfoList() {
  }

  public TRAdminQuestionInfoList(
    java.util.List<TAdminQuestionInfo> adminQuestionInfoList,
    TRResponse response,
    TPagination pagination)
  {
    this();
    this.adminQuestionInfoList = adminQuestionInfoList;
    this.response = response;
    this.pagination = pagination;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TRAdminQuestionInfoList(TRAdminQuestionInfoList other) {
    if (other.isSetAdminQuestionInfoList()) {
      java.util.List<TAdminQuestionInfo> __this__adminQuestionInfoList = new java.util.ArrayList<TAdminQuestionInfo>(other.adminQuestionInfoList.size());
      for (TAdminQuestionInfo other_element : other.adminQuestionInfoList) {
        __this__adminQuestionInfoList.add(new TAdminQuestionInfo(other_element));
      }
      this.adminQuestionInfoList = __this__adminQuestionInfoList;
    }
    if (other.isSetResponse()) {
      this.response = new TRResponse(other.response);
    }
    if (other.isSetPagination()) {
      this.pagination = new TPagination(other.pagination);
    }
  }

  public TRAdminQuestionInfoList deepCopy() {
    return new TRAdminQuestionInfoList(this);
  }

  @Override
  public void clear() {
    this.adminQuestionInfoList = null;
    this.response = null;
    this.pagination = null;
  }

  public int getAdminQuestionInfoListSize() {
    return (this.adminQuestionInfoList == null) ? 0 : this.adminQuestionInfoList.size();
  }

  public java.util.Iterator<TAdminQuestionInfo> getAdminQuestionInfoListIterator() {
    return (this.adminQuestionInfoList == null) ? null : this.adminQuestionInfoList.iterator();
  }

  public void addToAdminQuestionInfoList(TAdminQuestionInfo elem) {
    if (this.adminQuestionInfoList == null) {
      this.adminQuestionInfoList = new java.util.ArrayList<TAdminQuestionInfo>();
    }
    this.adminQuestionInfoList.add(elem);
  }

  public java.util.List<TAdminQuestionInfo> getAdminQuestionInfoList() {
    return this.adminQuestionInfoList;
  }

  public TRAdminQuestionInfoList setAdminQuestionInfoList(java.util.List<TAdminQuestionInfo> adminQuestionInfoList) {
    this.adminQuestionInfoList = adminQuestionInfoList;
    return this;
  }

  public void unsetAdminQuestionInfoList() {
    this.adminQuestionInfoList = null;
  }

  /** Returns true if field adminQuestionInfoList is set (has been assigned a value) and false otherwise */
  public boolean isSetAdminQuestionInfoList() {
    return this.adminQuestionInfoList != null;
  }

  public void setAdminQuestionInfoListIsSet(boolean value) {
    if (!value) {
      this.adminQuestionInfoList = null;
    }
  }

  public TRResponse getResponse() {
    return this.response;
  }

  public TRAdminQuestionInfoList setResponse(TRResponse response) {
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

  public TRAdminQuestionInfoList setPagination(TPagination pagination) {
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

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case ADMIN_QUESTION_INFO_LIST:
      if (value == null) {
        unsetAdminQuestionInfoList();
      } else {
        setAdminQuestionInfoList((java.util.List<TAdminQuestionInfo>)value);
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

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case ADMIN_QUESTION_INFO_LIST:
      return getAdminQuestionInfoList();

    case RESPONSE:
      return getResponse();

    case PAGINATION:
      return getPagination();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case ADMIN_QUESTION_INFO_LIST:
      return isSetAdminQuestionInfoList();
    case RESPONSE:
      return isSetResponse();
    case PAGINATION:
      return isSetPagination();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof TRAdminQuestionInfoList)
      return this.equals((TRAdminQuestionInfoList)that);
    return false;
  }

  public boolean equals(TRAdminQuestionInfoList that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_adminQuestionInfoList = true && this.isSetAdminQuestionInfoList();
    boolean that_present_adminQuestionInfoList = true && that.isSetAdminQuestionInfoList();
    if (this_present_adminQuestionInfoList || that_present_adminQuestionInfoList) {
      if (!(this_present_adminQuestionInfoList && that_present_adminQuestionInfoList))
        return false;
      if (!this.adminQuestionInfoList.equals(that.adminQuestionInfoList))
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

    hashCode = hashCode * 8191 + ((isSetAdminQuestionInfoList()) ? 131071 : 524287);
    if (isSetAdminQuestionInfoList())
      hashCode = hashCode * 8191 + adminQuestionInfoList.hashCode();

    hashCode = hashCode * 8191 + ((isSetResponse()) ? 131071 : 524287);
    if (isSetResponse())
      hashCode = hashCode * 8191 + response.hashCode();

    hashCode = hashCode * 8191 + ((isSetPagination()) ? 131071 : 524287);
    if (isSetPagination())
      hashCode = hashCode * 8191 + pagination.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(TRAdminQuestionInfoList other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetAdminQuestionInfoList()).compareTo(other.isSetAdminQuestionInfoList());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAdminQuestionInfoList()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.adminQuestionInfoList, other.adminQuestionInfoList);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetResponse()).compareTo(other.isSetResponse());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetResponse()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.response, other.response);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetPagination()).compareTo(other.isSetPagination());
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
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("TRAdminQuestionInfoList(");
    boolean first = true;

    sb.append("adminQuestionInfoList:");
    if (this.adminQuestionInfoList == null) {
      sb.append("null");
    } else {
      sb.append(this.adminQuestionInfoList);
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

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TRAdminQuestionInfoListStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TRAdminQuestionInfoListStandardScheme getScheme() {
      return new TRAdminQuestionInfoListStandardScheme();
    }
  }

  private static class TRAdminQuestionInfoListStandardScheme extends org.apache.thrift.scheme.StandardScheme<TRAdminQuestionInfoList> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TRAdminQuestionInfoList struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ADMIN_QUESTION_INFO_LIST
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list8 = iprot.readListBegin();
                struct.adminQuestionInfoList = new java.util.ArrayList<TAdminQuestionInfo>(_list8.size);
                TAdminQuestionInfo _elem9;
                for (int _i10 = 0; _i10 < _list8.size; ++_i10)
                {
                  _elem9 = new TAdminQuestionInfo();
                  _elem9.read(iprot);
                  struct.adminQuestionInfoList.add(_elem9);
                }
                iprot.readListEnd();
              }
              struct.setAdminQuestionInfoListIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, TRAdminQuestionInfoList struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.adminQuestionInfoList != null) {
        oprot.writeFieldBegin(ADMIN_QUESTION_INFO_LIST_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.adminQuestionInfoList.size()));
          for (TAdminQuestionInfo _iter11 : struct.adminQuestionInfoList)
          {
            _iter11.write(oprot);
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

  private static class TRAdminQuestionInfoListTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TRAdminQuestionInfoListTupleScheme getScheme() {
      return new TRAdminQuestionInfoListTupleScheme();
    }
  }

  private static class TRAdminQuestionInfoListTupleScheme extends org.apache.thrift.scheme.TupleScheme<TRAdminQuestionInfoList> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TRAdminQuestionInfoList struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetAdminQuestionInfoList()) {
        optionals.set(0);
      }
      if (struct.isSetResponse()) {
        optionals.set(1);
      }
      if (struct.isSetPagination()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetAdminQuestionInfoList()) {
        {
          oprot.writeI32(struct.adminQuestionInfoList.size());
          for (TAdminQuestionInfo _iter12 : struct.adminQuestionInfoList)
          {
            _iter12.write(oprot);
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
    public void read(org.apache.thrift.protocol.TProtocol prot, TRAdminQuestionInfoList struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        {
          org.apache.thrift.protocol.TList _list13 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.adminQuestionInfoList = new java.util.ArrayList<TAdminQuestionInfo>(_list13.size);
          TAdminQuestionInfo _elem14;
          for (int _i15 = 0; _i15 < _list13.size; ++_i15)
          {
            _elem14 = new TAdminQuestionInfo();
            _elem14.read(iprot);
            struct.adminQuestionInfoList.add(_elem14);
          }
        }
        struct.setAdminQuestionInfoListIsSet(true);
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

