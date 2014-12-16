package com.training.user.service.util;

import javax.ws.rs.core.UriInfo;

/**
 * Created by dhaya on 13.12.14.
 */
public class HelperFactory {

    public static CustomerHelper getCustomerHelper(UriInfo uriInfo){ return new CustomerHelper(uriInfo);}

    public static CustomTagHelper getCustomTagHelper(UriInfo uriInfo){ return new CustomTagHelper(uriInfo);}
}
