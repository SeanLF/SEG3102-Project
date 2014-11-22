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
public class Visitation implements Serializable {
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
    private String visitationid;
    private String propertyid;
    private String userid;

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Visitation)) {
            return false;
        }
        Visitation other = (Visitation) object;
        return !((this.getVisitationid()== null && other.getVisitationid() != null) || (this.getVisitationid() != null && !this.visitationid.equals(other.visitationid)));
    }

    @Override
    public String toString() {
        return "persistence.Visitation[ id=" + getVisitationid() + " ]";
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

    /**
     * @return the userid
     */
    public String getUserid() {
        return userid;
    }

    /**
     * @param userid the userid to set
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }
}
