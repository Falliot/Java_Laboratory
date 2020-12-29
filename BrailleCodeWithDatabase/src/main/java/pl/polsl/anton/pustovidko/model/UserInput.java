/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.anton.pustovidko.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * UserInput class for user Input table in database
 *
 * @author Anton Pustovidko
 * @version 1.0
 */
@Entity
@Table(name = "UserInput")
public class UserInput implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    /**
     * id
     */
    private Integer id;
    
    /**
     * Column userInput
     */
    @Column(name = "userInput", length = 50, nullable = false)
    
    /**
     * userInput
     */
    private String userInput;
    
    /**
     * operation
     */
    private String operation;
    
    @OneToOne
    private TranslationOutput translationOutput;
    
    /**
     * UserInput constructor
     */
    public UserInput() {
    }

    /**
     * UserInput constructor
     * @param userInput user input 
     * @param operation operation
     */
    public UserInput(String userInput, String operation) {
        this.userInput = userInput;
        this.operation = operation;
    }


    /**
     * Getter for id 
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter for id
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Getter for userInput
     * @return user input
     */
    public String getUserInput() {
        return userInput;
    }
    
    /**
     * Setter for userInput
     * @param userInput  userInput
     */
    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }
    
    /**
     * Getter for operation
     * @return operation
     */
    public String getOperation() {
        return operation;
    }
    
    /**
     * Setter for operation
     * @param operation operation
     */
    public void setOperation(String operation) {
        this.operation = operation;
    }
    
    /**
     * Getter for translationOutput
     * @return translation output
     */
     public TranslationOutput getTranslationOutput() {
      return translationOutput;
   }

     /**
      * Setter for translationOutput
      * @param translationOutput translation output
      */
   public void setDepartment(TranslationOutput translationOutput) {
      this.translationOutput = translationOutput;
   }	

   /**
    * Hash code method
    * @return hash
    */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Equals method
     * @param object object
     * @return true or false
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserInput)) {
            return false;
        }
        UserInput other = (UserInput) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * Method that returns specified string
     * @return string
     */
    @Override
    public String toString() {
        return userInput + operation + "is " + translationOutput;
    }
    
}
