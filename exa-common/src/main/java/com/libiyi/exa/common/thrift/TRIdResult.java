/**
 * Autogenerated by Thrift Compiler (0.11.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.libiyi.exa.common.thrift;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.11.0)", date = "2019-02-24")
public class TRIdResult implements org.apache.thrift.TBase<TRIdResult, TRIdResult._Fields>, java.io.Serializable, Cloneable, Comparable<TRIdResult> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TRIdResult");

  private static final org.apache.thrift.protocol.TField RESPONSE_FIELD_DESC = new org.apache.thrift.protocol.TField("response", org.apache.thrift.protocol.TType.STRUCT, (short)1);
  private static final org.apache.thrift.protocol.TField ID_FIELD_DESC = new org.apache.thrift.protocol.TField("id", org.apache.thrift.protocol.TType.I32, (short)2);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new TRIdResultStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new TRIdResultTupleSchemeFactory();

  public TRResponse response; // required
  public int id; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    RESPONSE((short)1, "response"),
    ID((short)2, "id");

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
        case 2: // ID
          return ID;
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
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.RESPONSE, new org.apache.thrift.meta_data.FieldMetaData("response", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, TRResponse.class)));
    tmpMap.put(_Fields.ID, new org.apache.thrift.meta_data.FieldMetaData("id", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TRIdResult.class, metaDataMap);
  }

  public TRIdResult() {
  }

  public TRIdResult(
    TRResponse response,
    int id)
  {
    this();
    this.response = response;
    this.id = id;
    setIdIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TRIdResult(TRIdResult other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetResponse()) {
      this.response = new TRResponse(other.response);
    }
    this.id = other.id;
  }

  public TRIdResult deepCopy() {
    return new TRIdResult(this);
  }

  @Override
  public void clear() {
    this.response = null;
    setIdIsSet(false);
    this.id = 0;
  }

  public TRResponse getResponse() {
    return this.response;
  }

  public TRIdResult setResponse(TRResponse response) {
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

  public int getId() {
    return this.id;
  }

  public TRIdResult setId(int id) {
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

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case RESPONSE:
      if (value == null) {
        unsetResponse();
      } else {
        setResponse((TRResponse)value);
      }
      break;

    case ID:
      if (value == null) {
        unsetId();
      } else {
        setId((Integer)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case RESPONSE:
      return getResponse();

    case ID:
      return getId();

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
    case ID:
      return isSetId();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TRIdResult)
      return this.equals((TRIdResult)that);
    return false;
  }

  public boolean equals(TRIdResult that) {
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

    boolean this_present_id = true;
    boolean that_present_id = true;
    if (this_present_id || that_present_id) {
      if (!(this_present_id && that_present_id))
        return false;
      if (this.id != that.id)
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

    hashCode = hashCode * 8191 + id;

    return hashCode;
  }

  @Override
  public int compareTo(TRIdResult other) {
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
    StringBuilder sb = new StringBuilder("TRIdResult(");
    boolean first = true;

    sb.append("response:");
    if (this.response == null) {
      sb.append("null");
    } else {
      sb.append(this.response);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("id:");
    sb.append(this.id);
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
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TRIdResultStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TRIdResultStandardScheme getScheme() {
      return new TRIdResultStandardScheme();
    }
  }

  private static class TRIdResultStandardScheme extends org.apache.thrift.scheme.StandardScheme<TRIdResult> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TRIdResult struct) throws org.apache.thrift.TException {
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
          case 2: // ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.id = iprot.readI32();
              struct.setIdIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, TRIdResult struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.response != null) {
        oprot.writeFieldBegin(RESPONSE_FIELD_DESC);
        struct.response.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(ID_FIELD_DESC);
      oprot.writeI32(struct.id);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TRIdResultTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TRIdResultTupleScheme getScheme() {
      return new TRIdResultTupleScheme();
    }
  }

  private static class TRIdResultTupleScheme extends org.apache.thrift.scheme.TupleScheme<TRIdResult> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TRIdResult struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetResponse()) {
        optionals.set(0);
      }
      if (struct.isSetId()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetResponse()) {
        struct.response.write(oprot);
      }
      if (struct.isSetId()) {
        oprot.writeI32(struct.id);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TRIdResult struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.response = new TRResponse();
        struct.response.read(iprot);
        struct.setResponseIsSet(true);
      }
      if (incoming.get(1)) {
        struct.id = iprot.readI32();
        struct.setIdIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

