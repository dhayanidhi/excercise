package com.training.user.model.entity;

import com.training.user.model.api.ICustomerAttribute;
import com.training.user.model.api.Validation;

import javax.persistence.*;

/**
 * Created by dhaya on 13.12.14.
 */
@Entity
@Table(name = "rs_customer_attribute")
public class CustomerAttributePE extends AbstractPE implements ICustomerAttribute<CustomerPE, CustomTagPE> {

    @ManyToOne(targetEntity = CustomerPE.class, cascade = {})
    @JoinColumn(name = "fk_customer")
    private CustomerPE customerPE;

    @ManyToOne(targetEntity = CustomTagPE.class, cascade = {})
    @JoinColumn(name = "fk_custom_tag")
    private CustomTagPE customTagPE;

    @Column(name = "c_value")
    private String value;

    @Override
    public CustomerPE getCustomer() {
        return customerPE;
    }

    @Override
    public void setCustomer(CustomerPE customer) {
        customerPE = customer;
    }

    @Override
    public CustomTagPE getCustomTag() {
        return customTagPE;
    }

    @Override
    public void setCustomTag(CustomTagPE customTag) {
        customTagPE = customTag;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerAttributePE)) return false;
        if (!super.equals(o)) return false;

        CustomerAttributePE that = (CustomerAttributePE) o;

        if (!customTagPE.equals(that.customTagPE)) return false;
        if (customerPE != null ? !customerPE.equals(that.customerPE) : that.customerPE != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (customerPE != null ? customerPE.hashCode() : 0);
        result = 31 * result + customTagPE.hashCode();
        return result;
    }
}
