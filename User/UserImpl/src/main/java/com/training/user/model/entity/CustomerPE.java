package com.training.user.model.entity;

import com.training.user.model.api.ICustomer;
import com.training.user.model.api.ICustomerAttribute;
import com.training.user.model.api.Sex;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dhaya on 13.12.14.
 */
@Entity
@Table(name = "rs_customer")
public class CustomerPE extends AbstractPE implements ICustomer<CustomerAttributePE> {

    public static final String CustomerById = "Customer.LoadById";

    public static String firstNameMeta = "firstName";

    public static String lastNameMeta = "lastName";

    public static String activeMeta = "active";

    public static String ageMeta = "age";

    public static String sexMeta = "sex";

    public static String customAttributeMeta = "customerAttributes";

    @Column(name = "c_firstname")
    private String firstName;

    @Column(name = "c_lastname")
    private String lastName;

    @Column(name = "c_active")
    private Boolean active;

    @Column(name = "c_age")
    private Integer age;

    @Column(name = "c_sex")
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "customerPE",
            targetEntity = CustomerAttributePE.class)
    private List<CustomerAttributePE> customerAttributes = new ArrayList<CustomerAttributePE>();

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
    public void setCustomAttribute(List<CustomerAttributePE> customAttribute) {
        this.customerAttributes = customAttribute;
    }

    @Override
    public void addCustomAttribute(CustomerAttributePE customAttribute) {
        customerAttributes.add(customAttribute);
    }

    @Override
    public List<CustomerAttributePE> getCustomAttribute() {
        return customerAttributes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerPE)) return false;
        if (!super.equals(o)) return false;

        CustomerPE that = (CustomerPE) o;

        if (active != null ? !active.equals(that.active) : that.active != null) return false;
        if (age != null ? !age.equals(that.age) : that.age != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (sex != that.sex) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        return result;
    }
}
