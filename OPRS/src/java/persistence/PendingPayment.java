/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Sean
 */
@Entity
public class PendingPayment implements Serializable {
    private static long serialVersionUID = 1L;

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }
    @Id
    private String pendingpaymentid;
    private String amount;
    private String useraccountid;
    private boolean archived;

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PendingPayment)) {
            return false;
        }
        PendingPayment other = (PendingPayment) object;
        if ((this.getPendingpaymentid()== null && other.getPendingpaymentid()!= null) || (this.getPendingpaymentid()!= null && !this.pendingpaymentid.equals(other.pendingpaymentid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.PendingPayment[ id=" + pendingpaymentid + " ]";
    }    

    /**
     * @return the pendingpaymentid
     */
    public String getPendingpaymentid() {
        return pendingpaymentid;
    }

    /**
     * @param pendingpaymentid the pendingpaymentid to set
     */
    public void setPendingpaymentid(String pendingpaymentid) {
        this.pendingpaymentid = pendingpaymentid;
    }

    /**
     * @return the amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(String amount) {
        this.amount = amount;
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
    
}
