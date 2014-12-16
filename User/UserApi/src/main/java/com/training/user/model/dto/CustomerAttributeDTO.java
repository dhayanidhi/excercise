package com.training.user.model.dto;

import com.training.user.model.api.ICustomerAttribute;

/**
 * Created by dhaya on 13.12.14.
 */
public class CustomerAttributeDTO extends AbstractDTO implements ICustomerAttribute<CustomerDTO, CustomTagDTO> {

    private CustomerDTO customerDTO;

    private CustomTagDTO customTagDTO;

    private String value;

    public CustomerAttributeDTO(CustomerDTO customerDTO, CustomTagDTO customTagDTO, String value, Long id){
        super(id);
        this.customerDTO = customerDTO;
        this.customTagDTO = customTagDTO;
        this.value = value;
    }

    public CustomerAttributeDTO(){
        super();
    }

    @Override
    public CustomerDTO getCustomer() {
        return customerDTO;
    }

    @Override
    public void setCustomer(CustomerDTO customer) {
        this.customerDTO = customer;
    }

    @Override
    public CustomTagDTO getCustomTag() {
        return customTagDTO;
    }

    @Override
    public void setCustomTag(CustomTagDTO customTag) {
        this.customTagDTO = customTag;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }
}
