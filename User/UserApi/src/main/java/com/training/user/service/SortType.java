package com.training.user.service;

/**
 * Created by dhaya on 15.12.14.
 */
public enum SortType {

    ID("id"),
    AGE("age");

    private String value;

    SortType(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public static SortType getByValue(String type){
        for(SortType sortType : values()){
            if(sortType.getValue().equalsIgnoreCase(type))
                return sortType;
        }
        return null;
    }
}
