package domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "autor")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int idAutor;

    private String nombre;

    private Date fechaNac;

    private Date fechaMue;

    @OneToMany(mappedBy = "autor", cascade =  CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Libro> libros = new ArrayList<>();



    public Autor() {
    }

    public Autor(String nombre){
        this.nombre = nombre;
    }

    public Autor(String nombre, Date fechaNac, Date fechaMue) {
        this.nombre = nombre;
        this.fechaNac = fechaNac;
        this.fechaMue = fechaMue;
    }

    public void anyadirLibro(Libro libro){
        libros.add(libro);
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public Date getFechaMue() {
        return fechaMue;
    }

    public void setFechaMue(Date fechaMue) {
        this.fechaMue = fechaMue;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        String libro="";
        String fechaNa="";
        String fechaMu="";
        if(libros.isEmpty()){
            libro = "No tiene";
        }
        else{
            for (Libro lib:
                 libros) {
                libro += lib.toString();
            }
        }
        if (fechaNac==null){
            fechaNa="No indicada";
        }else{
            fechaNa = fechaNac.toString();
        }
        if (fechaMue==null){
            fechaMu="No indicada/Sigue vivo";
        }else {
            fechaMu = fechaMue.toString();
        }
        return "Autor{" +
                "idAutor=" + idAutor +
                ", nombre='" + nombre + '\'' +
                ", fechaNac=" + fechaNa +
                ", fechaMue=" + fechaMu +
                ", libros=" + libro +
                '}';
    }
}
