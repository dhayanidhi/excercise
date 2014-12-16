package com.training.user.model.api;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by dhaya on 13.12.14.
 */
public interface IAbstract extends Serializable{

    Long getId();

    void setId(Long id);

    int getVersion();

    Calendar getCreationTime();

    void setCreationTime(Calendar creationTime);

    Calendar getModifiedTime();

    void setModifiedTime(Calendar modifiedTime);
}
