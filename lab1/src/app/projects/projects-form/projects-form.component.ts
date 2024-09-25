import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { Project } from '../model/Project';
import { ProjectsService } from '../projects.service';

@Component({
  selector: 'app-form',
  templateUrl: './projects-form.component.html',
  styleUrl: './projects-form.component.css'
})

export class ProjectsFormComponent implements OnInit{
  @ViewChild('projectForm') form!: NgForm;
  model: Project = { id: 0, name: '', description: '', customer: '', price: 0 };
  isEditing: boolean = false;

  constructor(
    private projectsService: ProjectsService,
    private router: Router,
    private route: ActivatedRoute,
  ) {}

  async ngOnInit() {
    const editedProjectId = this.route.snapshot.paramMap.get('id');
    if(editedProjectId) {
      const project = await this.projectsService.getProjectById(parseInt(editedProjectId));
      this.isEditing = true;
      if(project) {
        this.model = project;
      }
    }
    else {
      this.isEditing = false;
    }
  }

  async onSubmit(form: NgForm) {
    if(this.isEditing) {
      await this.projectsService.updateProject({id: this.model.id, ...form.value});
    }
    else {
      const id = await this.projectsService.getMaxId();
      await this.projectsService.addProject({id: id, ...form.value});
    }
    this.router.navigate(['']);
  }
  
}
