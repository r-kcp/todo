package com.example.todo.service;

import com.example.todo.model.Todo;
import com.example.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository){
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAllTodos(){
        return todoRepository.findAll();
    }

    public void addTodo(String title){
        Todo todo = new Todo(title);
        todoRepository.save(todo);
    }

    public void deleteTodo(Long id){
        todoRepository.deleteById(id);    
    }

    public Todo getTodo(Long id){
        return todoRepository.findById(id).orElse(null);
    }

    public void updateTodo(Long id, String newTitle){
        Todo todo = todoRepository.findById(id).orElse(null);
        if(todo != null){
            todo.setTitle(newTitle);
            todoRepository.save(todo);
        }
    }

    public void toggleDone(Long id){
        Todo todo = todoRepository.findById(id).orElse(null);
        if(todo != null){
            todo.setDone(!todo.isDone());
            todoRepository.save(todo);
        }
    }
}