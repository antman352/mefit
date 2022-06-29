import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class WorkoutService {

  constructor(private http: HttpClient) { }

  /**
   * Method which fetches all workouts from the API with authorization bearer token.
   * @returns Workout JSON object
   */
  getAllWorkouts() {
    var token = localStorage.getItem('token');  
    return fetch("http://localhost:8080/api/v1/workouts/list",{
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer " + token
      },
    })
      .then((res) => {
        return res.json();
      })
      .catch((error) => {
        return Promise.reject(error);
      });
  }

  /**
   * Adds a workout to the database through post request to the api.
   * @param data Workout data
   * @returns Workout
   */
  addWorkout(data: any) {
    var token = localStorage.getItem('token');
    return this.http.post("http://localhost:8080/api/v1/workouts", data, {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        "Authorization": "Bearer " + token
      })
    }).toPromise()
  }
}
