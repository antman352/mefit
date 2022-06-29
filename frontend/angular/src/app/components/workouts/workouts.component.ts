import { Component, OnInit } from '@angular/core';
import { WorkoutService } from 'src/app/services/workout-service/workout.service';
import { Workout } from 'src/app/models/workout.model';
import { LoginService } from 'src/app/services/login-service/login.service';
import {animate, state, style, transition, trigger} from '@angular/animations';
interface Transaction {
  item: string;
  cost: number;
}

@Component({
  selector: 'app-workouts',
  templateUrl: './workouts.component.html',
  styleUrls: ['./workouts.component.scss'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})

export class WorkoutsComponent implements OnInit {

  public dataSource: Workout[] = []; //Represent JSON object
  displayedColumns = ['name', 'type'];
  expandedElement!: Workout | null;
  /** Gets the total cost of all transactions. */

  constructor(private workoutService: WorkoutService, public loginService: LoginService) { }

  ngOnInit(): void {
    this.loadWorkouts();
  }

  /**
   * Fetches all the workouts through the workoutservice and present the data in workouts page.
   */
  async loadWorkouts() {
    this.dataSource = await this.workoutService.getAllWorkouts();
  }
}
