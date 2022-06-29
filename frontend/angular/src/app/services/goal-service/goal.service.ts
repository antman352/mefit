import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { Goal } from 'src/app/models/goal.model';

@Injectable({
  providedIn: 'root'
})
export class GoalService {

  constructor(private http: HttpClient) { }

  /**
   * Adds new goal to DB
   * @param data goal information
   * @returns New goal object connected to the logged in profile.
   */
  addGoal(data: any) {
    var token = localStorage.getItem('token');
    return this.http.post("http://localhost:8080/api/v1/goals", data, {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        "Authorization": "Bearer " + token
      })
    })
  }

  /**
   * Fetches all the goals from db.
   * @returns Goal List observable.
   */
  getGoal():Observable<Goal[]> {
    const token = localStorage.getItem('token');
    return this.http.get<Goal[]>("http://localhost:8080/api/v1/goals/list/", {
      headers: new HttpHeaders({
        "Authorization": "Bearer " + token
      })
    }).pipe()
  }

  /**
   * Patches a goal object in db.
   * @param goal Goal details with achieved variable.
   * @returns Updated goal object.
   */
  updateGoal(goal: any) {
    let token = localStorage.getItem('token');   
    let achieved = {
      "achieved": true
    }
    return this.http.patch("http://localhost:8080/api/v1/goals/" + goal.id, JSON.stringify(achieved), {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        "Authorization": "Bearer " + token
      })
    })
  }
}
