package com.training.user.service.util;

import com.training.user.model.dto.CustomTagDTO;
import com.training.user.service.model.CustomTag;

import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;

import static com.training.user.service.util.LinkerHelper.link;

/**
 * Created by dhaya on 13.12.14.
 */
public class CustomTagHelper extends AbstractHelper {

    public Converter CONVERTER = new Converter();

    public CustomTagHelper(UriInfo uriInfo){
        super(uriInfo);
    }

    public CustomTagHelper validateId(String id){
        if(id == null)
            throw new RestServiceException("custom tag id Parameter is null");
        return this;
    }

    public class Converter {

        public CustomTag getCustomTag(String id){
            CustomTag customTag = new CustomTag();
            customTag.setId(id);
            return link(uriInfo,customTag);
        }

        public List<CustomTag> getCustomTags(List<CustomTagDTO> customTagDTOs){
            List<CustomTag> customTags = new ArrayList<CustomTag>();
            for(CustomTagDTO customTagDTO : customTagDTOs)
                customTags.add(link(uriInfo,getCustomTag(customTagDTO)));
            return customTags;
        }

        public CustomTag getCustomTag(CustomTagDTO customTagDTO){
            CustomTag customTag = new CustomTag();
            customTag.setId(String.valueOf(customTagDTO.getId()));
            customTag.setName(customTagDTO.getName());
            customTag.setAllowedEntry(customTagDTO.getAllowedEntry());
            customTag.setValidation(customTagDTO.getValidation());
            customTag.setValidationValue(customTagDTO.getValidationValue());
            return link(uriInfo,customTag);
        }

        public CustomTagDTO getCustomTag(CustomTag customTag){
            CustomTagDTO customTagDTO = new CustomTagDTO();
            customTagDTO.setId(customTag.getId() != null ? Long.parseLong(customTag.getId()) : null);
            customTagDTO.setName(customTag.getName());
            customTagDTO.setAllowedEntry(customTag.getAllowedEntry());
            customTagDTO.setValidation(customTag.getValidation());
            customTagDTO.setValidationValue(customTag.getValidationValue());
            return customTagDTO;
        }
    }

}
