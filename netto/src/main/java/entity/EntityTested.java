package entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
/**
 *
 * @author SJUBE
 */
public class EntityTested {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        Customer cust1 = new Customer("John", "Johnson");
        Customer cust2 = new Customer("Marie", "Carrie");
        Customer cust3 = new Customer("Caster", "Cantripsen");
        try{
            em.getTransaction().begin();
            em.persist(cust1);
            em.persist(cust2);
            em.persist(cust3);
            em.getTransaction().commit();
        } finally{
            em.close();
        }
        
    }
}
