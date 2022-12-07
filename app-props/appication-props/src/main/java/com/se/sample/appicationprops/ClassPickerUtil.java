package com.se.sample.appicationprops;


import static com.se.sample.appicationprops.DefaultValueTypes.*;

public final class ClassPickerUtil {

    private ClassPickerUtil() { }

    public static Class getValueClass(String type) {
        switch (type.toUpperCase()) {
            case STRING_TYPE:
                return String.class;
            case INTEGER_TYPE:
                return Integer.class;
            case LONG_TYPE:
                return Long.class;
            case BOOLEAN_TYPE:
                return Boolean.class;
            case SHIP_ALONE_TYPE:
                return ShipAlone.class;
            case ADD_ON_FLAG_TYPE:
                return AddOnFlag.class;
            default:
                return Object.class;
        }
    }
}

