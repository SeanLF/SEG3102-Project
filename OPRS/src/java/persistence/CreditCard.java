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
public class CreditCard implements Serializable {
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
    private String number;
    private String type;
    private String expirationmonth;
    private String expirationyear;
    private String nameoncard;
    private String useraccountid;

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CreditCard)) {
            return false;
        }
        CreditCard other = (CreditCard) object;
        if ((this.getNumber()== null && other.getNumber() != null) || (this.number != null && !this.number.equals(other.number))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.CreditCart[ id=" + number + " ]";
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
