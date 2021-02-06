package facades;

import dtos.BankCustomerDTO;
import entities.BankCustomer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class BankFacade {

    private static BankFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private BankFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static BankFacade getBankFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new BankFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
// Get all

    public List<BankCustomer> getAllBankCustomers() {
        EntityManager em = emf.createEntityManager();
        TypedQuery tp = em.createQuery("SELECT b FROM BankCustomer b", BankCustomer.class);
        List<BankCustomer> bli = tp.getResultList();
        return bli;
    }

//CustomerDTO getCustomerByID(int id)
    public BankCustomerDTO getCustomerByID(int id) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<BankCustomer> q = em.createQuery("SELECT e FROM BankCustomer e WHERE e.id ='" + id + "'", BankCustomer.class);
        BankCustomerDTO bdo = new BankCustomerDTO(q.getSingleResult());
        return bdo;
    }

//List<CustomerDTO> getCustomerByName(String name)
    public BankCustomerDTO getCustomerByName(String name) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<BankCustomer> q = em.createQuery("SELECT e FROM BankCustomer e WHERE e.firstName ='" + name + "'", BankCustomer.class);
        BankCustomerDTO bdo = new BankCustomerDTO(q.getSingleResult());
        return bdo;
    }

//BankCustomer addCustomer(BankCustomer cust)
        public BankCustomer create(BankCustomer bc) {
        BankCustomer bce = new BankCustomer(bc.getFirstName(), bc.getLastName(), bc.getAccountNumber(), bc.getBalance(), bc.getCustomerRanking(),bc.getInternalInfo());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(bce);
            em.getTransaction().commit();
            return bce;
        } finally {
            em.close();
        }

    }
    
    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        BankFacade fe = getBankFacade(emf);
        fe.getAllBankCustomers().forEach(dto -> System.out.println(dto));
        BankCustomer bccc = new BankCustomer("Test", "Manden", "jfe33", 43, 122, "Stuffs Ja");
        System.out.println(fe.getCustomerByID(2));
        System.out.println(fe.getCustomerByName("Fillipa"));
        System.out.println(fe.create(bccc));
    }

}
