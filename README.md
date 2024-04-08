# Desafio-Elo7_API

Resumo do Projeto:

O Sistema é constituído por 3 Controllers sendo eles: Galaxy(Galáxia), Planet(Planeta) e Probe(Sonda).

**Galaxy Controller:**

**URL: http://localhost:4500/api/galaxy**

- **GET;** Retorna todas as Galáxias criadas.
- **POST;** Cria uma nova Galáxia, é necessário enviar um Body JSON com o campo "name".

**Planet Controller:**

**URL: http://localhost:4500/api/planet**

- **GET;** Retorna todos os Planetas criados filtrados por Galáxia. 
 **Obs:** É necessário inserir o ID da Galáxia ao final da URL.
 **Ex: http://localhost:4500/api/planet/{galaxyID}**

- **POST;** Cria um novo Planeta, é necessário enviar um Body JSON com o campo "name".
 **Obs:** É necessário inserir o ID da Galáxia ao final da URL.
 **Ex: http://localhost:4500/api/planet/{galaxyID}**


**Probe Controller:**

**URL: http://localhost:4500/api/probe**

- **GET;** Retorna todas as Sondas criadas filtradas por Planeta. 
  **Obs:** É necessário inserir o ID do Planeta no Query Parameter.
  **Ex: http://localhost:4500/api/probe?planetID={planetID}**

- **GET;** Retorna uma única Sonda. 
  **Obs:** É necessário inserir o ID da Sonda ao final da URL.
  **Ex: http://localhost:4500/api/probe/{probeID}**

- **POST;** Cria uma nova Sonda, é necessário enviar um Body JSON com o campo "name".
  **Obs:** É necessário inserir o ID do Planeta ao final da URL.
  **Ex: http://localhost:4500/api/probe/{planetID}**


- **PUT;**  Altera uma Sonda de Planeta, é necessário inserir o ID dos dois planetas e o da Sonda na URL.
  **Ex: http://localhost:4500/api/probe/{oldPlanetID}/{probeID}/{newPlanet}/change**

- **PUT;**  Movimenta a Sonda, é necessário enviar um Body JSON com o campo "commands".
  **Obs:** É necessário inserir o ID do Planeta e da Sonda na URL.
  **Ex: http://localhost:4500/api/probe/{planetID}/{probeID}/move**

- **DELETE;** Deleta uma Sonda, é necessário inserir o ID da Sonda e do Planeta na URL.
  **Ex: http://localhost:4500/api/probe/{planetID}/{probeID}/delete**

**Regras de Negócio:**

- Não é possível haver mais de 25 Sondas em um mesmo Planeta. É possível haver apenas uma Sonda por posição no Planeta.
- Todas as Sondas tem como padrão a posição de pouso X = 1 e Y = 1. Caso já haja uma Sonda nesta posição, ela será movida para uma posição vazia.
- São aceitos no máximo 100 comandos por vez. Caso a Sonda seja movida para um lugar já ocupado, ela vai ser movida para uma posição vazia automaticamente.
- Todos os nomes não podem ultrapassar o limite de 20 caracteres ou conter os caracteres; "/ \" ou espaços em branco.
