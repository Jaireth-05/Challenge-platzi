package platzi_play.contenido;

public class Documental extends Contenido implements Promocionar{

    private String narrador;
    public Documental(String titulo, Genero genero, int duracion, double calificar, Lenguage lenguage) {
        super(titulo, genero, duracion, calificar, lenguage);
    }


    public Documental(String titulo, int duracion, Genero genero, double calificacion, Lenguage lenguage, String narrador) {
        super(titulo, genero, duracion, calificacion, lenguage);
        this.narrador = narrador;
    }

    @Override
    public void reproducir() {
        System.out.println("Reproduciendo: " + getTitulo() + " con el/la narrador/a: " + getNarrador());
    }


    @Override
    public String promocionar() {
        return "No te pierdas de " + this.getTitulo() + " narrado por " + narrador + ". ¡Solo aquí en platzi play!";
    }

    public String getNarrador() {
        return narrador;
    }


}
