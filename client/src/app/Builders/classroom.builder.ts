import { ClassroomType } from "../Enums/classroom-type";
import { Classroom } from "../Models/classroom.model";

export class ClassroomBuilder {
    private classroom: Classroom;

    constructor() {
        this.classroom = new Classroom();
    }

    withName(classroomName: string): ClassroomBuilder {
        this.classroom.classroomName = classroomName;
        return this;
    }

    ofType(classroomType: ClassroomType): ClassroomBuilder {
        this.classroom.classroomType = classroomType;
        return this;
    }

    withNumberOfSeats(numberOfSeats: number): ClassroomBuilder {
        this.classroom.numberOfSeats = numberOfSeats;
        return this;
    }

    withNumberOfComputers(numberOfComputers: number): ClassroomBuilder {
        this.classroom.numberOfComputers = numberOfComputers;
        return this;
    }

    withNote(note: string): ClassroomBuilder {
        this.classroom.note = note;
        return this;
    }

    isActive(isActive: boolean): ClassroomBuilder {
        this.classroom.isActive = isActive;
        return this;
    }

    build(): Classroom {
        return this.classroom;
    }
}