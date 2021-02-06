package facades;

import utils.EMF_Creator;
import entities.Employee;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class EmployeeFacadetest {

    private static EntityManagerFactory emf;
    private static EmployeeFacade facade;

    public EmployeeFacadetest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = EmployeeFacade.getFacadeExample(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Employee.deleteAllRows").executeUpdate();
            em.persist(new Employee("Jens", "Jens vej", 1234));
            em.persist(new Employee("Per", "Pers vej", 4321));

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            em.getTransaction().commit();
        } finally {
            em.close();
        }
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method 
    @Test
    public void testSize() {
        assertEquals(2, facade.getAll().size(), "Expects two rows in the database");
    }

    @Test
    public void testRich() {
        assertEquals("Per", facade.getRichest().getName(), "Expects same name");
    }

    @Test
    public void testCreate() {

        Employee actual = facade.create("Lise", "Lises vej", 2324);
        Employee exp = facade.getById(actual.getId());
        assertEquals(actual.getName(), exp.getName(), "Expects same user");
    }

        @Test
    public void testByName() {
        assertEquals("Jens", facade.getByName("Jens").getName(), "Expects same name");
    }
    
    // Bro it be changing id all da time man
//        @Test
//    public void testById() {
//        assertEquals(2, facade.getById(2).getId(), "Expects same id");
//    }
    
}
