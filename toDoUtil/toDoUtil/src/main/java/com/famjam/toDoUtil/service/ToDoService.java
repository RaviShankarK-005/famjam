package com.famjam.toDoUtil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.famjam.toDoUtil.dao.ToDoRepository;
import com.famjam.toDoUtil.dto.TaskDTO;

@Service
public class ToDoService {

	@Autowired
	ToDoRepository toDoRepository;
	
	public TaskDTO addTask(TaskDTO taskDTO) {
		System.out.println(taskDTO.toString());
		return toDoRepository.save(taskDTO);
	}
	
	public List<TaskDTO> getToDoTasksByAssignedTo(String assignedTo){
		return toDoRepository.getToDoTasksByAssignedTo(assignedTo);
	}
	
	public List<TaskDTO> getToDoTasks(){
		return toDoRepository.findAll();
	}
}
