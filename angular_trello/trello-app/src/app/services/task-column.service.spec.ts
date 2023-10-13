import { TestBed } from '@angular/core/testing';

import { TaskColumnService } from './task-column.service';

describe('TaskColumnService', () => {
  let service: TaskColumnService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TaskColumnService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
