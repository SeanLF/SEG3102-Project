/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Sean
 */
@Entity
public class AuctionItem implements Serializable {
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
    private String auctionitemid;
    private String title;
    private String description;
    private String useraccountid;

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuctionItem)) {
            return false;
        }
        AuctionItem other = (AuctionItem) object;
        if ((this.getAuctionitemid() == null && other.getAuctionitemid() != null) || (this.auctionitemid != null && !this.auctionitemid.equals(other.auctionitemid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.AuctionItem[ id=" + auctionitemid + " ]";
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
    
}
