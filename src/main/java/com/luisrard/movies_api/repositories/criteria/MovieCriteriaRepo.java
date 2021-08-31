package com.luisrard.movies_api.repositories.criteria;

import com.luisrard.movies_api.entities.Movie;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository to make custom searches on the entity {@link Movie}.
 */
@Repository
public class MovieCriteriaRepo extends CriteriaRepo<Movie, MovieCriteriaRepo>{

    public MovieCriteriaRepo(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    protected Predicate getPredicate(MovieCriteriaRepo searchCriteria, Root<Movie> entityRoot) {
        List<Predicate> predicates = new ArrayList<>();
        return  criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
