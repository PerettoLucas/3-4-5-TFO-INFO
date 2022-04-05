import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MeasurementItemComponent } from './measurement-item.component';

describe('MeasurementItemComponent', () => {
  let component: MeasurementItemComponent;
  let fixture: ComponentFixture<MeasurementItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MeasurementItemComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MeasurementItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
