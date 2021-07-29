import {
  CdkDragDrop,
  moveItemInArray,
  transferArrayItem
} from '@angular/cdk/drag-drop';
import { Component, OnInit } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Device } from '../../types/device.type';
import { Group } from '../../types/group.type';
import { Zone } from '../../types/zone.type';

@Component({
  selector: 'app-zones',
  templateUrl: './zones.component.html',
  styleUrls: ['./zones.component.less'],
})
export class ZonesComponent implements OnInit {
  zonesSubject = new BehaviorSubject<Zone[]>([]);
  zones$: Observable<Zone[]>;

  constructor() {
    this.zones$ = this.zonesSubject.asObservable();
  }

  ngOnInit(): void {
    this.zonesSubject.next([
      {
        friendlyName: 'salon',
        id: '132432',
        leader: {
          id: '123532',
          type: 'device',
          audioChannel: 'LEFT',
          friendlyName: 'device1',
          groupStatus: 'status',
          zoneStatus: 'status',
        },
        members: [
          {
            id: '123532',
            type: 'device',
            audioChannel: 'LEFT',
            friendlyName: 'device1',
            groupStatus: 'status',
            zoneStatus: 'status',
          },
          {
            id: '123532',
            type: 'device',
            audioChannel: 'RIGHT',
            friendlyName: 'device2',
            groupStatus: 'status',
            zoneStatus: 'status',
          },
        ],
      },
    ]);
  }

  drop(event: CdkDragDrop<(Device | Group)[]>) {
    if (
      !event.isPointerOverContainer &&
      event.previousContainer.data.length > 1
    ) {
      const newZone: Zone = {
        friendlyName: 'new zone',
        id: '',
        leader: null,
        members: [],
      };
      const zones: Zone[] = this.zonesSubject.getValue();
      zones.push(newZone);
      this.zonesSubject.next(zones);

      transferArrayItem(
        event.previousContainer.data,
        newZone.members,
        event.previousIndex,
        0
      );
    } else if (event.previousContainer === event.container) {
      moveItemInArray(
        event.container.data,
        event.previousIndex,
        event.currentIndex
      );
    } else {
      if (event.previousContainer.data.length == 1) {
        console.log('array to remove', event.previousContainer);
        const zones: Zone[] = this.zonesSubject.getValue();
        this.zonesSubject.next(zones.filter(zone => zone.members !== event.previousContainer.data));
      }
      transferArrayItem(
        event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex
      );
    }
  }
}
