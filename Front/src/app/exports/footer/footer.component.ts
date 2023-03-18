import { Component } from '@angular/core';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent {

  categorias:string[] = ['Assistencia técnica', 'Eventos', 'Contabilidade', 'mais uma caralhada'];
}
