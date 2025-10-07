# Commits explicados
* 1 -> Upload inicial do projeto startado
* 2 -> Model de carro criado e primeiro teste
* 3 -> Conceitos explicados na anotação e novo teste escrito
* 4 -> Classe model e test Cliente criadas e nova biblioteca de testes usada
* 5 -> Classe model e test Reserva criadas, mostrado também forma de melhorar testes com método setUp que usa a anotação @BeforeEach para inicialização, esse método zera o retrabalho de instancia. Formas de testar exceções também demonstradas
* 6 -> Demonstração do BeforeAll, AfterAll e Disabled na classe DatabaseTest
* 7 -> Configurando conexão da aplicação com banco h2 e adicionando dependencia do jpa. Foi criado o properties do ambiente de teste
* 8 -> Foi definida uma estratégia para separar Model × Entity (estratégia de mercado) e criado repository de carro e teste para repository de carro
  - Model (ou DTO - Data Transfer Object)
    * Contém regras de negócio
  - Entity (JPA Entity)
    * Cuida da persistência no banco
* 9 -> usando arquivo sql para carregar registros no banco e montar cenário de teste
* 10 -> fazendo CRUD de testes no CarroRepositoryTest
* 11 -> Criando service de carro e conhecendo o mockito com 2 métodos que exibem seu comportamento em CarroServiceTest
* 12 ->