package com.training.user.model.dao;

import com.training.user.model.entity.AbstractPE;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dhaya on 15.11.14.
 */
public class AbstractDAO {

    EntityManager entityManager;

    public AbstractDAO(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    protected EntityManager getEntityManager() {return entityManager;}

    protected <T extends Object> void insertAndFlush(T entity){
        entityManager.persist(entity);
        entityManager.flush();
    }

    protected <T extends Object> void update(T entity){
        entityManager.merge(entity);
    }

    public <T extends AbstractPE> T findById(Class<T> clazz, String uuid) {
        Map<String, Object> loadingProp = new HashMap<String, Object>();
        loadingProp.put("javax.persistence.cache.storeMode", "REFRESH");
        return getEntityManager().find(clazz, Long.valueOf(uuid), loadingProp);
    }

    public <T extends AbstractPE> List<T> findAll(Class<T> clazz) {
        TypedQuery<T> query = entityManager.createQuery("SELECT entity FROM " + clazz.getSimpleName() + " entity", clazz);
        List<T> result = query.getResultList();
        return result != null ? result : new ArrayList<T>();
    }

    public <T extends AbstractPE> void removeById(Class<T> clazz, String uuid) {
        getEntityManager().remove(findById(clazz, uuid));
    }
}
