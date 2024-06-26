'use client'

import { TipoUsuario, Usuario } from "@/types";
import { UsuarioCard } from "./components/usuario-card";
import { useFetch } from "@/lib/hooks/use-fetch";
import { Button } from "@/components/button/button";
import { useModal } from "@/lib/hooks/use-modal";
import { UsuarioModal } from "./components/usuario-modal";

export default function UsuariosPage() {
  const { data } = useFetch<Usuario[]>('/usuario')

  const {data: tiposUsuario} = useFetch<TipoUsuario[]>('/tipousuario')

  const {handleOpen, handleOpenChange, isOpen} = useModal()

  return <div className="flex flex-col gap-10 px-5">
    <>
      <UsuarioModal handleOpen={handleOpen} handleOpenChange={handleOpenChange} isOpen={isOpen} isPhantom={true} roles={tiposUsuario ?? []} />

      <div className="flex justify-end">
        <Button text="Registrar novo usuário" handleClick={() => handleOpen()} />
      </div>
    </>

    <div className="grid grid-cols-5 gap-5">
      {data?.map((item) => <UsuarioCard
        roles={tiposUsuario ?? []}
        key={item.id + item.tipoUsuario} 
        data={item} 
        isHidden={false}
      />)}
    </div>
  </div>
}