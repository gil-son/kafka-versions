# Configurações do Kafka

<details><summary>Mac</summary>
<p>

- Certifique-se de estar dentro do diretório bin.

## Inicie o Zookeeper e o Kafka Broker

-   Inicie o Zookeeper:

```
./zookeeper-server-start.sh ../config/zookeeper.properties
```

- Adicione as propriedades abaixo no server.properties (opcional):

```
listeners=PLAINTEXT://localhost:9092
auto.create.topics.enable=false
```

-   Inicie o Kafka Broker:

```
./kafka-server-start.sh ../config/server.properties
```

## Como criar um tópico?

```
./kafka-topics.sh --create --topic test-topic -zookeeper localhost:2181 --replication-factor 1 --partitions 4
```

## Como instanciar um Produtor no console?

### Sem chave:

```
./kafka-console-producer.sh --broker-list localhost:9092 --topic test-topic
```

### Com chave:

```
./kafka-console-producer.sh --broker-list localhost:9092 --topic test-topic --property "key.separator=-" --property "parse.key=true"
```

## Como instanciar um Consumidor no console?

### Sem chave:

```
./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test-topic --from-beginning
```

### Com chave:

```
./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test-topic --from-beginning -property "key.separator= - " --property "print.key=true"
```

### Com grupo de consumidores:

```
./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test-topic --group <group-name>
```
</p>

</details>

<details><summary>Windows</summary>
<p>

- Certifique-se de estar dentro do diretório **bin/windows**.

## Inicie o Zookeeper e o Kafka Broker

-   Inicie o Zookeeper:

```
zookeeper-server-start.bat ..\..\config\zookeeper.properties
```

-   Inicie o Kafka Broker:

```
kafka-server-start.bat ..\..\config\server.properties
```

## Como criar um tópico?

```
kafka-topics.bat --create --topic test-topic -zookeeper localhost:2181 --replication-factor 1 --partitions 4
```

## Como instanciar um Produtor no console?

### Sem chave:

```
kafka-console-producer.bat --broker-list localhost:9092 --topic test-topic
```

### Com chave:

```
kafka-console-producer.bat --broker-list localhost:9092 --topic test-topic --property "key.separator=-" --property "parse.key=true"
```

## Como instanciar um Consumidor no console?

### Sem chave:

```
kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic test-topic --from-beginning
```

### Com chave:

```
kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic test-topic --from-beginning -property "key.separator= - " --property "print.key=true"
```

### Com grupo de consumidores:

```
kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic test-topic --group <group-name>
```
</p>

</details>

## Configurando vários 'Brokers'

- O primeiro passo é adicionar um novo **server.properties**.

- Precisamos modificar três propriedades para iniciar uma configuração de vários 'Brokers':

```
broker.id=<unique-broker-d>
listeners=PLAINTEXT://localhost:<unique-port>
log.dirs=/tmp/<unique-kafka-folder>
auto.create.topics.enable=false
```

- Exemplo de configuração:

```
broker.id=1
listeners=PLAINTEXT://localhost:9093
log.dirs=/tmp/kafka-logs-1
auto.create.topics.enable=false
```

### Iniciando um novo 'Broker'

- Forneça o novo **server.properties** e adicione:

```
./kafka-server-start.sh ../config/server-1.properties
```

```
./kafka-server-start.sh ../config/server-2.properties
```

# Operações avançadas de CLI do Kafka

<details><summary>Mac</summary>
<p>

## Listar os tópicos em um cluster:

```
./kafka-topics.sh --zookeeper localhost:2181 --list
```

## Descreva o tópico:

- O comando abaixo pode ser usado para descrever todos os tópicos.

```
./kafka-topics.sh --zookeeper localhost:2181 --describe
```

- O comando abaixo pode ser usado para descrever um tópico específico.

```
./kafka-topics.sh --zookeeper localhost:2181 --describe --topic <topic-name>
```

## Alterar a réplica insíncrona mínima:
```
./kafka-topics.sh --alter --zookeeper localhost:2181 --topic library-events --config min.insync.replicas=2
```

## Deletar um tópico:

```
./kafka-topics.sh --zookeeper localhost:2181 --delete --topic test-topic
```
## Visualizar grupos de consumidores:

```
./kafka-consumer-groups.sh --bootstrap-server localhost:9092 --list
```

### Deslocamento de grupos de consumidores:

```
./kafka-consumer-groups.sh --bootstrap-server localhost:9092 --describe --group console-consumer-27773
```

## Visualizando o registro de 'commit':

```
./kafka-run-class.sh kafka.tools.DumpLogSegments --deep-iteration --files /tmp/kafka-logs/test-topic-0/00000000000000000000.log
```

## Configurando a réplica insíncrona mínima:

```
./kafka-configs.sh --alter --zookeeper localhost:2181 --entity-type topics --entity-name test-topic --add-config min.insync.replicas=2
```
</p>
</details>


<details><summary>Windows</summary>
<p>

- Certifique-se de estar dentro do diretório **bin/windows**.

## Listar os tópicos em um cluster:

```
kafka-topics.bat --zookeeper localhost:2181 --list
```

## Descreva o tópico

- O comando abaixo pode ser usado para descrever todos os tópicos:

```
kafka-topics.bat --zookeeper localhost:2181 --describe
```

- O comando abaixo pode ser usado para descrever um tópico específico:

```
kafka-topics.bat --zookeeper localhost:2181 --describe --topic <topic-name>
```

## Alterar a réplica insíncrona mínima:
```
kafka-topics.bat --alter --zookeeper localhost:2181 --topic library-events --config min.insync.replicas=2
```


## Deletar um tópico:

```
kafka-topics.bat --zookeeper localhost:2181 --delete --topic <topic-name>
```


## Visualizar grupos de consumidores:

```
kafka-consumer-groups.bat --bootstrap-server localhost:9092 --list
```

### Deslocamento de grupos de consumidores:

```
kafka-consumer-groups.bat --bootstrap-server localhost:9092 --describe --group console-consumer-27773
```

## Visualizando o registro de 'commit':

```
kafka-run-class.bat kafka.tools.DumpLogSegments --deep-iteration --files /tmp/kafka-logs/test-topic-0/00000000000000000000.log
```
</p>
</details>
