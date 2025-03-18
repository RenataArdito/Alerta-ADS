```plantuml
@startuml

:Abre o aplicativo;

if (Usuário logado?) then (Sim)
    :Permite entrada;
else (Não)
    if (Já possui uma conta no app?) then (Sim)
        :Insere credenciais;
        while (Credenciais válidas?) is (Não)
              :Solicita reinserção dos dados;
        endwhile (Sim)
    else (Não)
        :Cria uma conta;
    endif
    :Permite entrada;
endif

:Mostra menu principal;

while (Sair do app?) is (Não)

switch (menu)
case ( Fazer Solicitação )
    switch (Fazer )
    case (Tipo de Relato)
        :Buraco na Rua / Iluminação pública defeituosa / Descarte irregular / Vazamento de água / etc;
    case ( Fazer Solicitação )
    endswitch

case ( Consultar Histórico ) 
    switch (Seção selecionada)
    case(Visualizar Histórico de solicitações)
    endswitch

endwhile (Sim)
stop
@enduml
```
