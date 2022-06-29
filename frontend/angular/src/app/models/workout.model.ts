export interface Workout {
    id: number; 
    name: string;
    type: string;
    complete: boolean;
    programs: [];
    goals: [];
    exercises: [];
}