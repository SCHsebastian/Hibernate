package dao;

import domain.Libro;

import java.util.List;

public interface LibroDao {
    void insert(Libro libro);
    void update(Libro libro);
    void delete(Libro libro);
    Libro read(int idLibro);
    List<Libro> readAll();

}
