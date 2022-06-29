import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Program } from 'src/app/models/program.model';

@Injectable({
  providedIn: 'root'
})
export class ProgramService {

  constructor(private http: HttpClient) { }

  /**
   * Fetches all programs from the DB.
   * @returns List of programs from DB.
   */
  getAllPrograms() {
    var token = localStorage.getItem('token');
    return fetch("http://localhost:8080/api/v1/programs/list",{
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
   * Fetches programs from DB.
   * @returns 
   */
  getProgram(): Observable<Program> {
    var token = localStorage.getItem('token');
    return this.http.get<Program>("http://localhost:8080/api/v1/programs/list", {
      headers: new HttpHeaders({
        "Authorization": "Bearer " + token
      })
    }).pipe()
  }

  /**
   * Posts a new program to the DB. 
   * @param data Program object
   * @returns New program object.
   */
  addProgram(data: any) {
    var token = localStorage.getItem('token') 
    return this.http.post("http://localhost:8080/api/v1/programs", data, {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        "Authorization": "Bearer " + token
      })
    }).toPromise()
  }
}
