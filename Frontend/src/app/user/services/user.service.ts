import { Injectable } from '@angular/core';
import {User} from "../user";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  users : User [] = [
    {
      id: BigInt(1),
      login: 'l1',
      password: 'p1'
    },
    {
      id: BigInt(2),
      login: 'l2',
      password: 'p2'
    }
  ];

  constructor(private httpClient: HttpClient) { }

  getAllUsers() {
    return this.httpClient.get<User[]>('http://localhost:8080/users');
  }
}
