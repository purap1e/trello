package com.example.trello.repositories;

import com.example.trello.entities.Folders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface FolderRepository extends JpaRepository<Folders, Long> {
}
