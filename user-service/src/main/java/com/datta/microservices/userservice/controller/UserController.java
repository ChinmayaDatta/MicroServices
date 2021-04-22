package com.datta.microservices.userservice.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.datta.microservices.userservice.model.Task ;
import com.datta.microservices.userservice.model.User;
import com.datta.microservices.userservice.model.UserTask;
import com.datta.microservices.userservice.Repository.UserRepository;
import com.datta.microservices.userservice.services.TaskService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("v1")
public class UserController {

	Logger logger = LoggerFactory.getLogger(UserController.class);

	private UserRepository userRepository;
	private RestTemplate restTemplate;
	private TaskService taskService;
	UserController(UserRepository userRepository, RestTemplate restTemplate,TaskService taskService) {
		this.userRepository = userRepository;
		this.restTemplate = restTemplate;
		this.taskService=taskService;
	}

	@PostMapping("user")
	public User user(@RequestBody User user) {
		logger.info("New User {}", user);
		return userRepository.save(user);
	}

	@GetMapping("user/{userId}")
	public Optional<User> getUser(@PathVariable("userId") Long id) {
		logger.info(" User id {}", id);
		return userRepository.findById(id);
	}

	@GetMapping("user/{id}/tasks")
	public ResponseEntity<?> userTasks(@PathVariable("id") Long id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			logger.info("User found {}", user);
		 /*	ResponseEntity<List<Task>> tasks = 
					restTemplate.exchange("http://localhost:8083/user/" + id + "/tasks",
					HttpMethod.GET, 
					null,
					new ParameterizedTypeReference<List<Task>>() {
					});
					*/ 
			ResponseEntity<List<Task>> tasks =taskService.userTasks(id);
			return new ResponseEntity<UserTask>(
					new UserTask(user.get(),
							tasks.getBody()), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
