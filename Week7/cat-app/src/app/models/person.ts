import { Role } from "./role";
import { Cat } from "./cat";

export class Person {
    id: number;
    username: string;
    password: string;
    cats: Cat[];
    role: Role;
}
