/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Sean
 */
@Entity
public class Bid implements Serializable {
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
    private String bidid;
    private String amount;
    private Date dateofbid;
    private String timeofbid;
    private String auctionid;

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bid)) {
            return false;
        }
        Bid other = (Bid) object;
        if ((this.getBidid() == null && other.getBidid() != null) || (this.bidid != null && !this.bidid.equals(other.bidid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.Bid[ id=" + bidid + " ]";
    }

    /**
     * @return the bidid
     */
    public String getBidid() {
        return bidid;
    }

    /**
     * @param bidid the bidid to set
     */
    public void setBidid(String bidid) {
        this.bidid = bidid;
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
     * @return the dateofbid
     */
    public Date getDateofbid() {
        return dateofbid;
    }

    /**
     * @param dateofbid the dateofbid to set
     */
    public void setDateofbid(Date dateofbid) {
        this.dateofbid = dateofbid;
    }

    /**
     * @return the timeofbid
     */
    public String getTimeofbid() {
        return timeofbid;
    }

    /**
     * @param timeofbid the timeofbid to set
     */
    public void setTimeofbid(String timeofbid) {
        this.timeofbid = timeofbid;
    }

    /**
     * @return the auctionid
     */
    public String getAuctionid() {
        return auctionid;
    }

    /**
     * @param auctionid the auctionid to set
     */
    public void setAuctionid(String auctionid) {
        this.auctionid = auctionid;
    }
}
