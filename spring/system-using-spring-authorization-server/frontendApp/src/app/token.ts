export class Token {

    public access_token: string;
    public expires_in: number;

    constructor(_access_token: string, _expires_in: number) {
        this.access_token = _access_token;
        this.expires_in = _expires_in;
    }

}
