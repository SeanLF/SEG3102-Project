/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import persistence.Property;
import persistence.UserAccount;

/**
 *
 * @author Sean
 */
@Named(value = "visitationBean")
@RequestScoped
public class VisitationBean {

    private String visitationid;
    private Date startdate;
    private String starttime;
    private Date enddate;
    private String endtime;
    private String propertyid;
    private String categoryid;
    private String searchResults;
    @PersistenceContext(unitName = "OPRS")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

    private String status;
    
    /**
     * Creates a new instance of VisitationBean
     */
    public VisitationBean() {
    }
    
    public void persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }

    public void update(Object object) {
        try {
            utx.begin();
            em.merge(object);
            utx.commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }
    
    public void addToVisitationList(){
        try {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            UserAccount useraccount = (UserAccount) session.getAttribute("User");
            useraccount = em.find(UserAccount.class, useraccount.getUserId());
            
            
            status = "Successfuly added to visitation list";
        } catch (Exception ex) {
            Logger.getLogger(PropertyBean.class.getName()).log(Level.SEVERE, null, ex);
            status = "Error While Creating New Property";
        }
        
    }

    /**
     * @return the searchResults
     */
    public String getSearchResults() {
        return searchResults;
    }

    /**
     * @param searchResults the searchResults to set
     */
    public void setSearchResults(String searchResults) {
        this.searchResults = searchResults;
    }

    /**
     * @return the visitationid
     */
    public String getVisitationid() {
        return visitationid;
    }

    /**
     * @param visitationid the visitationid to set
     */
    public void setVisitationid(String visitationid) {
        this.visitationid = visitationid;
    }

    /**
     * @return the startdate
     */
    public Date getStartdate() {
        return startdate;
    }

    /**
     * @param startdate the startdate to set
     */
    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    /**
     * @return the starttime
     */
    public String getStarttime() {
        return starttime;
    }

    /**
     * @param starttime the starttime to set
     */
    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    /**
     * @return the enddate
     */
    public Date getEnddate() {
        return enddate;
    }

    /**
     * @param enddate the enddate to set
     */
    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    /**
     * @return the endtime
     */
    public String getEndtime() {
        return endtime;
    }

    /**
     * @param endtime the endtime to set
     */
    public void setEndtime(String endtime) {
        this.endtime = endtime;
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
     * @return the categoryid
     */
    public String getCategoryid() {
        return categoryid;
    }

    /**
     * @param categoryid the categoryid to set
     */
    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
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
