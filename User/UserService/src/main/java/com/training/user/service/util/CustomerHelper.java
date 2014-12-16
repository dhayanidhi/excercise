package com.training.user.service.util;

import com.training.user.model.dto.CustomTagDTO;
import com.training.user.model.dto.CustomerAttributeDTO;
import com.training.user.model.dto.CustomerDTO;
import com.training.user.service.model.Customer;
import com.training.user.service.model.CustomerProperty;
import com.training.user.service.model.Customers;

import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;

import static com.training.user.service.util.LinkerHelper.link;

/**
 * Created by dhaya on 13.12.14.
 */
public class CustomerHelper extends AbstractHelper {

    public Converter CONVERTER = new Converter();

    public CustomerHelper(UriInfo uriInfo){
        super(uriInfo);
    }

    public CustomerHelper validateId(String...ids){
        for(String id : ids) {
            if (id == null)
                throw new RestServiceException("customer id Parameter is null");
        }
        return this;
    }

    public CustomerHelper validateCustomer(Customer customer){
        if(customer.getFirstName() == null || customer.getLastName() == null)
            throw new RestServiceException("customer first name or last name Parameter is null");
        return this;
    }

    public class Converter {

        public Customers getCustomer(List<CustomerDTO> customerDTOs, Integer page, Integer limit){
            Customers customers = new Customers();
            if((page == null) & (limit != null))
                page = new Integer(1);

            if(page != null && limit != null){
                int skip = (page * limit) - limit;
                customers.setCurrentHref(uriInfo,page,skip,limit);
                customers.setNextHref(uriInfo,page,skip,limit,customerDTOs.size());
                customers.setPreviousHref(uriInfo,page,skip,limit,customerDTOs.size());
                int max = skip + limit;
                fillCustomer(customerDTOs, customers, skip, max);
            }else{
                fillCustomer(customerDTOs, customers);
            }
            return customers;
        }

        private void fillCustomer(List<CustomerDTO> customerDTOs, Customers customers){
            List<Customer> customerList = new ArrayList<Customer>();
            for(CustomerDTO customerDTO : customerDTOs) {
                Customer customer = new Customer();
                customer.setId(String.valueOf(customerDTO.getId()));
                customer.setFirstName(customerDTO.getFirstName());
                customer.setLastName(customerDTO.getLastName());
                customer.setSex(customerDTO.getSex());
                customerList.add(link(uriInfo,customer));
            }
            customers.setCustomers(customerList);
        }

        private void fillCustomer(List<CustomerDTO> customerDTOs, Customers customers,int start, int max){
            int count = -1;
            List<Customer> customerList = new ArrayList<Customer>();
            for(CustomerDTO customerDTO : customerDTOs) {
                count++;
                if(count < start)
                    continue;
                if(count >= max)
                    break;
                Customer customer = new Customer();
                customer.setId(String.valueOf(customerDTO.getId()));
                customer.setFirstName(customerDTO.getFirstName());
                customer.setLastName(customerDTO.getLastName());
                customer.setSex(customerDTO.getSex());
                customerList.add(link(uriInfo,customer));
            }
            customers.setCustomers(customerList);
        }

        public Customer getCustomer(CustomerDTO customerDTO){
            Customer customer = new Customer();
            customer.setId(String.valueOf(customerDTO.getId()));
            customer.setActive(customerDTO.isActive());
            customer.setAge(customerDTO.getAge());
            customer.setFirstName(customerDTO.getFirstName());
            customer.setLastName(customerDTO.getLastName());
            customer.setSex(customerDTO.getSex());
            for(CustomerAttributeDTO customerAttributeDTO : customerDTO.getCustomAttribute()){
                CustomerProperty customerProperty = new CustomerProperty();
                customerProperty.setName(customerAttributeDTO.getCustomTag().getName());
                customerProperty.setCustomerId(String.valueOf(customerAttributeDTO.getCustomer().getId()));
                customerProperty.setCustomTagId(String.valueOf(customerAttributeDTO.getCustomTag().getId()));
                customerProperty.setValue(customerAttributeDTO.getValue());
                customerProperty.setId(String.valueOf(customerAttributeDTO.getId()));
                customer.addCustomerProperty(link(uriInfo,customerProperty));
            }
            return link(uriInfo,customer);
        }

        public CustomerAttributeDTO getCustomerAttribute(String attrId, CustomerProperty customerProperty){
            CustomerAttributeDTO customerAttributeDTO = new CustomerAttributeDTO();
            customerAttributeDTO.setId(attrId != null ? Long.parseLong(attrId) : null);
            customerAttributeDTO.setValue(customerProperty.getValue());
            return customerAttributeDTO;
        }

        public Customer getCustomer(String customerId){
            Customer customer = new Customer();
            customer.setId(customerId);
            return link(uriInfo,customer);
        }

        public CustomerProperty getCustomerAttribute(String customerId, String customTagId, String attrId){
            CustomerProperty customerProperty = new CustomerProperty();
            customerProperty.setCustomerId(customerId);
            customerProperty.setCustomTagId(customTagId);
            customerProperty.setId(attrId);
            return link(uriInfo,customerProperty);
        }

        public CustomerDTO getCustomer(Customer customer){
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setId(customer.getId() != null ? Long.parseLong(customer.getId()) : null);
            customerDTO.setActive(customer.isActive());
            customerDTO.setAge(customer.getAge());
            customerDTO.setFirstName(customer.getFirstName());
            customerDTO.setLastName(customer.getLastName());
            customerDTO.setSex(customer.getSex());
            return customerDTO;
        }
    }
}
