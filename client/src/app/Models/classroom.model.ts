import { ClassroomType } from "../Enums/classroom-type";

export class Classroom {
    id: number = 0;
    classroomName: string = "";
    note: string = "";
    classroomType: ClassroomType = ClassroomType.ComputerRoom;
    numberOfSeats: number = 0;
    numberOfComputers: number = 0;
    isActive: boolean = false;
}