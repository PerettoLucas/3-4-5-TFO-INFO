import { TestBed } from '@angular/core/testing';

import { BookStoreServiceService } from './book-store-service.service';

describe('BookStoreServiceService', () => {
  let service: BookStoreServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BookStoreServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
