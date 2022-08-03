# Conceitos do Apache Kafka


## Introdução 

Apache Kafka é uma plataforma open-source serve para o processamento/transmissão de dados/streams em um fluxo contínuo, desenvolvida pela Apache Software Foundation, escrita em Scala e Java. Tem como objetivo fornecer uma plataforma unificada, de alta capacidade e baixa latência para tratamento de dados em tempo real. Ou seja, é um sistema de mensageria de alto desempenho e em tempo real. Essa plataforma se popularizou muito nos últimos anos com a “ascensão dos microsserviços”. O Kafka se destaca por ser extremamente veloz, escalável e versátil.


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


