import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UrlService {
  private url: string = 'http://localhost:8080/CatApp/';

  constructor() { }

  getUrl(): string {
    return this.url;
  }
}
