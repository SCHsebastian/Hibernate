package dao;

import domain.Libro;
import domain.Stock;

import java.util.List;

public interface StockDao {
    void insert(Stock stock);
    void update(Stock stock);
    void delete(Stock stock);
    Stock read(int idLibro);
    List<Stock> readAll();
}
