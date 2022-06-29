import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ExerciseService {

  userData: any;

  constructor(private http: HttpClient) { }

  /**
   * Fetches all the current exercises from the db.
   * @returns List of exerices.
   */
  getAllExercises() {
    var token = localStorage.getItem('token');
    return fetch("http://localhost:8080/api/v1/exercises/list",{
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
   * Adds a new exercise into the db.
   * @param data Exercise details
   * @returns Posted exercise object into db.
   */
  addExercise(data: any) {
    var token = localStorage.getItem('token')   
    return this.http.post("http://localhost:8080/api/v1/exercises", data, {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        "Authorization": "Bearer " + token
      })
    }).toPromise()
  }
}
