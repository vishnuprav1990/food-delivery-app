import { TestBed } from '@angular/core/testing';

import { RestautrantService } from './restautrant.service';

describe('RestautrantService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RestautrantService = TestBed.get(RestautrantService);
    expect(service).toBeTruthy();
  });
});
