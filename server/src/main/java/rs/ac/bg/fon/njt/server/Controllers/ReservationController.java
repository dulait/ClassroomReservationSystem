/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.server.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.bg.fon.njt.server.Models.Reservation;
import rs.ac.bg.fon.njt.server.Models.ReservationRequest;
import rs.ac.bg.fon.njt.server.Services.ReservationService;
import rs.ac.bg.fon.njt.server.Utils.Response;
import rs.ac.bg.fon.njt.server.Utils.ResponseConverter;

/**
 *
 * @author Lenovo
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    
    private final ReservationService reservationService;
    private final ResponseConverter<Reservation> converter;
    
    @PostMapping
    public ResponseEntity<Reservation> create(@RequestBody ReservationRequest request) {
        Response<Reservation> response = reservationService.createNewReservation(request);
        return converter.toResponseEntity(response);
    }
    
    @PutMapping("/{action}/{reservation_id}/{administrator_id}")
    public ResponseEntity<Reservation> acceptOrReject(@PathVariable("action") String action,@PathVariable("reservation_id") String reservation_id, @PathVariable("administrator_id") String administrator_id) {
        Response<Reservation> response = reservationService.acceptOrRejectExistingReservation(reservation_id, administrator_id, action);
        return converter.toResponseEntity(response);
    }
    
    
    
    
    
}
