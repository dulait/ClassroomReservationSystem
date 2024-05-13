package rs.ac.bg.fon.njt.server.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.bg.fon.njt.server.Enums.ClassroomType;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "classroom_name")
    private String classroomName;

    @Column(name = "note")
    private String note;

    @Column(name = "classroom_type")
    @Enumerated(EnumType.STRING)
    private ClassroomType classroomType;

    @Column(name = "number_of_seats")
    private int numberOfSeats;

    @Column(name = "number_of_computers")
    private int numberOfComputers;

    @Column(name = "is_active")
    private boolean isActive;

}
