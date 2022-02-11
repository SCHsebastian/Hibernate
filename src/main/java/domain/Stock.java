package domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name= "stock")
public class Stock implements Serializable {

    @Id
    @OneToOne
    @JoinColumn(name = "idLibro")
    private Libro idLibro;

    private int totalStock;

    private Date ultimoInventario;

    public Stock(){    }

    public Stock(Libro idLibro, int totalStock) {
        this.idLibro = idLibro;
        this.totalStock = totalStock;
    }

    public Stock(Libro idLibro, int totalStock, Date ultimoInventario) {
        this.idLibro = idLibro;
        this.totalStock = totalStock;
        this.ultimoInventario = ultimoInventario;
    }

    public Libro getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Libro idLibro) {
        this.idLibro = idLibro;
    }

    public int getTotalStock() {
        return totalStock;
    }

    public void setTotalStock(int totalStock) {
        this.totalStock = totalStock;
    }

    public Date getUltimoInventario() {
        return ultimoInventario;
    }

    public void setUltimoInventario(Date ultimoInventario) {
        this.ultimoInventario = ultimoInventario;
    }
}
