import { Employee } from "./employee.model";

export class TeachingEmployee extends Employee {
    academicTitle: string = "";
    department: string = "";

    constructor(data?: any) {
        super(data);
        if (data) {
            this.academicTitle = data.academicTitle;
            this.department = data.department;
        }
    }
}