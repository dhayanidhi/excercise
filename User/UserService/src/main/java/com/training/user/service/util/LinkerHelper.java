package com.training.user.service.util;

import com.sun.jersey.server.linking.impl.RefProcessor;
import com.training.user.service.model.AbstractModel;

import javax.ws.rs.core.UriInfo;

/**
 * Created by dhaya on 14.12.14.
 */
public class LinkerHelper {

    public static <T extends AbstractModel>  T link(UriInfo uriInfo, T model){
        RefProcessor processor = new RefProcessor(model.getClass());
        processor.processLinks(model, uriInfo);
        return model;
    }
}
