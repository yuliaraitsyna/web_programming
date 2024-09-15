import { Component } from '@angular/core';
import { Project } from '../model/Project';
import { Router } from '@angular/router';
import { ProjectsService } from '../services/projects.service';

@Component({
  selector: 'app-projects-list',
  templateUrl: './projects-list.component.html',
  styleUrl: './projects-list.component.css'
})

export class ProjectsListComponent {
  projects: Project[] = [];
  selectedProject: Project | null = null;

  constructor(
    private projectsService: ProjectsService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.projects = this.projectsService.getProjects();
  }

  onSelect(project: Project): void {
    this.selectedProject = project;
    this.router.navigate(['', project.id]);
  }

  onDelete(id: number) {
    this.projectsService.deleteProjectById(id);
  }

  onEdit(id: number) {
    this.router.navigate(['/edit', id]);
  }

}
