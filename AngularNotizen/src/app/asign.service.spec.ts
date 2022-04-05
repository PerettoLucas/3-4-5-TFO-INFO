import { TestBed } from '@angular/core/testing';

import { AsignService } from './asign.service';

describe('AsignService', () => {
  let service: AsignService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AsignService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
