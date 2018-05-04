package com.example.cvolk.aidlproj;

public class DataContract {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPkName() {
        return pkName;
    }

    public void setPkName(String pkName) {
        this.pkName = pkName;
    }

    public String getPkType() {
        return pkType;
    }

    public void setPkType(String pkType) {
        this.pkType = pkType;
    }

    public String[] getColNames() {
        return colNames;
    }

    public void setColNames(String[] colNames) {
        this.colNames = colNames;
    }

    public String[] getColTypes() {
        return colTypes;
    }

    public void setColTypes(String[] colTypes) {
        this.colTypes = colTypes;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    String name;
    String pkName;
    String pkType;
    String[] colNames;
    String[] colTypes;
    int version;

    public DataContract(String name, String pkName, String pkType, String[] colNames, String[] colTypes, int version) {
        this.name = name;
        this.pkName = pkName;
        this.pkType = pkType;
        this.colNames = colNames;
        this.colTypes = colTypes;
        this.version = version;
    }
}
