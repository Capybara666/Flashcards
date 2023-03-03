import {Component, Injectable, OnInit} from '@angular/core';
import {User} from "./user";
import {UserService} from "./services/user.service";

@Injectable({providedIn: 'root'})
@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {

  users : User[] = [];

  constructor(private userService: UserService) {
  }

  ngOnInit(): void {
    this.userService.getAllUsers()
      .subscribe(response => {
        this.users = response;
      })
  }

  registerNewUser() {
    const user: User = {
      id: BigInt(2),
      login: "registeredLogin",
      password: "registeredPassword"
    }

    this.userService.addNewUser(user).subscribe((response) => {
      this.users = response;
    });
  }
}
