package menea.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class Bitacora implements Serializable {

    private static final ArrayList<Evento> eventos = new ArrayList<>();

    //CONSTRUCCION
    private Bitacora() {
    }

    public static void registrarEvento(EventType tipo, String jugadorPrincipal, String jugadorObjetivo, String detalles) {
        Evento evento = new Evento(tipo, jugadorPrincipal, jugadorObjetivo, detalles);
        eventos.add(evento);
    }

    //para eventos sin objetivo a atacar
    public static void registrarEvento(EventType tipo, String jugadorPrincipal, String detalles) {
        Evento evento = new Evento(tipo, jugadorPrincipal, detalles);
        eventos.add(evento);
    }

    public static String obtenerBitacoraTexto() {
        StringBuilder sb = new StringBuilder();
        for (Evento evento : eventos) {
            sb.append(evento.toLogMessage()).append("\n");
        }
        return sb.toString();
    }

    public static String obtenerEventosJugadorTexto(String nombreJugador) {
        StringBuilder sb = new StringBuilder();
        for (Evento evento : eventos) {
            if (evento.involucraJugador(nombreJugador)) {
                sb.append(evento.toLogMessage()).append("\n");
            }
        }
        return sb.toString();
    }

    public static String obtenerAtaquesJugadorTexto(String nombreJugador) {
        StringBuilder sb = new StringBuilder();
        for (Evento evento : eventos) {
            if (evento.getTipo() == EventType.ATAQUE && evento.involucraJugador(nombreJugador)) {
                sb.append(evento.toLogMessage()).append("\n");
            }
        }
        return sb.toString();
    }

    //limpia bitacora para reiniciar partida
    public static void limpiar() {
        eventos.clear();
    }

    public static int getTotalEventos() {
        return eventos.size();
    }
}
