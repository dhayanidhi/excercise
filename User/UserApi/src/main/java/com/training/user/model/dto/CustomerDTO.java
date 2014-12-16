package com.training.user.model.dto;

import com.training.user.model.api.ICustomTag;
import com.training.user.model.api.ICustomer;
import com.training.user.model.api.ICustomerAttribute;
import com.training.user.model.api.Sex;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dhaya on 13.12.14.
 */
public class CustomerDTO extends AbstractDTO implements ICustomer<CustomerAttributeDTO> {

    private String firstName;

    private String lastName;

    private Boolean active;

    private Integer age;

    private Sex sex;

    private List<CustomerAttributeDTO> customerAttributeDTOs = new ArrayList<CustomerAttributeDTO>();

    public CustomerDTO(Long Id, String firstName, String lastName, Sex sex){
        super(Id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
    }

    public CustomerDTO(ICustomer customer){
        super(customer.getId());
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.active = customer.isActive();
        this.age = customer.getAge();
        this.sex = customer.getSex();
        List<ICustomerAttribute> customerAttributes = customer.getCustomAttribute();
        for(ICustomerAttribute customerAttribute : customerAttributes){
            CustomTagDTO customTagDTO = new CustomTagDTO(customerAttribute.getCustomTag());
            customerAttributeDTOs.add(new CustomerAttributeDTO(this, customTagDTO,
                    customerAttribute.getValue(), customerAttribute.getId()));
        }
    }

    public CustomerDTO(){
        super();
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public Boolean isActive() {
        return active;
    }

    @Override
    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public Integer getAge() {
        return age;
    }

    @Override
    public void setSex(Sex sex) {
        this.sex = sex;
    }

    @Override
    public Sex getSex() {
        return sex;
    }

    @Override
    public void setCustomAttribute(List<CustomerAttributeDTO> customAttribute) {
        this.customerAttributeDTOs = customAttribute;
    }

    @Override
    public void addCustomAttribute(CustomerAttributeDTO customAttribute) {
        this.customerAttributeDTOs.add(customAttribute);
    }


    @Override
    public List<CustomerAttributeDTO> getCustomAttribute() {
        return customerAttributeDTOs;
    }
}
