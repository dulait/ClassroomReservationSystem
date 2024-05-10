/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.server.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 *
 * @author Lenovo
 */
@Entity
@Inheritance
@DiscriminatorColumn(name="Tip")
@Table(name = "users")
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String firstName;
    private String lastName;
    private String email;
    
    
    @Enumerated(EnumType.STRING)
    private UserType userType;
    
     
    /*@OneToOne(cascade = CascadeType.MERGE) REKAO DUSAN DA USER NE ZNA NISTA O temppasswordu
    @JoinColumn(name = "temp_password_id")
    private TempPassword tempPassword;
    
     @OneToOne(cascade = CascadeType.MERGE)  REKAO DUSAN DA USER NE ZNA NISTA sifri
    @JoinColumn(name = "password_id")
    private Password password;*/
     
     
    /*@OneToMany      REKAO DUSAN DA USER NE ZNA NISTA O TOKENU
    @JoinColumn(name = "user_id")
    private List<Token> tokens;*/
    

    public User() {
    }

    public User(Long id, String firstName, String lastName, String email, UserType userType) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userType = userType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", userType=" + userType + '}';
    }

    
    
    
    
}
