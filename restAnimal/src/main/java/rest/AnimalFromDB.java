/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import entity.Animal;
import java.util.Random;
import javax.ws.rs.PathParam;

/**
 * REST Web Service
 *
 * @author SJUBE
 */
@Path("animals_db")
public class AnimalFromDB {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AnimalFromDB
     */
    public AnimalFromDB() {
    }

    @Path("animals")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimals() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a", Animal.class);
            List<Animal> animals = query.getResultList();
            return new Gson().toJson(animals);
        } finally {
            em.close();
        }
    }

    @Path("animalById/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimalById(@PathParam("id") int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Animal anim = em.find(Animal.class, id);
            return new Gson().toJson(anim);
        } finally {
            em.close();
        }
    }

    @Path("animalByType/{type}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimals(@PathParam("type") String type) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a WHERE a.type = '" + type + "'", Animal.class);
            Animal anim = query.getSingleResult();
//    List<Animal> anim = query.getResultList();
            return new Gson().toJson(anim);
        } finally {
            em.close();
        }
    }

    @Path("random")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimalRandom() {
        EntityManager em = emf.createEntityManager();

        TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a", Animal.class);
        List<Animal> animals = query.getResultList();
        
        Random rando = new Random();
        int id = rando.nextInt(animals.size()) + 1;
        try {
            Animal anim = em.find(Animal.class, id);
            return new Gson().toJson(anim);
        } finally {
            em.close();
        }
    }

}
