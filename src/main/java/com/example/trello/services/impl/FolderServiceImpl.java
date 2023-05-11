package com.example.trello.services.impl;

import com.example.trello.entities.Folders;
import com.example.trello.entities.TaskCategories;
import com.example.trello.entities.Tasks;
import com.example.trello.repositories.CategoryRepository;
import com.example.trello.repositories.FolderRepository;
import com.example.trello.repositories.TaskRepository;
import com.example.trello.services.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FolderServiceImpl implements FolderService {
    private FolderRepository folderRepository;
    private CategoryRepository categoryRepository;
    private TaskRepository taskRepository;

    @Autowired
    public FolderServiceImpl(FolderRepository folderRepository, CategoryRepository categoryRepository, TaskRepository taskRepository) {
        this.folderRepository = folderRepository;
        this.categoryRepository = categoryRepository;
        this.taskRepository = taskRepository;
    }


    @Override
    public List<Folders> getAllFolders() {
        return folderRepository.findAll();
    }

    @Override
    public Folders getFolder(Long id) {
        var folder = folderRepository.findById(id);

        if(folder.isPresent()) {
            return folder.get();
        }else {
            throw new RuntimeException("something went wrong with id = " + id);
        }
    }

    @Override
    public Folders saveFolder(Folders folder) {
        return folderRepository.save(folder);
    }

    @Override
    public void deleteFolder(Long id) {
        folderRepository.delete(getFolder(id));
    }

    @Override
    public Folders updateFolder(Folders folder) {
        return folderRepository.save(folder);
    }

    @Override
    public List<TaskCategories> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public TaskCategories getCategory(Long id) {
        var category = categoryRepository.findById(id);

        if(category.isPresent()) {
            return category.get();
        }else {
            throw new RuntimeException("something went wrong with id = " + id);
        }
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.delete(getCategory(id));
    }

    @Override
    public List<Tasks> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Tasks getTask(Long id) {
        var task = taskRepository.findById(id);

        if(task.isPresent()) {
            return task.get();
        }else {
            throw new RuntimeException("something went wrong with id = " + id);
        }
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.delete(getTask(id));
    }

    @Override
    public Tasks updateTask(Tasks task) {
        return taskRepository.save(task);
    }

    @Override
    public Tasks saveTask(Tasks task) {
        return taskRepository.save(task);
    }

    @Override
    public List<Tasks> findAllByFolder_Id(Long id) {
        return taskRepository.findAllByFolder_Id(id);
    }
}
