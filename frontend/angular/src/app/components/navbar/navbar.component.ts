import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user.model';
import { LoginService } from 'src/app/services/login-service/login.service';
import { UserRegistrationService } from 'src/app/services/registration-service/user-registration.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  public isContributor: boolean = false; 
  public isAdmin: boolean = false;

  constructor(private readonly userService: UserRegistrationService, public loginService: LoginService) { }

  ngOnInit(): void {
    //this.userService.fetchUser();
  }

  /*get users(): User[] {
    return this.userService.getUsers();
  }*/

  logoutUser() {
    this.loginService.SignOut();
  }
}
