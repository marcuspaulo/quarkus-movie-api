
# Quarkus Movie API

<!-- PROJECT LOGO -->
<br />
<p align="center">
  <a href="https://quarkus.io">
  </a>
</p>

## Link para os Artigos
- [Linkedin Pulse](https://www.linkedin.com/pulse/tutorial-quarkus-do-zero-at%25C3%25A9-o-deploy-heroku-java-cdi-da-silva-melo )

- [Medium](https://medium.com/@marcus.paulo/tutorial-quarkus-do-zero-at%C3%A9-o-deploy-no-heroku-utilizando-quarkus-java-rest-cdi-panache-8d702caf451f?sk=e6566ef8cf92e4a717054da4de4bd2fb)

<h1 align="center">
   <img alt="Listar Produtos" title="Listar Produtos" src="./assets/quarkus-logo.png" width="600px" />
</h1>

<!-- TABLE OF CONTENTS -->

## Tabela de Conteúdo

- [Tabela de Conteúdo](#tabela-de-conte%C3%BAdo)
- [Sobre o Projeto](#sobre-o-projeto)
    - [Feito Com](#feito-com)
- [Começando](#come%C3%A7ando)
    - [Pré-requisitos](#pr%C3%A9-requisitos)
    - [Instalação](#instala%C3%A7%C3%A3o)
    - [Edição](#edi%C3%A7%C3%A3o)
    - [Publicação](#publica%C3%A7%C3%A3o)
- [Contribuição](#contribui%C3%A7%C3%A3o)
- [Licença](#licen%C3%A7a)
- [Contato](#contato)

<!-- ABOUT THE PROJECT -->

## Sobre o Projeto

Quarkus API Movie - Integração com a API de filmes do IMBD através do RapidAPI.

### Feito Com

Tecnologias utilizadas no projeto

- [JAVA](https://www.java.com/pt_BR/download/) - Java é uma linguagem de programação e plataforma computacional lançada pela primeira vez pela Sun Microsystems em 1995. Existem muitas aplicações e sites que não funcionarão, a menos que você tenha o Java instalado, e mais desses são criados todos os dias;
- [Quarkus](https://quarkus.io/) - A Red Hat lançou o Quarkus, um framework Java nativo do Kubernetes feito sob medida para o GraalVM e OpenJDK HotSpot. O Quarkus visa tornar o java uma plataforma líder em ambientes serverless e Kubernetes, oferecendo aos desenvolvedores um modelo unificado de programação reativa e imperativa;
- [Hibernate](http://hibernate.org/) - O Hibernate é um framework para o mapeamento objeto-relacional escrito na linguagem Java.
- [Hibernate Validator](https://hibernate.org/validator/)) - Permite implementar validações dos dados

<!-- GETTING STARTED -->

## Começando

Para reproduzir o exemplo, é necessário seguir os requisitos mínimos.

### Pré-requisitos

- Você vai precisar de uma IDE como por exemplo: IntelliJ IDEA, Eclipse, VSCode.
- Instale a JDK 11+
- Instale o Apache Maven 3.6+
- Panache Entity
- Docker (Apenas para subir o banco de dados Postgres Localmente)
- Escolha um cliente para conectar com o Banco de dados, exemplo: DBeaver, PGAdmin, Postico (Mac)
- Cliente para realizar requisições REST: Postman ou o Insomnia.
- Conta no Github (repositório de Código)


#### Docker
- Escolha um cliente para conectar com o Banco de dados, exemplo: DBeaver, PGAdmin, Postico (Mac)
- Cliente para realizar requisições REST: Postman ou o Insomnia.

### Criação da aplicação

1. Para criar o projeto, basta utlizar o template do Maven + Quarkus, conforme o comando abaixo:

```sh
mvn io.quarkus:quarkus-maven-plugin:1.12.2.Final:create \
    -DprojectGroupId=br.com.mp \
    -DprojectArtifactId=quarkus-movie \
    -DclassName="br.com.mp.quarkusmovie.MovieResource" \
    -Dpath="/movies"
```

(Alternativo) - O Quarkus disponibiliza um site chamado `https://code.quarkus.io/`, onde é posísvel configurar o projeto de uma forma mais visual, vale a pena conferir, segue o link: https://code.quarkus.io/

---

#### Executando a Instância do Postgresql no Docker

Para iniciar o Postgresql, basta rodar o comando abaixo (O Docker precisa estar instalado):

```sh
 docker run --name postgres-movie -e  "POSTGRES_PASSWORD=postgres" -p 5432:5432 -v ~/developer/PostgreSQL:/var/lib/postgresql/data -d postgres

```

### Executando o projeto em Quarkus

Para executar um projeto em Quarkus, basta executar o comando:
```sh
mvn compile quarkus:dev
```

<!-- CONTRIBUTING -->

## Contribuição

Fique a vontade para contribuir com o projeto.

1. Faça um Fork do projeto
2. Crie uma Branch para sua Feature (`git checkout -b feature/newFeature`)
3. Adicione suas mudanças (`git add .`)
4. Comite suas mudanças (`git commit -m 'Nova funcionalidade para facilitar ...`)
5. Faça o Push da Branch (`git push origin feature/newFeature`)
6. Abra um Pull Request

-- English
- Make a fork;
- Create a branck with your feature: `git checkout -b my-feature`;
- Commit changes: `git commit -m 'feat: My new feature'`;
- Make a push to your branch: `git push origin my-feature`.

After merging your receipt request to done, you can delete a branch from yours.

## :memo: License

This project is under the MIT license. See the [LICENSE](LICENSE.md) for details.

---

Made with ♥ by Marcus Paulo :wave: [Get in touch!](https://www.linkedin.com/in/marcuspaulo/)
