import java.util.Scanner;

public class Menu {
    private Scanner lectura;
    private ConsultarMonedas consulta;
    private HistorialConversiones historial;

    public Menu(Scanner lectura, ConsultarMonedas consulta, HistorialConversiones historial) {
        this.lectura = lectura;
        this.consulta = consulta;
        this.historial = historial;
    }

    public void mostrarMenuPrincipal() {
        boolean continuar = true;

        while (continuar) {
            System.out.println("""
                    ********** Seleccione una opcion: **********
                    1. Realizar una conversion:
                    2. Consultar historial de conversiones:
                    3. Salir
                    *************************************************""");
            int opcion = Integer.parseInt(lectura.nextLine().trim());

            switch (opcion) {
                case 1:
                    mostrarMenuConversiones();
                    break;
                case 2:
                    mostrarHistorial();
                    break;
                case 3:
                    continuar = false;
                    System.out.println("Saliendo del programa.");
                default:
                    System.out.println("Opcion no válida");
            }
        }
    }

    private void mostrarMenuConversiones() {
        boolean regresar = false;
        while (!regresar) {
            System.out.println("""
                ********** Seleccione una opcion de conversión **********
                1. Dólar =>>> Peso argentino
                2. Peso argentino =>>> Dólar
                3. Dólar =>>> Real brasileño
                4. Real brasileño =>>> Dólar
                5. Dólar =>>> Peso colombiano
                6. Peso colombiano =>>> Dólar
                7. Peso mexicano =>>> Dólar
                8. Dólar =>>> Peso mexicano
                9. Indica tus propias monedas
                0. Regresar al menú principal
                *************************************************""");
            int opcionConversion = Integer.parseInt(lectura.nextLine().trim());

            switch (opcionConversion) {
                case 1:
                    realizarConversion("USD", "ARS");
                    break;
                case 2:
                    realizarConversion("ARS", "USD");
                    break;
                case 3:
                    realizarConversion("USD", "BRL");
                    break;
                case 4:
                    realizarConversion("BRL", "USD");
                    break;
                case 5:
                    realizarConversion("USD", "COP");
                    break;
                case 6:
                    realizarConversion("COP", "USD");
                    break;
                case 7:
                    realizarConversion("MXN", "USD");
                    break;
                case 8:
                    realizarConversion("USD", "MXN");
                    break;
                case 9:
                    ingresarPropiasMonedas();
                    break;
                case 0:
                    regresar = true;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    private void realizarConversion(String monedaBase, String monedaDestino) {
        System.out.println("Escriba la cantidad a convertir:");
        double cantidad = Double.parseDouble(lectura.nextLine().trim());

        try {
            double resultadoConversion = consulta.convertirMoneda(monedaBase, monedaDestino, cantidad);
            String resultado = String.format("""
                *************************************************
                Resultado de la conversion: %.2f %s es igual a %.2f %s
                *************************************************""", cantidad, monedaBase, resultadoConversion, monedaDestino);
            System.out.println(resultado);
            historial.agregarConversion(resultado);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void ingresarPropiasMonedas() {
        System.out.println("""
                ********** Bienvenido aqui puedes ingresar los Codigos de moneda que desees **********
                Toma en cuenta que tienes que ingresar un codigo de moneda que exista, la unica moneda no admitida es:
                WON NORCOREANO (WPW)
                Asi que por favor ingresa codigos de monedas de tres letras ISO 4217
                *************************************************
                """);
        System.out.println("Ingrese la moneda base:");
        String monedaBase = lectura.nextLine().trim();
        System.out.println("Ingrese la moneda destino:");
        String monedaDestino = lectura.nextLine().trim();
        realizarConversion(monedaBase, monedaDestino);
    }

    private void mostrarHistorial() {
        System.out.println("********** Historial de conversiones: **********");
        for (String conversion : historial.obtenerHistorial()) {
            // Eliminamos los asteriscos de la cadena de conversión
            String conversionSinAsteriscos = conversion.replace("*", "");
            System.out.println(conversionSinAsteriscos);
            System.out.println("*************************************************");
        }
    }
}
