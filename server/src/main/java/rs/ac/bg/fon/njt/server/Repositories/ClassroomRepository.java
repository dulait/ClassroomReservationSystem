package rs.ac.bg.fon.njt.server.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.bg.fon.njt.server.Models.Classroom;

import java.util.Optional;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
}
