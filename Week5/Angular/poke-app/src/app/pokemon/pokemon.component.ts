import { Component, Input, OnInit } from '@angular/core';
import { Pokemon } from '../models/pokemon';

@Component({
  selector: 'app-pokemon',
  templateUrl: './pokemon.component.html',
  styleUrls: ['./pokemon.component.css']
})
export class PokemonComponent implements OnInit {
  // the input decorator allows this field to be used as an attribute on the
  @Input() pokemon: Pokemon;

  constructor() { }

  ngOnInit(): void {
  }

}
