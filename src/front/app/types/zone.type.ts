import { Device } from './device.type';
import { Group } from './group.type';

export interface Zone {
  friendlyName: string;
  id: string;
  leader: Device | null;
  members: Array<Device | Group>;
}
