import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Zone } from '../types/zone.type';

@Injectable({
  providedIn: 'root'
})
export class ZoneService {

  public zones$: Observable<Zone[]>;

  constructor(private http: HttpClient) { 
    this.zones$ = this.http.get<Zone[]>('api/zones');
  }
  
}
