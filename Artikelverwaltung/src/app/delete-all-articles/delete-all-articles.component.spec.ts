import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteAllArticlesComponent } from './delete-all-articles.component';

describe('DeleteAllArticlesComponent', () => {
  let component: DeleteAllArticlesComponent;
  let fixture: ComponentFixture<DeleteAllArticlesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeleteAllArticlesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteAllArticlesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
