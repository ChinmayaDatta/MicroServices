package com.datta.microservices.userservice.services;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.datta.microservices.userservice.model.Task;

@Service
// @FeignClient(name="task-service" ,url="http://localhost:8083/")
@FeignClient(name = "task-service")
@RibbonClient(name = "task-service")
public interface TaskService {

  @RequestMapping("user/{id}/tasks")
  ResponseEntity<List<Task>> userTasks(@PathVariable("id") Long userId);
}
