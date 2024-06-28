import NextAuth from 'next-auth'

declare module 'next-auth' {
  interface Session {
    id: string
    email: string
    nome: string
    usuario: string
    accessToken: string
  }
}