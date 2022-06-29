export interface Exercise {
    id: number;
    name: string;
    description: string;
    targetMuscleGroup: string;
    secondaryTargetMuscleGroup: string;
    image: string;
    videoLink: string;
    workouts: [];
}