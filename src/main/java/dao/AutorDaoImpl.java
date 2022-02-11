package dao;

import domain.Autor;
import domain.Autor_;
import domain.Libro;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class AutorDaoImpl implements AutorDao{
    @Override
    public void insert(Autor autor) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(autor);

            transaction.commit();
        }catch (HibernateException e){
            if (transaction!= null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void update(Autor autor) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.update(autor);

            transaction.commit();
        }catch (HibernateException e){
            if (transaction!= null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Autor autor) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.delete(autor);

            transaction.commit();
        }catch (HibernateException e){
            if (transaction!= null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Autor read(int idAutor) {
        Autor autor = new Autor();
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Autor> criteria = builder.createQuery(Autor.class);
            Root<Autor> root = criteria.from(Autor.class);

            criteria.where(
                    builder.equal(root.get(Autor_.idAutor),idAutor)
            );
            autor = session.createQuery(criteria).getSingleResult();

            transaction.commit();
        }catch (HibernateException e){
            if (transaction!= null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return autor;

    }

    @Override
    public List<Autor> readAll() {
        List<Autor> autores;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Autor> criteria = builder.createQuery(Autor.class);
            Root<Autor> root = criteria.from(Autor.class);

            criteria.select(root);
            autores = session.createQuery(criteria).getResultList();

        }catch (HibernateException e){
            autores = null;
            e.printStackTrace();
        }
        return autores;
    }

    @Override
    public List<Libro> ReadBooks(Autor autor) {
        return autor.getLibros();
    }
}
