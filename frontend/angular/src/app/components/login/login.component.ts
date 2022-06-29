import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user-service/user.service';
import { LoginService } from '../../services/login-service/login.service';

@Component({
	selector: 'login-user',
	templateUrl: './login.component.html',
	styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  email: any;
  password: any;
  error: any;
  userID: any;
  user: any;

  constructor(public loginService: LoginService, private router: Router, private userService: UserService) { }

  ngOnInit(): void {
  }

  fetchUserId(token:any) {
    this.loginService.getFirebaseUser().then(res => {
    })
  }
  //If a user wants to register it redirects to registeruser
  redirectRegistration(): void {
    this.router.navigate(['/registeruser']);
  }

  //Login fucntion that checks if the user exist or not. If it do it redirects to dashboard
  logInUser() {
    this.loginService.SignIn(this.email, this.password)
      .then((result: any) => {
        const accessToken = result.user.Aa;
        
        localStorage.setItem('token', accessToken);
        this.fetchUserId(result.user.Aa);
        this.backendLogin(result, result.user.email);

        this.userID = result.user.uid;
        this.userService.getUser(this.userID).subscribe(res => {
          this.user = res;
          if (this.user.profile === null) {
            this.loginService.ngZone.run(() => {
              this.loginService.router.navigate(['/registerprofile']);
            });
          }
          else {
            this.loginService.router.navigate(['/dashboard']);
          }
        })
      }).catch((error: any) => {
        window.alert(error.message)
      })
  }

  //Login in to autenticate with the firebase.
  backendLogin(result:any, email:any) {
    return fetch("http://localhost:8080/api/v1/session/login",{
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer " + result.user.Aa,
      },
      body: JSON.stringify({ 
        email: email
      }),
    })
      .then((res) => {
        return Promise.resolve({ success: true });
      })
      .catch((error) => {
        return Promise.reject(error);
      });
  }
}