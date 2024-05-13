import { User } from "./user.model";

export class Employee extends User {
    jobTitle: string = "";

    constructor(data?: any) {
        super(data);
        if (data) {
            this.jobTitle = data.jobTitle;
        }
    }
}