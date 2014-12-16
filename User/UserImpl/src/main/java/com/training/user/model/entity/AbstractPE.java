package com.training.user.model.entity;

import com.training.user.model.api.IAbstract;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Created by dhaya on 13.12.14.
 */
@MappedSuperclass
public class AbstractPE implements IAbstract {

    public static String idMeta = "id";

    @Id
    @Column(name = "c_id")
    protected Long id;

    @Version
    @Column(name = "c_version")
    private int version;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "c_creation_time")
    private Calendar creationTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "c_modified_time")
    private Calendar modifiedTime;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id){ this.id = id;}

    @Override
    public int getVersion() {
        return version;
    }

    @Override
    public Calendar getCreationTime() {
        return creationTime;
    }

    @Override
    public void setCreationTime(Calendar creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public Calendar getModifiedTime() {
        return modifiedTime;
    }

    @Override
    public void setModifiedTime(Calendar modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractPE)) return false;

        AbstractPE that = (AbstractPE) o;

        if (creationTime != null ? !creationTime.equals(that.creationTime) : that.creationTime != null) return false;
        if (!id.equals(that.id)) return false;
        if (modifiedTime != null ? !modifiedTime.equals(that.modifiedTime) : that.modifiedTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (creationTime != null ? creationTime.hashCode() : 0);
        result = 31 * result + (modifiedTime != null ? modifiedTime.hashCode() : 0);
        return result;
    }
}
