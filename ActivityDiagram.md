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
    case (Configura Solicitação)
        :Solicitação Enviada;
        switch(Solicitação avaliada por Adm)
    endswitch
endswitch


@enduml
```
