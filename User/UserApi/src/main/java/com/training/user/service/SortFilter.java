package com.training.user.service;

/**
 * Created by dhaya on 15.12.14.
 */
public class SortFilter {

    boolean asc = true;

    SortType sortType = null;

    public SortFilter(SortType sortType, boolean asc){
        this.sortType = sortType;
        this.asc = asc;
    }

    public boolean isAsc() {
        return asc;
    }

    public SortType getType() {
        return sortType;
    }
}
