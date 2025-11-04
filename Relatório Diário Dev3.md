Data: 04/11/2025
Branch: feature/crud-registro

1. Estrutura e Organização

Clonagem do repositório Umprojetodep2 via terminal (git clone).

Criação da branch feature/crud-registro a partir de develop.

Configuração e autenticação com o GitHub via IntelliJ.

Ajuste do package base para com.gestao_habitos_saudaveis.

Confirmação da compilação e funcionamento do projeto.

2. Camada Model — Representação de Entidades

Foram criadas as classes responsáveis por representar os registros diários e de hábitos:

Classe	            | Descrição
RegistroDiario.java | Representa o registro diário de um usuário, armazenando data e lista de hábitos.
RegistroHabito.java | Representa o registro individual de um hábito em um determinado dia.

As classes incluem construtores, métodos de acesso (getters/setters) e relacionamento via List<RegistroHabito>.

3. Camada Exception — Tratamento de Erros
   Classe	                            | Função
   RecursoNaoEncontradoException.java	| Exceção personalizada lançada quando um registro não é encontrado.
   DadosInvalidosException.java	Exceção | personalizada lançada para dados de entrada inválidos.

Foi necessário corrigir um erro de refatoração que gerou um package incorreto (om.gestao_habitos_saudaveis.exception), posteriormente ajustado para o nome correto.
Após a correção, as classes foram reconhecidas normalmente pelo IntelliJ.

4. Camada Repository — Persistência em Memória
   Arquivo                 | Função
   RegistroRepository.java | Responsável por simular a persistência de dados utilizando ArrayList, com métodos CRUD para RegistroDiario e RegistroHabito.

Métodos implementados:

listarRegistrosDiarios()

buscarRegistroDiarioPorId(Long id)

salvarRegistroDiario(RegistroDiario registro)

deletarRegistroDiario(Long id)

listarRegistrosHabitos()

buscarRegistroHabitoPorId(Long id)

salvarRegistroHabito(RegistroHabito registro)

deletarRegistroHabito(Long id)

5. Camada Service — Regras de Negócio
   Arquivo	            | Descrição
   RegistroService.java | Implementa a lógica de negócio e validações, conectando a camada Repository à Controller.

Funções principais:

listarRegistrosDiarios() — Retorna todos os registros diários.

buscarRegistroDiarioPorId(Long id) — Busca um registro diário pelo ID.

salvarRegistroDiario(RegistroDiario registro) — Valida e adiciona novo registro.

deletarRegistroDiario(Long id) — Remove registro existente.

Métodos equivalentes para RegistroHabito.

Inclui lançamento de exceções personalizadas em caso de dados inválidos ou registros inexistentes.

6. Camada Controller — Interface Simulada
   Arquivo	               | Descrição
   RegistroController.java | Controla as operações CRUD simuladas, chamando os métodos da camada Service.

Métodos correspondentes a ações típicas de uma API REST:

Listar, buscar, criar e deletar registros diários e de hábitos.
(Implementação sem endpoints REST reais neste estágio do projeto.)

7. Histórico de Commits
   Ordem | Commit	                             | Tipo
   1	 | feat(dev3): CRUD completo de Registro | Feature
8. Situação da Branch

Branch feature/crud-registro criada e sincronizada com sucesso.

Estrutura de CRUD implementada conforme o roteiro do projeto.

Commit e push realizados sem conflitos.

Aguardando integração futura com as branches feature/crud-habito e feature/crud-usuario.

9. Próximos Passos

Implementar GlobalExceptionHandler.java para tratamento centralizado de exceções.

Integrar os módulos dos três desenvolvedores.

Iniciar os testes de validação e integração.

Status Final (04/11/2025):
CRUD do módulo Registro finalizado e versionado com sucesso na branch pessoal.