package menea.Models;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

public class Evento implements Serializable {


    private final EventType tipo;
    private final String jugadorPrincipal;
    private final String jugadorObjetivo;
    private final String detalles;

    //CONSTRUCTOR
    public Evento(EventType tipo, String jugadorPrincipal, String jugadorObjetivo, String detalles) {
        this.tipo = tipo;
        this.jugadorPrincipal = jugadorPrincipal;
        this.jugadorObjetivo = jugadorObjetivo;
        this.detalles = detalles;
    }

    //CONSTRUCTOR para eventos sin objetivo a atacar
    public Evento(EventType tipo, String jugadorPrincipal, String detalles) {
        this(tipo, jugadorPrincipal, null, detalles);
    }

    //GETTERS
    public EventType getTipo() {
        return tipo;
    }

    public String getJugadorPrincipal() {
        return jugadorPrincipal;
    }

    public String getJugadorObjetivo() {
        return jugadorObjetivo;
    }

    public String getDetalles() {
        return detalles;
    }

    
    public boolean involucraJugador(String nombreJugador) {
        return nombreJugador.equals(jugadorPrincipal) || nombreJugador.equals(jugadorObjetivo);
    }


    //mensaje que se muestra en la bitacora
    public String toLogMessage() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        StringBuilder sb = new StringBuilder();

        switch (tipo) {
            case CREAR_PERSONAJE:
                sb.append(jugadorPrincipal).append(" ha creado un personaje: ").append(detalles);
                break;
            case INICIAR_PARTIDA:
                sb.append("Partida iniciada: ").append(detalles);
                break;
            case ATAQUE:
                if (jugadorObjetivo != null) {
                    sb.append(jugadorPrincipal).append(" atacó a ").append(jugadorObjetivo)
                      .append(" - ").append(detalles);
                } else {
                    sb.append(jugadorPrincipal).append(" ejecutó ataque: ").append(detalles);
                }
                break;
            case SALTO_TURNO:
                sb.append(jugadorPrincipal).append(" saltó su turno");
                break;
            case RENDIRSE:
                sb.append(jugadorPrincipal).append(" se ha rendido");
                break;
            case FIN_PARTIDA:
                sb.append("Partida finalizada: ").append(detalles);
                break;
            case OTRO:
                sb.append(detalles);
                break;
        }

        return sb.toString();
    }

    @Override
    public String toString() {
        return toLogMessage();
    }
}
