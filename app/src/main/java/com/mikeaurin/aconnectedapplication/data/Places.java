package com.mikeaurin.aconnectedapplication.data;


public class Places {

    private String mName;
    private String mType;

    public Places() { mName = mType = "";}

    public Places(String _name, String _type) {
        mName = _name;
        mType = _type;
    }

    public String getName() {
        return mName;
    }

    public String getType() {
        return mType;
    }
}
