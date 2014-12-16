package com.training.user.model.dto;

import com.training.user.model.api.AllowedAttributeType;
import com.training.user.model.api.ICustomTag;
import com.training.user.model.api.Validation;

/**
 * Created by dhaya on 13.12.14.
 */
public class CustomTagDTO extends AbstractDTO implements ICustomTag {

    private String name;

    private Integer allowedEntry;

    private Validation validation;

    private AllowedAttributeType allowedAttributeType;

    private String validationValue;

    public CustomTagDTO(Long id, String name){
        super(id);
        this.name = name;
    }

    public CustomTagDTO(ICustomTag customTag){
        super(customTag.getId());
        this.name = customTag.getName();
        this.allowedEntry = customTag.getAllowedEntry();
        this.allowedAttributeType = customTag.getAllowedAttributeType();
        this.validation = customTag.getValidation();
        this.validationValue = customTag.getValidationValue();
    }

    public CustomTagDTO(){
        super();
    }

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
    public void setValidationValue(String value) {
        this.validationValue = value;
    }
}
