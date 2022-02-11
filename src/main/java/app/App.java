package app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import domain.Autor;
import domain.Libro;
import domain.Stock;
import service.AutorService;
import service.LibroService;
import service.StockService;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class App {

    static Libro libro;
    static Autor autor;
    static Stock stock;

    static AutorService autorService = new AutorService();
    static LibroService libroService = new LibroService();
    static StockService stockService = new StockService();

    static String util,util2,sn;
    static int num;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion=1;

        while (opcion != 0) {
            System.out.println(
                    "1. Ver libro por ID\n"+
                    "2. Añadir un libro\n"+
                    "3. Ver todos los libros\n"+
                    "4. Modificar un libro\n"+
                    "5. Ver libros de un autor\n"+
                    "6. Ver todos los autores\n"+
                    "7. Añadir Autor\n"+
                    "8. Modificar Autor\n"+
                    "9. Ver Stock de un Libro\n"+
                    "10. Modificar Stock de un Libro\n"+
                    "11. Eliminar Libro\n"+
                    "12. Eliminar Autor\n"+
                    "13. Exportar datos a JSON\n"+
                    "0. Salir\n");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    verLibro();
                    break;
                case 2:
                    anyadeLibro();
                    break;
                case 3:
                    verLibros();
                    break;
                case 4:
                    modificaLibro();
                    break;
                case 5:
                    verLibrosAutor();
                    break;
                case 6:
                    verAutores();
                    break;
                case 7:
                    anyadeAutor();
                    break;
                case 8:
                    modificaAutor();
                    break;
                case 9:
                    verStockLibro();
                    break;
                case 10:
                    modificaStock();
                    break;
                case 11:
                    eliminaLibro();
                    break;
                case 12:
                    eliminarAutor();
                    break;
                case 13:
                    exportarJSON();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opcion no valida");
                    break;

            }
        }
    }

    private static void exportarJSON() {
        exportJson(libroService.readAll(), autorService.readAll(), stockService.readAll());
    }


    // ACCIONES DE STOCK

    private static void modificaStock() {
        System.out.println("Introduce el ID del libro:\n");
        num = sc.nextInt();
        stock = stockService.read(num);
        System.out.println("Introduce el nuevo stock:\n");
        util2 = sc.nextLine();
        stockService.update(stock);
        System.out.println("Stock modificado con éxito.\n");
    }

    private static void verStockLibro() {
        System.out.println("Introduce el ID del libro:\n");
        num = sc.nextInt();
        stock = stockService.read(num);
        System.out.println("Stock: "+stock.getTotalStock()+"\n");
    }


    // ACCIONES DE AUTOR

    private static void modificaAutor() {
        System.out.println("Introduce el ID del autor:\n");
        num = sc.nextInt();
        autor = autorService.read(num);
        System.out.println("Introduce el nuevo nombre:\n");
        util = sc.nextLine();
        autor.setNombre(util);
        System.out.println("El nombre ha sido cambiado con éxito.\n");

    }

    private static void anyadeAutor() {
        System.out.println("Introduce el nombre del Autor a Añadir:\n");
        util = sc.nextLine();
        autor = new Autor(util);
        autorService.insert(autor);
        System.out.println("Autor añadido con éxito.\n");
    }

    private static void verAutores() {
        System.out.println("Estos son todos los autores:\n");
        for (Autor auto:
             autorService.readAll()) {
            System.out.println(auto.toString());
        }
    }

    private static void verLibrosAutor() {
        System.out.println("Introduce el id del Autor:\n");
        num = sc.nextInt();
        autor=autorService.read(num);
        for (Libro librillo:
                autorService.readBooks(autor)) {
            System.out.println(librillo.toString());
        }
    }

    private static void eliminarAutor() {
        System.out.println("Introduce el id del Autor a eliminar:\n");
        num = sc.nextInt();
        autor = autorService.read(num);
        System.out.println("Se eliminará el autor: " + autor.getNombre()+"\n");
        System.out.println("¿Estás seguro? (S/N)\n");
        sn = sc.next().toUpperCase();
        while (!sn.equals("S") || !sn.equals("N")){
            System.out.println("¿Estás seguro?(S/N)\n");
            sn = sc.next().toUpperCase();
        }
        if (sn.equals("S")){
            autorService.delete(autor);
            System.out.println("El autor ha sido eliminado.\n");
        }else {
            System.out.println("Se ha cancelado el borrado del Autor\n");
        }
    }


    // ACCIONES DE LIBROS

    private static void eliminaLibro() {
        System.out.println("Introduce el id del Libro a eliminar:\n");
        sc = new Scanner(System.in);
        num = sc.nextInt();
        libro=libroService.read(num);
        System.out.println("Se eliminará el libro: "+libro.getTitulo()+"\n");
        System.out.println("¿Estás seguro?(S/N)\n");
        sn = sc.next().toUpperCase();
        while (!sn.equals("S") || !sn.equals("N")){
            System.out.println("¿Estás seguro?(S/N)\n");
            sn = sc.next().toUpperCase();
        }
        if (sn.equals("S")){
            libroService.delete(libro);
            System.out.println("Libro eliminado con éxito.\n");
        }else {
            System.out.println("Se ha cancelado el borrado del Libro " + libro.getTitulo()+"\n");
        }
    }

    private static void modificaLibro() {
        System.out.println("Introduce el id del Libro a modificar:\n");
        sc = new Scanner(System.in);
        num = sc.nextInt();
        libro = libroService.read(num);
        System.out.println("Vas a modificar el libro: \n"+
                libroService.read(num)+
                "\nIntroduce el nuevo nombre:");
        util = sc.next();
        System.out.println("Introduce la nueva descripción:\n");
        util2 = sc.next();
        libro.setTitulo(util);
        libro.setDescripcion(util2);
        System.out.println("¿Deseas modificar el Autor? (S/N)\n");
        sn = sc.next().toUpperCase();
        while (!sn.equals("S") || !sn.equals("N")){
            System.out.println("¿Deseas modificar el Autor? (S/N)\n");
            sn = sc.next().toUpperCase();
        }
        if (sn.equals("S")){
            System.out.println("¿Existe el autor a utilizar? (S/N)\n");
            sn = sc.next().toUpperCase();
            while (!sn.equals("S") || !sn.equals("N")){
                System.out.println("¿Existe el autor a utilizar? (S/N)\n");
                sn = sc.next().toUpperCase();
            }
            if(sn.equals("S")){
                System.out.println("Introduce el id del autor:\n");
                num = sc.nextInt();
                autor = autorService.read(num);
                libro.setAutor(autor);
            }else {
                anyadeAutor();
                libro.setAutor(autor);
            }
        }
        else{
            System.out.println("No se modificará el autor\n");
            libroService.update(libro);
        }
        System.out.println("Libro modificado con éxito.\n");
    }

    private static void verLibros() {
        for (Libro libro: libroService.readAll()) {
            System.out.println(libro.toString());
        }
    }

    static void verLibro(){
        System.out.println("Introduce el id del Libro\n");
        sc = new Scanner(System.in);
        num = sc.nextInt();
        System.out.println(libroService.read(num));
    }

    private static void anyadeLibro() {
        System.out.println("Introduce el nómbre del libro\n");
        sc = new Scanner(System.in);
        util = sc.nextLine();
        System.out.println("Introduce una descripción\n");
        util2 = sc.nextLine();
        System.out.println("¿Existe un autor para el libro? (S/N)\n");
        sn = sc.nextLine().toUpperCase();
        while (!sn.equals("S") || !sn.equals("N")){
            System.out.println("¿Existe un autor para el libro? (S/N)\n");
            sn = sc.nextLine().toUpperCase();
        }
        autor=null;
        if (sn.equals("S")){
            System.out.println("Introduce el id del autor:\n");
            num = sc.nextInt();
            // Comprueba que el autor existe
            autor = autorService.read(num);
            while (autor == null){
                System.out.println("El autor no existe. Introduce el id del autor:\n");
                num = sc.nextInt();
                autor = autorService.read(num);
            }
            libro = new Libro(util, util2, autor);
        }
        else{
            System.out.println("¿Desea crear uno? (S/N)\n");
            sn = sc.nextLine().toUpperCase();
            while (!sn.equals("S") || !sn.equals("N")){
                System.out.println("¿Desea crear uno? (S/N)\n");
                sn = sc.nextLine().toUpperCase();
            }
            if (sn.equals("S")){
                anyadeAutor();
                libro = new Libro(util, util2, autor);
                libroService.insert(libro);
                System.out.println("Libro creado con éxito.\n");
            }
            else{
                libro = new Libro(util, util2);
                libroService.insert(libro);
                System.out.println("Libro añadido sin autor\n");
            }
        }
        anyadeStock(libro);
    }

    private static void anyadeStock(Libro libro) {
        System.out.println("¿Desea añadir stock? (S/N)\n");
        sn = sc.nextLine().toUpperCase();
        while (!sn.equals("S") || !sn.equals("N")){
            System.out.println("¿Desea añadir stock? (S/N)\n");
            sn = sc.nextLine().toUpperCase();
        }
        if (sn.equals("S")){
            System.out.println("Introduce el stock:\n");
            num = sc.nextInt();
            stock = new Stock(libro, num);
        }
        else{
            System.out.println("No se añadirá stock\n");
            stock = new Stock(libro, 0);
        }
        stockService.insert(stock);
    }


    private static void exportJson(List<Libro> libros, List<Autor> autores, List<Stock> stocks) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            FileWriter writer = new FileWriter("Libros.json");
            gson.toJson(libros, writer);
            writer = new FileWriter("Autores.json");
            gson.toJson(autores, writer);
            writer = new FileWriter("Stocks.json");
            gson.toJson(stocks, writer);
            System.out.println("EXPORTACIÓN COMPLETA.\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
