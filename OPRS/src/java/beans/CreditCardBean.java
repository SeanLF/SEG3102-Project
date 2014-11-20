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
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import persistence.CreditCard;

/**
 *
 * @author Sean
 */
@Named(value = "creditCardBean")
@RequestScoped
public class CreditCardBean {
    private String number;
    private String type;
    private String expirationmonth;
    private String expirationyear;
    private String nameoncard;
    private String useraccountid;
    @PersistenceContext(unitName = "OPRS")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    
    private String status;
    /**
     * Creates a new instance of CreditCardBean
     */
    public CreditCardBean() {
    }

    /**
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the expirationmonth
     */
    public String getExpirationmonth() {
        return expirationmonth;
    }

    /**
     * @param expirationmonth the expirationmonth to set
     */
    public void setExpirationmonth(String expirationmonth) {
        this.expirationmonth = expirationmonth;
    }

    /**
     * @return the expirationyear
     */
    public String getExpirationyear() {
        return expirationyear;
    }

    /**
     * @param expirationyear the expirationyear to set
     */
    public void setExpirationyear(String expirationyear) {
        this.expirationyear = expirationyear;
    }

    /**
     * @return the nameoncard
     */
    public String getNameoncard() {
        return nameoncard;
    }

    /**
     * @param nameoncard the nameoncard to set
     */
    public void setNameoncard(String nameoncard) {
        this.nameoncard = nameoncard;
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
    
    public void addCreditCard() {
        try {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            
            CreditCard cc = new CreditCard();
            cc.setUseraccountid(session.getAttribute("userId").toString());
            cc.setNumber(number);
            cc.setExpirationmonth(expirationmonth);
            cc.setExpirationyear(expirationyear);
            cc.setNameoncard(nameoncard);
            cc.setType(type);
            cc.setUseraccountid(useraccountid);
            
            persist(cc);
            status="Successfuly added Credit Card";
            FacesContext.getCurrentInstance().getExternalContext().dispatch("protected/myAccount");
        } catch (Exception ex ) {
            Logger.getLogger(CreditCardBean.class.getName()).log(Level.SEVERE, null, ex);
            status="Error While Creating New Credit Card";
        }
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
    
}
