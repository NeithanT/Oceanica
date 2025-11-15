package menea.Models;

public class CommandHelp {
    
    public static String help() {
        return "COMANDOS DISPONIBLES:\n" +
               "- HELP ; SIN ARGUMENTOS, ESTE REGISTRO\n" +
               "- HELP ATTACK ; 1 ARGUMENTO, MUESTRA AYUDA DE ATAQUES\n" +
               "==================================================\n" +
               "- CREATE [] ; Sin args, abre un frame para que cree un personaje\n" +
               "- CREATE [NOMBRE:STR] [IMAGEN:STR] [TIPO_ATAQUE:STR/ENUM] [CONTROL_DEL_MAPA:INT] [FUERZA:INT] [RESISTENCIA:INT] [SALUD:INT]\n" +
               "- CREATE [NOMBRE:STR] [RUTA_ABSOLUTA_IMAGEN:STR] [TIPO_ATAQUE:STR/ENUM] [CONTROL_DEL_MAPA:INT] [FUERZA:INT] [RESISTENCIA:INT] [SALUD:INT] ; HACER TODAS LAS VERIFICACIONES, MENOS QUE 3 JUGADORES, QUE TODO SEA VALIDO, ETC\n" +
               "- READY ; SIN ARGUMENTOS, CONECTA CON EL SOCKET CUANDO LOS 3 LUCHADORES ESTAN LISTOS\n" +
               "- ATTACK [18 TIPOS DE ATAQUE] [DIFERENTES ARGUMENTOS]; HELP ATTACK PARA MAS INFO\n" +
               "- SKIP ; SALTA TURNO\n" +
               "- SURRENDER ; PIERDE EL JUEGO\n" +
               "- CELL [X:INT] [Y:INT] ; VERIFICAR QUE SEA CELDA VALIDA, MOSTRAR LOG DE ACCIONES EN BITACORA\n" +
               "- LOG ; MUESTRA LA BITACORA DE EVENTOS\n" +
               "- LOGATTACK ; MUESTRA EN LOS ATAQUES, TODOS LOS ATAQUES RECIBIDOS Y MANDADOS\n" +
               "- LOGRESUMEN ; SIN ARGS, MUESTRA ATAQUES REALIZADOS, PORCENTAJE DE EXITO, CUANTOS ATINARON Y CUANTOS NO.\n" +
               "- LOGENEMY ; SIN ARGS, MUESTRA EL ESTADO DE TODOS LOS ENEMIGOS\n" +
               "- LOGDAMAGE ; MUESTRA EN EL MAPA LAS CELDAS OCUPADAS POR VOLCANES, REMOLINOS\n" +
               "- LOGCELLS ; MUESTRA EN EL MAPA LA VIDA DE CADA CELDA\n" +
               "- LOGALIVE ; ROJO TODAS LAS CELDAS MUERTAS, VERDE LAS OTRAS";
    }
    
    public static String helpAttack() {
        return "Attack [metodo] [parámetros] <- cambian segun el ataque \n" +
               "Métodos disponibles dependen de tu tipo de ataque seleccionado:\n\n" +
               "FISH_TELEPHATY:\n" +
               "  - ATTACK CARDUMEN\n" +
               "  - ATTACK SHARK\n" +
               "  - ATTACK PULP\n\n" +
               "THUNDERS_UNDER_THE_SEA:\n" +
               "  - ATTACK THUNDERRAIN\n" +
               "  - ATTACK POSEIDON\n" +
               "  - ATTACK ELATTACK\n\n" +
               "RELEASE_THE_KRAKEN:\n" +
               "  - ATTACK TENTACLES <f1> <c1> <f2> <c2> <f3> <c3>\n" +
               "  - ATTACK BREATH <fila> <col> <direccion>\n" +
               "  - ATTACK KRAKEN <fila> <col> (no coordenadas = ataque aleatorio )\n\n" +
               "THE_TRIDENT:\n" +
               "  - ATTACK THREELINES <fila1> <col1> <fila2> <col2> <fila3> <col3\n" +
               "  - ATTACK THREENUMBERS <num1> <num2> <num3>\n" +
               "  - ATTACK CONTROLKRAKEN\n\n" +
               "UNDERSEA_VOLCANOES:\n" +
               "  - ATTACK VOLCANORAISING <>\n" +
               "  - ATTACK VOLCANOEXPLOSION <>\n" +
               "  - ATTACK TERMALRUSH <>\n\n" +
               "WAVES_CONTROL:\n" +
               "  - ATTACK SWIRLRAISING <fila1> <col1>\n" +
               "  - ATTACK SENDHUMANGARDBAGE <>\n" +
               "  - ATTACK RADIACTIVERUSH <>\n\n";
    }

}

