package service;

import dao.LibroDao;
import dao.LibroDaoImpl;
import domain.Libro;

import java.util.List;

public class LibroService {

    private final LibroDao libroDao;

    public LibroService(){
        libroDao = new LibroDaoImpl();
    }

    public Libro read(int idLibro){
        return libroDao.read(idLibro);
    }

    public void insert(Libro libro){
        libroDao.insert(libro);
    }
    public List<Libro> readAll(){
        return libroDao.readAll();
    }

    public void update(Libro libro){
        libroDao.update(libro);
    }

    public void delete(Libro libro){
        libroDao.delete(libro);
    }
}
