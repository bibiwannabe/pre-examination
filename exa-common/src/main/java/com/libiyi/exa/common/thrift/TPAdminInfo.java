/**
 * Autogenerated by Thrift Compiler (0.11.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.libiyi.exa.common.thrift;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.11.0)", date = "2019-01-17")
public class TPAdminInfo implements org.apache.thrift.TBase<TPAdminInfo, TPAdminInfo._Fields>, java.io.Serializable, Cloneable, Comparable<TPAdminInfo> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TPAdminInfo");

  private static final org.apache.thrift.protocol.TField USER_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("userId", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField TITLE_FIELD_DESC = new org.apache.thrift.protocol.TField("title", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField SUBJECT_FIELD_DESC = new org.apache.thrift.protocol.TField("subject", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField TEACH_YEAR_FIELD_DESC = new org.apache.thrift.protocol.TField("teachYear", org.apache.thrift.protocol.TType.I32, (short)4);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new TPAdminInfoStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new TPAdminInfoTupleSchemeFactory();

  public int userId; // required
  public String title; // required
  public String subject; // required
  public int teachYear; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    USER_ID((short)1, "userId"),
    TITLE((short)2, "title"),
    SUBJECT((short)3, "subject"),
    TEACH_YEAR((short)4, "teachYear");

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
        case 1: // USER_ID
          return USER_ID;
        case 2: // TITLE
          return TITLE;
        case 3: // SUBJECT
          return SUBJECT;
        case 4: // TEACH_YEAR
          return TEACH_YEAR;
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
  private static final int __USERID_ISSET_ID = 0;
  private static final int __TEACHYEAR_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.USER_ID, new org.apache.thrift.meta_data.FieldMetaData("userId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.TITLE, new org.apache.thrift.meta_data.FieldMetaData("title", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.SUBJECT, new org.apache.thrift.meta_data.FieldMetaData("subject", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.TEACH_YEAR, new org.apache.thrift.meta_data.FieldMetaData("teachYear", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TPAdminInfo.class, metaDataMap);
  }

  public TPAdminInfo() {
  }

  public TPAdminInfo(
    int userId,
    String title,
    String subject,
    int teachYear)
  {
    this();
    this.userId = userId;
    setUserIdIsSet(true);
    this.title = title;
    this.subject = subject;
    this.teachYear = teachYear;
    setTeachYearIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TPAdminInfo(TPAdminInfo other) {
    __isset_bitfield = other.__isset_bitfield;
    this.userId = other.userId;
    if (other.isSetTitle()) {
      this.title = other.title;
    }
    if (other.isSetSubject()) {
      this.subject = other.subject;
    }
    this.teachYear = other.teachYear;
  }

  public TPAdminInfo deepCopy() {
    return new TPAdminInfo(this);
  }

  @Override
  public void clear() {
    setUserIdIsSet(false);
    this.userId = 0;
    this.title = null;
    this.subject = null;
    setTeachYearIsSet(false);
    this.teachYear = 0;
  }

  public int getUserId() {
    return this.userId;
  }

  public TPAdminInfo setUserId(int userId) {
    this.userId = userId;
    setUserIdIsSet(true);
    return this;
  }

  public void unsetUserId() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __USERID_ISSET_ID);
  }

  /** Returns true if field userId is set (has been assigned a value) and false otherwise */
  public boolean isSetUserId() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __USERID_ISSET_ID);
  }

  public void setUserIdIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __USERID_ISSET_ID, value);
  }

  public String getTitle() {
    return this.title;
  }

  public TPAdminInfo setTitle(String title) {
    this.title = title;
    return this;
  }

  public void unsetTitle() {
    this.title = null;
  }

  /** Returns true if field title is set (has been assigned a value) and false otherwise */
  public boolean isSetTitle() {
    return this.title != null;
  }

  public void setTitleIsSet(boolean value) {
    if (!value) {
      this.title = null;
    }
  }

  public String getSubject() {
    return this.subject;
  }

  public TPAdminInfo setSubject(String subject) {
    this.subject = subject;
    return this;
  }

  public void unsetSubject() {
    this.subject = null;
  }

  /** Returns true if field subject is set (has been assigned a value) and false otherwise */
  public boolean isSetSubject() {
    return this.subject != null;
  }

  public void setSubjectIsSet(boolean value) {
    if (!value) {
      this.subject = null;
    }
  }

  public int getTeachYear() {
    return this.teachYear;
  }

  public TPAdminInfo setTeachYear(int teachYear) {
    this.teachYear = teachYear;
    setTeachYearIsSet(true);
    return this;
  }

  public void unsetTeachYear() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __TEACHYEAR_ISSET_ID);
  }

  /** Returns true if field teachYear is set (has been assigned a value) and false otherwise */
  public boolean isSetTeachYear() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __TEACHYEAR_ISSET_ID);
  }

  public void setTeachYearIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __TEACHYEAR_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case USER_ID:
      if (value == null) {
        unsetUserId();
      } else {
        setUserId((Integer)value);
      }
      break;

    case TITLE:
      if (value == null) {
        unsetTitle();
      } else {
        setTitle((String)value);
      }
      break;

    case SUBJECT:
      if (value == null) {
        unsetSubject();
      } else {
        setSubject((String)value);
      }
      break;

    case TEACH_YEAR:
      if (value == null) {
        unsetTeachYear();
      } else {
        setTeachYear((Integer)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case USER_ID:
      return getUserId();

    case TITLE:
      return getTitle();

    case SUBJECT:
      return getSubject();

    case TEACH_YEAR:
      return getTeachYear();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case USER_ID:
      return isSetUserId();
    case TITLE:
      return isSetTitle();
    case SUBJECT:
      return isSetSubject();
    case TEACH_YEAR:
      return isSetTeachYear();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TPAdminInfo)
      return this.equals((TPAdminInfo)that);
    return false;
  }

  public boolean equals(TPAdminInfo that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_userId = true;
    boolean that_present_userId = true;
    if (this_present_userId || that_present_userId) {
      if (!(this_present_userId && that_present_userId))
        return false;
      if (this.userId != that.userId)
        return false;
    }

    boolean this_present_title = true && this.isSetTitle();
    boolean that_present_title = true && that.isSetTitle();
    if (this_present_title || that_present_title) {
      if (!(this_present_title && that_present_title))
        return false;
      if (!this.title.equals(that.title))
        return false;
    }

    boolean this_present_subject = true && this.isSetSubject();
    boolean that_present_subject = true && that.isSetSubject();
    if (this_present_subject || that_present_subject) {
      if (!(this_present_subject && that_present_subject))
        return false;
      if (!this.subject.equals(that.subject))
        return false;
    }

    boolean this_present_teachYear = true;
    boolean that_present_teachYear = true;
    if (this_present_teachYear || that_present_teachYear) {
      if (!(this_present_teachYear && that_present_teachYear))
        return false;
      if (this.teachYear != that.teachYear)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + userId;

    hashCode = hashCode * 8191 + ((isSetTitle()) ? 131071 : 524287);
    if (isSetTitle())
      hashCode = hashCode * 8191 + title.hashCode();

    hashCode = hashCode * 8191 + ((isSetSubject()) ? 131071 : 524287);
    if (isSetSubject())
      hashCode = hashCode * 8191 + subject.hashCode();

    hashCode = hashCode * 8191 + teachYear;

    return hashCode;
  }

  @Override
  public int compareTo(TPAdminInfo other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetUserId()).compareTo(other.isSetUserId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUserId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.userId, other.userId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTitle()).compareTo(other.isSetTitle());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTitle()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.title, other.title);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetSubject()).compareTo(other.isSetSubject());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSubject()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.subject, other.subject);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTeachYear()).compareTo(other.isSetTeachYear());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTeachYear()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.teachYear, other.teachYear);
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
    StringBuilder sb = new StringBuilder("TPAdminInfo(");
    boolean first = true;

    sb.append("userId:");
    sb.append(this.userId);
    first = false;
    if (!first) sb.append(", ");
    sb.append("title:");
    if (this.title == null) {
      sb.append("null");
    } else {
      sb.append(this.title);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("subject:");
    if (this.subject == null) {
      sb.append("null");
    } else {
      sb.append(this.subject);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("teachYear:");
    sb.append(this.teachYear);
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

  private static class TPAdminInfoStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TPAdminInfoStandardScheme getScheme() {
      return new TPAdminInfoStandardScheme();
    }
  }

  private static class TPAdminInfoStandardScheme extends org.apache.thrift.scheme.StandardScheme<TPAdminInfo> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TPAdminInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // USER_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.userId = iprot.readI32();
              struct.setUserIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // TITLE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.title = iprot.readString();
              struct.setTitleIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // SUBJECT
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.subject = iprot.readString();
              struct.setSubjectIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // TEACH_YEAR
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.teachYear = iprot.readI32();
              struct.setTeachYearIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, TPAdminInfo struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(USER_ID_FIELD_DESC);
      oprot.writeI32(struct.userId);
      oprot.writeFieldEnd();
      if (struct.title != null) {
        oprot.writeFieldBegin(TITLE_FIELD_DESC);
        oprot.writeString(struct.title);
        oprot.writeFieldEnd();
      }
      if (struct.subject != null) {
        oprot.writeFieldBegin(SUBJECT_FIELD_DESC);
        oprot.writeString(struct.subject);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(TEACH_YEAR_FIELD_DESC);
      oprot.writeI32(struct.teachYear);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TPAdminInfoTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TPAdminInfoTupleScheme getScheme() {
      return new TPAdminInfoTupleScheme();
    }
  }

  private static class TPAdminInfoTupleScheme extends org.apache.thrift.scheme.TupleScheme<TPAdminInfo> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TPAdminInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetUserId()) {
        optionals.set(0);
      }
      if (struct.isSetTitle()) {
        optionals.set(1);
      }
      if (struct.isSetSubject()) {
        optionals.set(2);
      }
      if (struct.isSetTeachYear()) {
        optionals.set(3);
      }
      oprot.writeBitSet(optionals, 4);
      if (struct.isSetUserId()) {
        oprot.writeI32(struct.userId);
      }
      if (struct.isSetTitle()) {
        oprot.writeString(struct.title);
      }
      if (struct.isSetSubject()) {
        oprot.writeString(struct.subject);
      }
      if (struct.isSetTeachYear()) {
        oprot.writeI32(struct.teachYear);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TPAdminInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(4);
      if (incoming.get(0)) {
        struct.userId = iprot.readI32();
        struct.setUserIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.title = iprot.readString();
        struct.setTitleIsSet(true);
      }
      if (incoming.get(2)) {
        struct.subject = iprot.readString();
        struct.setSubjectIsSet(true);
      }
      if (incoming.get(3)) {
        struct.teachYear = iprot.readI32();
        struct.setTeachYearIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

