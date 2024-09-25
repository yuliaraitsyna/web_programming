import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProjectsService } from '../projects.service';
import { Project } from '../model/Project';

@Component({
  selector: 'app-project-details',
  templateUrl: './project-details.component.html',
  styleUrls: ['./project-details.component.css']
})
export class ProjectDetailsComponent implements OnInit {
  project: Project | null = null;

  constructor(
    private route: ActivatedRoute,
    private projectsService: ProjectsService
  ) {}

  async ngOnInit(): Promise<void> {
    this.route.paramMap.subscribe(async params => {
      const projectId = params.get('id');
      if (projectId) {
        this.project = await this.projectsService.getProjectById(+projectId);
      }
    });
  }
}
