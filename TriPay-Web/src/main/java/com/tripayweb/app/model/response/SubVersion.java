package com.tripayweb.app.model.response;



/**
 * Created by vibhu on 25-07-2016.
 */
public enum SubVersion {
    SUBVERSION_0("0"),SUBVERSION_1("1"),SUBVERSION_2("2"),SUBVERSION_3("3"),SUBVERSION_4("4"),SUBVERSION_5("5"),SUBVERSION_6("6"),SUBVERSION_7("7"),SUBVERSION_8("8"),SUBVERSION_9("9");
    private String value;

    private SubVersion(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
    public static SubVersion getEnum(String value) {
        if (value == null)
            throw new IllegalArgumentException();
        for (SubVersion v : values())
            if (value.equalsIgnoreCase(v.getValue()))
                return v;
        throw new IllegalArgumentException();
    }



    }
