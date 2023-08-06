package springboot_demo.service;

import org.springframework.stereotype.Service;
import springboot_demo.dao.TodoDAO;
import springboot_demo.model.Todo;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private TodoDAO todoDAO;

    public TodoService(TodoDAO todoDAO){
        this.todoDAO = todoDAO;
    }

    public List<Todo> findAllTodos(){
        try {
            return todoDAO.findAll();
        } catch (SQLException e) {
            throw new RuntimeException("There has been an error when fetching all todos");
        }
    }

    public Optional<Todo> findTodoById(int id){
        try{
           return todoDAO.findById(id);
        }catch(SQLException e){
            throw new RuntimeException("There has been an error when fetching todo with id : "+id);
        }
    }

    public Todo insert(Todo toInsert){
       try{
           this.todoDAO.insert(toInsert);

           return toInsert;
       }catch(SQLException e){
           throw new RuntimeException("There was an error when inserting the todo.");
       }
    }
}
