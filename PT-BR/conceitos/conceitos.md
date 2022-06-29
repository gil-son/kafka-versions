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

- Registo completo
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

[producer] --dados--> [kafka]


### Broker

O Apache Kafka possui um sistema de 'Clusters' que são disversos bancos de dados integrados conhecidos como 'Brokers' que ajudam no gerenciamento dos dados. Então o Kafka é um 'Cluster' com diversos 'Brokers'. E, cada 'Broker' tem o seu próprio banco de dados:

[producer] --dados--> [ [kafka] [[Broker A],[Broker B],[Broker C]] ]


### Consumer

O 'Consumer' é o sistema que quer receber o dado que veio 'Producer' e está em um 'Broker'. O 'Consumer' ficar ouvindo/em alerta pelo dado que pode vir do Kafka e quando chega o dado ele consome. O sistema que compoem o 'Consumer' possui uma lógica que o faz monitorar o Kafka "para sempre" em um loop:

[producer] --dados--> [ [kafka] [[Broker A],[Broker B],[Broker C]] ] <--while(true)-- Consumer


### Zookeeper

O Zookeeper é um sistema de 'Service Discovery' ele consegue orquestrar os 'Brokers' que o Kafka está rodando. Possui gerenciamento de permissões, gerenciamento de erros e recuperação de 'Brokers'. Por exemplo, se o 'Broker A' cair ele consegue redirecionar as coisas para o 'Broker B'. Precisa subir um novo 'Broker'? O Zookeeper faz isso e integra a comunicação com outros 'Brokers'. Então  existe o 'Cluster' do Kafka e existe o 'Cluster' do Zookeeper que gerencia o 'Cluster' do Kafka. E tudo isso funciona de forma transparante para o desenvolvedor.


É um sistema que trabalha em conjunto com o Kafka, e talvez em breve seja possível utilizar sistemas semelhantes. Em todo caso, o Kafka precisa desse tipo de sistema para o seu funcionamento:


                                          [Zookeeper]
                                              |
[producer] --dados--> [ [kafka] [[Broker A],[Broker B],[Broker C]] ] <--while(true)-- Consumer









