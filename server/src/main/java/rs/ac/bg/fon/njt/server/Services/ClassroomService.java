package rs.ac.bg.fon.njt.server.Services;

import java.util.List;
import rs.ac.bg.fon.njt.server.Models.Classroom;

import rs.ac.bg.fon.njt.server.Utils.Response;

public interface ClassroomService {

    public Response<List<Classroom>> getAllClassrooms(int year,int month,int day);

    public Response<Classroom> findClassroomById(Long id);
}
