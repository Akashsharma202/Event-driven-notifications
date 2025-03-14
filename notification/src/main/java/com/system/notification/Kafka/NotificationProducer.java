package com.system.notification.Kafka;
import com.system.notification.Constants.CommonConstants;
import com.system.notification.model.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationProducer {
    private final KafkaTemplate<String, Notification> kafkaTemplate;
    public static final Logger logger= LoggerFactory.getLogger(NotificationProducer.class);
    public NotificationProducer(KafkaTemplate<String, Notification> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendNotification(Notification notification) {
        kafkaTemplate.send(CommonConstants.KAFKA_NOTIFICATION_TOPIC, notification);
        logger.info("Sent Notification: {}" , notification);
    }
}