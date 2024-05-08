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
@DiscriminatorValue("NonTeachingEmployee")
public class NonTeachingEmployee extends Employee{

    public NonTeachingEmployee() {
    }
    
   
}
