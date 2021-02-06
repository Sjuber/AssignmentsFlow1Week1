package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.BankCustomerDTO;
import utils.EMF_Creator;
import facades.BankFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//Todo Remove or change relevant parts before ACTUAL use
@Path("bank")
public class BankCustomerResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final BankFacade FACADE = BankFacade.getBankFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRenameMeCount() {
        long count = FACADE.getAllBankCustomers().size();
        //System.out.println("--------------->"+count);
        return "{\"count\":" + count + "}";  //Done manually so no need for a DTO
    }

    @Path("all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBankers() {
        EntityManager em = EMF.createEntityManager();
        return Response.ok().entity(GSON.toJson(FACADE.getAllBankCustomers())).build();

    }

    @Path("id/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getBankerById(@PathParam("id") int id) {
        EntityManager em = EMF.createEntityManager();
        try {
            BankCustomerDTO baal = FACADE.getCustomerByID(id);
            return new Gson().toJson(baal);
        } finally {
            em.close();
        }
    }
}
