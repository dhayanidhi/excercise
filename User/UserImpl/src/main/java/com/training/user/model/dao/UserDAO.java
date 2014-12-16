package com.training.user.model.dao;

import com.training.user.model.dto.CustomerDTO;
import com.training.user.model.entity.AbstractPE;
import com.training.user.model.entity.CustomerAttributePE;
import com.training.user.model.entity.CustomerPE;
import com.training.user.service.CustomerAccessFilter;
import com.training.user.service.SortFilter;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by dhaya on 13.12.14.
 */
public class UserDAO extends AbstractDAO {

    private static final Logger LOG = Logger.getLogger(UserDAO.class.getName());

    private UserDAO(EntityManager entityManager){
        super(entityManager);
    }

    public static UserDAO getInstance(EntityManager entityManager){return new UserDAO(entityManager);}

    public List<CustomerDTO> getCustomer(CustomerAccessFilter customerAccessFilter){

        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<CustomerDTO> criteriaQuery = criteriaBuilder.createQuery(CustomerDTO.class);
        Metamodel metamodel = getEntityManager().getMetamodel();
        EntityType<CustomerPE> entityType = metamodel.entity(CustomerPE.class);
        Root<CustomerPE> root = criteriaQuery.from(entityType);

        List<Selection<?>> selections = new ArrayList<Selection<?>>();
        selections.add(root.get(CustomerPE.idMeta));
        selections.add(root.get(CustomerPE.firstNameMeta));
        selections.add(root.get(CustomerPE.lastNameMeta));
        selections.add(root.get(CustomerPE.sexMeta));

        criteriaQuery.select(criteriaBuilder.construct(CustomerDTO.class,
                selections.toArray(new Selection[0])));

        List<Predicate> predicates = new ArrayList<Predicate>();
        if (customerAccessFilter.getFirstName() != null) {
            predicates.add(criteriaBuilder.equal(root.get(CustomerPE.firstNameMeta),
                    customerAccessFilter.getFirstName()));
        }
        if (customerAccessFilter.getLastName() != null) {
            predicates.add(criteriaBuilder.equal(root.get(CustomerPE.lastNameMeta),
                    customerAccessFilter.getLastName()));
        }
        if(customerAccessFilter.getMaxAge() > 0 & customerAccessFilter.getMinAge() > 0){
            predicates.add(criteriaBuilder.between(root.<Integer>get(CustomerPE.ageMeta),
                    customerAccessFilter.getMinAge(), customerAccessFilter.getMaxAge()));
        }
        if(customerAccessFilter.getSex() != null) {
            predicates.add(criteriaBuilder.equal(root.get(CustomerPE.sexMeta),
                    customerAccessFilter.getSex()));
        }
        if(!predicates.isEmpty())
            criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));

        List<Order> orders = new ArrayList<Order>();
        for(SortFilter sortFilter : customerAccessFilter.getSortFilter()){
            if (sortFilter.isAsc())
                orders.add(criteriaBuilder.asc(root.get(sortFilter.getType().getValue())));
            else
                orders.add(criteriaBuilder.desc(root.get(sortFilter.getType().getValue())));
        }
        if(orders.isEmpty())
            orders.add(criteriaBuilder.desc(root.get(CustomerPE.idMeta)));
        criteriaQuery.orderBy(orders);

        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }

    public <T extends AbstractPE> void createEntity(T entity){
        insertAndFlush(entity);
    }

    public <T extends AbstractPE> void updateEntity(T entity){
        getEntityManager().merge(entity);
    }

    public void updateCustomerAttribute(CustomerAttributePE customerAttributePE){
        getEntityManager().merge(customerAttributePE);
    }

    public long getSeq(){
        Long seq = null;
        try {
            Object sequence = getEntityManager().createNativeQuery("select nextval('id_sequence')").getSingleResult();

            if (sequence instanceof Long) {
                seq = ((Long) sequence);
            } else {
                LOG.log(Level.SEVERE, "Unable to retrieve a sequence number.");
            }
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Unable to retrieve a sequence number.", e);
        }
        return seq;
    }
}
