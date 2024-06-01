/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.njt.server.Services;

import rs.ac.bg.fon.njt.server.Models.Reservation;
import rs.ac.bg.fon.njt.server.Models.ReservationRequest;
import rs.ac.bg.fon.njt.server.Utils.Response;

/**
 *
 * @author Lenovo
 */
public interface ReservationService {

    public Response<Reservation> createNewReservation(ReservationRequest request);

    public Response<Reservation> acceptOrRejectExistingReservation(String reservation_id,String administrator_id,String action);
    
}
