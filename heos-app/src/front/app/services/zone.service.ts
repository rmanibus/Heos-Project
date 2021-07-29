import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Zone } from '../types/zone.type';

@Injectable({
  providedIn: 'root',
})
export class ZoneService {
  private selectedZoneSubject = new BehaviorSubject<Zone | null>(null);
  public zones$: Observable<Zone[]>;
  public selectedZone$: Observable<Zone | null>;

  constructor(private http: HttpClient) {
    this.zones$ = this.http.get<Zone[]>('api/zones');
    this.selectedZone$ = this.selectedZoneSubject.asObservable();
  }
  public setSelectedZone(zone: Zone) {
    this.selectedZoneSubject.next(zone);
  }
}
