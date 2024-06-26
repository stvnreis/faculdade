import { Modal } from "@/components/modal/modal"
import { Usuario, Atividade, UsuarioAtividade } from "@/types"
import { Input } from "@/components/input/input"
import { Controller, Form, SubmitHandler, useFieldArray, useForm } from "react-hook-form"
import { Button } from "@/components/button/button"
import { usePost } from "@/lib/hooks/use-post"
import { MouseEvent, useEffect } from "react"
import { usePatch } from "@/lib/hooks/use-patch"
import { useModal } from "@/lib/hooks/use-modal"
import { TextArea } from "@/components/input/text-area"

interface ArticleModalProps {
  isOpen: boolean,
  handleOpen: (b: boolean) => void
  handleOpenChange: (b: boolean) => void
  atividade?: Atividade
  usuarios: Usuario[]
  handleItemSubmit: (atividade: Atividade) => void
}

export const SubmitArticleModal = ({
  isOpen, 
  handleOpen, 
  handleOpenChange,
  atividade,
  handleItemSubmit,
  usuarios
}: ArticleModalProps) => {
  const {isOpen: isGradeModalOpen, handleOpen: handleOpenGradeModal, handleOpenChange: handleOpenChangeGradeModal} = useModal()

  const {
    control,
    handleSubmit,
    reset,
    watch,
    formState: { errors },
  } = useForm<Atividade>()
  
  const {handlePost} = usePost<Atividade>('/atividade')
  const {handlePatch} = usePatch<Atividade>('/atividade/' + atividade?.id)

  const onSubmit: SubmitHandler<Atividade> = (data) => {
    data = mapData(data)
    handleItemSubmit(data)

    atividade === undefined || !atividade?.id ? handlePost(data) : handlePatch(data)
  }

  const { fields, append, remove } = useFieldArray({
    control,
    name: "usuariosAtividade",
  });

  useEffect(() => {
    remove()
    if (atividade) reset(atividade)
  // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [atividade, isOpen])

  const addUsuario = (e: MouseEvent<HTMLButtonElement>) => {
    e.preventDefault()

    append({} as UsuarioAtividade)
  }

  return (
    <Form control={control}>
      <Modal 
        isOpen={isOpen} 
        onOpen={handleOpen} 
        onOpenChange={handleOpenChange}
        type="form"
        submitButton={<Button text="Enviar" handleClick={handleSubmit(onSubmit)} />}
      >
        <label>Atividade</label>
        <Controller 
          name="titulo"
          control={control}
          render={({field}) => <Input
            type="text" 
            label="Titulo da atividade"
            field={field}
          />} 
        />

        <Controller 
          name="descricao"
          control={control}
          render={({field}) => <Input
            type="text" 
            label="Descrição da atividade"
            field={field}
          />} 
        />

        <div className="mt-2 flex flex-col">
          <label className="text-sm font-bold">Data para entrega máxima</label>
          <Controller 
            name="dtEntregaMaxima"
            control={control}
            render={({field}) =>
              <input type="date" {...field}></input>} 
          />
        </div>

        <Controller 
          name="artefato.titulo"
          control={control}
          render={({field}) => <Input
            type="text" 
            label="Titulo artefato"
            field={field}
          />} 
        />

        <Controller 
          name="artefato.resumo"
          control={control}
          render={({field}) => <TextArea 
            label="Resumo artefato"
            field={field}
          />} 
        />

        <Controller 
          name="artefato.urlPdf"
          control={control}
          render={({field}) => <Input
            type="text" 
            label="Link para PDF do artefato"
            field={field}
          />} 
        />
        
        <Button text="Atribuir usuários" handleClick={addUsuario} />

        <label className="text-sm font-bold">Usuarios atribuidos</label>
        {fields.map((field, index) => {
            return (
              <Controller 
              key={index}
              name={`usuariosAtividade.${index}.usuario`}
              control={control}
              render={({field}) => <p {...field}>{field.value?.nome}</p>}
            />
          )
        })}
      </Modal>
    </Form>
  )
}

function mapData(data: Atividade): Atividade {
  data.usuariosAtividade.forEach((usuarioAtividade) => usuarioAtividade.dhEntregaMaximo = new Date(data.dtEntregaMaxima!).toISOString())

  data.dtEntregaMaxima = undefined

  return data
}