package com.training.user.service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Created by dhaya on 13.12.14.
 */
@JsonPropertyOrder({"id", "type", "href"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class AbstractModel {

    protected String id;

    protected Type type;

    public AbstractModel(Type type){this.type = type;}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
