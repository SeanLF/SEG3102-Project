/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewModels;

import java.util.ArrayList;
import java.util.List;
import persistence.*;

/**
 *
 * @author Sean
 */
public class PropertyWithVisitation {
    private Property property;
    private Visitation visitation;

    /**
     * @return the property
     */
    public Property getProperty() {
        return property;
    }

    /**
     * @param property the property to set
     */
    public void setProperty(Property property) {
        this.property = property;
    }

    /**
     * @return the visitation
     */
    public Visitation getVisitation() {
        return visitation;
    }

    /**
     * @param visitation the visitation to set
     */
    public void setVisitation(Visitation visitation) {
        this.visitation = visitation;
    }
    
    public PropertyWithVisitation(Object[] object){
        this.property = (Property)object[0];
        this.visitation = (Visitation)object[1];
    }
    
    
    
    public static List<PropertyWithVisitation> mapFromModelToViewModel(List<Object[]> object){
        ArrayList<PropertyWithVisitation> propertyWithVisitationList = new ArrayList<>();
        for (Object[] obj : object) {
            propertyWithVisitationList.add(new PropertyWithVisitation(obj));
        }
        return propertyWithVisitationList;
    }
}
