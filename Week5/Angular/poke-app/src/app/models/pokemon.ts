import { Move } from './move';

export class Pokemon {
    id: number;
    name: string;
    sprites: {};
    abilities: Array<{}>;
    types: Array<{}>;
    moves: Array<Move>;
}
