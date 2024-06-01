/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.server.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.bg.fon.njt.server.Enums.ClassroomType;

/**
 *
 * @author Lenovo
 */

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Reservation {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   
    @Column(name = "note")
    private String note;


     @Column(name = "is_accepted")
    private boolean isAccepted;
     
     
        
        @Column(name = "is_waiting_for_approval")
    private boolean isWaitingForApproval;
     
     @Column(name="start")
    private Date start;
     
     @Column(name="end")
     private Date end;
     
     
     @Column(name="purpose")
     private String purpose;
    
    
     @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
     
     
     @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "classroom_id", referencedColumnName = "id")
    private Classroom classroom;
}
