package com.famjam.notifiUtil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.famjam.notifiUtil.model.Notifrequests;
import com.famjam.notifiUtil.repo.Notifyrepo;

@Service
public class NotifyService {

    @Autowired
    private Notifyrepo repository;

    public Notifrequests sendNotification(String userId, String message, String type) {
        Notifrequests n = new Notifrequests();
        n.setUserId(userId);
        n.setMessage(message);
        n.setType(type);
        return repository.save(n);
    }

    public List<Notifrequests> getUserNotifications(String userId) {
        return repository.findByUserIdOrderByTimestampDesc(userId);
    }

    public void markAsRead(Long id) {
    	Notifrequests n = repository.findById(id).orElseThrow();
        n.setRead(true);
        repository.save(n);
    }
}
