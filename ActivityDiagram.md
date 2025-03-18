```plantuml
@startuml

:Abre o aplicativo;

if (Usuário logado?) then (Sim)
    :Permite entrada;
else (Não)
    :logar;
    endif
    :Permite entrada;
endif

:Mostra menu principal;

while (Sair do app?) is (Não)

switch (Ambiente selecionado)
case ( Cadastro Pet )
    switch (Funcionalidade selecionada)
    case (Cadastrar Pet)
        :Adiciona dados do Pet;
    case (Atualizar dados)
        :Atualiza dados do Pet;
    case (Remover)
        :Exclui Pet;
    endswitch
case ( Pet Care ) 
    switch (Seção selecionada)
    case (Área de Alimentação)
        switch (Card selecionado)
        case (Dicas)
            :Lê dicas\nde alimentação;
        case (Rotina)
            :Insere informações\nrelacionadas\nà alimentação;
        endswitch
    case (Área de Saúde)
        switch (Card selecionado)
        case (Medicamentos)
            :Confere medicações\na serem administradas;
        case (Área Vet)
            :Verifica informações\nde consultas e exames;
        endswitch
    endswitch
case ( Entretenimento e Adestramento )
    switch (Card selecionado)
    case (Entretenimento)
        :Confere dicas de atividades;
    case (Locais Pet-friendly)
        :Confere locais de entretenimento;
    case (Adestramento)
        :Confere dicas de adestramento;
    endswitch
endswitch

endwhile (Sim)
stop
@enduml
```
