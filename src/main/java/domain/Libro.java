package domain;

import javax.persistence.*;

@Entity
@Table(name = "libro")
public class Libro{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLibro;

    private String titulo;

    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "idAutor")
    private Autor autor;

    public Libro() {
    }

    public Libro(String titulo, String descripcion, Autor autor) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.autor = autor;
    }
    public Libro(String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        autor=null;
    }

    public Autor getAutor() {
        return autor;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        if(autor==null){
            return "Libro{" +
                    "idLibro=" + idLibro +
                    ", titulo='" + titulo + '\'' +
                    ", descripcion='" + descripcion + '\'' +
                    ", autor= vacio" +
                    '}';
        }
        return "Libro{" +
                "idLibro=" + idLibro +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", autor=" + autor.getNombre() +
                '}';
    }
}
