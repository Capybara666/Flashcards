import {Inject, Injectable} from '@angular/core';
import {User} from "../user";
import {HttpClient} from "@angular/common/http";
import {APP_SERVICE_CONFIG} from "../../AppConfig/appconfig.service";
import {AppConfig} from "../../AppConfig/appconfig.interface";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient, @Inject(APP_SERVICE_CONFIG) private config: AppConfig) {
  }

  getAllUsers() {
    return this.httpClient.get<User[]>('/api/users');
  }
}
