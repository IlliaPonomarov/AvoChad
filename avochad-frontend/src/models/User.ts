
export default class User {
  private _username: string
  private _email: string
  private _password: string
  private _token: string

  constructor (username: string, email: string, password: string, token: string) {
    this._username = username
    this._email = email
    this._password = password
    this._token = token
  }

  get username (): string {
    return this._username
  }

  set username (value: string) {
    this._username = value
  }

  get email (): string {
    return this._email
  }

  set email (value: string) {
    this._email = value
  }

  get password (): string {
    return this._password
  }

  set password (value: string) {
    this._password = value
  }

  get token (): string {
    return this._token
  }

  set token (value: string) {
    this._token = value
  }
}
