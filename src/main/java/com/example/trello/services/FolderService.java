package com.example.trello.services;

import com.example.trello.entities.Folders;
import com.example.trello.entities.TaskCategories;
import com.example.trello.entities.Tasks;

import java.util.List;

public interface FolderService {
    List<Folders> getAllFolders();
    Folders getFolder(Long id);
    Folders saveFolder(Folders folder);
    void deleteFolder(Long id);
    Folders updateFolder(Folders folder);

    List<TaskCategories> getAllCategories();
    TaskCategories getCategory(Long id);
    void deleteCategory(Long id);

    List<Tasks> getAllTasks();
    Tasks getTask(Long id);
    void deleteTask(Long id);
    Tasks updateTask(Tasks task);
    Tasks saveTask(Tasks task);
    List<Tasks> findAllByFolder_Id(Long id);
}
