package com.training.user.service.util;

import javax.ws.rs.core.UriInfo;

/**
 * Created by dhaya on 14.12.14.
 */
public class AbstractHelper {

    protected UriInfo uriInfo;

    public AbstractHelper(UriInfo uriInfo){
        this.uriInfo = uriInfo;
    }
}
