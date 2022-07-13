# Desafio
## Tarefa Principal
Em uma cooperativa, cada associado possui um voto e as decisões são tomadas através de assembleias, por votação. A partir disso, você precisa criar o backend para gerenciar essas sessões de votação. A solução deve atender os seguintes requisitos através de uma API REST:

- (RF0) Autenticação e autorização.
  - Todo usuário, seja ele administrador ou cooperado (pessoa que votam) deve estar devidamente autenticado para operar o sistema. (Utilize bearer token JWT).
  - Usuários administradores podem realizar todas as operações do sistema. 
  - Usuários cooperados podem apenas votar. 
  - Deve ser possível adicionar, alterar ou excluir usuários.
  - Os dados requisitados para cadastro de usuário são: CPF, nome tipo (administrador ou cooperado) e e-mail.

- (RF1) Cadastrar pauta.
  - A pauta pode ser apenas cadastrada.
  - Toda pauta deve conter um nome da pauta e uma descrição.

- (RF2) Abrir uma sessão de votação para uma pauta.
  - Cada pauta deve comportar apenas uma sessão de votação. 
  - A sessão de votação deve ficar aberta por um tempo determinado na chamada de abertura ou 1 minuto por padrão.

- (RF3) Receber votos dos cooperados em pautas abertas. 
  - Os votos são apenas 'Sim'/'Não'.
  - Cada cooperado é identificado por um id único e pode votar apenas uma vez por pauta. 
  - Registre a data/hora do voto.

- (RF4) Contabilizar os votos e dar o resultado da votação na pauta.
  - Exibir vencedor por maioria simples.
  - Exibir quantidade de votos para cada um dos grupos 'Sim'/'Não'.
  - Exibir percentual para cada um dos grupos 'Sim'/'Não'.
  
  
  
  INCOMPLETO
