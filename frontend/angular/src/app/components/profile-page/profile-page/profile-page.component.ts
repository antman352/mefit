import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login-service/login.service';
import { ProfileService } from 'src/app/services/profile-service/profile.service';
import { UserService } from 'src/app/services/user-service/user.service';

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.scss']
})
export class ProfilePageComponent implements OnInit {
  email: any;
  password: any;
  weight: any;
  height: any;
  medical_conditions: any;
  disabilities: any;

  profile: any;
  user: any;
  userID: any;
  firebaseUser: any;
  profileID: any;

  constructor(private profileService: ProfileService, private userService: UserService, private loginService: LoginService) { }

  ngOnInit(): void {
    this.loadUserId();
  }

  //Loads to user from firebase to get the user if it exists.
  async loadUserId() {
    await this.loginService.getFirebaseUser().then(res => {
      this.firebaseUser = res
      this.userID = this.firebaseUser.uid
      this.userService.getUser(this.userID).subscribe(res => {
        this.user = res;
        this.profileID = this.user.profile.id;
        
        this.getProfile(this.profileID);
      })
    })
  }

  //Gets the profile for the specific user.
  getProfile(data: any) {
    this.profileService.getProfile(data).subscribe(res => {
      this.profile = res;
    })
  }

  onSubmit(profileData: any) {
    
  }
}
