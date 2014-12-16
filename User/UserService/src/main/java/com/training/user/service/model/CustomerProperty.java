package com.training.user.service.model;

import com.sun.jersey.server.linking.Binding;
import com.sun.jersey.server.linking.Link;
import com.sun.jersey.server.linking.Ref;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dhaya on 13.12.14.
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties({"customerId","customTagId"})
public class CustomerProperty extends AbstractModel{

    @Ref(value = "customers/{customerId}/customtags/{customTagId}/attribute/{attributeId}", bindings = {
            @Binding(name = "customerId", value = "${instance.customerId}"),
            @Binding(name = "customTagId", value = "${instance.customTagId}"),
            @Binding(name = "attributeId", value = "${instance.id}")
    }, style = Ref.Style.ABSOLUTE)
    private URI href;

    private String customerId;

    private String customTagId;

    private String name;

    private String value;

    public CustomerProperty(){
        super(Type.CustomerAttr);
    }

    public URI getHref() {
        return href;
    }

    public void setHref(URI href) {
        this.href = href;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomTagId() {
        return customTagId;
    }

    public void setCustomTagId(String customTagId) {
        this.customTagId = customTagId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue(){ return value;}

}
