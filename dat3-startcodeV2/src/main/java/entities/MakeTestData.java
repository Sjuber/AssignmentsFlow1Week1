/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author SJUBE
 */
public class MakeTestData {
    
        public static void main(String[] args) {
            EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
            EntityManager ef = emf.createEntityManager();
            BankCustomer bc1 = new BankCustomer("John", "Doe", "13d3434", 10, 100, "stuff");
            BankCustomer bc2 = new BankCustomer("Fillipa", "Doe", "djje23d", 100, 10, "things");
            BankCustomer bc3 = new BankCustomer("Verdac", "Soulcrusher", "akdj323", 666, 777, "junk");
            
            ef.getTransaction().begin();
            ef.persist(bc1);
            ef.persist(bc2);
            ef.persist(bc3);
            ef.getTransaction().commit();
            ef.close();
    }
    
}
