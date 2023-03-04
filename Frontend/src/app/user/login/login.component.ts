import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  form: any = {
    login: null,
    password: null
  };
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = 'You entered wrong login or password';

  constructor(private authService: AuthenticationService) { }

  ngOnInit(): void {
    if (this.authService.getSessionLogin() != null) {
      this.isLoggedIn = true
    }
  }

  onSubmit(): void {
    const { login, password } = this.form
    this.authService.login(login, password)
    this.isLoginFailed = this.authService.getSessionLogin() == null
    this.isLoggedIn = !this.isLoginFailed
  }

}
