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

- sem restrições de dados, pode enviar o que quiser, pois não há verificação de dados
- não exclui/atualiza os dados armazenados, para isso é necessário um novo envio do "estado" daquele dado
- suportam qualquer tipo de mensagem:
	- JSON
	- Avro
	- Arquivo em geral
	- Binário
- não se adiciona dados em um 'Topic' existente, é necessário um novo envio através de um 'Producer'
- não se pode consultar os 'Topics', mas é possível ler o dado final através de um 'Consumer'

<p>Para localizar um 'Topic' em um 'Cluster', basta utilziar o seu nome</p>
<p>A sequência das mensagens em um 'Topic' é chamada de fluxo de dados. E, por isso o Kafka é conhecido por plataforma de stream de dados. Então o stream é feito por meio de 'Topics'</p>



### Producer

Uma forma de conexão é através do 'Producer'. O 'Producer' é um sistema que produz um dado, seja um carro que disparou um alarme, um e-commercer que efetuou uma notificação, um sistema de envio de mensagens, etc. Esse dado vai ser encaminhado para o Apache Kafka e o mesmo fará o gerenciamento do dado:

<div align="center"><img src="https://thumbs2.imgbox.com/e6/3d/m6YRD1OW_t.png" alt="image host"/></div>

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


