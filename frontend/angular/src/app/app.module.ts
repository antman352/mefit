import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { MatSliderModule } from '@angular/material/slider';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field'
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatTableModule } from '@angular/material/table';

import { UserRegisterComponent } from './components/user-register/user-register.component';
import { ProfileRegisterComponent } from './components/profile-register/profile-register.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { LoginComponent } from './components/login/login.component';
import { WorkoutsComponent } from './components/workouts/workouts.component';
import { environment } from "src/environments/environment";
import { AngularFireModule } from "@angular/fire";
import { AngularFirestoreModule } from "@angular/fire/firestore";
import { AngularFireAuthModule } from '@angular/fire/auth';
import { DashboardComponent } from './components/dashboard/dashboard/dashboard.component';
import { ProgramsComponent } from './components/programs/programs/programs.component';
import { ExercisesComponent } from './components/exercises/exercises/exercises.component';
import { ProfilePageComponent } from './components/profile-page/profile-page/profile-page.component';
import { AddGoalComponent } from './components/goal/add-goal/add-goal.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';

@NgModule({
  declarations: [
    AppComponent,
    UserRegisterComponent,
    ProfileRegisterComponent,
    NavbarComponent,
    LoginComponent,
    WorkoutsComponent,
    DashboardComponent,
    ProgramsComponent,
    ExercisesComponent,
    ProfilePageComponent,
    AddGoalComponent,
    SidebarComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    MatSliderModule,
    MatCardModule,
    MatButtonModule,
    MatFormFieldModule,
    MatSelectModule,
    MatInputModule,
    MatToolbarModule,
    MatTableModule,
    MatProgressBarModule,
    AngularFireModule.initializeApp(environment.firebaseConfig),
    AngularFirestoreModule,
    AngularFireAuthModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
