import { Component, OnChanges, OnInit } from '@angular/core';
import { Person } from '../models/person';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, OnChanges {
  loggedUser: Person;

  constructor(private userServ: UserService) { }

  ngOnInit(): void {
    this.userServ.checkLogin().subscribe(
      resp => {
        this.loggedUser = resp;
        console.log(this.loggedUser.role.name);
      }
    );
  }

  ngOnChanges(): void {
    this.ngOnInit();
  }

}
