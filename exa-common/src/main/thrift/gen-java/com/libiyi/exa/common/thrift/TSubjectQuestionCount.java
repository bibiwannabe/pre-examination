/**
 * Autogenerated by Thrift Compiler (0.11.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.libiyi.exa.common.thrift;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.11.0)", date = "2019-03-05")
public class TSubjectQuestionCount implements org.apache.thrift.TBase<TSubjectQuestionCount, TSubjectQuestionCount._Fields>, java.io.Serializable, Cloneable, Comparable<TSubjectQuestionCount> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TSubjectQuestionCount");

  private static final org.apache.thrift.protocol.TField SUBJECT_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("subjectId", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField SUBJECT_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("subjectName", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField QUESTION_NUM_FIELD_DESC = new org.apache.thrift.protocol.TField("questionNum", org.apache.thrift.protocol.TType.I32, (short)3);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new TSubjectQuestionCountStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new TSubjectQuestionCountTupleSchemeFactory();

  public int subjectId; // required
  public java.lang.String subjectName; // required
  public int questionNum; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    SUBJECT_ID((short)1, "subjectId"),
    SUBJECT_NAME((short)2, "subjectName"),
    QUESTION_NUM((short)3, "questionNum");

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
        case 1: // SUBJECT_ID
          return SUBJECT_ID;
        case 2: // SUBJECT_NAME
          return SUBJECT_NAME;
        case 3: // QUESTION_NUM
          return QUESTION_NUM;
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
  private static final int __SUBJECTID_ISSET_ID = 0;
  private static final int __QUESTIONNUM_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.SUBJECT_ID, new org.apache.thrift.meta_data.FieldMetaData("subjectId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.SUBJECT_NAME, new org.apache.thrift.meta_data.FieldMetaData("subjectName", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.QUESTION_NUM, new org.apache.thrift.meta_data.FieldMetaData("questionNum", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TSubjectQuestionCount.class, metaDataMap);
  }

  public TSubjectQuestionCount() {
  }

  public TSubjectQuestionCount(
    int subjectId,
    java.lang.String subjectName,
    int questionNum)
  {
    this();
    this.subjectId = subjectId;
    setSubjectIdIsSet(true);
    this.subjectName = subjectName;
    this.questionNum = questionNum;
    setQuestionNumIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TSubjectQuestionCount(TSubjectQuestionCount other) {
    __isset_bitfield = other.__isset_bitfield;
    this.subjectId = other.subjectId;
    if (other.isSetSubjectName()) {
      this.subjectName = other.subjectName;
    }
    this.questionNum = other.questionNum;
  }

  public TSubjectQuestionCount deepCopy() {
    return new TSubjectQuestionCount(this);
  }

  @Override
  public void clear() {
    setSubjectIdIsSet(false);
    this.subjectId = 0;
    this.subjectName = null;
    setQuestionNumIsSet(false);
    this.questionNum = 0;
  }

  public int getSubjectId() {
    return this.subjectId;
  }

  public TSubjectQuestionCount setSubjectId(int subjectId) {
    this.subjectId = subjectId;
    setSubjectIdIsSet(true);
    return this;
  }

  public void unsetSubjectId() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __SUBJECTID_ISSET_ID);
  }

  /** Returns true if field subjectId is set (has been assigned a value) and false otherwise */
  public boolean isSetSubjectId() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __SUBJECTID_ISSET_ID);
  }

  public void setSubjectIdIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __SUBJECTID_ISSET_ID, value);
  }

  public java.lang.String getSubjectName() {
    return this.subjectName;
  }

  public TSubjectQuestionCount setSubjectName(java.lang.String subjectName) {
    this.subjectName = subjectName;
    return this;
  }

  public void unsetSubjectName() {
    this.subjectName = null;
  }

  /** Returns true if field subjectName is set (has been assigned a value) and false otherwise */
  public boolean isSetSubjectName() {
    return this.subjectName != null;
  }

  public void setSubjectNameIsSet(boolean value) {
    if (!value) {
      this.subjectName = null;
    }
  }

  public int getQuestionNum() {
    return this.questionNum;
  }

  public TSubjectQuestionCount setQuestionNum(int questionNum) {
    this.questionNum = questionNum;
    setQuestionNumIsSet(true);
    return this;
  }

  public void unsetQuestionNum() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __QUESTIONNUM_ISSET_ID);
  }

  /** Returns true if field questionNum is set (has been assigned a value) and false otherwise */
  public boolean isSetQuestionNum() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __QUESTIONNUM_ISSET_ID);
  }

  public void setQuestionNumIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __QUESTIONNUM_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case SUBJECT_ID:
      if (value == null) {
        unsetSubjectId();
      } else {
        setSubjectId((java.lang.Integer)value);
      }
      break;

    case SUBJECT_NAME:
      if (value == null) {
        unsetSubjectName();
      } else {
        setSubjectName((java.lang.String)value);
      }
      break;

    case QUESTION_NUM:
      if (value == null) {
        unsetQuestionNum();
      } else {
        setQuestionNum((java.lang.Integer)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case SUBJECT_ID:
      return getSubjectId();

    case SUBJECT_NAME:
      return getSubjectName();

    case QUESTION_NUM:
      return getQuestionNum();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case SUBJECT_ID:
      return isSetSubjectId();
    case SUBJECT_NAME:
      return isSetSubjectName();
    case QUESTION_NUM:
      return isSetQuestionNum();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof TSubjectQuestionCount)
      return this.equals((TSubjectQuestionCount)that);
    return false;
  }

  public boolean equals(TSubjectQuestionCount that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_subjectId = true;
    boolean that_present_subjectId = true;
    if (this_present_subjectId || that_present_subjectId) {
      if (!(this_present_subjectId && that_present_subjectId))
        return false;
      if (this.subjectId != that.subjectId)
        return false;
    }

    boolean this_present_subjectName = true && this.isSetSubjectName();
    boolean that_present_subjectName = true && that.isSetSubjectName();
    if (this_present_subjectName || that_present_subjectName) {
      if (!(this_present_subjectName && that_present_subjectName))
        return false;
      if (!this.subjectName.equals(that.subjectName))
        return false;
    }

    boolean this_present_questionNum = true;
    boolean that_present_questionNum = true;
    if (this_present_questionNum || that_present_questionNum) {
      if (!(this_present_questionNum && that_present_questionNum))
        return false;
      if (this.questionNum != that.questionNum)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + subjectId;

    hashCode = hashCode * 8191 + ((isSetSubjectName()) ? 131071 : 524287);
    if (isSetSubjectName())
      hashCode = hashCode * 8191 + subjectName.hashCode();

    hashCode = hashCode * 8191 + questionNum;

    return hashCode;
  }

  @Override
  public int compareTo(TSubjectQuestionCount other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetSubjectId()).compareTo(other.isSetSubjectId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSubjectId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.subjectId, other.subjectId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetSubjectName()).compareTo(other.isSetSubjectName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSubjectName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.subjectName, other.subjectName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetQuestionNum()).compareTo(other.isSetQuestionNum());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetQuestionNum()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.questionNum, other.questionNum);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("TSubjectQuestionCount(");
    boolean first = true;

    sb.append("subjectId:");
    sb.append(this.subjectId);
    first = false;
    if (!first) sb.append(", ");
    sb.append("subjectName:");
    if (this.subjectName == null) {
      sb.append("null");
    } else {
      sb.append(this.subjectName);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("questionNum:");
    sb.append(this.questionNum);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
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
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TSubjectQuestionCountStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TSubjectQuestionCountStandardScheme getScheme() {
      return new TSubjectQuestionCountStandardScheme();
    }
  }

  private static class TSubjectQuestionCountStandardScheme extends org.apache.thrift.scheme.StandardScheme<TSubjectQuestionCount> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TSubjectQuestionCount struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // SUBJECT_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.subjectId = iprot.readI32();
              struct.setSubjectIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // SUBJECT_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.subjectName = iprot.readString();
              struct.setSubjectNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // QUESTION_NUM
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.questionNum = iprot.readI32();
              struct.setQuestionNumIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, TSubjectQuestionCount struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(SUBJECT_ID_FIELD_DESC);
      oprot.writeI32(struct.subjectId);
      oprot.writeFieldEnd();
      if (struct.subjectName != null) {
        oprot.writeFieldBegin(SUBJECT_NAME_FIELD_DESC);
        oprot.writeString(struct.subjectName);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(QUESTION_NUM_FIELD_DESC);
      oprot.writeI32(struct.questionNum);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TSubjectQuestionCountTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TSubjectQuestionCountTupleScheme getScheme() {
      return new TSubjectQuestionCountTupleScheme();
    }
  }

  private static class TSubjectQuestionCountTupleScheme extends org.apache.thrift.scheme.TupleScheme<TSubjectQuestionCount> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TSubjectQuestionCount struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetSubjectId()) {
        optionals.set(0);
      }
      if (struct.isSetSubjectName()) {
        optionals.set(1);
      }
      if (struct.isSetQuestionNum()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetSubjectId()) {
        oprot.writeI32(struct.subjectId);
      }
      if (struct.isSetSubjectName()) {
        oprot.writeString(struct.subjectName);
      }
      if (struct.isSetQuestionNum()) {
        oprot.writeI32(struct.questionNum);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TSubjectQuestionCount struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.subjectId = iprot.readI32();
        struct.setSubjectIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.subjectName = iprot.readString();
        struct.setSubjectNameIsSet(true);
      }
      if (incoming.get(2)) {
        struct.questionNum = iprot.readI32();
        struct.setQuestionNumIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

