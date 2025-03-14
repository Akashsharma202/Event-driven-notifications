package com.system.notification.Kafka;

import com.system.notification.Constants.CommonConstants;
import com.system.notification.Repository.CommonCrudRepo;
import com.system.notification.model.Notification;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class NotificationKafkaListener {
    public static final Logger logger= LoggerFactory.getLogger(NotificationKafkaListener.class);
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private CommonCrudRepo<Notification> notificationRepository;

    public static Map<String,Notification> notificationBuffer = new HashMap<>();

    @KafkaListener(topics = CommonConstants.KAFKA_NOTIFICATION_TOPIC, groupId = CommonConstants.KAFKA_NOTIFICATION_GROUP_ID)
    public void listen(Notification notification) {
        notification.setId(String.valueOf(new ObjectId()));
        notificationBuffer.put(notification.getId(),notification);
        if (notificationBuffer.size() >= CommonConstants.BUFFER_SIZE) {
            List<Notification> notifications= new ArrayList<>();
            for(Map.Entry<String,Notification> map: notificationBuffer.entrySet()){
                notifications.add(map.getValue());
            }
            saveNotifications(notifications);
            notificationBuffer.clear();
        }
        messagingTemplate.convertAndSend("/topic/user/"+notification.getUserId(), notification);
    }

    private void saveNotifications(List<Notification> notifications) {
        logger.info("saveNotifications(): saving notifications.");
        notificationRepository.insert(notifications);
    }
}
