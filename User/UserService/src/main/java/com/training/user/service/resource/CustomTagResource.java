package com.training.user.service.resource;

import com.training.user.service.IUserAccess;
import com.training.user.service.model.CustomTag;
import com.training.user.service.util.CustomTagHelper;
import com.training.user.service.util.CustomerHelper;
import com.training.user.service.util.HelperFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Created by dhaya on 13.12.14.
 */
@Stateless
@Path("/customtags")
@Produces({ "application/json" })
@Consumes({ "application/json" })
public class CustomTagResource extends AbstractResource {

    @EJB
    IUserAccess userAccess;

    @GET
    public Response getAllCustomTag(){
        CustomTagHelper customTagHelper = HelperFactory.getCustomTagHelper(uriInfo);
        return getOkResponse(customTagHelper.CONVERTER.getCustomTags(userAccess.getCustomTags()));
    }

    @POST
    public Response createCustomTag(CustomTag customTag){
        CustomTagHelper customTagHelper = HelperFactory.getCustomTagHelper(uriInfo);
        String customTagId = userAccess.createCustomTag(customTagHelper.CONVERTER.getCustomTag(customTag));
        return getOkResponse(customTagHelper.CONVERTER.getCustomTag(customTagId));
    }

    @GET
    @Path("/{customTagId}")
    public Response getCustomTag(@PathParam(value = "customTagId") String id){
        CustomTagHelper customTagHelper = HelperFactory.getCustomTagHelper(uriInfo).validateId(id);
        return getOkResponse(customTagHelper.CONVERTER.getCustomTag(userAccess.getCustomTag(id)));
    }

    @PUT
    @Path("/{customTagId}")
    public Response updateCustomTag(@PathParam(value = "customTagId") String id, CustomTag customTag){
        CustomTagHelper customTagHelper = HelperFactory.getCustomTagHelper(uriInfo).validateId(id);
        customTag.setId(id);
        userAccess.updateCustomTag(customTagHelper.CONVERTER.getCustomTag(customTag));
        return getOkResponse(customTagHelper.CONVERTER.getCustomTag(id));
    }

    @DELETE
    @Path("/{customTagId}")
    public Response deleteCustomTag(@PathParam(value = "customTagId") String id){
        CustomTagHelper customTagHelper = HelperFactory.getCustomTagHelper(uriInfo).validateId(id);
        userAccess.deleteCustomTag(id);
        return getOkResponse(customTagHelper.CONVERTER.getCustomTag(id));
    }

}
