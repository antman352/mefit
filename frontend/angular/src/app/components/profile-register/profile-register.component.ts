import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Profile } from 'src/app/models/profile.model';
import { LoginService } from 'src/app/services/login-service/login.service';
import { ProfileService } from 'src/app/services/profile-service/profile.service';
import { UserService } from 'src/app/services/user-service/user.service';

@Component({
  selector: 'app-profile-register',
  templateUrl: './profile-register.component.html',
  styleUrls: ['./profile-register.component.scss']
})
export class ProfileRegisterComponent implements OnInit {

  constructor(private router: Router, private loginService: LoginService, private profileService: ProfileService, private userService: UserService) { }

  user: any;
  firebaseUser: any;
  userID: any;
  profile: any;

  /**
   * Model which represent profile details.
   */
  model:any = {
    weight: '',
    height: '',
    medicalConditions: '',
    disabilities: ''
  }
  error!:string;

  ngOnInit(): void {
  }

  /**
   * Fetches user ID from localstorage.
   * @returns 
   */
  getUserId() {
    this.user = JSON.parse(localStorage.getItem('user')!);
    return this.userID = this.user.uid;
  }

  /**
   * Adds a new profile into the db. 
   * Uses the profile model to represent the data.
   * Calls addProfile method from the profileservice to add the new profile and then redirect the new user to the dashboard.
   */
  registerProfile() {
    this.getUserId();
    this.profile = {
      user: {
        id: this.userID,
      },
      weight: this.model.weight,
      height: this.model.height,
      disabilities: this.model.disabilities,
      medical_conditions: this.model.medicalConditions,
    }
    this.profileService.addProfile(this.profile)
      .then(() => {
        alert("Success!");
        this.router.navigate(['/dashboard']);
      })
      .catch((error) => {
        alert("Something went wrong!");
      })
  }
}