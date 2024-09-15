import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProjectsCenterComponent } from './projects-center/projects-center.component';
import { ProjectsListComponent } from './projects-list/projects-list.component';
import { ProjectsDetailsComponent } from './projects-details/projects-details.component';



@NgModule({
  declarations: [
    ProjectsCenterComponent,
    ProjectsListComponent,
    ProjectsDetailsComponent
  ],
  imports: [
    CommonModule
  ]
})
export class ProjectsModule { }
