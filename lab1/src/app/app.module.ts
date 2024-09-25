import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { initializeApp, provideFirebaseApp } from '@angular/fire/app';
import { getFirestore, provideFirestore } from '@angular/fire/firestore';
import { ProjectsModule } from './projects/projects.module';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ProjectsModule,
    FormsModule
  ],
  providers: [
    provideFirebaseApp(() => initializeApp({"projectId":"angular-project-bsu","appId":"1:591139877636:web:c827fc6a553d4b47c7d651","storageBucket":"angular-bsu.appspot.com","apiKey":"AIzaSyCk0cAYLp56olSKPBtwpT2ICwbpRvL1hGc","authDomain":"angular-bsu.firebaseapp.com","messagingSenderId":"744235381465"})),
    provideFirestore(() => getFirestore())
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
