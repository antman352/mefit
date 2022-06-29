import { Component, OnInit } from '@angular/core';
import { Exercise } from 'src/app/models/exercise.model';
import { ExerciseService } from 'src/app/services/exercise-service/exercise.service';
import {animate, state, style, transition, trigger} from '@angular/animations';

@Component({
  selector: 'app-exercises',
  templateUrl: './exercises.component.html',
  styleUrls: ['./exercises.component.scss'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})

export class ExercisesComponent implements OnInit {

  public dataSource: Exercise[] = [];
  columnsToDisplay = ['name', 'primaryTargetMuscleGroup', 'secondaryTargetMuscleGroup'];
  expandedElement!: Exercise | null;
  name = ['Name', 'Primary Musclegroup','Secondary Musclegroup'];

  constructor(private exerciseService: ExerciseService) { }

  ngOnInit(): void {
    this.loadExercises();
  }

  /**
   * Fetches all the exercises from the database through the exerciseservice.
   */
  async loadExercises() {
    this.dataSource = await this.exerciseService.getAllExercises();
  }
}

