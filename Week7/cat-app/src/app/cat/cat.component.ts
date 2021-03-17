import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Cat } from '../models/cat';

@Component({
  selector: 'app-cat',
  templateUrl: './cat.component.html',
  styleUrls: ['./cat.component.css']
})
export class CatComponent implements OnInit {
  @Input() cat: Cat;
  @Output() adoptedCat: EventEmitter<any> = new EventEmitter();

  constructor() { }

  ngOnInit(): void {
  }

  emitAdopt(): void {
    this.adoptedCat.emit();
  }

  petCat(): void {
    let msg: string = (this.cat.status.name === 'Adopted') ? 'very pleased' : 'nervous, but pleased';
    alert('You pet ' + this.cat.name + '! They seem ' + msg + '.');
  }

}
