# Testes
## Sobre nomeclaturas
No pacote de testes devemos sempre lembrar de adicionar o sufixo 'Test', depois do nome da classe como apresentado no pacote main.  
Outro detalhe a se observar, é que as classes de teste não são public class, são apenas `class`.  
Mantendo os imports das bibliotecas static, podemos criar testes de forma menos verbosa. Isso é mostrado em `ClienteTest`.

## Sobre pacotes
Da mesma forma que organizamos os pacotes em src/main, devemos organizar em src/test.
* Exemplo: 
  * em src/main temos java/... e resources/...
  * em src/test temos, também, java/... e resources/...

## Sobre estrutura e cenários de teste
A menor unidade de algoritmo dentro de uma classe é o método, são eles quem devem ser testados.
### Estrutura
Quando se pensa em criar um teste, devemos pensar nele em 3 partes.
* 1 -> Cenário: "Tenho um carro com tais informações", este é o cenário, o que temos.
* 2 -> Execução: O método que deverá ser executado para o teste.
* 3 -> Verificação: Se o método está realmente funcionando como o desejado.
### Cenários
Para cada método testamos seus cenários, para saber se um método tem mais de um cenário é só observar se o método contém um `if`, `while` ou qualquer outra coisa que possa implicar que aquele método não seja uma `execução linear`.  
Uma execução linear é a execução que tem apenas um procedimento, sem variações.

## Bibliotecas de testes usadas
* org.junit.jupiter.api.Assertions.*; -> Biblioteca padrão do JUnit.
* org.assertj.core.api.Assertions.*; -> Biblioteca complementar (vem junto do springboot) Assertj.

## Sobre tipos de teste
### Testes Unitários
Testa lógica de componentes isolados. Tem Mock.
* Ex: CarroTest, que testou lógica de calculo de valor sem se integrar a nenhum serviço externo (como: bancos de dados, mensagerias, API externas).

### Testes de Integração
Testa lógica de componentes integrados a algum serviço. Não tem Mock.
* Ex: CarroRepositoryTest, testa CRUD realizando conexão com o banco de dados em memória.
* OBS: testes são sempre realizados com bancos em memória, pois fica mais leve para a aplicação rodar.
