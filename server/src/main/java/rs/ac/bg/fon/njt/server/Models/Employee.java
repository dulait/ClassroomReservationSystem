/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.server.domain;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author Lenovo
 */
@Entity
@DiscriminatorValue("Employee")
public class Employee extends User{
    private String jobTitle;

    public Employee() {
    }

    public Employee(String jobTitle, Long id, String firstName, String lastName, String email, UserType userType) {
        super(id, firstName, lastName, email, userType);
        this.jobTitle = jobTitle;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    
    
    
    
}
