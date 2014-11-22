/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ViewModels.PropertyWithVisitation;
import java.sql.Date;
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
import persistence.Visitation;

/**
 *
 * @author Sean
 */
@Named(value = "visitationBean")
@RequestScoped
public class VisitationBean {

    private String visitationid;
    private String propertyid;
    private List<PropertyWithVisitation> searchResults;
    @PersistenceContext(unitName = "OPRS")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

    /**
     * Creates a new instance of VisitationBean
     */
    public VisitationBean() {
    }

    private void persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }

    private void destroyVisitation(Visitation visitation) {
        try {
            utx.begin();
            visitation = em.find(Visitation.class, visitation.getVisitationid());
            em.remove(visitation);
            utx.commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }

    public void addToVisitingList(PropertyWithVisitation pwv) {
        try {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            UserAccount useraccount = (UserAccount) session.getAttribute("User");
            useraccount = em.find(UserAccount.class, useraccount.getUserId());
            Visitation v = new Visitation();
            String _propertyid = pwv.getProperty().getPropertyid();
            v.setPropertyid(_propertyid);
            v.setVisitationid(useraccount.getUserId() + "_" + _propertyid);
            v.setUserid(useraccount.getUserId());
            persist(v);
        } catch (Exception ex) {
            Logger.getLogger(VisitationBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void removeFromVisitingList(PropertyWithVisitation pwv) {
        try {
            destroyVisitation(pwv.getVisitation());
        } catch (Exception ex) {
            Logger.getLogger(VisitationBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void viewVisitationList() {
        try {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            UserAccount useraccount = (UserAccount) session.getAttribute("User");
            useraccount = em.find(UserAccount.class, useraccount.getUserId());
            List<Object[]> obj = findVisitationListForUser(em, useraccount.getUserId());
            searchResults = (PropertyWithVisitation.mapFromModelToViewModel(obj));
        } catch (Exception ex) {
            Logger.getLogger(VisitationBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private List findVisitationListForUser(EntityManager em, String user) {
        String queryString = "SELECT p , v FROM Property as p left join Visitation as v on p.propertyid = v.propertyid where  p.archived=false and :userid = v.userid order by p.title";
        Query query = em.createQuery(queryString)
                .setParameter("userid", user);

        return performQuery(query);
    }

    private static List performQuery(final Query query) {
        List resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return null;
        }
        ArrayList<Object> results;
        results = new ArrayList<>();
        results.addAll(resultList);
        return results;
    }

    /**
     * @return the searchResults
     */
    public List<PropertyWithVisitation> getSearchResults() {
        viewVisitationList();
        return searchResults;
    }

    /**
     * @param searchResults the searchResults to set
     */
    public void setSearchResults(List<PropertyWithVisitation> searchResults) {
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
}
