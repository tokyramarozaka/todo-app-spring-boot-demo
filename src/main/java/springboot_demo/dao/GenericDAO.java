package springboot_demo.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public abstract class GenericDAO<T> {
    private Connection connection;

    public GenericDAO(Connection connection){
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public abstract void insert(T toInsert) throws SQLException;

    public abstract List<T> findAll() throws SQLException;

    public abstract Optional<T> findById(int id) throws SQLException;
}
