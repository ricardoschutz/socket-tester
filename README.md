# socket-tester

Este software foi criado para testar conexão com o Firebird, mas pode ser usado para qualquer teste simples de conexão TCP.

Crie um arquivo .txt contendo os destinos que deseja testar seguindo o exemplo:
```
192.168.2.9
192.168.2.10:3128
www.google.com:443
```

Para customizar a saída adicione o seguinte ao começo do arquivo:
```
separator: ->
okMessage: Conectou!
failMessage: Tempo excedido...
```

Alterar porta e timeout padrão:
```
port:443
timeout:10000
```

## Executar
Em um terminal:
```
java -jar SocketTester.jar < arquivo.txt
```
