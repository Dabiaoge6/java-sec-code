package rmi;



import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.List;



/**
 * Interface for StusDAO.
 *
 * @author MyEclipse Persistence Tools
 */

public interface IStusDAO extends Remote{
    /**
     * Perform an initial save of a previously unsaved Stus entity. All
     * subsequent persist actions of this entity should use the #update()
     * method. This operation must be performed within the a database
     * transaction context for the entity's data to be permanently saved to the
     * persistence store, i.e., database. This method uses the
     * {@link javax.persistence.EntityManager#persist(Object)
     * EntityManager#persist} operation.
     *
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * IStusDAO.save(entity);
     * EntityManagerHelper.commit();
     * </pre>
     *
     * @param entity
     *            Stus entity to persist
     * @throws RuntimeException
     *             when the operation fails
     */
    public void save(Stus entity) throws RemoteException;

    /**
     * Delete a persistent Stus entity. This operation must be performed within
     * the a database transaction context for the entity's data to be
     * permanently deleted from the persistence store, i.e., database. This
     * method uses the {@link javax.persistence.EntityManager#remove(Object)
     * EntityManager#delete} operation.
     *
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * IStusDAO.delete(entity);
     * EntityManagerHelper.commit();
     * entity = null;
     * </pre>
     *
     * @param entity
     *            Stus entity to delete
     * @throws RuntimeException
     *             when the operation fails
     */
    public void delete(Stus entity) throws RemoteException;

    /**
     * Persist a previously saved Stus entity and return it or a copy of it to
     * the sender. A copy of the Stus entity parameter is returned when the JPA
     * persistence mechanism has not previously been tracking the updated
     * entity. This operation must be performed within the a database
     * transaction context for the entity's data to be permanently saved to the
     * persistence store, i.e., database. This method uses the
     * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
     * operation.
     *
     * <pre>
     * EntityManagerHelper.beginTransaction();
     * entity = IStusDAO.update(entity);
     * EntityManagerHelper.commit();
     * </pre>
     *
     * @param entity
     *            Stus entity to update
     * @return Stus the persisted Stus entity instance, may not be the same
     * @throws RuntimeException
     *             if the operation fails
     */
    public Stus update(Stus entity) throws RemoteException;

    public Stus findById(String id) throws RemoteException;

    /**
     * Find all Stus entities with a specific property value.
     *
     * @param propertyName
     *            the name of the Stus property to query
     * @param value
     *            the property value to match
     * @param rowStartIdxAndCount
     *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
     *            row index in the query result-set to begin collecting the
     *            results. rowStartIdxAndCount[1] specifies the the maximum
     *            count of results to return.
     * @return List<Stus> found by query
     */
    public List<Stus> findByProperty(String propertyName, Object value,
                                     int... rowStartIdxAndCount) throws RemoteException;

    public List<Stus> findByStuName(Object stuName, int... rowStartIdxAndCount) throws RemoteException;

    public List<Stus> findByStuAge(Object stuAge, int... rowStartIdxAndCount) throws RemoteException;

    /**
     * Find all Stus entities.
     *
     * @param rowStartIdxAndCount
     *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
     *            row index in the query result-set to begin collecting the
     *            results. rowStartIdxAndCount[1] specifies the the maximum
     *            count of results to return.
     * @return List<Stus> all Stus entities
     */
    public List<Stus> findAll(int... rowStartIdxAndCount) throws RemoteException;
}