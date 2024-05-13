import { User } from "./user.model";

export class Student extends User {

    reasonForAccess: string = "";

    constructor(data?: any) {
        super(data);
        if (data) {
            this.reasonForAccess = data.reasonForAccess;
        }
    }

}