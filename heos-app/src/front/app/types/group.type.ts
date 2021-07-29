import { Device } from './device.type';
import { Member } from './member.type';

export interface Group extends Member {
  leader: Device;
  members: Device[];
}
