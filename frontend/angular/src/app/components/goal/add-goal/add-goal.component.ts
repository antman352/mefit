import { Component, OnInit } from '@angular/core';
import { GoalService } from 'src/app/services/goal-service/goal.service';

@Component({
  selector: 'app-add-goal',
  templateUrl: './add-goal.component.html',
  styleUrls: ['./add-goal.component.scss']
})
export class AddGoalComponent implements OnInit {

  endDate: any;

  constructor(private goalService: GoalService) { }

  ngOnInit(): void {
  }
  addGoal(data: any) {
    
  }

}
