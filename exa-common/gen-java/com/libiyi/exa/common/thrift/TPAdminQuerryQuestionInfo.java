/**
 * Autogenerated by Thrift Compiler (0.11.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.libiyi.exa.common.thrift;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.11.0)", date = "2019-01-16")
public class TPAdminQuerryQuestionInfo implements org.apache.thrift.TBase<TPAdminQuerryQuestionInfo, TPAdminQuerryQuestionInfo._Fields>, java.io.Serializable, Cloneable, Comparable<TPAdminQuerryQuestionInfo> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TPAdminQuerryQuestionInfo");

  private static final org.apache.thrift.protocol.TField SUBJECT_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("subjectId", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("type", org.apache.thrift.protocol.TType.I32, (short)2);
  private static final org.apache.thrift.protocol.TField QUERRY_TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("querryType", org.apache.thrift.protocol.TType.I32, (short)3);
  private static final org.apache.thrift.protocol.TField PAGINATION_FIELD_DESC = new org.apache.thrift.protocol.TField("pagination", org.apache.thrift.protocol.TType.STRUCT, (short)4);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new TPAdminQuerryQuestionInfoStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new TPAdminQuerryQuestionInfoTupleSchemeFactory();

  public int subjectId; // required
  public int type; // required
  public int querryType; // required
  public TPagination pagination; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    SUBJECT_ID((short)1, "subjectId"),
    TYPE((short)2, "type"),
    QUERRY_TYPE((short)3, "querryType"),
    PAGINATION((short)4, "pagination");

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
        case 2: // TYPE
          return TYPE;
        case 3: // QUERRY_TYPE
          return QUERRY_TYPE;
        case 4: // PAGINATION
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
  private static final int __SUBJECTID_ISSET_ID = 0;
  private static final int __TYPE_ISSET_ID = 1;
  private static final int __QUERRYTYPE_ISSET_ID = 2;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.SUBJECT_ID, new org.apache.thrift.meta_data.FieldMetaData("subjectId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.TYPE, new org.apache.thrift.meta_data.FieldMetaData("type", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.QUERRY_TYPE, new org.apache.thrift.meta_data.FieldMetaData("querryType", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.PAGINATION, new org.apache.thrift.meta_data.FieldMetaData("pagination", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, TPagination.class)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TPAdminQuerryQuestionInfo.class, metaDataMap);
  }

  public TPAdminQuerryQuestionInfo() {
  }

  public TPAdminQuerryQuestionInfo(
    int subjectId,
    int type,
    int querryType,
    TPagination pagination)
  {
    this();
    this.subjectId = subjectId;
    setSubjectIdIsSet(true);
    this.type = type;
    setTypeIsSet(true);
    this.querryType = querryType;
    setQuerryTypeIsSet(true);
    this.pagination = pagination;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TPAdminQuerryQuestionInfo(TPAdminQuerryQuestionInfo other) {
    __isset_bitfield = other.__isset_bitfield;
    this.subjectId = other.subjectId;
    this.type = other.type;
    this.querryType = other.querryType;
    if (other.isSetPagination()) {
      this.pagination = new TPagination(other.pagination);
    }
  }

  public TPAdminQuerryQuestionInfo deepCopy() {
    return new TPAdminQuerryQuestionInfo(this);
  }

  @Override
  public void clear() {
    setSubjectIdIsSet(false);
    this.subjectId = 0;
    setTypeIsSet(false);
    this.type = 0;
    setQuerryTypeIsSet(false);
    this.querryType = 0;
    this.pagination = null;
  }

  public int getSubjectId() {
    return this.subjectId;
  }

  public TPAdminQuerryQuestionInfo setSubjectId(int subjectId) {
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

  public int getType() {
    return this.type;
  }

  public TPAdminQuerryQuestionInfo setType(int type) {
    this.type = type;
    setTypeIsSet(true);
    return this;
  }

  public void unsetType() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __TYPE_ISSET_ID);
  }

  /** Returns true if field type is set (has been assigned a value) and false otherwise */
  public boolean isSetType() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __TYPE_ISSET_ID);
  }

  public void setTypeIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __TYPE_ISSET_ID, value);
  }

  public int getQuerryType() {
    return this.querryType;
  }

  public TPAdminQuerryQuestionInfo setQuerryType(int querryType) {
    this.querryType = querryType;
    setQuerryTypeIsSet(true);
    return this;
  }

  public void unsetQuerryType() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __QUERRYTYPE_ISSET_ID);
  }

  /** Returns true if field querryType is set (has been assigned a value) and false otherwise */
  public boolean isSetQuerryType() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __QUERRYTYPE_ISSET_ID);
  }

  public void setQuerryTypeIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __QUERRYTYPE_ISSET_ID, value);
  }

  public TPagination getPagination() {
    return this.pagination;
  }

  public TPAdminQuerryQuestionInfo setPagination(TPagination pagination) {
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
    case SUBJECT_ID:
      if (value == null) {
        unsetSubjectId();
      } else {
        setSubjectId((java.lang.Integer)value);
      }
      break;

    case TYPE:
      if (value == null) {
        unsetType();
      } else {
        setType((java.lang.Integer)value);
      }
      break;

    case QUERRY_TYPE:
      if (value == null) {
        unsetQuerryType();
      } else {
        setQuerryType((java.lang.Integer)value);
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
    case SUBJECT_ID:
      return getSubjectId();

    case TYPE:
      return getType();

    case QUERRY_TYPE:
      return getQuerryType();

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
    case SUBJECT_ID:
      return isSetSubjectId();
    case TYPE:
      return isSetType();
    case QUERRY_TYPE:
      return isSetQuerryType();
    case PAGINATION:
      return isSetPagination();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof TPAdminQuerryQuestionInfo)
      return this.equals((TPAdminQuerryQuestionInfo)that);
    return false;
  }

  public boolean equals(TPAdminQuerryQuestionInfo that) {
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

    boolean this_present_type = true;
    boolean that_present_type = true;
    if (this_present_type || that_present_type) {
      if (!(this_present_type && that_present_type))
        return false;
      if (this.type != that.type)
        return false;
    }

    boolean this_present_querryType = true;
    boolean that_present_querryType = true;
    if (this_present_querryType || that_present_querryType) {
      if (!(this_present_querryType && that_present_querryType))
        return false;
      if (this.querryType != that.querryType)
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

    hashCode = hashCode * 8191 + subjectId;

    hashCode = hashCode * 8191 + type;

    hashCode = hashCode * 8191 + querryType;

    hashCode = hashCode * 8191 + ((isSetPagination()) ? 131071 : 524287);
    if (isSetPagination())
      hashCode = hashCode * 8191 + pagination.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(TPAdminQuerryQuestionInfo other) {
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
    lastComparison = java.lang.Boolean.valueOf(isSetType()).compareTo(other.isSetType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.type, other.type);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetQuerryType()).compareTo(other.isSetQuerryType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetQuerryType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.querryType, other.querryType);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("TPAdminQuerryQuestionInfo(");
    boolean first = true;

    sb.append("subjectId:");
    sb.append(this.subjectId);
    first = false;
    if (!first) sb.append(", ");
    sb.append("type:");
    sb.append(this.type);
    first = false;
    if (!first) sb.append(", ");
    sb.append("querryType:");
    sb.append(this.querryType);
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
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TPAdminQuerryQuestionInfoStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TPAdminQuerryQuestionInfoStandardScheme getScheme() {
      return new TPAdminQuerryQuestionInfoStandardScheme();
    }
  }

  private static class TPAdminQuerryQuestionInfoStandardScheme extends org.apache.thrift.scheme.StandardScheme<TPAdminQuerryQuestionInfo> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TPAdminQuerryQuestionInfo struct) throws org.apache.thrift.TException {
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
          case 2: // TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.type = iprot.readI32();
              struct.setTypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // QUERRY_TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.querryType = iprot.readI32();
              struct.setQuerryTypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // PAGINATION
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, TPAdminQuerryQuestionInfo struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(SUBJECT_ID_FIELD_DESC);
      oprot.writeI32(struct.subjectId);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(TYPE_FIELD_DESC);
      oprot.writeI32(struct.type);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(QUERRY_TYPE_FIELD_DESC);
      oprot.writeI32(struct.querryType);
      oprot.writeFieldEnd();
      if (struct.pagination != null) {
        oprot.writeFieldBegin(PAGINATION_FIELD_DESC);
        struct.pagination.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TPAdminQuerryQuestionInfoTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TPAdminQuerryQuestionInfoTupleScheme getScheme() {
      return new TPAdminQuerryQuestionInfoTupleScheme();
    }
  }

  private static class TPAdminQuerryQuestionInfoTupleScheme extends org.apache.thrift.scheme.TupleScheme<TPAdminQuerryQuestionInfo> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TPAdminQuerryQuestionInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetSubjectId()) {
        optionals.set(0);
      }
      if (struct.isSetType()) {
        optionals.set(1);
      }
      if (struct.isSetQuerryType()) {
        optionals.set(2);
      }
      if (struct.isSetPagination()) {
        optionals.set(3);
      }
      oprot.writeBitSet(optionals, 4);
      if (struct.isSetSubjectId()) {
        oprot.writeI32(struct.subjectId);
      }
      if (struct.isSetType()) {
        oprot.writeI32(struct.type);
      }
      if (struct.isSetQuerryType()) {
        oprot.writeI32(struct.querryType);
      }
      if (struct.isSetPagination()) {
        struct.pagination.write(oprot);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TPAdminQuerryQuestionInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(4);
      if (incoming.get(0)) {
        struct.subjectId = iprot.readI32();
        struct.setSubjectIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.type = iprot.readI32();
        struct.setTypeIsSet(true);
      }
      if (incoming.get(2)) {
        struct.querryType = iprot.readI32();
        struct.setQuerryTypeIsSet(true);
      }
      if (incoming.get(3)) {
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

