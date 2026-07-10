package platzi_play.Plataforma;

import platzi_play.Util.FilesUtils;
import platzi_play.contenido.*;
import platzi_play.exception.ContenidoExistenteException;

import java.util.*;

public class Plataforma {
    private String nombre;
    private List<Contenido> contenido;
    private Map<Contenido, Integer> visualizaciones;

    public Plataforma(String nombre) {
        this.nombre = nombre;
        this.contenido = new ArrayList<>();
        this.visualizaciones = new HashMap<>();
    }


    public void agregar(Contenido elemento){
        Contenido contenido = this.buscarTitulo(elemento.getTitulo());
        if(contenido != null){
            throw new ContenidoExistenteException(elemento.getTitulo());
        }
        FilesUtils.escribriContenido(elemento);
        this.contenido.add(elemento);

    }

    public void reproducir(Contenido contenido){
        int conteo = visualizaciones.getOrDefault(contenido, 0);
        System.out.println(contenido.getTitulo() + " Ha sido reproducida " + conteo + " veces");
        this.contarVisualizaciones(contenido);
        contenido.reproducir();

    }

    public void contarVisualizaciones(Contenido contenido){
        int contarVisualizacion = visualizaciones.getOrDefault(contenido, 0);
        visualizaciones.put(contenido,contarVisualizacion + 1 );
    }
    
    public List<String> mostrarTitulo(){
        return contenido.stream()
                .map(Contenido::getTitulo)
                .toList();
    }

    public List<ResumenContenido> getResumen(){
        return contenido.stream()
                .map(c -> new ResumenContenido(c.getTitulo(), c.getDuracion(), c.getGenero()))
                .toList();
    }

    public void eliminar (Contenido elemento){
        this.contenido.remove(elemento);
    }

    public Contenido buscarTitulo (String titulo){
        return contenido.stream()
                .filter(contenido -> contenido.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .orElse(null);

    }

    public List<Contenido> buscarporGenero(Genero genero){
        return contenido.stream()
                .filter(contenido -> contenido.getGenero().equals(genero))
                .toList();
    }

    public List<Contenido> buscarPorIdioma(Lenguage lenguage){
        return contenido.stream()
                .filter(contenido -> contenido.getLenguage().equals(lenguage))
                .toList();
    }

    public int getDuracionTotal(){
        return contenido.stream()
                .mapToInt(Contenido::getDuracion)
                .sum();
    }

    public List<Contenido> getPopular(int cantidad){
        return contenido.stream()
                .sorted(Comparator.comparingDouble(Contenido::getCalificacion).reversed())
                .limit(cantidad)
                .toList();
    }

    public List<Pelicula> getPeliculas() {
        return contenido.stream()
                .filter(contenido -> contenido instanceof Pelicula)
                .map(contenidoFiltrado -> (Pelicula) contenidoFiltrado)
                .toList();
    }

    public List<Documental> getDocumentales() {
        return contenido.stream()
                .filter(contenido -> contenido instanceof Documental)
                .map(contenidoFiltrado -> (Documental) contenidoFiltrado)
                .toList();
    }

    public List<Promocionar> getPromocionar(){
        return contenido.stream()
                .filter(contenido -> contenido instanceof Promocionar)
                .map(contenidoProm -> (Promocionar) contenidoProm)
                .toList();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Contenido> getContenido() {
        return contenido;
    }


}
