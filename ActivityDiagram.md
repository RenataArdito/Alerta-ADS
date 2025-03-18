@startuml

start

:Abre a página web;
:Abre a página de Login;

if (Usuário logado?) then (Sim)
    if (É administrador?) then (Sim)
        if (Opções) then (Avaliar Solicitações)
            :Visualiza solicitações pendentes;
            :Atualiza status e acrescenta descrição de status;
            stop
        else (Ver Histórico)
            :Ver Histórico de Avaliações;
            stop
        endif
    else (Não - Usuário Comum)
        if (Opções) then (Fazer Solicitação)
            :Preencher Solicitação;
            :Visualizar solicitações enviadas;
            stop
        else (Ver Histórico)
            :Visualizar solicitações enviadas;
            stop
        endif
    endif
else (Não)
    :Fazer Login;
    back to :Abre a página de Login;
endif

@enduml