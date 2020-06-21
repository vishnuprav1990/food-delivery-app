import { Component, OnInit } from '@angular/core';
import { RestautrantService } from './restautrant.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  title = 'food-delivery-app';
  catalogue: any;

  constructor(private restautrantService: RestautrantService) {

  }
  ngOnInit(): void {
    this.getCatalogue();
  }

  getCatalogue(): void {
    this.restautrantService.getAllCatalogue().subscribe((catalogue) => {
      this.catalogue = catalogue;
    });
  }

}
