export type TipoUsuario = {
  tpUsuario: string
  descricao: string
}

export type Usuario = {
  id: number
  idExterno?: string
  nome: string
  usuario: string
  email: string
  senha?: string
  accessToken?: string
  tipoUsuario: TipoUsuario
}

export type Atividade = {
  id: number
  descricao: string
  titulo: string
  dhCriacao: string
  artefato: Artefato
  usuariosAtividade: UsuarioAtividade[]
  dtEntregaMaxima?: string
}

export type Artefato = {
  id: number
  titulo: string
  resumo: string
  urlPdf: string
}

export type UsuarioAtividade = {
  usuario: Usuario
  id: number
  artefato: Artefato
  dhEntrega?: string
  dhEntregaMaximo: string
}

export type ApiResponse<T> = {
  data: T
  message?: string
}