package com.training.user.service.resource;

import com.training.user.service.model.AbstractModel;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

/**
 * Created by dhaya on 13.12.14.
 */
public class AbstractResource {

    @Context
    protected UriInfo uriInfo;

    protected Response getOkResponse(Object body) {
        Response.ResponseBuilder builder = Response.status(Response.Status.OK);
        return builder.entity(body).type(MediaType.APPLICATION_JSON).build();
    }
}
