package com.training.user.service.impl;

import com.training.user.model.dao.UserDAO;
import com.training.user.model.dto.CustomTagDTO;
import com.training.user.model.dto.CustomerAttributeDTO;
import com.training.user.model.dto.CustomerDTO;
import com.training.user.model.entity.CustomTagPE;
import com.training.user.model.entity.CustomerAttributePE;
import com.training.user.model.entity.CustomerPE;
import com.training.user.service.CustomerAccessFilter;
import com.training.user.service.IUserAccess;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by dhaya on 13.12.14.
 */
@Stateless
@EJB(name = "java:global/training/user/IUserAccess", beanInterface = IUserAccess.class)
@Remote(IUserAccess.class)
public class UserAccessImpl implements IUserAccess {

    @PersistenceContext(unitName = "USER-PU")
    private EntityManager entityManager;

    @Override
    public CustomerDTO getCustomer(List<Long> attributeId, Long customerId){
        return null;
    }

    @Override
    public List<CustomerDTO> getCustomer(CustomerAccessFilter customerAccessFilter){
        return UserDAO.getInstance(entityManager).getCustomer(customerAccessFilter);
    }

    @Override
    public CustomerDTO getCustomer(String customerId){
        CustomerPE customerPE = UserDAO.getInstance(entityManager).findById(CustomerPE.class, customerId);
        return customerPE != null ? new CustomerDTO(customerPE) : null;
    }

    @Override
    public String createCustomer(CustomerDTO customerDTO){
        UserDAO userDAO = UserDAO.getInstance(entityManager);
        CustomerPE customerPE = new CustomerPE();
        customerPE.setAge(customerDTO.getAge());
        customerPE.setFirstName(customerDTO.getFirstName());
        customerPE.setLastName(customerDTO.getLastName());
        customerPE.setSex(customerDTO.getSex());
        customerPE.setActive(customerDTO.isActive());
        customerPE.setId(userDAO.getSeq());
        customerPE.setCreationTime(Calendar.getInstance());
        UserDAO.getInstance(entityManager).createEntity(customerPE);
        return Long.toString(customerPE.getId());
    }

    @Override
    public String createCustomerAttribute(String customerId, String customTagId, String value) {
        UserDAO userDAO = UserDAO.getInstance(entityManager);
        CustomerPE customerPE = userDAO.findById(CustomerPE.class, customerId);
        customerPE.setModifiedTime(Calendar.getInstance());
        CustomTagPE customTagPE = userDAO.findById(CustomTagPE.class, customTagId);
        CustomerAttributePE customerAttributePE = new CustomerAttributePE();
        customerAttributePE.setCustomer(customerPE);
        customerAttributePE.setCustomTag(customTagPE);
        customerAttributePE.setId(userDAO.getSeq());
        customerAttributePE.setValue(value);
        customerAttributePE.setCreationTime(Calendar.getInstance());
        customerPE.getCustomAttribute().add(customerAttributePE);
        return Long.toString(customerAttributePE.getId());
    }

    @Override
    public void updateCustomer(CustomerDTO customerDTO){
        UserDAO userDAO = UserDAO.getInstance(entityManager);
        CustomerPE customerPE = userDAO.findById(CustomerPE.class, String.valueOf(customerDTO.getId()));
        if(customerDTO.getLastName() != null)
            customerPE.setLastName(customerDTO.getLastName());
        if(customerDTO.getFirstName() != null)
            customerPE.setFirstName(customerDTO.getFirstName());
        if(customerDTO.getSex() != null)
            customerPE.setSex(customerDTO.getSex());
        if(customerDTO.getAge() != null)
            customerPE.setAge(customerDTO.getAge());
        if(customerDTO.isActive() != null)
            customerPE.setActive(customerDTO.isActive());
        customerPE.setModifiedTime(Calendar.getInstance());
        userDAO.updateEntity(customerPE);
    }

    @Override
    public void updateCustomerAttribute(CustomerAttributeDTO customerAttributeDTO) {
        UserDAO userDAO = UserDAO.getInstance(entityManager);
        CustomerAttributePE customerAttributePE = userDAO.findById(CustomerAttributePE.class,
                                                                String.valueOf(customerAttributeDTO.getId()));
        if(customerAttributeDTO.getValue() != null) {
            customerAttributePE.setValue(customerAttributeDTO.getValue());
            customerAttributePE.setModifiedTime(Calendar.getInstance());
        }
        userDAO.updateCustomerAttribute(customerAttributePE);
    }

    @Override
    public void deleteCustomer(String customerId) {
        UserDAO.getInstance(entityManager).removeById(CustomerPE.class, customerId);
    }

    @Override
    public List<CustomTagDTO> getCustomTags(){
        List<CustomTagDTO> customTagDTOs = new ArrayList<CustomTagDTO>();
        for(CustomTagPE customTagPE : UserDAO.getInstance(entityManager).findAll(CustomTagPE.class))
            customTagDTOs.add(new CustomTagDTO(customTagPE));
        return customTagDTOs;
    }

    @Override
    public CustomTagDTO getCustomTag(String customTagId){
        CustomTagPE customTagPE = UserDAO.getInstance(entityManager).findById(CustomTagPE.class, customTagId);
        return customTagPE != null ? new CustomTagDTO(customTagPE) : null;
    }

    @Override
    public void updateCustomTag(CustomTagDTO customTagDTO) {
        UserDAO userDAO = UserDAO.getInstance(entityManager);
        CustomTagPE customTagPE = userDAO.findById(CustomTagPE.class, String.valueOf(customTagDTO.getId()));
        if(customTagDTO.getAllowedAttributeType() != null)
            customTagPE.setAllowedAttributeType(customTagDTO.getAllowedAttributeType());
        if(customTagDTO.getAllowedEntry() != null)
            customTagPE.setAllowedEntry(customTagDTO.getAllowedEntry());
        if(customTagDTO.getName() != null)
            customTagPE.setName(customTagDTO.getName());
        if(customTagDTO.getValidation() != null)
            customTagPE.setValidation(customTagDTO.getValidation());
        customTagPE.setModifiedTime(Calendar.getInstance());
        userDAO.updateEntity(customTagPE);
    }

    @Override
    public String createCustomTag(CustomTagDTO customTagDTO){
        UserDAO userDAO = UserDAO.getInstance(entityManager);
        CustomTagPE customTagPE = new CustomTagPE();
        customTagPE.setId(userDAO.getSeq());
        customTagPE.setName(customTagDTO.getName());
        customTagPE.setValidation(customTagDTO.getValidation());
        customTagPE.setCreationTime(Calendar.getInstance());
        customTagPE.setValidationValue(customTagDTO.getValidationValue());
        customTagPE.setAllowedEntry(customTagDTO.getAllowedEntry());
        customTagPE.setAllowedAttributeType(customTagDTO.getAllowedAttributeType());
        userDAO.createEntity(customTagPE);
        return Long.toString(customTagPE.getId());
    }

    @Override
    public void deleteCustomTag(String id){
        UserDAO.getInstance(entityManager).removeById(CustomTagPE.class, id);
    }

    @Override
    public void deleteCustomerAttribute(String attributeId){
        UserDAO.getInstance(entityManager).removeById(CustomerAttributePE.class, attributeId);
    }

}
