# Desafio-Elo7_API

Resumo do Projeto:

O Sistema é constituído por 4 classes que são controladas por seus respectivos Controllers, são elas: 
Galaxy(Galáxia), Planet(Planeta), Probe(Sonda) e Terminal.

**Galaxy(Galáxia) e GalaxyController:**
- Contém os campos, **"id"**, **"name"** e **"planetsIDs"**.
- Para criar uma nova Galáxia é necessário, enviar uma requisição para o endereço: "/api/galaxy/post", com um Body JSON contendo o campo **"name"**.
  O campo "name" não pode conter os carecteres "**/ \**" ou espaços em branco.
- É possível analisar todas as Galáxias já criadas enviando uma requisição para o endereço: "/api/galaxy/get".
  **Obs:** Não é possível analisar apenas uma única Galáxia, porém caso necessário, é possível analisar todos os planetas contidos na Galáxia via PlanetController.

**Planet(Planeta) e PlanetController:**
- Contém os campos, **"id"**, **"name"** e **"probesIDs"**.
- Para criar um novo Planeta é necessário que já haja uma Galáxia criada, feito isso, pode ser enviado uma requisição para o endereço: "/api/planet/{galaxyID}/post", com um Body JSON contendo o campo **"name"**.
  O campo "name" não pode conter os carecteres "**/ \**" ou espaços em branco.
- É possível analisar todos os Planetas criados por Galáxia, enviado uma requisição para o endereço: "/api/planet/{galaxyID}/get"
  **Obs:** Não é possível analisar apenas um único Planeta, porém caso necessário, é possível analisar todas as Sondas contidas no Planeta via ProbeController.
           Também não é possível analisar todos os Planetas já criados, apenas filtrados por Galáxia.

**Probe(Sonda) e ProbeController:**
- Contém os campos, **"id"**, **"name"**, **"positionInX"**, **"positionInY"** e **"guidance"**.
- Para criar uma Sonda é necessário que haja um Planeta criado, feito isso, pode ser enviado uma requisição para o endereço: "/api/probe/{planetID}/post". com um Body JSON contendo o campo **"name"**.
  O campo "name" não pode conter os carecteres "**/ \**" ou espaços em branco.
- Alterar uma Sonda de Planeta envie uma requisição para o endereço: "/api/probe/{oldPlanetID}/{probeID}/{newPlanetID}/change".
- Deletar uma Sonda existente, envie uma requisição para o endereço: "/api/probe/{planetID}/{probeID}/delete".
- É também possível pegar todas as Sondas em um determinado Planeta enviando uma requisição para o endereço: "/api/probe/{planetID}/get".
- Pegar uma Sonda em especifíco, envia uma requisção para o endereço: "/api/probe/{probeID}/getOne".

**Regras de Negócio:**

- Não é possível haver mais de 25 Sondas em um mesmo Planeta. É possível Haver apenas uma sonda por posição no planeta.
- Todas as sondas tem como padrão a posição de Pouso X = 1 e Y = 1. Caso já haja uma Sonda nesta posição, ela será movida para uma posição vazia.
- São aceitos no máximo 100 comandos por vez. Caso a sonda seja movida para um lugar já ocupado, ela vai ser movida para uma posição vazia automaticamente.
