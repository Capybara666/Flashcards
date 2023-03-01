import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClientModule} from "@angular/common/http";
import { UserComponent } from './user/user.component';
import { UserListComponent } from './user/user-list/user-list.component';
import {APP_CONFIG, APP_SERVICE_CONFIG} from "./AppConfig/appconfig.service";

@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    UserListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [
    {
      provide: APP_SERVICE_CONFIG,
      useValue: APP_CONFIG
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
