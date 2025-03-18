```plantuml
@startuml

:Abre o aplicativo;

if (Usuário logado?) then (Sim)
    :Permite entrada;
else (Não)
    :logar;
endif
if(Usuário: ) then (Comum)
    if (Opções) then (Fazer Solicitação)
        :Configurar Solicitação;
    else (Ver Histórico)
        :Ver detalhes sobre solicitações;
else (Adm)
    :Atualizar Status de solicitações
@enduml
```
