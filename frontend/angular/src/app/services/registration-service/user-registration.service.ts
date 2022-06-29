import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import firebase from 'firebase/app';
import { AngularFireAuth } from "@angular/fire/auth";

@Injectable({
  providedIn: 'root'
})
export class UserRegistrationService {
  
  userData: any;

  constructor(
    private readonly http: HttpClient, 
    public afAuth: AngularFireAuth
    ) { 

    this.afAuth.authState.subscribe(user => {
      if (user) {
        this.userData = user;
        localStorage.setItem('user', JSON.stringify(this.userData));
        JSON.parse(localStorage.getItem('user')!);
      } else {
        localStorage.setItem('user', null!);
        JSON.parse(localStorage.getItem('user')!);
      }
    })
  }

  /**
   * Signs up a user in firebase service.
   * @param email 
   * @param password 
   * @returns User firebase object
   */
  signUp(email: string, password: string) {
    return this.afAuth.createUserWithEmailAndPassword(email, password)
  }

  /**
   * Adds user to database. 
   * @param data Auto generated ID which represent firebase UID and first name and last name.
   * @returns new user object
   */
  addUser(data: any) {
    var token = localStorage.getItem('token');
    
    return this.http.post("http://localhost:8080/api/v1/users", data, {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        "Authorization": "Bearer " + token
      })
    }).toPromise()
  }
}