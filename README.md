# Bora!

Aplicativo Android simples para conferir o que levar ao sair de casa.
Escolha uma lista (trabalho, academia ou viagem), marque os itens e acompanhe
quantos já foram conferidos. Ao completar a lista, aparece “Tudo conferido. Bora!”.
É possível desmarcar todos para repetir a conferência.

## Escopo do trabalho

- Duas Activities com layouts Android Views em XML.
- Intent explícita de HomeActivity para ChecklistDetailActivity, passando o ID da lista.
- Apenas ViewBinding para acessar e inflar as Views.
- Modelos Checklist e ChecklistItem como data classes com propriedades val.
- Observação opcional String?, escondida quando ausente ou vazia.
- Dados simulados em ChecklistMocks, sem API, banco, login ou permissões.
- Componentes XML reutilizáveis para os cartões e itens da lista.
- Estado de marcação restaurado pelo Bundle ao recriar a tela, como na rotação.
- ID ausente ou desconhecido apresenta uma mensagem e opção de voltar.

As listas são fixas. As marcações valem enquanto a tela de conferência estiver
aberta: voltar e abrir uma lista novamente inicia outra conferência.
Não há armazenamento permanente nem limpeza automática por data.

## Organização

```text
app/src/main/
  java/com/example/adotapet/
    HomeActivity.kt
    ChecklistDetailActivity.kt
    model/Checklist.kt
    data/ChecklistMocks.kt
  res/
    layout/activity_home.xml
    layout/activity_checklist_detail.xml
    layout/home_list_item_layout.xml
    layout/checklist_item_layout.xml
    drawable/                 # Fundos e ícone XML
    values/                   # Textos, cores e estilos
  AndroidManifest.xml
```

## Executar

Abra esta pasta no Android Studio, sincronize o Gradle e execute o módulo app.
Requer SDK 35, JDK 17 ou superior e dispositivo com Android 7.0 (API 24) ou superior.
O JDK integrado ao Android Studio pode ser usado.

```powershell
.\gradlew.bat :app:assembleDebug :app:lintDebug
```

APK de teste: app/build/outputs/apk/debug/app-debug.apk.

## Roteiro de conferência no celular

1. Abra cada cartão e confira se título e itens correspondem à lista selecionada.
2. Marque e desmarque um item: o contador deve acompanhar a alteração.
3. Marque todos: a mensagem deve mudar para “Tudo conferido. Bora!”.
4. Toque em “Desmarcar todos”: o contador deve voltar a zero.
5. Marque alguns itens e gire a tela: as marcações devem permanecer.
6. Volte pelo botão da tela ou pelo Android e abra novamente: a lista começa desmarcada.
7. Verifique itens com e sem observações, fonte ampliada e rolagem em tela pequena.
