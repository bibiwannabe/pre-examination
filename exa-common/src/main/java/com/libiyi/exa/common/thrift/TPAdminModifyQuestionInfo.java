/**
 * Autogenerated by Thrift Compiler (0.11.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.libiyi.exa.common.thrift;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.11.0)", date = "2019-01-17")
public class TPAdminModifyQuestionInfo implements org.apache.thrift.TBase<TPAdminModifyQuestionInfo, TPAdminModifyQuestionInfo._Fields>, java.io.Serializable, Cloneable, Comparable<TPAdminModifyQuestionInfo> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TPAdminModifyQuestionInfo");

  private static final org.apache.thrift.protocol.TField ID_FIELD_DESC = new org.apache.thrift.protocol.TField("id", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField SUBJECT_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("subjectId", org.apache.thrift.protocol.TType.I32, (short)2);
  private static final org.apache.thrift.protocol.TField CONTENT_FIELD_DESC = new org.apache.thrift.protocol.TField("content", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField OPTIONS_FIELD_DESC = new org.apache.thrift.protocol.TField("options", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField ANSWER_FIELD_DESC = new org.apache.thrift.protocol.TField("answer", org.apache.thrift.protocol.TType.STRING, (short)5);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new TPAdminModifyQuestionInfoStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new TPAdminModifyQuestionInfoTupleSchemeFactory();

  public int id; // required
  public int subjectId; // required
  public String content; // required
  public String options; // required
  public String answer; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ID((short)1, "id"),
    SUBJECT_ID((short)2, "subjectId"),
    CONTENT((short)3, "content"),
    OPTIONS((short)4, "options"),
    ANSWER((short)5, "answer");

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
        case 1: // ID
          return ID;
        case 2: // SUBJECT_ID
          return SUBJECT_ID;
        case 3: // CONTENT
          return CONTENT;
        case 4: // OPTIONS
          return OPTIONS;
        case 5: // ANSWER
          return ANSWER;
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
  private static final int __ID_ISSET_ID = 0;
  private static final int __SUBJECTID_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ID, new org.apache.thrift.meta_data.FieldMetaData("id", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.SUBJECT_ID, new org.apache.thrift.meta_data.FieldMetaData("subjectId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.CONTENT, new org.apache.thrift.meta_data.FieldMetaData("content", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.OPTIONS, new org.apache.thrift.meta_data.FieldMetaData("options", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.ANSWER, new org.apache.thrift.meta_data.FieldMetaData("answer", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TPAdminModifyQuestionInfo.class, metaDataMap);
  }

  public TPAdminModifyQuestionInfo() {
  }

  public TPAdminModifyQuestionInfo(
    int id,
    int subjectId,
    String content,
    String options,
    String answer)
  {
    this();
    this.id = id;
    setIdIsSet(true);
    this.subjectId = subjectId;
    setSubjectIdIsSet(true);
    this.content = content;
    this.options = options;
    this.answer = answer;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TPAdminModifyQuestionInfo(TPAdminModifyQuestionInfo other) {
    __isset_bitfield = other.__isset_bitfield;
    this.id = other.id;
    this.subjectId = other.subjectId;
    if (other.isSetContent()) {
      this.content = other.content;
    }
    if (other.isSetOptions()) {
      this.options = other.options;
    }
    if (other.isSetAnswer()) {
      this.answer = other.answer;
    }
  }

  public TPAdminModifyQuestionInfo deepCopy() {
    return new TPAdminModifyQuestionInfo(this);
  }

  @Override
  public void clear() {
    setIdIsSet(false);
    this.id = 0;
    setSubjectIdIsSet(false);
    this.subjectId = 0;
    this.content = null;
    this.options = null;
    this.answer = null;
  }

  public int getId() {
    return this.id;
  }

  public TPAdminModifyQuestionInfo setId(int id) {
    this.id = id;
    setIdIsSet(true);
    return this;
  }

  public void unsetId() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __ID_ISSET_ID);
  }

  /** Returns true if field id is set (has been assigned a value) and false otherwise */
  public boolean isSetId() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __ID_ISSET_ID);
  }

  public void setIdIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __ID_ISSET_ID, value);
  }

  public int getSubjectId() {
    return this.subjectId;
  }

  public TPAdminModifyQuestionInfo setSubjectId(int subjectId) {
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

  public String getContent() {
    return this.content;
  }

  public TPAdminModifyQuestionInfo setContent(String content) {
    this.content = content;
    return this;
  }

  public void unsetContent() {
    this.content = null;
  }

  /** Returns true if field content is set (has been assigned a value) and false otherwise */
  public boolean isSetContent() {
    return this.content != null;
  }

  public void setContentIsSet(boolean value) {
    if (!value) {
      this.content = null;
    }
  }

  public String getOptions() {
    return this.options;
  }

  public TPAdminModifyQuestionInfo setOptions(String options) {
    this.options = options;
    return this;
  }

  public void unsetOptions() {
    this.options = null;
  }

  /** Returns true if field options is set (has been assigned a value) and false otherwise */
  public boolean isSetOptions() {
    return this.options != null;
  }

  public void setOptionsIsSet(boolean value) {
    if (!value) {
      this.options = null;
    }
  }

  public String getAnswer() {
    return this.answer;
  }

  public TPAdminModifyQuestionInfo setAnswer(String answer) {
    this.answer = answer;
    return this;
  }

  public void unsetAnswer() {
    this.answer = null;
  }

  /** Returns true if field answer is set (has been assigned a value) and false otherwise */
  public boolean isSetAnswer() {
    return this.answer != null;
  }

  public void setAnswerIsSet(boolean value) {
    if (!value) {
      this.answer = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case ID:
      if (value == null) {
        unsetId();
      } else {
        setId((Integer)value);
      }
      break;

    case SUBJECT_ID:
      if (value == null) {
        unsetSubjectId();
      } else {
        setSubjectId((Integer)value);
      }
      break;

    case CONTENT:
      if (value == null) {
        unsetContent();
      } else {
        setContent((String)value);
      }
      break;

    case OPTIONS:
      if (value == null) {
        unsetOptions();
      } else {
        setOptions((String)value);
      }
      break;

    case ANSWER:
      if (value == null) {
        unsetAnswer();
      } else {
        setAnswer((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case ID:
      return getId();

    case SUBJECT_ID:
      return getSubjectId();

    case CONTENT:
      return getContent();

    case OPTIONS:
      return getOptions();

    case ANSWER:
      return getAnswer();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case ID:
      return isSetId();
    case SUBJECT_ID:
      return isSetSubjectId();
    case CONTENT:
      return isSetContent();
    case OPTIONS:
      return isSetOptions();
    case ANSWER:
      return isSetAnswer();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TPAdminModifyQuestionInfo)
      return this.equals((TPAdminModifyQuestionInfo)that);
    return false;
  }

  public boolean equals(TPAdminModifyQuestionInfo that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_id = true;
    boolean that_present_id = true;
    if (this_present_id || that_present_id) {
      if (!(this_present_id && that_present_id))
        return false;
      if (this.id != that.id)
        return false;
    }

    boolean this_present_subjectId = true;
    boolean that_present_subjectId = true;
    if (this_present_subjectId || that_present_subjectId) {
      if (!(this_present_subjectId && that_present_subjectId))
        return false;
      if (this.subjectId != that.subjectId)
        return false;
    }

    boolean this_present_content = true && this.isSetContent();
    boolean that_present_content = true && that.isSetContent();
    if (this_present_content || that_present_content) {
      if (!(this_present_content && that_present_content))
        return false;
      if (!this.content.equals(that.content))
        return false;
    }

    boolean this_present_options = true && this.isSetOptions();
    boolean that_present_options = true && that.isSetOptions();
    if (this_present_options || that_present_options) {
      if (!(this_present_options && that_present_options))
        return false;
      if (!this.options.equals(that.options))
        return false;
    }

    boolean this_present_answer = true && this.isSetAnswer();
    boolean that_present_answer = true && that.isSetAnswer();
    if (this_present_answer || that_present_answer) {
      if (!(this_present_answer && that_present_answer))
        return false;
      if (!this.answer.equals(that.answer))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + id;

    hashCode = hashCode * 8191 + subjectId;

    hashCode = hashCode * 8191 + ((isSetContent()) ? 131071 : 524287);
    if (isSetContent())
      hashCode = hashCode * 8191 + content.hashCode();

    hashCode = hashCode * 8191 + ((isSetOptions()) ? 131071 : 524287);
    if (isSetOptions())
      hashCode = hashCode * 8191 + options.hashCode();

    hashCode = hashCode * 8191 + ((isSetAnswer()) ? 131071 : 524287);
    if (isSetAnswer())
      hashCode = hashCode * 8191 + answer.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(TPAdminModifyQuestionInfo other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetId()).compareTo(other.isSetId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.id, other.id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetSubjectId()).compareTo(other.isSetSubjectId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSubjectId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.subjectId, other.subjectId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetContent()).compareTo(other.isSetContent());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetContent()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.content, other.content);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetOptions()).compareTo(other.isSetOptions());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetOptions()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.options, other.options);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetAnswer()).compareTo(other.isSetAnswer());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAnswer()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.answer, other.answer);
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
    StringBuilder sb = new StringBuilder("TPAdminModifyQuestionInfo(");
    boolean first = true;

    sb.append("id:");
    sb.append(this.id);
    first = false;
    if (!first) sb.append(", ");
    sb.append("subjectId:");
    sb.append(this.subjectId);
    first = false;
    if (!first) sb.append(", ");
    sb.append("content:");
    if (this.content == null) {
      sb.append("null");
    } else {
      sb.append(this.content);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("options:");
    if (this.options == null) {
      sb.append("null");
    } else {
      sb.append(this.options);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("answer:");
    if (this.answer == null) {
      sb.append("null");
    } else {
      sb.append(this.answer);
    }
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

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TPAdminModifyQuestionInfoStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TPAdminModifyQuestionInfoStandardScheme getScheme() {
      return new TPAdminModifyQuestionInfoStandardScheme();
    }
  }

  private static class TPAdminModifyQuestionInfoStandardScheme extends org.apache.thrift.scheme.StandardScheme<TPAdminModifyQuestionInfo> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TPAdminModifyQuestionInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.id = iprot.readI32();
              struct.setIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // SUBJECT_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.subjectId = iprot.readI32();
              struct.setSubjectIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // CONTENT
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.content = iprot.readString();
              struct.setContentIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // OPTIONS
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.options = iprot.readString();
              struct.setOptionsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // ANSWER
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.answer = iprot.readString();
              struct.setAnswerIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, TPAdminModifyQuestionInfo struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(ID_FIELD_DESC);
      oprot.writeI32(struct.id);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(SUBJECT_ID_FIELD_DESC);
      oprot.writeI32(struct.subjectId);
      oprot.writeFieldEnd();
      if (struct.content != null) {
        oprot.writeFieldBegin(CONTENT_FIELD_DESC);
        oprot.writeString(struct.content);
        oprot.writeFieldEnd();
      }
      if (struct.options != null) {
        oprot.writeFieldBegin(OPTIONS_FIELD_DESC);
        oprot.writeString(struct.options);
        oprot.writeFieldEnd();
      }
      if (struct.answer != null) {
        oprot.writeFieldBegin(ANSWER_FIELD_DESC);
        oprot.writeString(struct.answer);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TPAdminModifyQuestionInfoTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TPAdminModifyQuestionInfoTupleScheme getScheme() {
      return new TPAdminModifyQuestionInfoTupleScheme();
    }
  }

  private static class TPAdminModifyQuestionInfoTupleScheme extends org.apache.thrift.scheme.TupleScheme<TPAdminModifyQuestionInfo> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TPAdminModifyQuestionInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetId()) {
        optionals.set(0);
      }
      if (struct.isSetSubjectId()) {
        optionals.set(1);
      }
      if (struct.isSetContent()) {
        optionals.set(2);
      }
      if (struct.isSetOptions()) {
        optionals.set(3);
      }
      if (struct.isSetAnswer()) {
        optionals.set(4);
      }
      oprot.writeBitSet(optionals, 5);
      if (struct.isSetId()) {
        oprot.writeI32(struct.id);
      }
      if (struct.isSetSubjectId()) {
        oprot.writeI32(struct.subjectId);
      }
      if (struct.isSetContent()) {
        oprot.writeString(struct.content);
      }
      if (struct.isSetOptions()) {
        oprot.writeString(struct.options);
      }
      if (struct.isSetAnswer()) {
        oprot.writeString(struct.answer);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TPAdminModifyQuestionInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(5);
      if (incoming.get(0)) {
        struct.id = iprot.readI32();
        struct.setIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.subjectId = iprot.readI32();
        struct.setSubjectIdIsSet(true);
      }
      if (incoming.get(2)) {
        struct.content = iprot.readString();
        struct.setContentIsSet(true);
      }
      if (incoming.get(3)) {
        struct.options = iprot.readString();
        struct.setOptionsIsSet(true);
      }
      if (incoming.get(4)) {
        struct.answer = iprot.readString();
        struct.setAnswerIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

