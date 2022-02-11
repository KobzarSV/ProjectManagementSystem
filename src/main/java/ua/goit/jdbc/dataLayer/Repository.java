package ua.goit.jdbc.dataLayer;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {
    void create(T t);

    Optional<T> readById(int id);

    List<T> readAll();

    int update(T t);

    void delete(T t);
}
