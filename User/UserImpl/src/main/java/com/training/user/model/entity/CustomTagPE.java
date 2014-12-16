package com.training.user.model.entity;

import com.training.user.model.api.AllowedAttributeType;
import com.training.user.model.api.ICustomTag;
import com.training.user.model.api.Validation;

import javax.persistence.*;

/**
 * Created by dhaya on 13.12.14.
 */
@Entity
@Table(name = "rs_custom_tag")
public class CustomTagPE extends AbstractPE implements ICustomTag {

    @Column(name = "c_name")
    private String name;

    @Column(name = "c_allowed_entry")
    private Integer allowedEntry;

    @Column(name = "c_validation")
    @Enumerated(EnumType.STRING)
    private Validation validation;

    @Column(name = "c_attribute_type")
    @Enumerated(EnumType.STRING)
    private AllowedAttributeType allowedAttributeType;

    @Column(name = "c_validation_value")
    private String validationValue;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Integer getAllowedEntry() {
        return allowedEntry;
    }

    @Override
    public void setAllowedEntry(Integer allowedEntry) {
        this.allowedEntry = allowedEntry;
    }

    @Override
    public Validation getValidation() {
        return validation;
    }

    @Override
    public void setValidation(Validation validation) {
        this.validation = validation;
    }

    @Override
    public AllowedAttributeType getAllowedAttributeType() {
        return allowedAttributeType;
    }

    @Override
    public void setAllowedAttributeType(AllowedAttributeType allowedAttributeType) {
        this.allowedAttributeType = allowedAttributeType;
    }

    @Override
    public String getValidationValue() {
        return validationValue;
    }

    @Override
    public void setValidationValue(String validationValue) {
        this.validationValue = validationValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomTagPE)) return false;
        if (!super.equals(o)) return false;

        CustomTagPE that = (CustomTagPE) o;

        if (allowedAttributeType != that.allowedAttributeType) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (allowedAttributeType != null ? allowedAttributeType.hashCode() : 0);
        return result;
    }
}
