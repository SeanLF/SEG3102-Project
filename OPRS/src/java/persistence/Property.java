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
public class Property implements Serializable {
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
    private String propertyid;
    private String title;
    private String description;
    private String useraccountid;

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Property)) {
            return false;
        }
        Property other = (Property) object;
        if ((this.getPropertyid()== null && other.getPropertyid() != null) || (this.getPropertyid() != null && !this.propertyid.equals(other.propertyid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.Property[ id=" + getPropertyid() + " ]";
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
