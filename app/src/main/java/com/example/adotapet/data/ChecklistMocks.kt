package com.example.adotapet.data

import com.example.adotapet.model.Checklist
import com.example.adotapet.model.ChecklistItem

object ChecklistMocks {
    val lists: List<Checklist> = listOf(
        Checklist("trabalho", "Dia de trabalho", "Saia com o essencial para um dia organizado.", listOf(
            ChecklistItem("chaves", "Chaves"),
            ChecklistItem("carteira", "Carteira e documento"),
            ChecklistItem("celular", "Celular"),
            ChecklistItem("carregador", "Carregador", "Confira se está na mochila."),
            ChecklistItem("garrafa", "Garrafa de água"),
            ChecklistItem("lanche", "Lanche", "Uma pausa fica melhor com algo na bolsa.")
        )),
        Checklist("academia", "Hora do treino", "Prepare a bolsa antes de sair.", listOf(
            ChecklistItem("garrafa", "Garrafa de água"),
            ChecklistItem("toalha", "Toalha"),
            ChecklistItem("tenis", "Tênis"),
            ChecklistItem("roupa", "Roupa para trocar", "Inclua uma sacola para a roupa usada."),
            ChecklistItem("cadeado", "Cadeado", "Se o armário da academia precisar.")
        )),
        Checklist("viagem", "Fim de semana fora", "Uma lista curta para uma escapada tranquila.", listOf(
            ChecklistItem("documento", "Documento"),
            ChecklistItem("passagem", "Passagens ou reserva", "Confira horário e endereço."),
            ChecklistItem("roupas", "Roupas e roupa de dormir"),
            ChecklistItem("higiene", "Escova e pasta de dentes"),
            ChecklistItem("carregador", "Carregador"),
            ChecklistItem("chaves", "Chaves de casa")
        ))
    )

    fun findById(id: String?): Checklist? = lists.firstOrNull { it.id == id }
}
