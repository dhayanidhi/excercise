package com.training.user.service.model;

import com.sun.jersey.server.linking.Binding;
import com.sun.jersey.server.linking.Ref;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

/**
 * Created by dhaya on 15.12.14.
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Customers {

    private URI previousHref;

    private URI nextHref;

    private URI selfHref;

    private List<Customer> customer;

    public URI getNextHref() {
        return nextHref;
    }

    public void setNextHref(UriInfo currentHref, int page, int skip, int limit, int total) {
        if((skip+limit) > total)
            return;
        UriBuilder ub = currentHref.getRequestUriBuilder().clone();
        this.nextHref = ub.replaceQueryParam("page", (page+1)).
                replaceQueryParam("limit", limit).
                build();
    }

    public void setPreviousHref(UriInfo currentHref, int page, int skip, int limit, int total) {
        if((skip - limit) < 0)
            return;
        UriBuilder ub = currentHref.getRequestUriBuilder().clone();
        this.previousHref = ub.replaceQueryParam("page", (page-1)).
                replaceQueryParam("limit", limit).
                build();
    }

    public void setCurrentHref(UriInfo currentHref, int page, int skip, int limit) {
        UriBuilder ub = currentHref.getRequestUriBuilder().clone();
        this.selfHref = ub.replaceQueryParam("page", page).
                replaceQueryParam("limit", limit).
                build();
    }

    public URI getSelfHref() {
        return selfHref;
    }

    public void setSelfHref(URI selfHref) {
        this.selfHref = selfHref;
    }

    public URI getPreviousHref() {
        return previousHref;
    }

    public void setPreviousHref(URI previousHref) {
        this.previousHref = previousHref;
    }

    public Customers(){
    }

    public List<Customer> getCustomers() {
        return customer;
    }

    public void setCustomers(List<Customer> customer) {
        this.customer = customer;
    }
}
