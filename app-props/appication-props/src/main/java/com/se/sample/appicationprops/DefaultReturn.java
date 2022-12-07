package com.se.sample.appicationprops;


public enum DefaultReturn {
    STRING(String.class, null),
    INTEGER(Integer.class, null),
    DOUBLE(Double.class, null),
    LONG(Long.class, null),
    BOOLEAN(Boolean.class, false),
    SHIP_ALONE(ShipAlone.class, ShipAlone.N),
    ADD_ON_FLAG(AddOnFlag.class, null);

    final Class clazz;

    final Object value;

    DefaultReturn(Class clazz, Object value) {
        this.clazz = clazz;
        this.value = value;
    }

    public Class getClazz() {
        return clazz;
    }

    public Object getValue() {
        return value;
    }

    public static Object defaultReturnValue(Class type) {
        for (DefaultReturn dr : DefaultReturn.values()) {
            if(dr.getClazz().equals(type)){
                return dr.getValue();
            }
        }
        return null;
    }
}

