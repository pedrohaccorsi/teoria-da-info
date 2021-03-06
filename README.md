# Teoria da Informação

Trabalho desenvolvido para a cadeira de Teoria da Informação da Universidade Unisinos.

## Requisitos de sistema

- Java 16
- SDK 16

## Instruções de execução 

Baixe o arquivo [teoria-da-info-ga.jar](https://github.com/pedrohaccorsi/teoria-da-info/raw/main/download/teoria-da-info-ga.jar) e ponha ele no mesmo diretório que os arquivos a serem codificados e/ou decodificados, como no exemplo abaixo:

```
 -|grupo-pedro-mateus-joao-felipe
  | ----- teoria-da-info-ga.jar 
  | ----- alice29.txt
```

Feito isso, execute o seguinte comando:

```
java -Xss256M -jar teoria-da-info-ga.jar
```

Agora siga os passos mostrados no terminal. Nele, será pedido qual tipo de operação tu quer fazer:

1. Encode
2. Ecc encode
3. Ecc decode
4. Decode

Cada uma das operações vai consultar o resultado da operação anterior, então é importante executar na ordem.
Os arquivos resultados serão criados dentro de diretórios novos na raiz da execução, facilitando a leitura.

Obs.: arquivos maiores levam um certo tempo para serem codificados, e ainda mais para serem decodificados, pois a pressa é inimiga da perfeição.

## Autores

- João Augusto Tonial Laner
- Felipe Veronezi Peters
- Pedro Henrique Accorsi
- Mateus Melchiades


