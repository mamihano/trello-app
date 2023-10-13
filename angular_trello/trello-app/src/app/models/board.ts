import { TaskColumn } from "./task-column";

export interface Board {
    boardId?: number;
    boardName: string;
    taskColumn?: TaskColumn[];
}
