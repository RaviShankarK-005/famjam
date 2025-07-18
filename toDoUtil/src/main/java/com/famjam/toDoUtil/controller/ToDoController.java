package com.famjam.toDoUtil.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.famjam.toDoUtil.dto.TaskDTO;
import com.famjam.toDoUtil.service.ToDoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class ToDoController {
	
	@Autowired
	ToDoService toDoService;
	
	@Value("${famjam.env.test}")
	String envProp;
	
	@Value("${spring.profiles.active}")
	String actProf;
	
	@GetMapping(value="/env")
	public String getEnvProp() {
		return actProf+"_"+envProp;
	}

	@RequestMapping(value = "/task", method=RequestMethod.POST)
	public TaskDTO addTask(@RequestBody TaskDTO taskReq) {
		return toDoService.addTask(taskReq);
	}
	
	@RequestMapping(value = "/tasks", method=RequestMethod.GET)
	public List<TaskDTO> getTasks() {
		return toDoService.getToDoTasks();
	}
	
	@RequestMapping(value = "/tasks/{user}", method=RequestMethod.GET)
	public List<TaskDTO> getToDoTasksByAssignedTo(@PathVariable String user) {
		return toDoService.getToDoTasksByAssignedTo(user);
	}
	
	
}
