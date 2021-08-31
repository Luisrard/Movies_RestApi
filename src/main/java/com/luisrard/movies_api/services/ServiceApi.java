package com.luisrard.movies_api.services;

import com.luisrard.movies_api.models.search_criteria.PageProp;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;

/**
 * Interface to make the CRUD services of the entity.<br>
 * As the <b>getPage</b> to obtain a page. {@link #getPage(Pageable)}, {@link #getPage(PageProp, Object)}, {@link #getObjects()}<br>
 * <b>find</b> to find the object. {@link #find(Integer)}<br>
 * <b>save</b> to save the object. {@link #save(Object)}<br>
 * <b>update</b> to update the object. {@link #update(Object)}<br>
 * <b>delete</b> to delete the object. {@link #delete(Integer)}<br>
 * @param <D> The Data dto type
 * @param <C> The CriteriaSearch type
 * @author Luisrard
 */
public interface ServiceApi<D,C> {
    /**
     * Function to <b>get</b> the objects in a <b>page</b> in the database
     * @param pageProp The details of the page
     * @param criteria The details of the filter criteria
     * @return The Page of objects
     */
    default Page<D> getPage(PageProp pageProp, C criteria){
        return null;
    }

    /**
     * Function to <b>get</b> the objects in a <b>page</b> in the database
     * @param pageable The details of the page
     * @param criteria The details of the filter criteria
     * @return The Page of objects
     */
    default Page<D> getPage(Pageable pageable, C criteria){
        return null;
    }

    /**
     * Function to <b>get</b> the objects in a <b>page</b> in the database
     * @param pageable The details of the page
     * @return The Page of objects
     */
    default Page<D> getPage(Pageable pageable){
        return null;
    }

    /**
     * Function to <b>get</b> the objects in a <b>page</b> in the database
     * @param pageable The details of the page
     * @return The Page of objects
     */
    default Page<D> getPage(PageProp pageable){
        return null;
    }
    /**
     * Function to <b>get</b> the all the objects in a list in the database
     * @return The list of the objects
     */
    default List<D> getObjects(){return Collections.emptyList();}

    /**
     * Function to <b>get</b> the all the objects in a list in the database, whit a filter
     * @param criteria the filters of the search.
     * @return The list of the objects
     */
    default List<D> getObjects(C criteria){return Collections.emptyList();}

    /**
     * Function to find one object or the first object found with a criteria filter
     * @param criteria the criteria
     * @return the object found
     */
    default D findOne(C criteria){return null;}
    /**
     * Function to <b>find</b> the object by their id in the database
     * @param id Id of the object to search
     * @return The object result or {@code null} if not found
     */
    D find(@NonNull Integer id);

    /**
     * Function to <b>save</b> the object in the database
     * @param request The object to save
     * @return The object saved or {@code null} if happened an error
     */
    D save(@NonNull D request)  throws RuntimeException;

    /**
     * Function to <b>update</b> the object in the database
     * @param request The object to update
     * @return The object saved or {@code null} if happened an error
     */
    D update(@NonNull  D request)  throws RuntimeException;

    /**
     * Function to <b>delete</b> the product in the database
     * @param id The id of the object to delete
     * @return The result of the request EITHER was deleted {@code true} or not {@code false}
     */
    boolean delete(@NonNull Integer id) throws RuntimeException;
}
