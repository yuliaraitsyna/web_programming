import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProjectsCenterComponent } from './projects/projects-center/projects-center.component';
import { ProjectsListComponent } from './projects/projects-list/projects-list.component';
import { ProjectsDetailsComponent } from './projects/projects-details/projects-details.component';

const routes: Routes = [
  {
    path: '',
    component: ProjectsCenterComponent,
    children: [{
      path: '',
      component: ProjectsListComponent,
      children: [{
        path: ':id',
        component: ProjectsDetailsComponent
      }]
    }]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
