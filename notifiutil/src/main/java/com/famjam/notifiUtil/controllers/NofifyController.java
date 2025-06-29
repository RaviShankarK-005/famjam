package com.famjam.notifiUtil.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.famjam.notifiUtil.service.NotifyService;

@RestController
public class NofifyController {
	@Autowired
    private NotifyService service;
	
	@Value("${famjam.env.test}")
	String envProp;
	
	@Value("${spring.profiles.active}")
	String actProf;
	
	@GetMapping(value="/env")
	public String getEnvProp() {
		return actProf+"_"+envProp;
	}

    @PostMapping("/send")
    public ResponseEntity<?> send(@RequestParam String userId,
                                  @RequestParam String message,
                                  @RequestParam(defaultValue = "INFO") String type) {
        return ResponseEntity.ok(service.sendNotification(userId, message, type));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserNotifications(@PathVariable String userId) {
        return ResponseEntity.ok(service.getUserNotifications(userId));
    }

    @PostMapping("/read/{id}")
    public ResponseEntity<?> markRead(@PathVariable Long id) {
        service.markAsRead(id);
        return ResponseEntity.ok("Marked as read");
    }
}
