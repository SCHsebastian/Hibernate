package service;

import dao.StockDao;
import dao.StockDaoImpl;
import domain.Stock;

import java.util.List;


public class StockService {

    private final StockDao stockDao;

    public StockService(){
        stockDao = new StockDaoImpl();
    }

    public Stock read(int idLibro){
        return stockDao.read(idLibro);
    }
    public void update(Stock stock){
        stockDao.update(stock);
    }
    public void delete(Stock stock){
        stockDao.delete(stock);
    }
    public void insert(Stock stock){
        stockDao.insert(stock);
    }
    public List<Stock> readAll(){
        return stockDao.readAll();
    }


}
