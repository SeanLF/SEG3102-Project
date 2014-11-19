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
public class Interview implements Serializable {
    private static  long serialVersionUID = 1L;

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
    private String interviewid;
    private String description;
    private Date interviewdate;
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Interview)) {
            return false;
        }
        Interview other = (Interview) object;
        if ((this.getInterviewid() == null && other.getInterviewid() != null) || (this.interviewid != null && !this.interviewid.equals(other.interviewid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.Interview[ id=" + interviewid + " ]";
    }

    /**
     * @return the interviewid
     */
    public String getInterviewid() {
        return interviewid;
    }

    /**
     * @param interviewid the interviewid to set
     */
    public void setInterviewid(String interviewid) {
        this.interviewid = interviewid;
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
     * @return the interviewdate
     */
    public Date getInterviewdate() {
        return interviewdate;
    }

    /**
     * @param interviewdate the interviewdate to set
     */
    public void setInterviewdate(Date interviewdate) {
        this.interviewdate = interviewdate;
    }
    
}
