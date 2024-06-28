import { Modal } from "@/components/modal/modal"
import { Usuario, Atividade, Artefato } from "@/types"
import { Input } from "@/components/input/input"
import { Controller, Form, useForm } from "react-hook-form"
import { Button } from "@/components/button/button"
import { usePost } from "@/lib/hooks/use-post"
import { TextArea } from "@/components/input/text-area"

interface EntregarAtividadeProps {
  isOpen: boolean,
  handleOpen: (b: boolean) => void
  handleOpenChange: (b: boolean) => void
  atividade: Atividade
  handleItemSubmit: (atividade: Atividade) => void
}

export const EntregarAtividadeModal = ({
  isOpen, 
  handleOpen, 
  handleOpenChange,
  atividade,
  handleItemSubmit,
}: EntregarAtividadeProps) => {

  const {
    control,
    handleSubmit
  } = useForm<Artefato>()
  
  const {handlePost} = usePost<Artefato>(`/atividade/${atividade?.id}/entregar`)

  return (
    <Form control={control}>
      <Modal 
        isOpen={isOpen} 
        onOpen={handleOpen} 
        onOpenChange={handleOpenChange}
        type="form"
        submitButton={<Button text="Enviar" handleClick={handleSubmit(handlePost)} />}
      >

        <Controller 
          name="titulo"
          control={control}
          render={({field}) => <Input
            type="text" 
            label="Titulo artefato"
            field={field}
          />} 
        />

        <Controller 
          name="resumo"
          control={control}
          render={({field}) => <TextArea 
            label="Resumo artefato"
            field={field}
          />} 
        />

        <Controller 
          name="urlPdf"
          control={control}
          render={({field}) => <Input
            type="text" 
            label="Link para PDF do artefato"
            field={field}
          />} 
        />
      </Modal>
    </Form>
  )
}