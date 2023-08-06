package springboot_demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springboot_demo.model.Todo;
import springboot_demo.service.TodoService;

import java.util.List;
import java.util.Optional;

@RestController
public class TodoController {
    private TodoService todoService;

    public TodoController(TodoService todoService){
        this.todoService = todoService;
    }

    @GetMapping("/ping")
    public String pingPong(){
        return "pong";
    }

    @GetMapping("/todos")
    public List<Todo> getAllTodos(){
        return todoService.findAllTodos();
    }

    @GetMapping("/todo/{id}")
    public Optional<Todo> getTodoById(@PathVariable int id){
        return todoService.findTodoById(id);
    }

    @PostMapping("/todo")
    public Todo insertTodo(@RequestBody Todo toInsert){
        return todoService.insert(toInsert);
    }
}
