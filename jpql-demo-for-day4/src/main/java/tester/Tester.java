    package tester;

import entity.Employee;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class Tester {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new Employee("xa12tt", "Kurt", "Wonnegut", new BigDecimal(335567)));
            em.persist(new Employee("hyu654", "Hanne", "Olsen", new BigDecimal(435867)));
            em.persist(new Employee("uio876", "Jan", "Olsen", new BigDecimal(411567)));
            em.persist(new Employee("klo999", "Irene", "Petersen", new BigDecimal(33567)));
            em.persist(new Employee("jik666", "Tian", "Wonnegut", new BigDecimal(56567)));
            em.persist(new Employee("dsn282", "Danny", "Sexbanga", new BigDecimal(124558)));
            em.getTransaction().commit();

            //Complete all these small tasks. Your will find the solution to all, but the last,
            //In this document: https://en.wikibooks.org/wiki/Java_Persistence/JPQL#JPQL_supported_functions
            //1) Create a query to fetch all employees with a salary > 100000 and print out all the salaries
            TypedQuery<Employee> q1 = em.createQuery("SELECT e FROM Employee e WHERE e.salary > 100000", Employee.class);
            System.out.println(q1.getResultList());
            //2) Create a query to fetch the employee with the id "klo999" and print out the firstname
            String idee = "klo999";
            Query q2 = em.createQuery("SELECT e FROM Employee e WHERE e.id = :id");
            q2.setParameter("id", idee);
            System.out.println(q2.getSingleResult());
            //3) Create a query to fetch the highest salary and print the value
            Query q3 = em.createQuery("SELECT MAX(e.salary) FROM Employee e");
            System.out.println(q3.getSingleResult());
            //4) Create a query to fetch the firstName of all Employees and print the names
            Query q4 = em.createQuery("Select e.firstName FROM Employee e");
            System.out.println(q4.getResultList());
            //4) Create a query to fetch the firstName of all Employees and print the names
            Query q4half = em.createQuery("Select e.salary FROM Employee e");
            System.out.println(q4half.getResultList());
            //5 Create a query to calculate the number of employees and print the number
            Query q5 = em.createQuery("Select COUNT(e) FROM Employee e");
            System.out.println(q5.getSingleResult());
            //6 Create a query to fetch the Employee with the higest salary and print all his details
            Query q6 = em.createQuery("SELECT e FROM Employee e WHERE e.salary = (SELECT MAX(e.salary) FROM Employee e)");
            System.out.println(q6.getSingleResult());
        } finally {
            em.close();
            emf.close();
        }
    }

}
