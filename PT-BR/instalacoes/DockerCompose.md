#  Configurações do Kafka no Docker Compose e Comandos

<details><summary>Exemplo de arquivo básico</summary>
  <p> exemplo </p>



</details>

<details><summary>Exemplo de arquivos pré-configurados</summary>

<p> exemplo</p>

</details>

## Comandos iniciais
<details><summary>Docker Compose - primeiros comandos</summary>

<p>

- Make sure you are inside the **bin/windows** directory.

### Inicie o Zookeeper e o Kafka Broker

-   Inicie o Zookeeper:

```
zookeeper-server-start.bat ..\..\config\zookeeper.properties
```

-   Inicie o Kafka Broker:

```
kafka-server-start.bat ..\..\config\server.properties
```

### Como criar um tópico ?

```
kafka-topics.bat --create --topic test-topic  --replication-factor 1 --partitions 4 --bootstrap-server localhost:9092
```

### Como instanciar um Produtor no console?

#### Sem chave:

```
kafka-console-producer.bat --broker-list localhost:9092 --topic test-topic
```

#### Com chave:

```
kafka-console-producer.bat --broker-list localhost:9092 --topic test-topic --property "key.separator=-" --property "parse.key=true"
```

### Como instanciar um Consumidor no console?

#### Sem chave:

```
kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic test-topic --from-beginning
```

#### Com chave:

```
kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic test-topic --from-beginning -property "key.separator= - " --property "print.key=true"
```

#### Com grupo de consumidores:

```
kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic test-topic --group <group-name>
```

#### Consuma mensagens com 'Kafka Headers':

```
kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic library-events.DLT --from-beginning --property print.headers=true --property print.timestamp=true
```
</details>



### Operações avançadas de CLI do Kafka:

<details><summary>Docker Compose - avançado</summary>
<p>exemplo</p>
</details>

