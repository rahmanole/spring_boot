import { Component } from '@angular/core';
import {User} from './hello/user.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  user: User;
  inputText = 'Initial Text';
  constructor() {
    this.user = new User();
    this.user.name = 'Minhazur Rahman';
    this.user.title = 'Software Engineer';
    this.user.address = 'Fh Hall DU';
    this.user.phone = [
      '01717948254',
      '01515616259'
    ];
  }
}
