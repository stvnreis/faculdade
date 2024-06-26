import NextAuth from 'next-auth'

declare module 'next-auth' {
  interface Session {
    usuario: {
      id: string
      email: string
      nome: string
      usuario: string
    },
    tipoUsuario: {
      descricao: string
      tpUsuario: string
    },
    accessToken: string
  }
}