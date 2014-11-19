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
public class CreditCart implements Serializable {
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
    private String creditcardid;
    private String number;
    private String type;
    private String expirationmonth;
    private String expirationyear;
    private String nameoncard;

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CreditCart)) {
            return false;
        }
        CreditCart other = (CreditCart) object;
        if ((this.getCreditcardid() == null && other.getCreditcardid() != null) || (this.creditcardid != null && !this.creditcardid.equals(other.creditcardid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.CreditCart[ id=" + creditcardid + " ]";
    }

    /**
     * @return the creditcardid
     */
    public String getCreditcardid() {
        return creditcardid;
    }

    /**
     * @param creditcardid the creditcardid to set
     */
    public void setCreditcardid(String creditcardid) {
        this.creditcardid = creditcardid;
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
    
}
