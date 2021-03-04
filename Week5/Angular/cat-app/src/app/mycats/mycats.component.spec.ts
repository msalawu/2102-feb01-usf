import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MycatsComponent } from './mycats.component';

describe('MycatsComponent', () => {
  let component: MycatsComponent;
  let fixture: ComponentFixture<MycatsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MycatsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MycatsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
