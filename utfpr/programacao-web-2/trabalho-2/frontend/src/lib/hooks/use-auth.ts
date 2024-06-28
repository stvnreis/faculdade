'use client'

import { useSession } from "next-auth/react"

export type TUserData = {
  usuario?: {
    nome: string
    usuario: string
  }
  senha?: string
  accessToken: string
}

export function useAuth(): TUserData {
  const { data } = useSession()

  console.log(data)

  return {
    usuario: {
      nome: data?.nome as any,
      usuario: data?.usuario as any
    },
    accessToken: data?.accessToken as string
  }
}