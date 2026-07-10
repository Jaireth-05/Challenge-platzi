/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package platzi_play.contenido;
import java.time.LocalDate;
/**
 *
 * @author jaire
 */
public abstract class Contenido {
    private String titulo;
    private String descripcion;
    private int duracion;
    private Genero genero;
    private LocalDate fechaEstreno;
    private double calificacion;
    private boolean disponible;
    private Lenguage lenguage;



    public Contenido(String titulo, Genero genero, int duracion, double calificar,Lenguage lenguage) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.genero = genero;
        this.calificacion = calificar;
        this.fechaEstreno = LocalDate.now();
        this.lenguage = lenguage;
    }

    public Contenido(String titulo, int duracion,Genero genero, double calificacion, Lenguage lenguage) {
        this(titulo, genero, duracion, calificacion, lenguage);
        this.calificar(calificacion);
    }

    public abstract void reproducir();
    
    public String obtenerFichaTecnica(){
        return titulo +" ("+ fechaEstreno.getYear() +")" + "\n" +
                "Descripción: " + descripcion + "\n"+
                "Genero: " + genero + " \n" +
                "Duracion: " +duracion + " \n" +
                "Calificaión: "+calificacion + "/5";
    }
    
    public void calificar(double calificacion){
        if(calificacion >=0 && calificacion <=5){
            this.calificacion = calificacion;
        }
    }
    
    public boolean esPopular(){
        return calificacion >= 4;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getDuracion() {
        return duracion;
    }

    public Genero getGenero() {
        return genero;
    }

    public Lenguage getLenguage(){
        return lenguage;
    }

    public LocalDate getFechaEstreno() {
        return fechaEstreno;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public void setFechaEstreno(LocalDate fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
