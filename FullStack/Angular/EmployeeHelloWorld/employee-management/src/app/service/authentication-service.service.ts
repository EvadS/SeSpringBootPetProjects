import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {map} from 'rxjs/operators';

export class User {
  constructor(
    public status: string,
  ) {
  }

}

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(
    private httpClient: HttpClient
  ) {
  }

  authenticate(username, password) {
    console.log(password);
    const headers = new HttpHeaders({Authorization: 'Bearer ' + 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqYXZhaW51c2UiLCJleHAiOjE1ODc0NjY3NjYsImlhdCI6MTU4NzQ0ODc2NX0._DIFh3ZSpu2NeYmM5HeQxuwWujCWNtAihCQIDeMrY98VjuDMTR-DSMCRmQSrHXQJZ02TtqU6li51fMLYyWrnsw'
        // btoa(username + ':' + password)
      });
    return this.httpClient.get<User>('http://localhost:8080/employees/validateLogin', {headers}).pipe(
      map(
        userData => {
          sessionStorage.setItem('username', username);
          return userData;
        }
      )
    );
  }


  public  isUserLoggedIn() {
    const user = sessionStorage.getItem('username');
    console.log(!(user === null));
    return !(user === null);
  }

  logOut() {
    sessionStorage.removeItem('username');
  }
}
