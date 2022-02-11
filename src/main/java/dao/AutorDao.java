package dao;

import domain.Autor;
import domain.Autor_;
import domain.Libro;

import java.util.List;

public interface AutorDao {
    //CRUD
    void insert(Autor autor);
    void update(Autor autor);
    void delete(Autor autor);
    Autor read(int idAutor);
    List<Autor> readAll();
    List<Libro> ReadBooks(Autor autor);
}
