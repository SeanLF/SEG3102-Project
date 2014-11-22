/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import persistence.Property;
import persistence.UserAccount;
import ViewModels.PropertyWithVisitation;
import java.util.Iterator;

/**
 * This is our property
 *
 * @author Sean
 */
@Named(value = "propertyBean")
@RequestScoped
public class PropertyBean {

    private String propertyid;
    private String title;
    private String description;
    private String useraccountid;
    private String address;
    private double rent;
    private double maxRent;
    private boolean archived;
    private boolean editable;
    private List<Property> viewOwnerP;
    private List<PropertyWithVisitation> searchResults;
    private List<PropertyWithVisitation> browseCatalogP;
    @PersistenceContext(unitName = "OPRS")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

    private String status;

    /**
     * Creates a new instance of PropertyBean
     */
    public PropertyBean() {
        viewOwnerP = new ArrayList<>();
        searchResults = new ArrayList<>();
        browseCatalogP = new ArrayList<>();
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

    public String addProperty() {
        try {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            Property p = new Property();
            UserAccount useraccount = (UserAccount) session.getAttribute("User");
            p.setUseraccountid(useraccount.getUserId());
            p.setDescription(description);
            p.setTitle(title);
            p.setArchived(false);
            p.setPropertyid(useraccount.getUserId() + "_" + title);
            p.setAddress(getAddress());
            p.setRent(getRent());

            persist(p);
            status = "Successfuly added Property";

            // make the client an owner
            UserAccount acc = em.find(UserAccount.class, useraccount.getUserId());
            acc.setHasProperties(true);
            update(acc);
            session.setAttribute("Owner", true);
            return "viewOwnerProperties";
        } catch (Exception ex) {
            Logger.getLogger(PropertyBean.class.getName()).log(Level.SEVERE, null, ex);
            status = "Error While Creating New Property";
        }
        return null;
    }

    public void deleteProperty(Property p) {
        try {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            p = em.find(Property.class, p.getPropertyid());
            UserAccount useraccount = (UserAccount)session.getAttribute("User");
            p.setArchived(true);

            update(p);

            // is the client still an owner?
            UserAccount acc = em.find(UserAccount.class, useraccount.getUserId());
            boolean isOwner = findPropertiesForUser(em, acc.getUserId()).size() > 0;
            acc.setHasProperties(isOwner);
            persist(acc);
            session.setAttribute("Owner", isOwner);
            if (isOwner) {
                FacesContext.getCurrentInstance().getExternalContext().dispatch("protected/viewOwnerProperties");
            } else {
                FacesContext.getCurrentInstance().getExternalContext().dispatch("protected/welcome");
            }
        } catch (Exception ex) {
            Logger.getLogger(PropertyBean.class.getName()).log(Level.SEVERE, null, ex);
            status = "Error While Deleting Property";
        }
    }

    public void searchProperties() {
        searchResults = new ArrayList<>();
        if (rent >= 0.0 & maxRent >= rent) {
            // lookup by name
            List<Object[]> obj = findPropertiesByRent(em, rent, maxRent);
            searchResults = PropertyWithVisitation.mapFromModelToViewModel(obj);
        } else if (rent >= maxRent && maxRent >= 0) {
            List<Object[]> obj = findPropertiesByRent(em, maxRent, rent);
            searchResults = PropertyWithVisitation.mapFromModelToViewModel(obj);
        }
    }

    public void viewOwnerProperties() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        UserAccount acc = (UserAccount) session.getAttribute("User");
        viewOwnerP = findPropertiesForUser(em, acc.getUserId());
    }

    public void browseCatalog() {
        browseCatalogP = PropertyWithVisitation.mapFromModelToViewModel(findAllProperties(em));
    }

    // show searchResults if any
    public boolean getShowResults() {
        return (searchResults != null) && searchResults.size() > 0;
    }

    // show message if no result
    public boolean getShowMessage() {
        return (searchResults != null) && searchResults.isEmpty();
    }

    /**
     * @return the propertyid
     */
    public String getPropertyid() {
        return propertyid;
    }

    /**
     * @return the rent
     */
    public double getRent() {
        return rent;
    }

    /**
     * @param rent the rent to set
     */
    public void setRent(double rent) {
        this.rent = rent;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
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
     * @return the archived
     */
    public boolean isArchived() {
        return archived;
    }

    /**
     * @param archived the archived to set
     */
    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    private static List performQuery(final Query query) {
        List resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return null;
        }
        ArrayList<Object[]> results;
        results = new ArrayList<>();
        results.addAll(resultList);
        return results;
    }

    private static List findAllProperties(EntityManager em) {
        String queryString = "SELECT p , v FROM Property p left join Visitation v on p.propertyid = v.propertyid where  p.archived=false";
        Query query = em.createQuery(queryString);

        return performQuery(query);
    }

    private static List<Property> findPropertiesForUser(EntityManager em, String userid) {
        Query query = em.createQuery(
                "SELECT p  FROM Property p where p.useraccountid = :userid order by p.title");
        query.setParameter("userid", userid);

        return performQuery(query);
    }

    private List findPropertiesByRent(EntityManager em, double rent, double maxRent) {
        String queryString = "SELECT p, v FROM Property p left join Visitation v on p.propertyid = v.propertyid where :minRent <= p.rent and p.rent <= :maxRent and p.archived=false order by p.rent";
        Query query = em.createQuery(queryString)
                .setParameter("minRent", rent)
                .setParameter("maxRent", maxRent);

        return performQuery(query);
    }

    /**
     * @return the searchResults
     */
    public List<PropertyWithVisitation> getSearchResults() {
        return searchResults;
    }

    /**
     * @param searchResults the searchResults to set
     */
    public void setSearchResults(List<PropertyWithVisitation> searchResults) {
        this.searchResults = searchResults;
    }

    /**
     * @return the maxRent
     */
    public double getMaxRent() {
        return maxRent;
    }

    /**
     * @param maxRent the maxRent to set
     */
    public void setMaxRent(double maxRent) {
        this.maxRent = maxRent;
    }

    /**
     * @return the editable
     */
    public boolean isEditable() {
        return editable;
    }

    /**
     * @param editable the editable to set
     */
    public void setEditable(boolean editable) {
        this.editable = editable;
    }
    
    public void save(){
        this.saveAction();
    }

    public void saveAction() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        UserAccount useraccount = (UserAccount)session.getAttribute("User");
        useraccount = em.find(UserAccount.class, useraccount.getUserId());
        for (Property p : viewOwnerP) {
            p.setEditable(false);
            update(p);
        }
        viewOwnerP = findPropertiesForUser(em, useraccount.getUserId());
    }
    
    public void editAction(Property p){
         p.setEditable(true);
         update(p);
    }
    
    public String deleteAction(Property p){
         deleteProperty(p);
         return null;
    }

    /**
     * @return the viewOwnerP
     */
    public List<Property> getViewOwnerP() {
        viewOwnerProperties();
        return viewOwnerP;
    }

    /**
     * @param viewOwnerP the viewOwnerP to set
     */
    public void setViewOwnerP(List<Property> viewOwnerP) {
        this.viewOwnerP = viewOwnerP;
    }

    /**
     * @return the browseCatalogP
     */
    public List<PropertyWithVisitation> getBrowseCatalogP() {
        browseCatalog();
        return browseCatalogP;
    }

    /**
     * @param browseCatalogP the browseCatalogP to set
     */
    public void setBrowseCatalogP(List<PropertyWithVisitation> browseCatalogP) {
        this.browseCatalogP = browseCatalogP;
    }
}
