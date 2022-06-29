import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login-service/login.service';
import { GoalService } from 'src/app/services/goal-service/goal.service';
import { ProfileService } from 'src/app/services/profile-service/profile.service';
import { UserService } from 'src/app/services/user-service/user.service';
import { ProgramService } from 'src/app/services/program-service/program.service';
import { Program } from 'src/app/models/program.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Workout } from 'src/app/models/workout.model';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  activeGoal: any | null;
  user: any; 

  userID: any;
  firebaseUser: any;
  public profile: any;
  
  progresValue: number;
  rangeArray: number[];
  weekday!: string;
  month!: string;
  date!: string;
  programDetailsOpen: boolean = false;
  completedGoals: any[] = [];
  completedGoalDetailsOpen: any[] = [];

  public programsArray: Program[] = [];
  public activeProgram: any;
  public clickedWorkout: any;
  public completeWorkouts: any[] = [];

  goal: any;
  goalEndDate: any;

  constructor(
    public loginService: LoginService, 
    public goalService: GoalService,
    public profileService: ProfileService,
    public userService: UserService,
    public programService: ProgramService,
    private http: HttpClient
    ) {
    this.progresValue = 0;
    this.rangeArray = new Array(100);
  }

  //Checks for active goal and completedgoals in the database and sets those values on refresh
  checkForActiveGoal() {
    this.user.profile.goals.forEach((goal: { achieved: boolean; }) => {
      
      if(goal.achieved === false) {
        this.activeGoal = goal;
      }
      else {
        this.completedGoals.push(goal);
      }
      if(this.activeGoal != null) {
        this.loadActiveGoalWorkouts(); 
      } 
    })
  }
  //Sets active program 
  setActiveProgram(program:any) {
    this.activeProgram = program;
  }

  //Model for the goal 
  addNewGoal(data: any) {  
    this.goal = {
      program: {
        id: this.activeProgram.id 
      },
      profile: {
      id:this.user.profile.id
    },
      startDate: `${this.date}/${this.month}`,
      endDate: this.goalEndDate
    }
    //Function that adds a new goal and sets it to active goal. 
    this.goalService.addGoal(this.goal)
    .subscribe((res) => {
      this.completedGoals = [];
      this.activeGoal = res;
      this.loadUserId();
      alert("Success!")
    })
  }

  //Loads in the user and all credaintials to the local state.
  async loadUserId() {
    await this.loginService.getFirebaseUser().then(res => {
      this.firebaseUser = res
      this.userID = this.firebaseUser.uid
      this.userService.getUser(this.userID).subscribe(res => {
        this.user = res;
        this.checkForActiveGoal();
        this.loadPrograms();
      })
    })
  }
  
  async loadUser() {
    await this.userService.getUser(this.userID).subscribe(res => {
      this.user = res;
    })
  }

  async loadPrograms() {
    this.programsArray = await this.programService.getAllPrograms();
  }

  //Function that loads completed workouts and the progresbar when refreshing the website or when logging in. 
  loadActiveGoalWorkouts() {
    this.progresValue = 0;
    this.completeWorkouts = [];
    let workoutArray = this.activeGoal.program.workouts;

    for (let i = 0; i < workoutArray.length; i++) {
      for (let j = 0; j < workoutArray[i].workoutsCompleted.length; j++) {
        if (workoutArray[i].workoutsCompleted[j].goal === this.activeGoal.id) {
          this.completeWorkouts.push(workoutArray[i].workoutsCompleted[j]);
          if (workoutArray[i].workoutsCompleted[j].completed === true) { 
            this.progresValue = this.progresValue + (100 / this.activeGoal.program.workouts.length);
          }
        }
      }
    }
  }

  //Function that holds the information of the current date.
  public getCurrentDate() {
    const today = new Date();
    this.weekday = today.toLocaleString('en-US', { weekday:'long' });
    this.month = today.toLocaleString('en-US', { month: 'long' });
    this.date = String(today.getDate()).padStart(2, '0');
  }

  toggleProgramDetails() {
    this.programDetailsOpen = !this.programDetailsOpen;
  }

  toggleCompletedGoalDetails(goalIndex:number) {
    this.completedGoalDetailsOpen[goalIndex] = !this.completedGoalDetailsOpen[goalIndex];
  }

  //Function that executes everytime the completebutton for the workouts in the active goal is pressed.
  //It patches the change of the workout from false to true when the workout is complete.
  completedWorkouts(workout: any) { 
    var token = localStorage.getItem('token');
    let completedWorkout : any;
    let completedFalse = {"completed": false };
    let completedTrue = { "completed": true };

    this.getAllCompletedWorkouts(this.activeGoal.id).subscribe(completedWorkouts => {
      completedWorkouts.map((w: any) => { // maps all workouts to see which one that were pressed to change that state
        if (w.workout === workout.id) {
          completedWorkout = w;
          if (completedWorkout.completed === false) { //Sets the workout_complete from false to true
            this.http.patch("http://localhost:8080/api/v1/completes/" + completedWorkout.id, JSON.stringify(completedTrue), {
              headers: new HttpHeaders({
                "Content-Type": "application/json",
                "Authorization": "Bearer " + token
              })
            }).subscribe(res => {
              this.progresValue = this.progresValue + (100 / this.activeGoal.program.workouts.length);
              for(let i = 0; i<this.completeWorkouts.length; i++) {  
                if(this.completeWorkouts[i].id === completedWorkout.id) { // Updates the progresvalue.
                  this.completeWorkouts[i].completed = true;
                }
              }
            })
          }
          else {
            this.http.patch("http://localhost:8080/api/v1/completes/" + completedWorkout.id, JSON.stringify(completedFalse), {
              headers: new HttpHeaders({
                "Content-Type": "application/json",
                "Authorization": "Bearer " + token
              })
            }).subscribe(res => {
              this.progresValue = this.progresValue - (100 / this.activeGoal.program.workouts.length);
            })
          }
        }
      })
    })
  }

  //Function that gets all the completed workouts in the database
  getAllCompletedWorkouts(id: number) {
    const token = localStorage.getItem('token');
    return this.http.get<any>("http://localhost:8080/api/v1/goals/completed/" + id, {
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer " + token
      },
    })
  }


  //sets the active goal to completed
  completeGoal() {
    this.goalService.updateGoal(this.activeGoal).subscribe(res => {
      this.completedGoals.push(this.activeGoal);
      this.activeGoal = null;
    })
  }
  //Gets the information about the clicked workout inside the activegoal.
  getClickedWorkout(id: number) {
    const token = localStorage.getItem('token');
    return this.http.get<Workout>("http://localhost:8080/api/v1/workouts/" + id, {
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer " + token
      },
    })
  }

  ngOnInit(): void {
    this.getCurrentDate();
    this.loadUserId();
  }
}
