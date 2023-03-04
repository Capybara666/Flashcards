import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {UserRequestedDto} from "../dtos/user-requested-dto";
import {UserResponseDto} from "../dtos/user-response-dto";
import {BehaviorSubject, Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private USER_KEY = "logged_in_user";
  private userResponseDto: UserResponseDto = {"login": "", "password": "", "isLoginSuccessful": false}
  private subject: BehaviorSubject<UserResponseDto> = new BehaviorSubject<UserResponseDto>(this.userResponseDto)
  resource: Observable<UserResponseDto> = this.subject.asObservable()

  constructor(private httpClient: HttpClient) {}

  login(login: string, password: string) {
    let userRequestedDto = {"login": login, "password": password}
    this.httpClient.post<UserResponseDto>('/api/auth/login', userRequestedDto)
      .subscribe(response => {
      this.userResponseDto = response;
      if(this.userResponseDto.isLoginSuccessful) {
        this.createSession(this.userResponseDto.login);
      }
      window.location.reload()
    });
  }

  logout(): void {
    window.sessionStorage.clear();
  }

  private createSession(login: String): void {
    window.sessionStorage.removeItem(this.USER_KEY);
    window.sessionStorage.setItem(this.USER_KEY, login.toString());
  }

  public getSessionLogin(): String | null {
    return window.sessionStorage.getItem(this.USER_KEY);
  }

}
