'use client'

import { TipoUsuario } from "@/types"
import { useSession } from "next-auth/react"

export type TUserData = {
  email: string
  password: string
}

export function useAuth() {
  // const { data } = useSession()

  // return {
  //   usuario: data?.usuario,
  //   tipoUsuario: data?.tipoUsuario as TipoUsuario,
  //   accessToken: data?.accessToken
  // }
}