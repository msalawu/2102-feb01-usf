import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Pokemon } from '../models/pokemon';
import { PokemonService } from '../pokemon.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  // random: number;
  msg: string = 'Get Pokemon';
  pokemonId: number;
  homePokemon: Pokemon;
  @Output() onGetPokemon: EventEmitter<any> = new EventEmitter();

  constructor(private pokemonServ: PokemonService) {
    // this.random = Math.floor(Math.random()*3);
    this.pokemonId = 1;
    this.homePokemon = new Pokemon();
    this.homePokemon.id = 1;
    this.homePokemon.name = 'bulbasaur';
    this.homePokemon.abilities = [{ability:{name:'ability 1'}},{ability:{name:'ability 2'}}];
    this.homePokemon.types = [{type: {name: "grass"}},{type: {name: "poison"}}];
    this.homePokemon.moves = [{move:{name:'move 1'}}];
    this.homePokemon.sprites = {
      back_default: "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/1.png",
      back_female: null,
      back_shiny: "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/shiny/1.png",
      back_shiny_female: null,
      front_default: "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
      front_female: null,
      front_shiny: "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/1.png",
      front_shiny_female: null};
    console.log(this.homePokemon);
  }

  ngOnInit(): void {
    console.log('component initialized');
    let b = 'something';
  }

  getPokemon() {
    // let response = await fetch('https://pokeapi.co/api/v2/pokemon/' + this.pokemonId);
    // if (response.status === 200) this.homePokemon = await response.json();
    this.pokemonServ.getPokemonById(this.pokemonId).subscribe(
      resp => {
        this.homePokemon = resp;
        this.onGetPokemon.emit();
      }
    );
  }

}
