package springboot_demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import springboot_demo.controller.TodoController;
import springboot_demo.model.Todo;
import springboot_demo.service.TodoService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TodoControllerTest {

    @Mock
    private TodoService todoService;

    @InjectMocks
    private TodoController todoController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllTodos() {
        // Prepare test data
        List<Todo> todos = List.of(todo1(), todo2());

        // Configure mock behavior
        when(todoService.findAllTodos()).thenReturn(todos);

        // Call the controller method
        List<Todo> result = todoController.getAllTodos();

        // Verify the result
        assertEquals(2, result.size());
        assertEquals("Todo 1", result.get(0).getTitle());
        assertEquals("Todo 2", result.get(1).getTitle());

        // Verify that the service method was called only once
        verify(todoService, times(1)).findAllTodos();
    }

    @Test
    public void getTodoById_ok() {
        // Prepare test data
        int todoId = 1;
        Todo todo = new Todo(todoId, "Test Todo", "Test Description",
                LocalDateTime.now(), 1, false);

        // Configure mock behavior
        when(todoService.findTodoById(todoId)).thenReturn(Optional.of(todo));

        // Call the controller method
        Optional<Todo> result = todoController.getTodoById(todoId);

        // Verify the result
        assertEquals(todo.getTitle(), result.get().getTitle());

        // Verify that the service method was called
        verify(todoService, times(1)).findTodoById(todoId);
    }

    @Test
    public void getTodoById_ko() {
        // Prepare test data
        int todoId = 100;

        // Configure mock behavior
        when(todoService.findTodoById(todoId)).thenReturn(Optional.empty());

        // Call the controller method
        Optional<Todo> result = todoController.getTodoById(todoId);

        // Verify the result
        assertEquals(Optional.empty(), result);

        // Verify that the service method was called
        verify(todoService, times(1)).findTodoById(todoId);
    }

    @Test
    public void insertTodo_ok() {
        // Prepare test data
        Todo todoToInsert = todo1();

        // Configure mock behavior
        when(todoService.insert(todoToInsert)).thenReturn(todoToInsert);

        // Call the controller method
        Todo result = todoController.insertTodo(todoToInsert);

        // Verify the result
        assertEquals(todoToInsert, result);

        // Verify that the service method was called
        verify(todoService, times(1)).insert(todoToInsert);
    }

    private Todo todo2() {
        return new Todo(2, "Todo 2", "Description 2", LocalDateTime.now(),
                2, true);
    }

    private Todo todo1() {
        return new Todo(1, "Todo 1", "Description 1", LocalDateTime.now(),
                1, false);
    }
}
