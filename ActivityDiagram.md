```plantuml
@startuml

:Abre o aplicativo;

if (Usuário logado?) then (Sim)
    :Permite entrada;
else (Não)
    :logar;
endif
:Mostra menu principal;

while (Escolher opções?) is (True)

switch (Opções)
case ( Fazer Solicitação )
    switch (Funcionalidade selecionada)
    case (Tipo de Solicitação)
        :Escolhe tipo de solicitação;
    case (Adicionar endereço)
        :Localização do evento;
    case (Adicionar descrição)
        :Detalhes da solicitação;
    endswitch
case ( Visualizar Histórico ) 
    switch (Histórico)
    case (Ver Status do Histórico)
    case (Ver Detalhes do Histórico)
case ( Ver Detalhes do Histórico )
    switch (Card selecionado)
    case (Entretenimento)
        :Confere dicas de atividades;
    case (Locais Pet-friendly)
        :Confere locais de entretenimento;
    case (Adestramento)
        :Confere dicas de adestramento;
    endswitch
endswitch



    

@enduml
```
