package platzi_play.exception;

public class ContenidoExistenteException extends RuntimeException{
    public ContenidoExistenteException(String titulo){
        super("El contenido " + titulo + " ya existe!");
    }
}
