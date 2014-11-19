/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author isaac
 */
@Named(value = "categoryBean")
@Dependent
public class CategoryBean {

    /**
     * Creates a new instance of CategoryBean
     */
    public CategoryBean() {
    }
    
}
