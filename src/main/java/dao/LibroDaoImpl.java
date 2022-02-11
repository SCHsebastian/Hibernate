package dao;

import domain.Libro;
import domain.Libro_;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class LibroDaoImpl implements LibroDao {
    @Override
    public void insert(Libro libro) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(libro);

            transaction.commit();
        }catch (HibernateException e){
            if (transaction!= null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void update(Libro libro) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.update(libro);

            transaction.commit();
        }catch (HibernateException e){
            if (transaction!= null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Libro libro) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.delete(libro);

            transaction.commit();
        }catch (HibernateException e){
            if (transaction!= null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Libro read(int idLibro) {
        Libro libro = new Libro();
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Libro> criteria = builder.createQuery(Libro.class);
            Root<Libro> root = criteria.from(Libro.class);

            criteria.where(
                    builder.equal(root.get(Libro_.idLibro),idLibro)
            );
            libro = session.createQuery(criteria).getSingleResult();

            transaction.commit();
        }catch (HibernateException e){
            if (transaction!= null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return libro;
    }

    @Override
    public List<Libro> readAll() {
        List<Libro> libros;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Libro> criteria = builder.createQuery(Libro.class);
            Root<Libro>root = criteria.from(Libro.class);

            criteria.select(root);
            libros = session.createQuery(criteria).getResultList();

        }catch (HibernateException e){
            libros = null;
            e.printStackTrace();
        }
        return libros;
    }
}
