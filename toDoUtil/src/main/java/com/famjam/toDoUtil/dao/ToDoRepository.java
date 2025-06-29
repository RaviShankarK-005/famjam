package com.famjam.toDoUtil.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.famjam.toDoUtil.dto.TaskDTO;

@Repository
public interface ToDoRepository extends JpaRepository<TaskDTO, Integer>{

	List<TaskDTO> getToDoTasksByAssignedTo(String assignedTo);

}
