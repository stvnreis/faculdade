import { NextAuthOptions } from "next-auth"
import CredentialsProvider from 'next-auth/providers/credentials'
import { Api } from "./axios"

export const nextAuthOptions: NextAuthOptions = {
  providers: [
    CredentialsProvider({
      name: 'credentials',
      credentials: {
        usuario: { label: 'usuario', type: 'text' },
        senha: { label: 'senha', type: 'text' },
      },

      async authorize(credentials, req) {
        const { data: apiData } = await Api().post('/auth/login', {
          usuario: credentials?.usuario,
          senha: credentials?.senha,
        })

        return apiData || null
      },
    }),
  ],
  pages: {
    signIn: '/auth',
  },
  callbacks: {
    async jwt({ token, user }) {
      return { ...token, ...user }
    },

    async session({ session, token }) {
      session = token as any

      return session
    },
  },
}