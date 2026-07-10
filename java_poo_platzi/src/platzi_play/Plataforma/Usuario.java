/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package platzi_play.Plataforma;
import platzi_play.contenido.Contenido;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author jaire
 */
public class Usuario {
    private String nombre;
    private String email;
    private LocalDateTime fechaRegistro;
    private String Pasword;

    public Usuario(String nombre, String email) {
        this.nombre = nombre;
        this.email= email;
        this.fechaRegistro = LocalDateTime.now();
    }

    public void ver(Contenido pelicula){
        System.out.println(nombre + " Está viendo ...");
        pelicula.reproducir();
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public String getPasword() {
        return Pasword;
    }

    public void setPasword(String pasword) {
        Pasword = pasword;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
