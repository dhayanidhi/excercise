package com.training.user.service;

import com.training.user.model.api.Sex;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dhaya on 13.12.14.
 */
public class CustomerAccessFilter {

    private String firstName;

    private String lastName;

    private Sex sex;

    private Integer minAge;

    private Integer maxAge;

    private List<SortFilter> sortFilterList = new ArrayList<SortFilter>();

    public static CustomerAccessFilter INSTANCE(){return new CustomerAccessFilter();}

    public String getFirstName() {
        return firstName;
    }

    public CustomerAccessFilter setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CustomerAccessFilter setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Sex getSex() {
        return sex;
    }

    public CustomerAccessFilter setSex(String sex) {
        this.sex = sex != null ? Sex.valueOf(sex) : null;
        return this;
    }

    public int getMinAge() {
        return minAge == null ? 0 : minAge;
    }

    public CustomerAccessFilter setMinAge(Integer minAge) {
        this.minAge = minAge;
        return this;
    }

    public int getMaxAge() {
        return maxAge == null ? 0 : maxAge;
    }

    public CustomerAccessFilter setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
        return this;
    }

    public CustomerAccessFilter addSortFilter(String name, boolean asc){
        this.sortFilterList.add(new SortFilter(SortType.getByValue(name), asc));
        return this;
    }

    public CustomerAccessFilter addAscSortFilter(String name){
        addSortFilter(name,true);
        return this;
    }

    public CustomerAccessFilter addDescSortFilter(String name){
        addSortFilter(name,false);
        return this;
    }

    public List<SortFilter> getSortFilter(){
        return sortFilterList;
    }
}
