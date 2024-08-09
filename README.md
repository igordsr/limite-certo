# limite-certo
Sistema eficiente para o processamento de pagamentos de operadoras de cartão de crédito, validando o limite disponível dos clientes em tempo real.

## 1. Visão Geral do Sistema

O sistema foi desenvolvido para processar pagamentos de operadoras de cartão de crédito de forma eficiente. O objetivo principal é receber os dados das transações com cartão de crédito e verificar se o cartão do cliente possui limite disponível para a realização da compra. O sistema garante a validação precisa e em tempo real do limite de crédito dos clientes, proporcionando uma experiência de pagamento segura e confiável.

## 2. Arquitetura

O projeto é baseado em uma arquitetura de microsserviços, utilizando o ecossistema Spring para a implementação. Cada serviço possui uma base de dados isolada, garantindo autonomia e comunicação eficiente entre os componentes.

- **Backend:** Baseado em MVC.
- **Banco de Dados:** Utiliza MySQL para persistência de dados.

### Justificativa para a Arquitetura MVC

A escolha pela arquitetura MVC neste projeto visa organizar e modularizar o código de forma eficiente. Cada camada tem uma função específica:
- **Model:** Responsável pelos dados e pela lógica de negócios.
- **View:** Cuida da interface com o usuário.
- **Controller:** Atua como intermediário, processando as entradas do usuário e determinando como os dados devem ser apresentados.

**No código fornecido:**
- **Controllers:** Os controladores (`ClienteController`, `CartaoController`, `PagamentoController`, `AutenticacaoController`) recebem as requisições do usuário, processam-nas e devolvem uma resposta adequada, chamando os serviços necessários.
- **Services:** As classes de serviço (`ClienteService`, `CartaoService`, `PagamentoService`, `JwtUserDetailsService`) contêm a lógica de negócios e manipulam os dados fornecidos pelos modelos, realizando operações como cadastrar um cliente ou autenticar um usuário.
- **Models:** As entidades (`Cliente`, `Cartao`, `Pagamento`, `UserLogin`) representam os dados do negócio e são manipuladas pelos serviços, sendo usadas para mapear e gerenciar as informações persistidas no banco de dados.

Essa estrutura facilita a manutenção e a escalabilidade do sistema, permitindo a modificação de uma camada sem impactar as demais e promovendo a reutilização de componentes. A separação clara das camadas também facilita a criação de testes unitários, garantindo a integridade e confiabilidade do código.

## 3. Documentação da API com Swagger

Para acessar a documentação detalhada dos endpoints da API, utilize o seguinte link:
[http://localhost:8080/api/swagger-ui/index.html#/](http://localhost:8080/api/swagger-ui/index.html#/)

## 4. Guias de Implantação

#### Requisitos

- Docker e Docker Compose instalados.

##### Passos de Implantação

1. **Clone o repositório**:
    ```sh
    https://github.com/igordsr/limite-certo.git
    ```
2. **Navegue até o diretório do projeto**:
    ```sh
    cd limite-certo
    ```
3. **Execute o Docker Compose**:
    ```sh
    docker-compose up --build
    ```