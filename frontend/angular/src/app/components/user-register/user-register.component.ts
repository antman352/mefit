import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login-service/login.service';
import { UserRegistrationService } from 'src/app/services/registration-service/user-registration.service';
import { User } from '../../models/user.model';

@Component({
  selector: 'app-user-register',
  templateUrl: './user-register.component.html',
  styleUrls: ['./user-register.component.scss']
})
export class UserRegisterComponent implements OnInit {

  constructor(
    public userRegService: UserRegistrationService, 
    public loginService: LoginService,
    private router: Router
    ){}

  model: User = {
    id: '',
    email: '',
    firstName: '',
    lastName: '',
    password: '',
    role: 'ROLE_USER',
  }

  confirmPassword:any;
  error: any;
  user: any;

  ngOnInit(): void {
  }

  /**
   * Adds user to db through userRegService. Uses user model to represent data.
   */
  addUser() {
    this.userRegService.addUser(this.model);
  }

  /**
   * Register a new user into the system.
   * Uses two methods to sign up which adds data into the firebase service, and also into the db. 
   * Also sets the accessToken from the result, which is used in the api calls.
   * If the user is successfully created, the user is dedirected to the profile screen which is mandatory to use the system.
   */
  registerUser() {
    if(this.model.password === this.confirmPassword) {
      this.userRegService.signUp(this.model.email, this.model.password)
      .then((res: any) => {
        const accessToken = res.user.Aa;
        this.model.id = res.user!.uid;
        localStorage.setItem('token',  accessToken);
      })
      .then(res => {
        this.userRegService.addUser({
        firstName: this.model.firstName,
        lastName: this.model.lastName,
        email: this.model.email,
        id: this.model.id,
        password: this.model.password,
        role: this.model.role
      });
        this.router.navigate(['/registerprofile']);
      })
      .catch(err => {
        this.error = err;
      })
    }
    else {
      this.error = 'Passwords do not match!';
    }
  }

  /**
   * Method to redirects to loginscreen
   */
  redirectLogin(): void {
		this.router.navigate(['/login']);
	}
}
