package com.datta.microservices.taskservice.repository;


import com.datta.microservices.taskservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByName(String name);
}