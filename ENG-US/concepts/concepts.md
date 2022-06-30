# Apache Kafka concepts


## Introduction

Apache Kafka is an open-source platform for processing/transmitting data/streams in a continuous flow, developed by the Apache Software Foundation, written in Scala and Java. It aims to provide a unified, high-capacity, low-latency platform for real-time data handling. In other words, it is a high-performance, real-time messaging system. This platform has become very popular in recent years with the “rise of microservices”. Kafka stands out for being extremely fast, scalable and versatile.


## Reasons to use

### Events

- Integration between systems
- Devices / IoT
- Monitoring
- Alarms

### Records

- Complete registration
- Historic

### Core Compatibility

- High yield. Low latency like 2ms.
- Scalable
- Permanent storage
- High Availability

### Ecosystem

- Integrated stream processing
- Connects to almost everything
- Client libraries
- Open source tools

### Companies that use Kafka

- Netflix
- Linkedin
- Uber
- Twitter
- Dropbox
- Spotify
- Paypal
- Banks...


## Understanding

Apache Kafka allows for connectors in which it is possible to read and forward to one or more systems in real time.

### Producer

One way to connect is through the 'Producer'. The 'Producer' is a system that produces data, be it a car that triggered an alarm, an e-commercer that made a notification, a system for sending messages, etc. This data will be forwarded to Apache Kafka and it will manage the data:

<div align="center"><img src="https://thumbs2.imgbox.com/e6/3d/m6YRD1OW_t.png" alt="image host"/></div>

### Broker

Apache Kafka has a system of 'Clusters' which are several integrated databases known as 'Brokers' that help in data management. So Kafka is a 'Cluster' with several 'Brokers'. And, each 'Broker' has its own database:

<div align="center"><img src="https://thumbs2.imgbox.com/d7/53/jfXc7XGK_t.png" alt="image host"/></div>


### Consumer

The 'Consumer' is the system that wants to receive the data that came from 'Producer' and is in a 'Broker'. The 'Consumer' is listening/on alert for the data that can come from Kafka and when the data arrives it consumes. The system that makes up 'Consumer' has a logic that makes it monitor Kafka "forever" in a loop:

<div align="center"><img src="https://thumbs2.imgbox.com/29/5f/ZZcrdqgr_t.png" alt="image host"/><</div>


### Zookeeper

Zookeeper is a 'Service Discovery' system it manages to orchestrate the 'Brokers' that Kafka is running. It has permissions management, error management and 'Brokers' recovery. For example, if 'Broker A' goes down he can redirect things to 'Broker B'. Need to upload a new 'Broker'? Zookeeper does this and integrates communication with other 'Brokers'. So there's Kafka's 'Cluster' and then there's Zookeeper's 'Cluster' that manages Kafka's 'Cluster'. And all of this works seamlessly for the developer.


It is a system that works in conjunction with Kafka, and it may soon be possible to use similar systems. In any case, Kafka needs this type of system to work:

<div align="center"><img src="https://thumbs2.imgbox.com/45/4d/ozqgNj91_t.png" alt="image host"/><</div>
                                       




