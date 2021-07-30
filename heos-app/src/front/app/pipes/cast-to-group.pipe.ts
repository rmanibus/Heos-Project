import { Pipe, PipeTransform } from '@angular/core';
import { Group } from '../types/group.type';

@Pipe({
  name: 'castToGroup'
})
export class CastToGroupPipe implements PipeTransform {

  transform(value: any, ...args: unknown[]): Group {
    return value;
  }

}
