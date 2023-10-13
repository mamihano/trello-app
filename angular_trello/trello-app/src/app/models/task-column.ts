import { State } from "../enums/state";
import { Board } from "./board";
import { Card } from "./card";

export interface TaskColumn {
    columnId?: number;
    columnName: string;
    columnState: State;
    board?: Board;
    cards?: Card[];
}
