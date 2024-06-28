import { TipoUsuario, Usuario } from "@/types";
import { Card, CardBody } from "@nextui-org/react";
import { Button } from "@/components/button/button";
import { MouseEvent } from "react";
import { TUsuario, UsuarioModal } from "./usuario-modal";
import { useModal } from "@/lib/hooks/use-modal";
import { useDelete } from "@/lib/hooks/use-delete";

interface UsuarioCardProps {
  data: Usuario
  isHidden: boolean
  roles: TipoUsuario[]
}

export const UsuarioCard = ({data, isHidden, roles}: UsuarioCardProps) => {
  const { isOpen, handleOpen, handleOpenChange } = useModal()

  const onClick = (e: MouseEvent<HTMLButtonElement>) => {
    e.preventDefault()
    
    handleOpen()
  }

  const { handleDelete } = useDelete('/usuario/' + data.id)

  return (
    <div>
      {data &&
      <>
        <Card>
          <CardBody className="flex flex-col items-center justify-center gap-5">
            <label>{data.nome}</label>
            <p className="text-xs">{data.email}</p>
            <p>{data.tipoUsuario ? data.tipoUsuario.descricao : ''}</p>

          <div className="w-full flex justify-center gap-5">
            <Button 
              isHidden={isHidden} 
              handleClick={onClick} 
              text="Editar" 
            />
            <Button 
              isHidden={isHidden}
              handleClick={handleDelete}
              text="Excluir"
              color="danger"
            />
          </div>
            
          </CardBody>
        </Card>

        <UsuarioModal 
          handleOpen={handleOpen} 
          handleOpenChange={handleOpenChange} 
          isOpen={isOpen} 
          usuario={mapUsuario(data)} 
          roles={roles} 
          isPhantom={data.id === undefined} 
        />
      </>}
    </div>
  )
}

function mapUsuario(usuario: Usuario): Usuario {

  return {
    id: usuario.id,
    nome: usuario.nome,
    email: usuario.email,
    senha: usuario.senha ?? '',
    usuario: usuario.usuario,
    tipoUsuario: usuario.tipoUsuario
  }
}