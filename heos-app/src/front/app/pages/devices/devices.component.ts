import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Device } from '../../types/device.type';

@Component({
  selector: 'app-devices',
  templateUrl: './devices.component.html',
  styleUrls: ['./devices.component.less']
})
export class DevicesComponent implements OnInit {

  devices$: Observable<Device[]>;

  constructor(private http: HttpClient) { 
    this.devices$ = this.http.get<Device[]>('api/devices/');
  }

  ngOnInit(): void {
  }

}
