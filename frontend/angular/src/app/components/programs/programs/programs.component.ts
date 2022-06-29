import { Component, OnInit } from '@angular/core';
import { Program } from 'src/app/models/program.model';
import { ProgramService } from 'src/app/services/program-service/program.service';
import {animate, state, style, transition, trigger} from '@angular/animations';

@Component({
  selector: 'app-programs',
  templateUrl: './programs.component.html',
  styleUrls: ['./programs.component.scss'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class ProgramsComponent implements OnInit {

  public dataSource: Program[] = [];
  displayedColumns = ['name', 'category'];
  expandedElement!: Program | null;

  constructor(private programService: ProgramService) { }

  ngOnInit(): void {
    this.loadPrograms();
  }

  /**
   * Function which fetches all the current programs from us through the programservice.
   */
  async loadPrograms() {
    this.dataSource = await this.programService.getAllPrograms();
  }
}
