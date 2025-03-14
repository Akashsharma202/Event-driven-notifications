package com.system.notification.Service;

import com.system.notification.Kafka.NotificationKafkaListener;
import com.system.notification.Repository.CommonCrudRepo;
import com.system.notification.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {
    @Autowired
    private CommonCrudRepo<Notification> notificationRepository;

    public List<Notification> getAllNotifications() {
        return new ArrayList<>(notificationRepository.findAll());
    }

    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    public void markAsSeen(String notificationId) {
        notificationRepository.findById(notificationId).ifPresent(notification -> {
            notification.setSeen(true);
            notificationRepository.save(notification);
        });
    }

    public String deleteNotification(String id) {
        if(NotificationKafkaListener.notificationBuffer.containsKey(id)){
            NotificationKafkaListener.notificationBuffer.remove(id);
            return "Notification removed from buffer";
        }
        notificationRepository.deleteById(id);
        return "notification removed from database";
    }
}
