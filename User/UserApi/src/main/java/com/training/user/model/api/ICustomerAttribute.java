package com.training.user.model.api;

/**
 * Created by dhaya on 13.12.14.
 */
public interface ICustomerAttribute<C extends ICustomer, T extends ICustomTag> extends IAbstract {

    C getCustomer();

    void setCustomer(C customer);

    T getCustomTag();

    void setCustomTag(T customTag);

    String getValue();

    void setValue(String value);
}
