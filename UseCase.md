```plantuml
@startuml

scale 0.9
left to right direction

rectangle "Sistema de Relatos de Problemas Urbanos"{

usecase "Fazer Login" as UC001
usecase "Relatar Problema" as UC002
usecase "Classificar Relato" as UC003
usecase "Consultar Relatos" as UC004
}

actor "Usuário" as a001
actor "Administrador" as a002

UC002 .> UC001: <<extends>>
a001 -- UC001
a001 -- UC002
UC003 .> UC004: <<extends>>
a002 -- UC003

UC004 -- a001
UC004 -- a002

@enduml
```

|2 Caso de Uso      | Relatar Problema |
| ------------- | ------------- |
| Ator principal |Usuário|
| Resumo | O usuário relata um problema urbano na plataforma. |
|Pré-condições | O usuário deve estar autenticado.|
|Pós-condições | Relato registrado com sucesso. |

### Fluxo principal

| Usuário    | Sistema      |
| ------------- | ------------- |
| 1. Insere a descrição, localização e anexos do problema. |   |
|  | 2. Valida as informações e registra o relato. |
|3. Confirma o envio do relato.| |
|| 4. Exibe mensagem de enviado.|

### Fluxo alternativo CA 1 - Usuário não autenticado

| Secretária      | Sistema      |
| ------------- | ------------- |
| | 2. Usuário não autenticado. Executar UC006 Autenticação de Usuário e retornar ao passo 2..|


