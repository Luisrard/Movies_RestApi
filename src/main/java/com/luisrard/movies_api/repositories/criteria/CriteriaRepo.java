package com.luisrard.movies_api.repositories.criteria;

import com.luisrard.movies_api.models.search_criteria.PageProp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;

/**
 * Abstract class to make personalized queries with pagination, sort and filters.
 * @param <T> The entity class
 * @param <X> The search criteria
 */
public abstract class CriteriaRepo<T,X> {
    Logger logger = LoggerFactory.getLogger(CriteriaRepo.class);
    protected final EntityManager entityManager;
    protected final CriteriaBuilder criteriaBuilder;

    protected CriteriaRepo(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    /**
     * Function to make the query and get the data, applying the corresponding parameters
     * @param searchCriteria The filters of the query
     * @param entity The entity class path
     * @param limit The limit of the results, if does not need set null
     * @return The result list
     */
    public List<T> findAllWithFilters(X searchCriteria, Class<T> entity, Integer limit)
    {
        //Initializing the query
        CriteriaQuery<T> criteriaQuery = this.criteriaBuilder.createQuery(entity);
        Root<T> entityRoot = criteriaQuery.from(entity);

        //Adding the filters
        Predicate predicate = getPredicate(searchCriteria, entityRoot);
        criteriaQuery.select(entityRoot).where(predicate);

        if(limit != null){
            return entityManager.createQuery(criteriaQuery).setMaxResults(limit).getResultList();
        }
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    /**
     * Function to make the query and get the data, applying the corresponding parameters
     * @param searchCriteria The filters of the query
     * @param entity The entity class path
     * @param pageProp The properties of the page
     * @return The result in a form of Page
     */
    public Page<T> findAllWithFilters(X searchCriteria, Class<T> entity, PageProp pageProp)
    {
        //Initializing the query
        CriteriaQuery<T> criteriaQuery = this.criteriaBuilder.createQuery(entity);
        Root<T> entityRoot = criteriaQuery.from(entity);

        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<T> countRoot = countQuery.from(entity);

        countQuery.select(criteriaBuilder.count(countRoot)).where( getPredicate(searchCriteria, countRoot));

        //Adding the filters
        Predicate predicate = getPredicate(searchCriteria, entityRoot);
        criteriaQuery.where(predicate);

        //Applying the sort method
        setOrder(pageProp, criteriaQuery, entityRoot);

        //Adding the limits of the query
        TypedQuery<T> typedQuery = this.entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(pageProp.getPage() * pageProp.getSize());
        typedQuery.setMaxResults(pageProp.getSize());

        //Generating the pageable
        Pageable pageable = getPageable(pageProp);

        //Getting the total count
        long totalCount = entityManager.createQuery(countQuery).getSingleResult();
        return new PageImpl<>(typedQuery.getResultList(), pageable, totalCount);
    }

    /**
     * Function to apply the filters in a predicate
     * @param searchCriteria The filters of the query
     * @param entityRoot The entity root
     * @return The predicate of the filters
     */
    protected abstract Predicate getPredicate(X searchCriteria, Root<T> entityRoot);

    /**
     * Function to set the order of the result
     * @param pageProp The page properties
     * @param criteriaQuery The criteria query or in other words the query
     * @param entityRoot The entity root
     */
    protected void setOrder(PageProp pageProp, CriteriaQuery<T> criteriaQuery, Root<T> entityRoot) {
        try {
            if (Objects.nonNull(pageProp)) {
                if (pageProp.getSortDirection().equals(Sort.Direction.ASC)) {
                    criteriaQuery.orderBy(criteriaBuilder.asc(entityRoot.get(pageProp.getSortBy())));
                } else {
                    criteriaQuery.orderBy(criteriaBuilder.desc(entityRoot.get(pageProp.getSortBy())));
                }
            }
        } catch(Exception e){
            logger.error(e.getMessage());
        }
    }

    /**
     * Function to get Pageable properties
     * @param pageProp The page properties
     * @return The pageable object
     */
    protected Pageable getPageable(PageProp pageProp) {
        if(pageProp.getSortBy() != null) {
            Sort sort = Sort.by(pageProp.getSortDirection(), pageProp.getSortBy());
            return PageRequest.of(pageProp.getPage(), pageProp.getSize(), sort);
        }
        else{
            return PageRequest.of(pageProp.getPage(), pageProp.getSize());
        }
    }
}
