/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.anton.pustovidko.model;

import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 * Manager class
 *
 * @author Anton Pustovidko
 * @version 1.0
 */
public class Manager {

    /**
     * UserInput object 
     */
    private List<UserInput> userInputs;
    

    /**
     * Method for saving data to the database
     * @param input userInput
     * @param operation operation
     * @param output translation output
     * @param inputObject UserInput object
     * @param outputObject TranslationOutput object
     * @param em EntityManager
     */
    public void persitEntity(String input, String operation, String output, UserInput inputObject, TranslationOutput outputObject, EntityManager em) {

        inputObject.setUserInput(input);
        inputObject.setOperation(operation);
        inputObject.setDepartment(outputObject);

        outputObject.setName(output);

        em.getTransaction().begin();
        try {
            em.persist(outputObject);
            em.persist(inputObject);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } 
    }

    /**
     * Method for getting data from database
     * @param em EntityManager
     * @return list of data
     */
    public List<String> getResults(EntityManager em) {
        List<String> results = new ArrayList<>();
        userInputs = em.createQuery("SELECT s FROM UserInput s", UserInput.class).getResultList();
        for (UserInput s : userInputs) {
            out.println(s + "<br>");
            results.add(s.toString());
        }
        return results;
    }
}
