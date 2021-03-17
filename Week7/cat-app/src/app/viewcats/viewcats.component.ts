import { Component, OnInit } from '@angular/core';
import { Breed } from '../models/breed';
import { Cat } from '../models/cat';
import { Person } from '../models/person';
import { SpecialNeed } from '../models/special-need';
import { CatService } from '../services/cat.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-viewcats',
  templateUrl: './viewcats.component.html',
  styleUrls: ['./viewcats.component.css']
})
export class ViewCatsComponent implements OnInit {
  availCats: Cat[];
  loggedUser: Person;
  newCat: Cat;
  specialNeeds: SpecialNeed[];
  breeds: Breed[];
  selectedBreed: Breed;
  selectedNeeds: SpecialNeed[];

  constructor(private catServ: CatService, private userServ: UserService) { }

  ngOnInit(): void {
    this.userServ.checkLogin().subscribe(
      resp => {
        this.loggedUser = resp;
      }
    );

    this.newCat = new Cat();
    this.selectedBreed = new Breed();
    this.selectedNeeds = new Array<SpecialNeed>();
    this.catServ.getAvailableCats().subscribe(
      resp => {
        this.availCats = resp;
      }
    );

    this.catServ.getBreeds().subscribe(
      resp => {
        this.breeds = resp;
      }
    );

    this.catServ.getSpecialNeeds().subscribe(
      resp => {
        this.specialNeeds = resp;
      }
    );
  }

  adopt(cat: Cat): void {
    this.catServ.adoptCat(cat).subscribe();
  }

  addNeedToCat(need: SpecialNeed): void {

  }

  removeNeedFromCat(need: SpecialNeed): void {

  }

  addCat(): void {
    this.selectedBreed.name = this.breeds.find(b => b.id==this.selectedBreed.id).name;
    this.newCat.breed = this.selectedBreed;
    this.newCat.specialNeeds = this.selectedNeeds;
    console.log(this.newCat);
  }

}
