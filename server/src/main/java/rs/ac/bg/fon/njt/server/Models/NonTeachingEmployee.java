package rs.ac.bg.fon.njt.server.Models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("NonTeachingEmployee")
public class NonTeachingEmployee extends Employee {
}
