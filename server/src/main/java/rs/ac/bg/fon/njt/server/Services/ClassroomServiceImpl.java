package rs.ac.bg.fon.njt.server.Services;



import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.njt.server.Enums.ResponseStatus;
import rs.ac.bg.fon.njt.server.Models.Classroom;

import rs.ac.bg.fon.njt.server.Models.Reservation;

import rs.ac.bg.fon.njt.server.Repositories.ClassroomRepository;
import rs.ac.bg.fon.njt.server.Repositories.ReservationRepository;

import rs.ac.bg.fon.njt.server.Utils.Response;

@RequiredArgsConstructor
@Service
public class ClassroomServiceImpl implements ClassroomService {

      
    private final ClassroomRepository classroomRepository;
      
      
      
      private final ReservationRepository reservationRepository;
    
    @Override
    public Response<List<Classroom>> getAllClassrooms(int year,int month,int day) {
      
        List<Classroom> classrooms = classroomRepository.findAll();
         System.out.println(classrooms);
         
         
         for(int i=0;i<classrooms.size();i++){
             List<Reservation> reservations=new ArrayList<>();
             for(int j=0;j<classrooms.get(i).getReservations().size();j++){
                 System.out.println(classrooms.get(i).getReservations().get(j).getStart().getClass());
                 
                  Timestamp timestamp = (Timestamp) classrooms.get(i).getReservations().get(j).getStart();

      
               LocalDateTime localDateTime = timestamp.toLocalDateTime();

     
                LocalDate date = localDateTime.toLocalDate();
                  if(year==date.getYear() && month==date.getMonthValue() && day==date.getDayOfMonth()){
                       reservations.add(classrooms.get(i).getReservations().get(j));
                  }else{
                     
                      
                     
                  }
                  
                
             }
             classrooms.get(i).setReservations(reservations);
         }
         
         return new Response<>(ResponseStatus.Ok, classrooms);
    }

    @Override
    public Response<Classroom> findClassroomById(Long id) {
         if (id == null || id <= 0) {
            return new Response<>(ResponseStatus.BadRequest);
        }
        Optional<Classroom> user = classroomRepository.findById(id);

        return user.map(
                        value -> new Response<>(ResponseStatus.Ok, value))
                .orElseGet(() -> new Response<>(ResponseStatus.NotFound)
                );
    }
}
