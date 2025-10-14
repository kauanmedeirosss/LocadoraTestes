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

### Testes de Funcionalidade
Testa se a API (seus endpoints) está funcionando do ponto de vista de quem consome a API (o client).
* Ex: CarroControllerTest.

## Cobertura de testes e JaCoCo
### Sobre cobertura de testes
Quando se fala em cobertura de testes, se fala sobre quantos % do código foi testado.  
Para essa cobertura de testes se faz necessária uma feramenta que auxilia a saber quantos % de cada classe e que partes do código não foram testadas ainda.  
Essa é uma prática de qualidade de código/projeto.

### Sobre ferramenta de cobertura da IDE IntelliJ
O próprio IntelliJ possui essa ferramenta de cobertura.  

Acessamos com: click direito na pasta raiz do projeto (neste caso "locadora") > More run/debug > Run "All Tests" with Coverage.
* Esse comando faz com que todos os testes sejam executados com cobertura.
* Após rodar esse comando, é mostrado, no pacore main, ao lado de cada pacote e classe a sua % de cobertura.
* Ao entrarmos em cada classe, vemos na barra lateral esquerda (onde ficam os nº das linhas) em verde o que foi testado e em vermelho o que não foi testado.
  - Em CarroController pode-se observar isso com o método de criar, ele tem uma parte testada e outra não testada.

### Sobre JaCoCo
Uma ferramenta para medir a cobertura de código em projetos

Adicionando o JaCoCo
* Para adicionar ao projeto é necessário colocar o plugin do Jacoco no pom.xml
* Normalmente se configura o plugin para excluir pasta de model e entity, como foi feito (normalmente essas classes não possuem lógica)
* Na parte de execution do plugin está sendo preparado o agente paa coletar dados e gerar relatório após os testes

Rodando o JaCoCo
* No menu maven (parte direita), selecionamos (neste exemplo) locadora > lifecycle > habilitamos testes > damos um clean install
* No terminal dessa run é possível observar o resultado dos testes, ex:
  - [INFO] Tests run: 34, Failures: 0, Errors: 0, Skipped: 0
* Para obter o relatório do jacoco navegamos a pasta do projeto
  - target > site > Jacoco > index.html
* Para abrir o relatório:
  - botão direito em index.html > Open In > Browser > escolher o que mais agrada