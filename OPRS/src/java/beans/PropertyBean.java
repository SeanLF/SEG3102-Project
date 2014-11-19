/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import persistence.Property;

/**
 * This is our property
 * @author Sean
 */
@Named(value = "propertyBean")
@RequestScoped
public class PropertyBean {
    private String propertyid;
    private String title;
    private String description;
    private String useraccountid;
    @PersistenceContext(unitName = "OPRS")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    
    private String status;
    /**
     * Creates a new instance of PropertyBean
     */
    public PropertyBean() {
    }
    
    public void persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }
    
    public void addProperty() {
        try {
            Property p = new Property();
            
            p.setDescription(getDescription());
            p.setTitle(getTitle());
            p.setUseraccountid(getUseraccountid());
            setPropertyid(getUseraccountid() + "_" + getTitle());
            p.setPropertyid(getPropertyid());
            
            persist(p);
            setStatus("Successfuly added Property");
        } catch (Exception ex ) {
            Logger.getLogger(PropertyBean.class.getName()).log(Level.SEVERE, null, ex);
            setStatus("Error While Creating New Property");
        }
    }

    /**
     * @return the propertyid
     */
    public String getPropertyid() {
        return propertyid;
    }

    /**
     * @param propertyid the propertyid to set
     */
    public void setPropertyid(String propertyid) {
        this.propertyid = propertyid;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the useraccountid
     */
    public String getUseraccountid() {
        return useraccountid;
    }

    /**
     * @param useraccountid the useraccountid to set
     */
    public void setUseraccountid(String useraccountid) {
        this.useraccountid = useraccountid;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
}
