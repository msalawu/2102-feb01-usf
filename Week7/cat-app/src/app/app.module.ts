import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './navbar/navbar.component';
import { MyCatsComponent } from './mycats/mycats.component';
import { ViewCatsComponent } from './viewcats/viewcats.component';
import { ManageComponent } from './manage/manage.component';
import { CatComponent } from './cat/cat.component';
import { FormsModule } from '@angular/forms';

import { CatService } from './services/cat.service';
import { UserService } from './services/user.service';
import { AdminService } from './services/admin.service';
import { UrlService } from './services/url.service';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavbarComponent,
    MyCatsComponent,
    ViewCatsComponent,
    ManageComponent,
    CatComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    CatService,
    UserService,
    AdminService,
    UrlService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
