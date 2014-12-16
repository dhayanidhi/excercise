package com.training.user.service.resource;

import com.training.user.model.dto.CustomerDTO;
import com.training.user.service.CustomerAccessFilter;
import com.training.user.service.IUserAccess;
import com.training.user.service.model.Customer;
import com.training.user.service.model.CustomerProperty;
import com.training.user.service.util.CustomerHelper;
import com.training.user.service.util.HelperFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.ResponseBuilder;

/**
 * Created by dhaya on 13.12.14.
 */
@Stateless
@Path("/customers")
@Produces({ "application/json" })
@Consumes({ "application/json" })
public class CustomerResource extends AbstractResource {

    @EJB
    IUserAccess userAccess;

    @GET
    @Path("/{customerId}")
    public Response getCustomer(@PathParam(value = "customerId") String id){
        CustomerHelper customerHelper = HelperFactory.getCustomerHelper(uriInfo).validateId(id);
        return getOkResponse(customerHelper.CONVERTER.getCustomer(userAccess.getCustomer(id)));
    }

    @GET
    @Path("/filter")
    public Response getCustomerByFilter(@QueryParam(value = "firstname") String firstName,
                                      @QueryParam(value = "lastname") String lastName,
                                      @QueryParam(value = "sex") String sex,
                                      @QueryParam(value = "age-min") Integer ageMin,
                                      @QueryParam(value = "age-max") Integer ageMax,
                                      @QueryParam(value = "limit") Integer limit,
                                      @QueryParam(value = "page") Integer page,
                                      @QueryParam(value = "sort-asc") String ascValue,
                                      @QueryParam(value = "sort-desc") String desValue){
        CustomerAccessFilter filter = CustomerAccessFilter.INSTANCE().setFirstName(firstName)
                .setLastName(lastName).setSex(sex).setMinAge(ageMin).setMaxAge(ageMax);
        if(ascValue != null)
            filter.addAscSortFilter(ascValue);
        if(desValue != null)
            filter.addDescSortFilter(desValue);

        return getOkResponse(HelperFactory.getCustomerHelper(uriInfo).CONVERTER.getCustomer(
                userAccess.getCustomer(filter), page, limit));
    }

    @POST
    public Response createCustomer(Customer customer){
        CustomerHelper customerHelper = HelperFactory.getCustomerHelper(uriInfo).validateCustomer(customer);
        String customerId = userAccess.createCustomer(customerHelper.CONVERTER.getCustomer(customer));
        return getOkResponse(customerHelper.CONVERTER.getCustomer(customerId));
    }

    @POST
    @Path("/{customerId}/customtags/{customTagId}")
    public Response createCustomerAttribute(@PathParam(value = "customerId") String customerId,
                                            @PathParam(value = "customTagId") String customTagId,
                                            CustomerProperty customerProperty){
        CustomerHelper customerHelper = HelperFactory.getCustomerHelper(uriInfo).validateId(customerId, customTagId, customerProperty.getValue());
        String attrId = userAccess.createCustomerAttribute(customerId, customTagId, customerProperty.getValue());
        return getOkResponse(customerHelper.CONVERTER.getCustomerAttribute(customerId,
                customTagId, attrId));
    }

    @PUT
    @Path("/{customerId}")
    public Response updateCustomer(@PathParam(value = "customerId") String id, Customer customer){
        CustomerHelper customerHelper = HelperFactory.getCustomerHelper(uriInfo).validateId(id);
        customer.setId(id);
        userAccess.updateCustomer(customerHelper.CONVERTER.getCustomer(customer));
        return getOkResponse(customerHelper.CONVERTER.getCustomer(id));
    }

    @PUT
    @Path("/{customerId}/customtags/{customTagId}/attribute/{attributeId}")
    public Response updateCustomer(@PathParam(value = "customerId") String customerId,
                                   @PathParam(value = "customTagId") String customTagId,
                                   @PathParam(value = "attributeId") String attributeId,
                                   CustomerProperty customerProperty){
        CustomerHelper customerHelper = HelperFactory.getCustomerHelper(uriInfo).validateId(attributeId);
        userAccess.updateCustomerAttribute(customerHelper.CONVERTER
                .getCustomerAttribute(attributeId, customerProperty));
        return getOkResponse(customerHelper.CONVERTER.getCustomerAttribute(customerId, customTagId, attributeId));
    }

    @DELETE
    @Path("/{customerId}/customtags/{customTagId}/attribute/{attributeId}")
    public Response updateCustomer(@PathParam(value = "customerId") String customerId,
                                   @PathParam(value = "customTagId") String customTagId,
                                   @PathParam(value = "attributeId") String attributeId){
        HelperFactory.getCustomerHelper(uriInfo).validateId(attributeId);
        userAccess.deleteCustomerAttribute(attributeId);
        return getOkResponse(null);
    }

    @DELETE
    @Path("/{customerId}")
    public Response updateCustomer(@PathParam(value = "customerId") String id){
        HelperFactory.getCustomerHelper(uriInfo).validateId(id);
        userAccess.deleteCustomer(id);
        return getOkResponse(null);
    }
}
