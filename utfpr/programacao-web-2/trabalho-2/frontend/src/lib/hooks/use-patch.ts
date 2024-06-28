'use client'

import { ApiResponse } from "@/types"
import { useState } from "react"
import { Api } from "../axios"
import { AxiosError } from "axios"
import { toast } from "sonner"
import { useAuth } from "./use-auth"

export interface postApiDataResponse<TData> {
  isLoading: boolean
  handlePatch: (data: TData) => void
}

export function usePatch<TData>(url: string): postApiDataResponse<TData> {
  const { accessToken } = useAuth()
  const [isLoading, setIsLoading] = useState(true)

  const handlePatch = (data: TData) => {
    Api(accessToken)
      .patch<ApiResponse<TData>>(url, {
        ...data
      })
      .then((response) => {

        toast.success('Sucesso')
      })
      .catch((err: AxiosError) => {
        const apiResponse = err.response?.data as ApiResponse<null>

        toast.error('Erro')
      })
      .finally(() => setIsLoading(false))
  }

  return { isLoading, handlePatch }
}

function validateToken(route: string, accessToken?: string): boolean {
  return (accessToken !== undefined && route !== '/auth')
}