package springboot_demo.dao;

import org.springframework.stereotype.Repository;
import springboot_demo.model.Todo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TodoDAO extends GenericDAO<Todo> {
    public TodoDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void insert(Todo toInsert) throws SQLException {
        String sql = "INSERT INTO todo(title, description, deadline, priority, done) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, toInsert.getTitle());
            statement.setString(2, toInsert.getDescription());
            statement.setTimestamp(3, Timestamp.valueOf(toInsert.getDeadline()));
            statement.setInt(4, toInsert.getPriority());
            statement.setBoolean(5, toInsert.isDone());

            statement.executeUpdate();
        }
    }

    @Override
    public List<Todo> findAll() throws SQLException {
        List<Todo> todos = new ArrayList<>();
        String sql = "SELECT * FROM todo";

        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Todo todo = extractTodoFromResultSet(resultSet);
                todos.add(todo);
            }
        }
        return todos;
    }

    @Override
    public Optional<Todo> findById(int id) throws SQLException {
        String sql = "SELECT * FROM todo WHERE id = ?";

        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(extractTodoFromResultSet(resultSet));
                }
            }
        }
        return Optional.empty();
    }

    private Todo extractTodoFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String description = resultSet.getString("description");
        LocalDateTime deadline = resultSet.getTimestamp("deadline").toLocalDateTime();
        int priority = resultSet.getInt("priority");
        boolean done = resultSet.getBoolean("done");

        return new Todo(id, title, description, deadline, priority, done);
    }
}
