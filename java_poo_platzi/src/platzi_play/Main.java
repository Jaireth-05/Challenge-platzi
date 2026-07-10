/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package platzi_play;
import platzi_play.Plataforma.Plataforma;
import platzi_play.Util.FilesUtils;
import platzi_play.Util.ScannerUtils;
import platzi_play.contenido.*;
import platzi_play.Plataforma.Usuario;
import platzi_play.exception.ContenidoExistenteException;

import java.io.File;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author jaire
 */
public class Main {
 public static final String NOMBRE_PLATAFORMA = "PLATZI PLAY";
 public static final String VERSION = "1.0.0";

 public static final int AGREGAR = 1;
 public static final int MOSTRAR = 2;
 public static final int BUSCAR = 3;
 public static final int BUSCARGENERO = 4;
 public static final int BUSCARPORIDIOMA = 5;
 public static final int POPULARES = 6;
 public static final int REPRODUCIR = 7;
 public static final int BUSCAR_POR_TIPO = 8;
 public static final int ELIMINAR = 9;
 public static final int SALIR = 10;
       public static void main(String[] args) {
        System.out.println(NOMBRE_PLATAFORMA + " v " + VERSION);
        Plataforma plataforma = new Plataforma(NOMBRE_PLATAFORMA);

        cargarContenidos(plataforma);

        System.out.println("Mas de " + plataforma.getDuracionTotal() + " minutos de contenido" +"\n");
        plataforma.getPromocionar().forEach(promocionar -> System.out.println(promocionar.promocionar()));
        while (true){
         int opcionSeleccionada = ScannerUtils.capturarNumero("""
                 Ingrese una de las siguientes opciones:
                 1. Agregar contenido
                 2. Mostrar todo
                 3. Buscar por título
                 4. Buscar por genero
                 5. Buscar por idioma
                 6. Ver populares
                 7. Reproducir
                 8. ¿Qué tipo de contenido deseas ver?
                 9. Eliminar
                 10. Salir
                 """);


         switch (opcionSeleccionada) {
          case AGREGAR -> {
           int opcionContenido = ScannerUtils.capturarNumero("Que tipo de contenido deseas agregar: 1.Película \n2.Documental");
           String nombre = ScannerUtils.capturarTexto("Nombre del contenido");
           Genero genero = ScannerUtils.capturarGenero("Genero del contenido a buscar");
           int duracion = ScannerUtils.capturarNumero("Duración del contenido");
           double calificar = ScannerUtils.capturarDecimal("Calificación del contenido");
           Lenguage lenguage = ScannerUtils.capturaIdioma("Idioma del contenido");
           try {
            if(opcionContenido == 1){
             plataforma.agregar(new Pelicula(nombre,duracion, genero, calificar, lenguage));
            }else{
             String narrador = ScannerUtils.capturarTexto("Ingrese el narrador del documental");
             plataforma.agregar(new Documental(nombre,duracion, genero, calificar, lenguage, narrador));
            }

           }catch (ContenidoExistenteException e){
            System.out.println(e.getMessage());
           }

          }
          case MOSTRAR -> {
            List<ResumenContenido> contenidoResumidos = plataforma.getResumen();
           contenidoResumidos.forEach(resumen -> System.out.println(resumen.toString()));
          }
          case BUSCAR -> {
           String tituloBuscado = ScannerUtils.capturarTexto("Digite el titulo de la película");
           Contenido Contenido = plataforma.buscarTitulo(tituloBuscado);

           if (Contenido !=null){
            System.out.println(Contenido.obtenerFichaTecnica());
           }else{
            System.out.println(tituloBuscado + " El conteindo no está disponible dentro de " + plataforma.getNombre());
           }
          }
          case BUSCARGENERO -> {
           Genero genero = ScannerUtils.capturarGenero("Digite el genero que deseas ver");
           List<Contenido> contenidoPorGenero = plataforma.buscarporGenero(Genero.valueOf(String.valueOf(genero)));

           System.out.println(contenidoPorGenero.size() + " resultados encontrados para el genero " + genero );

           contenidoPorGenero.forEach(contenido -> System.out.println(contenido.obtenerFichaTecnica() + "\n"));
          }
          case BUSCARPORIDIOMA -> {
           Lenguage lenguage = ScannerUtils.capturaIdioma("Digite el idioma por el que deseas buscar");
           List<Contenido> contenidoPorIdioma = plataforma.buscarPorIdioma(Lenguage.valueOf(String.valueOf(lenguage)));

           System.out.println(contenidoPorIdioma.size() + " Resultados encontrados para el idioma " + lenguage);

           contenidoPorIdioma.forEach(contenido -> System.out.println(contenido.obtenerFichaTecnica() + " \n"));
          }
          case POPULARES -> {
           int cantidad = ScannerUtils.capturarNumero("Cantidad de resultados a mostrar");

           List<Contenido> verPopulares = plataforma.getPopular(cantidad);
           verPopulares.forEach(contenido -> System.out.println(contenido.obtenerFichaTecnica() + "\n"));
          }

          case REPRODUCIR -> {
           String nombre = ScannerUtils.capturarTexto("Ingrese el nombre de la película");
           Contenido contenido = plataforma.buscarTitulo(nombre);

           if(contenido !=null){
            plataforma.reproducir(contenido);
           }else{
            System.out.println(nombre + " no se encuentra disponible dentro de " + plataforma.getNombre());
           }
          }

          case BUSCAR_POR_TIPO -> {
           int tipoDeContenido = ScannerUtils.capturarNumero("Que tipo de contenido quieres agregar?\n 1. Pelicula\n2. Documental");

           if (tipoDeContenido == 1) {
            List<Pelicula> peliculas = plataforma.getPeliculas();
            peliculas.forEach(pelicula -> System.out.println(pelicula.obtenerFichaTecnica() + "\n"));
           } else {
            List<Documental> documentales = plataforma.getDocumentales();
            documentales.forEach(documental -> System.out.println(documental.obtenerFichaTecnica() + "\n"));
           }
          }
          case ELIMINAR -> {
           String tituloAEliminar = ScannerUtils.capturarTexto("Digite el titulo de la película a eliminar");
           Contenido Contenido = plataforma.buscarTitulo(tituloAEliminar);


           if (Contenido !=null){
            plataforma.eliminar(Contenido);
            System.out.println(tituloAEliminar + " Ha sido eliminado correctamente");
           }else{
            System.out.println(tituloAEliminar + " El conteindo no esta disponible dentro de " + plataforma.getNombre());
           }
          }
          case SALIR -> System.exit(0);
         }


        }
    }
 private static void cargarContenidos (Plataforma plataforma){
        plataforma.getContenido().addAll(FilesUtils.leerContenido());

 }
}

