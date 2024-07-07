export class ProcedureForm {
    name: string;
    identityCode: string;
    email: string;
    reason: string;

    constructor(name: string, identityCode: string, email: string, reason: string) {
      this.name = name;
      this.identityCode = identityCode;
      this.email = email;
      this.reason = reason;
    }
}
