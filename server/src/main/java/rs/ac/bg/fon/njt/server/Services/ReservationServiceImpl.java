/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.server.Services;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.njt.server.Enums.ResponseStatus;
import rs.ac.bg.fon.njt.server.Enums.UserType;
import rs.ac.bg.fon.njt.server.Models.Classroom;
import rs.ac.bg.fon.njt.server.Models.Reservation;
import rs.ac.bg.fon.njt.server.Models.ReservationRequest;
import rs.ac.bg.fon.njt.server.Models.User;
import rs.ac.bg.fon.njt.server.Repositories.ReservationRepository;
import rs.ac.bg.fon.njt.server.Utils.Response;

/**
 *
 * @author Lenovo
 */
@RequiredArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService{
    
    private final UserService userService;
    private final ClassroomService classromService;
    
    private final ReservationRepository reservationRepository;
    
    @Override
    public Response<Reservation> createNewReservation(ReservationRequest request) {
        
        
        if(request.getUser_id()==null || request.getClassroom_id()==null || request.getEnd()==null || request.getStart()==null){
        return new Response<>(ResponseStatus.BadRequest, null);
        }
        
        Response<User> existingUserResponse = userService.findUserById(request.getUser_id());

        if (existingUserResponse.getStatus() == ResponseStatus.NotFound) {
            return new Response<>(ResponseStatus.NotFound, null);
        }
        
        Response<Classroom> existingClassroomResponse=classromService.findClassroomById(request.getClassroom_id());
        
        if (existingClassroomResponse.getStatus() == ResponseStatus.NotFound) {
            return new Response<>(ResponseStatus.NotFound, null);
        }
        
        
        
        Reservation reservation=new Reservation();
        reservation.setAccepted(false);
        reservation.setWaitingForApproval(true);
        reservation.setUser(existingUserResponse.getData());
        reservation.setClassroom(existingClassroomResponse.getData());
        reservation.setStart(request.getStart());
        reservation.setEnd(request.getEnd());
        
        reservation.setNote(request.getNote());
        reservation.setPurpose(request.getPurpose());
        
        Reservation r=reservationRepository.save(reservation);
       

        
        return new Response<>(ResponseStatus.Ok, r);
    }

    @Override
    public Response<Reservation> acceptOrRejectExistingReservation(String reservation_id,String administrator_id,String action) {
        try{
        Long rId=Long.valueOf(reservation_id);
        Long aId=Long.valueOf(administrator_id);
        
         Response<User> existingUserResponse = userService.findUserById(aId);

        if (existingUserResponse.getStatus() == ResponseStatus.NotFound) {
            return new Response<>(ResponseStatus.NotFound, null);
        }
        if(existingUserResponse.getData().getUserType()!=UserType.Administrator){
            return new Response<>(ResponseStatus.Unauthorized,null);
        }
        
        Optional<Reservation> existingReservationResponse = reservationRepository.findById(rId);
        
        if (existingReservationResponse.isPresent()) {
    
            Reservation reservation = existingReservationResponse.get();
            
            if(action.equals("reject")){
                reservationRepository.delete(reservation);
                return new Response<>(ResponseStatus.Ok,null);
            }
            if(action.equals("accept")){
                reservation.setAccepted(true);
                reservation.setWaitingForApproval(false);
           
                Reservation r=reservationRepository.save(reservation);
                return new Response<>(ResponseStatus.Ok,r);
            }
            return new Response<>(ResponseStatus.BadRequest,null);
        } else {
         return new Response<>(ResponseStatus.NotFound,null);
        }
        
        }catch(Exception e){
             return new Response<>(ResponseStatus.BadRequest,null);
        }
       
        
        
    }
    
}
