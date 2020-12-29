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
import javax.persistence.Table;

/**
 * TranslationOutput class for translationOutput table in database
 *
 * @author Anton Pustovidko
 * @version 1.0
 */
@Entity
@Table(name = "TranslationOutput")
public class TranslationOutput implements Serializable {

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
     * Column userOutput
     */
    @Column(name = "userOutput", length = 50, nullable = false)
    
    /**
     * userOutput
     */
    private String output;

    /**
     * TranslationOutput constructor
     */
    public TranslationOutput() {
    }

    /**
     * TranslationOutput constructor
     * @param output output
     */
    public TranslationOutput(String output) {
        this.output = output;
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
     * Getter for output
     * @return output
     */
    public String getOutput() {
        return output;
    }
    
    /**
     * Setter for output
     * @param output output
     */
    public void setName(String output) {
        this.output = output;
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
        if (!(object instanceof TranslationOutput)) {
            return false;
        }
        TranslationOutput other = (TranslationOutput) object;
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
        return output;
    }

}
