import { contato } from './contato';
import { Disponibilidade } from './disponibilidade';
import { endereco } from './endereco';
export interface dadosPj {
  nome: string;
  cpf: string;
  especializacao: string;
  urlImagem: string;
  endereco: endereco;
  contato: contato;
  listadeservico: string[];
  //servicosPrestados: string[],
  disponibilidades: Disponibilidade[];
  descricaoAdicional: string;
}