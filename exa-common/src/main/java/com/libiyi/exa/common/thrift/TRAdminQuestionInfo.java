/**
 * Autogenerated by Thrift Compiler (0.11.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.libiyi.exa.common.thrift;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.11.0)", date = "2019-02-20")
public class TRAdminQuestionInfo implements org.apache.thrift.TBase<TRAdminQuestionInfo, TRAdminQuestionInfo._Fields>, java.io.Serializable, Cloneable, Comparable<TRAdminQuestionInfo> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TRAdminQuestionInfo");

  private static final org.apache.thrift.protocol.TField ADMIN_QUESTION_INFO_FIELD_DESC = new org.apache.thrift.protocol.TField("adminQuestionInfo", org.apache.thrift.protocol.TType.STRUCT, (short)1);
  private static final org.apache.thrift.protocol.TField RESPONSE_FIELD_DESC = new org.apache.thrift.protocol.TField("response", org.apache.thrift.protocol.TType.STRUCT, (short)2);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new TRAdminQuestionInfoStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new TRAdminQuestionInfoTupleSchemeFactory();

  public TAdminQuestionInfo adminQuestionInfo; // required
  public TRResponse response; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ADMIN_QUESTION_INFO((short)1, "adminQuestionInfo"),
    RESPONSE((short)2, "response");

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
        case 1: // ADMIN_QUESTION_INFO
          return ADMIN_QUESTION_INFO;
        case 2: // RESPONSE
          return RESPONSE;
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
    tmpMap.put(_Fields.ADMIN_QUESTION_INFO, new org.apache.thrift.meta_data.FieldMetaData("adminQuestionInfo", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRUCT        , "TAdminQuestionInfo")));
    tmpMap.put(_Fields.RESPONSE, new org.apache.thrift.meta_data.FieldMetaData("response", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, TRResponse.class)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TRAdminQuestionInfo.class, metaDataMap);
  }

  public TRAdminQuestionInfo() {
  }

  public TRAdminQuestionInfo(
    TAdminQuestionInfo adminQuestionInfo,
    TRResponse response)
  {
    this();
    this.adminQuestionInfo = adminQuestionInfo;
    this.response = response;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TRAdminQuestionInfo(TRAdminQuestionInfo other) {
    if (other.isSetAdminQuestionInfo()) {
      this.adminQuestionInfo = new TAdminQuestionInfo(other.adminQuestionInfo);
    }
    if (other.isSetResponse()) {
      this.response = new TRResponse(other.response);
    }
  }

  public TRAdminQuestionInfo deepCopy() {
    return new TRAdminQuestionInfo(this);
  }

  @Override
  public void clear() {
    this.adminQuestionInfo = null;
    this.response = null;
  }

  public TAdminQuestionInfo getAdminQuestionInfo() {
    return this.adminQuestionInfo;
  }

  public TRAdminQuestionInfo setAdminQuestionInfo(TAdminQuestionInfo adminQuestionInfo) {
    this.adminQuestionInfo = adminQuestionInfo;
    return this;
  }

  public void unsetAdminQuestionInfo() {
    this.adminQuestionInfo = null;
  }

  /** Returns true if field adminQuestionInfo is set (has been assigned a value) and false otherwise */
  public boolean isSetAdminQuestionInfo() {
    return this.adminQuestionInfo != null;
  }

  public void setAdminQuestionInfoIsSet(boolean value) {
    if (!value) {
      this.adminQuestionInfo = null;
    }
  }

  public TRResponse getResponse() {
    return this.response;
  }

  public TRAdminQuestionInfo setResponse(TRResponse response) {
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

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case ADMIN_QUESTION_INFO:
      if (value == null) {
        unsetAdminQuestionInfo();
      } else {
        setAdminQuestionInfo((TAdminQuestionInfo)value);
      }
      break;

    case RESPONSE:
      if (value == null) {
        unsetResponse();
      } else {
        setResponse((TRResponse)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case ADMIN_QUESTION_INFO:
      return getAdminQuestionInfo();

    case RESPONSE:
      return getResponse();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case ADMIN_QUESTION_INFO:
      return isSetAdminQuestionInfo();
    case RESPONSE:
      return isSetResponse();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TRAdminQuestionInfo)
      return this.equals((TRAdminQuestionInfo)that);
    return false;
  }

  public boolean equals(TRAdminQuestionInfo that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_adminQuestionInfo = true && this.isSetAdminQuestionInfo();
    boolean that_present_adminQuestionInfo = true && that.isSetAdminQuestionInfo();
    if (this_present_adminQuestionInfo || that_present_adminQuestionInfo) {
      if (!(this_present_adminQuestionInfo && that_present_adminQuestionInfo))
        return false;
      if (!this.adminQuestionInfo.equals(that.adminQuestionInfo))
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

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetAdminQuestionInfo()) ? 131071 : 524287);
    if (isSetAdminQuestionInfo())
      hashCode = hashCode * 8191 + adminQuestionInfo.hashCode();

    hashCode = hashCode * 8191 + ((isSetResponse()) ? 131071 : 524287);
    if (isSetResponse())
      hashCode = hashCode * 8191 + response.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(TRAdminQuestionInfo other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetAdminQuestionInfo()).compareTo(other.isSetAdminQuestionInfo());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAdminQuestionInfo()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.adminQuestionInfo, other.adminQuestionInfo);
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
    StringBuilder sb = new StringBuilder("TRAdminQuestionInfo(");
    boolean first = true;

    sb.append("adminQuestionInfo:");
    if (this.adminQuestionInfo == null) {
      sb.append("null");
    } else {
      sb.append(this.adminQuestionInfo);
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
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
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
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TRAdminQuestionInfoStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TRAdminQuestionInfoStandardScheme getScheme() {
      return new TRAdminQuestionInfoStandardScheme();
    }
  }

  private static class TRAdminQuestionInfoStandardScheme extends org.apache.thrift.scheme.StandardScheme<TRAdminQuestionInfo> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TRAdminQuestionInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ADMIN_QUESTION_INFO
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.adminQuestionInfo = new TAdminQuestionInfo();
              struct.adminQuestionInfo.read(iprot);
              struct.setAdminQuestionInfoIsSet(true);
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
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, TRAdminQuestionInfo struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.adminQuestionInfo != null) {
        oprot.writeFieldBegin(ADMIN_QUESTION_INFO_FIELD_DESC);
        struct.adminQuestionInfo.write(oprot);
        oprot.writeFieldEnd();
      }
      if (struct.response != null) {
        oprot.writeFieldBegin(RESPONSE_FIELD_DESC);
        struct.response.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TRAdminQuestionInfoTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TRAdminQuestionInfoTupleScheme getScheme() {
      return new TRAdminQuestionInfoTupleScheme();
    }
  }

  private static class TRAdminQuestionInfoTupleScheme extends org.apache.thrift.scheme.TupleScheme<TRAdminQuestionInfo> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TRAdminQuestionInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetAdminQuestionInfo()) {
        optionals.set(0);
      }
      if (struct.isSetResponse()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetAdminQuestionInfo()) {
        struct.adminQuestionInfo.write(oprot);
      }
      if (struct.isSetResponse()) {
        struct.response.write(oprot);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TRAdminQuestionInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.adminQuestionInfo = new TAdminQuestionInfo();
        struct.adminQuestionInfo.read(iprot);
        struct.setAdminQuestionInfoIsSet(true);
      }
      if (incoming.get(1)) {
        struct.response = new TRResponse();
        struct.response.read(iprot);
        struct.setResponseIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

