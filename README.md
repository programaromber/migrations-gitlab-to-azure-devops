
### Projeto para Migração de repositórios do Gitlab para Azure Devops (Azure Repos)

O projeto foi inicalmente uma ideia minha e do Emerson Silva para ajudar a comunidade Devops para migrações de repositórios do Gilab para a Azure.
Encontramos uma única fonte de ajuda para fazer a operação que era escrita em C#, porém era um video e não possuia repostório.

Segue o link video: https://www.youtube.com/watch?v=RAPEuf9qI_c

## Tecnologias
1. Java 14
2. Spring Batch
3. GitlabApi (lib)
4. Jgit (lib)

## Conceitos
1. Solid.
2. DDD.
3. TDD.


## Start from spring

Command:
```
mvn spring-boot:run
```

## Ambiente.properties

- Alterar as propriedades conforme aas informações abaixo para o Gitlab e Azure Devops
```
migrations.gitlab.uri=[GITLAB_URL]
migrations.gitlab.token=[GITLAB_TOKEN]
migrations.gitlab.path=[NOME_NAMESPACE]
migrations.gitlab.repository=[CASO_SEJA_REPOSITORIO_ESPECIFICO]
migrations.gitlab.username=[GITLAB_USERNAME]
migrations.gitlab.password=[GITLAB_PASSWORD]
migrations.azure.devops.uri=https://dev.azure.com/
migrations.azure.devops.username=[AZURE_GIT_USERNAME]
migrations.azure.devops.password=[AZURE_GIT_PASSWORD]
migrations.azure.devops.token=[TOKEN_AZURE_API]
migrations.azure.devops.organization=[NOME_ORGANIZACAO_AZURE]
migrations.azure.devops.project=[NOME_PROJETO_AZURE]
migrations.azure.devops.api-version=6.0
```

## Licença GPL 3.0.
