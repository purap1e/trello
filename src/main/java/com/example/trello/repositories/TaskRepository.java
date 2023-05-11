package com.example.trello.repositories;

import com.example.trello.entities.Tasks;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TaskRepository extends JpaRepository<Tasks,Long> {
    List<Tasks> findAllByFolder_Id(Long id);
}
