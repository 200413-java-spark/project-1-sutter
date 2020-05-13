package app.database;

import java.util.List;

public interface Dao<E> {
    
    int count();

    boolean insert(E e);

    boolean insertMany(List<E> e);

    boolean update(E e);

    boolean delete(E e);

    boolean deleteAll();

    E select(E e);

    List<E> selectAll();

    int getId(E e);

    E selectById(int i);

}