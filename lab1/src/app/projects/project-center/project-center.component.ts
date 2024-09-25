import { Component } from '@angular/core';
import { Project } from '../model/Project';
import { ProjectsService } from '../projects.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-project-center',
  templateUrl: './project-center.component.html',
  styleUrl: './project-center.component.css'
})
export class ProjectCenterComponent {
  projects: Project[] = [];

  constructor(private projectsService: ProjectsService, private router: Router){}

  async ngOnInit(): Promise<void> {
    this.projects = await this.projectsService.getProjects();
  }

  onAddProject(): void {
    this.router.navigate(['/add']);
  }
}
