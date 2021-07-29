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
  
  constructor(public zoneService: ZoneService) {

  }

  ngOnInit(): void {}
}
