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
@DiscriminatorValue("TeachingEmployee")
public class TeachingEmployee extends Employee{
    private String academicTitle;
    private String department;

    public TeachingEmployee() {
    }

    public TeachingEmployee(String academicTitle, String department, String jobTitle, Long id, String firstName, String lastName, String email, UserType userType) {
        super(jobTitle, id, firstName, lastName, email, userType);
        this.academicTitle = academicTitle;
        this.department = department;
    }
    public String getAcademicTitle() {
        return academicTitle;
    }

    public void setAcademicTitle(String academicTitle) {
        this.academicTitle = academicTitle;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
    
    
}
