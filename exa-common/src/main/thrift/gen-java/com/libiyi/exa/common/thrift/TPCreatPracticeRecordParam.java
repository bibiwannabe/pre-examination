/**
 * Autogenerated by Thrift Compiler (0.11.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.libiyi.exa.common.thrift;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.11.0)", date = "2019-03-05")
public class TPCreatPracticeRecordParam implements org.apache.thrift.TBase<TPCreatPracticeRecordParam, TPCreatPracticeRecordParam._Fields>, java.io.Serializable, Cloneable, Comparable<TPCreatPracticeRecordParam> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TPCreatPracticeRecordParam");

  private static final org.apache.thrift.protocol.TField CHOICE_FIELD_DESC = new org.apache.thrift.protocol.TField("choice", org.apache.thrift.protocol.TType.MAP, (short)1);
  private static final org.apache.thrift.protocol.TField SELECTION_FIELD_DESC = new org.apache.thrift.protocol.TField("selection", org.apache.thrift.protocol.TType.MAP, (short)2);
  private static final org.apache.thrift.protocol.TField FILLING_FIELD_DESC = new org.apache.thrift.protocol.TField("filling", org.apache.thrift.protocol.TType.MAP, (short)3);
  private static final org.apache.thrift.protocol.TField USER_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("userId", org.apache.thrift.protocol.TType.I32, (short)4);
  private static final org.apache.thrift.protocol.TField PAPER_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("paperId", org.apache.thrift.protocol.TType.I32, (short)5);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new TPCreatPracticeRecordParamStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new TPCreatPracticeRecordParamTupleSchemeFactory();

  public java.util.Map<java.lang.Integer,java.lang.String> choice; // required
  public java.util.Map<java.lang.Integer,java.lang.String> selection; // required
  public java.util.Map<java.lang.Integer,java.lang.String> filling; // required
  public int userId; // required
  public int paperId; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    CHOICE((short)1, "choice"),
    SELECTION((short)2, "selection"),
    FILLING((short)3, "filling"),
    USER_ID((short)4, "userId"),
    PAPER_ID((short)5, "paperId");

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
        case 1: // CHOICE
          return CHOICE;
        case 2: // SELECTION
          return SELECTION;
        case 3: // FILLING
          return FILLING;
        case 4: // USER_ID
          return USER_ID;
        case 5: // PAPER_ID
          return PAPER_ID;
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
  private static final int __USERID_ISSET_ID = 0;
  private static final int __PAPERID_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.CHOICE, new org.apache.thrift.meta_data.FieldMetaData("choice", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.MapMetaData(org.apache.thrift.protocol.TType.MAP, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32), 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    tmpMap.put(_Fields.SELECTION, new org.apache.thrift.meta_data.FieldMetaData("selection", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.MapMetaData(org.apache.thrift.protocol.TType.MAP, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32), 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    tmpMap.put(_Fields.FILLING, new org.apache.thrift.meta_data.FieldMetaData("filling", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.MapMetaData(org.apache.thrift.protocol.TType.MAP, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32), 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    tmpMap.put(_Fields.USER_ID, new org.apache.thrift.meta_data.FieldMetaData("userId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.PAPER_ID, new org.apache.thrift.meta_data.FieldMetaData("paperId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TPCreatPracticeRecordParam.class, metaDataMap);
  }

  public TPCreatPracticeRecordParam() {
  }

  public TPCreatPracticeRecordParam(
    java.util.Map<java.lang.Integer,java.lang.String> choice,
    java.util.Map<java.lang.Integer,java.lang.String> selection,
    java.util.Map<java.lang.Integer,java.lang.String> filling,
    int userId,
    int paperId)
  {
    this();
    this.choice = choice;
    this.selection = selection;
    this.filling = filling;
    this.userId = userId;
    setUserIdIsSet(true);
    this.paperId = paperId;
    setPaperIdIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TPCreatPracticeRecordParam(TPCreatPracticeRecordParam other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetChoice()) {
      java.util.Map<java.lang.Integer,java.lang.String> __this__choice = new java.util.HashMap<java.lang.Integer,java.lang.String>(other.choice);
      this.choice = __this__choice;
    }
    if (other.isSetSelection()) {
      java.util.Map<java.lang.Integer,java.lang.String> __this__selection = new java.util.HashMap<java.lang.Integer,java.lang.String>(other.selection);
      this.selection = __this__selection;
    }
    if (other.isSetFilling()) {
      java.util.Map<java.lang.Integer,java.lang.String> __this__filling = new java.util.HashMap<java.lang.Integer,java.lang.String>(other.filling);
      this.filling = __this__filling;
    }
    this.userId = other.userId;
    this.paperId = other.paperId;
  }

  public TPCreatPracticeRecordParam deepCopy() {
    return new TPCreatPracticeRecordParam(this);
  }

  @Override
  public void clear() {
    this.choice = null;
    this.selection = null;
    this.filling = null;
    setUserIdIsSet(false);
    this.userId = 0;
    setPaperIdIsSet(false);
    this.paperId = 0;
  }

  public int getChoiceSize() {
    return (this.choice == null) ? 0 : this.choice.size();
  }

  public void putToChoice(int key, java.lang.String val) {
    if (this.choice == null) {
      this.choice = new java.util.HashMap<java.lang.Integer,java.lang.String>();
    }
    this.choice.put(key, val);
  }

  public java.util.Map<java.lang.Integer,java.lang.String> getChoice() {
    return this.choice;
  }

  public TPCreatPracticeRecordParam setChoice(java.util.Map<java.lang.Integer,java.lang.String> choice) {
    this.choice = choice;
    return this;
  }

  public void unsetChoice() {
    this.choice = null;
  }

  /** Returns true if field choice is set (has been assigned a value) and false otherwise */
  public boolean isSetChoice() {
    return this.choice != null;
  }

  public void setChoiceIsSet(boolean value) {
    if (!value) {
      this.choice = null;
    }
  }

  public int getSelectionSize() {
    return (this.selection == null) ? 0 : this.selection.size();
  }

  public void putToSelection(int key, java.lang.String val) {
    if (this.selection == null) {
      this.selection = new java.util.HashMap<java.lang.Integer,java.lang.String>();
    }
    this.selection.put(key, val);
  }

  public java.util.Map<java.lang.Integer,java.lang.String> getSelection() {
    return this.selection;
  }

  public TPCreatPracticeRecordParam setSelection(java.util.Map<java.lang.Integer,java.lang.String> selection) {
    this.selection = selection;
    return this;
  }

  public void unsetSelection() {
    this.selection = null;
  }

  /** Returns true if field selection is set (has been assigned a value) and false otherwise */
  public boolean isSetSelection() {
    return this.selection != null;
  }

  public void setSelectionIsSet(boolean value) {
    if (!value) {
      this.selection = null;
    }
  }

  public int getFillingSize() {
    return (this.filling == null) ? 0 : this.filling.size();
  }

  public void putToFilling(int key, java.lang.String val) {
    if (this.filling == null) {
      this.filling = new java.util.HashMap<java.lang.Integer,java.lang.String>();
    }
    this.filling.put(key, val);
  }

  public java.util.Map<java.lang.Integer,java.lang.String> getFilling() {
    return this.filling;
  }

  public TPCreatPracticeRecordParam setFilling(java.util.Map<java.lang.Integer,java.lang.String> filling) {
    this.filling = filling;
    return this;
  }

  public void unsetFilling() {
    this.filling = null;
  }

  /** Returns true if field filling is set (has been assigned a value) and false otherwise */
  public boolean isSetFilling() {
    return this.filling != null;
  }

  public void setFillingIsSet(boolean value) {
    if (!value) {
      this.filling = null;
    }
  }

  public int getUserId() {
    return this.userId;
  }

  public TPCreatPracticeRecordParam setUserId(int userId) {
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

  public int getPaperId() {
    return this.paperId;
  }

  public TPCreatPracticeRecordParam setPaperId(int paperId) {
    this.paperId = paperId;
    setPaperIdIsSet(true);
    return this;
  }

  public void unsetPaperId() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __PAPERID_ISSET_ID);
  }

  /** Returns true if field paperId is set (has been assigned a value) and false otherwise */
  public boolean isSetPaperId() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __PAPERID_ISSET_ID);
  }

  public void setPaperIdIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __PAPERID_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case CHOICE:
      if (value == null) {
        unsetChoice();
      } else {
        setChoice((java.util.Map<java.lang.Integer,java.lang.String>)value);
      }
      break;

    case SELECTION:
      if (value == null) {
        unsetSelection();
      } else {
        setSelection((java.util.Map<java.lang.Integer,java.lang.String>)value);
      }
      break;

    case FILLING:
      if (value == null) {
        unsetFilling();
      } else {
        setFilling((java.util.Map<java.lang.Integer,java.lang.String>)value);
      }
      break;

    case USER_ID:
      if (value == null) {
        unsetUserId();
      } else {
        setUserId((java.lang.Integer)value);
      }
      break;

    case PAPER_ID:
      if (value == null) {
        unsetPaperId();
      } else {
        setPaperId((java.lang.Integer)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case CHOICE:
      return getChoice();

    case SELECTION:
      return getSelection();

    case FILLING:
      return getFilling();

    case USER_ID:
      return getUserId();

    case PAPER_ID:
      return getPaperId();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case CHOICE:
      return isSetChoice();
    case SELECTION:
      return isSetSelection();
    case FILLING:
      return isSetFilling();
    case USER_ID:
      return isSetUserId();
    case PAPER_ID:
      return isSetPaperId();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof TPCreatPracticeRecordParam)
      return this.equals((TPCreatPracticeRecordParam)that);
    return false;
  }

  public boolean equals(TPCreatPracticeRecordParam that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_choice = true && this.isSetChoice();
    boolean that_present_choice = true && that.isSetChoice();
    if (this_present_choice || that_present_choice) {
      if (!(this_present_choice && that_present_choice))
        return false;
      if (!this.choice.equals(that.choice))
        return false;
    }

    boolean this_present_selection = true && this.isSetSelection();
    boolean that_present_selection = true && that.isSetSelection();
    if (this_present_selection || that_present_selection) {
      if (!(this_present_selection && that_present_selection))
        return false;
      if (!this.selection.equals(that.selection))
        return false;
    }

    boolean this_present_filling = true && this.isSetFilling();
    boolean that_present_filling = true && that.isSetFilling();
    if (this_present_filling || that_present_filling) {
      if (!(this_present_filling && that_present_filling))
        return false;
      if (!this.filling.equals(that.filling))
        return false;
    }

    boolean this_present_userId = true;
    boolean that_present_userId = true;
    if (this_present_userId || that_present_userId) {
      if (!(this_present_userId && that_present_userId))
        return false;
      if (this.userId != that.userId)
        return false;
    }

    boolean this_present_paperId = true;
    boolean that_present_paperId = true;
    if (this_present_paperId || that_present_paperId) {
      if (!(this_present_paperId && that_present_paperId))
        return false;
      if (this.paperId != that.paperId)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetChoice()) ? 131071 : 524287);
    if (isSetChoice())
      hashCode = hashCode * 8191 + choice.hashCode();

    hashCode = hashCode * 8191 + ((isSetSelection()) ? 131071 : 524287);
    if (isSetSelection())
      hashCode = hashCode * 8191 + selection.hashCode();

    hashCode = hashCode * 8191 + ((isSetFilling()) ? 131071 : 524287);
    if (isSetFilling())
      hashCode = hashCode * 8191 + filling.hashCode();

    hashCode = hashCode * 8191 + userId;

    hashCode = hashCode * 8191 + paperId;

    return hashCode;
  }

  @Override
  public int compareTo(TPCreatPracticeRecordParam other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetChoice()).compareTo(other.isSetChoice());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetChoice()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.choice, other.choice);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetSelection()).compareTo(other.isSetSelection());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSelection()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.selection, other.selection);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetFilling()).compareTo(other.isSetFilling());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetFilling()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.filling, other.filling);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetUserId()).compareTo(other.isSetUserId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUserId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.userId, other.userId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetPaperId()).compareTo(other.isSetPaperId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPaperId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.paperId, other.paperId);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("TPCreatPracticeRecordParam(");
    boolean first = true;

    sb.append("choice:");
    if (this.choice == null) {
      sb.append("null");
    } else {
      sb.append(this.choice);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("selection:");
    if (this.selection == null) {
      sb.append("null");
    } else {
      sb.append(this.selection);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("filling:");
    if (this.filling == null) {
      sb.append("null");
    } else {
      sb.append(this.filling);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("userId:");
    sb.append(this.userId);
    first = false;
    if (!first) sb.append(", ");
    sb.append("paperId:");
    sb.append(this.paperId);
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

  private static class TPCreatPracticeRecordParamStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TPCreatPracticeRecordParamStandardScheme getScheme() {
      return new TPCreatPracticeRecordParamStandardScheme();
    }
  }

  private static class TPCreatPracticeRecordParamStandardScheme extends org.apache.thrift.scheme.StandardScheme<TPCreatPracticeRecordParam> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TPCreatPracticeRecordParam struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // CHOICE
            if (schemeField.type == org.apache.thrift.protocol.TType.MAP) {
              {
                org.apache.thrift.protocol.TMap _map64 = iprot.readMapBegin();
                struct.choice = new java.util.HashMap<java.lang.Integer,java.lang.String>(2*_map64.size);
                int _key65;
                java.lang.String _val66;
                for (int _i67 = 0; _i67 < _map64.size; ++_i67)
                {
                  _key65 = iprot.readI32();
                  _val66 = iprot.readString();
                  struct.choice.put(_key65, _val66);
                }
                iprot.readMapEnd();
              }
              struct.setChoiceIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // SELECTION
            if (schemeField.type == org.apache.thrift.protocol.TType.MAP) {
              {
                org.apache.thrift.protocol.TMap _map68 = iprot.readMapBegin();
                struct.selection = new java.util.HashMap<java.lang.Integer,java.lang.String>(2*_map68.size);
                int _key69;
                java.lang.String _val70;
                for (int _i71 = 0; _i71 < _map68.size; ++_i71)
                {
                  _key69 = iprot.readI32();
                  _val70 = iprot.readString();
                  struct.selection.put(_key69, _val70);
                }
                iprot.readMapEnd();
              }
              struct.setSelectionIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // FILLING
            if (schemeField.type == org.apache.thrift.protocol.TType.MAP) {
              {
                org.apache.thrift.protocol.TMap _map72 = iprot.readMapBegin();
                struct.filling = new java.util.HashMap<java.lang.Integer,java.lang.String>(2*_map72.size);
                int _key73;
                java.lang.String _val74;
                for (int _i75 = 0; _i75 < _map72.size; ++_i75)
                {
                  _key73 = iprot.readI32();
                  _val74 = iprot.readString();
                  struct.filling.put(_key73, _val74);
                }
                iprot.readMapEnd();
              }
              struct.setFillingIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // USER_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.userId = iprot.readI32();
              struct.setUserIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // PAPER_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.paperId = iprot.readI32();
              struct.setPaperIdIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, TPCreatPracticeRecordParam struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.choice != null) {
        oprot.writeFieldBegin(CHOICE_FIELD_DESC);
        {
          oprot.writeMapBegin(new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.I32, org.apache.thrift.protocol.TType.STRING, struct.choice.size()));
          for (java.util.Map.Entry<java.lang.Integer, java.lang.String> _iter76 : struct.choice.entrySet())
          {
            oprot.writeI32(_iter76.getKey());
            oprot.writeString(_iter76.getValue());
          }
          oprot.writeMapEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.selection != null) {
        oprot.writeFieldBegin(SELECTION_FIELD_DESC);
        {
          oprot.writeMapBegin(new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.I32, org.apache.thrift.protocol.TType.STRING, struct.selection.size()));
          for (java.util.Map.Entry<java.lang.Integer, java.lang.String> _iter77 : struct.selection.entrySet())
          {
            oprot.writeI32(_iter77.getKey());
            oprot.writeString(_iter77.getValue());
          }
          oprot.writeMapEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.filling != null) {
        oprot.writeFieldBegin(FILLING_FIELD_DESC);
        {
          oprot.writeMapBegin(new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.I32, org.apache.thrift.protocol.TType.STRING, struct.filling.size()));
          for (java.util.Map.Entry<java.lang.Integer, java.lang.String> _iter78 : struct.filling.entrySet())
          {
            oprot.writeI32(_iter78.getKey());
            oprot.writeString(_iter78.getValue());
          }
          oprot.writeMapEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(USER_ID_FIELD_DESC);
      oprot.writeI32(struct.userId);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(PAPER_ID_FIELD_DESC);
      oprot.writeI32(struct.paperId);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TPCreatPracticeRecordParamTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TPCreatPracticeRecordParamTupleScheme getScheme() {
      return new TPCreatPracticeRecordParamTupleScheme();
    }
  }

  private static class TPCreatPracticeRecordParamTupleScheme extends org.apache.thrift.scheme.TupleScheme<TPCreatPracticeRecordParam> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TPCreatPracticeRecordParam struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetChoice()) {
        optionals.set(0);
      }
      if (struct.isSetSelection()) {
        optionals.set(1);
      }
      if (struct.isSetFilling()) {
        optionals.set(2);
      }
      if (struct.isSetUserId()) {
        optionals.set(3);
      }
      if (struct.isSetPaperId()) {
        optionals.set(4);
      }
      oprot.writeBitSet(optionals, 5);
      if (struct.isSetChoice()) {
        {
          oprot.writeI32(struct.choice.size());
          for (java.util.Map.Entry<java.lang.Integer, java.lang.String> _iter79 : struct.choice.entrySet())
          {
            oprot.writeI32(_iter79.getKey());
            oprot.writeString(_iter79.getValue());
          }
        }
      }
      if (struct.isSetSelection()) {
        {
          oprot.writeI32(struct.selection.size());
          for (java.util.Map.Entry<java.lang.Integer, java.lang.String> _iter80 : struct.selection.entrySet())
          {
            oprot.writeI32(_iter80.getKey());
            oprot.writeString(_iter80.getValue());
          }
        }
      }
      if (struct.isSetFilling()) {
        {
          oprot.writeI32(struct.filling.size());
          for (java.util.Map.Entry<java.lang.Integer, java.lang.String> _iter81 : struct.filling.entrySet())
          {
            oprot.writeI32(_iter81.getKey());
            oprot.writeString(_iter81.getValue());
          }
        }
      }
      if (struct.isSetUserId()) {
        oprot.writeI32(struct.userId);
      }
      if (struct.isSetPaperId()) {
        oprot.writeI32(struct.paperId);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TPCreatPracticeRecordParam struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(5);
      if (incoming.get(0)) {
        {
          org.apache.thrift.protocol.TMap _map82 = new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.I32, org.apache.thrift.protocol.TType.STRING, iprot.readI32());
          struct.choice = new java.util.HashMap<java.lang.Integer,java.lang.String>(2*_map82.size);
          int _key83;
          java.lang.String _val84;
          for (int _i85 = 0; _i85 < _map82.size; ++_i85)
          {
            _key83 = iprot.readI32();
            _val84 = iprot.readString();
            struct.choice.put(_key83, _val84);
          }
        }
        struct.setChoiceIsSet(true);
      }
      if (incoming.get(1)) {
        {
          org.apache.thrift.protocol.TMap _map86 = new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.I32, org.apache.thrift.protocol.TType.STRING, iprot.readI32());
          struct.selection = new java.util.HashMap<java.lang.Integer,java.lang.String>(2*_map86.size);
          int _key87;
          java.lang.String _val88;
          for (int _i89 = 0; _i89 < _map86.size; ++_i89)
          {
            _key87 = iprot.readI32();
            _val88 = iprot.readString();
            struct.selection.put(_key87, _val88);
          }
        }
        struct.setSelectionIsSet(true);
      }
      if (incoming.get(2)) {
        {
          org.apache.thrift.protocol.TMap _map90 = new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.I32, org.apache.thrift.protocol.TType.STRING, iprot.readI32());
          struct.filling = new java.util.HashMap<java.lang.Integer,java.lang.String>(2*_map90.size);
          int _key91;
          java.lang.String _val92;
          for (int _i93 = 0; _i93 < _map90.size; ++_i93)
          {
            _key91 = iprot.readI32();
            _val92 = iprot.readString();
            struct.filling.put(_key91, _val92);
          }
        }
        struct.setFillingIsSet(true);
      }
      if (incoming.get(3)) {
        struct.userId = iprot.readI32();
        struct.setUserIdIsSet(true);
      }
      if (incoming.get(4)) {
        struct.paperId = iprot.readI32();
        struct.setPaperIdIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

