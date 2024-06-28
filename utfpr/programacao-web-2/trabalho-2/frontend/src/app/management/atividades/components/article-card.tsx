'use client'

import { Usuario, Atividade } from "@/types"
import { Card, CardBody } from "@nextui-org/react"
import { SubmitArticleModal } from "./submit-article-modal"
import { useModal } from "@/lib/hooks/use-modal"
import { Button } from "@/components/button/button"
import { useDelete } from "@/lib/hooks/use-delete"
import { useAuth } from "@/lib/hooks/use-auth"
import { usePost } from "@/lib/hooks/use-post"

interface ArticleCardProps {
  data: Atividade
  usuarios: Usuario[]
  handleItemSubmit: (atividade: Atividade) => void
  handlePublish: (atividade: Atividade) => void
}

export const ArticleCard = ({data, handleItemSubmit,usuarios, handlePublish}: ArticleCardProps) => {
  const {handleOpen, handleOpenChange, isOpen} = useModal()

  const {handleDelete} = useDelete('/atividade/' + data.id)

  return <div>
    <SubmitArticleModal usuarios={usuarios} handleOpen={handleOpen} handleOpenChange={handleOpenChange} isOpen={isOpen} atividade={{...data}} handleItemSubmit={handleItemSubmit} />
    <Card className="w-full flex flex-col items-center justify-center">
      <CardBody className="gap-10 text-center">
        <label>{data.titulo}</label>

        <div className="flex justify-center gap-5">
          <Button text="Abrir Atividade" handleClick={() => handleOpen()}/>
          {/* <Button isHidden={false} text="Remover Atividade" handleClick={handleDelete} color="danger" /> */}
          <Button isHidden={false} text="Entregar atividade" handleClick={() => handlePublish(data)} color="success" />
        </div>
      </CardBody>
    </Card>
  </div>
}