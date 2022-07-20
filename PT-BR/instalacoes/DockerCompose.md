#  Configurações do Kafka no Docker Compose e Comandos

<details><summary>Exemplo de arquivo básico</summary>
<p>Crie um arquivo chamado docker-compose.yml e cole o script:</p>
  
  
  
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

<p>Esse arquivo será utilizado na seção Exemplo de arquivo básico - primeiros comandos</p>  
  
  
</details>

<details><summary>Exemplo de arquivos pré-configurados</summary>

<p> exemplo</p>

</details>

## Comandos iniciais
<details><summary> Exemplo de arquivo básico - primeiros comandos</summary>

<p>

- Certifique de estar no mesmo diretório que o arquivo do <b>docker compose</b> e de <b>ativar o docker</b>.

### Inicie o Zookeeper e o Kafka Broker

-   Inicie o Zookeeper e Kafka Broker:

```
docker-compose  up -d
```

### Como criar um tópico ?

```
docker exec broker \
kafka-topics --bootstrap-server broker:9092 \
             --create \
	           --replication-factor 1 \
	           --partitions 1 \
             --topic test-topic
```

### Como instanciar um Produtor no console?

#### Sem chave:

```
docker exec --interactive --tty broker \
  kafka-console-producer --broker-list localhost:9092 \
  --topic test-topic \
```

#### Com chave:

```
docker exec --interactive --tty broker \
  kafka-console-producer --broker-list localhost:9092 \
  --topic test-topic \
  --property "key.separator=-" \
  --property "parse.key=true"
```

### Como instanciar um Consumidor no console?

<p> O consumer tem 3 opções de configurações:</p>  
<ul> 
  <li>from-beginning (todos os dados)</li>
  <li>latest (somente os últimos dados)</li>
  <li>specific offset (especificar um conjunto de dados)</li>
</ul>
  
#### Sem chave:

```
docker exec --interactive --tty broker \
kafka-console-consumer --bootstrap-server localhost:9092 --topic test-topic --from-beginning
```


#### Com chave:

```
docker exec --interactive --tty broker \
kafka-console-consumer --bootstrap-server localhost:9092 --topic test-topic --from-beginning -property "key.separator= - " --property "print.key=true"
```


  
#### Consuma mensagens com 'Kafka Headers':

```
docker exec --interactive --tty broker \ --bootstrap-server localhost:9092 --topic library-events.DLT --from-beginning --property print.headers=true --property print.timestamp=true
```
</details>



### Mais operações:

<details><summary>Docker Compose - recursos avançados</summary>

  ### Listar os tópicos de um 'Cluster':

```
docker exec broker \
kafka-topics  --list --bootstrap-server localhost:9092
```
  
### Detalhe/Descrições dos tópicos de um 'Cluster':

```
docker exec broker \
kafka-topics  --bootstrap-server localhost:9092 --describe
```

### Grupo de 'consumers':

<ul>
   <li>Grupos de 'consumers' são usados para consumo de mensagens escalável</li>
   <li>Cada aplicativo diferente terá um grupo de 'consumers' exclusivo</li>
   <li>Quem gerencia o grupo de 'consumers'?
       <ul>
         <li>Kafka Broker gerencia os grupos de 'consumers'</li>
         <li>Kafka Broker coordena os grupos</li>
       </ul>
   </li>
</ul>

#### Ver grupo de 'consumers'
   
```
docker exec broker \
kafka-consumer-groups --bootstrap-server localhost:9092 --list
```
<p>Obsver que após o nome do console-consumer tem uma numeração, é o Id do grupo</p>  
<p>Se executar algum comando do 'consumer' e depois executar o comando para ver o grupo de 'consumer', verá que contem mais um Id do grupo que corresponde ao último 'consumer'</p>  

#### Criar grupo de 'consumers'

  <p>Utilize o console-consumer-12345 no lugar do <group-name></p>
   
```
docker exec --interactive --tty broker \
kafka-console-consumer --bootstrap-server localhost:9092 --topic test-topic --group <group-name>
```  
    <p>Agora, insira mais 'consumer' no grupo respetindo o comando acima</p>
    
    <p>Crie um producer para o mesmo tópico e insira valores e observe os dados serão dividos entre os 'consumers'</p>

```
docker exec --interactive --tty broker \
  kafka-console-producer --broker-list localhost:9092 \
  --topic test-topic \
```
  
</details>

