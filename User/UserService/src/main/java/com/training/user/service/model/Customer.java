package com.training.user.service.model;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.jersey.server.linking.Binding;
import com.sun.jersey.server.linking.Ref;
import com.training.user.model.api.Sex;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Created by dhaya on 13.12.14.
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Customer extends AbstractModel{

    @Ref(value = "customers/{customerId}", bindings = {
            @Binding(name = "customerId", value = "${instance.id}")
    }, style = Ref.Style.ABSOLUTE)
    private URI href;

    private String firstName;

    private String lastName;

    private Sex sex;

    private Integer age;

    private Boolean active;

    private List<CustomerProperty> customerPropertyList;

    public Customer(){
        super(Type.Customer);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public URI getHref() {
        return href;
    }

    public void setHref(URI href) {
        this.href = href;
    }

    public List<CustomerProperty> getCustomerPropertyList() {
        return customerPropertyList;
    }

    public void setCustomerPropertyList(List<CustomerProperty> customerPropertyList) {
        this.customerPropertyList = customerPropertyList;
    }

    public void addCustomerProperty(CustomerProperty customerProperty){
        customerPropertyList = (customerPropertyList == null) ? new ArrayList<CustomerProperty>() :customerPropertyList;
        this.customerPropertyList.add(customerProperty);
    }
}
