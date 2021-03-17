import { Component, OnInit } from '@angular/core';
import { Cat } from '../models/cat';
import { Person } from '../models/person';
import { SpecialNeed } from '../models/special-need';
import { CatService } from '../services/cat.service';

@Component({
  selector: 'app-viewcats',
  templateUrl: './viewcats.component.html',
  styleUrls: ['./viewcats.component.css']
})
export class ViewCatsComponent implements OnInit {
  availCats: Cat[];
  loggedUser: Person;
  newCat: Cat;

  constructor(private catServ: CatService) { }

  ngOnInit(): void {
    this.newCat = new Cat();
    this.catServ.getAvailableCats().subscribe(
      resp => {
        this.availCats = resp;
      }
    );
  }

  adopt(cat: Cat): void {

  }

  addNeedToCat(need: SpecialNeed): void {

  }

  removeNeedFromCat(need: SpecialNeed): void {

  }

}
