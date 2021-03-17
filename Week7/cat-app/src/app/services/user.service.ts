import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Person } from '../models/person';
import { UrlService } from './url.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  loggedUser: Person;
  userUrl: string = this.urlServ.getUrl() + 'users';

  constructor(private urlServ: UrlService, private http: HttpClient) { }

  checkLogin(): Observable<Person> {
    return this.http.get(this.userUrl, {withCredentials:true}).pipe(
      map(resp => resp as Person)
    );
  }

  logIn(username:string, password:string): Observable<Person> {
    let loginInfo = {
      username:username,
      password:password
    }
    return this.http.put(this.userUrl, loginInfo, {withCredentials:true}).pipe(
      map(resp => resp as Person)
    );
  }

  logOut(): Observable<any> {
    return this.http.delete(this.userUrl, {withCredentials:true}).pipe();
  }
}
