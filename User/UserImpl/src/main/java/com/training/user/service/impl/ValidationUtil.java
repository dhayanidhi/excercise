package com.training.user.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.training.user.model.api.ICustomTag;
import com.training.user.model.api.ICustomer;
import com.training.user.model.api.ICustomerAttribute;
import com.training.user.model.api.Validation;
import com.training.user.model.entity.CustomTagPE;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dhaya on 18.12.14.
 */
public class ValidationUtil {

    static List<Validation> createValidations = new ArrayList<Validation>();
    static {
        createValidations.add(new AllowedIndexValidation());
        createValidations.add(new CustomValueValidation());
    }

    static List<Validation> updateValidations = new ArrayList<Validation>();
    static {
        createValidations.add(new CustomValueValidation());
    }

    public static boolean validateCreate(AttributeWrapper attributeWrapper){
        boolean result = true;
        for(Validation validation : createValidations){
            result = validation.check(attributeWrapper);
            if(!result)
                break;
        }
        return result;
    }

    public static boolean validateUpdate(AttributeWrapper attributeWrapper){
        boolean result = true;
        for(Validation validation : updateValidations){
            result = validation.check(attributeWrapper);
            if(!result)
                break;
        }
        return result;
    }

    interface Validation {
        boolean check(AttributeWrapper attributeWrapper);
    }

    static class AllowedIndexValidation implements Validation{
        public boolean check(AttributeWrapper attributeWrapper){
            return !(attributeWrapper.getExistingTagCount() == attributeWrapper.getAllowedCount());
        }
    }

    static class CustomValueValidation implements Validation{
        public boolean check(AttributeWrapper attributeWrapper){
            if(!attributeWrapper.isValueValidationRequired())
                return true;
            boolean result = true;
            switch (attributeWrapper.getValidationType()){
                    case JSON_ALLOWED_VALUES:
                        result = checkAllowedValue(attributeWrapper.getValidationValue(),attributeWrapper.getAttributeValue());
                        break;
                    case REGULAR_EXPRESSION:
                        result = checkRegex(attributeWrapper.getValidationValue(),
                                attributeWrapper.getAttributeValue());
                        break;
            }
            return result;
        }

        private boolean checkRegex(String regex, String value){
            return value.matches(regex);
        }

        private boolean checkAllowedValue(String allowedValue, String value){
            return true;
        }
    }
}
