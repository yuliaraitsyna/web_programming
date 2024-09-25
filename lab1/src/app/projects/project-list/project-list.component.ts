import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ProjectsService } from '../projects.service';
import { Project } from '../model/Project';
import { from, Observable } from 'rxjs';

@Component({
  selector: 'app-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.css']
})

export class ProjectListComponent implements OnInit {
  projects: Project[] = [];

  constructor(
    private projectsService: ProjectsService,
    private router: Router,
  ) {}

  async ngOnInit(): Promise<void> {
    this.projects = await this.projectsService.getProjects();
  }

  onSelect(project: Project): void {
    this.router.navigate(['projects', project.id]);
  }

  async onDelete(id: number): Promise<void> {
    await this.projectsService.deleteProjectById(id);
    this.projects = await this.projectsService.getProjects();
  }

  onEdit(id: number): void {
    this.router.navigate(['/edit', id]);
  }
}
