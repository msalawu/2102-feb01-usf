import { Breed } from "./breed";
import { Status } from "./status";
import { SpecialNeed } from "./special-need";

export class Cat {
    id: number;
    name: string;
    age: number;
    breed: Breed;
    status: Status;
    specialNeeds: SpecialNeed[];
}
