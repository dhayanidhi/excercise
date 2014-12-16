package com.training.user.model.api;

import java.util.List;

/**
 * Created by dhaya on 13.12.14.
 */
public interface ICustomer<E extends ICustomerAttribute> extends IAbstract {

    void setFirstName(String firstName);

    String getFirstName();

    void setLastName(String lastName);

    String getLastName();

    void setActive(Boolean active);

    Boolean isActive();

    void setAge(Integer age);

    Integer getAge();

    void setSex(Sex sex);

    Sex getSex();

    void setCustomAttribute(List<E> customAttribute);

    void addCustomAttribute(E customAttribute);

    List<E> getCustomAttribute();

}
