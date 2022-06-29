import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth-service/auth.service';
import { LoginService } from '../services/login-service/login.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private router: Router, public authService: AuthService, private loginService: LoginService){}

  /**
   * Method for Angular auth. Protects certain pages from unauthorized users.
   * @param route 
   * @param state 
   * @returns 
   */
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
  
    if(this.loginService.userData || localStorage.getItem('user')) {
      return true;
    }
    else {
      this.router.navigate(['/login'])
      return false;
    }
  }
}
