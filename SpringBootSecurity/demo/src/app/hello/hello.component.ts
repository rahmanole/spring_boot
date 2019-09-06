import {Component, Input, OnInit} from '@angular/core';
import {User} from './user.model';

const input = Input;

@Component({
  selector: 'app-hello',
  templateUrl: './hello.component.html',
  styleUrls: ['./hello.component.css']
})
export class HelloComponent implements OnInit {
  user: any;
  @Input('name') userObj: User;
  constructor() {

  }

  ngOnInit() {
    this.user = {
      name: this.userObj.name,
      title: this.userObj.title,
      address: this.userObj.address,
      phone: this.userObj.phone
    };
  }

}
