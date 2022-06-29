import { Injectable, NgZone } from '@angular/core';
import firebase from 'firebase/app';
import { AngularFireAuth } from "@angular/fire/auth";
import { AngularFirestore } from '@angular/fire/firestore';
import { Router } from "@angular/router";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  userData: any; // Save logged in user data

  constructor(
    public afs: AngularFirestore,   // Inject Firestore service
    public afAuth: AngularFireAuth, // Inject Firebase auth service
    public router: Router,  
    public ngZone: NgZone, // NgZone service to remove outside scope warning
    public http: HttpClient
  ) {    
    /* Saving user data in localstorage when 
    logged in and setting up null when logged out */
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
   * Retrieves information from firebase, regarding the userID which stores in on the firebase side. 
   * This information is used in frontend to fetch the logged in user. 
   * @returns Firebase user
   */
  getFirebaseUser() {
    var token = localStorage.getItem('token');
    return this.http.post("http://localhost:8080/api/v1/session/me",{}, {
        headers: new HttpHeaders({
          "Content-Type": "application/json",
          "Authorization": "Bearer " + token
        })
    }).toPromise();
  }

  getAuthState():Observable<firebase.User | null> {
    return this.afAuth.authState;
  }

  getToken():string {
    return this.userData.Aa
  }

  // Sign in with email/password
  SignIn(email:string, password:string):Promise<firebase.auth.UserCredential> {
    return this.afAuth.signInWithEmailAndPassword(email, password)
  }

  // Sign up with email/password
  SignUp(email:string, password:string):Promise<firebase.auth.UserCredential> {
    return this.afAuth.createUserWithEmailAndPassword(email, password)
  }

  // Send email verfificaiton when new user sign up
  SendVerificationMail():Promise<void> {
    return this.afAuth.currentUser.then(u => u!.sendEmailVerification())
    
  }

  // Reset Forgot password
  ForgotPassword(passwordResetEmail:string):Promise<void> {
    return this.afAuth.sendPasswordResetEmail(passwordResetEmail)
    .then(() => {
      window.alert('Password reset email sent, check your inbox.');
    }).catch((error) => {
      window.alert(error)
    })
  }

  // Returns true when user is looged in and email is verified
  get isLoggedIn(): boolean {
    const user = JSON.parse(localStorage.getItem('user')!);
    return (user !== null && user.emailVerified !== false) ? true : false;
  }

  // Sign in with Google
  GoogleAuth():Promise<void> {
    return this.AuthLogin(new firebase.auth.GoogleAuthProvider());
  }

  // Auth logic to run auth providers
  AuthLogin(provider:any):Promise<void> {
    return this.afAuth.signInWithPopup(provider)
    .then((result) => {
       this.ngZone.run(() => {
          this.router.navigate(['dashboard']);
        })
    }).catch((error) => {
      window.alert(error)
    })
  }

  /**
   * Using the authservice from firebase, signs out the user and also removes from localstorage. 
   * Navigating back to the login screen.
   * @returns Sign out user
   */
  SignOut() {
    return this.afAuth.signOut().then(() => {
      localStorage.removeItem('user');
      this.router.navigate(['login']);
    })
  }
}
