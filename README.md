# Prática 05 - Leitura e Escrita de Arquivos

Implementação da Prática 05 da disciplina de Linguagem de Programação II. O
projeto simula o controle de estoque de uma papelaria, carregando produtos de
um arquivo CSV, calculando o valor total do estoque e exportando os dados para
JSON.

## Versão do JDK

A versão utilizada neste projeto foi verificada com `java -version`:

```text
openjdk version "25.0.4.1" 2026-08-18 LTS
```

## Como compilar e executar

Execute os comandos a partir da raiz do projeto.

Compile os arquivos `.java`:

```bash
javac -d bin *.java
```

Execute a aplicação principal com `java`:

```bash
java -cp bin Main
```

O programa carrega `produtos.csv`, lista os produtos, exibe o valor total,
salva `produtos.json` e carrega o JSON novamente. Para os dados fornecidos, o
valor total esperado é:

```text
Valor total em estoque: R$ 4823,20
```

## Como executar os testes

Compile o projeto com `javac` e execute `TesteEstoque` com `java -ea`. A opção
`-ea` habilita as asserções usadas nos testes:

```bash
javac -d bin *.java
java -ea -cp bin TesteEstoque
```

Os testes verificam:

- ida e volta dos produtos nos formatos CSV e JSON;
- tentativa de carregar arquivo inexistente;
- CSV com preço inválido;
- JSON sem o campo `quantidade`.

Os arquivos temporários criados pelos testes são removidos ao final da
execução.

## O que mudou na classe `Estoque` para trocar CSV por JSON?

Não foi necessário alterar a classe `Estoque` para trocar CSV por JSON. Ela
recebe um objeto do tipo `Formato` nos métodos `carregar` e `salvar`, em vez de
depender diretamente de `FormatoCsv` ou `FormatoJson`.

```java
FormatoCsv formatoCsv = new FormatoCsv();
List<Produto> produtos = estoque.carregar(
        Path.of("produtos.csv"), formatoCsv);

FormatoJson formatoJson = new FormatoJson();
estoque.salvar(produtos, Path.of("produtos.json"), formatoJson);
```

Essa abstração permite trocar a implementação de serialização sem modificar
`Estoque`, o objeto `FormatoCsv` interpreta CSV e o objeto `FormatoJson`
interpreta JSON. Assim, a classe permanece independente dos formatos
concretos, seguindo o polimorfismo definido pela interface `Formato`.
