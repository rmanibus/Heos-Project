import {
  CdkDragDrop,
  moveItemInArray,
  transferArrayItem
} from '@angular/cdk/drag-drop';
import { Component, OnInit, Pipe, PipeTransform } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { ZoneService } from '../../services/zone.service';
import { Device } from '../../types/device.type';
import { Group } from '../../types/group.type';
import { Zone } from '../../types/zone.type';
@Component({
  selector: 'app-zones',
  templateUrl: './zones.component.html',
  styleUrls: ['./zones.component.less'],
})
export class ZonesComponent implements OnInit {
  panelOpenState = false;
  zonesSubject = new BehaviorSubject<Zone[]>([]);
  zones$: Observable<Zone[]>;
  selectedZone$: Observable<Zone | null>;
  constructor(private zoneService: ZoneService) {
    this.zones$ = this.zonesSubject.asObservable();
    this.selectedZone$ = this.zoneService.selectedZone$;
    this.zoneService.zones$.subscribe(next => this.zonesSubject.next(next));
  }

  ngOnInit(): void {

  }

  selectZone(zone: Zone){
    this.zoneService.setSelectedZone(zone);
  }

  drop(event: CdkDragDrop<(Device | Group)[]>) {
    if (
      !event.isPointerOverContainer &&
      event.previousContainer.data.length > 1
    ) {
      const newZone: Zone = {
        friendlyName:
          event.previousContainer.data[event.previousIndex].friendlyName,
        id: '',
        leader: null,
        volume: 0,
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
        const zones: Zone[] = this.zonesSubject.getValue();
        this.zonesSubject.next(
          zones.filter((zone) => zone.members !== event.previousContainer.data)
        );
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
