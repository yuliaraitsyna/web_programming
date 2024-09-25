import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProjectListComponent } from './project-list/project-list.component';
import { ProjectDetailsComponent } from './project-details/project-details.component';
import { ProjectCenterComponent } from './project-center/project-center.component';

const routes: Routes = [
  { path: '',
    component: ProjectCenterComponent,
    children: [
      {
        path: '',
        component: ProjectListComponent,
        children: [
          {
            path: ':id',
            component: ProjectDetailsComponent
          }
        ]
      },
    ]},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProjectsRoutingModule { }
