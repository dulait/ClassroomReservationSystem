package rs.ac.bg.fon.njt.server.Repositories;


import java.sql.Date;
import java.time.LocalDate;



import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.bg.fon.njt.server.Models.Classroom;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    
        
    /* @Query("SELECT c, " +
           "(SELECT r FROM Reservation r WHERE r.classroom = c AND r.start = :date) " +
           "FROM Classroom c")
    List<Classroom> findClassroomsWithReservationsOnDate(@Param("date") Date date);*/

    
    
}
