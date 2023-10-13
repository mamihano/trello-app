import { State } from "../enums/state";

export interface Card {
    cardId?: number;
    cardName: string;
    cardDescription: string;
    cardState: State;
    taskHandler: string;
}
