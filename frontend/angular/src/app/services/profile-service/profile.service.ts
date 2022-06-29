import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Profile } from 'src/app/models/profile.model';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  constructor(private http: HttpClient) { }

  /**
   * Adds new profile to DB
   * @param data Profile information
   * @returns New profile object connected to the logged in user.
   */
  addProfile(data: any) {
    var token = localStorage.getItem('token');
    
    return this.http.post("http://localhost:8080/api/v1/profiles", data, {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        "Authorization": "Bearer " + token
      })
    }).toPromise()
  }

  /**
   * Fetches a profile connected to a specific user.
   * @param user UserID credentials, in this case Firebase UID.
   * @returns Profile object.
   */
  getProfile(user:any): Observable<Profile> {
    var token = localStorage.getItem('token');
    
    return this.http.get<Profile>("http://localhost:8080/api/v1/profiles/" + user, {
      headers: new HttpHeaders({
        "Authorization": "Bearer " + token
      })
    }).pipe();
  }
}
