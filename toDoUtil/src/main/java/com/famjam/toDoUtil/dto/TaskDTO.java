package com.famjam.toDoUtil.dto;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name="todotasks")
public class TaskDTO {
	
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Id
	private int id;
	private String title;
	private String description;
	private String status;
	private String assignedTo;
	private LocalDateTime createdon;
	private LocalDateTime updatedon;
	private LocalDateTime duedate;
	
	@PrePersist
    protected void onCreate() {
        this.createdon = LocalDateTime.now();
        this.updatedon = LocalDateTime.now(); // Set both when first created
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedon = LocalDateTime.now();
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public LocalDateTime getCreatedon() {
		return createdon;
	}

	public void setCreatedon(LocalDateTime createdon) {
		this.createdon = createdon;
	}

	public LocalDateTime getUpdatedon() {
		return updatedon;
	}

	public void setUpdatedon(LocalDateTime updatedon) {
		this.updatedon = updatedon;
	}

	public LocalDateTime getDuedate() {
		return duedate;
	}

	public void setDuedate(LocalDateTime duedate) {
		this.duedate = duedate;
	}

	@Override
	public String toString() {
		return "TaskDTO [id=" + id + ", title=" + title + ", description=" + description + ", status=" + status
				+ ", assignedTo=" + assignedTo + ", createdon=" + createdon + ", updatedon=" + updatedon + ", duedate="
				+ duedate + "]";
	}
    
    
}
