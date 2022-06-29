import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from 'src/app/models/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getUserToken() {
    return localStorage.getItem('firebaseID');
  }

  /**
   * Fetches a user by ID from the database.
   * @param userid 
   * @returns 
   */
  getUser(userid:any): Observable<User> {
    var token = localStorage.getItem('token');
    return this.http.get<User>("http://localhost:8080/api/v1/users/" + userid, {
      headers: new HttpHeaders({
          "Authorization": "Bearer " + token
      })
    }).pipe();
  }
}
