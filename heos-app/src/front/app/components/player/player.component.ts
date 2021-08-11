import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { MatSliderChange } from '@angular/material/slider';
import { interval, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';
import { Zone } from '../../types/zone.type';

@Component({
  selector: 'app-player',
  templateUrl: './player.component.html',
  styleUrls: ['./player.component.less']
})
export class PlayerComponent implements OnInit {

  @Input() zone: Zone | null = null;
  status$: Observable<any> | null = null;
  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    if(this.zone) {
      this.status$ = interval(1000).pipe(mergeMap(() => this.http.get('api/playback/status/' + this.zone?.id)));
    }
  }

  play() {

    this.zone && this.http.get('api/playback/play/' + this.zone.id).subscribe();
  }

  pause() {
    this.zone && this.http.get('api/playback/pause/' + this.zone.id).subscribe();
  }

  next() {
    this.zone && this.http.get('api/playback/next/' + this.zone.id).subscribe();
  }
  previous() {
    this.zone && this.http.get('api/playback/previous/' + this.zone.id).subscribe();

  }

  volume(event: MatSliderChange){
    this.zone && this.http.get('api/playback/volume/' + this.zone.id + '/' + event.value).subscribe();
  }
}