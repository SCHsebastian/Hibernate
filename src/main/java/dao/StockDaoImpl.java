package dao;

import domain.Stock;
import domain.Stock_;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class StockDaoImpl implements StockDao{
    @Override
    public void insert(Stock stock) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(stock);

            transaction.commit();
        }catch (HibernateException e){
            if (transaction!= null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void update(Stock stock) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.update(stock);

            transaction.commit();
        }catch (HibernateException e){
            if (transaction!= null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    @Override
    public void delete(Stock stock) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.delete(stock);

            transaction.commit();
        }catch (HibernateException e){
            if (transaction!= null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Stock read(int idLibro) {
        Stock stock = new Stock();
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Stock> criteria = builder.createQuery(Stock.class);
            Root<Stock> root = criteria.from(Stock.class);

            criteria.where(
                    builder.equal(root.get(Stock_.idLibro),idLibro)
            );
            stock = session.createQuery(criteria).getSingleResult();

            transaction.commit();
        }catch (HibernateException e){
            if (transaction!= null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return stock;
    }

    @Override
    public List<Stock> readAll() {
        List<Stock> stocks;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Stock> criteria = builder.createQuery(Stock.class);
            Root<Stock>root = criteria.from(Stock.class);

            criteria.select(root);
            stocks = session.createQuery(criteria).getResultList();

        }catch (HibernateException e){
            stocks = null;
            e.printStackTrace();
        }
        return stocks;
    }
}
