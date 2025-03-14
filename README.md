# Real-Time Notification System (Spring Boot Backend)

This project demonstrates a real-time notification system backend built using Spring Boot. It leverages Apache Kafka for message queuing, MongoDB for data storage, and WebSockets for real-time communication.

## Technologies Used

* Spring Boot
* Apache Kafka
* Zookeeper
* MongoDB
* WebSockets (SockJS, STOMP)
* Docker

## Project Structure

* `src/main/java/com/system/notification`:
    * `Config`: WebSocket configuration.
    * `model`: Data models (User, Notification, Event).
    * `repository`: Spring Data MongoDB repositories.
    * `service`: Business logic services.
    * `NotificationKafkaListener.java`: Kafka consumer for notifications.
    * `NotificationProducer.java`: Kafka Producer for notifications.
    * `NotificationWebSocketController.java`: WebSocket controller.
    * `CommonConstants.java`: Constants.
    * `NotificationApplication.java`: Main Spring Boot application.

## Setup Instructions

**1. Backend (Spring Boot):**

* **Prerequisites:**
    * Java 17 or higher
    * Maven or Gradle
    * MongoDB installed and running
    * Docker and Docker Compose (Optional, but recommended for Kafka and Zookeeper)
* **Configuration:**
    * Update `application.properties` or `application.yml` with your MongoDB and Kafka connection details.
* **Build:**
    * Use Maven (`mvn clean install`) or Gradle (`gradle clean build`) to build the project.
* **Run:**
    * Run the `NotificationApplication.java` class.

**2. Kafka and Zookeeper with Docker Compose (Optional, but Recommended):**

* **Create a `docker-compose.yml` file:**

```yaml
version: '3.8'
services:
  zookeeper:
    image: confluentinc/cp-zookeeper:latest
    ports:
      - "2181:2181"
    environment:
      ZOOKEEPER_CLIENT_PORT: 2181
      ZOOKEEPER_TICK_TIME: 2000

  kafka:
    image: confluentinc/cp-kafka:latest
    ports:
      - "9092:9092"
    depends_on:
      - zookeeper
    environment:
      KAFKA_BROKER_ID: 1
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://kafka:9092,PLAINTEXT_HOST://localhost:9092
      KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: PLAINTEXT:PLAINTEXT,PLAINTEXT_HOST:PLAINTEXT
      KAFKA_INTER_BROKER_LISTENER_NAME: PLAINTEXT
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
      KAFKA_TRANSACTION_STATE_LOG_REPLICATION_FACTOR: 1
      KAFKA_TRANSACTION_STATE_LOG_MIN_ISR: 1
