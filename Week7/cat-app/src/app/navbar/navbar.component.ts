import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Person } from '../models/person';
import { Role } from '../models/role';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  @Output() loggedIn: EventEmitter<any> = new EventEmitter();
  loggedUser: Person;
  username: string;
  password: string;

  constructor(private userServ: UserService, private router: Router) { }

  ngOnInit(): void {
    this.userServ.checkLogin().subscribe(
      resp => {
        this.loggedUser = resp;
      }
    );
  }

  sendLogin() {
    this.userServ.logIn(this.username, this.password).subscribe(
      resp => {
        this.loggedIn.emit();
        this.loggedUser = resp;
        this.router.navigate(['home']);
      }
    );
  }

  sendLogout() {
    this.loggedUser = null;
    this.username = '';
    this.password = '';
    this.userServ.logOut().subscribe(
      resp => {
        this.router.navigate(['home']);
      }
    );
  }

}
