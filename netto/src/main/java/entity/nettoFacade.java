/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author SJUBE
 */
public class nettoFacade {

    private static EntityManagerFactory emf;
    private static nettoFacade instance;

    
    public static void main(String[] args) {
  
        nettoFacade facade = nettoFacade.getNettoFacade(Persistence.createEntityManagerFactory("pu"));
        Customer c1 = facade.addCustomer("First 1", "Last one");
        Customer c2 = facade.addCustomer("First 2", "Last two");
        Customer c3 = facade.addCustomer("First 3", "Last three");
        Customer c4 = facade.addCustomer("First 4", "Last four");
  
        System.out.println("Customer 1: " + facade.findByID(c1.getId()));
System.out.println("Customer 2: " + facade.findByLastName(c2.getLastName()));

        System.out.println("All customers: " + facade.getAllCustomers());
        System.out.println("Number of customers: " + facade.getNumberOfCustomers());
        
    }
    
    private nettoFacade() {
    }

    public static nettoFacade getNettoFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new nettoFacade();
        }
        return instance;
    }

//Customer addCustomer(String fName, String lName);
    public Customer addCustomer(String fName, String lName) {
        Customer cust = new Customer(fName, lName);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cust);
            em.getTransaction().commit();
            return cust;
        } finally {
            em.close();
        }
    }

//    Customer findByID(int id);
    public Customer findByID(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Customer cust = em.find(Customer.class, id);
            return cust;
        } finally {
            em.close();
        }
    }
        public Customer findByLastName(String lName) {  
        EntityManager em = emf.createEntityManager();
        TypedQuery<Customer> q1 = em.createQuery("SELECT c FROM Customer c WHERE c.lastName = '" + lName + "'", Customer.class);
        try {
Customer cust = (Customer) q1.getSingleResult();
            return cust;
        } finally {
            em.close();
        }
    }

    //List<Customer> allCustomers();
    public List<Customer> getAllCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query
                    = em.createQuery("Select cust from Customer cust", Customer.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
//List<Customer> findByLastName(String name);



//int getNumberOfCustomers();
    public Long getNumberOfCustomers() {
        EntityManager em = emf.createEntityManager();
        Query q1 = em.createQuery("SELECT COUNT(c) FROM Customer c");
        return (Long) q1.getSingleResult();
    }

    
}
