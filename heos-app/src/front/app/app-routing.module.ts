import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DevicesComponent } from './pages/devices/devices.component';
import { HomeComponent } from './pages/home/home.component';
import { PlaybackComponent } from './pages/playback/playback.component';
import { ZonesComponent } from './pages/zones/zones.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'zones', component: ZonesComponent },
  { path: 'playback', component: PlaybackComponent },
  { path: 'devices', component: DevicesComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
