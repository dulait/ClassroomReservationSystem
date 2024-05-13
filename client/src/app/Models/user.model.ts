import { UserType } from "../Enums/user-type";

export class User {
    id: number = 0;
    firstName: string = "";
    lastName: string = "";
    email: string = "";
    userType: UserType = UserType.NotRegistered;

    constructor(data?: any) {
        if (data) {
            this.id = data.id;
            this.firstName = data.firstName;
            this.lastName = data.lastName;
            this.email = data.email;
            this.userType = data.userType;
        }
    }
}