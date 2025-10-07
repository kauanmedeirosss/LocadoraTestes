# 🧪 LocadoraTestes - Projeto de Estudo em Testes Automatizados

## 📖 Sobre o Projeto
Projeto de uma API para locadora de veículos com foco exclusivo em implementação de testes automatizados, demonstrando diferentes estratégias, ferramentas e boas práticas para garantir qualidade e confiabilidade do software.

---

## 🏗️ Stack Tecnológica
### 🧩 Frameworks de Teste
* JUnit 5 - Estrutura principal de testes
* AssertJ - Asserts fluentes e legíveis
* Mockito - Mocking de dependências
* Jacoco - Cobertura de testes

---

## 🗄️ Banco de Dados
* H2 Database - Banco em memória para testes
* Spring Data JPA - Persistência e repositórios

---

## 🔧 Outras Ferramentas
* JSONPath - Verificação de respostas JSON
* Spring Boot Test - Testes de integração
* Test Containers - Isolamento de testes

---

## 🎯 Estrutura dos Componentes
### 🚗 Carro
* Entidade principal do sistema
* Atributos: modelo, valorDiaria, disponibilidade
* Responsável por cálculos de valor

### 👤 Cliente
* Usuário da aplicação
* Atributo: nome

### 📋 Reserva
* Associa Cliente × Carro
* Calcula valor total do aluguel
* Valida regras de negócio

---

## 🧪 Estratégias de Teste Implementadas
### ✅ Testes Unitários
* `@Mock` e `@InjectMocks`
* Verificação com `Mockito.verify()`
* Isolamento completo de dependências

### 🔄 Testes de Serviço
* Mock de repositórios JPA
* Cenários de CRUD completo
* Validação de regras de negócio

### 🗄️ Testes de Persistência
* Configuração com H2
* `@DataJpaTest` para testes JPA
* Scripts SQL para cenários complexos
* Verificação de queries e mapeamentos

### 🌐 Testes de Integração
* `@SpringBootTest` para contexto completo
* Testes de endpoints REST
* Validação com JSONPath
* Verificação de status HTTP e corpo de resposta

### 🧩 Testes Funcionais
* GET - Listagem e busca
* POST - Criação de recursos
* PUT - Atualização de dados
* DELETE - Remoção com validações

## 📊 Métricas de Qualidade
### 🔍 Cobertura de Testes (Jacoco)
* Relatórios na IDE
* Cobertura mínima configurada
* Análise por pacote/classe

### 🎭 Cenários de Teste
* ✅ Cenário Feliz
* ❌ Cenário de Exceção
* ⚠️ Cenário de Borda
* 🔄 Cenário de Estado

### 🚀 Como Executar
#### ▶️ Executar Todos os Testes
```bash

./mvnw test
```

#### 📈 Gerar Relatório de Cobertura
```bash

./mvnw jacoco:report
```

🎯 Executar Testes Específicos
```bash

./mvnw test -Dtest="*Reserva*"
```

## 📝 Estrutura de Pastas
```text
src/test/
├── unit/           # Testes unitários
├── integration/    # Testes de integração  
├── functional/     # Testes funcionais
├── resources/
│   ├── sql/       # Scripts para cenários
│   └── data/      # Massas de teste
└── reports/       # Relatórios Jacoco
```