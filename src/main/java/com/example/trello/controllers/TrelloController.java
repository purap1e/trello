package com.example.trello.controllers;

import com.example.trello.entities.Folders;
import com.example.trello.entities.TaskCategories;
import com.example.trello.entities.Tasks;
import com.example.trello.services.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TrelloController {

    private final FolderService folderService;

    @Autowired
    public TrelloController(FolderService folderService) {
        this.folderService = folderService;
    }


    @GetMapping(value = "/")
    public String getTrello(Model model) {
        List<Folders> folders = folderService.getAllFolders();
        model.addAttribute("folders", folders);
        return "trello";
    }

    @PostMapping(value = "/addFolder")
    public String addFolder(@RequestParam(name = "name") String name) {
        Folders folder = new Folders(null, name, null);
        folderService.saveFolder(folder);

        return "redirect:/";
    }

    @GetMapping( value = "/folder-details/{id}")
    public String getFolderDetails(Model model,
                                   @PathVariable(name = "id") Long id) {
        model.addAttribute("folder",folderService.getFolder(id));
        List<TaskCategories> categories_of_folder = folderService.getFolder(id).getCategories();
        List<TaskCategories> categories = folderService.getAllCategories();
        List<Tasks> tasks = folderService.findAllByFolder_Id(id);
        model.addAttribute("tasks",tasks);
        model.addAttribute("categories",categories);
        model.addAttribute("categories_of_folder",categories_of_folder);
        return "folder-details";
    }

    @PostMapping(value = "/addCategory")
    public String addCategory(@RequestParam(name = "folder_id") Long folderId,
                              @RequestParam(name = "category_id") Long categoryId ) {
        Folders folder = folderService.getFolder(folderId);

        if(folder!=null) {
            TaskCategories category = folderService.getCategory(categoryId);
            if(category!=null){
                List<TaskCategories> categories = folder.getCategories();
                if(categories==null){
                    categories = new ArrayList<>();
                }
                categories.add(category);
                folderService.updateFolder(folder);
                return "redirect:/folder-details/" + folder.getId();
            }
        }
        return "redirect:/";
    }

    @PostMapping(value = "/deleteCategory")
    public String deleteCategory(@RequestParam(name = "category_id") Long categoryId,
                                 @RequestParam(name = "folder_id") Long folderId) {
        Folders folder = folderService.getFolder(folderId);

        if(folder!=null) {
            TaskCategories category = folderService.getCategory(categoryId);
            if(category!=null){
                List<TaskCategories> categories = folder.getCategories();
                if(categories==null){
                    categories = new ArrayList<>();
                }
                categories.remove(category);
                folderService.updateFolder(folder);
                return "redirect:/folder-details/" + folder.getId();
            }
        }
        return "redirect:/";
    }

    @PostMapping(value = "/addTask")
    public String addTask(@RequestParam(name = "folder_id") Long id,
                          @RequestParam(name = "title") String title,
                          @RequestParam(name = "description") String description){
        Tasks task = new Tasks(null,title,description,0,folderService.getFolder(id));
        folderService.saveTask(task);
        return "redirect:/folder-details/" + id;
    }

    @GetMapping(value = "/details-task/{id}")
    public String getDetailsTask(Model model,
                                 @PathVariable(name = "id") Long id) {
        Tasks task = folderService.getTask(id);
        model.addAttribute("task",task);
        return "details-task";
    }

    @PostMapping(value = "/updateTask")
    public String updateTask(@RequestParam(name = "id") Long id,
                             @RequestParam(name = "title") String title,
                             @RequestParam(name = "description") String description,
                             @RequestParam(name = "status") int status){
        Tasks task = folderService.getTask(id);
        task.setTitle(title);
        task.setDescription(description);
        task.setIndex(status);
        folderService.updateTask(task);

        return "redirect:/folder-details/" + folderService.getTask(id).getFolder().getId();
    }
}