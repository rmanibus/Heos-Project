import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Observable, of } from 'rxjs';
import { concatMap, take } from 'rxjs/operators';
import { ZoneService } from '../../services/zone.service';
@Component({
  selector: 'app-playback',
  templateUrl: './playback.component.html',
  styleUrls: ['./playback.component.less'],
})
export class PlaybackComponent implements OnInit {
  status$: Observable<any>;
  constructor(private zoneService: ZoneService, private http: HttpClient) {
    this.status$ = this.zoneService.selectedZone$.pipe(take(1)).pipe(
      concatMap((zone) => {
        if (zone) return this.http.get('api/playback/status/' + zone.id);
        return of();
      })
    );
  }

  play() {
    this.zoneService.selectedZone$.pipe(take(1)).subscribe((zone) => {
      zone && this.http.get('api/playback/play/' + zone.id).subscribe();
    });
  }

  pause() {
    this.zoneService.selectedZone$.pipe(take(1)).subscribe((zone) => {
      zone && this.http.get('api/playback/pause/' + zone.id).subscribe();
    });
  }
  ngOnInit(): void {}
}
