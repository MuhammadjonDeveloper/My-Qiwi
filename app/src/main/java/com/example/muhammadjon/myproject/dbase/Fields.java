package com.example.muhammadjon.myproject.dbase;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Fields {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private long merchantId;
    private String name;
    private String type;
    private String labelRu;
    private String labelUz;
    private String labelEn;
    private long fieldSize;
    private String controlType;
    private String controlTypeInfo;
    private long parentId;
    private long ord;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(long merchantId) {
        this.merchantId = merchantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLabelRu() {
        return labelRu;
    }

    public void setLabelRu(String labelRu) {
        this.labelRu = labelRu;
    }

    public String getLabelUz() {
        return labelUz;
    }

    public void setLabelUz(String labelUz) {
        this.labelUz = labelUz;
    }

    public String getLabelEn() {
        return labelEn;
    }

    public void setLabelEn(String labelEn) {
        this.labelEn = labelEn;
    }

    public long getFieldSize() {
        return fieldSize;
    }

    public void setFieldSize(long fieldSize) {
        this.fieldSize = fieldSize;
    }

    public String getControlType() {
        return controlType;
    }

    public void setControlType(String controlType) {
        this.controlType = controlType;
    }

    public String getControlTypeInfo() {
        return controlTypeInfo;
    }

    public void setControlTypeInfo(String controlTypeInfo) {
        this.controlTypeInfo = controlTypeInfo;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public long getOrd() {
        return ord;
    }

    public void setOrd(long ord) {
        this.ord = ord;
    }
}
