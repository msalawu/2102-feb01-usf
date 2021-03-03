import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Pokemon } from './models/pokemon';

@Injectable({
  providedIn: 'root'
})
export class PokemonService {

  // in TypeScript, if we put an access modifier on a constructor parameter,
  // it implicitly makes it a field on the class
  constructor(private http: HttpClient) { }

  getPokemonById(id:number): Observable<Pokemon> {
    return this.http.get('https://pokeapi.co/api/v2/pokemon/' + id).pipe(
      map(resp => resp as Pokemon)
    );
  }
}
