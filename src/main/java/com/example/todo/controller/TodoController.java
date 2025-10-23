package com.example.todo.controller;

import com.example.todo.model.Todo;
import com.example.todo.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TodoController {
    
    private final TodoService todoService;

    public TodoController(TodoService todoService){
        this.todoService = todoService;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("todos", todoService.getAllTodos());
        return "index";
    }

    @PostMapping("/add")
    public String addTodo(@RequestParam("title") String title) {
        todoService.addTodo(title);
        return "redirect:/";
    }
    
    @PostMapping("/delete")
    public String deleteTodo(@RequestParam("id") Long id) {
        todoService.deleteTodo(id);
        return "redirect:/";
    }
    
    @GetMapping("/edit")
    public String showEditForm(@RequestParam("id") Long id, Model model) {
        Todo todo = todoService.getTodo(id);
        model.addAttribute("todo", todo);
        model.addAttribute("id", id);
        return "edit";
    }
    
    @PostMapping("/update")
    public String updateTodo(@RequestParam("id") Long id, @RequestParam("title") String newTitle) {
        todoService.updateTodo(id, newTitle);        
        return "redirect:/";
    }
    
    @PostMapping("/toggle")
    public String toggleTodo(@RequestParam("id") Long id) {
        todoService.toggleDone(id);
        return "redirect:/";
    }
}

