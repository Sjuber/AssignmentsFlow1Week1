package facades;

import entities.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class EmployeeFacade {

    private static EmployeeFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    public EmployeeFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static EmployeeFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }



    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


    public Employee getById(long id) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Employee> q = em.createQuery("SELECT e FROM Employee e WHERE e.id ='" + id + "'", Employee.class);
        Employee emp = q.getSingleResult();
        return emp;
    }

    public Employee getByName(String name) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Employee> q = em.createQuery("SELECT e FROM Employee e WHERE e.name ='" + name + "'", Employee.class);
        Employee emp = q.getSingleResult();
        return emp;
    }

    public List<Employee> getAll() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Employee> q = em.createQuery("SELECT e FROM Employee e", Employee.class);
        List<Employee> eli = q.getResultList();
        return eli;
    }
    
    public Employee getRichest(){
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT e FROM Employee e WHERE e.salary = (SELECT MAX(e.salary) FROM Employee e)");
        return (Employee) q.getSingleResult();
    }

    public Employee create(String name, String address, int salary) {
        Employee emp = new Employee(name, address, salary);
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(emp);
            em.getTransaction().commit();
            return emp;
        } finally {
            em.close();
        }

    }

    //TODO Remove/Change this before use
//        TypedQuery<RenameMe> query = em.createQuery("SELECT r FROM RenameMe r", RenameMe.class);
//        List<RenameMe> rms = query.getResultList();
    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        EmployeeFacade fe = getFacadeExample(emf);
        fe.create("John", "Wilcan Steet 4", 41232);
        fe.create("Farquad", "Fantasy Steet 4", 99382);
        fe.create("Shrek", "Swamptown", 45);
        fe.create("Spazmaticus", "The World", 666777);
        System.out.println(fe.getById(4).toString());
        System.out.println(fe.getByName("Shrek").toString());
        System.out.println(fe.getAll().toString());
        System.out.println(fe.getRichest());
    }

}
