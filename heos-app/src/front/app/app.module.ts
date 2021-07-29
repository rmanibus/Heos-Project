import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { SidenavComponent } from './components/sidenav/sidenav.component';
import { ToolbarComponent } from './components/toolbar/toolbar.component'; 
import { ZonesComponent } from './pages/zones/zones.component';
import { PlaybackComponent } from './pages/playback/playback.component';
import { DevicesComponent } from './pages/devices/devices.component'; 

import { AppRoutingModule } from './app-routing.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FlexLayoutModule } from '@angular/flex-layout';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import {MatToolbarModule} from '@angular/material/toolbar'; 
import {MatSidenavModule} from '@angular/material/sidenav'; 
import {MatButtonModule} from '@angular/material/button'; 
import {MatIconModule} from '@angular/material/icon';
import {MatMenuModule} from '@angular/material/menu'; 
import {MatListModule} from '@angular/material/list';
import {DragDropModule} from '@angular/cdk/drag-drop'; 
import {MatExpansionModule} from '@angular/material/expansion'; 

import { GroupService } from './services/group.service';
import { ZoneService } from './services/zone.service';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    SidenavComponent,
    ToolbarComponent,
    ZonesComponent,
    PlaybackComponent,
    DevicesComponent
  ],
  imports: [
    HttpClientModule,
    FlexLayoutModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatSidenavModule,
    MatButtonModule,
    MatIconModule,
    MatMenuModule,
    MatListModule,
    DragDropModule,
    MatExpansionModule,
  ],
  providers: [GroupService, ZoneService],
  bootstrap: [AppComponent]
})
export class AppModule { }
