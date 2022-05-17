import { Component, OnInit } from '@angular/core';
import { PublicInfoService } from './public-info.service';

@Component({
  selector: 'app-public-info',
  templateUrl: './public-info.component.html',
  styleUrls: ['./public-info.component.scss']
})
export class PublicInfoComponent implements OnInit {

  publicInfo: String = "";

  constructor(private publicInfoService: PublicInfoService) { }

  ngOnInit(): void {
    this.getPublicInfo();
  }

  getPublicInfo(): void {
    this.publicInfoService.getPublicInfo()
      .subscribe(publicInfo => this.publicInfo = publicInfo.response);
  }

}
