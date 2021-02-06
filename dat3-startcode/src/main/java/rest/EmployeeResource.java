/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.EmployeeDTO;
import facades.EmployeeFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author SJUBE
 */
@Path("employee")
public class EmployeeResource {

    
    
    // De hellige tre skrifter
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private EmployeeFacade ef = EmployeeFacade.getFacadeExample(emf);
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();


    @Context
    private UriInfo context;

    /**
     * Creates a new instance of EmployeeResource
     */
    public EmployeeResource() {
    }

    @Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployees() {
        EntityManager em = emf.createEntityManager();

        List<EmployeeDTO> lister = EmployeeDTO.getDtos(ef.getAll());
        return Response.ok().entity(gson.toJson(lister)).build();

    }

    @Path("high")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getEmployeeById() {
        EntityManager em = emf.createEntityManager();
        try {
            EmployeeDTO empl = new EmployeeDTO(ef.getRichest());
            return new Gson().toJson(empl);
        } finally {
            em.close();
        }
    }

    @Path("id/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getEmployeeById(@PathParam("id") int id) {
        EntityManager em = emf.createEntityManager();
        try {
            EmployeeDTO empl = new EmployeeDTO(ef.getById(id));
            return new Gson().toJson(empl);
        } finally {
            em.close();
        }
    }

    @Path("name/{name}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getEmployeeById(@PathParam("name") String name) {
        EntityManager em = emf.createEntityManager();
        try {
            EmployeeDTO empl = new EmployeeDTO(ef.getByName(name));
            return new Gson().toJson(empl);
        } finally {
            em.close();
        }
    }

    /**
     * Retrieves representation of an instance of rest.EmployeeResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        return "Hi there :)";
    }

    /**
     * PUT method for updating or creating an instance of EmployeeResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
