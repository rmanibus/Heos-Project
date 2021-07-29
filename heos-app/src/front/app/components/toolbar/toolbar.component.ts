import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { ZoneService } from '../../services/zone.service';

@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.less']
})
export class ToolbarComponent implements OnInit {

  @Output() drawerClick = new EventEmitter();
  
  constructor(public zoneService: ZoneService) { }

  ngOnInit(): void {
  }

  toggleDrawer(){
    this.drawerClick.emit();
  }

}
