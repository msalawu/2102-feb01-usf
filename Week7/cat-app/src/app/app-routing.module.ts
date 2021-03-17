import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { MyCatsComponent } from './mycats/mycats.component';
import { ViewCatsComponent } from './viewcats/viewcats.component';
import { ManageComponent } from './manage/manage.component';

const routes: Routes = [
  {
    path:'',
    component:HomeComponent
  },
  {
    path:'home',
    component:HomeComponent
  },
  {
    path:'mycats',
    component:MyCatsComponent
  },
  {
    path:'viewcats',
    component:ViewCatsComponent
  },
  {
    path:'cats',
    component:ViewCatsComponent
  },
  {
    path:'manage',
    component:ManageComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
