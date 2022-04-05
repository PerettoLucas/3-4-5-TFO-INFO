import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookStoreServiceTestComponentComponent } from './book-store-service-test-component.component';

describe('BookStoreServiceTestComponentComponent', () => {
  let component: BookStoreServiceTestComponentComponent;
  let fixture: ComponentFixture<BookStoreServiceTestComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BookStoreServiceTestComponentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BookStoreServiceTestComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
