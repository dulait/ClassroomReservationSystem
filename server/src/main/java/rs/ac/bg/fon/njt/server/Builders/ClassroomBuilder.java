package rs.ac.bg.fon.njt.server.Builders;

import org.springframework.stereotype.Component;
import rs.ac.bg.fon.njt.server.Enums.ClassroomType;
import rs.ac.bg.fon.njt.server.Models.Classroom;

// classroom builder with fluent api
@Component
public class ClassroomBuilder {

    private final Classroom classroom = new Classroom();

    public ClassroomBuilder ofType(ClassroomType type) {
        classroom.setClassroomType(type);
        return this;
    }

    public ClassroomBuilder withName(String classroomName) {
        classroom.setClassroomName(classroomName);
        return this;
    }

    public ClassroomBuilder withNumberOfSeats(int numberOfSeats) {
        classroom.setNumberOfSeats(numberOfSeats);
        return this;
    }

    public ClassroomBuilder withNumberOfComputers(int numberOfComputers) {
        classroom.setNumberOfComputers(numberOfComputers);
        return this;
    }

    public ClassroomBuilder withNote(String note) {
        classroom.setNote(note);
        return this;
    }

    public ClassroomBuilder isActive(boolean isActive) {
        classroom.setActive(isActive);
        return this;
    }

    public Classroom build() {
        if (classroom.getClassroomType() != ClassroomType.ComputerRoom && classroom.getClassroomType() != ClassroomType.Combined) {
            classroom.setNumberOfComputers(0);
        }

        return classroom;
    }

}
