/**
 * Autogenerated by Thrift Compiler (0.11.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.libiyi.exa.common.thrift;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.11.0)", date = "2019-03-03")
public class TRPraticeSubject implements org.apache.thrift.TBase<TRPraticeSubject, TRPraticeSubject._Fields>, java.io.Serializable, Cloneable, Comparable<TRPraticeSubject> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TRPraticeSubject");

  private static final org.apache.thrift.protocol.TField RESPONSE_FIELD_DESC = new org.apache.thrift.protocol.TField("response", org.apache.thrift.protocol.TType.STRUCT, (short)1);
  private static final org.apache.thrift.protocol.TField SUBJECT_QUESTION_COUNT_FIELD_DESC = new org.apache.thrift.protocol.TField("subjectQuestionCount", org.apache.thrift.protocol.TType.LIST, (short)2);
  private static final org.apache.thrift.protocol.TField PAGINATION_FIELD_DESC = new org.apache.thrift.protocol.TField("pagination", org.apache.thrift.protocol.TType.STRUCT, (short)3);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new TRPraticeSubjectStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new TRPraticeSubjectTupleSchemeFactory();

  public TRResponse response; // required
  public java.util.List<TSubjectQuestionCount> subjectQuestionCount; // required
  public TPagination pagination; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    RESPONSE((short)1, "response"),
    SUBJECT_QUESTION_COUNT((short)2, "subjectQuestionCount"),
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
        case 1: // RESPONSE
          return RESPONSE;
        case 2: // SUBJECT_QUESTION_COUNT
          return SUBJECT_QUESTION_COUNT;
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
    tmpMap.put(_Fields.RESPONSE, new org.apache.thrift.meta_data.FieldMetaData("response", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, TRResponse.class)));
    tmpMap.put(_Fields.SUBJECT_QUESTION_COUNT, new org.apache.thrift.meta_data.FieldMetaData("subjectQuestionCount", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRUCT            , "TSubjectQuestionCount"))));
    tmpMap.put(_Fields.PAGINATION, new org.apache.thrift.meta_data.FieldMetaData("pagination", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, TPagination.class)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TRPraticeSubject.class, metaDataMap);
  }

  public TRPraticeSubject() {
  }

  public TRPraticeSubject(
    TRResponse response,
    java.util.List<TSubjectQuestionCount> subjectQuestionCount,
    TPagination pagination)
  {
    this();
    this.response = response;
    this.subjectQuestionCount = subjectQuestionCount;
    this.pagination = pagination;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TRPraticeSubject(TRPraticeSubject other) {
    if (other.isSetResponse()) {
      this.response = new TRResponse(other.response);
    }
    if (other.isSetSubjectQuestionCount()) {
      java.util.List<TSubjectQuestionCount> __this__subjectQuestionCount = new java.util.ArrayList<TSubjectQuestionCount>(other.subjectQuestionCount.size());
      for (TSubjectQuestionCount other_element : other.subjectQuestionCount) {
        __this__subjectQuestionCount.add(new TSubjectQuestionCount(other_element));
      }
      this.subjectQuestionCount = __this__subjectQuestionCount;
    }
    if (other.isSetPagination()) {
      this.pagination = new TPagination(other.pagination);
    }
  }

  public TRPraticeSubject deepCopy() {
    return new TRPraticeSubject(this);
  }

  @Override
  public void clear() {
    this.response = null;
    this.subjectQuestionCount = null;
    this.pagination = null;
  }

  public TRResponse getResponse() {
    return this.response;
  }

  public TRPraticeSubject setResponse(TRResponse response) {
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

  public int getSubjectQuestionCountSize() {
    return (this.subjectQuestionCount == null) ? 0 : this.subjectQuestionCount.size();
  }

  public java.util.Iterator<TSubjectQuestionCount> getSubjectQuestionCountIterator() {
    return (this.subjectQuestionCount == null) ? null : this.subjectQuestionCount.iterator();
  }

  public void addToSubjectQuestionCount(TSubjectQuestionCount elem) {
    if (this.subjectQuestionCount == null) {
      this.subjectQuestionCount = new java.util.ArrayList<TSubjectQuestionCount>();
    }
    this.subjectQuestionCount.add(elem);
  }

  public java.util.List<TSubjectQuestionCount> getSubjectQuestionCount() {
    return this.subjectQuestionCount;
  }

  public TRPraticeSubject setSubjectQuestionCount(java.util.List<TSubjectQuestionCount> subjectQuestionCount) {
    this.subjectQuestionCount = subjectQuestionCount;
    return this;
  }

  public void unsetSubjectQuestionCount() {
    this.subjectQuestionCount = null;
  }

  /** Returns true if field subjectQuestionCount is set (has been assigned a value) and false otherwise */
  public boolean isSetSubjectQuestionCount() {
    return this.subjectQuestionCount != null;
  }

  public void setSubjectQuestionCountIsSet(boolean value) {
    if (!value) {
      this.subjectQuestionCount = null;
    }
  }

  public TPagination getPagination() {
    return this.pagination;
  }

  public TRPraticeSubject setPagination(TPagination pagination) {
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
    case RESPONSE:
      if (value == null) {
        unsetResponse();
      } else {
        setResponse((TRResponse)value);
      }
      break;

    case SUBJECT_QUESTION_COUNT:
      if (value == null) {
        unsetSubjectQuestionCount();
      } else {
        setSubjectQuestionCount((java.util.List<TSubjectQuestionCount>)value);
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
    case RESPONSE:
      return getResponse();

    case SUBJECT_QUESTION_COUNT:
      return getSubjectQuestionCount();

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
    case RESPONSE:
      return isSetResponse();
    case SUBJECT_QUESTION_COUNT:
      return isSetSubjectQuestionCount();
    case PAGINATION:
      return isSetPagination();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TRPraticeSubject)
      return this.equals((TRPraticeSubject)that);
    return false;
  }

  public boolean equals(TRPraticeSubject that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_response = true && this.isSetResponse();
    boolean that_present_response = true && that.isSetResponse();
    if (this_present_response || that_present_response) {
      if (!(this_present_response && that_present_response))
        return false;
      if (!this.response.equals(that.response))
        return false;
    }

    boolean this_present_subjectQuestionCount = true && this.isSetSubjectQuestionCount();
    boolean that_present_subjectQuestionCount = true && that.isSetSubjectQuestionCount();
    if (this_present_subjectQuestionCount || that_present_subjectQuestionCount) {
      if (!(this_present_subjectQuestionCount && that_present_subjectQuestionCount))
        return false;
      if (!this.subjectQuestionCount.equals(that.subjectQuestionCount))
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

    hashCode = hashCode * 8191 + ((isSetResponse()) ? 131071 : 524287);
    if (isSetResponse())
      hashCode = hashCode * 8191 + response.hashCode();

    hashCode = hashCode * 8191 + ((isSetSubjectQuestionCount()) ? 131071 : 524287);
    if (isSetSubjectQuestionCount())
      hashCode = hashCode * 8191 + subjectQuestionCount.hashCode();

    hashCode = hashCode * 8191 + ((isSetPagination()) ? 131071 : 524287);
    if (isSetPagination())
      hashCode = hashCode * 8191 + pagination.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(TRPraticeSubject other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

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
    lastComparison = Boolean.valueOf(isSetSubjectQuestionCount()).compareTo(other.isSetSubjectQuestionCount());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSubjectQuestionCount()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.subjectQuestionCount, other.subjectQuestionCount);
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
    StringBuilder sb = new StringBuilder("TRPraticeSubject(");
    boolean first = true;

    sb.append("response:");
    if (this.response == null) {
      sb.append("null");
    } else {
      sb.append(this.response);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("subjectQuestionCount:");
    if (this.subjectQuestionCount == null) {
      sb.append("null");
    } else {
      sb.append(this.subjectQuestionCount);
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

  private static class TRPraticeSubjectStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TRPraticeSubjectStandardScheme getScheme() {
      return new TRPraticeSubjectStandardScheme();
    }
  }

  private static class TRPraticeSubjectStandardScheme extends org.apache.thrift.scheme.StandardScheme<TRPraticeSubject> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TRPraticeSubject struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // RESPONSE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.response = new TRResponse();
              struct.response.read(iprot);
              struct.setResponseIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // SUBJECT_QUESTION_COUNT
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list102 = iprot.readListBegin();
                struct.subjectQuestionCount = new java.util.ArrayList<TSubjectQuestionCount>(_list102.size);
                TSubjectQuestionCount _elem103;
                for (int _i104 = 0; _i104 < _list102.size; ++_i104)
                {
                  _elem103 = new TSubjectQuestionCount();
                  _elem103.read(iprot);
                  struct.subjectQuestionCount.add(_elem103);
                }
                iprot.readListEnd();
              }
              struct.setSubjectQuestionCountIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, TRPraticeSubject struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.response != null) {
        oprot.writeFieldBegin(RESPONSE_FIELD_DESC);
        struct.response.write(oprot);
        oprot.writeFieldEnd();
      }
      if (struct.subjectQuestionCount != null) {
        oprot.writeFieldBegin(SUBJECT_QUESTION_COUNT_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.subjectQuestionCount.size()));
          for (TSubjectQuestionCount _iter105 : struct.subjectQuestionCount)
          {
            _iter105.write(oprot);
          }
          oprot.writeListEnd();
        }
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

  private static class TRPraticeSubjectTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TRPraticeSubjectTupleScheme getScheme() {
      return new TRPraticeSubjectTupleScheme();
    }
  }

  private static class TRPraticeSubjectTupleScheme extends org.apache.thrift.scheme.TupleScheme<TRPraticeSubject> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TRPraticeSubject struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetResponse()) {
        optionals.set(0);
      }
      if (struct.isSetSubjectQuestionCount()) {
        optionals.set(1);
      }
      if (struct.isSetPagination()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetResponse()) {
        struct.response.write(oprot);
      }
      if (struct.isSetSubjectQuestionCount()) {
        {
          oprot.writeI32(struct.subjectQuestionCount.size());
          for (TSubjectQuestionCount _iter106 : struct.subjectQuestionCount)
          {
            _iter106.write(oprot);
          }
        }
      }
      if (struct.isSetPagination()) {
        struct.pagination.write(oprot);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TRPraticeSubject struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.response = new TRResponse();
        struct.response.read(iprot);
        struct.setResponseIsSet(true);
      }
      if (incoming.get(1)) {
        {
          org.apache.thrift.protocol.TList _list107 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.subjectQuestionCount = new java.util.ArrayList<TSubjectQuestionCount>(_list107.size);
          TSubjectQuestionCount _elem108;
          for (int _i109 = 0; _i109 < _list107.size; ++_i109)
          {
            _elem108 = new TSubjectQuestionCount();
            _elem108.read(iprot);
            struct.subjectQuestionCount.add(_elem108);
          }
        }
        struct.setSubjectQuestionCountIsSet(true);
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

