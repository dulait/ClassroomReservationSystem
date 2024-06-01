/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.server.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.bg.fon.njt.server.Models.Password;
import rs.ac.bg.fon.njt.server.Models.Reservation;

/**
 *
 * @author Lenovo
 */
public interface ReservationRepository  extends JpaRepository<Reservation, Long> {
    
}
