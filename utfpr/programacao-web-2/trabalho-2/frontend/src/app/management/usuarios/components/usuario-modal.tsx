
import { Modal } from "@/components/modal/modal"
import { Usuario, TipoUsuario } from "@/types"
import { Select, SelectItem } from "@nextui-org/react"
import { Input } from "@/components/input/input"
import { Controller, Form, SubmitHandler, useForm } from "react-hook-form"
import { Button } from "@/components/button/button"
import { usePost } from "@/lib/hooks/use-post"
import { usePatch } from "@/lib/hooks/use-patch"

interface UsuarioModalProps {
  usuario?: Usuario
  isOpen: boolean,
  handleOpen: (b: boolean) => void
  handleOpenChange: (b: boolean) => void
  roles: TipoUsuario[]
  isPhantom: boolean
}

export type TUsuario = {
  id: string
  nome: string
  email: string
  senha: string
  usuario: string
  tpUsuario: string
}

export const UsuarioModal = ({
  isOpen, 
  handleOpen, 
  handleOpenChange,
  usuario,
  roles,
  isPhantom,
}: UsuarioModalProps) => {

  const {
    control,
    register,
    handleSubmit,
  } = useForm<TUsuario>({
    defaultValues: isPhantom ? {
      nome: '',
      email: '',
      senha: '',
      usuario: '',
      tpUsuario: ''
    } : {
      nome: usuario!.nome,
      email: usuario!.email,
      senha: usuario!.senha,
      tpUsuario: usuario!.tipoUsuario.tpUsuario,
      usuario: usuario!.usuario
    }
  })

  const url = '/usuario'

  const {handlePost} = usePost<TUsuario>(url)
  const {handlePatch} = usePatch<TUsuario>(url.concat('/') + usuario?.id)

  const onSubmit: SubmitHandler<TUsuario> = (data) => isPhantom ? handlePost(data) : handlePatch(data)

  return (
    <Form action={(data) => alert(JSON.stringify(data))} control={control}>
      <Modal 
        isOpen={isOpen} 
        onOpen={handleOpen} 
        onOpenChange={handleOpenChange}
        type="form"
        submitButton={<Button text="Enviar" handleClick={handleSubmit(onSubmit)} />}
      >

        <Controller 
          name="nome"
          control={control}
          render={({field}) => 
            <Input
              type="text" 
              label="Nome"
              field={field}
            />}
        />
        
        <Controller 
          name="email"
          control={control}
          render={({field}) => 

            <Input
              type="text" 
              label="Email"
              field={field}
            />}
        />

        <Controller
          name="usuario"
          control={control}
          render={({field}) => <Input type="text" label="Usuario" field={field} />}
        />
        
        <Controller 
          name="senha"
          control={control}
          render={({field}) => 
            <Input
              type="text" 
              label="Senha"
              field={field}
            />}
        />

        <Controller 
          name="tpUsuario"
          control={control}
          render={({field}) => 
            <Select placeholder="Função" {...field}>
              {roles.map((role) => <SelectItem className="text-black" key={role.tpUsuario} value={role.tpUsuario} >{role.descricao}</SelectItem>)}
            </Select>
          }
        />
      </Modal>
    </Form>
  )
}