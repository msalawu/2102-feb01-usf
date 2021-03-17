import { Component, OnInit } from '@angular/core';
import { Person } from '../models/person';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-mycats',
  templateUrl: './mycats.component.html',
  styleUrls: ['./mycats.component.css']
})
export class MyCatsComponent implements OnInit {
  loggedUser: Person;

  constructor(private userServ: UserService) { }

  ngOnInit(): void {
    this.userServ.checkLogin().subscribe(
      resp => {
        this.loggedUser = resp;
      });
  }

}
