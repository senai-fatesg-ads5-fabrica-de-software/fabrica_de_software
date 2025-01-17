import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { Servico } from 'src/app/exports/interface/servico';
import { BaseServiceService } from 'src/app/exports/service/base-service.service';
import { ServicosService } from 'src/app/exports/service/servicos.service';
import { TelaBaseComponent } from 'src/app/exports/tela/tela-base/tela-base.component';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css'],
})
export class MainComponent extends TelaBaseComponent {
  usuarios: any = [
    {
      nome: 'aaaaaaaaaaaaa',
    },
    {
      nome: 'aaaaaaaaaaaaa',
    },
    {
      nome: 'aaaaaaaaaaaaa',
    },
    {
      nome: 'aaaaaaaaaaaaa',
    },
    {
      nome: 'aaaaaaaaaaaaa',
    },
    {
      nome: 'aaaaaaaaaaaaa',
    },
    {
      nome: 'aaaaaaaaaaaaa',
    },
    {
      nome: 'aaaaaaaaaaaaa',
    },
    {
      nome: 'aaaaaaaaaaaaa',
    },
    {
      nome: 'aaaaaaaaaaaaa',
    },
    {
      nome: 'aaaaaaaaaaaaa',
    },
    {
      nome: 'aaaaaaaaaaaaa',
    },
    {
      nome: 'aaaaaaaaaaaaa',
    },
    {
      nome: 'aaaaaaaaaaaaa',
    },
    {
      nome: 'aaaaaaaaaaaaa',
    },
    {
      nome: 'aaaaaaaaaaaaa',
    },
    {
      nome: 'aaaaaaaaaaaaa',
    },
    {
      nome: 'aaaaaaaaaaaaa',
    },
    {
      nome: 'aaaaaaaaaaaaa',
    },
    {
      nome: 'aaaaaaaaaaaaa',
    },
    {
      nome: 'aaaaaaaaaaaaa',
    },
    {
      nome: 'aaaaaaaaaaaaa',
    },
    {
      nome: 'aaaaaaaaaaaaa',
    },
    {
      nome: 'aaaaaaaaaaaaa',
    },
    {
      nome: 'aaaaaaaaaaaaa',
    },
    {
      nome: 'aaaaaaaaaaaaa',
    },
    {
      nome: 'aaaaaaaaaaaaa',
    },
    {
      nome: 'aaaaaaaaaaaaa',
    },
    {
      nome: 'aaaaaaaaaaaaa',
    },
    {
      nome: 'aaaaaaaaaaaaa',
    },
    {
      nome: 'aaaaaaaaaaaaa',
    },
    {
      nome: 'aaaaaaaaaaaaa',
    },
    {
      nome: 'aaaaaaaaaaaaa',
    },
    {
      nome: 'aaaaaaaaaaaaa',
    },
    {
      nome: 'aaaaaaaaaaaaa',
    },
    {
      nome: 'aaaaaaaaaaaaa',
    },
    {
      nome: 'aaaaaaaaaaaaa',
    },
    {
      nome: 'aaaaaaaaaaaaa',
    },
  ];

  categorias!: string[];

  constructor(
    override service: ServicosService,
    protected override route: ActivatedRoute
  ) {
    super(service, route);
    this.getCategorias();
  }

  // private dividirServicos(servicos: Servico[]) {
  //   let colunas = 5;
  //   let totalLinhas = Math.ceil(servicos.length / colunas);

  //   for (let i = 0; i < totalLinhas; i++) {
  //     this.servicosDivididos[i] = servicos.slice(
  //       i * colunas,
  //       i * colunas + colunas
  //     );
  //   }
  // }
  public getCategorias(): void {
    this.service.getCategorias().then((data) => {
      this.categorias = data.data.content;
    });
  }

  contratar(servico: Servico) {
    this.service.navigate('/contratar/' + servico.id);
  }

  teste() {
    this.service.getServicoDetail(2).then((data) => {
      console.log(data);
    });
  }
}
