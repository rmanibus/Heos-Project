import { Member } from "./member.type";

export interface Device extends Member {
    groupStatus: string;
    zoneStatus: string;
    audioChannel: string;
}