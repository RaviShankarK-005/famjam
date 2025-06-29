package com.famjam.notifiUtil.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.famjam.notifiUtil.model.Notifrequests;

public interface Notifyrepo extends JpaRepository<Notifrequests, Long> {

	List<Notifrequests> findByUserIdOrderByTimestampDesc(String userId);
}
