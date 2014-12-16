package com.training.user.model.dto;

import com.training.user.model.api.IAbstract;

import java.util.Calendar;

/**
 * Created by dhaya on 13.12.14.
 */
public class AbstractDTO implements IAbstract {

    private Long id;

    public AbstractDTO(){}

    public AbstractDTO(long id){
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id){
        this.id = id;
    }

    @Override
    public int getVersion() {
        return 0;
    }

    @Override
    public Calendar getCreationTime() {
        return null;
    }

    @Override
    public void setCreationTime(Calendar creationTime) {

    }

    @Override
    public Calendar getModifiedTime() {
        return null;
    }

    @Override
    public void setModifiedTime(Calendar modifiedTime) {

    }
}
