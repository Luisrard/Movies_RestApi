package com.luisrard.movies_api.controllers;

import com.luisrard.movies_api.exception.ApiRequestException;
import com.luisrard.movies_api.models.search_criteria.PageProp;
import com.luisrard.movies_api.services.ServiceApi;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Abstract class to manage the request of the clients in a generic form and works as template for a <b>Controller Class</b>,<br>
 * As the <b>GET</b> to get a page, a page with filters and find one object. {@link #getPage(Pageable)}, {@link #getPage(PageProp, Object)}, {@link #findObject(Integer)}.<br>
 * <b>POST</b> to save one object. {@link #saveObject(Object)}.<br>
 * <b>PUT</b> to update one object. {@link #updateObject(Object)}.<br>
 * <b>DELETE</b> to delete one object. {@link #deleteObject(int)}.<br>
 * Also to use those methods and appears in the controllers must to override, add the correct beans and in the constructor must to call the super
 * constructor and initialize the logger e.g.<br>
 * <pre>{@code
 *      //The controller beans
 *      @RestController
 *      @RequestMapping("/example")
 *      public class ExampleController extends ControllerApi<ExampleDto, ExampleCriteria, ExampleService>{
 *          public ExampleController(IServiceApi<ExampleDto, ExampleCriteria> service) {
 *              super(service);
 *              //The initialization of the logger
 *              this.logger = LoggerFactory.getLogger(ExampleController.class);
 *          }
 *          //The get mapping bean
 *          @GetMapping
 *          @Override
 *          public ResponseEntity<Page<ExampleDto>> getPage(PageProp pageProp, ExampleCriteria criteria) {
 *              return super.getPage(pageProp, criteria);
 *          }
 *     }
 * }</pre>
 * @param <D> The Data dto type
 * @param <C> The CriteriaSearch type
 * @param <S> The Service type with the previous types
 * @author Luis
 */
public abstract class ControllerApi<D,C, S extends ServiceApi<D,C>> {
    protected static final String EXC_MSG_INVALID_PARAMS = "Invalid parameters";
    protected static final String EXC_MSG_NOT_SAVED_END = " has not been saved";
    protected static final String EXC_MSG_INVALID_FIELD = "Invalid fields";
    protected static final String EXC_MSG_NOT_DELETED = " has not been deleted";
    protected final S service;
    protected final Logger logger;

    protected ControllerApi(Class<?> controllerClass, S service) {
        this.logger = LoggerFactory.getLogger(controllerClass);
        this.service = service;
    }

    /**
     * Function to get the request of the client in a <b>GET</b> form.<br>
     * This request is to obtain the objects in a page with the filters applied.
     * @param pageProp The page properties
     * @param criteria The search criteria properties
     * @return The page obtained
     */
    public ResponseEntity<Page<D>> getPage(PageProp pageProp, C criteria)
    {
        return ResponseEntity.ok(this.service.getPage(pageProp, criteria));
    }

    /**
     * Function to get the request of the client in a <b>GET</b> form.<br>
     * This request is to obtain the objects in a page.
     * @param pageProp The page properties
     * @return The page obtained
     */
    public ResponseEntity<Page<D>> getPage(Pageable pageProp)    {
        return ResponseEntity.ok(this.service.getPage(pageProp));
    }

    /**
     * Function to get the request of the client in a <b>GET</b> form.<br>
     * This request is to obtain the objects in a page.
     * @param pageProp The page properties
     * @return The page obtained
     */
    public ResponseEntity<Page<D>> getPage(PageProp pageProp)    {
        return ResponseEntity.ok(this.service.getPage(pageProp));
    }
    /**
     * Function to get the request of the client in a <b>GET</b> form.<br>
     * This request is to obtain all the objects in a list.
     * @return The list obtained
     */
    public ResponseEntity<List<D>> getObjects()
    {
        return ResponseEntity.ok(this.service.getObjects());
    }

    /**
     * Function to get the request of the client in a <b>GET</b> form.<br>
     * This request is to obtain all the objects in a list with a criteria field.
     * @return The list obtained
     */
    public ResponseEntity<List<D>> getObjects(C criteria)
    {
        return ResponseEntity.ok(this.service.getObjects(criteria));
    }

    /**
     * Function to obtain one object with a filter.
     * @param criteria the criteria filter
     * @return The object obtained
     */
    public ResponseEntity<D> findOne(C criteria)
    {
        D response = this.service.findOne(criteria);
        if(response == null)
        {
            logger.info(getEntityName(), " ", criteria, " not found...");
            throw new ApiRequestException("No " + getEntityName() + " " + criteria + " exist", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(response);
    }

    /**
     * Function to get the request of the client in a <b>GET</b> form.<br>
     * This request is to obtain the object with their own id.
     * @param id The id of the object
     * @return The object obtained
     */
    public ResponseEntity<D> findObject(Integer id)
    {
        D response;
        try {
            response = this.service.find(id);
        }catch (NullPointerException ex){
            throw new ApiRequestException(EXC_MSG_INVALID_PARAMS, HttpStatus.BAD_REQUEST);
        }


        if(response == null)
        {
            logger.info(getEntityName() , " " , id , " not found...");
            throw new ApiRequestException( getEntityName() + " " + id + " does not exist", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(response);
    }

    /**
     * Function to get the request of the client in a <b>POST</b> form.<br>
     * This request is to <b>save</b> the object in the database.
     * @param requestObject The object to save
     * @return The result and the returned object of the database
     */
    public ResponseEntity<D> saveObject(@RequestBody D requestObject)
    {
        try{
            D response = this.service.save(requestObject);
            if(response == null)
            {
                logger.warn(getEntityName(), " ", requestObject, EXC_MSG_NOT_SAVED_END);
                throw new ApiRequestException(EXC_MSG_INVALID_PARAMS, HttpStatus.BAD_REQUEST);
            }
            return ResponseEntity.ok(response);
        }catch (ApiRequestException e){
            e.printStackTrace();
            logger.warn(getEntityName(), " ", requestObject, EXC_MSG_NOT_SAVED_END);
            throw e;
        }catch (DataIntegrityViolationException e){
            logger.error(e.getMessage());
            logger.warn(getEntityName(), " ", requestObject, EXC_MSG_NOT_SAVED_END);
            if(e.getCause() instanceof ConstraintViolationException){
                ConstraintViolationException cause = (ConstraintViolationException) e.getCause();
                if(cause.getErrorCode() == 1062){
                    throw new ApiRequestException("Duplicate value: This " + getEntityName() + " already exist", HttpStatus.BAD_REQUEST);
                }
            }
            throw new ApiRequestException(EXC_MSG_INVALID_FIELD, HttpStatus.BAD_REQUEST);
        }catch (NullPointerException ex){
            throw new ApiRequestException(EXC_MSG_INVALID_PARAMS, HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e)
        {
            e.printStackTrace();
            logger.error(e.getMessage());
            logger.warn(getEntityName() , " " , requestObject , EXC_MSG_NOT_SAVED_END);
            throw new ApiRequestException(EXC_MSG_INVALID_FIELD, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Function to get the request of the client in a <b>PUT</b> form.<br>
     * This request is to <b>update</b> the object in the database.
     * @param requestObject The object to update
     * @return The result and the returned object of the database
     */
    public ResponseEntity<D> updateObject(@RequestBody D requestObject)
    {
        try{
            D response = this.service.update(requestObject);
            if(response == null) {
                logger.warn(getEntityName() , " " , requestObject , "has not been updated");
                throw new ApiRequestException(EXC_MSG_INVALID_FIELD, HttpStatus.BAD_REQUEST);
            }
            return ResponseEntity.ok(response);
        }catch (ApiRequestException e){
            e.printStackTrace();
            logger.warn(getEntityName() , " " , requestObject , " has not been updated");
            throw e;
        } catch (NullPointerException ex){
            throw new ApiRequestException(EXC_MSG_INVALID_PARAMS, HttpStatus.BAD_REQUEST);
        }
        catch (RuntimeException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            logger.warn(getEntityName() , " " , requestObject , " has not been updated");
            throw new ApiRequestException(EXC_MSG_INVALID_FIELD, HttpStatus.BAD_REQUEST);

        }
    }

    /**
     * Function to get the request of the client in a <b>DELETE</b> form.<br>
     * This request is to <b>delete</b> the object in the database.
     * @param id The id of the obeject to delete
     * @return The result of the request EITHER was deleted or not
     */
    public ResponseEntity<Object> deleteObject(int id)
    {
        try {
            this.service.delete(id);
        } catch (ApiRequestException e){
            logger.warn(getEntityName(), " ", id, EXC_MSG_NOT_DELETED);
            throw e;
        } catch (NullPointerException ex){
            throw new ApiRequestException(EXC_MSG_INVALID_PARAMS, HttpStatus.BAD_REQUEST);
        } catch (EmptyResultDataAccessException e){
            logger.warn(e.getMessage());
            String msg = "The " + getEntityName() + "with id " + id + "does not exist";
            logger.warn(msg);
            logger.warn(getEntityName(), " ", id, EXC_MSG_NOT_DELETED);
            throw new ApiRequestException(msg, HttpStatus.NOT_FOUND);
        } catch (DataIntegrityViolationException e){
            logger.warn(e.getMessage());
            String msg = "The " + getEntityName() + "cannot be delete for existent linked dependencies";
            logger.warn(msg);
            logger.warn(getEntityName(), " ", id, EXC_MSG_NOT_DELETED);
            throw new ApiRequestException(msg, HttpStatus.PRECONDITION_FAILED);
        } catch (RuntimeException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            logger.warn(getEntityName(), " ", id, EXC_MSG_NOT_DELETED);
            throw new ApiRequestException("No "+ getEntityName() + " " + id + " exist", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().build();
    }

    /**
     * Function to get the name of the entity
     * @return The Entity name
     */
    protected abstract String getEntityName();
    /**
     * Function to get the name of the entity in plural form
     * @return The Entity plural name
     */
    protected abstract String getEntityPluralName();
}
