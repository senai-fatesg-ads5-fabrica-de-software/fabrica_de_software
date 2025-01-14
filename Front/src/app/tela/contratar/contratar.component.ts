import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BaseServiceService } from 'src/app/exports/service/base-service.service';
import { ServicosService } from 'src/app/exports/service/servicos.service';

@Component({
  selector: 'app-contratar',
  templateUrl: './contratar.component.html',
  styleUrls: ['./contratar.component.css'],
})
export class ContratarComponent implements OnInit {
  constructor(
    private service: ServicosService,
    private route: ActivatedRoute
  ) {}
  servicoPrestado: any;

  date: Date = new Date();

  ngOnInit(): void {
    let id = this.route.snapshot.data['id'];
    this.service.getServicoPrestado(id).then((data) => {
      this.servicoPrestado = data.data;
    });
  }

  abrirPerfilPrestador() {
    this.service.navigate('/perfil/' + this.servicoPrestado.idPrestador);
  }
}
