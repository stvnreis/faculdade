import { useState } from "react"
import { useAuth } from "./use-auth"
import { Api } from "../axios"
import { ApiResponse } from "@/types"
import { toast } from "sonner"
import { AxiosError } from "axios"

export interface deleteApiDataResponse {
  isLoading: boolean
  handleDelete: () => void
}

export function useDelete(url: string): deleteApiDataResponse {
  // const { accessToken } = useAuth()
  const [isLoading, setIsLoading] = useState(true)

  const handleDelete = () => {
    Api()
      .delete<ApiResponse<null>>(url)
      .then((response) => {
        toast.success(response.data.message)
      })
      .catch((err: AxiosError) => {
        const apiResponse = err.response?.data as ApiResponse<null>

        toast.error(apiResponse.message)
      })
      .finally(() => setIsLoading(false))
  }
  
  return { isLoading, handleDelete }
}

function validateToken(route: string, accessToken?: string): boolean {
  return (accessToken !== undefined && route !== '/auth')
}