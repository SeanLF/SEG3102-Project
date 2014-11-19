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
import persistence.AuctionItem;

/**
 * This is our property
 * @author Sean
 */
@Named(value = "auctionItemBean")
@RequestScoped
public class AuctionItemBean {
    private String auctionitemid;
    private String title;
    private String description;
    private String useraccountid;
    @PersistenceContext(unitName = "OPRS")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    
    private String status;
    /**
     * Creates a new instance of AuctionItemBean
     */
    public AuctionItemBean() {
    }

    /**
     * @return the auctionitemid
     */
    public String getAuctionitemid() {
        return auctionitemid;
    }

    /**
     * @param auctionitemid the auctionitemid to set
     */
    public void setAuctionitemid(String auctionitemid) {
        this.auctionitemid = auctionitemid;
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
    
    public void addAuctionItem() {
        try {
            AuctionItem ai = new AuctionItem();
            
            ai.setDescription(description);
            ai.setTitle(title);
            ai.setUseraccountid(useraccountid);
            auctionitemid = useraccountid + "_" + title;
            ai.setAuctionitemid(auctionitemid);
            
            persist(ai);
            status="Successfuly added Property";
        } catch (Exception ex ) {
            Logger.getLogger(AuctionItemBean.class.getName()).log(Level.SEVERE, null, ex);
            status="Error While Creating New Property";
        }
    }
    
}
