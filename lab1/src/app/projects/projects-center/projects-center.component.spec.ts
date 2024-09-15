import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjectsCenterComponent } from './projects-center.component';

describe('ProjectsCenterComponent', () => {
  let component: ProjectsCenterComponent;
  let fixture: ComponentFixture<ProjectsCenterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ProjectsCenterComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProjectsCenterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
