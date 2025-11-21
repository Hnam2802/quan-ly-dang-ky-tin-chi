package model.interfaces;

import java.util.List;

/**
 * Interface CRUD tối giản cho manager
 */
public interface IManager<T> {
    void add(T t);
    void delete(String id);
    List<T> getAll();
}
