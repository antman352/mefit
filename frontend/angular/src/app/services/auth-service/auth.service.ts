import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private router: Router) { }
  
  /**
   * Checker to see localstorage for an user, else redirect to login screen.
   */
  get isLoggedIn(): boolean {
    if(localStorage.getItem('user') != null) {
      return true;
    } else {
      this.router.navigate(['/login'])
      return false;
    }
  }
}
