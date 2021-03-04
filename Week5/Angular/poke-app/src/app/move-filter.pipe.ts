import { Pipe, PipeTransform } from '@angular/core';
import { Move } from './models/move';

@Pipe({
  name: 'moveFilter'
})
export class MoveFilterPipe implements PipeTransform {

  transform(value: Move[], searchText: string): Move[] {
    let matches = [];
    for (const m of value) {
      if (m.move.name.toLowerCase().includes(searchText.toLowerCase())) {
        matches.push(m);
      }
    }
    return matches;
  }

}
