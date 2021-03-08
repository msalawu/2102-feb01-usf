import { Component, Input, OnInit, Output } from '@angular/core';
import { Pokemon } from '../models/pokemon';

@Component({
  selector: 'app-pokemon',
  templateUrl: './pokemon.component.html',
  styleUrls: ['./pokemon.component.css']
})
export class PokemonComponent implements OnInit {
  // the input decorator allows this field to be used as an attribute on the
  // component directive in the parent component template
  @Input() pokemon: Pokemon;
  filterText: string;

  constructor() { }

  ngOnInit(): void {
    this.filterText = '';
  }

}
