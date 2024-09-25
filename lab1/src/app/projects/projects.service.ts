import { inject, Injectable } from '@angular/core';
import { Project } from './model/Project';
import { Firestore, addDoc, collection, collectionData, deleteDoc, doc, getDoc, getDocs, orderBy, query, setDoc } from '@angular/fire/firestore';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class ProjectsService {
  private db = inject(Firestore);
  private dbPath = '/list-projects';
  private projectsFire: Observable<Project[]>;

  constructor() {
    this.projectsFire = collectionData(collection(this.db, this.dbPath)) as Observable<Project[]>;
  }

  async getProjects(): Promise<Project[]> {
    return new Promise((resolve, reject) => {
      this.projectsFire.subscribe(
        projects => () => {
          console.log(projects);
          resolve(projects)
        }
        ,
        error => reject(error)
      );
    });
  }

  async addProject(project: Project): Promise<void> {
    const projectsCollection = collection(this.db, this.dbPath);
    const projectDocRef = doc(projectsCollection, project.id.toString());
    await setDoc(projectDocRef, project);
  }

  async getProjectById(id: number): Promise<Project | null> {
    const projectDoc = doc(this.db, `${this.dbPath}/${id}`);
    const docSnapshot = await getDoc(projectDoc);
    return docSnapshot.exists() ? (docSnapshot.data() as Project) : null;
  }

  async getMaxId(): Promise<number> {
    const projects = await this.getProjects();
    console.log('max id', projects.length)
    return projects.length;
  }

  async deleteProjectById(id: number): Promise<void> {
    const projectDoc = doc(this.db, `${this.dbPath}/${id}`);
    await deleteDoc(projectDoc);
  }

  async updateProject(updatedProject: Project): Promise<void> {
    const projectDoc = doc(this.db, `${this.dbPath}/${updatedProject.id}`);
    await setDoc(projectDoc, updatedProject, { merge: true });
  }
}
