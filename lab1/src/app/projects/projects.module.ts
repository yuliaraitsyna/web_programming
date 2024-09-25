import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProjectCenterComponent } from './project-center/project-center.component';
import { ProjectListComponent } from './project-list/project-list.component';
import { ProjectDetailsComponent } from './project-details/project-details.component';
import { ProjectsRoutingModule } from './projects-routing.module';
import { FormsModule } from '@angular/forms';
import { ProjectsFormComponent } from './projects-form/projects-form.component';

@NgModule({
  declarations: [
    ProjectCenterComponent,
    ProjectListComponent,
    ProjectDetailsComponent,
    ProjectsFormComponent
  ],
  imports: [
    CommonModule,
    ProjectsRoutingModule,
    FormsModule
  ],
  exports: [
    ProjectListComponent
  ]
})
export class ProjectsModule { }
