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
    private Date startdate;
    private String starttime;
    private Date enddate;
    private String endtime;
    private String propertyid;
    private String categoryid;

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Visitation)) {
            return false;
        }
        Visitation other = (Visitation) object;
        if ((this.getCategoryid()== null && other.getCategoryid() != null) || (this.getVisitationid() != null && !this.visitationid.equals(other.visitationid))) {
            return false;
        }
        return true;
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
     * @return the startdate
     */
    public Date getStartdate() {
        return startdate;
    }

    /**
     * @param startdate the startdate to set
     */
    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    /**
     * @return the starttime
     */
    public String getStarttime() {
        return starttime;
    }

    /**
     * @param starttime the starttime to set
     */
    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    /**
     * @return the enddate
     */
    public Date getEnddate() {
        return enddate;
    }

    /**
     * @param enddate the enddate to set
     */
    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    /**
     * @return the endtime
     */
    public String getEndtime() {
        return endtime;
    }

    /**
     * @param endtime the endtime to set
     */
    public void setEndtime(String endtime) {
        this.endtime = endtime;
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
     * @return the categoryid
     */
    public String getCategoryid() {
        return categoryid;
    }

    /**
     * @param categoryid the categoryid to set
     */
    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }
    
    
}
