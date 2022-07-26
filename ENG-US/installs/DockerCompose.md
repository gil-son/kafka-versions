# Kafka Settings in Docker Compose and Commands

<details><summary>Example Basic File</summary>
<p>Create a file called docker-compose.yml and paste the script:</p>
  
  
  
```
version: '3'
services:
  zookeeper:
    image: confluentinc/cp-zookeeper:7.0.1
    container_name: zookeeper
    environment:
      ZOOKEEPER_CLIENT_PORT: 2181
      ZOOKEEPER_TICK_TIME: 2000

  broker:
    image: confluentinc/cp-kafka:7.0.1
    container_name: broker
    ports:
    # To learn about configuring Kafka for access across networks see
    # https://www.confluent.io/blog/kafka-client-cannot-connect-to-broker-on-aws-on-docker-etc/
      - "9092:9092"
    depends_on:
      - zookeeper
    environment:
      KAFKA_BROKER_ID: 1
      KAFKA_ZOOKEEPER_CONNECT: 'zookeeper:2181'
      KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: PLAINTEXT:PLAINTEXT,PLAINTEXT_INTERNAL:PLAINTEXT
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092,PLAINTEXT_INTERNAL://broker:29092
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
      KAFKA_TRANSACTION_STATE_LOG_MIN_ISR: 1
      KAFKA_TRANSACTION_STATE_LOG_REPLICATION_FACTOR: 1
```

<p>This file will be used in the Basic file example - first commands section</p>
  
  
</details>

<details><summary>Example of preconfigured files</summary>

<p>example</p>

</details>

## Initial commands
<details><summary> Basic file example - first commands</summary>

<p>

- Make sure you are in the same directory as the <b>docker compose</b> file and <b>enable docker</b>.

### Start Zookeeper and Kafka Broker

- Start Zookeeper and Kafka Broker:

```
docker-compose up -d
```

### How to create a topic ?

```
docker exec broker \
kafka-topics --bootstrap-server broker:9092 \
             --create \
--replication-factor 1 \
--partitions 1 \
             --topic test-topic
```

### How to instantiate a Producer in the console?

#### No key:

```
docker exec --interactive --tty broker \
  kafka-console-producer --broker-list localhost:9092 \
  --topic test-topic \
```

#### With key:

```
docker exec --interactive --tty broker \
  kafka-console-producer --broker-list localhost:9092 \
  --topic test-topic \
  --property "key.separator=-" \
  --property "parse.key=true"
```

### How to instantiate a Consumer in the console?

<p> The consumer has 3 configuration options:</p>
<ul>
  <li>from-beginning (all data)</li>
  <li>latest (last data only)</li>
  <li>specific offset (specify a dataset)</li>
</ul>
  
#### No key:

```
docker exec --interactive --tty broker \
kafka-console-consumer --bootstrap-server localhost:9092 --topic test-topic --from-beginning
```


#### With key:

```
docker exec --interactive --tty broker \
kafka-console-consumer --bootstrap-server localhost:9092 --topic test-topic --from-beginning -property "key.separator= - " --property "print.key=true"
```


  
#### Consume messages with 'Kafka Headers':

```
docker exec --interactive --tty broker \ --bootstrap-server localhost:9092 --topic library-events.DLT --from-beginning --property print.headers=true --property print.timestamp=true
```
</details>



### More operations:

<details><summary>Docker Compose - advanced features</summary>

  ### List the topics of a 'Cluster':

```
docker exec broker \
kafka-topics --list --bootstrap-server localhost:9092
```
  
### Details/Descriptions of a 'Cluster' topics:

```
docker exec broker \
kafka-topics --bootstrap-server localhost:9092 --describe
```

### Consumer group:

<ul>
   <li>Consumer groups are used for scalable message consumption</li>
   <li>Each different app will have a unique 'consumer' group</li>
   <li>Who manages the 'consumers' group?
       <ul>
         <li>Kafka Broker manages consumer groups</li>
         <li>Kafka Broker coordinates the groups</li>
       </ul>
   </li>
</ul>

#### See group of 'consumers'
   
```
docker exec broker \
kafka-consumer-groups --bootstrap-server localhost:9092 --list
```
<p>Note that after the console-consumer name there is a number, it is the group Id</p>
<p>If you run any 'consumer' command and then run the command to see the 'consumer' group, you will see that it contains one more group Id that corresponds to the last 'consumer'</p>

#### Create group of 'consumers'

  <p>Use console-consumer-12345 instead of <group-name></p>
   
```
docker exec --interactive --tty broker \
kafka-console-consumer --bootstrap-server localhost:9092 --topic test-topic --group <group-name>
```
    <p>Now, insert more 'consumer' in the group by following the above command</p>
    
    <p>Create a producer for the same topic and enter values and observe the data will be divided among the 'consumers'</p>

```
docker exec --interactive --tty broker \
  kafka-console-producer --broker-list localhost:9092 \
  --topic test-topic \
```
  
</details>
