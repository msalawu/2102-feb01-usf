import { Component, OnInit } from '@angular/core';
import { Person } from '../models/person';
import { Role } from '../models/role';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  loggedUser: Person;
  username: string;
  password: string;

  constructor(private userServ: UserService) { }

  ngOnInit(): void {
  }

  sendLogin() {
    
  }

  sendLogout() {
    this.loggedUser = null;
  }

}
