import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GenerateAllArticlesNewComponent } from './generate-all-articles-new.component';

describe('GenerateAllArticlesNewComponent', () => {
  let component: GenerateAllArticlesNewComponent;
  let fixture: ComponentFixture<GenerateAllArticlesNewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GenerateAllArticlesNewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GenerateAllArticlesNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
