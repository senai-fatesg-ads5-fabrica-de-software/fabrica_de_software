import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BaseServiceService } from 'src/app/exports/service/base-service.service';

@Component({
  selector: 'app-contratar',
  templateUrl: './contratar.component.html',
  styleUrls: ['./contratar.component.css'],
})
export class ContratarComponent implements OnInit {
  servico: any = '';

  constructor(
    private service: BaseServiceService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    let id = this.route.snapshot.data['id'];
    this.service.getServicoPrestado(id).subscribe({
      next: (a: any) => {
        console.log(a.data);
        if (a.erros?.lenght > 0) {
        } else {
          this.servico = a.data;
        }
      },
      error(err) {
        console.log(err);
      },
    });
  }

  abrirPerfilPrestador() {
    this.router.navigate(['/perfil/' + this.servico.prestador.id]);
  }
}
