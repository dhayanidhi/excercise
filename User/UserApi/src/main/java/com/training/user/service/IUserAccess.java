package com.training.user.service;

import com.training.user.model.api.ICustomTag;
import com.training.user.model.api.ICustomer;
import com.training.user.model.dto.CustomTagDTO;
import com.training.user.model.dto.CustomerAttributeDTO;
import com.training.user.model.dto.CustomerDTO;

import java.util.List;

/**
 * Created by dhaya on 13.12.14.
 */
public interface IUserAccess {

    CustomerDTO getCustomer(List<Long> attributeId, Long customerId);

    List<CustomerDTO> getCustomer(CustomerAccessFilter customerAccessFilter);

    CustomerDTO getCustomer(String customerId);

    String createCustomer(CustomerDTO customerDTO);

    String createCustomerAttribute(String customerId, String customTagId, String value);

    void updateCustomer(CustomerDTO customerDTO);

    void updateCustomerAttribute(CustomerAttributeDTO customerAttributeDTO);

    void deleteCustomer(String customerId);

    void deleteCustomerAttribute(String attributeId);

    CustomTagDTO getCustomTag(String customTagId);

    void updateCustomTag(CustomTagDTO customTagDTO);

    String createCustomTag(CustomTagDTO customTagDTO);

    void deleteCustomTag(String id);

    List<CustomTagDTO> getCustomTags();
}
