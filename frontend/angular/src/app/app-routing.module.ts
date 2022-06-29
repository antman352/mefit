import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProfileRegisterComponent } from './components/profile-register/profile-register.component';
import { UserRegisterComponent } from './components/user-register/user-register.component';
import { LoginComponent } from './components/login/login.component'
import { WorkoutsComponent } from './components/workouts/workouts.component';
import { AuthGuard } from './guards/auth.guard';
import { DashboardComponent } from './components/dashboard/dashboard/dashboard.component';
import { ProgramsComponent } from './components/programs/programs/programs.component';
import { ExercisesComponent } from './components/exercises/exercises/exercises.component';
import { ProfilePageComponent } from './components/profile-page/profile-page/profile-page.component';

const routes: Routes = [
  {
    path: '', redirectTo: 'login', pathMatch: 'full'
  },
  {
    path: "registeruser",
    component: UserRegisterComponent
  },
  {
    path: "registerprofile",
    component: ProfileRegisterComponent,
    canActivate: [AuthGuard]
  },
  {
    path: "login",
    component: LoginComponent,
  },
  {
    path: "workouts",
    component: WorkoutsComponent,
    canActivate: [AuthGuard]
  },
  {
    path: "dashboard",
    component: DashboardComponent,
    canActivate: [AuthGuard]
  },
  {
    path: "programs",
    component: ProgramsComponent,
    canActivate: [AuthGuard]
  },
  {
    path: "exercises",
    component: ExercisesComponent,
    canActivate: [AuthGuard]
  },
  {
    path: "profile",
    component: ProfilePageComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
