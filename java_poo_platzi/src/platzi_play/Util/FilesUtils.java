package platzi_play.Util;

import platzi_play.contenido.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FilesUtils {
    public static final String NOMBRE_ARCHIVO = "contenido.txt";
    public static final String SEPARADOR = "|";

    public static void escribriContenido(Contenido contenido){
        String lineaComun = String.join(SEPARADOR,
                contenido.getTitulo(),
                contenido.getGenero().name(),
                String.valueOf(contenido.getDuracion()),
                String.valueOf(contenido.getCalificacion()),
                contenido.getLenguage().name(),
                contenido.getFechaEstreno().toString()
        );

        String lineaFinal;
        if (contenido instanceof Documental documental) {
            lineaFinal = "DOCUMENTAL" + SEPARADOR + lineaComun + SEPARADOR + documental.getNarrador();
        } else {
            lineaFinal = "PELICULA" + SEPARADOR + lineaComun;
        }

        try {
            Files.writeString(Paths.get(NOMBRE_ARCHIVO),
                    lineaFinal + System.lineSeparator(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (Exception e) {
            System.out.println("Error escribiendo el Archivo " + e.getMessage());
        }
    }

    public static List<Contenido> leerContenido() {
        List<Contenido> contenidoDesdeArchivo = new ArrayList<>();

        try {
            List<String> lineas = Files.readAllLines(Paths.get(NOMBRE_ARCHIVO));

            for (String linea : lineas) {
                if (linea.isBlank()) continue;

                String[] datos = linea.split("\\" + SEPARADOR, -1);
                String tipoContenido = datos[0];

                try {
                    String titulo = datos[1];
                    String generoStr = datos[2];
                    int duracion = Integer.parseInt(datos[3]);
                    double calificacion = datos[4].isBlank() ? 0 : Double.parseDouble(datos[4]);
                    Lenguage lenguage = Lenguage.ENGLISH;
                    LocalDate fechaEstreno;
                    String narrador = "Desconocido";

                    Genero genero = Genero.valueOf(generoStr.toUpperCase());


                    if ("PELICULA".equals(tipoContenido)) {
                        if (datos.length >= 7) {
                            lenguage = datos[5].isBlank() ? Lenguage.ENGLISH : Lenguage.valueOf(datos[5].toUpperCase());
                            fechaEstreno = LocalDate.parse(datos[6]);
                        } else {

                            fechaEstreno = LocalDate.parse(datos[5]);
                        }

                        Contenido contenido = new Pelicula(titulo, genero, duracion, calificacion, lenguage);
                        contenido.setFechaEstreno(fechaEstreno);
                        contenidoDesdeArchivo.add(contenido);

                    } else if ("DOCUMENTAL".equals(tipoContenido)) {

                        if (datos.length == 6) {
                            fechaEstreno = LocalDate.parse(datos[5]);
                        }
                        else if (datos.length == 7) {
                            lenguage = datos[5].isBlank() ? Lenguage.ENGLISH : Lenguage.valueOf(datos[5].toUpperCase());
                            fechaEstreno = LocalDate.parse(datos[6]);
                        }
                        else {
                            lenguage = datos[5].isBlank() ? Lenguage.ENGLISH : Lenguage.valueOf(datos[5].toUpperCase());
                            fechaEstreno = LocalDate.parse(datos[6]);
                            narrador = datos[7].isBlank() ? "Desconocido" : datos[7];
                        }

                        Contenido contenido = new Documental(titulo,duracion,genero, calificacion, lenguage, narrador);
                        contenido.setFechaEstreno(fechaEstreno);
                        contenidoDesdeArchivo.add(contenido);
                    }

                } catch (Exception e) {
                    System.out.println("Error procesando la línea [" + linea + "]: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("Error general al leer el archivo: " + e.getMessage());
        }

        return contenidoDesdeArchivo;
    }
}