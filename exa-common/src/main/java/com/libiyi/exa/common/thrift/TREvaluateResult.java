/**
 * Autogenerated by Thrift Compiler (0.11.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.libiyi.exa.common.thrift;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.11.0)", date = "2019-01-17")
public class TREvaluateResult implements org.apache.thrift.TBase<TREvaluateResult, TREvaluateResult._Fields>, java.io.Serializable, Cloneable, Comparable<TREvaluateResult> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TREvaluateResult");

  private static final org.apache.thrift.protocol.TField T_PORTAL_PAPER_AND_QUESTION_FIELD_DESC = new org.apache.thrift.protocol.TField("tPortalPaperAndQuestion", org.apache.thrift.protocol.TType.STRUCT, (short)1);
  private static final org.apache.thrift.protocol.TField RESPONSE_FIELD_DESC = new org.apache.thrift.protocol.TField("response", org.apache.thrift.protocol.TType.STRUCT, (short)2);
  private static final org.apache.thrift.protocol.TField GRADES_FIELD_DESC = new org.apache.thrift.protocol.TField("grades", org.apache.thrift.protocol.TType.I32, (short)3);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new TREvaluateResultStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new TREvaluateResultTupleSchemeFactory();

  public TPortalPaperAndQuestion tPortalPaperAndQuestion; // required
  public TRResponse response; // required
  public int grades; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    T_PORTAL_PAPER_AND_QUESTION((short)1, "tPortalPaperAndQuestion"),
    RESPONSE((short)2, "response"),
    GRADES((short)3, "grades");

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
        case 1: // T_PORTAL_PAPER_AND_QUESTION
          return T_PORTAL_PAPER_AND_QUESTION;
        case 2: // RESPONSE
          return RESPONSE;
        case 3: // GRADES
          return GRADES;
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
  private static final int __GRADES_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.T_PORTAL_PAPER_AND_QUESTION, new org.apache.thrift.meta_data.FieldMetaData("tPortalPaperAndQuestion", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, TPortalPaperAndQuestion.class)));
    tmpMap.put(_Fields.RESPONSE, new org.apache.thrift.meta_data.FieldMetaData("response", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, TRResponse.class)));
    tmpMap.put(_Fields.GRADES, new org.apache.thrift.meta_data.FieldMetaData("grades", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TREvaluateResult.class, metaDataMap);
  }

  public TREvaluateResult() {
  }

  public TREvaluateResult(
    TPortalPaperAndQuestion tPortalPaperAndQuestion,
    TRResponse response,
    int grades)
  {
    this();
    this.tPortalPaperAndQuestion = tPortalPaperAndQuestion;
    this.response = response;
    this.grades = grades;
    setGradesIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TREvaluateResult(TREvaluateResult other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetTPortalPaperAndQuestion()) {
      this.tPortalPaperAndQuestion = new TPortalPaperAndQuestion(other.tPortalPaperAndQuestion);
    }
    if (other.isSetResponse()) {
      this.response = new TRResponse(other.response);
    }
    this.grades = other.grades;
  }

  public TREvaluateResult deepCopy() {
    return new TREvaluateResult(this);
  }

  @Override
  public void clear() {
    this.tPortalPaperAndQuestion = null;
    this.response = null;
    setGradesIsSet(false);
    this.grades = 0;
  }

  public TPortalPaperAndQuestion getTPortalPaperAndQuestion() {
    return this.tPortalPaperAndQuestion;
  }

  public TREvaluateResult setTPortalPaperAndQuestion(TPortalPaperAndQuestion tPortalPaperAndQuestion) {
    this.tPortalPaperAndQuestion = tPortalPaperAndQuestion;
    return this;
  }

  public void unsetTPortalPaperAndQuestion() {
    this.tPortalPaperAndQuestion = null;
  }

  /** Returns true if field tPortalPaperAndQuestion is set (has been assigned a value) and false otherwise */
  public boolean isSetTPortalPaperAndQuestion() {
    return this.tPortalPaperAndQuestion != null;
  }

  public void setTPortalPaperAndQuestionIsSet(boolean value) {
    if (!value) {
      this.tPortalPaperAndQuestion = null;
    }
  }

  public TRResponse getResponse() {
    return this.response;
  }

  public TREvaluateResult setResponse(TRResponse response) {
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

  public int getGrades() {
    return this.grades;
  }

  public TREvaluateResult setGrades(int grades) {
    this.grades = grades;
    setGradesIsSet(true);
    return this;
  }

  public void unsetGrades() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __GRADES_ISSET_ID);
  }

  /** Returns true if field grades is set (has been assigned a value) and false otherwise */
  public boolean isSetGrades() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __GRADES_ISSET_ID);
  }

  public void setGradesIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __GRADES_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case T_PORTAL_PAPER_AND_QUESTION:
      if (value == null) {
        unsetTPortalPaperAndQuestion();
      } else {
        setTPortalPaperAndQuestion((TPortalPaperAndQuestion)value);
      }
      break;

    case RESPONSE:
      if (value == null) {
        unsetResponse();
      } else {
        setResponse((TRResponse)value);
      }
      break;

    case GRADES:
      if (value == null) {
        unsetGrades();
      } else {
        setGrades((Integer)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case T_PORTAL_PAPER_AND_QUESTION:
      return getTPortalPaperAndQuestion();

    case RESPONSE:
      return getResponse();

    case GRADES:
      return getGrades();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case T_PORTAL_PAPER_AND_QUESTION:
      return isSetTPortalPaperAndQuestion();
    case RESPONSE:
      return isSetResponse();
    case GRADES:
      return isSetGrades();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TREvaluateResult)
      return this.equals((TREvaluateResult)that);
    return false;
  }

  public boolean equals(TREvaluateResult that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_tPortalPaperAndQuestion = true && this.isSetTPortalPaperAndQuestion();
    boolean that_present_tPortalPaperAndQuestion = true && that.isSetTPortalPaperAndQuestion();
    if (this_present_tPortalPaperAndQuestion || that_present_tPortalPaperAndQuestion) {
      if (!(this_present_tPortalPaperAndQuestion && that_present_tPortalPaperAndQuestion))
        return false;
      if (!this.tPortalPaperAndQuestion.equals(that.tPortalPaperAndQuestion))
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

    boolean this_present_grades = true;
    boolean that_present_grades = true;
    if (this_present_grades || that_present_grades) {
      if (!(this_present_grades && that_present_grades))
        return false;
      if (this.grades != that.grades)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetTPortalPaperAndQuestion()) ? 131071 : 524287);
    if (isSetTPortalPaperAndQuestion())
      hashCode = hashCode * 8191 + tPortalPaperAndQuestion.hashCode();

    hashCode = hashCode * 8191 + ((isSetResponse()) ? 131071 : 524287);
    if (isSetResponse())
      hashCode = hashCode * 8191 + response.hashCode();

    hashCode = hashCode * 8191 + grades;

    return hashCode;
  }

  @Override
  public int compareTo(TREvaluateResult other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetTPortalPaperAndQuestion()).compareTo(other.isSetTPortalPaperAndQuestion());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTPortalPaperAndQuestion()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.tPortalPaperAndQuestion, other.tPortalPaperAndQuestion);
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
    lastComparison = Boolean.valueOf(isSetGrades()).compareTo(other.isSetGrades());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetGrades()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.grades, other.grades);
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
    StringBuilder sb = new StringBuilder("TREvaluateResult(");
    boolean first = true;

    sb.append("tPortalPaperAndQuestion:");
    if (this.tPortalPaperAndQuestion == null) {
      sb.append("null");
    } else {
      sb.append(this.tPortalPaperAndQuestion);
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
    sb.append("grades:");
    sb.append(this.grades);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
    if (tPortalPaperAndQuestion != null) {
      tPortalPaperAndQuestion.validate();
    }
    if (response != null) {
      response.validate();
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
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TREvaluateResultStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TREvaluateResultStandardScheme getScheme() {
      return new TREvaluateResultStandardScheme();
    }
  }

  private static class TREvaluateResultStandardScheme extends org.apache.thrift.scheme.StandardScheme<TREvaluateResult> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TREvaluateResult struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // T_PORTAL_PAPER_AND_QUESTION
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.tPortalPaperAndQuestion = new TPortalPaperAndQuestion();
              struct.tPortalPaperAndQuestion.read(iprot);
              struct.setTPortalPaperAndQuestionIsSet(true);
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
          case 3: // GRADES
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.grades = iprot.readI32();
              struct.setGradesIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, TREvaluateResult struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.tPortalPaperAndQuestion != null) {
        oprot.writeFieldBegin(T_PORTAL_PAPER_AND_QUESTION_FIELD_DESC);
        struct.tPortalPaperAndQuestion.write(oprot);
        oprot.writeFieldEnd();
      }
      if (struct.response != null) {
        oprot.writeFieldBegin(RESPONSE_FIELD_DESC);
        struct.response.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(GRADES_FIELD_DESC);
      oprot.writeI32(struct.grades);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TREvaluateResultTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TREvaluateResultTupleScheme getScheme() {
      return new TREvaluateResultTupleScheme();
    }
  }

  private static class TREvaluateResultTupleScheme extends org.apache.thrift.scheme.TupleScheme<TREvaluateResult> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TREvaluateResult struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetTPortalPaperAndQuestion()) {
        optionals.set(0);
      }
      if (struct.isSetResponse()) {
        optionals.set(1);
      }
      if (struct.isSetGrades()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetTPortalPaperAndQuestion()) {
        struct.tPortalPaperAndQuestion.write(oprot);
      }
      if (struct.isSetResponse()) {
        struct.response.write(oprot);
      }
      if (struct.isSetGrades()) {
        oprot.writeI32(struct.grades);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TREvaluateResult struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.tPortalPaperAndQuestion = new TPortalPaperAndQuestion();
        struct.tPortalPaperAndQuestion.read(iprot);
        struct.setTPortalPaperAndQuestionIsSet(true);
      }
      if (incoming.get(1)) {
        struct.response = new TRResponse();
        struct.response.read(iprot);
        struct.setResponseIsSet(true);
      }
      if (incoming.get(2)) {
        struct.grades = iprot.readI32();
        struct.setGradesIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

