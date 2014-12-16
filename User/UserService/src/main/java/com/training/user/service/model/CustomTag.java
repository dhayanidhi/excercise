package com.training.user.service.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.jersey.server.linking.Binding;
import com.sun.jersey.server.linking.Ref;
import com.training.user.model.api.Validation;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.net.URI;

/**
 * Created by dhaya on 14.12.14.
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class CustomTag extends AbstractModel {

    @Ref(value = "customtags/{customTagId}", bindings = {
            @Binding(name = "customTagId", value = "${instance.id}")
    }, style = Ref.Style.ABSOLUTE)
    private URI href;

    private String name;

    private Integer allowedEntry;

    private Validation validation;

    private String validationValue;

    public CustomTag(){
        super(Type.CustomTag);
    }

    public URI getHref() {
        return href;
    }

    public void setHref(URI href) {
        this.href = href;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAllowedEntry() {
        return allowedEntry;
    }

    public void setAllowedEntry(Integer allowedEntry) {
        this.allowedEntry = allowedEntry;
    }

    public Validation getValidation() {
        return validation;
    }

    public void setValidation(Validation validation) {
        this.validation = validation;
    }

    public String getValidationValue() {
        return validationValue;
    }

    public void setValidationValue(String validationValue) {
        this.validationValue = validationValue;
    }
}
