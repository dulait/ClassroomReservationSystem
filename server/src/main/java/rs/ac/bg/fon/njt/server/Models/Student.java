/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.server.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author Lenovo
 */
@Entity
@DiscriminatorValue("Student")
public class Student extends User{
    private String reasonForAccess;

    public Student() {
    }

    public Student(String reasonForAccess, Long id, String firstName, String lastName, String email, UserType userType) {
        super(id, firstName, lastName, email, userType);
        this.reasonForAccess = reasonForAccess;
    }

    
    
    public String getReasonForAccess() {
        return reasonForAccess;
    }

    public void setReasonForAccess(String reasonForAccess) {
        this.reasonForAccess = reasonForAccess;
    }
    
    
}
