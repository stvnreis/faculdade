'use client'

import { Atividade, Usuario } from "@/types";
import { ArticleCard } from "./components/article-card";
import { Button } from "@/components/button/button";
import { useFetch } from "@/lib/hooks/use-fetch";
import { useModal } from "@/lib/hooks/use-modal";
import { SubmitArticleModal } from "./components/submit-article-modal";
import { EntregarAtividadeModal } from "./components/entregar-atividade-modal";
import { useState } from "react";

export default function ArtigosPage() {
  const { isOpen, handleOpen, handleOpenChange } = useModal()
  const { isOpen: isOpenEntregar, handleOpen: handleOpenEntregar, handleOpenChange: handleOpenChangeEntregar } = useModal()
  const {data: atividades} = useFetch<Atividade[]>('atividade')
  const {data: usuarios} = useFetch<Usuario[]>('usuario')
  const [atividadeSelecionada, setAtividadeSelecionada] = useState<Atividade>()

  const addItem = (atividade: Atividade) => {
    const index = atividades?.findIndex((item) => item.id === atividade.id)
    
    if (index) atividades![index] = atividade
    else atividades?.push(atividade)
  }

  const handleEntregar = (atividade: Atividade) => {
    setAtividadeSelecionada(atividade)

    handleOpenChangeEntregar()
  }

  return <div className="flex flex-col px-5 pt-5">
    <div className="flex justify-end">
      <Button 
        isHidden={false} 
        handleClick={() => handleOpen()} 
        text="Criar artigo" 
      />

      <SubmitArticleModal 
        handleOpen={handleOpen} 
        isOpen={isOpen} 
        handleOpenChange={handleOpenChange}
        handleItemSubmit={addItem}
        usuarios={usuarios ?? []}
      />

      <EntregarAtividadeModal 
        handleItemSubmit={() => console.log('oi')}
        handleOpen={handleOpenEntregar}
        handleOpenChange={handleOpenChangeEntregar}
        isOpen={isOpenEntregar}
        atividade={atividadeSelecionada!}
      />
    </div>

    <div className="mt-5 grid grid-cols-4 gap-5">
      {atividades?.map((item) => <ArticleCard usuarios={usuarios ?? []} data={item} key={item.id + item.descricao} handleItemSubmit={addItem} handlePublish={handleEntregar} />)}
    </div>
  </div>
}