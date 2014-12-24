package com.training.user.service.impl;

import com.training.user.model.api.ICustomTag;
import com.training.user.model.api.ICustomer;
import com.training.user.model.api.ICustomerAttribute;
import com.training.user.model.api.Validation;
import com.training.user.model.entity.CustomTagPE;
import com.training.user.model.entity.CustomerAttributePE;
import com.training.user.model.entity.CustomerPE;

/**
 * Created by dhaya on 19.12.14.
 */
public class AttributeWrapper {

    CustomerPE customer;
    CustomerAttributePE customAttribute;
    CustomTagPE customTag;

    public static AttributeWrapper init(){return new AttributeWrapper();}

    AttributeWrapper wrap(CustomerPE customer, CustomTagPE customTag){
        this.customer = customer;
        this.customTag = customTag;
        return this;
    }

    AttributeWrapper verify(CustomerAttributePE customerAttribute) {
        this.customAttribute = customerAttribute;
        this.customer = customerAttribute.getCustomer();
        this.customTag = customerAttribute.getCustomTag();
        return this;
    }

    public int getExistingTagCount(){
        int count = 0;
        for(ICustomerAttribute old : customer.getCustomAttribute()){
            if(old.getCustomTag().equals(customTag))
                count++;
        }
        return count;
    }

    public int getAllowedCount(){
        return customTag.getAllowedEntry() == null ? -1 : customTag.getAllowedEntry();
    }

    public boolean isValueValidationRequired(){
        return customTag.getValidation() != null && customTag.getValidationValue() != null;
    }

    public Validation getValidationType(){
        return customTag.getValidation();
    }

    public String getValidationValue(){
        return customTag.getValidationValue();
    }

    public String getAttributeValue(){
        return customAttribute.getValue();
    }

}
