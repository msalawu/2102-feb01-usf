import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { PokemonComponent } from './pokemon/pokemon.component';
import { PokemonService } from './pokemon.service';
import { TypeColorDirective } from './type-color.directive';
import { MoveFilterPipe } from './move-filter.pipe';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    PokemonComponent,
    TypeColorDirective,
    MoveFilterPipe
  ],
  imports: [
    BrowserModule,
    FormsModule, // allows us to use ngModel (two-way binding)
    HttpClientModule // allows us to use HttpClient
  ],
  providers: [
    PokemonService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
