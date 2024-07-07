import { Person } from "./person";

export class Procedure {
  id: number;
  person: Person;
  reason: string;
  emailSent: boolean;

  constructor(id: number, person: Person, reason: string, emailSent: boolean = false) {
    this.id = id;
    this.person = person;
    this.reason = reason;
    this.emailSent = emailSent;
  }
}
