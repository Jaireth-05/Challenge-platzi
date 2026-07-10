package platzi_play.contenido;

public class Pelicula extends Contenido{


    public Pelicula(String titulo, Genero genero, int duracion, double calificar, Lenguage lenguage) {
        super(titulo, genero, duracion, calificar, lenguage);
    }


    public Pelicula(String titulo, int duracion, Genero genero, double calificacion, Lenguage lenguage) {
       super(titulo, duracion, genero, calificacion, lenguage);
   }

    @Override
    public void reproducir() {
        System.out.println("Reproduciendo: " + getTitulo());
    }
}
