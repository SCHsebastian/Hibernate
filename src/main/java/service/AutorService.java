package service;

import dao.AutorDao;
import dao.AutorDaoImpl;
import domain.Autor;
import domain.Libro;

import java.util.List;

public class AutorService {

    private final AutorDao autorDao;

    public AutorService() {
        autorDao = new AutorDaoImpl();
    }

    public void insert(Autor autor){
        autorDao.insert(autor);
    }
    public void update(Autor autor){
        autorDao.update(autor);
    }

    public void delete(Autor autor){
        autorDao.delete(autor);
    }

    public Autor read(int idAutor)
    {
        return autorDao.read(idAutor);
    }
    public List<Libro> readBooks(Autor autor){
        return autorDao.ReadBooks(autor);
    }
    public List<Autor> readAll(){
        return autorDao.readAll();
    }

}
