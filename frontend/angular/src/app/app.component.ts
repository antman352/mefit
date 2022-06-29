import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginService } from './services/login-service/login.service';
import { Router,NavigationEnd  } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  isLoggedIn!: boolean; 
  title = 'angular';
  constructor(
    private loginService:LoginService,
    public router:Router
    ) {}
  
  checkAuth() {
    this.loginService.getAuthState().subscribe(user => {   
      if(user) {
        this.isLoggedIn = true;
      }
    })
  }

  ngOnInit() {
    this.checkAuth();
  }
}
