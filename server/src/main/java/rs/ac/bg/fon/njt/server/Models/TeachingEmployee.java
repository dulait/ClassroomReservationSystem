package rs.ac.bg.fon.njt.server.Models;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("TeachingEmployee")
public class TeachingEmployee extends Employee {

    @Column(name = "academic_title")
    private String academicTitle;

    @Column(name = "department")
    private String department;

}
