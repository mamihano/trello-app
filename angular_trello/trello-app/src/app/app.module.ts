import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { MenuComponent } from './menu/menu.component';
import { TaskColumnComponent } from './task-column/task-column.component';
import { CardComponent } from './card/card.component';
import { ColumnBoardComponent } from './column-board/column-board.component';

@NgModule({
  declarations: [
    AppComponent,
    ColumnBoardComponent
    ],
  imports: [
    BrowserModule,
    CardComponent,
    TaskColumnComponent,
    MenuComponent,
    HeaderComponent,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
