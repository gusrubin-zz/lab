import { Component, OnInit } from '@angular/core';
import { AppComponent } from '../app.component';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-top-bar',
  templateUrl: './top-bar.component.html',
  styleUrls: ['./top-bar.component.scss']
})
export class TopBarComponent implements OnInit {

  title = '';
  isLoggedIn = false;
  loggedInUsername = 'gusrubin';

  constructor(private appComponent: AppComponent, private authService: AuthService) { }

  ngOnInit(): void {
    this.title = this.appComponent.title;
    this.isLoggedIn = this.authService.checkCredentials();
  }

}
