# Conceitos do Apache Kafka

<details><summary><h2>Introdução</h2></summary>

<br/>

Apache Kafka é uma plataforma open-source serve para o processamento/transmissão de dados/streams em um fluxo contínuo, desenvolvida pela Apache Software Foundation, escrita em Scala e Java. Tem como objetivo fornecer uma plataforma unificada, de alta capacidade e baixa latência para tratamento de dados em tempo real. Ou seja, é um sistema de mensageria de alto desempenho e em tempo real. Essa plataforma se popularizou muito nos últimos anos com a “ascensão dos microsserviços”. O Kafka se destaca por ser extremamente veloz, escalável e versátil.


No início as companias apenas queriam integrar os dados de um sistema para o outro. Um Source System que envia dados para o System Target e nisso poderia ocorrer no percuso a Extração, Transformação e Carregamento dos dados:

<div align="center"><img src="https://thumbs2.imgbox.com/b8/57/1Yk11Tmu_t.jpg"/></div>

Após um tempo as companias passaram a necessitar de mais Source System e mais System Target:

<div align="center"><img src="https://thumbs2.imgbox.com/3c/c1/o4yJEXnl_t.jpg"/></div>


Com isso a integração entre os sistemas se torna um desafio maior. Se você tem 4 Source System e 6 System Target a quantidade de integração é 24:

- E cada integração possui suas configurações
	- Protocol 
		- como os dados são transportados(TCP, HTTP, REST, FTP, JDBC...)
	- Data format
		- como os dados são passados(Binary, CSV, JSON, Avro,Protobuf..)
- E cada Source System terá a sua carga aumentada por conexões

Então como resolvemos esse problema?


Bem, através de desacomplamentos utilizando o Apache Kafka, é possível otimizar:

Tanto o System Source quanto o Target System continuam com suas funções e o Kafka
passa a fazer intermédio entre esses sistemas

<div align="center"><img src="https://thumbs2.imgbox.com/a4/d3/krYKwxkO_t.jpg"/></div>

Mas o que muda? Agora o System Source são apenas responsáveis pelo envio dos dados.
E passam a ser chamados de Producer, pois produzem os dados no Kafka. E com isso
o Kafka passa a ter um fluxo de dados que origina do Producer. E, esses dados precisam
de um destino, que é o System Target, que passa a ser chamado de Consumer, pois consome
os dados:

<div align="center"><img src="https://thumbs2.imgbox.com/90/c5/koM7ku6a_t.jpg"/></div>

Isso deixa tudo mais excalável. 


Se continuarmos no mesmo exemplo, um Producer, pode ser:
	
- Uma página web
- Dados de preços
- transações financeiras
- interações de usuários
- todas essas coisas que criam fluxos de dados

E esses dados criados/trafegados em tempo real no Apache Kafka.

E um Consumer, pode ser:

- Um banco de dados
- Sistema de Análises
- Sistema de e-mail
- Auditoria
- sistemas que são alimentados por dados

</details>

## Motivos para utilizar

### Eventos

- Integração entre sistemas
- Dispositivos/ IOT
- Monitoramento
- Alarms

### Registros

- Registro completo
- Histórico

### Compatibilidade do Core

- Alto rendimento. Baixa latência como 2ms.
- Escalável
- Armazenamento permanente
- Alta disponibilidade

### Ecossistema

- Processamento de fluxo integrado
- Conecta a quase tudo
- Bibliotecas de cliente
- Ferramentas de código aberto

### Empresas que usam Kafka

- Netflix
- Linkedin
- Uber
- Twitter
- Dropbox
- Spotify
- Paypal
- Bancos...


## Entendendo

O Apache Kafka permite conectores no qual é possível fazer leituras em repassar para um ou mais sistemas em tempo real. 

### Topic

'Topic' é fluxo específico de dados dentro de um 'Cluster' no Kafka. Portanto um 'Cluster' do Kafka pode ter muitos 'Topics', por exemplo:

- logs;
- compras;
- twitter_tweets;
- trucks_gps;
- outras formas de aplicações que enviam dados.

<div align="center"><img src="https://thumbs2.imgbox.com/74/56/3aqNDUNC_t.png"/></div>

'Topic' é similar a uma tabela de banco de dados, com as seguintes diferenças:

- Sem restrições de dados, pode enviar o que quiser, pois não há verificação de dados;
- Não exclui/atualiza os dados armazenados (imutabilidade), para isso é necessário um novo envio do "estado" daquele dado;
- Os dados são mantidos por um tempo limitado. Por padrão 1 semana, mas é configurável;
- Suportam qualquer tipo de mensagem:
	- JSON;
	- Avro;
	- Arquivo em geral;
	- Binário;
- Não se adiciona dados em um 'Topic' existente, é necessário um novo envio através de um 'Producer';
- Não se pode consultar os 'Topics', mas é possível ler o dado final através de um 'Consumer';
- O deslocamento de um dado (Offset) só pode ser feito no 'Topic' daquela 'Partition';
- O dado é armazenado de forma aleatória na 'Partition', a menos que seja providênciado uma 'Key';
- É possível ter quantas 'Partitions' forem necessárias.

<p>Para localizar um 'Topic' em um 'Cluster', basta utilziar o seu nome</p>
<p>A sequência das mensagens em um 'Topic' é chamada de fluxo de dados. E, por isso o Kafka é conhecido por plataforma de stream de dados. Então o stream é feito por meio de 'Topics'</p>

#### Partition and Offset

'Topics' podem ser divididos em partições, então um 'Topic' pode ter inumeras partições, por exemplo 100. Nesse exemplo abaixo o 'Topic' possue 3 partições:

<div align="center"><img src="https://thumbs2.imgbox.com/06/ec/3TzRox64_t.png"/></div>

Logo as mensagens enviadas ao Kafka estarão dentro de uma 'Partition', onde essa 'Partition' estará dentro de um 'Topic', e esse 'Topic' dentro de um 'Cluster'... e esse 'Cluster' dentro de um 'Broker'

E as mensagens dentro de cada 'Partition' estarão ordenadas. As mensagens na 'Partition' zero terão o id zero, as mensagens na 'Partition' um terão o id um e assim por diante. Enquanto mensagens entrarem a quantidade de id's aumentam:

<div align="center"><img src="https://thumbs2.imgbox.com/45/d4/RgtVJGkU_t.png"/></div>

O id é reconhecido por deslocamento da 'Partition', também conhecido por 'Offset'. Como visto na imagem acima, cada 'Partition' tem o seu 'Offset' diferente do outro e agora os 'Topics' são imutáveis. Isso significa que uma vez que os dados são gravados em uma partição, eles não podem ser alterados/excluídos no Kafka. Para isso é necessário continuar gravando na 'Partition'.


### Producer

Uma forma de conexão é através do 'Producer'. O 'Producer' é um sistema que produz um dado no 'Topic', seja um carro que disparou um alarme, um e-commercer que efetuou uma notificação, um sistema de envio de mensagens, etc. Esse dado vai ser encaminhado para o Apache Kafka e o mesmo fará o gerenciamento do dado:

<div align="center"><img src="https://thumbs2.imgbox.com/e6/3d/m6YRD1OW_t.png" alt="image host"/></div>

- O 'Producer' sabe em qual 'Topic' vai registrar e é capaz de gerenciar
- Em casos de falhas do 'Broker' o 'Producer' vai automaticamente garantir que o dados seja salvo
- Possui 'Load balancing' o que permite organizar distribuir/organizar os dados

#### Message keys

- 'Producer' pode escolher se envia uma mensagem com uma 'key' (string, number, binary, etc)
- Se não possuir uma 'key' a ordem de inserção no 'Topic' será em sequência de um por um (partition 0, partition 1, ...). Sempre utilizando o balanceamento dos dados nas partições:

<div align="center"><img src="https://thumbs2.imgbox.com/1f/90/iVA8kFzl_t.png" alt="image host"/></div>

- Se possuir uma key, a inserção vai ser por referência, pode ser um id em combinação com uma técnica de 'hashing'. Por exemplo um caminhão envia dados de sua localização, a referência desse caminhão é o seu id, no caso truck_id123. O 'Producer' vai armazenar os dados em um 'Topic' e em uma 'Partition'. Nesse caso a partição 0... e vai continuar recebendo dados de outros caminhões com outros id's. Quando receber um dado referente ao truck_id123, ele vai armazenar na  mesma partição que corresponde aquele id, esse novo registro vai ter agora um novo id, por exemplo truck_id234:

<div align="center"><img src="https://thumbs2.imgbox.com/a4/4d/9BQuddHi_t.png" alt="image host"/></div>

Obs.: sempre para o mesmo 'Topic' o que muda é a 'Partition'. Portanto a 'key' faz parte da organização uma mensagem do 'Producer' em uma 'Partition'

### Kafka Message

#### Message anatomy

Verificando a fundo a estrutura completa que é uma mensagem do Kafka a mesma carrega uma 'Key' and 'Value', ondem ambos podem ser nulos

As mensagens do Kafka podem ser comprimidas em formatos menores, como:

- gzip
- snappy
- iz4 
- zstd

E é possível adicionar 'Headers' as mensagens que possui uma lista opciona de chave e valor:

<div align="center"><img src="https://thumbs2.imgbox.com/30/11/BgjTDsdF_t.png" alt="image host"/></div>

Note que a mensagem além da sua 'Partition' + 'Offset' no qual vai ser encaminhada. Possui também um 'Timestamp' de data/hora definido pelo sistema ou usuário para registrar de forma precisa os momentos

Sim, isso que é uma mensagem do Kafka :)

#### Message Serializes

Como essa mensagem é criada? 

Quando a mensagem vem de um 'Producer' qualquer, ela pode estar em qualquer tipo de formato (JSON, XML, Avro, String, Protobuf ...) só que ela não entra e não sai do Kafka, sem ser
serializada, ou seja, transformados em bytes

O Kafka apenas aceita valor em bytes em sua entrada quando vem de um 'Producer'. O mesmo se aplica quando o Kafka encaminha a sua mensagem para um 'Consumer', a mensagem vai estar serializada e depois o 'Consumer' vai precisar deserializar

Se a mensagem do Kafka precisa estar em bytes, logo a mensagem ao ser trafegada, precisa estar serializada:

<div align="center"><img src="https://thumbs2.imgbox.com/e8/54/h7hFOVJC_t.png" alt="image host"/></div>

#### Message Key

O Kafka possui recursos avançados para a criptografia das mensagens. Existe uma lógica dentro do Kafka que pega a mensagem gravada e determina qual a 'Partition'.
E por sua vez utiliza o 'Key Hashing' que determina um mapeamento de 'key' e 'value' para a partição escolhida. E essa partição vai ser protegida pelo 'murmur2 algoritm'

No momento não será aprofundado esse tema, mas fica para curiosos


### Broker

O Apache Kafka possui um sistema de 'Clusters' que são diversos bancos de dados integrados conhecidos como 'Brokers' que ajudam no gerenciamento dos dados. Então o Kafka é um 'Cluster' com diversos 'Brokers'. E, cada 'Broker' tem o seu próprio banco de dados:

<div align="center"><img src="https://thumbs2.imgbox.com/d7/53/jfXc7XGK_t.png" alt="image host"/></div>


### Consumer

O 'Consumer' é o sistema que quer receber o dado que veio 'Producer' e está em um 'Broker'. O 'Consumer' ficar ouvindo/em alerta pelo dado que pode vir do Kafka e quando chega o dado ele consome. O sistema que compoem o 'Consumer' possui uma lógica que o faz monitorar o Kafka "para sempre" em um loop:

<div align="center"><img src="https://thumbs2.imgbox.com/29/5f/ZZcrdqgr_t.png" alt="image host"/></div>


### Zookeeper

O Zookeeper é um sistema de 'Service Discovery' ele consegue orquestrar os 'Brokers' que o Kafka está rodando. Possui gerenciamento de permissões, gerenciamento de erros e recuperação de 'Brokers'. Por exemplo, se o 'Broker A' cair ele consegue redirecionar as coisas para o 'Broker B'. Precisa subir um novo 'Broker'? O Zookeeper faz isso e integra a comunicação com outros 'Brokers'. Então  existe o 'Cluster' do Kafka e existe o 'Cluster' do Zookeeper que gerencia o 'Cluster' do Kafka. E tudo isso funciona de forma transparante para o desenvolvedor.


É um sistema que trabalha em conjunto com o Kafka, e talvez em breve seja possível utilizar sistemas semelhantes. Em todo caso, o Kafka precisa desse tipo de sistema para o seu funcionamento:

<div align="center"><img src="https://thumbs2.imgbox.com/45/4d/ozqgNj91_t.png" alt="image host"/></div>
                                       

##### Example

Vamos supor que tenhamos uma frota de caminhões e que esses caminhões a cada 20 segundos enviam dados de sua localização (latitude e longitude), é um nosso 'Producer'. Com isso teremos um 'Topic' chamado trucks_gps. Optamos por criar um 'Topic' com 10 'Partitions' de forma arbitrária (é possível estabelecer a quantidade):

[img]


Agora, queremos que os 'Consumers' recebam os dados do trucks_gps e os envie para um painel de localização (dashboard). Assim é possível rastrear todos os caminhões em tempo real. Ou talvez queiramos que um serviço de notificação consuma o mesmo fluxo de dados e envie informações da entrega para clientes:

[img]


Tudo isso em tempo real!

<hr/>

<table>
  <tr>
    <th>Local</th>
    <th>Link</th>
  </tr>
  <tr>
    <td>Subir até o topo</td>
    <td><a href="https://github.com/gil-son/kafka-versions/tree/main/PT-BR/conceitos">:arrow_up:</a></td>
  </tr>
  <tr>
    <td>Voltar a sessão anterior</td>
    <td><a href="https://github.com/gil-son/kafka-versions/tree/main/PT-BR">:arrow_left:</a></td>
  </tr>
   <tr>
    <td>Primeira sessão</td>
    <td><a href="https://github.com/gil-son/kafka-versions">:taxi:</a></td>
  </tr>
</table>


