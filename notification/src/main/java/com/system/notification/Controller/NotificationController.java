package com.system.notification.Controller;

import com.system.notification.Kafka.NotificationProducer;
import com.system.notification.Service.NotificationService;
import com.system.notification.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private NotificationProducer producer;

    @GetMapping
    public List<Notification> getAllNotifications() {
        return notificationService.getAllNotifications();
    }

    @PostMapping
    public Notification createNotification(@RequestBody Notification notification) {
        return notificationService.createNotification(notification);
    }

    @PutMapping("/{id}/seen")
    public void markAsSeen(@PathVariable String id) {
        notificationService.markAsSeen(id);
    }

    @PostMapping("/send")
    public String sendNotification(@RequestBody Notification notification) {
        producer.sendNotification(notification);
        return "Notification sent to Kafka!";
    }

    @DeleteMapping()
    public String deleteNotification(@PathVariable String id){
        return notificationService.deleteNotification(id);
    }
}