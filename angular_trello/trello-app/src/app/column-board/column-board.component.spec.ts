import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ColumnBoardComponent } from './column-board.component';

describe('ColumnBoardComponent', () => {
  let component: ColumnBoardComponent;
  let fixture: ComponentFixture<ColumnBoardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ColumnBoardComponent]
    });
    fixture = TestBed.createComponent(ColumnBoardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
