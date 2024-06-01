/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.server.Controllers;

import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.bg.fon.njt.server.Models.Classroom;



import rs.ac.bg.fon.njt.server.Services.ClassroomService;
import rs.ac.bg.fon.njt.server.Utils.Response;
import rs.ac.bg.fon.njt.server.Utils.ResponseConverter;

/**
 *
 * @author Lenovo
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/classrooms")
public class ClassroomController {
    private final ClassroomService classroomService;
     private final ResponseConverter<Classroom> converter;
    
    @GetMapping("/{year}/{month}/{day}")
    public ResponseEntity<List<Classroom>> index(@PathVariable(name = "year") int year,@PathVariable(name="month") int month,@PathVariable(name="day") int day) {
        Response<List<Classroom>> response = classroomService.getAllClassrooms(year,month,day);
        return converter.toListResponseEntity(response);
    }
    
}
