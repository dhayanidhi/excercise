package com.training.user.model.api;

/**
 * Created by dhaya on 13.12.14.
 */
public interface ICustomTag extends IAbstract {

    String getName();
    
    void setName(String name);

    Integer getAllowedEntry();

    void setAllowedEntry(Integer allowedEntry);

    Validation getValidation();

    void setValidation(Validation validation);

    AllowedAttributeType getAllowedAttributeType();

    void setAllowedAttributeType(AllowedAttributeType allowedAttributeType);

    String getValidationValue();

    void setValidationValue(String value);
}
