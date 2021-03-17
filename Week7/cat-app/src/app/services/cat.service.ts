import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Cat } from '../models/cat';
import { UrlService } from './url.service';
import { Person } from '../models/person';

@Injectable({
  providedIn: 'root'
})
export class CatService {
  private url: string = this.urlServ.getUrl() + 'cats/';

  constructor(private urlServ: UrlService, private http: HttpClient) { }

  getAvailableCats(): Observable<Cat[]> {
    return this.http.get(this.url, {withCredentials:true}).pipe(
      map(resp => resp as Cat[])
    );
  }

  getCatById(id: number): Observable<Cat> {
    return this.http.get(this.url + id, {withCredentials:true}).pipe(
      map(resp => resp as Cat)
    );
  }

  adoptCat(cat: Cat): Observable<any> {
    return this.http.get(this.url + 'adopt/' + cat.id, {withCredentials:true}).pipe();
  }
}
