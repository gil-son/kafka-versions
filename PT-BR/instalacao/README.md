#  Configurações do Kafka 3.0.0

<details><summary>Mac</summary>
<p>

- Certifique-se de estar dentro do diretório bin.

## Start Zookeeper and Kafka Broker

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
./kafka-topics.sh --create --topic test-topic --replication-factor 1 --partitions 4 --bootstrap-server localhost:9092
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

### Consumir mensagens com 'Kafka Headers':

```
./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic library-events.DLT --from-beginning --property print.headers=true --property print.timestamp=true
```

</p>

</details>

<details><summary>Windows</summary>
<p>

- Make sure you are inside the **bin/windows** directory.

## Inicie o Zookeeper e o Kafka Broker

-   Inicie o Zookeeper:

```
zookeeper-server-start.bat ..\..\config\zookeeper.properties
```

-   Inicie o Kafka Broker:

```
kafka-server-start.bat ..\..\config\server.properties
```

## Como criar um tópico ?

```
kafka-topics.bat --create --topic test-topic  --replication-factor 1 --partitions 4 --bootstrap-server localhost:9092
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

### Consuma mensagens com 'Kafka Headers':

```
kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic library-events.DLT --from-beginning --property print.headers=true --property print.timestamp=true
```

</p>

</details>

## onfigurando vários 'Kafka Brokers'

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

- Forneça o novo **server.properties** e adicione.

```
./kafka-server-start.sh ../config/server-1.properties
```

```
./kafka-server-start.sh ../config/server-2.properties
```

# Operações avançadas de CLI do Kafka:

<details><summary>Mac</summary>
<p>

## Listar os tópicos em um cluster:

```
./kafka-topics.sh --bootstrap-server localhost:9092 --list
```

## Descreva o tópico:

- O comando abaixo pode ser usado para descrever todos os tópicos:

```
./kafka-topics.sh --bootstrap-server localhost:9092 --describe
```

- O comando abaixo pode ser usado para descrever um tópico específico:

```
./kafka-topics.sh --bootstrap-server localhost:9092 --describe --topic <topic-name>
```

## Alterar a réplica insíncrona mínima:
```
./kafka-configs.sh  --bootstrap-server localhost:9092 --entity-type topics --entity-name library-events --alter --add-config min.insync.replicas=2
```

## Alterar as partições de um tópico:
```
./kafka-topics.sh --bootstrap-server localhost:9092 --alter --topic test-topic --partitions 40
```

## Deletar um tópico:

```
./kafka-topics.sh --bootstrap-server localhost:9092 --delete --topic test-topic
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
./kafka-configs.sh --alter --bootstrap-server localhost:9092 --entity-type topics --entity-name test-topic --add-config min.insync.replicas=2
```
</p>
</details>


<details><summary>Windows</summary>
<p>

- Certifique-se de estar dentro do diretório **bin/windows**.

## Listar os tópicos em um cluster:

```
kafka-topics.bat --bootstrap-server localhost:9092 --list
```

## Descreva o tópico:

- O comando abaixo pode ser usado para descrever um tópico específico:

```
kafka-topics.bat --bootstrap-server localhost:9092 --describe
```

- The below command can be used to describe a specific topic:

```
kafka-topics.bat --bootstrap-server localhost:9092 --describe --topic <topic-name>
```

## Alterar a réplica insíncrona mínima:
```
kafka-configs.bat --bootstrap-server localhost:9092 --entity-type topics --entity-name library-events --alter --add-config min.insync.replicas=2
```
## Alterar as partições de um tópico:
```
kafka-configs.bat --bootstrap-server localhost:9092 --alter --topic test-topic --partitions 40
```

## Deletar um tópico:

```
kafka-topics.bat --bootstrap-server localhost:9092 --delete --topic <topic-name>
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
