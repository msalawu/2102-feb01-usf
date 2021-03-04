import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewcatsComponent } from './viewcats.component';

describe('ViewcatsComponent', () => {
  let component: ViewcatsComponent;
  let fixture: ComponentFixture<ViewcatsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewcatsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewcatsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
