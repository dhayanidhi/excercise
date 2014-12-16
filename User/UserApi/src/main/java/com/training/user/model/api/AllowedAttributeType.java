package com.training.user.model.api;

import java.util.Calendar;

/**
 * Created by dhaya on 13.12.14.
 */
public enum AllowedAttributeType {
    VAL_INTEGER(Integer.class),
    VAL_STRING(String.class),
    VAL_LONG(Long.class),
    VAL_CALENDER(Calendar.class);

    Class classVal;
    AllowedAttributeType(Class val){
        this.classVal = val;
    }

    public Class getClassVal(){return classVal;}
}
