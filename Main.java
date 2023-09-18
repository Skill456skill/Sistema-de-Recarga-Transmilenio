import java.util.Scanner;
import java.util.Random;
import java.lang.Math;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Main {

    // VARIABLES
    public static Scanner sc = new Scanner(System.in);
    public static Random ram = new Random();
    public static int codigo = ram.nextInt(1000000, 10000000), indalea, saldo = ram.nextInt(-2950, 10000000), opcion,
            recarga, pasaje = 2950;
    public static String nombalea;

    // Esta variable va a ser temporal (Durante la primera entrega, mientras
    // Se averigua como leer el nombre del usuario a traves de la tarjeta fisica)
    public static String[] nombres = { "Alejandra", "Karolina", "Dylan", "Javier", "Anónimo" };
    public static Double valorbillet = 0.0, vueltas;
    // FIN VARIABLES

    // METODO PRINCIPAL
    public static void main(String[] args) {

        // FECHA
        mostrarfecha();
        // FIN FECHA

        // GENERAR CODIGO DE TARJETA ---------> PRUEBA SALIDA DE CODIGO TARJETA

        // Aqui va la parte en donde se obtiene el usuario y el codigo
        // Esto a traves del escaneo de la tarjeta (De un arduino o de un Escaner)

        indalea = ram.nextInt(nombres.length);
        nombalea = nombres[indalea];
        System.out.println("\n----------------------------------------");
        System.out.println("Codigo: " + codigo); // ---> Aqui se simula como si se escaneara el codigo de la tarjeta

        if (nombalea != "Anónimo") {
            System.out.println("!! Bienvenido Usuari@ " + nombalea + "!!");

        }

        // FIN GENERAR CODIGO DE TARJETA ------------> PRUEBA SALIDA DE CODIGO TARJETA
        // MENU

        // Este menu debe mejorarse debido a que no se tiene totalidad
        // Del Apartado de los datos de Usuario que obtendran
        // En el escaneo previo de la tajeta (POR EL MOMENTO
        // SE DISEÑA UN APARTADO DE USUARIO DE PRUEBA)

        while (opcion != 1 || opcion != 2) {

            mostrarPerfil();
            System.out.println("   ¿Que desea hacer? ");
            System.out.println("1. Recargar");
            System.out.println("2. Cancelar");
            System.out.println("Por favor, ingrese 1 o 2: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    recargar();
                    break;
                case 2:
                    cancelar();
                    break;
                default:
                    System.out.println("ERROR: Proceso Inválido\n");
                    break;
            }
            if (opcion == 2) {
                break;
            }

            // FIN MENU
        }
        // FIN METODO PRINCIPAL
    }

    // CASOS DE RECARGA Y CANCELACION
    public static void recargar() {
        System.out.println("\n----------------------------------------");
        System.out.println("Codigo: " + codigo);
        System.out.println("Usuario: " + nombalea);

        // OPCIONES DE RECARGA
        System.out.println("Su saldo es: $" + saldo);
        System.out.println("Recarga minima: $" + pasaje);
        // FIN OPCIONES DE RECARGA

        // SISTEMA ENTRADA DE RECARGAS
        System.out.println("\nIngrese cuanto desea recargar: ");
        recarga = sc.nextInt();
        System.out.println();
        System.out.println("¿Con cuanto desea recargar? $");
        valorbillet = sc.nextDouble();
        // FIN ENTRADA DE RECARGAS

        // RECARGA NORMAL
        if (saldo >= pasaje && recarga > 0 && recarga >= 50) {
            saldo += recarga;
            System.out.println("TRANSACCION EXITOSA");

            // DEVUELTAS
            vueltas = valorbillet - recarga;
            System.out.println("VUELTAS: $" + Math.round(vueltas));
            System.out.println();
            // FIN DEVUELTAS

        } else if (saldo < 0 && recarga > 0 && recarga >= 50) {
            saldo += recarga;
            System.out.println("TRANSACCION EXITOSA");
            System.out.println("Se desconto su deuda del valor total de la recarga");

            // DEVUELTAS
            vueltas = valorbillet - recarga;
            System.out.println("VUELTAS: $" + Math.round(vueltas));
            System.out.println();
            // FIN DEVUELTAS

        } else if (saldo < 0 || recarga <= 0 || recarga < 50) {
            System.out.println("RECARGA INVALIDA");
            System.out.println("No fue posible efectuar la recarga\n");
        }
        System.out.println("----------------------------------------\n");
        // FIN RECARGA NORMAL
    }

    public static void cancelar() {

        System.out.println("\n----------------------------------------");
        System.out.println();
        String cancelcod = String.valueOf(codigo);
        System.out.println("Condigo: " + cancelcod);
        System.out.println("Usuari@ " + nombalea);
        System.out.println("Gracias por usar nuestro servicio !!");
        System.out.println("----------------------------------------\n");
    }
    // FIN CASOS

    // PERFIL
    public static void mostrarPerfil() {

        // INICIO DEL PERFIL
        System.out.println("\n----------------------------------------");
        System.out.println("PERFIL: " + nombalea);

        // SALDO
        // El tema del saldo es temporal mientras se realiza lo del escaneo de la
        // tarjeta
        if (saldo < 0) {
            System.out.println("Su saldo es: $" + saldo);
            System.out.println("Recarga minima: $" + pasaje);
        } else if (saldo >= 0) {
            System.out.println("Su saldo es: $" + saldo);
        }
        // FIN SALDO
        System.out.println("Cantidad ingresada: $" + Math.round(valorbillet));

        System.out.println("----------------------------------------\n");
        // FIN DEL PERFIL

    }

    public static void mostrarfecha() {

        // FECHA

        // Se utiliza la clase Date para poder obtener la fecha actual en el sistema
        Date fechaActual = new Date();

        // Se utiliza la clase SimpleDateFormat para poder aplicarle un formato a la
        // fecha
        // Es decir colocarla en diferentes formatos, en este caso se utilizo en formato
        // de Día/Mes/Año

        SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yy");

        // MOSTRAR FECHA

        // Finalmente Aqui se llama a la variable asignada al SimpleDateFormat "fecha",
        // luego
        // Se utiliza el metodo .format seguido dentro de unos parentesis de la variable
        // asignada
        // a la clase Date, esto para poder indicar que le aplique el formato dd/MM/yy a
        // esa variable
        // y lo muestre por consola
        System.out.println("\n" + fecha.format(fechaActual));

        // FIN MOSTRAR FECHA

        // FECHA

    }

}
