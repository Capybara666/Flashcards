import { Component } from '@angular/core';
import {AuthenticationService} from "../services/authentication.service";
import {UserResponseDto} from "../dtos/user-response-dto";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
  form: any = {
    login: null,
    password: null,
    repeatPassword: null
  };
  registerFailed = false;
  successfullyRegistered = false;
  errorMessage = 'This user already exists!';

  constructor(private authService: AuthenticationService) { }

  onSubmit(): void {
    this.authService.register(this.form.login, this.form.password)
      .subscribe(response => {
        let userResponseDto = (response as UserResponseDto)
        this.successfullyRegistered = userResponseDto.registered
        this.registerFailed = !this.successfullyRegistered;
        if(this.registerFailed) {
          window.location.reload();
        }
        else {
          this.form = {
            login: null,
            password: null,
            repeatPassword: null
          };
        }
      });
  }
}

