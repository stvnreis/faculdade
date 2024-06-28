import { ApiResponse } from "@/types";
import axios, { AxiosError } from "axios";
import { toast } from "sonner";

export const Api = (accessToken?: string) => axios.create({
  baseURL: 'http://localhost:8080',
  headers: accessToken ? {
    Authorization: 'Bearer ' + accessToken,
  } : undefined
})

export async function getData<TData>(url: string, accessToken?: string) {
  return Api()
    .get<TData>(url)
    .then((response) => response.data)
    .catch((err: AxiosError) => {
      const apiResponse = err.response?.data as ApiResponse<null>

      toast.error(apiResponse.message)
    })
}