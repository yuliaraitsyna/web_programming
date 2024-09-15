import { Injectable } from '@angular/core';
import { mockProjects } from '../model/mock-project-list';
import { Project } from '../model/Project';

@Injectable({
  providedIn: 'root'
})
export class ProjectsService {
  private projects = mockProjects;

  getProjects() {
    return this.projects;
  }

  addProject(project: Project): void {
    this.projects.push(project);
  }

  getProjectById(id: number): Project | null {
    return this.projects.find((project) => project.id === id) || null;
  }

  getMaxId(): number {
    return this.projects.length;
  }

  deleteProjectById(id: number): void {
    this.projects.splice(id, 1);
    this.projects.forEach((project, index) => {
      project.id = index;
    })
  }

  updateProject(updatedProject: Project): void {
    const index = this.projects.findIndex((p) => p.id === updatedProject.id);
    this.projects[index] = updatedProject;
  }
}
